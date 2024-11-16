package com.challenge.literalura.principal;

import com.challenge.literalura.model.*;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.service.ConsumoAPI;
import com.challenge.literalura.service.ConvierteDatos;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private int option;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        do {
            System.out.println("*****-----------BIENVENIDO A LITERALURA----------*****");
            System.out.println("Seleccione una opción de la lista: ");
            System.out.println("*****--------------------------------------------*****");
            showMenu();
            while (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next();
            }
            option = teclado.nextInt();
            teclado.nextLine();

            switch (option) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    System.out.print("Ingrese el año para revision : ");
                    int year = teclado.nextInt();
                    listarAutoresVivosEnAnio(year);

                    break;
                case 5:
                    System.out.println("""
                            Elija el idioma de su libro :
                            1. Español (es)
                            2. Ingles (en)
                            3. Frances (fr)
                            4. Portugues (pt)
                            5. Latin (la)
                            6. Aleman (de)
                            7. Italiano (it)
                            8. Hungaro (hu)
                            """);
                    String idiomaIngresado = teclado.nextLine().toUpperCase();
                    try {
                        Idiomas idiomaSeleccionado = Idiomas.valueOf(idiomaIngresado);
                        obtenerLibrosPorIdioma(idiomaSeleccionado);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Opción inválida. El idioma ingresado no es válido.");
                    }
                    break;
                case 0:
                    System.out.println("Usted ha saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
            }
            System.out.println();

        } while (option != 0);

        teclado.close();
    }


    //MENU DE OPCIONES
    private void showMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar libros por títulos");
        System.out.println("3. Listar autores registrados");
        System.out.println("4. Listar autores vivos en un determinado año");
        System.out.println("5. Listar libros por idiomas");
        System.out.println("0. Salir");
    }


    public DatosLibros getLibroDeAPI() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosAPI.class);

        return datosBusqueda.datosResultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    public void buscarLibroPorTitulo() {
        DatosLibros datosLibros = getLibroDeAPI();
        if (datosLibros != null) {
            Libro libro = new Libro(datosLibros);
            libroRepository.save(libro);
            System.out.println(libro);
        } else {
            System.out.println("No se encontró el libro especificado.");
        }
    }

    public void listarLibros(){
        List<Libro> libroList = libroRepository.findAll();
        if(libroList.isEmpty()){
            System.out.println("No hay libros para mostrar");
        }else {
            for (Libro libro: libroList){
                System.out.println(libro);
            }
        }
    }
    public void listarAutores(){
        List<Autor> autorList = autorRepository.findAll();
        if(autorList.isEmpty()){
            System.out.println("No hay autores para mostrar");
        }else {
            for (Autor autor: autorList){
                System.out.println(autor);
            }
        }
    }

    public void listarAutoresVivosEnAnio(int anio) {
        System.out.println("Autores vivos en el año " + anio + ":\n");
        List<Autor> autoresVivos = autorRepository.findAll().stream()
                .filter(autor -> {
                    try {
                        int nacimiento = Integer.parseInt(autor.getAnioNacimiento());
                        if (autor.getAnioFallecimient() == null || autor.getAnioFallecimient().isEmpty()) {
                            return nacimiento <= anio;
                        }
                        int fallecimiento = Integer.parseInt(autor.getAnioFallecimient());
                        return nacimiento <= anio && fallecimiento > anio;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    public List<Libro> obtenerLibrosPorIdioma(Idiomas idioma) {
        List<Libro> libros = libroRepository.findAll();
        List<Libro> librosFiltrados = libros.stream()
                .filter(libro -> libro.getIdiomas().equals(idioma))
                .collect(Collectors.toList());
        if (librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + idioma);
        } else {
            System.out.println("Libros en el idioma " + idioma + ":");
            librosFiltrados.forEach(libro -> System.out.println(libro));
        }
        return librosFiltrados;
    }
}






