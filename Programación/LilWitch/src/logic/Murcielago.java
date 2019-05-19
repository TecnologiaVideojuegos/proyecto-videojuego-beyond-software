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
    private int contador, dirX, dirY;

    public Murcielago(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque);
        this.contador = 0;
        this.dirX = (int) (Math.random() * 3+1);
        this.dirY = (int) (Math.random() * 3+1);
    }

    @Override
    void atacar(int delta) {
        super.setCooldown(0);
        avanzar(delta);
    }

    @Override
    void avanzar(int delta) {
        if (contador > 4) {
            dirX = (int) (Math.random() * 3+1);
            dirY = (int) (Math.random() * 3+1);
            contador = 0;
        }
        switch(dirX){
            case 1:
                super.getSprite().moverX(-2 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(0);
                super.setL(false);
                super.setR(false);
                break;
            case 3:
                super.getSprite().moverX(2 * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(dirY){
            case 1:
                super.getSprite().moverY(-2 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(0);
                super.setUp(false);
                super.setDown(false);
                break;
            case 3:
                super.getSprite().moverY(2 * ((float) delta / 1000));
                super.setUp(false);
                super.setDown(true);
                break;   
        }
        contador ++;
        
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
