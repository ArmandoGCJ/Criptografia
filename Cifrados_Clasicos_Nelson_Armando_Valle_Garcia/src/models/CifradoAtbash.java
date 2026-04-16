package models;

public class CifradoAtbash {

    public String cifrar(String texto) {

        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toUpperCase().toCharArray()) {

            if (Character.isLetter(c)) {

                char nuevo = (char) ('Z' - (c - 'A'));

                resultado.append(nuevo);

            } else {

                resultado.append(c);

            }

        }

        return resultado.toString();

    }

}