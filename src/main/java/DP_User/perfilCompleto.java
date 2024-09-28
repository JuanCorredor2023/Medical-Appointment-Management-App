/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DP_User;

import clasesCredenciales.Doctor;
import clasesCredenciales.Review;
import conexionMongoDB.Funciones;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.bson.Document;
import Calendar.*;
/**
 *
 * @author JB
 */
public class perfilCompleto extends JFrame{

    private Funciones fun;
    
    private Doctor doctor;
    
    private JLabel lb_nombre;
    private JLabel especialidad;
    
    private ImageIcon icon;
    private JLabel imagen;
    
    private JLabel lb_city;
    private JLabel lb_address;
    //-------
    private JLabel lb_reviews;
    private JTextArea ta_reviews;
    
    private JLabel lb_agregarReview;
    private JTextArea ta_agregarReview;
    private JButton bt_agregarReview;
    
    private JPanel panel;
    private JPanel panel_north;
    private JPanel panel_south;
    private JPanel panel_imagen;
    
    private JScrollPane sc_taReviews;
    private JScrollPane sc_taAgReviews;
    
    private JButton bt_calendario;
    
    public perfilCompleto(Doctor doctor) {
        
        this.doctor = doctor;
        
        panel = new JPanel();
        panel.setLayout(null);
        
        fun = new Funciones();
        
        lb_nombre = new JLabel(getDoctor().getName());
        lb_nombre.setBounds(20, 20, 200, 25);
        
        imagen = new JLabel();
        escalarImagen(getDoctor().getImagePath());
        imagen.setBounds(20, 50, 200, 200);
        
        lb_city = new JLabel(getDoctor().getCity());
        lb_city.setBounds(20, 260, 200, 25);
        
        lb_address = new JLabel(getDoctor().getAddress());
        lb_address.setBounds(20, 290, 200, 25); 
        
        lb_reviews = new JLabel("Reviews:");
        lb_reviews.setBounds(20, 320, 200, 25);
        
        ta_reviews = new JTextArea();
        ta_reviews = new JTextArea("\n");
        ta_reviews.setEditable(false);
        llenarReviews(getDoctor().getReviews(), ta_reviews);
        
        sc_taReviews = new JScrollPane(ta_reviews);
        sc_taReviews.setBounds(20, 350, 500, 100);
        
        
        lb_agregarReview = new JLabel("Agregar review: ");
        lb_agregarReview.setBounds(20, 460, 200, 25);
        
        ta_agregarReview = new JTextArea();
        sc_taAgReviews = new JScrollPane(ta_agregarReview);
        sc_taAgReviews.setBounds(20, 490, 300, 100);
        
        bt_agregarReview = new JButton("Subir");
        bt_agregarReview.setBounds(20, 600, 100, 25);
        
        bt_calendario = new JButton("Abrir Calendario");
        bt_calendario.setBounds(20, 650, 100, 25);
        
        bt_calendario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Calendario calendario = new Calendario(doctor);
                
                JFrame frame = new JFrame();
                frame.add(calendario);
                frame.setVisible(true);
                frame.setSize(600, 600);
            }
        });
        
        
        bt_agregarReview.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String body = ta_agregarReview.getText();
                
                fun.updateOne_Doctor(getDoctor().getName(), getDoctor().getCedula(), "User Actual", body);
                
                JOptionPane.showMessageDialog(null, "La review se agrego correctamente");
                
                limpiar();
            }
        });
        
        agregarPanel();
        
        setContentPane(panel);
    }
    
    public void escalarImagen(String imagePath){
        try{
            BufferedImage original = ImageIO.read(new File(imagePath));
            Image escalada = original.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(escalada));
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al escalar imagen " + e);
        }
    }
    
    public void limpiar(){
        
        ta_agregarReview.setText("");
        
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void agregarPanel(){
        
        panel.add(lb_nombre);
        
        panel.add(imagen);
        
        panel.add(lb_city);
        
        panel.add(lb_address);
     
        panel.add(lb_reviews);

        panel.add(sc_taReviews);

        panel.add(lb_agregarReview);

        panel.add(sc_taAgReviews);

        panel.add(bt_agregarReview);
        
        panel.add(bt_calendario);
    }
    
    public void llenarReviews(ArrayList<Review> arr_reviews, JTextArea ta_reviews){
        
        for(Review review : arr_reviews){
            
            ta_reviews.append(" " + review.getAuthor()+": " + "\n");
            
            ta_reviews.append(" " + review.getBody());
            
            ta_reviews.append("\n\n");
            
        }
        
    }
    
    
}
