package org.prog.airport;

import java.util.HashMap;

public class Plane {

    public String flightId;
    public String destination;
    public Passenger[] boardedPassenger = new Passenger[5];
    //23C
    public final HashMap<String, Passenger> passengerSitting = new HashMap<>();

    public void boardPassenger(Passenger passenger) {
        for (int i = 0; i < boardedPassenger.length; i++) {
            if (boardedPassenger[i] == null) {
                boardedPassenger[i] = passenger;
                return;
            }
        }

        //TODO: if place already taken - throw new RuntimeException
    }

    public void takeOff() {
        System.out.println("Flight " + flightId + " went to " + destination);
    }
}
