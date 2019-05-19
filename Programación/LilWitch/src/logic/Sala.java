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
    private ArrayList<Esqueleto> esqueletos;

    public Sala(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Esqueleto> esqueletos, Jugador jugador, ControladorProyectiles proyectiles) {
        this.imagen = imagen;
        this.player = jugador;
        this.esqueletos = esqueletos;
        gestor = new GestorColision(proyectiles);
        gestor.registrarCuerpo(jugador);
        for (int i = 0; i < paredes.size(); i++) {
            gestor.registrarCuerpo(paredes.get(i));
            
        }
        for (int j = 0; j < puertas.size(); j++) {
            gestor.registrarCuerpo(puertas.get(j));
            
        }
        if(esqueletos != null) {
            for (int k = 0; k < esqueletos.size(); k++) {
                gestor.registrarCuerpo(esqueletos.get(k));

            }
        }
    }
    
    public void draw(Graphics g, Input entrada) {
        imagen.draw(0, 0);
        player.draw(entrada);
        if(esqueletos != null) {
            drawEnemigos();
        }
        gestor.drawProyectiles();
        gestor.drawHitboxes(g);
    }
    
    public int update(Input entrada, int delta) {
        player.update(entrada, delta);
        if(esqueletos != null) {
            updateEnemigos(delta);
        }
        gestor.updateProyectiles(delta);
        return gestor.comprobarColisiones();
    }
    
    public void updateEnemigos(int delta) {
        for (int i = 0; i < esqueletos.size(); i++) {
            esqueletos.get(i).update(delta);
            
        }
    }
    
    public void drawEnemigos() {
        for (int i = 0; i < esqueletos.size(); i++) {
            esqueletos.get(i).draw();
            
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
