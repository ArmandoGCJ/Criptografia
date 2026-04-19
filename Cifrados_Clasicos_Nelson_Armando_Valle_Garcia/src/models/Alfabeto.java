package models;

public class Alfabeto {


    public static final String EN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String ES = "ABCDEFGHIJKLMN\u00d1OPQRSTUVWXYZ";


    public static final String EN_PLAYFAIR = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    public static final String ES_PLAYFAIR = "ABCDEFGHIKLMN\u00d1OPQRSTUVXYZ";


    public static char normalizar(char c) {
        c = Character.toUpperCase(c);
        switch (c) {
            case '\u00c1': return 'A';
            case '\u00e1': return 'A';
            case '\u00c9': return 'E';
            case '\u00e9': return 'E';
            case '\u00cd': return 'I';
            case '\u00ed': return 'I';
            case '\u00d3': return 'O';
            case '\u00f3': return 'O';
            case '\u00da': return 'U';
            case '\u00fa': return 'U';
            case '\u00dc': return 'U';
            case '\u00fc': return 'U';
            default:       return c;
        }
    }


    public static int indexOf(char c, String alfabeto) {
        return alfabeto.indexOf(normalizar(c));
    }

    public static char charAt(int i, String alfabeto) {
        return alfabeto.charAt(i);
    }
}
