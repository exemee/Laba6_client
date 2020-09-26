package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class Clear extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public Clear(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public Clear(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда clear - очистить коллекцию");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой clear.");
        commandReceiver.clear();
    }
}
