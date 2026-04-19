package models;

public class CifradoVigenere {

    public String cifrar(String texto, String clave, String alfabeto) {

        int n = alfabeto.length();
        StringBuilder resultado = new StringBuilder();
        int j = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);
            int idxC = Alfabeto.indexOf(c, alfabeto);

            if (idxC >= 0) {

                char claveChar = clave.charAt(j % clave.length());
                int idxK = Alfabeto.indexOf(claveChar, alfabeto);
                if (idxK < 0) idxK = 0;

                resultado.append(Alfabeto.charAt((idxC + idxK) % n, alfabeto));
                j++;

            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    public String descifrar(String texto, String clave, String alfabeto) {

        int n = alfabeto.length();
        StringBuilder resultado = new StringBuilder();
        int j = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);
            int idxC = Alfabeto.indexOf(c, alfabeto);

            if (idxC >= 0) {

                char claveChar = clave.charAt(j % clave.length());
                int idxK = Alfabeto.indexOf(claveChar, alfabeto);
                if (idxK < 0) idxK = 0;

                resultado.append(Alfabeto.charAt((idxC - idxK + n) % n, alfabeto));
                j++;

            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}
