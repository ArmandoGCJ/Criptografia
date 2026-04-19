package controller;

import models.*;

public class ControladorCifrado {

    private CifradoCesar    cesar    = new CifradoCesar();
    private CifradoAtbash   atbash   = new CifradoAtbash();
    private CifradoVigenere vigenere = new CifradoVigenere();
    private CifradoRailFence rail    = new CifradoRailFence();
    private CifradoPlayfair playfair = new CifradoPlayfair();

    private String idioma = "ES";

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }


    private String getAlfabeto() {
        return idioma.equals("ES") ? Alfabeto.ES : Alfabeto.EN;
    }

    private String getAlfabetoPlayfair() {
        return idioma.equals("ES") ? Alfabeto.ES_PLAYFAIR : Alfabeto.EN_PLAYFAIR;
    }


    public String cifrar(String metodo, String texto, String clave) {
        switch (metodo) {

            case "CESAR":
                int desp = parsearDesplazamiento(clave, 3);
                return cesar.cifrar(texto, desp, getAlfabeto());

            case "ATBASH":
                return atbash.cifrar(texto, getAlfabeto());

            case "VIGENERE":
                String claveVig = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return vigenere.cifrar(texto, claveVig, getAlfabeto());

            case "RAIL":
                int rails = parsearRails(clave, 3);
                return rail.cifrar(texto, rails);

            case "PLAYFAIR":
                String clavePF = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return playfair.cifrar(texto, clavePF, getAlfabetoPlayfair());
        }
        return texto;
    }



    public String descifrar(String metodo, String texto, String clave) {
        switch (metodo) {

            case "CESAR":
                int desp = parsearDesplazamiento(clave, 3);
                return cesar.descifrar(texto, desp, getAlfabeto());

            case "ATBASH":
                return atbash.cifrar(texto, getAlfabeto()); // Atbash es involuntario

            case "VIGENERE":
                String claveVig = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return vigenere.descifrar(texto, claveVig, getAlfabeto());

            case "RAIL":
                int rails = parsearRails(clave, 3);
                return rail.descifrar(texto, rails);

            case "PLAYFAIR":
                String clavePF = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return playfair.descifrar(texto, clavePF, getAlfabetoPlayfair());
        }
        return texto;
    }


    private int parsearDesplazamiento(String clave, int defecto) {
        try {
            int v = Integer.parseInt(clave.trim());
            return v >= 1 ? v : defecto;
        } catch (NumberFormatException e) {
            return defecto;
        }
    }

    private int parsearRails(String clave, int defecto) {
        try {
            int v = Integer.parseInt(clave.trim());
            return v >= 2 ? v : defecto;
        } catch (NumberFormatException e) {
            return defecto;
        }
    }
}
