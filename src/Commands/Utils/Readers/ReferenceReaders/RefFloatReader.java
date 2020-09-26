package Commands.Utils.Readers.ReferenceReaders;

import java.util.Scanner;

public class RefFloatReader {
    public static Float read(String messageForConsole, boolean canBeNull, float limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        float result = 0f;
        boolean end = false;
        while (!end) {
            try {
                result = Float.parseFloat(sc.nextLine().trim());
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

    public static Float readWithoutLimit(String messageForConsole, boolean canBeNull) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        float result = 0;
        boolean end = false;
        while (!end) {
            try {
                result = Float.parseFloat(sc.nextLine().trim());
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
