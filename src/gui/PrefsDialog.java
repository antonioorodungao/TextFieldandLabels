package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/13/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class PrefsDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passField;

    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent){
        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        userField = new JTextField(10);
        passField = new JPasswordField(10);
        passField.setEchoChar('*');

        layoutControls();

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer port = (Integer)portSpinner.getValue();
                String user = userField.getText();
                char[] passwd = passField.getPassword();

                System.out.println("user " + new String(passwd));
                System.out.println(port);

                if(prefsListener != null){
                    prefsListener.preferencesSet(user, new String(passwd), port);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 setVisible(false);
            }
        });


        setLocationRelativeTo(parent);
        setSize(300, 200);
        //setResizable(false);


    }

    private void layoutControls(){

        JPanel controlPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");

        controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        controlPanel.setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        Insets rightpadding = new Insets(0, 0, 0 , 15);
        Insets noPadding = new Insets(0, 0, 0 , 0);

        /////////////////FIRST ROW////////////////////////////////////
        gc.gridy = 0;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightpadding;
        controlPanel.add(new JLabel("User: "), gc);

        gc.insets = noPadding;
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx++;
        controlPanel.add(userField, gc);
        //////////////////NEXT ROW/////////////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightpadding;
        controlPanel.add(new JLabel("Password: "), gc);

        gc.insets = noPadding;
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx++;
        controlPanel.add(passField, gc);
        //////////////////NEXT ROW////////////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightpadding;
        controlPanel.add(new JLabel("Port: "), gc);

        gc.insets = noPadding;
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx++;
        controlPanel.add(portSpinner, gc);

        //////////////////Buttons Panel////////////////////////////////////
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        /////////////////Add subPanels to the Dialog/////////////////////////
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setPreferenceListener(PrefsListener pref){
        this.prefsListener = pref;
    }

    public void setDefaults(String user, String passwd, String port){

    }
}
