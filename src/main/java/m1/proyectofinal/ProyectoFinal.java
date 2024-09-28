/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package m1.proyectofinal;

import conexionMongoDB.*;
import Vista.*;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author JB
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        conexion.crearConexion();
        
        Preambulo preambulo = new Preambulo();
        preambulo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        preambulo.setDefaultCloseOperation(EXIT_ON_CLOSE);
        preambulo.setVisible(true);
        
    }
}
