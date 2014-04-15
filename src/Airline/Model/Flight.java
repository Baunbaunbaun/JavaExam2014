package Airline.Model;

import Airline.Model.Aircraft.Aircraft;
import Airline.Model.Person.Customer;
import Airline.Model.Person.Staff;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Flight implements Serializable {

    final private Aircraft aircraft;
    public final String id;
    private HashSet<Staff> crew = new HashSet<Staff>();
    private HashMap<Customer, Booking> bookings = new HashMap<Customer, Booking>();
    private Airport from;
    private Airport to;
    private int time;

    public Flight(String id, Aircraft aircraft, Airport from, Airport to, int time) throws FlightNameException {
        if (id.matches("\\w\\w\\d\\d\\d\\d")) {
            this.id = id.toUpperCase();
        } else {
            throw new FlightNameException();
        }
        this.from = from;
        this.to = to;
        this.time = time;
        this.aircraft = aircraft;
    }

    public HashMap<Customer, Booking> getBookings() {
        return bookings;
    }

    public boolean addStaff(Staff staff) {
        if (this.crew.size() < this.aircraft.crewCapacity)
            return this.crew.add(staff);
        return false;
    }

    public void bookSeat(Customer customer) throws BookingException {
        if (this.availableSeats() > 0 && !this.bookings.containsKey(customer))
            this.bookings.put(customer, new Booking((customer)));
        else {
            throw new BookingException();
        }
    }

    public void checkIn(Customer customer) {
        this.bookings.get(customer).checkIn();
    }


    private int availableSeats() {
        return (this.aircraft.passengerCapacity - this.bookings.size());
    }

    public int getTime() {
        return this.time;
    }

    public Airport getTo() {
        return this.to;
    }

    public Airport getFrom() {
        return this.from;
    }

    public Aircraft getAircraft() {
        return this.aircraft;
    }

    public String getId() {
        return this.id;
    }

    public boolean save() {
        Model.dataStore.saveFlight(this);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Id: ").append(this.id).append('\n')
                .append("Aircraft: ").append(aircraft.name);

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Flight) {
            Flight f = (Flight) other;
            return this.id.equals(f.id);
        }
        return false;
    }


    public class BookingException extends Exception {
    }

    public class FlightNameException extends Exception {
    }


    private class Booking implements Serializable {

        private Customer customer;
        private long time;
        private boolean checkedIn = false;

        protected Booking(Customer customer) {
            this.customer = customer;
            this.time = System.currentTimeMillis() / 1000L;
        }

        public void checkIn() {
            this.checkedIn = true;
        }
    }
}
