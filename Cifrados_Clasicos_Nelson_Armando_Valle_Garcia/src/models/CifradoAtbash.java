package models;


public class CifradoAtbash {


    public String cifrar(String texto, String alfabeto) {

        int n = alfabeto.length();
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {

            int idx = Alfabeto.indexOf(c, alfabeto);

            if (idx >= 0) {
                resultado.append(Alfabeto.charAt(n - 1 - idx, alfabeto));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}