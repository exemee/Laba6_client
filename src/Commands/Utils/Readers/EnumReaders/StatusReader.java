package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Status;

import java.util.Arrays;
import java.util.Scanner;

public class StatusReader {

    public static boolean checkExist(String text){
        return Arrays.stream(Status.values()).anyMatch((status -> status.name().equals(text)));
    }

    public static Status read(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите статус из предложенных: " + Arrays.asList(Status.values()));
        String text = in.nextLine().trim();
        if(!text.equals("")){
            while(!checkExist(text)){
                System.out.print("Вы ввели некорректное значение. Попробуйте снова: ");
                text = in.nextLine().trim();
                checkExist(text);
            }
        }else return null;
        return Enum.valueOf(Status.class,text);
    }
}
