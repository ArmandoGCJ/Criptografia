package models;

public class CifradoRailFence {

    public String cifrar(String texto, int filas) {

        if (filas == 1)
            return texto;

        char[][] rail = new char[filas][texto.length()];

        boolean direccionAbajo = false;
        int fila = 0;

        for (int i = 0; i < texto.length(); i++) {

            rail[fila][i] = texto.charAt(i);

            if (fila == 0 || fila == filas - 1)
                direccionAbajo = !direccionAbajo;

            fila += direccionAbajo ? 1 : -1;
        }

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < filas; i++)
            for (int j = 0; j < texto.length(); j++)
                if (rail[i][j] != 0)
                    resultado.append(rail[i][j]);

        return resultado.toString();
    }

    public String descifrar(String texto, int filas) {

        if (filas == 1)
            return texto;

        char[][] rail = new char[filas][texto.length()];

        boolean direccionAbajo = false;
        int fila = 0;

        for (int i = 0; i < texto.length(); i++) {

            rail[fila][i] = '*';

            if (fila == 0 || fila == filas - 1)
                direccionAbajo = !direccionAbajo;

            fila += direccionAbajo ? 1 : -1;
        }

        int index = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < texto.length(); j++) {
                if (rail[i][j] == '*' && index < texto.length()) {
                    rail[i][j] = texto.charAt(index++);
                }
            }
        }

        StringBuilder resultado = new StringBuilder();

        direccionAbajo = false;
        fila = 0;

        for (int i = 0; i < texto.length(); i++) {

            resultado.append(rail[fila][i]);

            if (fila == 0 || fila == filas - 1)
                direccionAbajo = !direccionAbajo;

            fila += direccionAbajo ? 1 : -1;
        }

        return resultado.toString();
    }
}