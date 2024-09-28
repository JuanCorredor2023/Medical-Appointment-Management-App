/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Calendar.calendarioDoctor;
import DP_User.perfilCompleto;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import conexionMongoDB.*;
import bitacoraLocal.*;
import clasesCredenciales.Doctor;
import java.awt.Font;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author JB
 */
public class Display_Doctor extends JPanel{
    
    private Funciones fun;
    
    private funcionesBitacora funb;
    
    public Display_Doctor() {
        
        setLayout(new BorderLayout());
        
        //MOSTRAR SU TARJETA AQUI PARA QUE LA PUEDA EDITAR A TRAVES DE LOS BOTONES DE ABAJO 
        
        fun = new Funciones();
        
        funb = new funcionesBitacora();
        
        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(new GridLayout(2, 1, 2, 2));
        
        //add(new JScrollPane(appointmentsArea), BorderLayout.CENTER);

        JButton revisarCalendario = new JButton("Revisar Calendario");
        
        revisarCalendario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                calendarioDoctor calendario = new calendarioDoctor(definirDoctor());
                
                JFrame frame = new JFrame();
                frame.add(calendario);
                frame.setVisible(true);
                frame.setSize(600, 600);
                
            }
        });
        
        panel_botones.add(revisarCalendario);
        
        add(panel_botones, BorderLayout.CENTER);
    }
    
    
    public Doctor definirDoctor(){
        
        
        for(Doctor d : fun.dbExtract_Doctor()){
            
            if(d.getName().equalsIgnoreCase(funb.obtenerBitacoraD().get(0)) && d.getCedula().equalsIgnoreCase(funb.obtenerBitacora().get(1))){
                
                Doctor doctorActual = new Doctor(d.getName(), d.getCity(), d.getCedula(), d.getAddress(), d.getImagePath(), d.getReviews(), d.getFechas_reservadas(), d.getEspecialidad());
                
                return doctorActual;
                
            }
            
        }
        
        return null;
        
    }
    
    
}
