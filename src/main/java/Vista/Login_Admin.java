/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import clasesCredenciales.*;
import com.mongodb.MongoException;
import conexionMongoDB.*;
import java.util.List;
import DP_Admin.*;

/**
 *
 * @author JB
 */
public class Login_Admin extends JFrame{
    
    private Funciones fun;
    private JPanel panel;
    private JLabel lb_titulo;
    private JLabel lb_login;
    private JLabel lb_cod;
    private JTextField txt_cod;
    private JLabel lb_pass;
    private JTextField txt_pass;
    private JButton bt_cancelar;
    private JButton bt_aceptar;

    public Login_Admin() {
        
        fun = new Funciones();
        
        panel = new JPanel();
        lb_titulo = new JLabel("F L E M I N G");
        lb_login = new JLabel("ADMIN LOGIN");
        lb_cod = new JLabel("Codigo: ");
        txt_cod = new JTextField(20);
        lb_pass = new JLabel("Contraseña: ");
        txt_pass = new JTextField(20);
        bt_cancelar = new JButton("Cancelar");
        bt_aceptar = new JButton("Aceptar");
        
        bt_aceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Todo: verificar las credenciales
                String codigo = txt_cod.getText();
                String pass = txt_pass.getText();

                if(revCredenciales(codigo, pass)){
                    
                    displayPanelA dpa = new displayPanelA();
                    
                    JFrame display_admin = new JFrame();
                    
                    display_admin.add(dpa);
                    
                    display_admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    display_admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    display_admin.setVisible(true);
                    setVisible(false);
                    fun.Close();
                }else{
                    JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
                    limpiarTf();
                }
                
            }
        });
        
        bt_cancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Preambulo preambulo = new Preambulo();
                preambulo.setVisible(true);
                preambulo.setSize(400, 400);
                preambulo.setDefaultCloseOperation(EXIT_ON_CLOSE);
                fun.Close();
            }
        });
        
        lb_titulo.setBounds(130, 40, 100, 25);
        lb_login.setBounds(127, 80, 100, 25);
        lb_cod.setBounds(70, 120, 100, 25);
        txt_cod.setBounds(180, 120, 100, 25);
        lb_pass.setBounds(70, 160, 100, 25);
        txt_pass.setBounds(180, 160, 100, 25);
        bt_cancelar.setBounds(60, 200, 100, 25);
        bt_aceptar.setBounds(180, 200, 100, 25);
        
        panel.add(lb_titulo);
        panel.add(lb_login);
        panel.add(lb_cod);
        panel.add(txt_cod);
        panel.add(lb_pass);
        panel.add(txt_pass);
        panel.add(bt_cancelar);
        panel.add(bt_aceptar);
        
        setContentPane(panel);
        panel.setLayout(null);
    }
    
    public void limpiarTf(){
        txt_cod.setText("");
        txt_pass.setText("");
    }
    
    public boolean revCredenciales(String codigo, String pass){
        
        boolean llave = false;
        
        try{
            
            for(Admin a : fun.dbExtract_Admin()){
                
                if(a.getCodigo().equals(codigo) && a.getPass().equals(pass)){
                    
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
