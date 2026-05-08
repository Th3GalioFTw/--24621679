package bg.tu_varna.sit.f24621679;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класът Database управлява таблиците.
 * Връща String съобщения вместо директен изход в конзолата.
 */
public class Database {
    private Map<String, Table> databaseTables = new HashMap<>();
    private String currentFileName = null;
    /**
     * Отваря файл за четене или създава нов, ако не съществува.
     */
    public String open(String fileName) {
        try {
            File databaseFile = new File(fileName);
            if (!databaseFile.exists()) {
                databaseFile.createNewFile();
                currentFileName = fileName;
                return "File not found. Created empty file: " + fileName;
            } else {
                currentFileName = fileName;
                return "Successfully opened " + fileName;
            }
        } catch (IOException exception) {
            return "Error opening file!";
        }
    }
    /**
     * Затваря текущия отворен файл.
     */
    public String close() {
        if (currentFileName != null) {
            String tempName = currentFileName;
            currentFileName = null;
            databaseTables.clear();
            return "Successfully closed " + tempName;
        } else {
            return "No open file to close.";
        }
    }
    /**
     * Запазва промените в текущия файл.
     */
    public String save() {
        if (currentFileName != null) {
            return saveas(currentFileName);
        } else {
            return "No file is currently open.";
        }
    }
    /**
     * Запазва всички таблици в нов файл.
     */
    public String saveas(String newFileName) {
        if (currentFileName == null) {
            return "Error: Open a file first!";
        }
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(newFileName))) {
            for (Table currentTable : databaseTables.values()) {
                fileWriter.println("TABLE:" + currentTable.getTableName());
                fileWriter.println(String.join(",", currentTable.getColumnNames()));
                for (List<String> row : currentTable.getTableRows()) {
                    fileWriter.println(String.join(",", row));
                }
            }
            return "Successfully saved to " + newFileName;
        } catch (IOException exception) {
            return "Error saving file.";
        }
    }

    public void addTable(Table newTable) {
        databaseTables.put(newTable.getTableName(), newTable);
    }

    public Table getTable(String tableName) {
        return databaseTables.get(tableName);
    }
    /**
     * Преименува съществуваща таблица.
     */
    public String renameTable(String oldName, String newName) {
        if (databaseTables.containsKey(newName)) {
            return "Error: Table name must be unique.";
        }
        Table targetTable = databaseTables.get(oldName);
        if (targetTable != null) {
            targetTable.setTableName(newName);
            databaseTables.remove(oldName);
            databaseTables.put(newName, targetTable);
            return "Renamed " + oldName + " to " + newName;
        } else {
            return "Table not found.";
        }
    }
    /**
     * Извършва математическа операция (sum, product, min, max, avg).
     */
    public String aggregate(String tableName, int searchColumn, String searchValue, int targetColumn, String operationType) {
        Table targetTable = databaseTables.get(tableName);
        if (targetTable == null) {
            return "Error: Table not found.";
        }
        int actualSearchIndex = searchColumn - 1;
        int actualTargetIndex = targetColumn - 1;
        double totalSum = 0;
        double totalProduct = 1;
        double minValue = Double.MAX_VALUE;
        double maxValue = -Double.MAX_VALUE;
        int matchCount = 0;
        for (int index = 0; index < targetTable.getTableRows().size(); index++) {
            List<String> currentRow = targetTable.getTableRows().get(index);
            if (currentRow.get(actualSearchIndex).equals(searchValue)) {
                try {
                    double numericValue = Double.parseDouble(currentRow.get(actualTargetIndex));
                    totalSum += numericValue;
                    totalProduct *= numericValue;

                    if (numericValue < minValue) minValue = numericValue;
                    if (numericValue > maxValue) maxValue = numericValue;

                    matchCount++;
                } catch (NumberFormatException exception) {
                    // Пропускаме грешки при парсване
                }
            }
        }

        if (matchCount == 0) {
            return "No matching numeric data found for operation.";
        }
        if (operationType.equalsIgnoreCase("sum")) {
            return "Result (SUM): " + totalSum;
        } else if (operationType.equalsIgnoreCase("product")) {
            return "Result (PRODUCT): " + totalProduct;
        } else if (operationType.equalsIgnoreCase("avg")) {
            return "Result (AVG): " + (totalSum / matchCount);
        } else if (operationType.equalsIgnoreCase("min")) {
            return "Result (MIN): " + minValue;
        } else if (operationType.equalsIgnoreCase("max")) {
            return "Result (MAX): " + maxValue;
        } else {
            return "Unknown mathematical operation.";
        }
    }
    /**
     * Прави Inner Join на две таблици по избрани колони.
     */
    public String innerjoin(String firstTableName, int firstColumn, String secondTableName, int secondColumn) {
        Table firstTable = databaseTables.get(firstTableName);
        Table secondTable = databaseTables.get(secondTableName);

        if (firstTable == null || secondTable == null) {
            return "Error: One of the tables does not exist.";
        }
        List<String> combinedColumns = new ArrayList<>(firstTable.getColumnNames());
        combinedColumns.addAll(secondTable.getColumnNames());
        String joinedTableName = firstTableName + "_" + secondTableName;
        Table joinedTable = new Table(joinedTableName, combinedColumns);
        int actualFirstIndex = firstColumn - 1;
        int actualSecondIndex = secondColumn - 1;
        for (int firstIndex = 0; firstIndex < firstTable.getTableRows().size(); firstIndex++) {
            List<String> rowFirst = firstTable.getTableRows().get(firstIndex);
            for (int secondIndex = 0; secondIndex < secondTable.getTableRows().size(); secondIndex++) {
                List<String> rowSecond = secondTable.getTableRows().get(secondIndex);
                if (rowFirst.get(actualFirstIndex).equals(rowSecond.get(actualSecondIndex))) {
                    List<String> combinedRow = new ArrayList<>(rowFirst);
                    combinedRow.addAll(rowSecond);
                    joinedTable.insertRow(combinedRow);
                }
            }
        }
        databaseTables.put(joinedTableName, joinedTable);
        return "Created joined table: " + joinedTableName;
    }
}