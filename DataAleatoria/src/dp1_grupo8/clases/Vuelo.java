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
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public class Vuelo {

    public String codIni;
    public String codFin;
    public Date ini;
    public Date fin;
    
    
    public Vuelo( Vuelo v){
        codIni = v.codIni;
        codFin = v.codFin;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(v.ini);
        cal.add(Calendar.HOUR_OF_DAY,24);
        ini = cal.getTime();
        
        cal.setTime(v.fin);
        cal.add(Calendar.HOUR_OF_DAY,24);
        fin = cal.getTime();
        
    }
    
    public Vuelo(String r){
        //System.out.println(r);
        String[] ss = r.split("-");
        
        codIni = ss[0];
        codFin = ss[1];
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        int horaini=Integer.parseInt( ss[2].substring(0, 2));
        cal.set(Calendar.HOUR_OF_DAY, horaini  );
        ini = cal.getTime();
        
        if( (codIni.charAt(0)=='S' || codFin.charAt(0)=='S' )&(codIni.charAt(0)!=codFin.charAt(0)  ) )
        cal.add(Calendar.HOUR_OF_DAY, 24);
        else cal.add(Calendar.HOUR_OF_DAY, 12);  
        
        fin = cal.getTime();
        
    }

    public Vuelo(Vuelo v, int dias) {
        
        codIni = v.codIni;
        codFin = v.codFin;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(v.ini);
        cal.add(Calendar.HOUR_OF_DAY,24*dias);
        ini = cal.getTime();
        
        cal.setTime(v.fin);
        cal.add(Calendar.HOUR_OF_DAY,24*dias);
        fin = cal.getTime();
        
    }
    
    
    public String ToString(){
        return codIni+codFin+ini+fin;
    }
    
    
    public static Vector<Vuelo> leerVuelos(String ruta) throws IOException {
         BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(EjecucionVNS.class.getName()).log(Level.SEVERE, null, ex);
        }
        String r;
        Vector<Vuelo> res = new Vector<>();

        while((r = in.readLine()) != null){
            Vuelo x= new Vuelo(r);
            res.add( x );
        }
        
        return res;
        
    }
    
}
