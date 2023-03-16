package org.prog.airport;

public class Plane {

    public String flightId;
    public String destination;
    public Passenger[] boardedPassenger = new Passenger[5];

    public void boardPassenger(Passenger passenger) {
        for (int i = 0; i < boardedPassenger.length; i++) {
            if (boardedPassenger[i] == null) {
                boardedPassenger[i] = passenger;
                return;
            }
        }
    }

    public void takeOff() {
        System.out.println("Flight " + flightId + " went to " + destination);
    }
}
