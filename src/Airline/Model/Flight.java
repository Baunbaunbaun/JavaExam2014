package Airline.Model;

import Airline.Application;
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
    public final Airport from;
    public final Airport to;
    public Date departureTime;

   //TODO: Staff shall in be a table
    private HashSet<Staff> crew = new HashSet<Staff>();

   //TODO: Remarks shall in be a table
    public HashSet<String> recordOfRemarks = new HashSet<String>();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

   //TODO: Bookings shall in be a table
    private HashMap<Customer, Booking> bookings = new HashMap<Customer, Booking>();

    public HashMap<Customer, Booking> getBookings() {
        return bookings;
    }

    public Flight(Aircraft aircraft, Airport from, Airport to, String time) throws DateException, ParseException {

        this.aircraft = aircraft;
        this.from = from;
        this.to = to;

        //TODO: catch if its a previous date.
        //Parse the date
        try {
            departureTime = sdf.parse(time);
        } catch (ParseException pe) {
            System.out.println("Incorrect date format. Should be: yyyy-MM-dd hh:mm");
        }

        //Create random flight.no. CH-CH-int-int-int-int
        Random rand = new Random();
        int number;
        String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int RANDOM_STRING_LENGTH = 2;
        StringBuilder sb = new StringBuilder();

        //Two uppercase characters
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            number = rand.nextInt(26);
            char ch = CHAR_LIST.charAt(number);
            sb.append(ch);
        }

        //Four digits
        while (sb.length() < 6) {
            sb.append(rand.nextInt(10));
        }
        this.flightNumber = sb.toString();

        //TODO: put remarks in a Remarks Table
        //For the record
        Date recordDate = new Date();
        this.recordOfRemarks.add("Flight.no. " + this.flightNumber + " was scheduled " + sdf.format(recordDate));
    }

    public boolean addStaff(Staff staff) {
        if (this.crew.size() < this.aircraft.crewCapacity) {

            //TODO: put remarks in a Remarks Table (and Staff in a Staff Table)
            //For the record
            Date recordDate = new Date();
            this.recordOfRemarks.add(sdf.format(recordDate) +
                    "Staff " + staff + " was added to flight.no. " + this.flightNumber);
            return this.crew.add(staff);
        }
        return false;
    }

    public void clearStaff() {
        this.crew.clear();

        //TODO: put remarks in a Remarks Table
        //For the record
        Date recordDate = new Date();
        this.recordOfRemarks.add(sdf.format(recordDate) +
                " All staff was cleared from flight.no. " + this.flightNumber);
    }

    public void bookSeat(Customer customer) throws BookingException {










        if (this.availableSeats() > 0 && !this.bookings.containsKey(customer)) {

            this.bookings.put(customer, new Booking((customer)));


            //TODO: put remarks in a Remarks Table
            //For the record
            Date recordDate = new Date();
            this.recordOfRemarks.add(sdf.format(recordDate) +
                    " " + customer + " was booked on flight.no. " + this.flightNumber);
        }
        else {
            throw new BookingException();
        }
    }

    public void checkIn(Customer customer) {
        this.bookings.get(customer).checkIn();

        //TODO: put remarks in a Remarks Table

        //For the record
        Date recordDate = new Date();
        this.recordOfRemarks.add(sdf.format(recordDate) +
                " " + customer + " was checked-in on flight.no. " + this.flightNumber);
    }

    public HashSet<String> getRecordOfRemarks() {
        return recordOfRemarks;
    }

    public int availableSeats() {
        return (this.aircraft.passengerCapacity - this.bookings.size());
    }

    public boolean save() {
        //Application.dataStoreFile.saveFlight(this);
        Application.dataStoreDB.saveFlight(this);
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

        public void checkIn(Customer customer) {
            this.checkedIn = true;

            //TODO:  create table column (in bookings table) with boolean checkedIn

        }

        //Overload
        public void checkIn() {
        }
    }
}