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
public class SpriteMovil {
    private Vector velocidad;
    private float x, y, escala;
    private Animation animacion;
    private int ancho, alto;

    public SpriteMovil(String path, int ancho, int alto, float x, float y, float escala, float vX, float vY) throws SlickException {
        SpriteSheet tileSet;
        tileSet = new SpriteSheet(path, ancho, alto);
        Image[] img = new Image[tileSet.getHorizontalCount()*tileSet.getVerticalCount()];
        int cont = 0;
        
        for (int i = 0; i < tileSet.getHorizontalCount(); i++) {
            for (int j = 0; j < tileSet.getVerticalCount(); j++) {
                img[cont] = tileSet.getSprite(i, j);
                cont++;
            }   
        }
        
        this.x = x;
        this.y = y;
        this.escala = 1;
        for (int i = 0; i < img.length; i++) {
            System.out.println("Array" + i + ": " + img[i]);;
            
        }
        this.animacion = new Animation(img, 100);
        this.velocidad = new Vector(new Punto(vX, vY));
        this.alto = alto;
        this.ancho = ancho;
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
        x = x + velocidad.getX() * ((float) delta / 1000);
        y = y + velocidad.getY() * ((float) delta / 1000);
    }
    
    public void draw() {
        animacion.draw(x, y);
        animacion.start();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    public Animation getAnimacion() {
        return animacion;
    }

    public void setAnimacion(Animation animacion) {
        this.animacion = animacion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
