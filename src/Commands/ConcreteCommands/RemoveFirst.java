package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class RemoveFirst extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public RemoveFirst(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public RemoveFirst(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_first - удалить первый элемент из коллекции");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой remove_first.");
        commandReceiver.removeFirst();
    }
}
