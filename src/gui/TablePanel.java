package gui;

import model.Database;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/12/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel(){
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table),BorderLayout.CENTER);
    }

    public void setData(List<Person> db){
        tableModel.setDB(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }

}
