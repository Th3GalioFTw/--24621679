package bg.tu_varna.sit.f24621679.command;

import bg.tu_varna.sit.f24621679.Database;

/**
 * Интерфейс за всички команди в системата.
 */
public interface Command {
    String execute(String[] args, Database database);
}