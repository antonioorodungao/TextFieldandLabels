package controller;

import gui.FormEvent;
import model.*;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Controller {
    Database db = new Database();

    public void addPerson(FormEvent ev){
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCat();
        EmpCategory empCat = ev.getEmpCat();
        boolean isUS = ev.isUsCitizen();
        String taxID = ev.getTaxID();
        String gender = ev.getGender();

        AgeCategory ageCategory = null;
        switch (ageCatId){
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;

        }

        GenderCategory genderCategory = null;
        if(gender.equals("male")){
            genderCategory = GenderCategory.male;
        }else if(gender.equals("female")){
            genderCategory = GenderCategory.female;
        }

        Person person = new Person(name,occupation, ageCategory,empCat,taxID,isUS, genderCategory);
        db.addPerson(person);

    }
}
