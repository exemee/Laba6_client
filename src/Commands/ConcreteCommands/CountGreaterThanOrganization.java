package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;
import Commands.Utils.Creators.ElementCreator;

import java.io.IOException;

public class CountGreaterThanOrganization extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public CountGreaterThanOrganization(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public CountGreaterThanOrganization(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда count_greater_than_organization - вывести количество элементов, значение поля organization которых больше заданного");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой exit.");
        commandReceiver.count_greater_than_organization();
    }
}
