package org.prog.airport;

public class Airport {

    public Passenger[] passengers = new Passenger[10];
    //    public List<Passenger> passengers = new ArrayList<>();
    public Plane[] planes = new Plane[3];

    public void addPassenger(Passenger arrivingPassenger) {
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = arrivingPassenger;
                break;
            }
        }
    }

    public void addPlane(Plane arrivingPlane) {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i] == null) {
                planes[i] = arrivingPlane;
                return;
            }
        }
    }

    public void boardPlane(Passenger passenger, Plane plane) {
        plane.boardPassenger(passenger);
    }
}
