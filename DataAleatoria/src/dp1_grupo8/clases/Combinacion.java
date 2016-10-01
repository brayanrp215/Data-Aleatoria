/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp1_grupo8.clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Brayan Rodriguez
 */
public class Combinacion {
    public String origen ; 
    public String destino ; 
    public Combinacion(String origen, String destino){
        this.origen = origen ; 
        this.destino = destino; 
    }
    public Combinacion(String ruta){       
        String[] ss = ruta.split(" ");        
        String origen = ss[0];
        String destino = ss[1];
        this.origen = origen ; 
        this.destino = destino ; 
    }
    
     public static Vector<Combinacion> leerCombinaciones(String ruta) throws IOException {
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(EjecucionVNS.class.getName()).log(Level.SEVERE, null, ex);
        }        
        String r;  
        Vector<Combinacion> res = new Vector<>();

        while((r = in.readLine()) != null){        
            Combinacion x= new Combinacion(r);            
            res.add(x);
        }            
        return res;
    }
}
