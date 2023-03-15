package org.prog.poly;

public class PolyDemo {

    public static void main(String... args) {
        Car car = new Car();
        car.drive();
        car.fuel();

        Tesla tesla = new Tesla();
        tesla.drive();
        tesla.charge();

        driveTheCar(car, "NY");
        driveTheCar(tesla, "NY");
    }

    public static void driveTheCar(ICar iCar, String destination) {
        System.out.println("Car is going to " + destination);
        iCar.drive();
    }
}
