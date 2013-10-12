package gui;

import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/12/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class PersonTableModel extends AbstractTableModel {

    private List<Person> db;

    public void PersonTableModel(){
    }

    public void setDB(List<Person> db){
        this.db = db;
    }

    public int getRowCount() {
        return db.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getColumnCount() {
        return 8;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = db.get(rowIndex);
        switch(columnIndex){
            case  0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCat();
            case 4:
                return person.getEmpcat();
            case 5:
                return person.getTaxID();
            case 6:
                return person.isUsCitizen();
            case 7:
                return person.getGender();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Occupation";
            case 3:
                return "Age Category";
            case 4:
                return "Employment Category";
            case 5:
                return "Tax ID";
            case 6:
                return "US Citizen";
            case 7:
                return "Gender";
            default:
                return null;
        }
    }


}
