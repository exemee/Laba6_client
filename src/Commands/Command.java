package Commands;

import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 32L;
    protected abstract void writeInfo();
    protected abstract void execute(String[] args) throws InterruptedException, IOException, ClassNotFoundException;
}
