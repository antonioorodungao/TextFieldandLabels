package model;

import java.io.*;
import java.sql.*;
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
        connectionProps.put("user", "udemy");
        connectionProps.put("password", "123456");

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

    public void save() throws SQLException {

        String checkSql = "SELECT COUNT(*) AS COUNT FROM people WHERE id=?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);


        String insertSql = "INSERT INTO people(id,NAME,occupation,agecategory,empcategory,taxid,isuscitizen,gendercategory) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertSql);

        String updateSql = "UPDATE people SET NAME=?, occupation=?, agecategory=?,empcategory=?,taxid=?,isuscitizen=?, gendercategory=? WHERE id=?";
        PreparedStatement updateStmt = conn.prepareStatement(updateSql);

        for(Person person: people){
            int id = person.getId();
            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);

            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory ageCat = person.getAgeCat();
            EmpCategory empCategory = person.getEmpcat();
            String taxID = person.getTaxID();
            boolean isUSCitizen = person.isUsCitizen();
            GenderCategory gender = person.getGender();

            if(count == 0){
                int col = 1;
                insertStmt.setInt(col++, id);
                insertStmt.setString(col++, name);
                insertStmt.setString(col++, occupation);
                insertStmt.setString(col++, ageCat.name());
                insertStmt.setString(col++, empCategory.name());
                insertStmt.setString(col++, taxID);
                insertStmt.setString(col++, Boolean.toString(isUSCitizen));
                insertStmt.setString(col++, gender.name());
                insertStmt.executeUpdate();
                System.out.println("Inserting person with ID " + id);
            }else{
                int col = 1;
                updateStmt.setString(col++,name);
                updateStmt.setString(col++,occupation);
                updateStmt.setString(col++,ageCat.name());
                updateStmt.setString(col++,empCategory.name());
                updateStmt.setString(col++,taxID);
                updateStmt.setString(col++,Boolean.toString(isUSCitizen));
                updateStmt.setString(col++,gender.name());
                updateStmt.setInt(col++, id);
                updateStmt.executeUpdate();
                System.out.println("Updating person with ID " + id);
            }
        }
        checkStmt.close();
        insertStmt.close();
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

    public void load() throws SQLException {
        people.clear();
        String sql = "SELECT id,NAME,occupation,agecategory,empcategory,taxid,isuscitizen,gendercategory FROM people ORDER BY NAME";
        Statement selectStatement = conn.createStatement();

        ResultSet rs = selectStatement.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String occupation = rs.getString("occupation");
            String ageCategory = rs.getString("agecategory");
            String empCategory = rs.getString("empcategory");
            String taxid = rs.getString("taxid");
            boolean isuscitizen = Boolean.parseBoolean(rs.getString("isuscitizen"));
            String gendercategory = rs.getString("gendercategory");

            Person person = new Person(id,name,occupation, AgeCategory.valueOf(ageCategory), EmpCategory.valueOf(empCategory), taxid, isuscitizen, GenderCategory.valueOf(gendercategory));
            people.add(person);
            System.out.println("Loading person" + person);
        }
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
