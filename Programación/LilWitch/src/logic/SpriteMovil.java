/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class SpriteMovil extends Sprite {
    private Vector velocidad;

    public SpriteMovil(Image imagen, float x, float y, float escala, float vX, float vY) throws SlickException {
        super(imagen, x, y, escala);
        this.velocidad = new Vector(new Punto(vX, vY));
    }

    public Vector getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Vector velocidad) {
        this.velocidad = velocidad;
    }
    
    public void setVelocidad(float x, float y) {
        this.velocidad = new Vector(new Punto(x, y));
    }
    
    public void setVelocidadX(float x) {
        this.velocidad.setX(x);
    }
    
    public void setVelocidadY(float y) {
        this.velocidad.setY(y);
    }

    //delta hace referencia al tiempo en ms
    public void update(int delta) {
        float x = posicion.getX() + velocidad.getX() * ((float) delta / 1000);
        float y = posicion.getY() + velocidad.getY() * ((float) delta / 1000);
        super.setPosicion(x,y);
    }
    
}
