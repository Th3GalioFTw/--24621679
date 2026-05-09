package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class CloseCommand implements Command {
    @Override
    public String execute(String[] args, Database database) { return database.close(); }
}