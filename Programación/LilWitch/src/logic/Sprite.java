/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.*;

/**
 *
 * @author alvar
 */
public class Sprite {
    protected Punto posicion;
    private Image imagen;
    private float escala;

    public Sprite(Image imagen, Punto posicion, float escala) throws SlickException {
        this.imagen = imagen;
        this.posicion = posicion;
        this.escala = escala;
    }
    
    public Sprite(Image imagen, float escala) throws SlickException {
        this(imagen, new Punto(0, 0), escala);
    }
    
    public Sprite(Image imagen, float x, float y, float escala) throws SlickException {
        this(imagen, new Punto(x, y), escala);
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }
    
    public Punto getPosicion() {
        return posicion;
    }

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }
    
    public void setPosicion(float x, float y) {
        this.posicion.setX(x);
        this.posicion.setY(y);
    }
    
    public void moverX() {
        float x = this.posicion.getX() + 1;
        this.posicion.setX(x);
    }
    
    public void moverX(float n) {
        float x = this.posicion.getX() + n;
        this.posicion.setX(x);
    }
    
    public void moverY() {
        float y = this.posicion.getY() + 1;
        this.posicion.setY(y);
    }
    
    public void moverY(float n) {
        float y = this.posicion.getY() + n;
        this.posicion.setY(y);
    }
    
    public void draw() {
        this.imagen.draw(posicion.getX(),posicion.getY(), escala);
    }
}
