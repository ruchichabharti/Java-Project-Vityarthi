package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateUtil {

    public static LocalDate getDate(Scanner scanner) {

        while (true) {

            System.out.print("Enter date (YYYY-MM-DD): ");
            String input = scanner.nextLine();

            try {

                return LocalDate.parse(input);

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date. Example: 2026-09-16"
                );
            }
        }
    }
}