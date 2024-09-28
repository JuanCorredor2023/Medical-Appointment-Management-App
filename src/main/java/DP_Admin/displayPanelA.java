/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DP_Admin;

import clasesCredenciales.Doctor;
import conexionMongoDB.Funciones;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author JB
 */
public class displayPanelA extends JPanel {
    private Funciones fun;
    private JTextField filterTextField;
    private JComboBox<String> filterOptions;
    private JPanel cardsPanel;
    private JPanel cardsPanelDisplay;
    // Eliminar
    //private funcionesBitacora fb;

    public displayPanelA() {
        super(new BorderLayout());
        fun = new Funciones();
        //fb = new funcionesBitacora();
        initDisplay();
    }

    private void initDisplay() {
        
        
        JPanel leftPanel = new JPanel(new GridLayout(2, 1, 1, 1));
        // Filter setup
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterTextField = new JTextField(20);
        filterOptions = new JComboBox<>(new String[]{"Name", "City"});
        JButton filterButton = new JButton("Filtrar");
        
        // ADD CARDS TO THE PANEL BUT some space that shouldnt be 
        cardsPanelDisplay = new JPanel(new GridLayout(10,2,4,4));
        for (Doctor doctor : fun.dbExtract_Doctor()){
            cardsPanelDisplay.add(createDoctorCard(doctor));
        }
        
        filterButton.addActionListener(this::filterDoctors);
        filterPanel.add(new JLabel("Filtrar por:"));
        filterPanel.add(filterOptions);
        filterPanel.add(filterTextField);
        filterPanel.add(filterButton);
        
        this.add(filterPanel, BorderLayout.NORTH);
        
        //leftPanel.add(filterPanel);
        cardsPanelDisplay.setBackground(Color.GRAY);
        leftPanel.add(cardsPanelDisplay);

        
        this.add(new JScrollPane(leftPanel), BorderLayout.WEST);
        
        
        JLabel space = new JLabel("   ");
        this.add(space, BorderLayout.SOUTH);
        JLabel space2 = new JLabel("   ");
        this.add(space, BorderLayout.EAST);

        // Cards display
        cardsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 5;
        gbc.weighty = 5;
        cardsPanel.add(new JPanel(), gbc); // Dummy panel for spacing
        this.add(new JScrollPane(cardsPanel), BorderLayout.CENTER);
    }
    

    public void filterDoctors(ActionEvent e) {
        String filter = filterTextField.getText();
        String filterType = (String) filterOptions.getSelectedItem();
        cardsPanel.removeAll();
        for (Doctor doctor : fun.dbExtract_Doctor()) {
            if (filterType.equals("Name") && doctor.getName().contains(filter) ||
                filterType.equals("City") && doctor.getCity().contains(filter) || 
                filterType.equals("Especialidad") && doctor.getEspecialidad().contains(filter)
                ) {
                JPanel card = createDoctorCard(doctor);
                cardsPanel.add(card);
            }
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }


    private JPanel createDoctorCard(Doctor doctor) {
        
        JPanel card = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(doctor.getName(),SwingConstants.CENTER); 
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel cityLabel = new JLabel("     City: " + doctor.getCity());
        JLabel addressLabel = new JLabel("     Address: " + doctor.getAddress());
        ImageIcon icon = new ImageIcon(doctor.getImagePath());
        JLabel imageLabel = new JLabel(icon);
        JButton eliminar = new JButton("Eliminar");
        
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                fun.deleteOne_Doctor(doctor.getName(), doctor.getCedula());
                
                JOptionPane.showMessageDialog(null, "Perfil de Doctor eliminado.");
            }
        });

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(cityLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(eliminar);

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        return card;
    }
}   
    

