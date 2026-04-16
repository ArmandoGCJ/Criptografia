package controller;

import models.*;

public class ControladorCifrado {

    private CifradoCesar cesar = new CifradoCesar();
    private CifradoAtbash atbash = new CifradoAtbash();
    private CifradoVigenere vigenere = new CifradoVigenere();
    private CifradoRailFence rail = new CifradoRailFence();
    private CifradoPlayfair playfair = new CifradoPlayfair();


    public String cifrar(String metodo, String texto, String clave) {
        switch (metodo) {
            case "CESAR":
                return cesar.cifrar(texto, 3);
            case "ATBASH":
                return atbash.cifrar(texto);
            case "VIGENERE":
                String claveVig = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return vigenere.cifrar(texto, claveVig);
            case "RAIL":
                int rails = parsearRails(clave, 3);
                return rail.cifrar(texto, rails);
            case "PLAYFAIR":
                String clavePF = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return playfair.cifrar(texto, clavePF);
        }
        return texto;
    }


    public String descifrar(String metodo, String texto, String clave) {
        switch (metodo) {
            case "CESAR":
                return cesar.descifrar(texto, 3);
            case "ATBASH":
                return atbash.cifrar(texto);
            case "VIGENERE":
                String claveVig = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return vigenere.descifrar(texto, claveVig);
            case "RAIL":
                int rails = parsearRails(clave, 3);
                return rail.descifrar(texto, rails);
            case "PLAYFAIR":
                String clavePF = clave.isBlank() ? "CLAVE" : clave.toUpperCase();
                return playfair.descifrar(texto, clavePF);
        }
        return texto;
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
