package criptografia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CifradosGUI extends JFrame {

    private JTextField txtTexto;
    private JTextField txtClave;
    private JTextField txtNumero;
    private JTextArea txtResultado;
    private JComboBox<String> comboCifrado;

    public CifradosGUI() {

        setTitle("Cifrados Clasicos - Interfaz GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));

        panel.add(new JLabel("Texto:"));
        txtTexto = new JTextField();
        panel.add(txtTexto);

        panel.add(new JLabel("Clave (si aplica):"));
        txtClave = new JTextField();
        panel.add(txtClave);

        panel.add(new JLabel("Numero (desplazamiento / filas):"));
        txtNumero = new JTextField();
        panel.add(txtNumero);

        panel.add(new JLabel("Tipo de Cifrado:"));

        comboCifrado = new JComboBox<>(new String[]{
                "Cesar",
                "Atbash",
                "Vigenere",
                "Rail Fence",
                "Playfair"
        });

        panel.add(comboCifrado);

        JButton btnCifrar = new JButton("Cifrar");
        panel.add(btnCifrar);

        JButton btnLimpiar = new JButton("Limpiar");
        panel.add(btnLimpiar);

        panel.add(new JLabel("Resultado:"));

        txtResultado = new JTextArea();
        txtResultado.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtResultado);

        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnCifrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String texto = txtTexto.getText();
                String clave = txtClave.getText();
                String numeroTexto = txtNumero.getText();

                String tipo = comboCifrado.getSelectedItem().toString();

                String resultado = "";

                try {

                    switch (tipo) {

                        case "Cesar":
                            int desplazamiento = Integer.parseInt(numeroTexto);
                            resultado = cifradoCesar(texto, desplazamiento);
                            break;

                        case "Atbash":
                            resultado = cifradoAtbash(texto);
                            break;

                        case "Vigenere":
                            resultado = cifradoVigenere(texto, clave);
                            break;

                        case "Rail Fence":
                            int filas = Integer.parseInt(numeroTexto);
                            resultado = cifradoRailFence(texto, filas);
                            break;

                        case "Playfair":
                            resultado = cifradoPlayfair(texto, clave);
                            break;

                    }

                    txtResultado.setText(resultado);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error en los datos ingresados");
                }

            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtTexto.setText("");
                txtClave.setText("");
                txtNumero.setText("");
                txtResultado.setText("");
            }
        });

    }

    // =====================
    // CIFRADO CESAR
    // =====================

    public static String cifradoCesar(String texto, int desplazamiento) {

        String resultado = "";

        for (char c : texto.toCharArray()) {

            if (Character.isLetter(c)) {

                char base = Character.isUpperCase(c) ? 'A' : 'a';

                c = (char) ((c - base + desplazamiento) % 26 + base);

            }

            resultado += c;

        }

        return resultado;

    }

    // =====================
    // ATBASH
    // =====================

    public static String cifradoAtbash(String texto) {

        String resultado = "";

        for (char c : texto.toUpperCase().toCharArray()) {

            if (Character.isLetter(c)) {

                char nuevo = (char) ('Z' - (c - 'A'));

                resultado += nuevo;

            } else {

                resultado += c;

            }

        }

        return resultado;

    }

    // =====================
    // VIGENERE
    // =====================

    public static String cifradoVigenere(String texto, String clave) {

        texto = texto.toUpperCase();
        clave = clave.toUpperCase();

        String resultado = "";

        int j = 0;

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            if (Character.isLetter(c)) {

                int desplazamiento = clave.charAt(j % clave.length()) - 'A';

                char nuevo = (char) ((c - 'A' + desplazamiento) % 26 + 'A');

                resultado += nuevo;

                j++;

            } else {

                resultado += c;

            }

        }

        return resultado;

    }

    // =====================
    // RAIL FENCE
    // =====================

    public static String cifradoRailFence(String texto, int filas) {

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

        String resultado = "";

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < texto.length(); j++) {

                if (rail[i][j] != 0)

                    resultado += rail[i][j];

            }

        }

        return resultado;

    }

    // =====================
    // PLAYFAIR
    // =====================

    public static String cifradoPlayfair(String texto, String clave) {

        texto = texto.toUpperCase().replace("J", "I");

        clave = clave.toUpperCase().replace("J", "I");

        char[][] matriz = generarMatriz(clave);

        texto = prepararTexto(texto);

        String resultado = "";

        for (int i = 0; i < texto.length(); i += 2) {

            char a = texto.charAt(i);
            char b = texto.charAt(i + 1);

            int[] posA = buscar(matriz, a);
            int[] posB = buscar(matriz, b);

            if (posA[0] == posB[0]) {

                resultado += matriz[posA[0]][(posA[1] + 1) % 5];
                resultado += matriz[posB[0]][(posB[1] + 1) % 5];

            }

            else if (posA[1] == posB[1]) {

                resultado += matriz[(posA[0] + 1) % 5][posA[1]];
                resultado += matriz[(posB[0] + 1) % 5][posB[1]];

            }

            else {

                resultado += matriz[posA[0]][posB[1]];
                resultado += matriz[posB[0]][posA[1]];

            }

        }

        return resultado;

    }

    public static char[][] generarMatriz(String clave) {

        char[][] matriz = new char[5][5];

        String alfabeto = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        String contenido = "";

        for (char c : clave.toCharArray())

            if (!contenido.contains("" + c))

                contenido += c;

        for (char c : alfabeto.toCharArray())

            if (!contenido.contains("" + c))

                contenido += c;

        int k = 0;

        for (int i = 0; i < 5; i++)

            for (int j = 0; j < 5; j++)

                matriz[i][j] = contenido.charAt(k++);

        return matriz;

    }

    public static String prepararTexto(String texto) {

        String resultado = "";

        for (int i = 0; i < texto.length(); i++) {

            resultado += texto.charAt(i);

            if (i + 1 < texto.length()
                    && texto.charAt(i) == texto.charAt(i + 1))

                resultado += 'X';

        }

        if (resultado.length() % 2 != 0)

            resultado += 'X';

        return resultado;

    }

    public static int[] buscar(char[][] matriz, char letra) {

        for (int i = 0; i < 5; i++)

            for (int j = 0; j < 5; j++)

                if (matriz[i][j] == letra)

                    return new int[]{i, j};

        return null;

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CifradosGUI().setVisible(true);
            }
        });

    }

}
