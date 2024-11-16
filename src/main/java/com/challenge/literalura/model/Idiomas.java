package com.challenge.literalura.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Idiomas {
    ESPAÑOL("es","español"),
    INGLES ("en","ingles"),
    FRANCES("fr", "frances"),
    PORTUGUES("pt","portugues"),
    LATIN("la", "latin"),
    ALEMAN("de", "aleman"),
    ITALIANO("it", "italiano"),
    HUNGARO("hu","hungaro");

    private String idiomaAbreviado;
    private String idiomaCompleto;

    Idiomas(String idiomaAbreviado, String idiomaCompleto) {
        this.idiomaAbreviado = idiomaAbreviado;
        this.idiomaCompleto = idiomaCompleto;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas: Idiomas.values()) {
            if (idiomas.idiomaAbreviado.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }
    public static Idiomas fromTotalString(String text) {
        for (Idiomas idiomas: Idiomas.values()) {
            if (idiomas.idiomaCompleto.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }

    public static List<String> getIdiomasDisponibles() {
        return Arrays.stream(Idiomas.values())
                .map(Idiomas::name)
                .collect(Collectors.toList());
    }
}
