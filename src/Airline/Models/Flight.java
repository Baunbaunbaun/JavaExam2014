package Airline.Models;

import Airline.Models.Aircrafts.Aircraft;
import Airline.Program;

import java.io.Serializable;
import java.util.HashSet;

public class Flight implements Serializable {

    private HashSet<Staff> crew;
    //passenger list with customers and seat numbers
    private HashSet<Customer> passengers;
    private Aircraft aircraft;
    //make this UUID
    public int id;

    //constructor
    public Flight(Aircraft aircraft) {
        this.aircraft = aircraft;
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

    //what have I booked?
    public void myBookings(){
        //return result from database
    }

    //get number of available seats
    public int availableSeats() {
        return (this.aircraft.passengerCapacity - this.passengers.size());
    }

    //print passenger list
    public void passengerList() {
        System.out.println(this.passengers);
    }
}
