package gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Copyrights 2002-2011 Webb Fontaine
 * Developer: Antonio Oro Dungao
 * Date: 10/18/13
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 */

class ServerInfo{
    private String name;
    private int id;
    private boolean checked;

    public ServerInfo(String name, int id, boolean checked){
        this.name = name;
        this.id = id;
        this.checked = checked;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return name;
    }

    public boolean isChecked(){
     return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }
}
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;

    public MessagePanel(){

        serverTree = new JTree(createTree());
        treeCellRenderer = new ServerTreeCellRenderer();



        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)serverTree.getLastSelectedPathComponent();
                Object userObject = dmt.getUserObject();

                System.out.println(userObject);

            }
        });
        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);

    }

    private DefaultMutableTreeNode createTree(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");



        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, true));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, false));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, true));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",3, false));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh",4, true));

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}
