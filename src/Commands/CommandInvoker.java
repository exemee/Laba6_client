package Commands;

import java.io.IOException;
import java.util.HashMap;

public class CommandInvoker {
    private final HashMap<String, Command> commandHashMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandHashMap.put(commandName, command);
    }

    public void executeCommand(String[] args){
        try {
            if(args.length > 0){
                Command command = commandHashMap.get(args[0]);
                command.execute(args);
            }
        }catch (NullPointerException | IllegalStateException ex){
            System.out.println("Не существует команды " + args[0] + ". Для справки используйте help.");
        } catch (InterruptedException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
}
