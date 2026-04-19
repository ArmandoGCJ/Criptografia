package models;


public class CifradoCesar {


    public String cifrar(String texto, int desplazamiento, String alfabeto) {

        int n = alfabeto.length();
        desplazamiento = ((desplazamiento % n) + n) % n;

        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {

            int idx = Alfabeto.indexOf(c, alfabeto);

            if (idx >= 0) {
                resultado.append(Alfabeto.charAt((idx + desplazamiento) % n, alfabeto));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }


    public String descifrar(String texto, int desplazamiento, String alfabeto) {
        int n = alfabeto.length();
        return cifrar(texto, n - (desplazamiento % n), alfabeto);
    }
}
