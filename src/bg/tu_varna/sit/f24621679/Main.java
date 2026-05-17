package bg.tu_varna.sit.f24621679;

import bg.tu_varna.sit.f24621679.command.Command;
import bg.tu_varna.sit.f24621679.command.CommandRegistry;
import java.util.Scanner;

/**
 * Главният клас Main. Отговаря за комуникацията с потребителя чрез Command Pattern.
 */
public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Database mainDatabase = new Database();
        CommandRegistry commandRegistry = new CommandRegistry();
        Table testTable = new Table("students", java.util.Arrays.asList("id", "name", "grade"));
        testTable.insertRow(java.util.Arrays.asList("1", "Ivan", "5.50"));
        testTable.insertRow(java.util.Arrays.asList("2", "Maria", "6.00"));
        testTable.insertRow(java.util.Arrays.asList("3", "Nasko", "3.40"));
        testTable.insertRow(java.util.Arrays.asList("4", "Ivelin", "4.25"));
        mainDatabase.addTable(testTable);
        System.out.println("=== Database Manager Started ===");
        System.out.println("Type 'help' for commands.");
        boolean isProgramRunning = true;
        while (isProgramRunning) {
            System.out.print("> ");
            String userInput = inputScanner.nextLine().trim();
            if (userInput.isEmpty()) continue;
            String[] commandWords = userInput.split("\\s+");
            String mainCommand = commandWords[0].toLowerCase();
            if (mainCommand.equals("exit")) {
                System.out.println("Exiting program...");
                isProgramRunning = false;
                continue;
            }
            try {
                Command command = commandRegistry.resolve(mainCommand);
                if (command != null) {
                    String result = command.execute(commandWords, mainDatabase);
                    if (!result.isEmpty()) {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("Unknown command. Type 'help' for the list of commands.");
                }
            } catch (Exception exception) {
                System.out.println("Bad command format or error executing command.");
            }
        }
        inputScanner.close();
    }
}