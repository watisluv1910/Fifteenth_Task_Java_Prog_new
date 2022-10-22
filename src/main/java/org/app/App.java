package org.app;


import org.firstSubtask.Calculator;

public class App {

    public static void main( String[] args ) {

        while (true) {

            Integer navigationVar = MyInput.inputInteger("""
                    Navigation menu:
                    Enter 1 to run first subtask.
                    Enter 2 to run second subtask and etc. up to 4.
                    Enter any other digit to stop the program.""", Integer::valueOf);

            switch (navigationVar) {
                case 1: {
                    Calculator calculator = new Calculator();
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                default:
                    System.exit(1);
                    break;
            }
        }
    }
}

