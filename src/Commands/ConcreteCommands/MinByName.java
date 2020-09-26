package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class MinByName extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public MinByName(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public MinByName(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда min_by_name - вывести любой объект из коллекции, значение поля name которого является минимальным");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой min_by_name.");
        commandReceiver.minByName();
    }
}
