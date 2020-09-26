package Commands.Utils.Creators;

import BasicClasses.*;
import Commands.Utils.Readers.EnumReaders.PositionReader;
import Commands.Utils.Readers.EnumReaders.StatusReader;
import Commands.Utils.Readers.PrimitiveReaders.PrimitiveFloatReader;
import Commands.Utils.Readers.ReferenceReaders.RefDoubleReader;
import Commands.Utils.Readers.ReferenceReaders.RefFloatReader;
import Commands.Utils.Readers.ReferenceReaders.RefIntegerReader;
import Commands.Utils.Readers.ReferenceReaders.RefStringReader;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class ElementCreator {
    public Worker createWorker(){

        String name = RefStringReader.read("Введите имя рабочего: ", false);
        float salary = PrimitiveFloatReader.read();
        Position position = PositionReader.read();
        Status status = StatusReader.read();

        return new Worker(name, createCoordinates(), salary, position, status, createOrganization());
    }

    public Coordinates createCoordinates(){
        Double coordinateX = RefDoubleReader.read("Введите координату x рабочего: ", false);
        Integer coordinateY = RefIntegerReader.readWithoutLimit("Введите координату y рабочего: ", false);
        return new Coordinates (coordinateX, coordinateY);
    }

    public Organization createOrganization(){
        String fullNameOrganization = RefStringReader.read("Введите имя организации: ", true);
        Float annualTurnover = RefFloatReader.read("Введите годовой оборот организации: ", true,0, "MIN");
        Integer employeesCount = RefIntegerReader.read("Введите количество сотрудников организации: ", true, 0 , "MIN");

        return new Organization(fullNameOrganization, annualTurnover, employeesCount, createAddress());
    }

    public Address createAddress(){
        String street = RefStringReader.read("Введите улицу организации: ",false);
        String zipCode = RefStringReader.read("Введите почтовый индекс организации: ", false);

        return new Address(street, zipCode, createLocation());
    }

    public Location createLocation(){
        Float xTown = RefFloatReader.readWithoutLimit("Введите координату X организации в городе: ", false);
        Float yTown = RefFloatReader.readWithoutLimit("Введите координату Y организации в городе: ", false);
        Double zTown = RefDoubleReader.read("Введите координату Z организации в городе: ", false);

        return new Location(xTown, yTown, zTown);
    }

    public Worker createScriptWorker(ArrayList<String> parameters) {
        if (validateArray(parameters)) {
            return new Worker(parameters.get(0),
                    new Coordinates(Double.parseDouble(parameters.get(4)), Integer.parseInt(parameters.get(5))),
                    Float.parseFloat(parameters.get(1)), Position.valueOf(parameters.get(2)), Status.valueOf(parameters.get(3)),
                    new Organization(parameters.get(6), Float.parseFloat(parameters.get(7)), Integer.parseInt(parameters.get(8)),
                            new Address(parameters.get(9), parameters.get(10),
                                    new Location(Float.parseFloat(parameters.get(11)), Float.parseFloat(parameters.get(12)), Double.parseDouble(parameters.get(13))))));
        } else { System.out.println("Один из параметров не соответствует требованиям."); }

        return null;
    }

    private boolean validateArray(ArrayList<String> parameters) {
        try {
            return !parameters.get(0).isEmpty()
                    && Float.parseFloat(parameters.get(1)) > 0f
                    && (PositionReader.checkExist(parameters.get(2)) || parameters.get(2).isEmpty())
                    && (StatusReader.checkExist(parameters.get(3)) || parameters.get(3).isEmpty())
                    && isDouble(parameters.get(4))
                    && isInteger(parameters.get(5))
                    && Float.parseFloat(parameters.get(7)) > 0
                    && Integer.parseInt(parameters.get(8)) > 0
                    && !parameters.get(9).isEmpty()
                    && !parameters.get(10).isEmpty()
                    && isFloat(parameters.get(11))
                    && isFloat(parameters.get(12))
                    && isDouble(parameters.get(13));
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isInteger(String parameter) {
        if (parameter.isEmpty()) return false;
        try {
            Integer.parseInt(parameter);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isFloat(String parameter) {
        if (parameter.isEmpty()) return false;
        try {
            Float.parseFloat(parameter);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isDouble(String parameter) {
        if (parameter.isEmpty()) return false;
        try {
            Double.parseDouble(parameter);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
