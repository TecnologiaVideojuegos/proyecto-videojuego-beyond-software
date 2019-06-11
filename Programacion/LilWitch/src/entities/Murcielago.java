/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author corte
 */
public class Murcielago  extends Enemigo{

    public Murcielago(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque, 300);
        super.setMovX((int) (Math.random()*2 + 1));
        super.setMovY((int) (Math.random()*2 + 1));
        
    }

    @Override
    public void avanzar(int delta) {
        if(super.isColision()) {
            if(super.isUp()) {
                super.setMovX((int) (Math.random()*2 + 1));
                super.setMovY(1);
            }
            if(super.isDown()) {
                super.setMovX((int) (Math.random()*2 + 1));
                super.setMovY(2);
            }
            if(super.isR()) {
                super.setMovX(1);
                super.setMovY((int) (Math.random()*2 + 1));
            }
            else {
                super.setMovX(2);
                super.setMovY((int) (Math.random()*2 + 1));
            }
            super.setColision(false);
        }
        switch(super.getMovX()){
            case 1:
                super.getSprite().moverX(-super.getVelocidad() * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(super.getVelocidad() * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(super.getMovY()){
            case 1:
                super.getSprite().moverY(-super.getVelocidad() * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(super.getVelocidad() * ((float) delta / 1000));
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
    }
}
