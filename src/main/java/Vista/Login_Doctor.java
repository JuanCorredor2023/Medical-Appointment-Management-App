/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import com.mongodb.MongoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import clasesCredenciales.*;
import conexionMongoDB.*;
import bitacoraLocal.*;

/**
 *
 * @author JB
 */
public class Login_Doctor extends JFrame{
    
    private JLabel lb_titulo;
    private JLabel txt_login;
    private JLabel lb_usuario;
    private JTextField txt_usuario; 
    //private JLabel lb_pass;
    private JLabel lb_cedula;
    private JTextField txt_cedula;
    //private JTextField txt_pass; 
    private JButton bt_cancelar;
    private JButton bt_aceptar;
    private JPanel panel_principal;
    
    private Funciones fun;
    
    private funcionesBitacora fb;
    
    public Login_Doctor() {
        
        fb = new funcionesBitacora();
        
        fun = new Funciones();
        
        panel_principal = new JPanel();
        lb_titulo = new JLabel("F L E M I N G");
        txt_login = new JLabel("DOCTOR LOGIN");
        lb_usuario = new JLabel("Nombre : ");
        //lb_pass = new JLabel("Contraseña: ");
        lb_cedula = new JLabel("Cedula:");
        
        txt_usuario = new JTextField(100);
        //txt_pass = new JTextField(100);
        txt_cedula = new JTextField(100);
        bt_cancelar = new JButton("Cancelar");
        bt_aceptar = new JButton("Aceptar");
        
        lb_titulo.setBounds(130, 40, 100, 25);
        txt_login.setBounds(120, 80, 100, 25);
        lb_usuario.setBounds(70, 120, 100, 25);
        txt_usuario.setBounds(200, 120, 100, 25);
        //lb_pass.setBounds(70, 160, 100, 25);
        //txt_pass.setBounds(200, 160, 100, 25);
        lb_cedula.setBounds(70, 170, 150, 25);
        txt_cedula.setBounds(200, 170, 100, 25);
        bt_cancelar.setBounds(60, 250, 100, 25);
        bt_aceptar.setBounds(180, 250, 100, 25);
        
        bt_aceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String name = txt_usuario.getText();
                
                String cedula = txt_cedula.getText();
                
                fb.guardarStringDoctor(name, cedula);
                
                if(revCredenciales(name, cedula)){
                    
                    JFrame display_doc = new JFrame();
                    
                    display_doc.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    display_doc.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    
                    Display_Doctor panel = new Display_Doctor();

                    display_doc.add(panel);
                    
                    display_doc.setVisible(true);

                    setVisible(false);

                }else{

                    JOptionPane.showMessageDialog(null, "El nombre o la cedula son incorrectos.");

                    limpiarTf();
                }
                
            }
        });
        
        // Sirve para regresar a la vista del preambulo
        bt_cancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Preambulo preambulo = new Preambulo();
                preambulo.setVisible(true);
                preambulo.setSize(400, 400);
                preambulo.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        
        panel_principal.add(lb_titulo);
        panel_principal.add(txt_login);
        panel_principal.add(lb_usuario);
        panel_principal.add(txt_usuario);
        //panel_principal.add(lb_pass);
        //panel_principal.add(txt_pass);
        panel_principal.add(lb_cedula);
        panel_principal.add(txt_cedula);
        panel_principal.add(bt_cancelar);
        panel_principal.add(bt_aceptar);
        
        setContentPane(panel_principal);
        panel_principal.setLayout(null);
        
    }
    
    public void limpiarTf(){
        txt_usuario.setText("");
        txt_cedula.setText("");
    }

    public boolean revCredenciales(String name, String cedula){
        
        boolean llave = false;
        
        try{
            
            for(Doctor d : fun.dbExtract_Doctor()){
                
                if(d.getName().equals(name) && d.getCedula().equals(cedula)){
                    
                    llave = true;
                    break;
                    
                }
                
            }
            
        }catch(MongoException e){
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        
        return llave;
    
    }
    
}

