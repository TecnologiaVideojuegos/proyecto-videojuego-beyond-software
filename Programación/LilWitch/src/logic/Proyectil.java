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

    public Proyectil(Image imagen, float x, float y, float escala, float vX, float vY) {
        try {
            sprite = new SpriteMovil(imagen, x, y, escala, vX, vY);
        }
        catch (SlickException e){
            System.out.println("Error al crear el proyectil");
        }
        hitbox = new Rectangle(sprite.getPosicion().getX(), sprite.getPosicion().getY(), sprite.getImagen().getWidth(), sprite.getImagen().getHeight());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alColisionar(IColisionable colision) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sincronizarArea() {
        hitbox.setX(sprite.getPosicion().getX());
        hitbox.setY(sprite.getPosicion().getY());
    }

    @Override
    public boolean isHostile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int getDir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getSalaDestino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
