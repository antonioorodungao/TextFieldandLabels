package model;

import java.io.Serializable;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Person implements Serializable {

    private static int count = 1;

    private int id;
    private String name;
    private String occupation;
    private AgeCategory ageCat;
    private EmpCategory empcat;
    private String taxID;
    private boolean usCitizen;
    private GenderCategory gender;

    public Person(String name, String occupation, AgeCategory ageCat,
                  EmpCategory empcat, String taxID, boolean usCitizen, GenderCategory gender){
        this.name = name;
        this.occupation = occupation;
        this.empcat = empcat;
        this.ageCat = ageCat;
        this.taxID = taxID;
        this.usCitizen = usCitizen;
        this.gender = gender;
        this.id = count;
        count++;
    }

    public Person(int ID, String name, String occupation, AgeCategory ageCat,
                  EmpCategory empcat, String taxID, boolean usCitizen, GenderCategory gender){

        this(name, occupation, ageCat, empcat, taxID, usCitizen, gender);
        this.id = ID;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCategory getAgeCat() {
        return ageCat;
    }

    public void setAgeCat(AgeCategory ageCat) {
        this.ageCat = ageCat;
    }

    public EmpCategory getEmpcat() {
        return empcat;
    }

    public void setEmpcat(EmpCategory empcat) {
        this.empcat = empcat;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public GenderCategory getGender() {
        return gender;
    }

    public void setGender(GenderCategory gender) {
        this.gender = gender;
    }

    public String toString(){
        return "ID: " + id + "Name: " + name;
    }
}
