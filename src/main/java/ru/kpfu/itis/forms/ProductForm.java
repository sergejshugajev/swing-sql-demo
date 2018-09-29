package ru.kpfu.itis.forms;

import ru.kpfu.itis.entities.Product;
import ru.kpfu.itis.repo.CategoryRepo;
import ru.kpfu.itis.repo.ProductRepo;
import ru.kpfu.itis.utilities.FormUtilities;
import ru.kpfu.itis.utilities.ManufacturerUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;


public class ProductForm {


    public JPanel getPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Product"));

        JLabel lName = new JLabel("name", JLabel.LEFT);
        JTextField name = new JTextField("", 45);

        panel.add(lName, FormUtilities.getLabelConstraints());
        panel.add(name, FormUtilities.getTextFieldConstraints());

        JLabel lPrice = new JLabel("price", JLabel.LEFT);
        JTextField price = new JTextField("", 32);

        panel.add(lPrice, FormUtilities.getLabelConstraints());
        panel.add(price, FormUtilities.getTextFieldConstraints());


        JLabel lWeight = new JLabel("weight", JLabel.LEFT);
        JTextField weight = new JTextField("", 10);

        panel.add(lWeight, FormUtilities.getLabelConstraints());
        panel.add(weight, FormUtilities.getTextFieldConstraints());

        JLabel lMan = new JLabel("manufacturer", JLabel.LEFT);
        JComboBox<String> man = new JComboBox<>(ManufacturerUtils.getManufacturers());
        man.setEditable(false);

        panel.add(lMan, FormUtilities.getLabelConstraints());
        panel.add(man, FormUtilities.getComboBoxConstraints());


        JLabel lCategory = new JLabel("category", JLabel.LEFT);
        String[] names = CategoryRepo.getAllCategoriesNames();
        JComboBox<String> categoryBox = new JComboBox<>(Arrays.copyOf(names, names.length - 1));

        panel.add(lCategory, FormUtilities.getLabelConstraints());
        panel.add(categoryBox, FormUtilities.getComboBoxConstraints());

        JLabel info = new JLabel("", JLabel.CENTER);
        GridBagConstraints spacer = new GridBagConstraints();
        spacer.fill = GridBagConstraints.BOTH;
        spacer.gridwidth = GridBagConstraints.REMAINDER;
        spacer.weighty = 1.0;
        panel.add(info, spacer);


        JButton add = new JButton("add");
        panel.add(add, FormUtilities.getTextFieldConstraints());

        add.addActionListener(e -> {
            String n = name.getText();
            String p = price.getText();
            String w = weight.getText();
            String m = (String) man.getSelectedItem();
            String c = (String) categoryBox.getSelectedItem();

            try {
                ProductRepo.add(new Product(
                                n,
                                Double.parseDouble(p),
                                Double.parseDouble(w),
                                ManufacturerUtils.getManufacturer(m),
                                CategoryRepo.getId(c)
                        )
                );
                name.setText("");
                price.setText("");
                weight.setText("");
                info.setText("Product has been successfully added!");

            } catch (Exception ex1) {
                ex1.printStackTrace();
                info.setText("Not valid input data");
            }

        });

        return panel;
    }
}