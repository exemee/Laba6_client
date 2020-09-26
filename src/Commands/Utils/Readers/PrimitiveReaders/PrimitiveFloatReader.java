package Commands.Utils.Readers.PrimitiveReaders;

import java.util.Scanner;

public class PrimitiveFloatReader {
    public static float read() {
        System.out.print("Введите зарплату работника: ");
        Scanner sc = new Scanner(System.in);
        float result = 0f;
        boolean end = false;
        while (!end) {
            try {
                result = Float.parseFloat(sc.nextLine().trim());
                if (result > 0) { end = true; }
                else { System.out.print("Вы ввели не подходящее значение. Оно должно быть больше 0. Попробуйте снова: ");}
            } catch (NumberFormatException ex) {
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }

        return result;
    }
}
