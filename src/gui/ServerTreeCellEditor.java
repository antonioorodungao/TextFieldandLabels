package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/19/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

    //will give checkbox formatted with the color
    private ServerTreeCellRenderer renderer;
    private JCheckBox checkBox;
    private ServerInfo serverInfo;

    public ServerTreeCellEditor() {
        renderer = new ServerTreeCellRenderer();
    }


    //will return an editable component. has to fire editing start method will call getCellEditor Value()
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        Component component = renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);

        if (leaf) {
            checkBox = (JCheckBox) component;

            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;

            serverInfo = (ServerInfo)treeNode.getUserObject();


            ItemListener itemListener = new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    fireEditingStopped();
                    System.out.println("Item state changed...");
                    checkBox.removeItemListener(this);
                }
            };
            checkBox.addItemListener(itemListener);
        }
        return component;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //will only be called when FireEditingStopped is called.
    public Object getCellEditorValue() {
        serverInfo.setChecked(checkBox.isSelected());
        return serverInfo;
    }

    @Override
    //First called when clicked a Note. Return true  will call getTreeCellEditorComponent()
    public boolean isCellEditable(EventObject event) {

        System.out.println("inside isCellEditable...");

        if (!(event instanceof MouseEvent)) return false;

        MouseEvent mouseEvent = (MouseEvent) event;
        JTree tree = (JTree) mouseEvent.getSource();

        TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());

        if (path == null) return false;
        Object lastComponent = path.getLastPathComponent();

        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) lastComponent;

        return treeNode.isLeaf();
    }
}
