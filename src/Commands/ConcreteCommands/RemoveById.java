package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class RemoveById extends Command {
    private static final long serialVersionUID = 32L;

    private transient CommandReceiver commandReceiver;
    public RemoveById(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public RemoveById(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_by_id - Обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length == 2) commandReceiver.removeById(args[1]);
        else System.out.println("Некорректно количество аргументов.");
    }
}
