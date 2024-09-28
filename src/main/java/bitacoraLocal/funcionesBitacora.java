/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bitacoraLocal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JB
 */
public class funcionesBitacora {
    
    public void guardarString(String user, String pass){
        
        File o = new File("C:\\Users\\JB\\Documents\\NetBeansProjects\\ProyectoFinal\\src\\main\\java\\bitacoraLocal\\credencialesUsuario.txt");
        
        try{
            
            FileWriter fw = new FileWriter(o, false);
            
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(user);
            
            bw.newLine();
            
            bw.write(pass);
            
            bw.close();
            
            fw.close();
            
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al almacenar en la bitacora");
        
        }
        
    }
    
    public void guardarStringDoctor(String user, String pass){
        
        File o = new File("C:\\Users\\JB\\Documents\\NetBeansProjects\\ProyectoFinal\\src\\main\\java\\bitacoraLocal\\credencialesDoctor.txt");
        
        try{
            
            FileWriter fw = new FileWriter(o, false);
            
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(user);
            
            bw.newLine();
            
            bw.write(pass);
            
            bw.close();
            
            fw.close();
            
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al almacenar en la bitacora");
        
        }
        
    }
    
    public ArrayList<String> obtenerBitacora(){
        
        ArrayList<String> arr_bitacora = new ArrayList<String>();
        
        File r = new File("C:\\Users\\JB\\Documents\\NetBeansProjects\\ProyectoFinal\\src\\main\\java\\bitacoraLocal\\credencialesUsuario.txt");
        
        String user;
        
        String pass;
        
        try{
        
            FileReader fr = new FileReader(r);
            
            BufferedReader br = new BufferedReader(fr);
            
            user = br.readLine();
            
            pass = br.readLine();
            
            //JOptionPane.showMessageDialog(null, user + " " + pass);
            
            arr_bitacora.add(user);
            
            arr_bitacora.add(pass);
            
            br.close();
            
            fr.close();
            
            
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al copiar bitacora");
        
        }
        
        if(!arr_bitacora.isEmpty()){
            
            return arr_bitacora;
        
        }
        
        return null;
        
    }
    
    public ArrayList<String> obtenerBitacoraD(){
        
        ArrayList<String> arr_bitacora = new ArrayList<String>();
        
        File r = new File("C:\\Users\\JB\\Documents\\NetBeansProjects\\ProyectoFinal\\src\\main\\java\\bitacoraLocal\\credencialesDoctor.txt");
        
        String name;
        
        String cedula;
        
        
        try{
        
            FileReader fr = new FileReader(r);
            
            BufferedReader br = new BufferedReader(fr);
            
            name = br.readLine();
            
            cedula = br.readLine();
            
            //JOptionPane.showMessageDialog(null, user + " " + pass);
            
            arr_bitacora.add(name);
            
            arr_bitacora.add(cedula);
            
            br.close();
            
            fr.close();
            
            
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al copiar bitacora");
        
        }
        
        if(!arr_bitacora.isEmpty()){
            
            return arr_bitacora;
        
        }
        
        return null;
        
    }
    
    
}
