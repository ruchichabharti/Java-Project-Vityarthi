package util;

import java.util.Scanner;

public class InputValidator {

    public static int getPositiveInt(Scanner scanner, String message) {

        while (true) {

            try {

                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.println("Please enter a positive number.");

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    public static double getPositiveDouble(
            Scanner scanner, String message) {

        while (true) {

            try {

                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.println("Amount must be greater than zero.");

            } catch (NumberFormatException e) {

                System.out.println("Invalid amount.");
            }
        }
    }

    public static String getNonEmptyString(
            Scanner scanner, String message) {

        while (true) {

            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }
}