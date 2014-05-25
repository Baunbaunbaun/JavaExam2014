package Airline.Model.Person;

import Airline.Application;

public class Customer extends Person {

    public Customer(String name, int cpr) {
        super(name, cpr);
    }

    //Overload
    //to be able to create a staff member first time they log in (with only the cpr number and no name)
    public Customer(int cpr) {
        super(cpr);
    }

    public boolean save(){
        Application.dataStoreDB.saveCustomer(this);
        return true;
    }
}
