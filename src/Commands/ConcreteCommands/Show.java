package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class Show extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public Show(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public Show(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда show - Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой show.");
        commandReceiver.show();
    }
}
