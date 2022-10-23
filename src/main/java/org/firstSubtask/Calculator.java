package org.firstSubtask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator extends JFrame {

    private static JTextField textField;
    private static Listener listener = new Listener();

    private static final Font BUTTON_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 24);

    private static final ArrayList<ArrayList<String>> BUTTON_TEXTS =
            new ArrayList<>(){
        {
            add(new ArrayList<>(Arrays.asList("7", "8", "9", "+")));
            add(new ArrayList<>(Arrays.asList("4", "5", "6", "-")));
            add(new ArrayList<>(Arrays.asList("1", "2", "3", "*")));
            add(new ArrayList<>(Arrays.asList("0", ".", "/", "=")));

        }
    };

    public Calculator() {
        SwingUtilities.invokeLater(Calculator::createFrame);
    }

    private static void createFrame() {

        // Creating a text field:
        textField = new JTextField(10);
        textField.setFont(BUTTON_FONT.deriveFont(Font.PLAIN));
        textField.setEditable(false);

        // Creating a panel for buttons:
        JPanel buttonsPanel = new JPanel(
                new GridLayout(
                        BUTTON_TEXTS.size(),
                        BUTTON_TEXTS.get(0).size()
                )
        );
        buttonsPanel.setBackground(Color.PINK);

        // Initializing buttons panel:
        for (ArrayList<String> buttonsLine : BUTTON_TEXTS) {

            for (String buttonText : buttonsLine) {

                buttonsPanel.add(getButton(buttonText));
            }
        }

        // Creating a main panel:
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textField, BorderLayout.PAGE_START);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(getButton("AC"), BorderLayout.PAGE_END);

        JFrame frame = new JFrame("Calculator");
        frame.getContentPane().add(mainPanel);
        frame.pack(); // Window will be sized according to the size of its components
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        frame.setVisible(true);
    }

    private static JButton getButton(String text) {
        JButton button = new JButton(text);

        button.setFont(BUTTON_FONT); // Setting custom font
        button.addActionListener(listener); // Adding action listener

        return button;
    }

    public static class Listener implements ActionListener {

        private String expr1, expr2, expr3;

        public Listener() {

            expr1 = expr2 = expr3 = "";
        }

        public void actionPerformed(ActionEvent event) {

            String expr = event.getActionCommand();

            // If the value is a number:
            if ((expr.charAt(0) >= '0' && expr.charAt(0) <= '9') || expr.charAt(0) == '.') {

                // If operand is present - adding to the second expression:
                if (!expr2.equals("")) {
                    expr3 = expr3 + expr;
                } else {
                    expr1 = expr1 + expr;
                }

                textField.setText(expr1 + expr2 + expr3); // Setting text field value

            } else if (expr.equals("AC")) {

                expr1 = expr2 = expr3 = ""; // Clearing expressions
                textField.setText(null); // Setting the value of text to null

            } else if (expr.equals("=")) {

                double tempExpr = switch (expr2) {
                    case "+" -> (Double.parseDouble(expr1) + Double.parseDouble(expr3));
                    case "-" -> (Double.parseDouble(expr1) - Double.parseDouble(expr3));
                    case "/" -> (Double.parseDouble(expr1) / Double.parseDouble(expr3));
                    default -> (Double.parseDouble(expr1) * Double.parseDouble(expr3));
                };

                // Setting text field value:
                textField.setText(expr1 + expr2 + expr3 + "=" + tempExpr);

                expr1 = Double.toString(tempExpr);
                expr2 = expr3 = "";

            } else {

                if (expr2.equals("") || expr3.equals("")) { // If there was no operand

                    expr2 = expr;

                } else { // Evaluate

                    double te = switch (expr2) {
                        case "+" -> (Double.parseDouble(expr1) + Double.parseDouble(expr3));
                        case "-" -> (Double.parseDouble(expr1) - Double.parseDouble(expr3));
                        case "/" -> (Double.parseDouble(expr1) / Double.parseDouble(expr3));
                        default -> (Double.parseDouble(expr1) * Double.parseDouble(expr3));
                    };

                    expr1 = Double.toString(te);
                    expr2 = expr; // Placing the operator
                    expr3 = ""; // Making the operand blank
                }

                textField.setText(expr1 + expr2 + expr3); // Setting text field value
            }
        }
    }
}
