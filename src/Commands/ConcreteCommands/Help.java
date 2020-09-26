package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

public class Help extends Command {
    private final CommandReceiver commandReceiver;
    public Help(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда help - Вывести справку по доступным командам");
    }

    @Override
    protected void execute(String[] args) {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой help.");
        commandReceiver.help();
    }
}
