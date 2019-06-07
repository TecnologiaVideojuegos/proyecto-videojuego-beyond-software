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
public class Esqueleto extends Enemigo {
    private ControladorProyectiles proyectiles;
    private IColisionable jugador;
    
    public Esqueleto(String filename, int ancho, int alto, int x, int y, int distanciaVision, int vida, int ataque, ControladorProyectiles proyectiles) throws SlickException {
        super(filename, ancho, alto, x, y, distanciaVision, vida, ataque, 150);
        this.proyectiles = proyectiles;
        
        //Normal 120x150 Escudo 130x180
    }

    @Override
    public void atacar(int delta) {
        avanzar(delta);
        if (jugador != null) {
            if (super.getCooldown() > 2000) {
                float x = super.getPosicion().getX();
                float y = super.getPosicion().getY();
                
                int vX, vY;
                float dirX = jugador.getPosicion().getX() + 48 - x;
                float dirY = jugador.getPosicion().getY() + 52 - y;
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
                proyectiles.addProyectil("Hueso.png", x, y, 60, 60, 0.25f, vX, vY, 1, 1);
                super.setCooldown(0);
            }
        }
    }
    
    

    /*@Override
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

    @Override
    public void alDetectar(IColisionable colision) {
        jugador = colision;
    }
}
