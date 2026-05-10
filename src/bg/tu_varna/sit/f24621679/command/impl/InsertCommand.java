package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.Table;
import bg.tu_varna.sit.f24621679.command.Command;
import java.util.ArrayList;
import java.util.List;

public class InsertCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 3) return "Error: Missing arguments.";
        Table targetTable = database.getTable(args[1]);
        if (targetTable == null) return "Error: Table not found.";

        List<String> newValues = new ArrayList<>();
        for (int wordIndex = 2; wordIndex < args.length; wordIndex++) {
            newValues.add(args[wordIndex]);
        }
        return targetTable.insertRow(newValues);
    }
}