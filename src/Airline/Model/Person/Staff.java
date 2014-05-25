package Airline.Model.Person;

import Airline.Application;

public class Staff extends Person {

    public Staff(String name, int StaffNumber) {
        super(name, StaffNumber);
    }

    //Overload
    //to be able to create a staff member first time they log in (with only the staff number and no name)
    public Staff(int staffNumber) {
        super(staffNumber);
    }
    public boolean save(){
        Application.dataStoreDB.saveStaff(this);
        return true;
    }
}
