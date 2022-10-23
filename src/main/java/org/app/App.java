package org.app;


import org.firstSubtask.Calculator;
import org.secondSubtask.Catalog;
import org.thirdSubtask.Menu;

import java.io.IOException;

public class App {

    public static void main( String[] args ) {

        while (true) {

            Integer navigationVar = MyInput.inputInteger("""
                    Navigation menu:
                    Enter 1 to run first subtask.
                    Enter 2 to run second subtask and etc. up to 3.
                    Enter any other digit to stop the program.""", Integer::valueOf);

            switch (navigationVar) {
                case 1 -> {
                    Calculator calculator = new Calculator();
                }
                case 2 -> {
                    try {
                        Catalog catalog = new Catalog();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    Menu menu = new Menu();
                }
                default -> System.exit(1);
            }
        }
    }
}

