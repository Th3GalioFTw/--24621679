package bg.tu_varna.sit.f24621679.command.impl;
import bg.tu_varna.sit.f24621679.Database;
import bg.tu_varna.sit.f24621679.command.Command;

public class HelpCommand implements Command {
    @Override
    public String execute(String[] args, Database database) {
        return "Supported commands: open, close, save, saveas, exit, insert, describe, print, update, delete, rename, count, aggregate, innerjoin";
    }
}