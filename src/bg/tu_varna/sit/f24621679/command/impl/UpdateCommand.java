package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.Table;
import bg.tu_varna.sit.f24621679.command.Command;

public class UpdateCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 6) return "Error: Missing arguments.";
        Table targetTable = database.getTable(args[1]);
        if (targetTable == null) return "Error: Table not found.";
        return targetTable.update(Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]), args[5]);
    }
}