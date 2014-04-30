package Airline.Model;

import Airline.Model.Aircraft.Aircraft;
import Airline.Model.Person.Customer;
import Airline.Model.Person.Staff;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Flight implements Serializable {

    public final Aircraft aircraft;
    public final String flightNumber;
    public String idTest;
    public final Airport from;
    public final Airport to;
    public Date departureTime;
    private HashSet<Staff> crew = new HashSet<Staff>();
    public HashSet<String> recordOfRemarks = new HashSet<String>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private Random random;

    public HashMap<Customer, Booking> getBookings() {
        return bookings;
    }

    private HashMap<Customer, Booking> bookings = new HashMap<Customer, Booking>();

    public Flight(Aircraft aircraft, Airport from, Airport to, String time) throws DateException, ParseException {

        this.aircraft = aircraft;
        this.from = from;
        this.to = to;

        // Parse the date
        try {
            departureTime = sdf.parse(time);
        } catch (ParseException pe) {
            System.out.println("Incorrect date format. Should be: yyyy-MM-dd hh:mm");
        }

        Random random = new Random();
        int idNumber = 0;
        int length = 0;
        String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int RANDOM_STRING_LENGTH = 2;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = random.nextInt(26);
            char ch = CHAR_LIST.charAt(number);
            builder.append(ch);
        }

        for (int i = 0; length < 4; i++) {
            idNumber = idNumber + random.nextInt(9999);
            length = String.valueOf(idNumber).length();
            idNumber = (length > 4) ? 0 : idNumber;
        }
        //this.idTest = builder.toString() + idNumber;
        //this.flightNumber = idTest.toUpperCase();
        this.flightNumber = builder.toString() + idNumber;

        Date recordDate = new Date();
        this.recordOfRemarks.add("Flight.no. " + this.flightNumber + " was scheduled " + sdf.format(recordDate));
    }

    public boolean addStaff(Staff staff) {
        if (this.crew.size() < this.aircraft.crewCapacity) {
            Date recordDate = new Date();
            this.recordOfRemarks.add(sdf.format(recordDate) +
                    "[ Staff " + staff + " was added to flight.no. " + this.flightNumber + " ]" );
            return this.crew.add(staff);
        }
        return false;
    }

    public void clearStaff() {
        this.crew.clear();
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
        Date recordDate = new Date();
        this.recordOfRemarks.add(sdf.format(recordDate) +
                "[ " + customer + " was checked-in on flight.no. " + this.flightNumber + " ]" );
    }

    public HashSet<String> getRecordOfRemarks() {
        return recordOfRemarks;
    }

    public int availableSeats() {
        return (this.aircraft.passengerCapacity - this.bookings.size());
    }

    public boolean save() {
        //Model.dataStore. saveFlight(this);
        Model.dataStoreDB.saveFlight(this);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("FLIGHT: ").append(this.flightNumber).append("\n")
                .append(this.from).append("-").append(this.to).append("\n")
                .append("AIRCRAFT: ").append(aircraft.model).append("\n")
                .append("SEATS AVAILABLE: ").append(this.availableSeats()).append("\n");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.flightNumber.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Flight) {
            Flight f = (Flight) other;
            return this.flightNumber.equals(f.flightNumber);
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