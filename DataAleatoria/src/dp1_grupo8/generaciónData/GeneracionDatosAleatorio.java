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

/**
 *
 * @author a20092101
 */

public class GeneracionDatosAleatorio {
    ArrayList<OrdenDeEnvio> permutaciones; 
    
         
    public static void main(String[] args) {         
        try {
            
            Vector<Ciudad> ciudades =  Ciudad.leerCiudades("./_aeropuertos.OACI.txt");
            /*for( int i =0; i< ciudades.size() ; i++) {
                 Ciudad ciudad_leida = ciudades.get(i);
                  System.out.println(ciudad_leida.codigo); 
             }  */
             Vector<Combinacion> combinaciones = Permutacion2(ciudades);             
             /* for( int i =0; i< combinaciones.size() ; i++) {
                 Combinacion comb = combinaciones.get(i);
                 System.out.println(comb.origen + " " + comb.destino );                   
              }  
              System.out.println("Cantidad de Combinaciones "+ combinaciones.size());
             */
              
        } catch (IOException ex){                        
              
        }
    }  
    
    private static Vector<Combinacion>  Permutacion2(Vector<Ciudad> ciudades) {
          Vector<Combinacion> combinaciones = new Vector<Combinacion>();
          Ciudad  ciudad_i = null , ciudad_j = null;
          
          for( int i =0; i< ciudades.size() ; i++) {
                  ciudad_i = ciudades.get(i);
                    for( int j =0; j< ciudades.size() ; j++) {
                         ciudad_j = ciudades.get(j);
                         if (ciudad_i.codigo.compareTo(ciudad_j.codigo) != 0)
                            combinaciones.add(new Combinacion(ciudad_i.codigo, ciudad_j.codigo));                         
                    }                    
          }  
         return combinaciones;
    }
    
}
