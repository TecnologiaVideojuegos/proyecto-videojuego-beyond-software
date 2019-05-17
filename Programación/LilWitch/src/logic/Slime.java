/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author corte
 */
public class Slime extends Enemigo{

    public Slime(SpriteAnimado sprite, Rectangle hitbox, int vida, int ataque) {
        super(sprite, hitbox, vida, ataque);
    }

    @Override
    void atacar() {
        avanzar();
    }

    @Override
    void avanzar() {    
        int rx = (int) (Math.random() * 3+1);
        int ry = (int) (Math.random() * 3+1);
        switch(rx){
            case 1:
                super.getSprite().moverX((float)-0.25);
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX((float)0);
                super.setL(false);
                super.setR(false);
                break;
            case 3:
                super.getSprite().moverX((float)0.25);
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(ry){
            case 1:
                super.getSprite().moverY((float)-0.25);
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY((float)0);
                super.setUp(false);
                super.setDown(false);
                break;
            case 3:
                super.getSprite().moverY((float)0.25);
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
