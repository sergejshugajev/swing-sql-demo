package ru.kpfu.itis.forms;

import ru.kpfu.itis.utilities.FormUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabbedPane extends JFrame {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    public void createGUI(){

        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(400,500));
        frame.setSize(400,500);

        JPanel panel = new JPanel(new GridBagLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.add("products",new ProductForm().getPanel());
        tabbedPane.add("categories", new CategoryForm().getPanel());
        tabbedPane.add("product table", new ProductListForm().getPanel());

        GridBagConstraints tbpConstraint = new GridBagConstraints();
        tbpConstraint.fill = GridBagConstraints.BOTH;
        tbpConstraint.gridwidth = GridBagConstraints.REMAINDER;
        tbpConstraint.weighty = 2;


        JButton refresh = new JButton("refresh");

        refresh.addActionListener(e -> {
            int index = tabbedPane.getSelectedIndex();
            tabbedPane.removeAll();
            tabbedPane.add("products",new ProductForm().getPanel());
            tabbedPane.add("categories", new CategoryForm().getPanel());
            tabbedPane.add("product table", new ProductListForm().getPanel());

            panel.add(tabbedPane,tbpConstraint);
            panel.add(refresh, FormUtilities.getTextFieldConstraints());
            tabbedPane.setSelectedIndex(index);
        });

        panel.add(tabbedPane,tbpConstraint);
        panel.add(refresh, FormUtilities.getTextFieldConstraints());

        frame.add(panel);
        frame.setVisible(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
    }
}
