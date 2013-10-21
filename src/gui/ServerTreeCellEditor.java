package gui;

import javax.swing.*;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.util.EventObject;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/19/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */
public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

    private ServerTreeCellRenderer renderer;

    public ServerTreeCellEditor(){
        renderer = new ServerTreeCellRenderer();

    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        JCheckBox checkBox = (JCheckBox) renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getCellEditorValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return super.isCellEditable(e);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
