package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/14/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class Toolbar extends JToolBar implements ActionListener {
    private JButton saveButton;
    private JButton refreshButton;
    private ToolbarListener toolbarListener;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());
        setFloatable(true);
        saveButton = new JButton();
        saveButton.setIcon(createIcon("images/safe.gif"));
        saveButton.setToolTipText("Save");
        refreshButton = new JButton();
        refreshButton.setIcon(createIcon("images/refresh.gif"));
        refreshButton.setToolTipText("Refresh");

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);
        add(saveButton);
        add(refreshButton);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if(clicked == saveButton){
            toolbarListener.saveEventOccured();
        }else if(clicked == refreshButton){
            toolbarListener.refreshEventOccured();
        }

    }

    public void setToolbarListener(ToolbarListener sl){
        this.toolbarListener = sl;
    }

    private ImageIcon createIcon(String path){
        URL url = getClass().getResource(path);
        if(url == null ){
            System.err.println("Unable to load image" + path);
        }
        ImageIcon icon = new ImageIcon(url);
        return icon;

    }


}
