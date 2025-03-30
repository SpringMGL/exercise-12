import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;

    public void divide() throws InputMismatchException, ArithmeticException {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Please enter the divisor: ");
            divisor = input.nextDouble();
            System.out.print("Please enter the dividend: ");
            dividend = input.nextDouble();
            result = divisor / dividend;
        } finally {
            input.close();
        }
    }

    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }

    public void displayChoices() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1. Division operation");
            System.out.println("2. Read from a file");
            System.out.println("3. Exit");
            System.out.print("Please make a selection: ");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            goToDivideMethod();
                            System.out.println("The result is: " + result);
                        } catch (InputMismatchException e) {
                            System.out.println("Input mismatch exception " + e + " occurred. Expected a number but none was provided.");
                        } catch (ArithmeticException e) {
                            System.out.println("Arithmetic exception " + e + " occurred. Attempted to divide by zero.");
                        }
                        break;
                    case 2:
                        try {
                            readAFile();
                        } catch (IOException e) {
                            System.out.println("File reading exception " + e + " occurred. Please check the file path.");
                        } catch (MissingExtensionException e) {
                            System.out.println("Custom exception " + e + " occurred. The file path is missing an extension.");
                        }
                        break;
                    case 3:
                        input.close();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch exception " + e + " occurred. Please enter a valid numerical selection.");
                input.nextLine();
            }
        }
    }

    public void readAFile() throws IOException, MissingExtensionException {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the file path: ");
        String filePath = input.nextLine();
        if (!filePath.contains(".")) {
            throw new MissingExtensionException("The file path is missing an extension");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            System.out.println("File content: " + line);
        } finally {
            input.close();
        }
    }

    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        demo.displayChoices();
    }
}    