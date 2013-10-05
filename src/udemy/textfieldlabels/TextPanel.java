package udemy.textfieldlabels;

import javax.swing.*;
import java.awt.*;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 9/13/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class TextPanel extends JPanel {

    private JTextArea textArea;
    public TextPanel(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);

    }

    public void appendText(String text){
        textArea.append(text);
    }

}
