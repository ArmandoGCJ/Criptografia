package models;

public class CifradoRailFence {

    private String normalizar(String texto) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char n = Alfabeto.normalizar(c);

            if ((n >= 'A' && n <= 'Z') || n == '\u00d1') {
                sb.append(n);
            }
        }
        return sb.toString();
    }

    public String cifrar(String texto, int filas) {

        texto = normalizar(texto);

        if (texto.isEmpty())
            return texto;

        if (filas <= 1 || filas >= texto.length())
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

        texto = normalizar(texto);

        if (texto.isEmpty())
            return texto;

        if (filas <= 1 || filas >= texto.length())
            return texto;

        char[][] rail = new char[filas][texto.length()];

        // Fase 1
        boolean direccionAbajo = false;
        int fila = 0;

        for (int i = 0; i < texto.length(); i++) {

            rail[fila][i] = '*';

            if (fila == 0 || fila == filas - 1)
                direccionAbajo = !direccionAbajo;

            fila += direccionAbajo ? 1 : -1;
        }

        // Fase 2:
        int index = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < texto.length(); j++) {
                if (rail[i][j] == '*' && index < texto.length()) {
                    rail[i][j] = texto.charAt(index++);
                }
            }
        }

        // Fase 3
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