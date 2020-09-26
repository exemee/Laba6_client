package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class Add extends Command {
    private static final long serialVersionUID = 32L;
    transient private CommandReceiver commandReceiver;

    public Add(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public Add() {}

    @Override
    protected void writeInfo() {
        System.out.println("Команда add - Добавить новый элемент в коллекцию");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой add.");
        commandReceiver.add();
    }
}
