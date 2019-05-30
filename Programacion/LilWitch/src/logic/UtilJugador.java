/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.*;
import org.newdawn.slick.SlickException;

/**
 * Clase de utilidades relacionadas con los datos de los datos
 * @author alvar
 */
public class UtilJugador {
    private static DatosJugador datos;
    
    public static Jugador retrieveJugador(float x, float y, ControladorProyectiles proyectiles) throws SlickException {
        Jugador j = new Jugador(x, y, proyectiles);
        if(datos.isVaritaNormal()) {
            j.getInventario().setVaritaNormal(true);
        }
        if(datos.isVaritaFuego()) {
            j.getInventario().setVaritaFuego(true);
        }
        if(datos.isVaritaAgua()) {
            j.getInventario().setVaritaAgua(true);
        }
        if(datos.isVaritaLuz()) {
            j.getInventario().setVaritaLuz(true);
        }
        j.setVidaTotal(datos.getVida());
        j.setVida(j.getVidaTotal());
        j.getInventario().setPociones(datos.getPociones());
        j.getInventario().setPocionesG(datos.getPocionesG());
        return j;
    }

    /** Carga los datos del archivo
     * @return l archivo */
    public static boolean cargarDatos() {
        try {
            FileInputStream fis = new FileInputStream("./savedata/save.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            datos = (DatosJugador) ois.readObject();
            ois.close();
            fis.close();
            return true;
        } catch(IOException ioe) {
            System.out.println("Error al cargar los datos");
            return false;
        } catch(ClassNotFoundException c) {
            System.out.println("Clase no encontrada");
            return false;
        }  
    }

    /** Guarda los datos en el archivo */
    public static void guardarDatos(Jugador j, int nivel) {
        try {
            Inventario i = j.getInventario();
            datos = new DatosJugador(i.isVaritaNormal(), i.isVaritaFuego(), i.isVaritaAgua(), i.isVaritaLuz(), i.isBotas(), i.isBotasFuego(), j.getVidaTotal(), i.getPociones(), i.getPocionesG(), nivel);
            FileOutputStream fos = new FileOutputStream("./savedata/save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(datos);
            oos.close();
            fos.close();
            System.out.println("Datos guardados con éxito en save.dat");
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static DatosJugador getDatos() {
        return datos;
    }
    
    public static int getNivelGuardado() {
        return datos.getNivel();
    }
}
