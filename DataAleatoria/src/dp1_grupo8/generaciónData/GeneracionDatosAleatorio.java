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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.NativeArray;


/**
 *
 * @author Brayan
 */

public class GeneracionDatosAleatorio {
    
    
    
    public static void main(String[] args) {         
        try {
               
            // Parametros 
            
            // Datos a configurar 
            int dia = 01 ;
            int mes = 10; 
            int anio = 2016; 
            int cant_min_paquetes = 10 ; 
            int cant_max_paquetes = 20 ;
                        
            int minutos_min_entrada = 12*60 ;  // 12 horas 
            int minutos_max_entrada = 13*60 ;  // 18 horas 
            
            String nombre_archivo = "ordenes envio.txt" ; 
            
            
            
            ArrayList<OrdenDeEnvio> listaOrdenesEnvios = new ArrayList<OrdenDeEnvio>();            
            Vector<Ciudad> ciudades =  Ciudad.leerCiudades("./_aeropuertos.OACI.txt");
            Vector<Combinacion> combinaciones_rutas_prohibidas = Combinacion.leerCombinaciones("./_rutas_prohibidas.txt");   
            // Primera generacion de datos
            Vector<Combinacion> combinaciones_rutas_filtradas = Combinacion_rutas_y_cantidad_posible_x_ruta(ciudades,combinaciones_rutas_prohibidas, cant_min_paquetes,cant_max_paquetes);                        
            combinaciones_rutas_filtradas = Combinacion_rutas_y_cantidad_posible_x_ruta(ciudades,combinaciones_rutas_prohibidas, cant_min_paquetes,cant_max_paquetes);
            generacion_ordenes_aleatorio(combinaciones_rutas_filtradas, minutos_min_entrada, minutos_max_entrada, listaOrdenesEnvios,dia, mes ,anio); 
            /*
            // Segunda generacion de datos 
             cant_min_paquetes = 10 ; 
             cant_max_paquetes = 20 ;
                       
             minutos_min_entrada = 13*60 ;  // 13 horas 
             minutos_max_entrada = 14*60 ;  // 14 horas 
            combinaciones_rutas_filtradas = Combinacion_rutas_y_cantidad_posible_x_ruta(ciudades,combinaciones_rutas_prohibidas, cant_min_paquetes,cant_max_paquetes);
            generacion_ordenes_aleatorio(combinaciones_rutas_filtradas, minutos_min_entrada, minutos_max_entrada, listaOrdenesEnvios,dia, mes ,anio);  
            
            */
            generar_archivo_datos(listaOrdenesEnvios, nombre_archivo);
            
           /*
                for( int i =0; i< combinaciones_rutas_prohibidas.size() ; i++) {
                 Combinacion comb = combinaciones_rutas_prohibidas.get(i);
                  System.out.println(comb.origen + " " + comb.destino); 
                }
                
            */
           System.out.println(combinaciones_rutas_filtradas.size());
            /*// Comprobacion lectura ciudades 
                for( int i =0; i< ciudades.size() ; i++) {
                 Ciudad ciudad_leida = ciudades.get(i);
                  System.out.println(ciudad_leida.codigo); 
             }  */
             
             
             // Comprobacion de lectura de combinaciones 
             
             
             /* for( int i =0; i< combinaciones_rutas_filtradas.size() ; i++) {
                 Combinacion comb = combinaciones_rutas_filtradas.get(i);
                 System.out.println(comb.origen + " " + comb.destino + " " + comb.numeroOrdenesPosibles); 
              }  */
             
              System.out.println("Cantidad de Ordenes Calculados "+ listaOrdenesEnvios.size());                                                     
        } catch (IOException ex){                        
              System.out.println("Error en el Bucle Principal Generación de Data Aleatoria");
        }
    }  
    public static void generar_archivo_datos(ArrayList<OrdenDeEnvio> listaNueva, String nombre_archivo ){
        // Codigo extraido de la wiki 
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("./" + nombre_archivo);
            pw = new PrintWriter(fichero);
                //////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////
                OrdenDeEnvio oe ; 
                for (int i = 0 ; i < listaNueva.size() ; i++){
                oe = listaNueva.get(i);
                //System.out.printf("El cuadrado de %.2f es %.2f\n", 1, 1*1);
                pw.printf("%s-%s-%02d-%02d-%4d-%02d:%02d\n",oe.origen,oe.destino,oe.dia,oe.mes,oe.anio,oe.hora,oe.minuto);
                // System.out.printf(oe.origen + "-" + oe.destino + "-" + oe.dia +"-"+ oe.mes +"-" + oe.anio +"-"+ oe.hora +":"+ oe.minuto);
                }            
                //////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////
        } catch (Exception e) {
            System.out.println("Error n° 1 al escribir el archio ");
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              System.out.println("Error n° 2 al escribir el archio ");
           }
        }
    
    }
    private static void generacion_ordenes_aleatorio(Vector<Combinacion> combinaciones_rutas_posibles , int hora_min, int hora_max, ArrayList<OrdenDeEnvio> listaNueva, int dia, int mes , int anio){
          int numerOrdenes ; 
          Random r = new Random();
          int Low = hora_min;
          int High = hora_max;          
          int cant = 0 ; 
          
          for( int i =0; i< combinaciones_rutas_posibles.size() ; i++) {
                 Combinacion comb = combinaciones_rutas_posibles.get(i);
                 numerOrdenes = comb.numeroOrdenesPosibles;                 
                 for (int j=0; j < numerOrdenes; j++){     
                     int cant_minutos = r.nextInt(High-Low) + Low;
                     cant= cant + 1; 
                     listaNueva.add(new OrdenDeEnvio(comb.origen, comb.destino, cant_minutos, anio, mes, dia));                                           
                    // listaNueva.sort(OrdenDeEnvio,new Comp);
                     Collections.sort(listaNueva, new Comparator<OrdenDeEnvio>(){
                	@Override
			public int compare(OrdenDeEnvio o1, OrdenDeEnvio o2) {
                                if (o1.minutos >= o2.minutos) 
                                     return 1; 
                                else return -1;
			}
		     });
                 }                                  
          }          
          
          /*OrdenDeEnvio oe ; 
          for (int i = 0 ; i < listaNueva.size() ; i++){
              oe = listaNueva.get(i);
      //System.out.printf("El cuadrado de %.2f es %.2f\n", 1, 1*1);
             System.out.printf("%s-%s-%02d-%02d-%4d-%02d:%02d\n",oe.origen,oe.destino,oe.dia,oe.mes,oe.anio,oe.hora,oe.minuto);
      // System.out.printf(oe.origen + "-" + oe.destino + "-" + oe.dia +"-"+ oe.mes +"-" + oe.anio +"-"+ oe.hora +":"+ oe.minuto);
          }*/
          // System.out.println(cant);
         
    }   
    private static Vector<Combinacion>  Combinacion_rutas_y_cantidad_posible_x_ruta(Vector<Ciudad> ciudades, Vector<Combinacion> combinaciones_rutas_prohibidas, int min_paquetes, int max_paquetes) {
          Vector<Combinacion> combinaciones = new Vector<Combinacion>();
          Ciudad  ciudad_i = null , ciudad_j = null;
          String codigo_i, codigo_j ; 
          
          //  Generacion del  Ramdom 
          Random r = new Random();
          int Low = min_paquetes;
          int High = max_paquetes;          
          
          for( int i =0; i< ciudades.size() ; i++) {
                  ciudad_i = ciudades.get(i);
                  codigo_i = ciudad_i.codigo ; 
                    for( int j =0; j< ciudades.size() ; j++) {
                         ciudad_j = ciudades.get(j);
                         codigo_j = ciudad_j.codigo ;                          
                         if (codigo_i.compareTo(codigo_j) != 0){
                             if (!se_encuentra(codigo_i,codigo_j,combinaciones_rutas_prohibidas)){
                                  Combinacion comb = new Combinacion(codigo_i, codigo_j);
                                  comb.numeroOrdenesPosibles = r.nextInt(High-Low) + Low;
                                  combinaciones.add(comb);
                                }
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
