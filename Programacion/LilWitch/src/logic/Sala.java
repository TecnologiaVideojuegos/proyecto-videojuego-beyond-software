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
        this.objetos = objetos;
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
            for (int k = 0; k < objetos.size(); k++) {
                gestor.registrarCuerpo(objetos.get(k));

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
        player.draw(entrada, g);
        gestor.drawProyectiles();
        gestor.drawHitboxes(g);
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
        gestor.drawHitboxes(g);
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
        for (int i = 0; i < objetos.size(); i++) {
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
}
