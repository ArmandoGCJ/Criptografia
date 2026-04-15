package models;

public class CifradoCesar {

    public String cifrar(String texto, int desplazamiento) {

        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {

            if (Character.isLetter(c)) {

                char base = Character.isUpperCase(c) ? 'A' : 'a';

                c = (char) ((c - base + desplazamiento) % 26 + base);

            }

            resultado.append(c);

        }

        return resultado.toString();

    }

    public String descifrar(String texto, int desplazamiento) {

        return cifrar(texto, 26 - desplazamiento);

    }

}
