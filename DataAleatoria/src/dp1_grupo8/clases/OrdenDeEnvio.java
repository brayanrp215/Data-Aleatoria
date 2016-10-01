/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp1_grupo8.clases;


import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Brayan
 */
public class OrdenDeEnvio {
    public String origen ;
    public String destino ;
    public Date fecha_registro ; 
    
    //System.out.println(r);
    public OrdenDeEnvio(String linea_leida){
        String[] ss = linea_leida.split("-");
        
        origen = ss[0];
        destino = ss[1];
        
        // BIKF-EBCI-26-09-2016-08:00 solo habra horas 
        Calendar cal = Calendar.getInstance(); 
        
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);        
        
        int horaini=Integer.parseInt( ss[5].substring(0, 2));
        cal.set(Calendar.HOUR_OF_DAY, horaini  );
        cal.set(Calendar.MINUTE,Integer.parseInt( ss[5].substring(3, 5)));
        
        fecha_registro = cal.getTime();     
    }
    public static Vector<OrdenDeEnvio> leerOrdenes(String ruta) throws IOException {
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
            // Logger.getLogger(EjecucionVNS.class.getName()).log(Level.SEVERE, null, ex);
        }        
        String r;  
        Vector<OrdenDeEnvio> res = new Vector<>();

        while((r = in.readLine()) != null){        
            OrdenDeEnvio x= new OrdenDeEnvio(r);
            //System.out.println(">" + x.origen + " " + x.destino + " " + x.fecha_registro + "<");
            res.add(x);
        }            
        return res;
    }
    
    
}
