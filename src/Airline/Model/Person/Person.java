package Airline.Model.Person;

import java.io.Serializable;
import java.util.HashSet;

abstract public class Person implements Serializable {

    public String name = "John Doe";
    public final String idNumber; //e.g. CPR.no. (10 digits) or staff.no. (8 digits)

    HashSet<Integer> lenghtEightorTen = new HashSet<Integer>();

    public Person(String name, int number) {

        //Parse the number and check length
        this.idNumber = Integer.toString(number);
        this.name = name;

        lenghtEightorTen.add(8);
        lenghtEightorTen.add(10);

        try {
            lenghtEightorTen.contains(idNumber.length());
        } catch (Exception e) {
            System.out.println("Incorrect number format. Should be: 8 (Staff) or 10 (CPR) digits");
        }
    }

    //Overload - because I want the program to be able to create Person objects,
    // just with a number, when they log in first time
    public Person(int number) {
        //Parse the number and check length
        this.idNumber = Integer.toString(number);

        lenghtEightorTen.add(8);
        lenghtEightorTen.add(10);

        try {
            lenghtEightorTen.contains(idNumber.length());
        } catch (Exception e) {
            System.out.println("Incorrect number format. Should be: 8 (Staff) or 10 (CPR) digits");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (this.name);
    }
}
