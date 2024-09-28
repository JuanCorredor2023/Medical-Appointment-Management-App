/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionMongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import clasesCredenciales.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Updates.pull;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JB
 */

public class Funciones {
    
    Conexion con = new Conexion();
    
    MongoClient mongo = con.crearConexion();
    
    MongoDatabase db = mongo.getDatabase("ProyectoFinal");
    
    
    public void Close(){
        if(mongo != null){
            mongo.close();
        }
    }
    
    public ArrayList<Admin> dbExtract_Admin(){
        
        try{
            ArrayList<Admin> admins = new ArrayList<Admin>();

            MongoCollection<Document> collection = db.getCollection("Admin");

            FindIterable<Document> documents = collection.find();

            for(Document doc : documents){

                Admin a = new Admin(doc.getString("codigo"), doc.getString("pass"));

                admins.add(a);

            }

            return admins;
            
        } catch(MongoException e){
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return null;
    }
    
    // arreglar para aceptar las reviews
    public ArrayList<Doctor> dbExtract_Doctor(){
        
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        FindIterable<Document> documents = collection.find();
        
        for(Document doc : documents){
            
            ArrayList<Review> reviews = new ArrayList<Review>();
            
            ArrayList<Fecha> fechas = new ArrayList<Fecha>();
            
            List<Document> arr_reviews = (List<Document>) doc.get("reviews"); 
            
            List<Document> arr_fechas = (List<Document>) doc.get("fecha_reservada");
            
            if(arr_reviews != null){
                
                for(Document review : arr_reviews){
                    
                    Review r = new Review(review.getString("author"), review.getString("body"));
                    
                    reviews.add(r);
                    
                }
                
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Date defaultDate = new Date(0);
            
            if(arr_fechas != null){
            
                for(Document fecha : arr_fechas){
                    
                    Date date = fecha.getDate("fecha");
                    
                    if(date == null){
                        
                        date = defaultDate;
                        
                    }
                    
                    Fecha f = new Fecha(sdf.format(fecha.getDate("fecha")));
                    
                    fechas.add(f);
                
                }
                
            }
            
            Doctor d = new Doctor(doc.getString("name"), doc.getString("city"), doc.getString("cedula"), doc.getString("address"), doc.getString("imagePath"), reviews, fechas, doc.getString("especialidad"));
            
            System.out.println("ATENCION----------------------------------------------");
            System.out.println(" PATH DE FOTO: " + d.getImagePath());
            System.out.println("------------------------------------------------------");
            
            doctors.add(d);
            
        }
        
        return doctors;
        
    }
    
    public ArrayList<Usuario> dbExtract_Usuario(){
        
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        
        MongoCollection<Document> collection = db.getCollection("Usuario");
              
        FindIterable<Document> documents = collection.find();
        
        for(Document doc : documents){
            
            ArrayList<Fecha> fechas = new ArrayList<Fecha>();
            
            List<Document> arr_fechas = (List<Document>) doc.get("fecha_cita");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            if(arr_fechas != null){
                
                for(Document fecha : arr_fechas){
                    
                    Fecha f = new Fecha(sdf.format(fecha.getDate("fecha")));
                    
                    fechas.add(f);
                    
                }
                
            }
            
            Usuario u = new Usuario(doc.getString("user"), doc.getString("pass"), doc.getString("name"), doc.getString("correo"), doc.getInteger("edad"), doc.getString("city"), fechas);
            
            users.add(u);
            
        }
        
        return users;
        
    }
    
    public void addOne_Usuario(String user, String pass, String correo, int edad, String city, String name){
        
        MongoCollection<Document> collection = db.getCollection("Usuario");
        
        Document doc = new Document("user", user).append("pass", pass).append("correo", correo).append("edad", edad).append("city", city).append("name", name).append("fecha_cita", new Document());
        
        collection.insertOne(doc);
        
    }
    
    public void addOne_Admin(int cod, String pass){
        
        MongoCollection<Document> collection = db.getCollection("Admin");
        
        Document doc = new Document("codigo", cod).append("pass", pass);
        
        collection.insertOne(doc);
        
    }
    
    public void addOne_Doctor(String name, String city, String cedula, String address, String imagePath, ArrayList<Review> reviews, ArrayList<Fecha> fechas_reservadas, String especialidad){
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        /*
        List<Document> arr_reviews = new ArrayList<>();
        
        for(Review review : reviews){
            
            arr_reviews.add(new Document("author", review.getAuthor()).append("body", review.getBody()));
            
        }
        
        List<Document> arr_fechas_reservadas = new ArrayList<>();
        
        for(Fecha fecha : fechas_reservadas){
            
            arr_fechas_reservadas.add(new Document("fecha", fecha.getFecha_str()));
            
        }
        */
        Document doc = new Document("name", name).append("city", city).append("cedula", cedula).append("address", address).append("imagePath", imagePath).append("reviews", new Document()).append("fecha_reservada", new Document()).append("especialidad", especialidad);
        
        collection.insertOne(doc);
        
    }
    
    public void deleteOne_Usuario(String user, String pass, String correo, int edad, String city, String name){
        
        MongoCollection<Document> collection = db.getCollection("Usuario");
        
        Document doc = new Document("user", user).append("pass", pass).append("correo", correo).append("edad", edad).append("city", city).append("name", name);
        
        collection.deleteOne(doc);
        
    }
    
    public void deleteOne_Admin(int cod, String pass){
        
        MongoCollection<Document> collection = db.getCollection("Admin");
        
        Document doc = new Document("codigo", cod).append("pass", pass);
        
        collection.deleteOne(doc);
        
    }
    
    public void deleteOne_Doctor(String name, String cedula){
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        Document doc = new Document("name", name).append("cedula", cedula);
        
        collection.deleteOne(doc);
        
    }
    
    public void updateOne_Doctor(String name, String cedula, String author, String body){
    
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        Document query = new Document("name", name).append("cedula", cedula);
        
        Document review = new Document("author", author).append("body", body);
        
        Document update = new Document("$push", new Document("reviews", review));
        
        collection.updateOne(query, update);
    }
    
    public void updateOne_DoctorF(String name, String cedula, String fecha){
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        Document query = new Document("name", name).append("cedula", cedula);
        
        fecha = fecha + "T00:00:00.000Z";
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        
        try {
            
            Instant instant = Instant.parse(fecha);
            
            Date ISOdate = Date.from(instant);
            
            Document nuevaFecha = new Document("fecha", ISOdate);
            
            Document update = new Document("$push", new Document("fecha_reservada", nuevaFecha));
            
            collection.updateOne(query, update);
            
            
        } catch (DateTimeParseException e) {
            
            JOptionPane.showMessageDialog(null, "Error " + e);
            
            e.printStackTrace();

        }
        
    }
    
    public void upDateOne_UserF(String user, String pass, String fecha ){
        
        MongoCollection<Document> collection = db.getCollection("Usuario");
        
        Document query = new Document("user", user).append("pass", pass);
        
        fecha = fecha + "T00:00:00.000Z";
        
        try {
            
            Instant instant = Instant.parse(fecha);
            
            Date ISOdate = Date.from(instant);
            
            Document nuevaFecha = new Document("fecha", ISOdate);
            
            Document update = new Document("$push", new Document("fecha_cita", nuevaFecha));
            
            collection.updateOne(query, update);
            
            
        } catch (DateTimeParseException e) {
            
            JOptionPane.showMessageDialog(null, "Error " + e);
            
            e.printStackTrace();

        }
    
    }
    
    public void pullFecha_Doctor(String name, String cedula, String fecha){
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        fecha = fecha + "T00:00:00.000Z";
        
        Document filter = new Document(name, cedula);
        
        try{
            
            Instant instant = Instant.parse(fecha);
            
            Date ISOdate = Date.from(instant);
            
            Document remover = new Document("fecha", ISOdate);
            
            collection.updateOne(filter, pull("fecha_reservada", remover));
            
        }catch(DateTimeParseException e){
            
            JOptionPane.showMessageDialog(null, "Error " + e);
            
            e.printStackTrace();
        
        }
    }
    
    public void pullFecha_User(String user, String pass, String fecha){
        
        MongoCollection<Document> collection = db.getCollection("Usuario");
        
        fecha = fecha + "T00:00:00.000Z";
        
        Document filter = new Document("user", user);
        
        try{
            
            Instant instant = Instant.parse(fecha);
            
            Date ISOdate = Date.from(instant);
            
            Document remover = new Document("fecha", ISOdate);
            
            collection.updateOne(filter, pull("fecha_cita", remover));
            
        }catch(DateTimeParseException e){
            
            JOptionPane.showMessageDialog(null, "Error " + e);
            
            e.printStackTrace();
        
        }
    }
    
    public void updateOne_DoctorData(String name, String cedula, String newName, String newAddress, String newCity){
        
        MongoCollection<Document> collection = db.getCollection("Doctor");
        
        Document query = new Document("name", name).append("cedula", cedula);
        
        Document nuevo = new Document("name", newName).append("city", newCity).append("address", newAddress);
        
        Document update = new Document("$set", nuevo);
        
        collection.updateOne(query, update);
        
    }
    
}
