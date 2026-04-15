package controller;

import models.*;

public class ControladorCifrado {

    private CifradoCesar cesar = new CifradoCesar();
    private CifradoAtbash atbash = new CifradoAtbash();
    private CifradoVigenere vigenere = new CifradoVigenere();
    private CifradoRailFence rail = new CifradoRailFence();
    private CifradoPlayfair playfair = new CifradoPlayfair();

    public String cifrar(String metodo, String texto) {

        switch (metodo) {

            case "CESAR":
                return cesar.cifrar(texto, 3);

            case "ATBASH":
                return atbash.cifrar(texto);

            case "VIGENERE":
                return vigenere.cifrar(texto, "CLAVE");

            case "RAIL":
                return rail.cifrar(texto, 3);

            case "PLAYFAIR":
                return playfair.cifrar(texto, "CLAVE");

        }

        return texto;

    }

    public String descifrar(String metodo, String texto) {

        switch (metodo) {

            case "CESAR":
                return cesar.descifrar(texto, 3);

            case "ATBASH":
                return atbash.cifrar(texto);

            case "VIGENERE":
                return vigenere.descifrar(texto, "CLAVE");

            case "RAIL":
                return rail.descifrar(texto, 3);

            case "PLAYFAIR":
                return playfair.descifrar(texto, "CLAVE");
        }

        return texto;

    }

}

