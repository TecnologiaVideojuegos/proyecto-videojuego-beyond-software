/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 *
 * @author alvar
 */
public class SpriteAnimado {
    private ControladorAnimacion animaciones;
    private Image staticDown, staticUp, staticR, staticL;
    protected Punto posicion;

    public SpriteAnimado(ControladorAnimacion animaciones, Image imagenDown, Image imagenUp, Image imagenR, Image imagenL, Punto posicion) {
        this.animaciones = animaciones;
        this.staticDown = imagenDown;
        this.staticUp = imagenUp;
        this.staticR = imagenR;
        this.staticL = imagenL;
        this.posicion = posicion;
    }

    public SpriteAnimado(ControladorAnimacion animaciones, Image imagenDown, Image imagenUp, Image imagenR, Image imagenL, float x, float y) throws SlickException {
        this(animaciones, imagenDown, imagenUp, imagenR, imagenL, new Punto(x, y));
    }

    public ControladorAnimacion getAnimaciones() {
        return animaciones;
    }

    public void setAnimaciones(ControladorAnimacion animaciones) {
        this.animaciones = animaciones;
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
        this.staticDown.draw(posicion.getX(), posicion.getY());
    }
    
    public void drawStaticUp() {
        this.staticUp.draw(posicion.getX(), posicion.getY());
    }
    
    public void drawStaticR() {
        this.staticR.draw(posicion.getX(), posicion.getY());
    }
    
    public void drawStaticL() {
        this.staticL.draw(posicion.getX(), posicion.getY());
    }
    
    public void drawUp() {
        this.animaciones.drawUp(posicion.getX(), posicion.getY());
    }
    
    public void drawDown() {
        this.animaciones.drawDown(posicion.getX(), posicion.getY());
    }
    
    public void drawL() {
        this.animaciones.drawL(posicion.getX(), posicion.getY());
    }
    
    public void drawR() {
        this.animaciones.drawR(posicion.getX(), posicion.getY());
    }
    
    public void startUp() {
        this.animaciones.startUp();
    }
    
    public void startDown() {
        this.animaciones.startDown();
    }
    
    public void startL() {
        this.animaciones.startL();
    }
    
    public void startR() {
        this.animaciones.startR();
    }
    
    public void stopUp() {
        this.animaciones.stopUp();
    }
    
    public void stopDown() {
        this.animaciones.stopDown();
    }
    
    public void stopL() {
        this.animaciones.stopL();
    }
    
    public void stopR() {
        this.animaciones.stopR();
    }

    public Image getStaticDown() {
        return staticDown;
    }

    public void setStaticDown(Image staticDown) {
        this.staticDown = staticDown;
    }

    public Image getStaticUp() {
        return staticUp;
    }

    public void setStaticUp(Image staticUp) {
        this.staticUp = staticUp;
    }

    public Image getStaticR() {
        return staticR;
    }

    public void setStaticR(Image staticR) {
        this.staticR = staticR;
    }

    public Image getStaticL() {
        return staticL;
    }

    public void setStaticL(Image staticL) {
        this.staticL = staticL;
    }  
}
