package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String anioNacimiento;

    private String anioFallecimient;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();


    public Autor() {}
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioFallecimient = datosAutor.anioFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public String getAnioFallecimient() {
        return anioFallecimient;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public String toString() {
        StringBuilder librosString = new StringBuilder("[");
        for (Libro libro : libros) {
            librosString.append(libro.getTitulo()).append(", ");
        }
        if (!libros.isEmpty()) {
            librosString.setLength(librosString.length() - 2);
        }
        librosString.append("]");
        return "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + anioNacimiento + "\n" +
                "Fecha de fallecimiento: " + anioFallecimient + "\n" +
                "Libros: " + librosString.toString()+ "\n" ;
    }
}
