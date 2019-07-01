/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import entities.Enemigo;
import collisions.Wall;
import collisions.Puerta;
import collisions.Objeto;
import entities.Jugador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.ControladorProyectiles;
import logic.GestorColision;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class Sala {
    private GestorColision gestor;
    private Image imagen;
    private Jugador player;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<Objeto> objetos;

    public Sala(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Enemigo> enemigos, ArrayList<Objeto> objetos, Jugador jugador, ControladorProyectiles proyectiles) {
        this.imagen = imagen;
        this.player = jugador;
        this.enemigos = enemigos;
        
        gestor = new GestorColision(proyectiles);
        gestor.registrarCuerpo(jugador);
        
        for (int i = 0; i < paredes.size(); i++) {
            gestor.registrarCuerpo(paredes.get(i));
            
        }
        
        if(puertas != null) {
            for (int j = 0; j < puertas.size(); j++) {
                gestor.registrarCuerpo(puertas.get(j));

            }
        }
        
        if(objetos != null) {
            this.objetos = objetos;
            for (int k = 0; k < this.objetos.size(); k++) {
                gestor.registrarCuerpo(this.objetos.get(k));
            }
        }
        
        if(this.enemigos != null) {
            for (int l = 0; l < this.enemigos.size(); l++) {
                gestor.registrarCuerpo(this.enemigos.get(l));

            }
        }
    }
    
    public void draw(Graphics g, Input entrada) {
        imagen.draw(0, 0);
        if(enemigos != null) {
            drawEnemigos();
        }
        if(objetos != null) {
            drawObjetos();
        }
        gestor.drawProyectiles();
        player.draw(entrada, g);  
        //gestor.drawHitboxes(g);
    }
    
    public void draw(Graphics g) {
        imagen.draw(0, 0);
        if(enemigos != null) {
            drawEnemigos();
        }
        if(objetos != null) {
            drawObjetos();
        }
        gestor.drawProyectiles();
        //gestor.drawHitboxes(g);
    }
    
    public int update(Input entrada, int delta) {
        player.update(entrada, delta);
        if(enemigos != null) {
            updateEnemigos(delta);
        }
        if(objetos != null) {
            updateObjetos();
        }
        gestor.updateProyectiles(delta);
        return gestor.comprobarColisiones(delta);
    }
    
    public void updateEnemigos(int delta) {
        for (int i = 0; i < enemigos.size(); i++) {
            if(enemigos.get(i).getVida() <= 0) {
                if((int) (Math.random() * 10+1) <= 2) {
                    int x = (int) enemigos.get(i).getPosicion().getX();
                    int y = (int)enemigos.get(i).getPosicion().getY();
                    try {
                        if(objetos == null) {
                            objetos = new ArrayList<>();
                        }
                        Objeto obj = new Objeto(7, x, y);
                        objetos.add(obj);
                        gestor.registrarCuerpo(obj);
                    } catch (SlickException ex) {
                        System.out.println("Error al crear el objeto");;
                    }
                }
                enemigos.remove(i);
            }    
        }
        
        for (int j = 0; j < enemigos.size(); j++) {
            enemigos.get(j).update(delta);
            
        }
    }
    
    public void drawEnemigos() {
        for (int i = 0; i < enemigos.size(); i++) {
            enemigos.get(i).draw();    
        }
    }
    
    public void updateObjetos() {
        //System.out.println("Tamaño: " + objetos.size());
        for (int i = 0; i < objetos.size(); i++) {
            //System.out.println("Objeto " + i + ": " + objetos.get(i).getVida());
            if(objetos.get(i).getVida() <= 0) {
                
                objetos.remove(i);
            }    
        }
    }
    
    public void drawObjetos() {
        for (int i = 0; i < objetos.size(); i++) {
            objetos.get(i).draw();    
        }
    }

    public GestorColision getGestor() {
        return gestor;
    }

    public void setGestor(GestorColision gestor) {
        this.gestor = gestor;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Jugador getPlayer() {
        return player;
    }

    public void setPlayer(Jugador player) {
        this.player = player;
    } 
    
    public int getvidaBoss() {
        return 99;
    }
}
