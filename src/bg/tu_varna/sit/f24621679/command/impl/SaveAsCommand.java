package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class SaveAsCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 2) return "Error: Missing file name.";
        return database.saveas(args[1]);
    }
}