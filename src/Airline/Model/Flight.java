package Airline.Model;

import Airline.Model.Aircraft.Aircraft;
import Airline.Model.Person.Customer;
import Airline.Model.Person.Staff;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Flight implements Serializable {

    public final Aircraft aircraft;
    public final String id;
    public String idTest;
    public final Airport from;
    public final Airport to;
    public Date departureTime;
    private HashSet<Staff> crew = new HashSet<Staff>();

    private Random random;

    public HashMap<Customer, Booking> getBookings() {
        return bookings;
    }

    private HashMap<Customer, Booking> bookings = new HashMap<Customer, Booking>();

    public Flight(Aircraft aircraft, Airport from, Airport to, int time) throws DateException{

        this.from = from;
        this.to = to;
        this.aircraft = aircraft;

        Random random = new Random();
        int idNumber = 0;
        String idString;
        int length = 0;
        String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int RANDOM_STRING_LENGTH = 2;
        StringBuffer randStr = new StringBuffer();

        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = random.nextInt(26);
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }

        for (int i = 0; length < 4; i++) {
            idNumber = idNumber + random.nextInt(9999);
            length = String.valueOf(idNumber).length();
            idNumber = (length > 4) ? 0 : idNumber;
        }
        this.idTest = randStr.toString() + idNumber;
        this.id = idTest.toUpperCase();

        System.out.println("\n flight no." + this.id);
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

    public class DateException extends Exception {
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