package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/19/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class ServerTreeCellRenderer implements TreeCellRenderer {

    private JCheckBox leafRenderer;
    private DefaultTreeCellRenderer nonLeafRenderer;
    private Color textForegound;
    private Color textBackground;
    private Color selectionForeground;
    private Color selectionBackground;

    public ServerTreeCellRenderer(){
        leafRenderer = new JCheckBox();

        nonLeafRenderer = new DefaultTreeCellRenderer();

        nonLeafRenderer.setLeafIcon(Util.createIcon("/gui/images/calc.gif"));
        nonLeafRenderer.setOpenIcon(Util.createIcon("/gui/images/current.gif"));
        nonLeafRenderer.setClosedIcon(Util.createIcon("/gui/images/note.gif"));

        textForegound = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");



    }
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        if(leaf){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            ServerInfo nodeInfo = (ServerInfo)node.getUserObject();

            if(selected){
                leafRenderer.setForeground(selectionForeground);
                leafRenderer.setBackground(selectionBackground);
            }else{
                leafRenderer.setForeground(textForegound);
                leafRenderer.setBackground(textBackground);
            }


            leafRenderer.setText(nodeInfo.toString());
            leafRenderer.setSelected(nodeInfo.isChecked());
            return leafRenderer;

        }else{
            return nonLeafRenderer.getTreeCellRendererComponent(tree,value, selected,expanded,leaf,row,hasFocus);

        }
    }
}
