package com.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double numeroDeDescargas;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();

        if (!datosLibros.idiomas().isEmpty()) {
            this.idiomas = Idiomas.fromString(datosLibros.idiomas().get(0));
        } else {
            throw new IllegalArgumentException("No se proporcionó un idioma válido");
        }

        if (!datosLibros.autor().isEmpty()) {
            DatosAutor datosAutor = datosLibros.autor().get(0);
            this.autor = new Autor(datosAutor);
        }
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "******LIBRO******\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNombre() : "Autor no disponible") + "\n" +
                "Idioma: " + (idiomas != null ? idiomas.name() : "Idioma no disponible") + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "--------------------\n";
    }

}
