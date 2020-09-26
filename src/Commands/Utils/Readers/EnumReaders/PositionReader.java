package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Position;

import java.util.Arrays;
import java.util.Scanner;

public class PositionReader {
    public static boolean checkExist(String text){
        return Arrays.stream(Position.values()).anyMatch((status -> status.name().equals(text)));
    }

    public static Position read(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите должность из предложенных: " + Arrays.asList(Position.values()));
        String text = in.nextLine().trim();
        if(!text.equals("")){
            while(!checkExist(text)){
                System.out.print("Вы ввели некорректное значение. Попробуйте снова: ");
                text = in.nextLine().trim();
                checkExist(text);
            }
        }else return null;
        return Enum.valueOf(Position.class,text);
    }
}
