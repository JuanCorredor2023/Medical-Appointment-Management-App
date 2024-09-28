/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesCredenciales;

import java.util.ArrayList;

/**
 *
 * @author JB
 */
public class Doctor {
    
    private String name;
    private String city;
    private String cedula;
    private String address;
    private String imagePath;
    private ArrayList<Review> reviews;
    private ArrayList<Fecha> fechas_reservadas;
    private String especialidad;

    public Doctor(String name, String city, String cedula, String address, String imagePath, ArrayList<Review> reviews, ArrayList<Fecha> fechas_reservadas, String especialidad) {
        this.name = name;
        this.city = city;
        this.cedula = cedula;
        this.address = address;
        this.imagePath = imagePath;
        this.reviews = reviews;
        this.fechas_reservadas = fechas_reservadas;
        this.especialidad = especialidad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Fecha> getFechas_reservadas() {
        return fechas_reservadas;
    }

    public void setFechas_reservadas(ArrayList<Fecha> fechas_reservadas) {
        this.fechas_reservadas = fechas_reservadas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    

    

}
