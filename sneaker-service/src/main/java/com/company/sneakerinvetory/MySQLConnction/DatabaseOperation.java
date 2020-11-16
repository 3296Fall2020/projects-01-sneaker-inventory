package com.company.sneakerinvetory.MySQLConnction;

import java.sql.*;

public class DatabaseOperation {
    Connection connect; // where connection to database will be stored on createConnection call
    Statement statement; // interface to represent SQL statement

    public static void main(String[] args) { // main used for testing
        DatabaseOperation database = new DatabaseOperation();
        database.createConnect(); // create connect, needs to be done it initialize connect variable
        //----------------------------------------------------------------------------------------------
        //user table
        //System.out.println(database.checkName("tony"));//f = free name
        //System.out.println(database.addUser("Tony", "password")); //  t = added
        //System.out.println(database.checkName("tony"));//t = taken
        //System.out.println(database.addUser("tony", "password")); // f = not added

        // System.out.println(database.signIn("Tony","password")); // login t
        // System.out.println(database.signIn("tony","password")); // t because case doesn't matter

        //----------------------------------------------------------
        // inventory table
        //System.out.println(database.insertData(2,"Fila","f-13","12",11,"2018-3-7",100.50,"Tony"));
        //System.out.println(database.editBrand("tony","cocos",1)); // true if done
        //System.out.println(database.editModel("tony","fishScale",1)); // true if done
        //System.out.println(database.editSKU("tony","p1u5",1)); // true if done
        //System.out.println(database.editSize("tony",11.5,1)); // true if done
        //System.out.println(database.editPrice("tony",120,1)); // true if done
        //System.out.println(database.editDate("tony","2020/12/25",1)); // true if done YYYY/MM/DD
        System.out.println(database.editForm(1,"Tims",null,"123shoe",11,null,101.67,"Tony"));
        //--------------------------------------------------------------
        database.closeConnection(); // log off
    }

    public void createConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // load mysql driver
            connect = DriverManager.getConnection("jdbc:mysql://sneaker-inventory.ckxchjjiiu5r.us-east-2.rds.amazonaws.com:3306/SneakerInventory", "admin", "sneaker5"); // database & driver:// serveraddress:port/schema name
            System.out.println("Connection established");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------  user_table functions
    private void printUsers() {

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
    } // don't need was for testing

    public boolean checkName(String userID) throws SQLException { // if userID exists, return true
        ResultSet results = null;
        try {
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT user_id FROM user_table WHERE user_id ='"+userID+"'");
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            return false;
        }
        while (results.next()) {

            try {
                if (results.getString("user_id").equals(userID)) { // if no match
                    //System.out.println("Username available");
                    return true; // no match
                }
                return false;
            } catch (SQLException throwables) {
                //throwables.printStackTrace();
                return false; //empty dataset
            }
        }
        return false;


    }

    public boolean addUser(String userID, String password) {
        try {
            statement = connect.createStatement(); // create statement to be executed on connection
            statement.executeUpdate("INSERT INTO user_table  (user_id, user_password) VALUES('" + userID + "','" + password + "')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        //System.out.println("User added");
        try { // if user added, create userID_inventory
            String tableName = userID.toLowerCase() + "_inventory";
            statement.executeUpdate("CREATE TABLE " + tableName + // create unique user inventory table if add
                    " (index_id INTEGER not NULL AUTO_INCREMENT, " +
                    " shoeName VARCHAR(255) not NULL, " +
                    " sku VARCHAR(255) not NULL, " +
                    " size VARCHAR(255) not NULL, " +
                    " date VARCHAR(255) not NULL, " +
                    " price VARCHAR(255) not NULL, " +
                    " user_id VARCHAR(20)," +
                    " PRIMARY KEY (index_id),"+
                    " FOREIGN KEY ( user_id ) REFERENCES user_table (user_id))");
        } catch(SQLException throwables){ throwables.printStackTrace();return false;} // this should never happen
        return true;

    }

    public boolean signIn(String userID, String password, String sessionID) throws SQLException {
        ResultSet results = null;

        try {
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT user_password FROM user_table WHERE user_id = '"+userID+"'"); // get specific entry from database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        while(results.next()) {
            try {
                if (!results.getString("user_password").equals(password)){ // if no match
                    //System.out.println("Please check the username and password");
                    return  false;
                }
                else if (password.equals(results.getString("user_password"))) { // only 1 col retrieved
                    //System.out.println("User can sign in"); // if match
                    statement.executeUpdate("UPDATE user_table SET sessionID = '" + sessionID + "' WHERE user_id ='"+userID+"'");
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;

    }

    public boolean signOut(String userID, String password) throws SQLException {
        ResultSet results = null;
        try {
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT user_password FROM user_table WHERE user_id ='"+userID+"'"); // get specific entry from database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        while(results.next()) {
            try {
                if (!results.getString("user_password").equals(password)){ // if no match
                    //System.out.println("Please check the username and password");
                    return  false;
                }
                else if (password.equals(results.getString("user_password"))) { // only 1 col retrieved
                    //System.out.println("User can sign in"); // if match
                    statement.executeUpdate("UPDATE user_table SET sessionID = NULL WHERE user_id = '"+userID+"'");
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public String querySessionID(String userID){
        ResultSet results = null;
        String sessionQuery = "SELECT sessionID FROM user_table WHERE user_id = '" + userID + "'";
        String sessionID = "";
        try {
            statement = connect.createStatement();
            results = statement.executeQuery(sessionQuery);
            if (results.next()) {
                sessionID = results.getString("sessionID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sessionID;
    }
//---------------------------------------------------------------------------- inventory functions

    public boolean insertData(String shoeName, String sku, String size , String date, String price, String userID) // first entry needs to be entered via this method
    {
        String tableName = userID.toLowerCase() + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("INSERT INTO "+ tableName + " "+
                    "VALUES (" +shoeName+"','" +sku+"',"+size+",'"+date+"', "+price+",'"+userID+"')" );

        }catch(SQLException throwables){
            throwables.printStackTrace();
            return false;}
        return true;
    }

    boolean editForm(int index, String brand, String model, String sku, double size , String date, double price, String userID) // edits rows that are already created, insert null/0 for unwanted string/number changes
    {
        int isDone = 0; //false
        if(brand != null){
            editBrand(userID,brand,index);
            isDone++;
        }

        if(model != null){
            editModel(userID,model,index);
            isDone++;
        }

        if(sku != null){
            editSKU(userID,sku,index);
            isDone++;
        }

        if(size != 0){
            editSize(userID,size,index);
            isDone++;
        } // 0 is no edit token

        if(date != null){
            editDate(userID,date,index);
            isDone++;
        }

        if(price != 0){
            editPrice(userID,price,index);
            isDone++;
        }

        if(isDone>0){return true;}
        else {return false;}
    }

    boolean editBrand( String userID, String brandName, int index)  {
        String tableName = userID.toLowerCase() + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET brand = '"+brandName+"' "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }

    }

    boolean editModel(String userID, String model, int index){
        String tableName = userID.toLowerCase()  + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET model = '"+model+"' "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    boolean editSKU(String userID, String sku, int index){
        String tableName = userID.toLowerCase() + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET sku_number = '"+sku+"' "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    boolean editSize(String userID, double size, int index){
        String tableName = userID.toLowerCase()  + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET size = "+size+" "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    boolean editDate(String userID, String date, int index){
        String tableName = userID.toLowerCase()  + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET date_bought = '"+date+"' "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }}

    boolean editPrice(String userID, double price, int index){
        String tableName = userID.toLowerCase()  + "_inventory";
        try {
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE " +tableName +" "+
                    "SET price = '"+price+"' "+
                    "WHERE index_id ="+index);
            return true;
        } catch(SQLException throwables){
            throwables.printStackTrace();
            return false;
        }
    }

    //-----------------------------------------------------------------------------
    public void closeConnection() {
        try {
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}