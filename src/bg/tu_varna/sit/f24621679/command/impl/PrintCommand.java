package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.Table;
import bg.tu_varna.sit.f24621679.command.Command;
import java.util.Scanner;

public class PrintCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 2) return "Error: Missing table name.";
        Table targetTable = database.getTable(args[1]);
        if (targetTable == null) return "Error: Table not found.";

        String fullData = targetTable.getFormattedData();
        String[] dataRows = fullData.split("\n");
        Scanner consoleScanner = new Scanner(System.in);

        for (int lineIndex = 0; lineIndex < dataRows.length; lineIndex++) {
            System.out.println(dataRows[lineIndex]);
            if (lineIndex > 0 && lineIndex % 10 == 0 && lineIndex < dataRows.length - 1) {
                System.out.println("--- Press Enter for next page ---");
                consoleScanner.nextLine();
            }
        }
        return "";
    }
}