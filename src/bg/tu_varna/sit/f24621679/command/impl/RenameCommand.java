package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class RenameCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 3) return "Error: Missing arguments.";
        return database.renameTable(args[1], args[2]);
    }
}