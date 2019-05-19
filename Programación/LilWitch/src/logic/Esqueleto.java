/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author corte
 */
public class Esqueleto extends Enemigo{
    private ControladorProyectiles proyectiles;
    private boolean escudo;
    private int contador, dirX, dirY;
    
    public Esqueleto(String filename, int ancho, int alto, int x, int y, int distanciaVision, int vida, int ataque, ControladorProyectiles proyectiles) throws SlickException {
        super(filename, ancho, alto, x, y, distanciaVision, vida, ataque);
        this.proyectiles = proyectiles;
        this.contador = 0;
        this.dirX = (int) (Math.random() * 3+1);
        this.dirY = (int) (Math.random() * 3+1);
        if(x == 120) {
            escudo = false;
        }
        else {
            escudo = true;
        }
        //Normal 120x150 Escudo 130x180
    }

    @Override
    void atacar(int delta) {
        //avanzar(delta);
        if (super.getPlayerPosition() != null) {
            if (super.getCooldown() > 500) {
                float x = super.getPosicion().getX();
                float y = super.getPosicion().getY();
                int vX, vY;
                /*x += (super.getSprite().getStaticDown().getWidth() / 2) - 29;
                y += super.getSprite().getStaticDown().getHeight();*/
                float dirX = super.getPlayerPosition().getX() - x;
                float dirY = super.getPlayerPosition().getY() - y;
                if (dirX < 0) {
                    vX = -100;
                }
                else {
                    vX = 100;
                }
                if (dirY < 0) {
                    vY = -100;
                }
                else {
                    vY = 100;
                }
                proyectiles.addProyectil("Hueso.png", x, y, vX, vY, 1, 1);
                super.setCooldown(0);
            }
        }
    }

    @Override
    void avanzar(int delta) {
        if (contador > 50) {
            dirX = (int) (Math.random() * 3+1);
            dirY = (int) (Math.random() * 3+1);
            contador = 0;
        }
        switch(dirX){
            case 1:
                super.getSprite().moverX(-100 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(0);
                super.setL(false);
                super.setR(false);
                break;
            case 3:
                super.getSprite().moverX(100 * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(dirY){
            case 1:
                super.getSprite().moverY(-100 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(0);
                super.setUp(false);
                super.setDown(false);
                break;
            case 3:
                super.getSprite().moverY(100 * ((float) delta / 1000));
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
