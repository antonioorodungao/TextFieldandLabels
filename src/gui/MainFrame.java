package gui;

import controller.Controller;
import model.EmpCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/13/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JButton btn;
    private Toolbar toolbar;
    private FormPanel formPanel;
    JFileChooser fileChooser;
    private Controller controller; //TODO:
    private TablePanel table;

    public MainFrame() {
        super("Udemy");
        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        btn = new JButton("Click Me");
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        controller = new Controller();
        table = new TablePanel();
        table.setData(controller.getPeople());

        //
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new PersonFileFilter());

        //add(textArea, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        //add(textPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.WEST);
        add(createMenuBar(), BorderLayout.NORTH);
        //Table

        add(table, BorderLayout.CENTER);

        toolbar.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                textPanel.appendText(text);

            }
        });

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("Pressed");

            }
        });

        formPanel.setFormListener(new FormListener(){
            public void formEventOccurred(FormEvent e){
                controller.addPerson(e);
                table.refresh();

            }
        });


        setMinimumSize(new Dimension(500,400));
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");


        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu showMenu = new JMenu("Show");
        //JMenuItem showForm = new JMenuItem("Person Form");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);

        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        //setup Mnemonic
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainFrame.this, "Enter your user name. ", "Enter User Name", JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application? " + name, "Confirm Exit", JOptionPane.OK_CANCEL_OPTION | JOptionPane.WARNING_MESSAGE);

                if(action == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        System.out.println(fileChooser.getSelectedFile());
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        table.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load from file ", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    };

                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                        table.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save to file ", "Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                }
            }
        });



        return  menuBar;


    }




}
