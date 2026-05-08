package bg.tu_varna.sit.f24621679;

import java.util.ArrayList;
import java.util.List;

/**
 * Класът Table представлява една таблица в базата данни.
 * Връща резултатите от операциите като String съобщения, без да принтира директно.
 */
public class Table {
    private String tableName;
    private List<String> columnNames;
    private List<List<String>> tableRows;

    public Table(String name, List<String> columns) {
        this.tableName = name;
        this.columnNames = new ArrayList<>(columns);
        this.tableRows = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String newName) {
        this.tableName = newName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<List<String>> getTableRows() {
        return tableRows;
    }
    /**
     * Вмъква нов ред в таблицата. Ако липсват стойности, допълва с NULL.
     */
    public String insertRow(List<String> valuesList) {
        List<String> newRow = new ArrayList<>(valuesList);
        while (newRow.size() < columnNames.size()) {
            newRow.add("NULL");
        }
        tableRows.add(newRow);
        return "Row inserted successfully.";
    }
    /**
     * Връща типовете на колоните като форматиран текст.
     */
    public String describe() {
        StringBuilder result = new StringBuilder("Columns in table " + tableName + ":\n");
        for (int index = 0; index < columnNames.size(); index++) {
            result.append(columnNames.get(index)).append(" (String/Mixed)\n");
        }
        return result.toString();
    }
    /**
     * Връща цялата таблица като форматиран текст.
     */
    public String getFormattedData() {
        StringBuilder result = new StringBuilder("--- Table: " + tableName + " ---\n");

        for (int index = 0; index < columnNames.size(); index++) {
            result.append(columnNames.get(index)).append(" | ");
        }
        result.append("\n");
        for (int rowIndex = 0; rowIndex < tableRows.size(); rowIndex++) {
            List<String> currentRow = tableRows.get(rowIndex);
            for (int colIndex = 0; colIndex < currentRow.size(); colIndex++) {
                result.append(currentRow.get(colIndex)).append(" | ");
            }
            result.append("\n");
        }
        return result.toString();
    }
    /**
     * Изтрива редове, които съдържат дадена стойност в определена колона.
     */
    public String delete(int columnIndex, String searchValue) {
        int actualIndex = columnIndex - 1;
        if (actualIndex < 0 || actualIndex >= columnNames.size()) {
            return "Error: Invalid column number.";
        }
        List<List<String>> rowsToRemove = new ArrayList<>();
        for (int index = 0; index < tableRows.size(); index++) {
            List<String> row = tableRows.get(index);
            if (row.get(actualIndex).equals(searchValue)) {
                rowsToRemove.add(row);
            }
        }
        tableRows.removeAll(rowsToRemove);
        return "Deleted " + rowsToRemove.size() + " rows.";
    }
    /**
     * Обновява стойности в таблицата и връща резултата като съобщение.
     */
    public String update(int searchColumnIndex, String searchValue, int targetColumnIndex, String targetValue) {
        int actualSearchIndex = searchColumnIndex - 1;
        int actualTargetIndex = targetColumnIndex - 1;
        if (actualSearchIndex < 0 || actualSearchIndex >= columnNames.size() ||
                actualTargetIndex < 0 || actualTargetIndex >= columnNames.size()) {
            return "Error: Invalid column index.";
        }
        int updatedCount = 0;
        for (int index = 0; index < tableRows.size(); index++) {
            List<String> row = tableRows.get(index);
            if (row.get(actualSearchIndex).equals(searchValue)) {
                row.set(actualTargetIndex, targetValue);
                updatedCount++;
            }
        }
        return "Updated " + updatedCount + " rows.";
    }
    /**
     * Намира броя на редовете с дадена стойност.
     */
    public int count(int searchColumnIndex, String searchValue) {
        int foundCount = 0;
        int actualIndex = searchColumnIndex - 1;

        if (actualIndex < 0 || actualIndex >= columnNames.size()) {
            return -1;
        }
        for (int index = 0; index < tableRows.size(); index++) {
            List<String> row = tableRows.get(index);
            if (row.get(actualIndex).equals(searchValue)) {
                foundCount++;
            }
        }
        return foundCount;
    }
}