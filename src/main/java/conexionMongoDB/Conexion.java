/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionMongoDB;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author JB
 */
public class Conexion {
    
    public MongoClient crearConexion(){

        MongoClient mongo = null;
        
        String server = "localhost";
        Integer port = 27017;
        
        try{
            
            mongo = new MongoClient(server, port);
            
            List<String> db_nombres = mongo.getDatabaseNames();
            
            //db = mongo.getDatabase("ProyectoFinal");
            
            JOptionPane.showMessageDialog(null, "Se conecto correctamente con el mongoClient");
            
        }catch(MongoException e){
            
            JOptionPane.showMessageDialog(null, "Error em la conexion:" + "\n" + e);
        
        }
        
        return mongo;
        
    }
    
}
