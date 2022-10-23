package org.thirdSubtask;

import org.secondSubtask.Catalog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {

    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu fileMenu, editMenu, helpMenu;
    private static JMenuItem saveSub, exitSub, copySub, cutSub, pasteSub;
    private static JTextField textField;

    private static final Listener listener = new Listener();

    public Menu() {

        if (GraphicsEnvironment.isHeadless()) {

            throw new HeadlessException();
        }

        EventQueue.invokeLater(Menu::createFrameAndGUI);
    }

    private static void addComponents(Container pane) {

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuPanel.setBackground(Color.PINK);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        // File menu configuration:
        fileMenu = new JMenu("File");

        saveSub = new JMenuItem("Save");
        exitSub = new JMenuItem("Exit");
        exitSub.addActionListener(listener);

        fileMenu.add(saveSub);
        fileMenu.add(exitSub);

        // Edit menu configuration:
        editMenu = new JMenu("Edit");
        editMenu.setBackground(Color.WHITE);

        copySub = new JMenuItem("Copy");
        copySub.addActionListener(listener);

        cutSub = new JMenuItem("Cut");
        cutSub.addActionListener(listener);

        pasteSub = new JMenuItem("Paste");
        pasteSub.addActionListener(listener);

        editMenu.add(copySub);
        editMenu.add(cutSub);
        editMenu.add(pasteSub);

        // Help menu:
        helpMenu = new JMenu("Help");

        // Adding menus to menubar:
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // Text field initialization:
        textField = new JTextField(12);

        menuPanel.add(menuBar);
        menuPanel.add(textField);

        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2));
        buttonsPanel.add(new JButton("Button 1"));
        buttonsPanel.add(new JButton("Button 2"));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        pane.add(mainPanel);
    }

    private static void createFrameAndGUI() {

        frame = new JFrame("Menu");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

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

    public static class Listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == cutSub) {
                textField.cut();
            } else if (event.getSource() == pasteSub) {
                textField.paste();
            } else if (event.getSource() == copySub) {
                textField.copy();
            } else if (event.getSource() == exitSub) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
