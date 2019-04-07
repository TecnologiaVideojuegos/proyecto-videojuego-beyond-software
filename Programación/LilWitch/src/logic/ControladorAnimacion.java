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
public class ControladorAnimacion {
    private Animation animUp, animDown, animL, animR;
    private float escala, anchura, altura;

    public ControladorAnimacion(Animation animUp, Animation animDown, Animation animL, Animation animR, float escala) {
        this.animUp = animUp;
        this.animDown = animDown;
        this.animL = animL;
        this.animR = animR;
        this.escala = escala;
        this.anchura = this.animUp.getWidth();
        this.altura = this.animUp.getHeight();
    }
    
    public void draw(float x, float y) {
        drawDown(x, y);
        this.animDown.start();
        this.animDown.stopAt(0);
    }
    
    public void drawUp(float x, float y) {
        this.animUp.draw(x, y, anchura*escala, altura*escala);
    }
    
    public void drawDown(float x, float y) {
        this.animDown.draw(x, y, anchura*escala, altura*escala);
    }
    
    public void drawL(float x, float y) {
        this.animL.draw(x, y, anchura*escala, altura*escala);
    }
    
    public void drawR(float x, float y) {
        this.animR.draw(x, y, anchura*escala, altura*escala);
    }
    
    public void startUp() {
        this.animUp.start();
    }
    
    public void startDown() {
        this.animDown.start();
    }
    
    public void startL() {
        this.animL.start();
    }
    
    public void startR() {
        this.animR.start();
    }
    
    public void stopUp() {
        this.animUp.stop();
    }
    
    public void stopDown() {
        this.animDown.stop();
    }
    
    public void stopL() {
        this.animL.stop();
    }
    
    public void stopR() {
        this.animR.stop();
    }

    public Animation getAnimUp() {
        return animUp;
    }

    public void setAnimUp(Animation animUp) {
        this.animUp = animUp;
    }

    public Animation getAnimDown() {
        return animDown;
    }

    public void setAnimDown(Animation animDown) {
        this.animDown = animDown;
    }

    public Animation getAnimL() {
        return animL;
    }

    public void setAnimL(Animation animL) {
        this.animL = animL;
    }

    public Animation getAnimR() {
        return animR;
    }

    public void setAnimR(Animation animR) {
        this.animR = animR;
    }

    public float getEscala() {
        return escala;
    }

    public void setEscala(float escala) {
        this.escala = escala;
    }

    public float getAnchura() {
        return anchura;
    }

    public void setAnchura(float anchura) {
        this.anchura = anchura;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    
    
}
