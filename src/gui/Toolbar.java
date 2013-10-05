package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/14/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Toolbar extends JPanel implements ActionListener {
    private JButton helloButton;
    private JButton goodbyeButton;
    private StringListener stringListener;

    public Toolbar(){
        helloButton = new JButton("Hello");
        goodbyeButton = new JButton("Goodbye");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);
        add(helloButton);
        add(goodbyeButton);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("A button has been clicked");
        JButton clicked = (JButton)e.getSource();

        if(clicked == helloButton){
            stringListener.textEmitted("Hello button is pressed\n");
        }else if(clicked == goodbyeButton){
            stringListener.textEmitted("Goodbye button is pressed\n");
        }

    }

    public void setStringListener(StringListener sl){
        this.stringListener = sl;
    }


}
