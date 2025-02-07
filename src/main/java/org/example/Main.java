package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Suzuki("Celerio", 2014, "Механика", "Голубой", 65, false));
        cars.add(new Suzuki("Aerio", 2001, "Автомат", "Серый", 110, false));
        cars.add(new Toyota("Auris", 2006, "Механика", "Голубой", 90, false));
        cars.add(new Toyota("Corolla", 2003, "Механика", "Зелёный", 120, false));
        cars.add(new BMW("X5", 2018, "Автомат", "Белый", 184, false));
        cars.add(new BMW("X4", 2014, "Механика", "Красный", 184, false));
        cars.add(new Mercedes("C-Class", 2012, "Автомат", "Зелёный", 200, false));
        cars.add(new Mercedes("W124", 1984, "Автомат", "Серебряный", 72, false));
        cars.add(new Volkswagen("Golf Plus", 2005, "Автомат", "Красный", 75, false));
        cars.add(new Volkswagen("Q7", 2022, "Автомат", "Чёрный", 330, true));

        printRecentCars(cars);
        changeGreenToRed(cars);
        countElectricCars(cars);
    }

    public static void printRecentCars(List<Car> cars) {
        for (Car car : cars) {
            if (car.getYear() > 2006)
                System.out.println(car.getInfo());
            else
                System.out.println("Устаревший авто: " + car.getName());
        }
    }

    public static void changeGreenToRed(List<Car> cars) {
        for (Car car : cars) {
            if (Objects.equals(car.getColor(), "Зелёный")) {
                car.setColor("Красный");
                System.out.println(car.getName() + " теперь красный.");
            }
        }
    }

    public static void countElectricCars(List<Car> cars) {
        int count = 0;
        for (Car car : cars) {
            if (Objects.equals(car.getTransmission_box(), "Механика")) {count++;}
        }
        System.out.println("Количество авто на механике: " + count);
    }
}