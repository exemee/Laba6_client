package Commands.Utils.Readers.ReferenceReaders;

import java.util.Scanner;

public class RefDoubleReader {
    public static Double read(String messageForConsole, boolean canBeNull) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        double result = 0d;
        boolean end = false;
        while (!end) {
            try {
                result = Double.parseDouble(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                if (canBeNull && sc.nextLine().trim().equals("")) {
                    return null;
                }
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }

        return result;
    }
}
