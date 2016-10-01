/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp1_grupo8.clases;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alulab14
 */
public class Paquete {
    
    public static int IND=1;
    public int ind;
    public String cidOri;
    public String cidIni;
    public Date iniOri;
    
    public String cidFin;
    public Date ini;
    public Date fin;

    public Paquete(String cidIni, String cidFin, Date ini) {
        ind = IND++;
        this.cidOri =this.cidIni = cidIni;
        this.cidFin = cidFin;
        
        this.iniOri = this.ini = ini;
        
        long timeini = ini.getTime();
/*
        if( dp1_grupo8.Dp1_grupo8.mismo(cidIni, cidFin) ){    
            this.fin = new Date (timeini + (3600*1000)*24) ;        
        }
        else this.fin = new Date (timeini + (3600*1000)*48) ;
        */
    }
    
    public String ToString(  ){
        return cidOri+cidIni+cidFin+iniOri+ini+fin;
    }
    
    
    public Paquete( Paquete pq, Vuelo v ){
        
        ind = pq.ind;
        
        this.cidOri = pq.cidOri;
        this.iniOri = pq.iniOri;
        
        this.cidFin = pq.cidFin;
        this.fin = pq.fin;
        
        this.cidIni = v.codFin;
        this.ini = v.fin;
    }
    
    public Paquete(String cidIni, String cidFin ){
        ind = IND++;
        this.cidFin = cidFin;
        this.cidOri =this.cidIni = cidIni;
        
        Calendar cal = Calendar.getInstance();
        iniOri =ini = cal.getTime();
     /*   
        if( dp1_grupo8.Dp1_grupo8.mismo( cidIni, cidFin ) ){
            cal.add(Calendar.HOUR, 24);
            fin = cal.getTime();
       }
        else{
            cal.add( Calendar.HOUR, 48 );
            fin = cal.getTime();
        }*/
     
    }
    
    public Paquete( String cidini, String cidFin, Date ini, Date fin  ){
        ind = IND++;
        this.cidOri =this.cidIni = cidIni;
        this.cidFin = cidFin;
        
        this.iniOri = this.ini = ini;
        this.fin = fin;
    }
    

    
}
