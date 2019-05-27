/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class SalaBoss extends Sala {
    private ArrayList<Objeto> objetos;
    private ArrayList<Puerta> puertas;
    private ArrayList<Image> imagenes;
    private Boss jefe;

    public SalaBoss(ArrayList<Image> imagenes, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, int tipo, Objeto objeto, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagenes.get(0), paredes, null, null, jugador, proyectiles);
        this.puertas = puertas;
        this.imagenes = imagenes;
        this.objetos = new ArrayList<>();
        try {
            objetos.add(new Objeto(8, 840, 420));
            switch(tipo) {
                case 1:
                   this.jefe = new ReySlime(jugador); 
                   break;
                case 2:
                    this.jefe = new ReySlime(jugador);  
                    break;
                case 3:
                    this.jefe = new ReySlime(jugador); 
                    break;
                case 4:
                    this.jefe = new ReySlime(jugador); 
                    break;
                case 5:
                    this.jefe = new ReySlime(jugador); 
                    break;
            }
        }
        catch (SlickException se) {
            System.out.println("Error al crear la sala");
        }
        
        objetos.add(objeto);
        super.getGestor().registrarCuerpo(jefe);
    }

    @Override
    public int update(Input entrada, int delta) {
        if(jefe.getVida() <= 0) {
            super.setImagen(imagenes.get(1));
            habilitarPuertas();
            dropObjetos();
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
            for (int i = 0; i < objetos.size(); i++) {
                objetos.get(i).draw(); 
            }
        }
    }
    
    public void habilitarPuertas() {
        for (int j = 0; j < puertas.size(); j++) {
            super.getGestor().registrarCuerpo(puertas.get(j));   
        }
    }
    
    public void dropObjetos() {
        for (int i = 0; i < objetos.size(); i++) {
            super.getGestor().registrarCuerpo(objetos.get(i));
            
        }
    }
}
