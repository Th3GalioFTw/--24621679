package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class InnerJoinCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        if (args.length < 5) return "Error: Missing arguments.";
        return database.innerjoin(args[1], Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));
    }
}