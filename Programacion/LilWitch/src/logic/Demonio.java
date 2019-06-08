/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author alvar
 */
public class Demonio extends Boss {
    private Jugador player;
    private int movX, movY, dirXo, dirYo, tiempo, numColisiones, eleccion, eleccion2, contador, posSombra;
    private boolean primerTurno, saltando, start;
    private Animation sombra;
    private ControladorProyectiles proyectiles;

    public Demonio(Jugador player, ControladorProyectiles proyectiles) throws SlickException {
        super("Ignis, Guardián del Infierno", "demonio.png", 400, 400, 840, 120, 2, 1, 200, player, 30, 30, 60, 35);
        this.player = player;
        this.eleccion = (int) (Math.random() * 2+1);
        this.dirXo = 0;
        this.dirYo = 0;
        this.tiempo = 0;
        this.primerTurno = true;
        this.numColisiones = 0;
        SpriteSheet tileSet;
        Image[] img = new Image[5];
        tileSet = new SpriteSheet("resources/sprites/Sombra.png", 330, 330);
        for (int i = 0; i < 5; i++) {
            img[i] = tileSet.getSprite(i, 0);
        }
        this.sombra = new Animation(img, 150);
        sombra.setLooping(false);
        this.saltando = false;
        this.contador = 0;
        this.proyectiles = proyectiles;
        this.start = true;
        this.posSombra = 3;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g); 
        if(saltando) {
            switch(posSombra) {
                case 1:
                    sombra.draw(20, 20);
                    break;
                case 2:
                    sombra.draw(1850 - super.getHitbox().getWidth(), 20);
                    break;
                case 3:
                    sombra.draw(800, 300);
                    break;         
            }
        }
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        tiempo += delta;
    }

    @Override
    public void atacar(int delta) {
        if(tiempo < 5000) {
            if(tiempo > 500 || start) {
                perseguir(delta);
            }
        }
        else if(eleccion == 1 && numColisiones < 6) {
            ataque1(delta);
            lanzarHechizo();
        }
        else if(eleccion == 2 && numColisiones < 7) {
            ataque2(delta);
            lanzarHechizo();
        }
        else {
            if(start) {
                start = false;
            }
            if(contador == 0) {
                posSombra = 3;
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                super.resetDirecciones();
                sombra.restart();
                contador = 0;
                saltando = false;
                primerTurno = true;
                tiempo = 0;
                numColisiones = 0;
                super.getSprite().setPosicion(800, 300);
                eleccion = (int) (Math.random() * 3+1);
                disparoCircular();
            }
        }
        updateAnimacion();
    }
    
    public void ataque1(int delta) {
        if(primerTurno) {
            if(contador == 0) {
                posSombra = 1;
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                super.resetDirecciones();
                sombra.restart();
                contador = 0;
                saltando = false;
                super.setColision(false);
                movX = 2;
                movY = 2;
                super.getSprite().setPosicion(20, 20);
                super.resetDirecciones();
                primerTurno = false;
            }  
        }
        if(super.isColision()) {
            if(super.isUp()) {
                movY = 2;
            }
            if(super.isDown()) {
                movY = 1;
            }
            if(super.isR() && numColisiones == 3) {
                movX = 1;
            }
            else if(super.isL() && numColisiones == 3) {
                movX = 2;
            }
            super.setColision(false);
        }
        avanzar(delta);
    }
    
    public void ataque2(int delta) {
        if(primerTurno) {
            if(contador == 0) {
                posSombra = 2;
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                super.resetDirecciones();
                sombra.restart();
                contador = 0;
                saltando = false;
                super.setColision(false);
                movX = 1;
                movY = 2;
                super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 20);
                super.resetDirecciones();
                primerTurno = false;
            }  
        }
        if(super.isColision()) {
            if(super.isUp()) {
                movY = 2;
            }
            if(super.isDown()) {
                movY = 1;
            }
            if(super.isR() && numColisiones == 4) {
                movX = 1;
            }
            else if(super.isL() && numColisiones == 4) {
                movX = 2;
            }
            super.setColision(false);
        }
        avanzar(delta);  
    }
    
    public void lanzarHechizo() {
        if (super.getCooldown() > 1000) {
                float x = super.getPosicion().getX() + 200;
                float y = super.getPosicion().getY() + 200;
                
                int dirX = signo(player.getPosicion().getX() + 48 - x);
                int dirY = signo(player.getPosicion().getY() + 52 - y);
     
                crearProyectil(x, y, dirX, dirY);
                super.setCooldown(0);
            }
    }
    
    public void disparoCircular() {
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, 0, -1);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, -1, -1);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, -1, 0);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, -1, 1);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, 0, 1);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, 1, 1);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, 1, 0);  
        crearProyectil(super.getSprite().getPosicion().getX() + 200, super.getSprite().getPosicion().getY() + 200, 1, -1);  
    }
    
    public void crearProyectil(float x, float y, float dirX, float dirY) {
        proyectiles.addProyectil("Fire_2.png", x, y, 58, 72, 1f, 300*dirX, 300*dirY, 1, 1);
        }
    
    @Override
    public void avanzar(int delta) {
        switch(movX){
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
            case 3:
                super.getSprite().moverX(0);
                super.setL(false);
                super.setR(false);
                break;
        }
        switch(movY){
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
            case 3:
                super.getSprite().moverY(0);
                super.setUp(false);
                super.setDown(false);
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
        if (!colision.isPlayer() && colision.isProyectile() == 0 && tiempo > 5000) {
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
    
    public int signo(float num) {
        if(num >= 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
