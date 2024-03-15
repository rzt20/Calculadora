import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class Calculadora2 extends JFrame { // extends es herencia
    private JTextField display;
    private double valor1 = 0;
    private String operador = "";
    private boolean inicio = true;

    //crear constructor de la pantalla
    public Calculadora2() {
        setTitle("Mi Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 18));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        //Crear el borde interno
        Border bordeInterno = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        //Crear el borde externa
        Border bordeExterno = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        //Crear linea entre los bordes
        Border linea = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);

        //combinar borde interno, linea , externo

        Border union = BorderFactory.createCompoundBorder(linea, bordeInterno);
        Border borderfinal = BorderFactory.createCompoundBorder(bordeExterno, union);

        display.setBorder(borderfinal);

        add(display, BorderLayout.NORTH);

        //Creacion de panel que tendra los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 5, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] botones = {
                "C", "<", "%", "00",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"

        };
        for (String boton : botones) {
            JButton btn = new JButton(boton);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(new BotonClickListener());
            panelBotones.add(btn);
        }
        add(panelBotones, BorderLayout.CENTER);
    }

    private class BotonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {// actionPerformed atrapa un evento.
            JButton sourse = (JButton) e.getSource();
            String textoBoton = sourse.getText();

            switch (textoBoton) {
                case "[0-9]":
                    setText(textoBoton);
                    break;
                case ".":
                    if (!display.getText().contains(".")) {
                        display.setText(display.getText() + textoBoton);
                    }
                    break;
                case "[+\\-*/]":
                    if (textoBoton.matches("[+\\-*/]")) {
                        valor1 = Double.parseDouble(display.getText());
                        operador = textoBoton;
                        inicio = true;
                    }
                case "=":
                    if (textoBoton.equals("=")) {
                        double valor2 = Double.parseDouble(display.getText());

                        switch (operador) {
                            case "+":
                                valor1 = valor1 + valor2;//Tambien se puede poner valor1+=valor2
                                break;
                            case "-":
                                valor1 = valor1 - valor2;//Tambien se puede poner valor1-=valor2
                                break;
                            case "*":
                                valor1 = valor1 * valor2;//Tambien se puede poner valor1*=valor2
                                break;
                            case "/":
                                if (valor2 != 0) {
                                    valor1 = valor1 / valor2;//Tambien se puede poner valor1/=valor2
                                } else {
                                    JOptionPane.showMessageDialog(null, "Infinito");
                                }
                                break;
                        }
                        display.setText(String.valueOf(valor1));//convertir de double a string con string.valueof()
                        inicio = true;
                    }
                case "C":
                    if (textoBoton.equals("C")) {
                        // Lógica para limpiar el display
                        display.setText("");
                        inicio = true;
                        break;
                    }
                case "<":
                    if (textoBoton.equals("<")) {
                        // Lógica para eliminar el último carácter del display
                        String textoActual = display.getText();
                        if (!textoActual.isEmpty()) {
                            display.setText(textoActual.substring(0, textoActual.length() - 1));
                        }

                    }
            }
        }
    }

    private void setText(String textoBoton) {
        if (inicio) {
            display.setText(textoBoton);
            inicio = false;
        } else {
            display.setText(display.getText() + textoBoton);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);

        });
    }
}

