package gui;

import model.Database;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPopupMenu popup;
    private PersonTableListener personTableListener;

    public TablePanel(){
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        setLayout(new BorderLayout());

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);

                if(e.getButton() == MouseEvent.BUTTON3){
                    popup.show(table,e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(personTableListener != null){
                    personTableListener.rowDeleted(row);
                }

            }
        });
    }

    public void setData(List<Person> db){
        tableModel.setDB(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }

    public void setPersonTableListener(PersonTableListener listener){
        this.personTableListener = listener;

    }

}
