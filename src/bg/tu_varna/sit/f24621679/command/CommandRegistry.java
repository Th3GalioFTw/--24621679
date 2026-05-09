package bg.tu_varna.sit.f24621679.command;

import bg.tu_varna.sit.f24621679.command.impl.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Регистър, който свързва текста на командата със съответния клас.
 */
public class CommandRegistry {
    private Map<String, Command> activeCommands = new HashMap<>();

    public CommandRegistry() {
        activeCommands.put("open", new OpenCommand());
        activeCommands.put("close", new CloseCommand());
        activeCommands.put("save", new SaveCommand());
        activeCommands.put("saveas", new SaveAsCommand());
        activeCommands.put("help", new HelpCommand());
        activeCommands.put("print", new PrintCommand());
        activeCommands.put("describe", new DescribeCommand());
        activeCommands.put("insert", new InsertCommand());
        activeCommands.put("delete", new DeleteCommand());
        activeCommands.put("update", new UpdateCommand());
        activeCommands.put("rename", new RenameCommand());
        activeCommands.put("count", new CountCommand());
        activeCommands.put("aggregate", new AggregateCommand());
        activeCommands.put("innerjoin", new InnerJoinCommand());
    }

    public Command resolve(String commandName) {
        return activeCommands.get(commandName);
    }
}