package udemy.textfieldlabels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/14/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel(){

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("Ok");
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel();

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        maleRadio.setSelected(true);

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        //set Mnemonic
        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        //Setup ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxField.setEnabled(isTicked);
                taxLabel.setEnabled(isTicked);
            }
        });

        //Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        Border listBorder = BorderFactory.createEtchedBorder();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 10 64"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setBorder(listBorder);
        ageList.setModel(ageModel);

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        //set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Employed");
        empModel.addElement("Self-employed");
        empModel.addElement("Unemployed");

        empCombo.setModel(empModel);

        layoutComponents();

        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                String taxID = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();

                String gender = genderGroup.getSelection().getActionCommand();
                formListener.formEventOccurred(new FormEvent(e,name, occupation, ageCat.toString(), taxID, usCitizen, gender));
            }
        });
    }

    public void setFormListener(FormListener listener){
        this.formListener = listener;

    }

    public void layoutComponents(){

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //FIRST ROW///////////////////////////////////////
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nameField, gc);
        //SECOND ROW//////////////////////////////////////////////////
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        //gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(occupationField, gc);

        //THIRD ROW//////////////////////////////////////////////////
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);


        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        /////////////////////////////////////////////////////////////
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Status: "), gc);


        gc.gridx = 1;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        //////////////////////////////////////////////////////////////
         gc.gridy++;

         gc.gridx = 0;
         gc.weightx = 1;
         gc.anchor = GridBagConstraints.FIRST_LINE_END;
         add(new JLabel("US Citizen: "), gc);


         gc.gridx = 1;
         gc.weightx = 1;
         gc.anchor = GridBagConstraints.FIRST_LINE_START;
         add(citizenCheck, gc);

        ///////////////////////////////////////////////////////////////
         gc.gridy++;

         gc.gridx = 0;
         gc.weightx = 1;
         gc.anchor = GridBagConstraints.FIRST_LINE_END;
         add(new JLabel("Tax ID: "), gc);

         gc.gridx = 1;
         gc.weightx = 1;
         gc.anchor = GridBagConstraints.FIRST_LINE_START;
         add(taxField, gc);

        ///////////////////////////////////////////////////////////////
        //Radio Button
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Gender"), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadio, gc);

        /////////////////////////////////////////////////////////////
        //Radio Button
        gc.gridy++;
//
//        gc.gridx = 0;
//        gc.weightx = 1;
//        gc.weighty = 0.2;
//        gc.fill = GridBagConstraints.NONE;
//        gc.anchor = GridBagConstraints.FIRST_LINE_END;
//        gc.insets = new Insets(0,0,0,5);
//        add(new JLabel("Gender"), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

       ///////////////////////////////////////////////////
        gc.gridy++;
        //gc.weightx = 1;
        //gc.weighty = 0.1;
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);


    }

}

class AgeCategory{
    private int id;
    private String text;
    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    public String toString(){
        return  text;
    }

    public int getId() {
        return id;
    }
}
