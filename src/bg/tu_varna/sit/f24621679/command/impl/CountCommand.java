package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.Table;
import bg.tu_varna.sit.f24621679.command.Command;

public class CountCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 4) return "Error: Missing arguments.";
        Table targetTable = database.getTable(args[1]);
        if (targetTable == null) return "Error: Table not found.";

        int result = targetTable.count(Integer.parseInt(args[2]), args[3]);
        return (result == -1) ? "Error: Invalid column index." : "Count: " + result;
    }
}