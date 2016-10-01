/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp1_grupo8.generaciónData;

import dp1_grupo8.clases.Ciudad;
import dp1_grupo8.clases.Combinacion;
import dp1_grupo8.clases.OrdenDeEnvio;
import dp1_grupo8.clases.Vuelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.NativeArray;


/**
 *
 * @author a20092101
 */

public class GeneracionDatosAleatorio {
    
    
         
    public static void main(String[] args) {         
        try {
            
            Vector<Ciudad> ciudades =  Ciudad.leerCiudades("./_aeropuertos.OACI.txt");
            Vector<Combinacion> combinaciones_rutas_prohibidas = Combinacion.leerCombinaciones("./_rutas_prohibidas.txt");   
            Vector<Combinacion> combinaciones_rutas_filtradas = Permutacion2(ciudades,combinaciones_rutas_prohibidas );   
            
           /*
                for( int i =0; i< combinaciones_rutas_prohibidas.size() ; i++) {
                 Combinacion comb = combinaciones_rutas_prohibidas.get(i);
                  System.out.println(comb.origen + " " + comb.destino); 
                }
                System.out.println(combinaciones_rutas_prohibidas.size());
            */
            /*// Comprobacion lectura ciudades 
                for( int i =0; i< ciudades.size() ; i++) {
                 Ciudad ciudad_leida = ciudades.get(i);
                  System.out.println(ciudad_leida.codigo); 
             }  */
             
             
             // Comprobacion de lectura de combinaciones 
             
             
             for( int i =0; i< combinaciones_rutas_filtradas.size() ; i++) {
                 Combinacion comb = combinaciones_rutas_filtradas.get(i);
                 System.out.println(comb.origen + " " + comb.destino );                   
              }  
              System.out.println("Cantidad de Combinaciones "+ combinaciones_rutas_filtradas.size());
             
              
        } catch (IOException ex){                        
              System.out.println("Error en el Bucle Principal Generación de Data Aleatoria");
        }
    }  
    
    private static Vector<Combinacion>  Permutacion2(Vector<Ciudad> ciudades, Vector<Combinacion> combinaciones_rutas_prohibidas) {
          Vector<Combinacion> combinaciones = new Vector<Combinacion>();
          Ciudad  ciudad_i = null , ciudad_j = null;
          String codigo_i, codigo_j ; 
          
          for( int i =0; i< ciudades.size() ; i++) {
                  ciudad_i = ciudades.get(i);
                  codigo_i = ciudad_i.codigo ; 
                    for( int j =0; j< ciudades.size() ; j++) {
                         ciudad_j = ciudades.get(j);
                         codigo_j = ciudad_j.codigo ;                          
                         if (codigo_i.compareTo(codigo_j) != 0){
                             if (!se_encuentra(codigo_i,codigo_j,combinaciones_rutas_prohibidas))
                                   combinaciones.add(new Combinacion(codigo_i, codigo_j));                         
                         }
                    }                    
          }  
         return combinaciones;
    }
    private static boolean se_encuentra(String origen, String destino,Vector<Combinacion> combinaciones_rutas_prohibidas){
        Combinacion comb ; 
        for (int i = 0 ; i< combinaciones_rutas_prohibidas.size(); i++ ){
             comb = combinaciones_rutas_prohibidas.get(i); 
             if ((origen.compareTo(comb.origen) == 0) && destino.compareTo(comb.destino) == 0) {
                   return true;                   
             }
        }        
        return false; 
    }
}
