package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class SaveCommand implements Command {
    @Override
    public String execute(String[] args, Database database) { return database.save(); }
}