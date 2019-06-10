/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public class Wall implements IColisionable {
    Polygon hitbox;

    public Wall(float[] puntos) {
        hitbox = new Polygon(puntos);
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision, int delta) {
        //System.out.println("Colision");
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
        return false;
    }
    
    @Override
    public boolean isPlayer() {
        return false;
    }
    
    @Override
    public int isProyectile() {
        return 0;
    }
    
    @Override
    public int getDir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getSalaDestino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public Shape getVisionRange() {
        return hitbox;
    }

    @Override
    public void alDetectar(IColisionable colision) {}
    
    @Override
    public Punto getPosicion() {
        return new Punto(hitbox.getX(), hitbox.getY());
    }
    
    @Override
    public int isObjeto() {
        return 0;
    }
    
    @Override
    public boolean isWall() {
        return true;
    }
}
