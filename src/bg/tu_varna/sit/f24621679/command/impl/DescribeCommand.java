package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.Table;
import bg.tu_varna.sit.f24621679.command.Command;

public class DescribeCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 2) return "Error: Missing table name.";
        Table targetTable = database.getTable(args[1]);
        return (targetTable != null) ? targetTable.describe() : "Error: Table not found.";
    }
}