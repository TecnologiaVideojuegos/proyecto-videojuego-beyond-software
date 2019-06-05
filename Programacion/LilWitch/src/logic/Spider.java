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
public class Spider extends Boss {
    private Jugador player;
    private int movX, movY, dirXo, dirYo, tiempo, numColisiones;
    private boolean primerTurno;

    public Spider(Jugador player) throws SlickException {
        super("arana_2.png", 300, 300, 960, 120, 2, 1, 200, player, 30, 30, 60, 35);
        this.player = player;
        this.movX = 3;
        this.movY = 3;
        this.dirXo = 0;
        this.dirYo = 0;
        this.tiempo = 0;
        this.primerTurno = true;
        this.numColisiones = 0;
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        tiempo += delta;
    }

    @Override
    public void atacar(int delta) {
        if(tiempo < 5000) {
            perseguir(delta);
        }
        else if(tiempo < 50000) {
            ataque1(delta);
        }
//        else if(tiempo < 15000) {
//            super.atacar(delta);
//        }
        else {
            primerTurno = true;
            tiempo = 0;
        }
        updateAnimacion();
    }
    
    public void ataque1(int delta) {
        if(primerTurno) {
            super.getSprite().setPosicion(20, 20);
            super.resetDirecciones();
            primerTurno = false;
            
        }
        if(super.isColision()) {
            if(super.isUp()) {
                movY = 3;
            }
            if(super.isDown()) {
                movY = 1;
            }
            if(super.isR() && numColisiones == 3) {
                movX = 1;
            }
            else if(super.isL() && numColisiones == 3) {
                movX = 3;
            }
            super.setColision(false);
        }
        switch(movX){
            case 1:
                super.getSprite().moverX(-super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(0);
                super.setL(false);
                super.setR(false);
                break;
            case 3:
                super.getSprite().moverX(super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setL(false);
                super.setR(true);
                break;  
        }
        switch(movY){
            case 1:
                super.getSprite().moverY(-super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(0);
                super.setUp(false);
                super.setDown(false);
                break;
            case 3:
                super.getSprite().moverY(super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setUp(false);
                super.setDown(true);
                break;   
        }  
    }
    
    public void perseguir(int delta) { 
        float x = super.getHitbox().getCenterX();
        float y = super.getHitbox().getCenterY(); 
        int dirX;
        int dirY;
        
        if(!super.isColision()) {        
            dirX = (int) (player.getPosicion().getX() + 48 - x);
            dirY = (int) (player.getPosicion().getY() + 52 - y);
        }
        else {
            if(x <= 21 + super.getHitbox().getWidth() / 2) {
                if(dirXo == 0) {
                    dirXo = signo((int) (player.getPosicion().getX() + 48 - x)); 
                }
                dirX = (int) (player.getPosicion().getX() + 48 - x);
                if(signo(dirX) == dirXo) {
                    dirX = 0;
                }
                else {
                    dirXo = 0;
                    super.setColision(false);
                }
            }
            else if(x >= 1899 - super.getHitbox().getWidth() / 2) {
                if(dirXo == 0) {
                    dirXo = signo((int) (player.getPosicion().getX() + 48 - x)); 
                }
                dirX = (int) (player.getPosicion().getX() + 48 - x);
                if(signo(dirX) == dirXo) {
                    dirX = 0;
                }
                else {
                    dirXo = 0;
                    super.setColision(false);
                }
            }
            else {
                dirX = (int) (player.getPosicion().getX() + 48 - x); 
            }
            
            if(y <= 21 + super.getHitbox().getHeight() / 2) {
                if(dirYo == 0) {
                    dirYo = signo((int) (player.getPosicion().getY() + 52 - y)); 
                }
                dirY = (int) (player.getPosicion().getY() + 52 - y);
                if(signo(dirY) == dirYo) {
                    dirY = 0;
                }
                else {
                    dirYo = 0;
                    super.setColision(false);
                }
            }
            else if(y >= 939 - super.getHitbox().getHeight() / 2) {
                if(dirYo == 0) {
                    dirYo = signo((int) (player.getPosicion().getY() + 52 - y)); 
                }
                dirY = (int) (player.getPosicion().getY() + 52 - y);
                if(signo(dirY) == dirYo) {
                    dirY = 0;
                }
                else {
                    dirYo = 0;
                    super.setColision(false);
                }
            }
            else {
                dirY = (int) (player.getPosicion().getY() + 52 - y);
            }
        }
        
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

    @Override
    public void alColisionar(IColisionable colision, int delta) {
        super.alColisionar(colision, delta); 
        if (!colision.isPlayer()) {
            numColisiones += 1;
        }
    }
    
    
    
    public int signo(int num) {
        if(num >= 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
