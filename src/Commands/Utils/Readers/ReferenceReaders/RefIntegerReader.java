package Commands.Utils.Readers.ReferenceReaders;

import java.util.Scanner;

public class RefIntegerReader {
    public static Integer read(String messageForConsole, boolean canBeNull, int limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Integer.parseInt(sc.nextLine().trim());
                switch (type) {
                    case ("MIN"):
                        if (result > limit) { end = true; }
                        else { System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");}
                        break;
                    case ("MAX"):
                        if (result < limit) { end = true; }
                        else { System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");}
                        break;
                }
            } catch (NumberFormatException ex) {
                if (canBeNull && sc.nextLine().trim().equals("")) {
                    return null;
                }
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }

        return result;
    }

    public static Integer readWithoutLimit(String messageForConsole, boolean canBeNull) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Integer.parseInt(sc.nextLine().trim());
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
