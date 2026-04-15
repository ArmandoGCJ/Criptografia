package models;

import java.util.HashSet;

public class CifradoPlayfair {

    private char[][] matriz = new char[5][5];

    private void generarMatriz(String clave) {

        clave = clave.toUpperCase().replaceAll("J", "I");

        HashSet<Character> usados = new HashSet<>();

        String alfabeto = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        String texto = clave + alfabeto;

        int fila = 0;
        int col = 0;

        for (char c : texto.toCharArray()) {

            if (!usados.contains(c) && Character.isLetter(c)) {

                usados.add(c);

                matriz[fila][col] = c;

                col++;

                if (col == 5) {
                    col = 0;
                    fila++;
                }

                if (fila == 5)
                    break;
            }
        }
    }

    private int[] buscar(char letra) {

        if (letra == 'J')
            letra = 'I';

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                if (matriz[i][j] == letra) {

                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    private String prepararTexto(String texto) {

        texto = texto.toUpperCase().replaceAll("J", "I");
        texto = texto.replaceAll("[^A-Z]", "");

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {

            resultado.append(texto.charAt(i));

            if (i + 1 < texto.length()
                    && texto.charAt(i) == texto.charAt(i + 1)) {

                resultado.append('X');
            }
        }

        if (resultado.length() % 2 != 0) {
            resultado.append('X');
        }

        return resultado.toString();
    }

    public String cifrar(String texto, String clave) {

        generarMatriz(clave);

        texto = prepararTexto(texto);

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i += 2) {

            char a = texto.charAt(i);
            char b = texto.charAt(i + 1);

            int[] posA = buscar(a);
            int[] posB = buscar(b);

            if (posA[0] == posB[0]) {

                resultado.append(
                        matriz[posA[0]][(posA[1] + 1) % 5]);

                resultado.append(
                        matriz[posB[0]][(posB[1] + 1) % 5]);

            } else if (posA[1] == posB[1]) {

                resultado.append(
                        matriz[(posA[0] + 1) % 5][posA[1]]);

                resultado.append(
                        matriz[(posB[0] + 1) % 5][posB[1]]);

            } else {

                resultado.append(
                        matriz[posA[0]][posB[1]]);

                resultado.append(
                        matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }

    public String descifrar(String texto, String clave) {

        generarMatriz(clave);

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i += 2) {

            char a = texto.charAt(i);
            char b = texto.charAt(i + 1);

            int[] posA = buscar(a);
            int[] posB = buscar(b);

            if (posA[0] == posB[0]) {

                resultado.append(
                        matriz[posA[0]][(posA[1] + 4) % 5]);

                resultado.append(
                        matriz[posB[0]][(posB[1] + 4) % 5]);

            } else if (posA[1] == posB[1]) {

                resultado.append(
                        matriz[(posA[0] + 4) % 5][posA[1]]);

                resultado.append(
                        matriz[(posB[0] + 4) % 5][posB[1]]);

            } else {

                resultado.append(
                        matriz[posA[0]][posB[1]]);

                resultado.append(
                        matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }
}