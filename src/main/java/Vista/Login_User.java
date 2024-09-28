/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import DP_User.displayPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import conexionMongoDB.*;
import clasesCredenciales.*;
import com.mongodb.MongoException;
import bitacoraLocal.*;
        
/**
 *
 * @author JB
 */
public class Login_User extends JFrame{
    
    private Funciones fun;
    private JLabel lb_titulo;
    private JLabel txt_login;
    private JLabel lb_usuario;
    private JTextField txt_usuario; 
    private JLabel lb_pass;
    private JTextField txt_pass; 
    private JButton bt_cancelar;
    private JButton bt_aceptar;
    private JPanel panel_principal;
    
    
    public Login_User() {
        
        fun = new Funciones();
        
        funcionesBitacora fb = new funcionesBitacora();
        
        panel_principal = new JPanel();
        lb_titulo = new JLabel("F L E M I N G");
        txt_login = new JLabel("USER LOGIN");
        lb_usuario = new JLabel("Usuario: ");
        lb_pass = new JLabel("Contraseña: ");
        txt_usuario = new JTextField(100);
        txt_pass = new JTextField(100);
        bt_cancelar = new JButton("Cancelar");
        bt_aceptar = new JButton("Aceptar");
        
        bt_aceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String user = txt_usuario.getText();
                String pass = txt_pass.getText();
                
                fb.guardarString(user, pass);
                
                if(revCredenciales(user, pass)){
                    
                    JFrame display_user = new JFrame();
                    //Display_User display_user = new Display_User();
                    display_user.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    display_user.setDefaultCloseOperation(EXIT_ON_CLOSE);

                    displayPanel panel = new displayPanel();
                    display_user.add(panel);
                    
                    display_user.setVisible(true);
                    setVisible(false);
                    
                    fun.Close();
                }else{
                    JOptionPane.showMessageDialog(null, "El usuario o la contraseña con incorrectos");
                    limpiarTf();
                }
            }
        });
        
        // Sirve para regresar a la vista del preambulo
        bt_cancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Preambulo preambulo = new Preambulo();
                preambulo.setVisible(true);
                preambulo.setSize(400, 400);
                preambulo.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        
        
        lb_titulo.setBounds(130, 40, 100, 25);
        txt_login.setBounds(127, 80, 100, 25);
        lb_usuario.setBounds(70, 120, 100, 25);
        txt_usuario.setBounds(180, 120, 100, 25);
        lb_pass.setBounds(70, 160, 100, 25);
        txt_pass.setBounds(180, 160, 100, 25);
        bt_cancelar.setBounds(60, 200, 100, 25);
        bt_aceptar.setBounds(180, 200, 100, 25);
        
        panel_principal.add(lb_titulo);
        panel_principal.add(txt_login);
        panel_principal.add(lb_usuario);
        panel_principal.add(txt_usuario);
        panel_principal.add(lb_pass);
        panel_principal.add(txt_pass);
        panel_principal.add(bt_cancelar);
        panel_principal.add(bt_aceptar);
        
        setContentPane(panel_principal);
        panel_principal.setLayout(null);
        
    }
    
    public void limpiarTf(){
        
        txt_usuario.setText("");
        txt_pass.setText("");
        
    }
    
    //metodo que revisa las credenciales de quien intenta acceder
    public boolean revCredenciales(String user, String pass){
        
        boolean llave = false;
        
        try{
        
            for(Usuario u : fun.dbExtract_Usuario()){

                if(u.getUsuario().equals(user) && u.getPass().equals(pass)){

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
