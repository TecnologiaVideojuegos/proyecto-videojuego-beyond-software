/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public class Proyectil implements IColisionable {
    private SpriteMovil sprite;
    private Rectangle hitbox;
    private int ataque;
    private int tipo;

    public Proyectil(String path, float x, float y, int width, int height, float escala, float vX, float vY, int daño, int tipo) {
        try {
            sprite = new SpriteMovil(path, width, height, x, y, escala, vX, vY);
        }
        catch (SlickException e){
            System.out.println("Error al crear el proyectil");
        }
        hitbox = new Rectangle(sprite.getX(), sprite.getY(), width, height);
        this.ataque = daño;
        this.tipo = tipo;
    }
    
    public void update(int delta) {
        sprite.update(delta);
        sincronizarArea();
    }
    
    public void draw() {
        sprite.draw();
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        System.out.println("Colision");
    }

    @Override
    public void sincronizarArea() {
        hitbox.setX(sprite.getX());
        hitbox.setY(sprite.getY());
    }

    @Override
    public boolean isHostile() {
        return true;
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
        return tipo;
    }
    
    @Override
    public boolean isEnemy() {
        if(tipo == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public int getDir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getSalaDestino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getWidth() {
        return sprite.getAncho();
    }

    public int getHeight() {
        return sprite.getAlto();
    }
    
    @Override
    public int getAtaque() {
        return ataque;
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
        return new Punto(sprite.getX(), sprite.getY());
    }
}
