package org.prog.airport;

public class AirportMain {

    public static void main(String... args) {
        Airport airport = new Airport();

        Plane planeOne = new Plane();
        Plane planeTwo = new Plane();

        planeOne.flightId = "AA0011";
        planeTwo.flightId = "BB2233";

        planeOne.destination = "New York";
        planeTwo.destination = "Paris";

        Passenger passengerOne = new Passenger();
        Passenger passengerTwo = new Passenger();
        Passenger passengerThree = new Passenger();

        airport.addPlane(planeOne);
        airport.addPlane(planeTwo);

        airport.addPassenger(passengerOne);
        airport.addPassenger(passengerTwo);
        airport.addPassenger(passengerThree);

        airport.boardPlane(passengerOne, planeOne);
        airport.boardPlane(passengerTwo, planeOne);
        airport.boardPlane(passengerThree, planeTwo);

        passengerOne.name = "John";
        planeOne.boardedPassenger[0].surname = "Doe";
        passengerOne.passengerSitNumber = "11A";

        passengerTwo.passengerSitNumber = "11A";

        planeOne.takeOff();
        planeTwo.takeOff();
    }
}
