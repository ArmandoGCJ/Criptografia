package models;

import java.util.HashSet;

public class CifradoPlayfair {

    private char[][] matriz = new char[5][5];


    private void generarMatriz(String clave, String alfabetoPlayfair) {

        HashSet<Character> usados = new HashSet<>();

        StringBuilder texto = new StringBuilder();
        for (char c : clave.toCharArray()) {
            texto.append(mapearParaPlayfair(c, alfabetoPlayfair));
        }
        texto.append(alfabetoPlayfair);

        int fila = 0, col = 0;

        for (char c : texto.toString().toCharArray()) {
            if (!usados.contains(c) && alfabetoPlayfair.indexOf(c) >= 0) {
                usados.add(c);
                matriz[fila][col] = c;
                col++;
                if (col == 5) { col = 0; fila++; }
                if (fila == 5) break;
            }
        }
    }


    private char mapearParaPlayfair(char c, String alfabetoPlayfair) {
        char norm = Alfabeto.normalizar(c);
        if (alfabetoPlayfair.indexOf(norm) >= 0) return norm;
        if (norm == 'J') return 'I';
        if (norm == 'W' && alfabetoPlayfair.indexOf('W') < 0) return 'V';
        return '?';
    }

    private int[] buscar(char letra) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matriz[i][j] == letra)
                    return new int[]{i, j};
        return null;
    }




    private String prepararTexto(String texto, String alfabetoPlayfair) {

        StringBuilder filtrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char m = mapearParaPlayfair(c, alfabetoPlayfair);
            if (alfabetoPlayfair.indexOf(m) >= 0) filtrado.append(m);
        }

        StringBuilder resultado = new StringBuilder();
        int i = 0;
        while (i < filtrado.length()) {
            char a = filtrado.charAt(i);
            resultado.append(a);
            if (i + 1 < filtrado.length()) {
                char b = filtrado.charAt(i + 1);
                if (a == b) {
                    resultado.append('X');
                    i++;
                } else {
                    resultado.append(b);
                    i += 2;
                }
            } else {
                resultado.append('X');
                i++;
            }
        }

        return resultado.toString();
    }


    public String cifrar(String texto, String clave, String alfabetoPlayfair) {

        generarMatriz(clave.toUpperCase(), alfabetoPlayfair);
        texto = prepararTexto(texto, alfabetoPlayfair);

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i += 2) {

            char a = texto.charAt(i);
            char b = texto.charAt(i + 1);

            int[] posA = buscar(a);
            int[] posB = buscar(b);
            if (posA == null || posB == null) continue;

            if (posA[0] == posB[0]) {
                resultado.append(matriz[posA[0]][(posA[1] + 1) % 5]);
                resultado.append(matriz[posB[0]][(posB[1] + 1) % 5]);

            } else if (posA[1] == posB[1]) {
                resultado.append(matriz[(posA[0] + 1) % 5][posA[1]]);
                resultado.append(matriz[(posB[0] + 1) % 5][posB[1]]);

            } else {
                resultado.append(matriz[posA[0]][posB[1]]);
                resultado.append(matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }


    public String descifrar(String texto, String clave, String alfabetoPlayfair) {

        generarMatriz(clave.toUpperCase(), alfabetoPlayfair);

        StringBuilder filtrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char m = mapearParaPlayfair(c, alfabetoPlayfair);
            if (alfabetoPlayfair.indexOf(m) >= 0) filtrado.append(m);
        }
        String txt = filtrado.toString();
        if (txt.length() % 2 != 0) txt += "X";

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < txt.length(); i += 2) {

            char a = txt.charAt(i);
            char b = txt.charAt(i + 1);

            int[] posA = buscar(a);
            int[] posB = buscar(b);
            if (posA == null || posB == null) continue;

            if (posA[0] == posB[0]) {
                resultado.append(matriz[posA[0]][(posA[1] + 4) % 5]);
                resultado.append(matriz[posB[0]][(posB[1] + 4) % 5]);

            } else if (posA[1] == posB[1]) {
                resultado.append(matriz[(posA[0] + 4) % 5][posA[1]]);
                resultado.append(matriz[(posB[0] + 4) % 5][posB[1]]);

            } else {
                resultado.append(matriz[posA[0]][posB[1]]);
                resultado.append(matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }
}