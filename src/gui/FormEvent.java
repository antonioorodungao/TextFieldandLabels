package gui;

import model.EmpCategory;

import java.util.EventObject;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/14/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class FormEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException
     *          if source is null.
     */

    private String name;
    private String occupation;
    private int ageCat;
    private String taxID;
    private boolean usCitizen;
    private String gender;



    private EmpCategory empCat;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int agecat, String taxID, boolean  usCitizen,
            String gender, EmpCategory empCat){
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCat = agecat;
        this.taxID = taxID;
        this.usCitizen = usCitizen;
        this.gender = gender;
        this.empCat = empCat;
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

    public int getAgeCat() {
        return ageCat;
    }

    public void setAgeCat(int ageCat) {
        this.ageCat = ageCat;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public EmpCategory getEmpCat() {
        return empCat;
    }

    public void setEmpCat(EmpCategory empCat) {
        this.empCat = empCat;
    }
}
