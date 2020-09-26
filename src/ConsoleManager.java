import Client.Sender;
import Client.Session;
import Commands.CommandInvoker;
import Commands.CommandReceiver;
import Commands.ConcreteCommands.*;
import Commands.Utils.Creators.ElementCreator;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
    void startInteractiveMode(String hostName, String port, String delayArg) throws IOException {
        Session session = null;
        int delay = 0;

        try {
            session = new Session(hostName, Integer.parseInt(port));
            session.startSession();
            delay = Integer.parseInt(delayArg);
            if (delay < 80) delay = 80;  // Минимальная задержка 80
        } catch (NumberFormatException ex) {
            System.out.println("Один из аргументов не соответствует требованием.\n" +
                    "Имя хоста должно быть текстовым значением, а порта и задержки(в мс) - целочисленным!");
            System.exit(0);
        } catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        Sender sender = new Sender(session);

        ElementCreator elementCreator = new ElementCreator();
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker, sender, session.getSocketChannel(), delay, elementCreator);

        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveById(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("remove_first", new RemoveFirst(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));
        commandInvoker.register("add_if_max", new AddIfMax(commandReceiver));
        commandInvoker.register("sum_of_salary", new SumOfSalary(commandReceiver));
        commandInvoker.register("min_by_name", new MinByName(commandReceiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }

        session.closeSession();
    }
}
