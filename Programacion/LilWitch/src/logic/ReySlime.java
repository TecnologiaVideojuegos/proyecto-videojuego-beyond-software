/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class ReySlime extends Boss {
    private Jugador player;
    private int movX, movY;

    public ReySlime(Jugador player) throws SlickException {
        super("slime_boss_2.png", 360, 450, 960, 120, 20, 1, 100, player);
        this.player = player;
        this.movX = (int) (Math.random() * 3+1);
        this.movY = (int) (Math.random() * 3+1);
        
        while(movX == 2 && movY == 2) {
            this.movX = (int) (Math.random() * 3+1);
            this.movY = (int) (Math.random() * 3+1);
        }
    }

    @Override
    public void atacar(int delta) {
        //avanzar(delta); 
        float x = super.getPosicion().getX();
        float y = super.getPosicion().getY();
        System.out.println("Posicion jugador: " + player.getPosicion().getX() + " , " + player.getPosicion().getY());
        float dirX = player.getPosicion().getX() + 48 - x;
        float dirY = player.getPosicion().getY() + 52 - y;
        System.out.println("Dir: " + dirX + " , " + dirY);
                
        if (dirX > 0) {
            super.getSprite().moverX(super.getVelocidad() * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
        }
        else if (dirX < 0) {
            super.getSprite().moverX(-super.getVelocidad() * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
        }
        else {
            super.setL(false);
            super.setR(false);
        }
        
        if (dirY > 0) {
            super.getSprite().moverY(super.getVelocidad() * ((float) delta / 1000));
                super.setUp(false);
                super.setDown(true);
        }
        else if (dirY < 0) {
            super.getSprite().moverY(-super.getVelocidad() * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
        }
        else {
            super.setUp(false);
            super.setDown(false);
        }
        updateAnimacion();
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
            
            switch(movX){
                case 1:
                    super.getSprite().moverX(-super.getVelocidad() * ((float) delta / 1000));
                    super.setL(true);
                    super.setR(false);
                    break;
                case 2:
                    super.getSprite().moverX(0);
                    super.setL(false);
                    super.setR(false);
                    break;
                case 3:
                    super.getSprite().moverX(super.getVelocidad() * ((float) delta / 1000));
                    super.setL(false);
                    super.setR(true);
                    break;  
            }
            
            switch(movY){
                case 1:
                    super.getSprite().moverY(-super.getVelocidad() * ((float) delta / 1000));
                    super.setUp(true);
                    super.setDown(false);
                    break;
                case 2:
                    super.getSprite().moverY(0);
                    super.setUp(false);
                    super.setDown(false);
                    break;
                case 3:
                    super.getSprite().moverY(super.getVelocidad() * ((float) delta / 1000));
                    super.setUp(false);
                    super.setDown(true);
                    break;   
            }
            updateAnimacion();
        }
    }
    
    public void updateAnimacion() {
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
