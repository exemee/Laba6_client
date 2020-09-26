package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class Exit extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public Exit(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public Exit(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда exit - Завершить программу (без сохранения в файл)");
    }

    @Override
    protected void execute(String[] args) throws IOException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой exit.");
        commandReceiver.exit();
    }
}
