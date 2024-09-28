/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calendar;

/**
 *
 * @author JB
 */
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.*;
import clasesCredenciales.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import conexionMongoDB.*;
import javax.swing.border.EmptyBorder;
import bitacoraLocal.*;
import java.awt.GridLayout;
/**
 *
 * @author JB
 */
public class calendarioDoctor extends JPanel{
    
    private JPanel panel_botones;
    
    private JCalendar calendario;
    
    private int day;
    
    private int month;
    
    private int year;
    
    private Funciones fun;
    
    private funcionesBitacora funb;
    
    public calendarioDoctor(Doctor doctor){
    
        setLayout(new BorderLayout());

        fun = new Funciones();
        
        funb = new funcionesBitacora();
        
        calendario = new JCalendar();
        
        limites(calendario);
        
        JDayChooser chooser = calendario.getDayChooser();
    
        JPanel panel_dias = chooser.getDayPanel();
        
        calendario.addPropertyChangeListener("calendar", evt -> updateButtons(chooser, doctor));
    
        updateButtons(chooser, doctor);
    
        add(calendario, BorderLayout.CENTER);
        
    }
    
    
    public void limites(JCalendar calendario){
        
        Calendar inf_lim = Calendar.getInstance();
        
        Calendar sup_lim = (Calendar) inf_lim.clone();
        
        sup_lim.add(Calendar.MONTH, 3);
        
        calendario.setMinSelectableDate(inf_lim.getTime());
        
        calendario.setMaxSelectableDate(sup_lim.getTime());
    }
    
    private void updateButtons(JDayChooser chooser, Doctor doctor){
    
        JPanel panel_dias = chooser.getDayPanel();
        
        JButton[] botones = Arrays.stream(panel_dias.getComponents()).filter(component -> component instanceof JButton).map(component -> (JButton) component).toArray(JButton[]::new);
        
        Calendar calendarioActual = calendario.getCalendar();
        
        int mesActual = calendarioActual.get(Calendar.MONTH) + 1;
        
        int yearActual = calendarioActual.get(Calendar.YEAR);
        
        for(JButton boton : botones){
            
            String botonTexto = boton.getText();
            
            String fecha_str = yearActual + "-" + mesActual + "-" + botonTexto;
            
            try{
                
                int day_aux = Integer.parseInt(botonTexto);
                
                fechaDividida fecha = new fechaDividida(day_aux, mesActual, yearActual, fecha_str);
                
                Arrays.stream(boton.getActionListeners()).forEach(boton::removeActionListener);
                
                if(revisarRango(fecha)){
                    
                    for(fechaDividida fechaCita : dividirFecha(fechasDoctor())){
                        
                        if(fechaCita.equals(fecha)){
                            
                            boton.setBackground(Color.GREEN);
                        
                            boton.setForeground(Color.WHITE);

                            boton.setBorder(new EmptyBorder(5, 5, 5, 5));
                            
                        }
                    }
                    
                } else {
                    
                    boton.setBackground(null);
                    
                    boton.setForeground(Color.BLACK);
                    
                    boton.setBorder(UIManager.getBorder("Button.border"));
                    
                }
                
            }catch(NumberFormatException e){
                
                boton.setBackground(null);
                
                boton.setForeground(Color.BLACK);
                
                boton.setBorder(UIManager.getBorder("Button.border"));
                
            }
        
        }
        
    }
    
    public boolean revisarRango(fechaDividida fecha){
        
        Calendar cal = Calendar.getInstance();
        
        cal.add(Calendar.MONTH, 3);
        
        fechaDividida fechaLimite = new fechaDividida(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR), (cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)) + 1) + "/" + cal.get(Calendar.YEAR));
        
        Calendar fechaHoy = Calendar.getInstance();
        
        fechaHoy.set(fecha.getYear(), fecha.getMonth() - 1, fecha.getDay());
        
        return !fechaHoy.after(cal);
        
    }
    
    public ArrayList<Fecha> fechasDoctor(){
        
        ArrayList<Fecha> arr_Dfechas = new ArrayList<Fecha>();
        
        for(Doctor d : fun.dbExtract_Doctor()){
            
            if(d.getName().equals(funb.obtenerBitacoraD().get(0)) && d.getCedula().equals(funb.obtenerBitacoraD().get(1))){
                
                for(Fecha f : d.getFechas_reservadas()){
                    
                    arr_Dfechas.add(f);
                    
                }
                
                return arr_Dfechas;

            }
            
        }
        
        return null;
    }
    
    // Convierte cada fecha en fechaDividida
    public ArrayList<fechaDividida> dividirFecha(ArrayList<Fecha> arr_fechas){
        
        ArrayList<fechaDividida> arr_fechas_divididas = new ArrayList<fechaDividida>();
        
        for(Fecha f : arr_fechas){
            
            fechaDividida fd = new fechaDividida(separarDia(f.getFecha_str()), separarMes(f.getFecha_str()), separarYear(f.getFecha_str()), f.getFecha_str());
            
            if(fd != null){
                
                arr_fechas_divididas.add(fd);
            
            }

        }
        
        if(arr_fechas_divididas != null){
            
            return arr_fechas_divididas; 
        
        }
        
        return null;
    }
    
    public int separarDia(String fecha_str){
        
        LocalDate date = LocalDate.parse(fecha_str, DateTimeFormatter.ISO_DATE);
        
        int dia = date.getDayOfMonth();
        
        return (dia + 1);
        
    }
    
    public int separarMes(String fecha_str){
    
        LocalDate date = LocalDate.parse(fecha_str, DateTimeFormatter.ISO_DATE);
        
        int mes = date.getMonthValue();
        
        return mes;
    
    }
    
    public int separarYear(String fecha_str){
        
        LocalDate date = LocalDate.parse(fecha_str, DateTimeFormatter.ISO_DATE);
        
        int year_a = date.getYear();
        
        return year_a;
    }
    
    
    
}

