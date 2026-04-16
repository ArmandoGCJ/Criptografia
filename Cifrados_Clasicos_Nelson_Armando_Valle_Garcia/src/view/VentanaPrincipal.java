package view;

import controller.ControladorCifrado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

        private static final Color COLOR_FONDO = new Color(245, 247, 250);
        private static final Color COLOR_PANEL = new Color(255, 255, 255);
        private static final Color COLOR_PRIMARIO = new Color(52, 110, 235);
        private static final Color COLOR_PRIMARIO_OVER = new Color(36, 87, 200);
        private static final Color COLOR_ACCION = new Color(30, 165, 120);
        private static final Color COLOR_ACCION_OVER = new Color(20, 130, 94);
        private static final Color COLOR_LIMPIAR = new Color(220, 70, 70);
        private static final Color COLOR_LIMPIAR_OVER = new Color(185, 45, 45);
        private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;
        private static final Color COLOR_BORDE = new Color(210, 215, 225);
        private static final Color COLOR_AREA_BG = new Color(250, 251, 253);

        private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 13);
        private static final Font FUENTE_BOTONES = new Font("Segoe UI", Font.BOLD, 12);
        private static final Font FUENTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);
        private static final Font FUENTE_AREA = new Font("Consolas", Font.PLAIN, 12);

        private JTextField txtPalabra;
        private JTextField txtClave;
        private JLabel lblClave;
        private JPanel panelClave;
        private JTextArea txtCifrado;
        private JTextArea txtDescifrado;
        private JTextArea txtExplicacion;

        private JButton btnCesar;
        private JButton btnAtbash;
        private JButton btnVigenere;
        private JButton btnRailFence;
        private JButton btnPlayfair;

        private JButton btnCifrar;
        private JButton btnDescifrar;
        private JButton btnLimpiar;

        private ControladorCifrado controlador;
        private String metodoSeleccionado = "CESAR";
        private JButton btnSeleccionado;

        public VentanaPrincipal() {
                controlador = new ControladorCifrado();

                setTitle("Sistema de Cifrados Clásicos");
                setSize(900, 580);
                setMinimumSize(new Dimension(800, 540));
                setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                getContentPane().setBackground(COLOR_FONDO);
                setLayout(new BorderLayout(0, 0));

                add(crearPanelSuperior(), BorderLayout.NORTH);
                add(crearPanelCentral(), BorderLayout.CENTER);

                configurarAcciones();
                marcarSeleccionado(btnCesar);
                actualizarPanelClave("CESAR");
        }

        // Panel superior
        private JPanel crearPanelSuperior() {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setBackground(COLOR_FONDO);
                panel.setBorder(new EmptyBorder(18, 24, 8, 24));

                // Fila 1
                JPanel filaEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
                filaEntrada.setOpaque(false);

                JLabel lblPalabra = new JLabel("ESCRIBA SU PALABRA:");
                lblPalabra.setFont(FUENTE_LABEL);
                lblPalabra.setForeground(new Color(55, 65, 81));

                txtPalabra = new JTextField(24);
                estilizarCampo(txtPalabra, COLOR_BORDE);

                filaEntrada.add(lblPalabra);
                filaEntrada.add(txtPalabra);

                panelClave = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
                panelClave.setOpaque(false);

                lblClave = new JLabel("CLAVE:");
                lblClave.setFont(FUENTE_LABEL);
                lblClave.setForeground(new Color(55, 65, 81));

                txtClave = new JTextField(10);
                estilizarCampo(txtClave, COLOR_BORDE);

                panelClave.add(lblClave);
                panelClave.add(txtClave);
                panelClave.setVisible(false);

                filaEntrada.add(panelClave);
                panel.add(filaEntrada);
                panel.add(Box.createVerticalStrut(10));

                // Fila 2
                JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
                filaBotones.setOpaque(false);

                btnCesar = crearBotonMetodo("CÉSAR");
                btnAtbash = crearBotonMetodo("ATBASH");
                btnVigenere = crearBotonMetodo("VIGENERE");
                btnRailFence = crearBotonMetodo("RAIL FENCE");
                btnPlayfair = crearBotonMetodo("PLAYFAIR");

                filaBotones.add(btnCesar);
                filaBotones.add(btnAtbash);
                filaBotones.add(btnVigenere);
                filaBotones.add(btnRailFence);
                filaBotones.add(btnPlayfair);
                panel.add(filaBotones);
                panel.add(Box.createVerticalStrut(10));

                // Fila 3
                JPanel filaExp = new JPanel(new BorderLayout(0, 4));
                filaExp.setOpaque(false);

                JLabel lblExp = new JLabel("  EXPLICACIÓN DEL MÉTODO");
                lblExp.setFont(FUENTE_TITULO);
                lblExp.setForeground(new Color(55, 65, 81));
                lblExp.setBorder(new EmptyBorder(0, 2, 2, 0));

                txtExplicacion = new JTextArea(2, 60);
                txtExplicacion.setEditable(false);
                txtExplicacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                txtExplicacion.setBackground(new Color(239, 246, 255));
                txtExplicacion.setForeground(new Color(30, 64, 175));
                txtExplicacion.setBorder(new EmptyBorder(8, 12, 8, 12));
                txtExplicacion.setLineWrap(true);
                txtExplicacion.setWrapStyleWord(true);
                txtExplicacion.setText(
                                "Cifrado César: desplaza cada letra del texto un número fijo de posiciones en el alfabeto.");

                JScrollPane scrollExp = new JScrollPane(txtExplicacion);
                scrollExp.setBorder(BorderFactory.createLineBorder(new Color(186, 215, 255), 1, true));

                filaExp.add(lblExp, BorderLayout.NORTH);
                filaExp.add(scrollExp, BorderLayout.CENTER);
                panel.add(filaExp);

                panel.add(Box.createVerticalStrut(10));
                JSeparator sep = new JSeparator();
                sep.setForeground(COLOR_BORDE);
                panel.add(sep);

                return panel;
        }

        // Panel central
        private JPanel crearPanelCentral() {
                JPanel panel = new JPanel(new GridLayout(1, 3, 12, 0));
                panel.setBackground(COLOR_FONDO);
                panel.setBorder(new EmptyBorder(14, 24, 20, 24));

                panel.add(crearColumnaTexto("TEXTO CIFRADO", txtCifrado = new JTextArea()));
                panel.add(crearColumnaBotones());
                panel.add(crearColumnaTexto("TEXTO DESCIFRADO", txtDescifrado = new JTextArea()));

                return panel;
        }

        private JPanel crearColumnaTexto(String titulo, JTextArea area) {
                JPanel col = new JPanel(new BorderLayout(0, 6));
                col.setBackground(COLOR_PANEL);
                col.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                                new EmptyBorder(10, 10, 10, 10)));

                JLabel lbl = new JLabel(titulo);
                lbl.setFont(FUENTE_TITULO);
                lbl.setForeground(new Color(75, 85, 99));
                lbl.setBorder(new EmptyBorder(0, 2, 4, 0));

                area.setFont(FUENTE_AREA);
                area.setBackground(COLOR_AREA_BG);
                area.setForeground(new Color(30, 41, 59));
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                area.setBorder(new EmptyBorder(6, 8, 6, 8));

                JScrollPane scroll = new JScrollPane(area);
                scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 1, true));

                col.add(lbl, BorderLayout.NORTH);
                col.add(scroll, BorderLayout.CENTER);
                return col;
        }

        private JPanel crearColumnaBotones() {
                JPanel col = new JPanel();
                col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
                col.setBackground(COLOR_FONDO);
                col.setBorder(new EmptyBorder(10, 8, 10, 8));

                col.add(Box.createVerticalGlue());

                btnCifrar = crearBotonAccion("← CIFRAR ←", COLOR_ACCION, COLOR_ACCION_OVER);
                btnDescifrar = crearBotonAccion("→ DESCIFRAR →", COLOR_PRIMARIO, COLOR_PRIMARIO_OVER);
                btnLimpiar = crearBotonAccion("✕ LIMPIAR", COLOR_LIMPIAR, COLOR_LIMPIAR_OVER);

                for (JButton btn : new JButton[] { btnCifrar, btnDescifrar, btnLimpiar }) {
                        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                        btn.setMaximumSize(new Dimension(160, 44));
                        col.add(btn);
                        col.add(Box.createVerticalStrut(14));
                }

                col.add(Box.createVerticalGlue());
                return col;
        }

        private JButton crearBotonMetodo(String texto) {
                JButton btn = new JButton(texto);
                btn.setFont(FUENTE_BOTONES);
                btn.setForeground(COLOR_PRIMARIO);
                btn.setBackground(COLOR_PANEL);
                btn.setFocusPainted(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(COLOR_PRIMARIO, 2, true),
                                new EmptyBorder(7, 18, 7, 18)));

                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent e) {
                                if (btn != btnSeleccionado) {
                                        btn.setBackground(COLOR_PRIMARIO_OVER);
                                        btn.setForeground(COLOR_TEXTO_BLANCO);
                                }
                        }

                        public void mouseExited(java.awt.event.MouseEvent e) {
                                if (btn != btnSeleccionado) {
                                        btn.setBackground(COLOR_PANEL);
                                        btn.setForeground(COLOR_PRIMARIO);
                                }
                        }
                });
                return btn;
        }

        private JButton crearBotonAccion(String texto, Color bg, Color bgHover) {
                JButton btn = new JButton(texto);
                btn.setFont(FUENTE_BOTONES);
                btn.setForeground(COLOR_TEXTO_BLANCO);
                btn.setBackground(bg);
                btn.setOpaque(true);
                btn.setFocusPainted(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.setBorder(new EmptyBorder(10, 20, 10, 20));

                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent e) {
                                btn.setBackground(bgHover);
                        }

                        public void mouseExited(java.awt.event.MouseEvent e) {
                                btn.setBackground(bg);
                        }
                });
                return btn;
        }

        // Acciones
        private void configurarAcciones() {
                btnCesar.addActionListener(e -> seleccionarMetodo("CESAR",
                                "Cifrado César: desplaza cada letra del texto un número fijo de posiciones en el alfabeto."));
                btnAtbash.addActionListener(e -> seleccionarMetodo("ATBASH",
                                "Cifrado Atbash: sustituye cada letra por su opuesta en el alfabeto invertido."));
                btnVigenere.addActionListener(e -> seleccionarMetodo("VIGENERE",
                                "Cifrado Vigenère: usa una clave para cifrar cada letra con un desplazamiento diferente."));
                btnRailFence.addActionListener(e -> seleccionarMetodo("RAIL",
                                "Cifrado Rail Fence: reorganiza las letras en forma de zigzag y luego las lee por filas."));
                btnPlayfair.addActionListener(e -> seleccionarMetodo("PLAYFAIR",
                                "Cifrado Playfair: usa una matriz 5×5 creada con una clave y cifra las letras en pares."));

                btnCifrar.addActionListener(e -> {
                        String resultado = controlador.cifrar(
                                        metodoSeleccionado, txtPalabra.getText(), txtClave.getText());
                        txtCifrado.setText(resultado);
                });

                btnDescifrar.addActionListener(e -> {
                        String resultado = controlador.descifrar(
                                        metodoSeleccionado, txtCifrado.getText(), txtClave.getText());
                        txtDescifrado.setText(resultado);
                });

                btnLimpiar.addActionListener(e -> {
                        txtPalabra.setText("");
                        txtCifrado.setText("");
                        txtDescifrado.setText("");
                        // Restaurar clave por defecto según el método activo
                        actualizarPanelClave(metodoSeleccionado);
                        txtPalabra.requestFocus();
                });
        }

        private void marcarSeleccionado(JButton nuevo) {
                if (btnSeleccionado != null) {
                        btnSeleccionado.setBackground(COLOR_PANEL);
                        btnSeleccionado.setForeground(COLOR_PRIMARIO);
                }
                btnSeleccionado = nuevo;
                btnSeleccionado.setBackground(COLOR_PRIMARIO);
                btnSeleccionado.setForeground(COLOR_TEXTO_BLANCO);
        }

        private void seleccionarMetodo(String metodo, String explicacion) {
                metodoSeleccionado = metodo;
                txtExplicacion.setText(explicacion);
                actualizarPanelClave(metodo);
                JButton btn = switch (metodo) {
                        case "CESAR" -> btnCesar;
                        case "ATBASH" -> btnAtbash;
                        case "VIGENERE" -> btnVigenere;
                        case "RAIL" -> btnRailFence;
                        case "PLAYFAIR" -> btnPlayfair;
                        default -> null;
                };
                if (btn != null)
                        marcarSeleccionado(btn);
        }

        private void actualizarPanelClave(String metodo) {
                switch (metodo) {
                        case "VIGENERE":
                                lblClave.setText("CLAVE :");
                                txtClave.setText("CLAVE");
                                panelClave.setVisible(true);
                                break;
                        case "PLAYFAIR":
                                lblClave.setText("CLAVE :");
                                txtClave.setText("CLAVE");
                                panelClave.setVisible(true);
                                break;
                        case "RAIL":
                                lblClave.setText("NÚMERO DE RIELES:");
                                txtClave.setText("3");
                                panelClave.setVisible(true);
                                break;
                        default:
                                panelClave.setVisible(false);
                                txtClave.setText("");
                                break;
                }

                if (panelClave.getParent() != null) {
                        panelClave.getParent().revalidate();
                        panelClave.getParent().repaint();
                }
        }

        private void estilizarCampo(JTextField campo, Color colorBorde) {
                campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                campo.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(colorBorde, 1, true),
                                new EmptyBorder(6, 10, 6, 10)));
                campo.setBackground(COLOR_PANEL);
        }
}
