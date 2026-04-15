package view;

import controller.ControladorCifrado;

import javax.swing.*;
        import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTextField txtPalabra;
    private JTextArea txtExplicacion;
    private JTextArea txtCifrado;
    private JTextArea txtDescifrado;

    private JButton btnCesar;
    private JButton btnAtbash;
    private JButton btnVigenere;
    private JButton btnRailFence;
    private JButton btnPlayfair;

    private JButton btnCifrar;
    private JButton btnDescifrar;

    private ControladorCifrado controlador;

    private String metodoSeleccionado = "CESAR";

    public VentanaPrincipal() {

        controlador = new ControladorCifrado();

        setTitle("Sistema de Cifrados Clasicos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(3, 1));


        JPanel fila1 = new JPanel();

        fila1.add(new JLabel("ESCRIBA SU PALABRA:"));

        txtPalabra = new JTextField(20);
        fila1.add(txtPalabra);

        panelSuperior.add(fila1);


        JPanel fila2 = new JPanel();

        btnCesar = new JButton("CESAR");
        btnAtbash = new JButton("ATBASH");
        btnVigenere = new JButton("VIGENERE");
        btnRailFence = new JButton("RAIL FENCE");
        btnPlayfair = new JButton("PLAYFAIR");

        fila2.add(btnCesar);
        fila2.add(btnAtbash);
        fila2.add(btnVigenere);
        fila2.add(btnRailFence);
        fila2.add(btnPlayfair);

        panelSuperior.add(fila2);


        JPanel fila3 = new JPanel(new BorderLayout());

        txtExplicacion = new JTextArea(2, 40);
        txtExplicacion.setEditable(false);

        fila3.add(new JLabel("EXPLICACION DEL METODO"), BorderLayout.NORTH);
        fila3.add(new JScrollPane(txtExplicacion), BorderLayout.CENTER);

        panelSuperior.add(fila3);

        add(panelSuperior, BorderLayout.NORTH);

        // PANEL CENTRAL

        JPanel panelCentral = new JPanel(new GridLayout(1, 3));

        txtCifrado = new JTextArea();
        txtDescifrado = new JTextArea();

        panelCentral.add(new JScrollPane(txtCifrado));


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);

        btnCifrar = new JButton("← CIFRAR ←");
        btnDescifrar = new JButton("→ DESCIFRAR →");

        btnCifrar.setBounds(60, 80, 140, 40);
        btnDescifrar.setBounds(60, 140, 140, 40);

        panelBotones.add(btnCifrar);
        panelBotones.add(btnDescifrar);

        panelCentral.add(panelBotones);

        panelCentral.add(new JScrollPane(txtDescifrado));

        add(panelCentral, BorderLayout.CENTER);



        btnCesar.addActionListener(e -> seleccionarMetodo("CESAR",
                "Cifrado Cesar: desplaza cada letra del texto un número fijo de posiciones en el alfabeto."));

        btnAtbash.addActionListener(e -> seleccionarMetodo("ATBASH",
                "Cifrado Atbash: invierte alfabeto."));

        btnVigenere.addActionListener(e -> seleccionarMetodo("VIGENERE",
                "Cifrado Vigenere: usa una clave para cifrar cada letra con un desplazamiento diferente."));

        btnRailFence.addActionListener(e -> seleccionarMetodo("RAIL",
                "Cifrado Rail Fence: reorganiza las letras en forma de zigzag y luego las lee por filas."));

        btnPlayfair.addActionListener(e -> seleccionarMetodo("PLAYFAIR",
                "Cifrado Playfair: usa una matriz 5x5 creada con una clave y cifra las letras en pares."));


        btnCifrar.addActionListener(e -> {

            String texto = txtPalabra.getText();

            String resultado = controlador.cifrar(metodoSeleccionado, texto);

            txtCifrado.setText(resultado);

        });


        btnDescifrar.addActionListener(e -> {

            String texto = txtCifrado.getText();

            String resultado = controlador.descifrar(metodoSeleccionado, texto);

            txtDescifrado.setText(resultado);

        });

    }

    private void seleccionarMetodo(String metodo, String explicacion) {

        metodoSeleccionado = metodo;
        txtExplicacion.setText(explicacion);

    }

}

