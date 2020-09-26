package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class RemoveGreater extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public RemoveGreater(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public RemoveGreater(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_greater - удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой remove_greater.");
        commandReceiver.removeGreater();
    }
}
