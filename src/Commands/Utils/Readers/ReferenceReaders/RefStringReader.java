package Commands.Utils.Readers.ReferenceReaders;

import java.util.Scanner;

public class RefStringReader {
    public static String read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        String readString = in.nextLine().trim();

        while(!canBeNull && readString.equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readString = in.nextLine().trim();
        }

        if(canBeNull && readString.equals("")) { readString = null; }
        return readString;
    }
}
