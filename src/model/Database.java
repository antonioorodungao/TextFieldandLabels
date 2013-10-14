package model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Database {
    private LinkedList<Person> people;
    private Connection conn = null;

    public Database(){
        people = new LinkedList<Person>();
    }

    public void connect() throws SQLException {

        if(conn != null)
            return;

        Properties connectionProps = new Properties();
        connectionProps.put("user", "twm3_mod");
        connectionProps.put("password", "twm123");

        //Check if the driver exists
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String connectUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
        conn = DriverManager.getConnection(connectUrl, connectionProps);
    }

    public void disconnect(){

    }

    public void addPerson(Person person){
        people.add(person);
    }

    public void removePerson(int index){
        people.remove(index);
    }

    public List<Person> getPeople(){
        return Collections.unmodifiableList(people);
    }

    public void saveToFile(File file) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = people.toArray(new Person[people.size()]);
        oos.writeObject(persons);

        oos.close();


    }
    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            Person[] persons = (Person[]) ois.readObject();
            people.clear();
            people.addAll(Arrays.asList(persons));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
