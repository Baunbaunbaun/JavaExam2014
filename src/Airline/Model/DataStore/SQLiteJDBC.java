package Airline.Model.DataStore;

import Airline.Model.Flight;
import Airline.Model.Person.Customer;
import Airline.Model.Person.Person;
import Airline.Model.Person.Staff;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

public class SQLiteJDBC implements IDataStorage, Serializable {

    private final String path;

    public SQLiteJDBC(final String path) {
        this.path = path;

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);

            stmt = c.createStatement();
            String sql =
                    "CREATE TABLE if not exists Flight " +
                            "(id            INTEGER PRIMARY KEY," +
                            " flightNumber  TEXT    NOT NULL," +
                            " aircraft      TEXT    NOT NULL, " +
                            " departCity    TEXT    NOT NULL, " +
                            " arriveCity    TEXT    NOT NULL, " +
                            " departTime    DATE    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public boolean saveFlight(Flight flight) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);

            stmt = c.createStatement();
            String sql =
                    "CREATE TABLE if not exists Flight " +
                            "(id            INTEGER PRIMARY KEY," +
                            " flightNumber  TEXT    NOT NULL," +
                            " aircraft      TEXT    NOT NULL, " +
                            " departCity    TEXT    NOT NULL, " +
                            " arriveCity    TEXT    NOT NULL, " +
                            " departTime    DATE    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Flight VALUES (" +
                    "null, '" +
                    flight.flightNumber + "', '" +
                    flight.aircraft + "', '" +
                    flight.from + "', '" +
                    flight.to + "', '" +
                    flight.departureTime + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return true;
    }

    @Override
    public HashSet<Flight> getAllFlights() {
        return null;
    }

    public void printAllFlights() {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flight;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String flightnumber = rs.getString("flightnumber");
                String aircraft = rs.getString("aircraft");
                String departCity = rs.getString("departCity");
                String arriveCity = rs.getString("arriveCity");
                //Date departTime = rs.getDate("departTime");
                System.out.println("ID = " + id);
                System.out.println("FLIGHT.NO. = " + flightnumber);
                System.out.println("AIRCRAFT = " + aircraft);
                System.out.println("FROM = " + departCity);
                System.out.println("TO = " + arriveCity);
                //TODO: make it possible to print this date, error parsing date, from sqlite
                //System.out.println("Date = " + departTime);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public String getAllFlightAsString() {

        StringBuilder sb = new StringBuilder();
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flight;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String flightnumber = rs.getString("flightnumber");
                //String aircraft = rs.getString("aircraft");
                String departCity = rs.getString("departCity");
                String arriveCity = rs.getString("arriveCity");
                //Date departTime = rs.getDate("departTime");
                sb
                        .append("TABLE.NO. = ").append(id).append("\n")
                        .append("FLIGHT.NO. = ").append(flightnumber).append("\n")
                        .append("FROM       = ").append(departCity).append("\n")
                        .append("TO         = ").append(arriveCity).append("\n").append("\n");
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return sb.toString();
    }

    @Override
    public HashSet<Flight> getAllFlights(Person person) {
        return null;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return false;
    }

    public boolean saveCustomer(Customer customer) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);

            stmt = c.createStatement();
            String sql =
                    "CREATE TABLE if not exists customer " +
                            "(id            INTEGER PRIMARY KEY, " +
                            " idNumber      TEXT    NOT NULL, " +
                            " name          TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Customer VALUES (" +
                    "null, '" +
                    customer.idNumber + "', '" +
                    customer.name + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return true;
    }

    public String getAllCustomersAsString() {

        StringBuilder sb = new StringBuilder();
        Connection c = null;
        Statement stmt = null;

        //String (int beginIndex, int endIndex)

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String idNumber = rs.getString("idNumber");
                String name = rs.getString("name");
                //Date departTime = rs.getDate("departTime");
                sb
                        .append("Table number: ").append(id).append("\n")
                        .append("Customer: ").append(name).append("\n")
                        .append("Born: ").append(idNumber.substring(0, 6)).append("\n").append("\n");
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return sb.toString();
    }

    public boolean saveStaff(Staff staff) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);

            stmt = c.createStatement();
            String sql =
                    "CREATE TABLE if not exists Staff " +
                            "(id            INTEGER PRIMARY KEY, " +
                            " idNumber      TEXT    NOT NULL, " +
                            " name          TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO Staff VALUES (" +
                    "null, '" +
                    staff.idNumber + "', '" +
                    staff.name + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return true;
    }

    public String getAllStaffAsString() {

        StringBuilder sb = new StringBuilder();
        Connection c = null;
        Statement stmt = null;

        //String (int beginIndex, int endIndex)

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String idNumber = rs.getString("idNumber");
                String name = rs.getString("name");
                //Date departTime = rs.getDate("departTime");
                sb
                        .append("Table number: ").append(id).append("\n")
                        .append("Staff: ").append(name).append("\n")
                        .append("with ID: ").append(idNumber.substring(0, 4)).append("\n").append("\n");
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return sb.toString();
    }

 /*   public boolean saveBooking(Customer customer) {

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);

            stmt = c.createStatement();
            String sql =
                    "CREATE TABLE if not exists BookingCustomer " +
                            "(id                    INTEGER PRIMARY KEY, " +
                            " customerPrimKey       INTEGER    NOT NULL, " +
                            " flightPrimKey         INTEGER    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/" + this.path);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO BookingCustomer VALUES (" +
                    "null, '" +





                    staff.idNumber + "', '" +
                    staff.name + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return true;
    }*/


}