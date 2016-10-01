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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public class Ciudad {



    public String nombre;
    public String codigo;
    public Integer continente;
    public Integer horaUTC=0;
    
    public Ciudad(){
    
    }    
    public Ciudad(String ciudad, int cnt){
        ciudad = ciudad.replace('\t', ' ');
        ciudad = ciudad.replace("  "," ");
        //System.out.println(ciudad);
        String[] ss = ciudad.split(" ");        
        codigo = ss[1];
        //System.out.println(ss[1]);
        nombre = ss[2];
        continente= cnt;
        horaUTC=0;
    }
    
    public String ToString(){
        return nombre+codigo;        
    }
    
    
    public static Vector<Ciudad> leerUTC(Vector<Ciudad> ciudades, String horas )throws IOException  {
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(horas));
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(EjecucionVNS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String r;
        int cnt=0;
        
        Vector<Ciudad> res = new Vector<>();

        while((r = in.readLine()) != null){
            String[] sr = r.split(" ");
            System.out.println(sr[0]+" "+sr[1]);
            for(Ciudad ciudad: ciudades){
                if(sr[0].compareTo(ciudad.codigo)==0){
                    ciudad.horaUTC = Integer.parseInt(sr[1]);
                    res.add(ciudad);
                }
            }
        }
        
        return res;        
    }
    
    public static Vector<Ciudad> leerCiudades(String ruta) throws IOException {
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(EjecucionVNS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String r;
        in.readLine();
        int cnt=0;
        
        Vector<Ciudad> res = new Vector<>();

        while((r = in.readLine()) != null){

            if( r.charAt(0)=='\t' ){
                cnt++;
                continue;
            }
            
            Ciudad x= new Ciudad(r,cnt);
            
            res.add( x );
        }
        
        return res;
    }
    
}
