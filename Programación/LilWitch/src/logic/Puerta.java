/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public class Puerta implements IColisionable {
    Rectangle hitbox;
    int destino, direccion;
    int nivel;
    /*  Direccion hace referencia a la posicion de la puerta y destino apunta a la siguiente sala:
        Direccion  Significado
        0          Norte
        1          Este
        2          Sur
        3          Oeste*/

    public Puerta(float x, float y, float width, float height, int direccion, int destino, int nivel) { 
        hitbox = new Rectangle(x, y, width, height);
        this.direccion = direccion;
        this.destino = destino;
        this.nivel = nivel;
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(colision.isPlayer()) {
            System.out.println("Teleport");
        }
    }

    @Override
    public void sincronizarArea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isHostile() {
        return false;
    }
    
    @Override
    public boolean isGate() {
        return true;
    }
    
    @Override
    public boolean isPlayer() {
        return false;
    }
    
    @Override
    public boolean isProyectile() {
        return false;
    }
    
    @Override
    public int getDir() {
        return direccion;
    }
    
    @Override
    public int getSalaDestino() {
        return destino;
    }
    
    public int getEstado() {
        return nivel;
    }
    
    @Override
    public boolean isEnemy() {
        return false;
    } 
    
    @Override
    public int getAtaque() {
        return 0;
    }
    
    @Override
    public int getVida() {
        return 1;
    } 
}