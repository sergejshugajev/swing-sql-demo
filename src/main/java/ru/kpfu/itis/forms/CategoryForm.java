package ru.kpfu.itis.forms;

import ru.kpfu.itis.entities.Category;
import ru.kpfu.itis.exceptions.NotValidCategoryException;
import ru.kpfu.itis.repo.CategoryRepo;
import ru.kpfu.itis.utilities.FormUtilities;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;


public class CategoryForm extends JFrame {

    public JPanel getPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel name = new JLabel("name", JLabel.LEFT);
        JTextField nameField = new JTextField("", 45);

        panel.add(name, FormUtilities.getLabelConstraints());
        panel.add(nameField, FormUtilities.getTextFieldConstraints());


        JLabel parent = new JLabel("parent", JLabel.LEFT);
        JComboBox<String> parentBox = new JComboBox<>(CategoryRepo.getAllCategoriesNames());
        parentBox.setSelectedItem("none");

        panel.add(parent, FormUtilities.getLabelConstraints());
        panel.add(parentBox, FormUtilities.getComboBoxConstraints());


        JLabel info = new JLabel("", JLabel.CENTER);

        GridBagConstraints spacer = new GridBagConstraints();
        spacer.fill = GridBagConstraints.BOTH;
        spacer.gridwidth = GridBagConstraints.REMAINDER;
        spacer.weighty = 1.0;
        panel.add(info, spacer);

        JButton add = new JButton("add");

        panel.add(add, FormUtilities.getTextFieldConstraints());

        add.addActionListener(e -> {

            String n = nameField.getText();
            Integer p;

            try {
                p = Objects.equals(parentBox.getSelectedItem(), "none") ?
                        null : CategoryRepo.getId((String) parentBox.getSelectedItem());

                CategoryRepo.add(new Category(n, p));

                info.setText("Category has been successfully added!");

                parentBox.removeAllItems();

                for (String item : CategoryRepo.getAllCategoriesNames()) {
                    parentBox.addItem(item);
                }
                nameField.setText("");

            } catch (SQLException | NotValidCategoryException ex) {
                info.setText("not valid input");
                ex.printStackTrace();
            }
        });

        panel.setBorder(new TitledBorder("Category"));

        return panel;
    }
}
