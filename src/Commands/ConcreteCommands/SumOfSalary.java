package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

public class SumOfSalary extends Command {
    private static final long serialVersionUID = 32L;
    private transient CommandReceiver commandReceiver;
    public SumOfSalary(CommandReceiver commandReceiver){
        this.commandReceiver = commandReceiver;
    }
    public SumOfSalary(){}

    @Override
    protected void writeInfo() {
        System.out.println("Команда sum_of_salary - вывести сумму значений поля salary для всех элементов коллекции");
    }

    @Override
    protected void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if(args.length > 1) System.out.println("Введен ненужный аргумент. Команда приведена к базовой sum_of_salary.");
        commandReceiver.sumOfSalary();
    }
}
