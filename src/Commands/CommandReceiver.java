package Commands;


import BasicClasses.Worker;
import Client.Receiver;
import Client.Sender;
import Commands.ConcreteCommands.*;
import Commands.SerializedCommands.*;
import Commands.Utils.Creators.ElementCreator;


import java.io.*;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class CommandReceiver {
    private final CommandInvoker commandInvoker;
    private final Sender sender;
    private final SocketChannel socketChannel;
    private final Integer delay;
    private final ElementCreator elementCreator;


    public CommandReceiver(CommandInvoker commandInvoker, Sender sender, SocketChannel socketChannel, Integer delay, ElementCreator elementCreator) {
        this.commandInvoker = commandInvoker;
        this.sender = sender;
        this.socketChannel = socketChannel;
        this.delay = delay;
        this.elementCreator = elementCreator;
    }

    public void help() {
        commandInvoker.getCommandHashMap().forEach((name, command) -> command.writeInfo());
    }

    public void add() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new Add(), elementCreator.createWorker()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void removeById(String ID) throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveById(), ID));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void info() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Info()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void show() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Show()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void update(String ID) throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedCombinedCommand(new Update(), elementCreator.createWorker(), ID));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void clear() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Clear()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void exit() throws IOException {
        socketChannel.close();
        System.out.println("Завершение работы клиента.");
        System.exit(0);
    }

    public void removeFirst() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new RemoveFirst()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void removeGreater() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new RemoveGreater(), elementCreator.createWorker()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void addIfMax() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedObjectCommand(new AddIfMax(), elementCreator.createWorker()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|add_if_max|remove_greater")) {
                    command = line;
                    for (int i = 0; i < 14; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else {
                            System.out.println("Не хватает параметров для создания объекта.");
                            break;
                        }
                    }
                    Worker worker = elementCreator.createScriptWorker(parameters);
                    if (worker != null) {
                        switch (command.split(" ")[0]) {
                            case "add":
                                sender.sendObject(new SerializedObjectCommand(new Add(), elementCreator.createWorker()));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                            case "update":
                                sender.sendObject(new SerializedCombinedCommand(new Update(), elementCreator.createWorker(), command.split(" ")[1]));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                            case "remove_greater":
                                sender.sendObject(new SerializedObjectCommand(new RemoveGreater(), elementCreator.createWorker()));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                            case "add_if_max":
                                sender.sendObject(new SerializedObjectCommand(new AddIfMax(), elementCreator.createWorker()));
                                Thread.sleep(delay);
                                Receiver.receive(socketChannel);
                                break;
                        }
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath())) {
                    System.out.println("Пресечена попытка рекурсивного вызова скрипта.");
                } else {
                    commandInvoker.executeCommand(line.split(" "));
                }
            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    public void minByName() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new MinByName()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void sumOfSalary() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new SumOfSalary()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }

    public void count_greater_than_organization() throws InterruptedException, IOException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new CountGreaterThanOrganization(), elementCreator.createOrganization()));
        Thread.sleep(delay);
        Receiver.receive(socketChannel);
    }
}
