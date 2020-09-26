package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class Update extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public Update(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public Update(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда update id - обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length == 2) commandReceiver.update(args[1]);
        else System.out.println("Некорректно количество аргументов.");
    }
}
