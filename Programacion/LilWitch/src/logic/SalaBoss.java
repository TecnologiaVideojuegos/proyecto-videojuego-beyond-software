/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class SalaBoss extends Sala {
    private ArrayList<Cofre> cofres;
    private ArrayList<Puerta> puertas;
    private ArrayList<Image> imagenes;
    private Boss jefe;

    public SalaBoss(ArrayList<Image> imagenes, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, int tipo, Objeto objeto, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagenes.get(0), paredes, null, null, null, jugador, proyectiles);
        this.puertas = puertas;
        this.imagenes = imagenes;
        this.cofres = new ArrayList<>();
        try {
            switch(tipo) {
                case 1:
                   this.jefe = new ReySlime(jugador);
                   crearCofres(objeto, 0);
                   break;
                case 2:
                    this.jefe = new ReySlime(jugador); 
                    crearCofres(objeto, 0);
                    break;
                case 3:
                    this.jefe = new ReySlime(jugador); 
                    crearCofres(objeto, 0);
                    break;
                case 4:
                    this.jefe = new ReySlime(jugador);
                    crearCofres(objeto, 0);
                    break;
                case 5:
                    this.jefe = new ReySlime(jugador); 
                    crearCofres(objeto, 0);
                    break;
            }
        }
        catch (SlickException se) {
            System.out.println("Error al crear la sala");
        }
        
        super.getGestor().registrarCuerpo(jefe);
    }
    
    public void crearCofres(Objeto obj, int tipo) {
        try {
            cofres.add(new Cofre(new Objeto(9, 840, 420), 840, 420, 0));
            cofres.add(new Cofre(obj, obj.getPosicion().getX(), obj.getPosicion().getY(), 0));
        } catch (SlickException ex) {
            System.out.println("Error al generar los cofres");
        }
    }

    @Override
    public int update(Input entrada, int delta) {
        if(jefe.getVida() <= 0) {
            super.setImagen(imagenes.get(1));
            habilitarPuertas();
            dropObjetos();
            for (int i = 0; i < cofres.size(); i++) {
                cofres.get(i).update(delta);   
            }
        }
        jefe.update(delta);
        int n = super.update(entrada, delta);
        return n;
    }
    
    @Override
    public void draw(Graphics g, Input entrada) {
        super.draw(g, entrada);
        jefe.draw();
        if(jefe.getVida() <= 0) {
            for (int i = 0; i < cofres.size(); i++) {
                cofres.get(i).draw(); 
            }
        }
    }
    
    public void habilitarPuertas() {
        for (int j = 0; j < puertas.size(); j++) {
            super.getGestor().registrarCuerpo(puertas.get(j));   
        }
    }
    
    public void dropObjetos() {
        for (int i = 0; i < cofres.size(); i++) {
            super.getGestor().registrarCuerpo(cofres.get(i));
        }
    }
}
