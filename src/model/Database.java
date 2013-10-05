package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/5/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Database {
    private ArrayList<Person> people;

    public Database(){
        people = new ArrayList<Person>();
    }

    public void addPerson(Person person){
        people.add(person);
    }

    public List<Person> getPeople(){
        return people;
    }
}
