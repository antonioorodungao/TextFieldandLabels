package udemy.textfieldlabels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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

    public MainFrame() {
        super("Udemy");
        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        btn = new JButton("Click Me");
        toolbar = new Toolbar();
        formPanel = new FormPanel();

        //add(textArea, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.WEST);
        add(createMenuBar(), BorderLayout.NORTH);

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
                String name = e.getName();
                String occupation = e.getOccupation();
                String ageCat = e.getAgeCat();
                String gender = e.getGender();
                textPanel.appendText("name: " + name + "occupation: " + occupation + " age category: " + ageCat + " gender: " + gender);

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

        return  menuBar;


    }




}
