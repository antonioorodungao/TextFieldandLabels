package gui;

import controller.MessageServer;
import model.Message;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

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
    private ServerTreeCellEditor serverTreeCellEditor;

    private Set<Integer> selectedServers;

    private MessageServer messageServer;

    public MessagePanel(){

        selectedServers = new TreeSet<Integer>();

        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);

        serverTree = new JTree(createTree());
        treeCellRenderer = new ServerTreeCellRenderer();
        serverTreeCellEditor = new ServerTreeCellEditor();
        serverTree.setEditable(true);

        messageServer = new MessageServer();

        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(serverTreeCellEditor);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);


        serverTreeCellEditor.addCellEditorListener(new CellEditorListener() {
            public void editingStopped(ChangeEvent e) {
                System.out.println("Editing stopped..");
                ServerInfo info = (ServerInfo) serverTreeCellEditor.getCellEditorValue();

                int serverId = info.getId();
                if(info.isChecked()){
                    selectedServers.add(serverId);
                    System.out.println("ID added " + serverId);
                }else{
                    selectedServers.remove(serverId);
                    System.out.println("ID removed " + serverId);
                }

                messageServer.setSelectedServers(selectedServers);
                System.out.println("Info: " + info.getId() + " is checked: " + info.isChecked());
                System.out.println("Messages waiting: " + messageServer.getMessageCount());

                for(Message message: messageServer){
                    System.out.println(message.getTitle());
                }
            }

            public void editingCanceled(ChangeEvent e) {
                System.out.println("Editing Cancelled");
            }
        });



        //Will tell you if the node is selected or not
        //removing since we are not concerned anymore with selection.
//        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
//            public void valueChanged(TreeSelectionEvent e) {
//                DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)serverTree.getLastSelectedPathComponent();
//                Object userObject = dmt.getUserObject();
//                System.out.println(userObject);
//            }
//        });
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);

    }

    private DefaultMutableTreeNode createTree(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, selectedServers.contains(0)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, selectedServers.contains(1)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",3, selectedServers.contains(3)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh",4, selectedServers.contains(4)));

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}
