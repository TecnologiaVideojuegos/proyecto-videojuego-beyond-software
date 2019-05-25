/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author corte
 */
public class Murcielago  extends Enemigo{

    public Murcielago(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque);
        
    }

    /*@Override
    public void atacar(int delta) {
        super.setCooldown(0);
        avanzar(delta);
    }

    @Override
    public void avanzar(int delta) {
        if(super.isColision()) {
            if(super.isUp()) {
                movX = (int) (Math.random() * 3 + 1);
                movY = 1;
            }
            if(super.isDown()) {
                movX = (int) (Math.random() * 3 + 1);
                movY = 3;
            }
            if(super.isR()) {
                movX = 1;
                movY = (int) (Math.random() * 3 + 1);
            }
            else {
                movX = 3;
                movY = (int) (Math.random() * 3 + 1);
            }
            super.setColision(false);
        }
        switch(movX){
            case 1:
                super.getSprite().moverX(-150 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(0);
                super.setL(false);
                super.setR(false);
                break;
            case 3:
                super.getSprite().moverX(150 * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(movY){
            case 1:
                super.getSprite().moverY(-150 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(0);
                super.setUp(false);
                super.setDown(false);
                break;
            case 3:
                super.getSprite().moverY(150 * ((float) delta / 1000));
                super.setUp(false);
                super.setDown(true);
                break;   
        }
        
        if (super.isUp()) {
            super.getSprite().stopL();
            super.getSprite().stopR();
            super.getSprite().startUp();
            super.getSprite().stopDown();
        }
        else if (super.isDown()) {
            super.getSprite().stopL();
            super.getSprite().stopR();
            super.getSprite().stopUp();
            super.getSprite().startDown();
        }
        else if (super.isL()) {
            super.getSprite().startL();
            super.getSprite().stopR();
            super.getSprite().stopUp();
            super.getSprite().stopDown();
        }
        else if (super.isR()) {
            super.getSprite().stopL();
            super.getSprite().startR();
            super.getSprite().stopUp();
            super.getSprite().stopDown();
        } 
    }*/
}
