package models;

public class CifradoVigenere {

    public String cifrar(String texto, String clave) {

        texto = texto.toUpperCase();
        clave = clave.toUpperCase();

        StringBuilder resultado = new StringBuilder();

        int j = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            if (Character.isLetter(c)) {

                int desplazamiento = clave.charAt(j % clave.length()) - 'A';

                char nuevo = (char) ((c - 'A' + desplazamiento) % 26 + 'A');

                resultado.append(nuevo);

                j++;

            } else {

                resultado.append(c);

            }

        }

        return resultado.toString();

    }

    public String descifrar(String texto, String clave) {

        texto = texto.toUpperCase();
        clave = clave.toUpperCase();

        StringBuilder resultado = new StringBuilder();

        int j = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            if (Character.isLetter(c)) {

                int desplazamiento = clave.charAt(j % clave.length()) - 'A';

                char nuevo = (char) ((c - 'A' - desplazamiento + 26) % 26 + 'A');

                resultado.append(nuevo);

                j++;

            } else {

                resultado.append(c);

            }

        }

        return resultado.toString();

    }

}

