package gui;

import controller.Controller;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

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
    private TablePanel tablePanel;
    private PrefsDialog prefsDialog;
    private Preferences prefs;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;


    public MainFrame() {
        super("Udemy");
        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        btn = new JButton("Click Me");
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        controller = new Controller();
        tablePanel = new TablePanel();
        tablePanel.setData(controller.getPeople());
        prefsDialog = new PrefsDialog(this);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Person Data", tablePanel);
        tabbedPane.addTab("Messages", textPanel);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabbedPane);
        splitPane.setOneTouchExpandable(true);



        prefs = Preferences.userRoot().node("db");

        tablePanel.setPersonTableListener(new PersonTableListener() {
            public void rowDeleted(int row) {
                controller.removePerson(row);
                tablePanel.refresh();
            }
        });

        //
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new PersonFileFilter());

        //add(textArea, BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
        add(toolbar, BorderLayout.PAGE_START);

        add(splitPane, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        //add(formPanel, BorderLayout.WEST);

        //Table

        //add(tablePanel, BorderLayout.CENTER);


        //Listeners////////////////

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing");
                dispose();
                //System.gc();
            }
        });

        toolbar.setToolbarListener(new ToolbarListener() {
            public void saveEventOccured() {
                System.out.println("Save");
                connect();
                try {
                    controller.save();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database", "Database Connection Failed", JOptionPane.ERROR_MESSAGE);
                }

            }

            public void refreshEventOccured(){
                System.out.println("Refresh");
                connect();
                try {
                    controller.load();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database", "Database Connection Failed", JOptionPane.ERROR_MESSAGE);
                }
                tablePanel.refresh();

            }
        });

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("Pressed");

            }
        });

        prefsDialog.setPreferenceListener(new PrefsListener() {
            public void preferencesSet(String user, String password, int port) {
                System.out.println(user + " " + password + " " + port);
                prefs.put("username", user);
                prefs.put("password", password);
                prefs.put("port", new Integer(port).toString());

            }
        });

        formPanel.setFormListener(new FormListener(){
            public void formEventOccurred(FormEvent e){
                controller.addPerson(e);
                tablePanel.refresh();

            }
        });

        String user = prefs.get("user", "");
        String passwd = prefs.get("password", "");
        String port = prefs.get("port", "3306");
        prefsDialog.setDefaults(user, passwd, port);


        setMinimumSize(new Dimension(500,400));
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void connect(){
        try {
            controller.connect();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
        }
    }



    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");
        JMenuItem prefsItem = new JMenuItem("Preferences...");


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
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
                //formPanel.setVisible(menuItem.isSelected());
                if(menuItem.isSelected()){
                    splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth() );
                }else{
                    splitPane.setDividerLocation(0);
                }

            }
        });

        //setup Mnemonic
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,  ActionEvent.CTRL_MASK));

        //End of Setup Mnemonic

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainFrame.this, "Enter your user name. ", "Enter User Name", JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application? " + name, "Confirm Exit", JOptionPane.OK_CANCEL_OPTION | JOptionPane.WARNING_MESSAGE);

                if(action == JOptionPane.OK_OPTION){
                    WindowListener[] wl = getWindowListeners();
                    for(WindowListener listener: wl){
                        listener.windowClosing(new WindowEvent(MainFrame.this, 0));
                    }
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        System.out.println(fileChooser.getSelectedFile());
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
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
                        tablePanel.refresh();
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
