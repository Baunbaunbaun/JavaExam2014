package Airline.Models;

import Airline.Models.Aircrafts.Aircraft;
import Airline.Program;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Flight implements Serializable {

    private HashSet<Staff> crew;
    //passenger list with customers and seat numbers
    private HashMap<Customer, Integer> passengers;
    private Aircraft aircraft;
    public int id;

    //constructor
    public Flight(int id, Aircraft aircraft) {
        this.id = id;
        this.aircraft = aircraft;
    }

    //save method
    public boolean save() {

        Program.ds.createFlight(this);

        return true;
    }

    //add staff
    public void addStaffToCrew(Staff staff) {

        if (this.crew.size() != this.aircraft.numberOfCrew) {
            this.crew.add(staff);
        } else {
            System.out.println("Crew is full");
        }
    }
    //remove staff
    public void removeStaff(Staff staff){
        this.crew.remove(staff);
    }

    //clear crew
    public void clearCrew(){
        this.crew.clear();
    }

    //add passenger
    public void addPassenger(Customer customer, int seat){

        this.passengers.put(customer, seat);
    }

    //get number of available seats
    public int availableSeats(){
        return (this.aircraft.passengerCapacity - this.passengers.size());
    }

    //print passenger list
    public void passengerList(){
        System.out.println(this.passengers);
    }
}
