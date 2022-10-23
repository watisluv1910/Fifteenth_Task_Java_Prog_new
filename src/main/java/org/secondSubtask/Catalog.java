package org.secondSubtask;

import org.app.MyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Catalog extends JFrame {

    private static JComboBox comboBox;
    private static JTextArea textArea;

    private static final Listener listener = new Listener();

    private static ArrayList<String> countries;
    private static Map<String, String> countriesInfo;

    public Catalog() throws HeadlessException, IOException {

        if (GraphicsEnvironment.isHeadless()) {

            throw new HeadlessException();
        }

        SwingUtilities.invokeLater(Catalog::createFrameAndGUI);

        countries = new ArrayList<>(){
            {
                add("Australia");
                add("China");
                add("England");
                add("Russia");
            }
        };

        countriesInfo = new HashMap<>() {
            {
                for (int i = 0; i < countries.size(); i++) {

                    put(countries.get(i),
                            MyInput.readFileLine("src/main/resources/countries_info.txt", i)
                    );
                }
            }
        };
    }

    private static void addComponents(Container pane) {

        JPanel searchPanel = new JPanel(new GridLayout(1, 2));

        JLabel label = new JLabel("Select a county: ");
        label.setForeground(Color.PINK);
        searchPanel.add(label);

        comboBox = new JComboBox<>(countries.toArray());
        comboBox.addItemListener(listener);
        searchPanel.add(comboBox);

        JPanel mainPanel = new JPanel(new BorderLayout());

        textArea = new JTextArea(18, 30);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBackground(Color.PINK);
        JScrollPane scrollPane = new JScrollPane(textArea);

        mainPanel.add(searchPanel, BorderLayout.PAGE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        pane.add(mainPanel);
    }

    private static void createFrameAndGUI() {

        JFrame frame = new JFrame("Catalog");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        addComponents(frame.getContentPane());

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        frame.pack(); // Window will be sized according to the size of its components
        frame.setVisible(true);
    }

    public static class Listener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getSource() == comboBox) {

                textArea.setText(countriesInfo.get(
                        Objects.requireNonNull(comboBox.getSelectedItem()).toString())
                );
            }
        }
    }
}
