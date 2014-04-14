package Airline.Models;

import Airline.Models.Aircrafts.Aircraft;
import Airline.Program;
import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class Flight implements Serializable {

    private HashSet<Staff> crew;
    //passenger list with customers and seat numbers
    private HashSet<Customer> passengers;
    private Date departureDate;
    private Aircraft aircraft;
    private Airport departure;
    private Airport destination;
    public UUID flightNo;

    //constructor
    public Flight(Date date, Aircraft aircraft, Airport departure, Airport destination) {
        this.departureDate = date;
        this.aircraft = aircraft;
        this.departure = departure;
        this.destination = destination;
        this.flightNo = UUID.randomUUID();
    }

    //save method
    public boolean save() {
        Program.ds.createFlight(this);
        return true;
    }

    //add staff
    public boolean addStaffToCrew(Staff staff) {

        if (this.crew.size() < this.aircraft.crewCapacity) {
            this.crew.add(staff);
            return true;
        } else {
            System.out.println("Crew is full");
            return false;
        }
    }

    //remove staff
    public boolean removeStaff(Staff staff) {
        return this.crew.remove(staff);
    }

    //add passenger
    public boolean addPassenger(Customer customer) {

        if (this.availableSeats() != 0) {
            this.passengers.add(customer);
            return true;
        } else {
            return false;
        }
    }
/*
    //what have I booked?
    public void myBookings(){
        //return result from database
    }*/

    //get number of available seats
    public int availableSeats() {
        return (this.aircraft.passengerCapacity - this.passengers.size());
    }

    //print passenger list
    public void passengerList() {
        System.out.println(this.passengers);
    }
    @Override
    public String toString(){
        return ("Flight No.: " + this.flightNo +
                "\nDeparture: " + this.departureDate +
                "\nFrom: " + this.departure + " To: " + this.destination);
    }
}
