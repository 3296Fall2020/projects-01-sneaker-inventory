package com.company.sneakerinvetory.MySQLConnction;

import java.sql.*;

public class DatabaseOperation {
    Connection connect; // where connection to database will be stored on createConnection call
    Statement statement; // interface to represent SQL statement

    /*public static void main(String[] args) { // main used for testing
        DatabaseOperation database = new DatabaseOperation();
        database.createConnect(); // create connect, needs to be done it initialize connect variable
        //database.printUsers();
       // System.out.println(database.addUser("Tony", "password")); // register
        //System.out.println(database.signIn("Akeem","password")); // login
        database.closeConnection(); // log off
    }*/

    public void createConnect() {
        try {
            //Class.forName("com.mysql.jdbc.Driver"); // load mysql driver
            connect = DriverManager.getConnection("jdbc:mysql://sneaker-inventory.ckxchjjiiu5r.us-east-2.rds.amazonaws.com:3306/SneakerInventory", "admin", "sneaker5"); // database & driver:// serveraddress:port/schema name
            System.out.println("Connection established");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //} catch (ClassNotFoundException e) {
            //     e.printStackTrace();
            //  }
        }
    }

    public void printUsers() {

        ResultSet results = null;
        try {
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM user_table");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while (true) {
            try {
                if (!results.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String value = null;
            try {
                value = results.getString(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.print("user_id: " + value +", ");

            try {
                value = results.getString(2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("user_password: " + value);
        }
    }

    public boolean addUser(String userID, String password) {
        try {
            statement = connect.createStatement(); // create statement to be exicuted on connection
            statement.executeUpdate("INSERT INTO user_table  VALUES('" + userID + "','" + password + "')");
            return true;
        } catch (SQLException throwables) {
            //System.out.println("User already exists");
            return false;
        }
        //System.out.println("User added");
    }

    public boolean signIn(String userID ,String password) {
        ResultSet results = null;

        try {
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT user_password FROM user_table WHERE user_id LIKE '"+userID+"'"); // get specific entry from database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while(true) {
            try {
                if (!results.next()){ // if no match
                    //System.out.println("Please check the username and password");
                    return  false;
                }
                else if (password.equals(results.getString(1))) { // only 1 col retrieved
                    //System.out.println("User can sign in"); // if match
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void closeConnection() {
        try {
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
