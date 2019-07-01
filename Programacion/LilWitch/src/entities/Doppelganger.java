/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import logic.ControladorAnimacion;
import logic.ControladorProyectiles;
import collisions.IColisionable;
import logic.SpriteAnimado;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author alvar
 */
public class Doppelganger extends Boss {
    private Jugador player;
    private int movX, movY, dirXo, dirYo, tiempo, numColisiones, eleccion, eleccion2, contador, posSombra, contadorAtaque, eleccionBarrera, eleccionCirculo;
    private boolean primerTurno, saltando, start, subiendo, primerTurnoF;
    private Animation sombra;
    private ControladorProyectiles proyectiles;

    public Doppelganger(Jugador player, ControladorProyectiles proyectiles) throws SlickException {
        super("Anaid", "boss_final.png", 96, 104, 840, 120, 300, 2, 250, player, 20, 15, 40, 15);
        SpriteSheet s;
        Image[] i1 = new Image[10];
        Image[] i2 = new Image[10];
        Image[] i3 = new Image[10];
        Image[] i4 = new Image[10];
        Animation up, down, l, r;
        s = new SpriteSheet("resources/enemigos/boss_final.png", 96, 104);
        for (int i = 0; i < 10; i++) {
            i1[i] = s.getSprite(i, 4);
            i2[i] = s.getSprite(i, 5);
            i3[i] = s.getSprite(i, 6);
            i4[i] = s.getSprite(i, 7);
        }
        up = new Animation(i3, 100);
        down = new Animation(i1, 100);
        l = new Animation(i2, 100);
        r = new Animation(i4, 100);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        super.setSprite(new SpriteAnimado(animaciones, s.getSprite(0, 0), s.getSprite(0, 2), s.getSprite(0, 3), s.getSprite(0, 1), 840, 120));
        super.setHitbox(new Rectangle(840 + super.getOffsetX(), 120 + super.getOffsetY(), 96 - super.getOffsetWidth(), 104 - super.getOffsetHeight()));
        this.player = player;
        this.eleccion = (int) (Math.random() * 4+1);
        this.eleccionBarrera = 0;
        this.eleccionCirculo = 1;
        this.eleccion2 = 0;
        this.dirXo = 0;
        this.dirYo = 0;
        this.tiempo = 0;
        this.primerTurno = true;
        this.primerTurnoF = true;
        this.numColisiones = 0;
        
        SpriteSheet tileSet;
        Image[] img = new Image[5];
        tileSet = new SpriteSheet("resources/sprites/Sombra.png", 330, 330);
        for (int i = 0; i < 5; i++) {
            img[i] = tileSet.getSprite(i, 0);
        }
        
        this.sombra = new Animation(img, 150);
        this.sombra.setLooping(false);
        this.saltando = false;
        this.contador = 0;
        this.proyectiles = proyectiles;
        this.start = true;
        this.posSombra = 5;
        this.contadorAtaque = 0;
        this.subiendo = true;
    }

    @Override
    public void draw(Graphics g) {
        if(super.getPorcentajeVida() > 25) {
            super.draw(g);
        }
        else {
            super.draw(g, 4);
        } 
        if(saltando) {
            switch(posSombra) {
                case 1:
                    sombra.draw(50, 50, 110, 110);
                    break;
                case 2:
                    sombra.draw(21, 825, 110, 110);
                    break;
                case 3:
                    sombra.draw(1850 - super.getHitbox().getWidth(), 21, 110, 110);
                    break;
                case 4:
                    sombra.draw(1850 - super.getHitbox().getWidth(), 825, 110, 110);
                    break;
                case 5:
                    sombra.draw(900, 450, 110, 110);
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
        if(super.getPorcentajeVida() > 25) {
            if(tiempo < 9000) {
                if(!start && tiempo < 4000) {
                    if(super.getCooldown() > 500) {
                        disparoCircular();   
                        cambiarEleccionCirculo();
                        super.setCooldown(0);
                    }
                }
                if(tiempo > 4000 || start) {
                    perseguir(delta);
                    lanzarHechizo();
                    if(eleccion == 3 || eleccion == 4) posSombra = 1;
                }
            }
            else if(eleccion == 1 && numColisiones < 3) {
                ataque1(delta);

            }
            else if(eleccion == 2 && numColisiones < 3) {
                ataque2(delta);
            }
            else if(eleccion == 3 || eleccion == 4) {
                if(eleccion == 3) {
                    ataque3(delta);
                }
                else {
                    ataque4(delta);
                }
                tiempo += delta;
                if(tiempo > 12000) {
                    primerTurno = true;
                    tiempo = 9001;
                    if(eleccion2 == 4) {
                        eleccion = 99;
                    }
                }
            }
            else {
                if(start) {
                    start = false;
                }
                if(contador == 0) {
                    posSombra = 5;
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
                    numColisiones = 0;
                    tiempo = 0;   
                    numColisiones = 0;
                    super.getSprite().setPosicion(900, 450);
                    eleccion = (int) (Math.random() * 4+1);
                    eleccion2 = 0;  
                    eleccionCirculo = 1;
                    //disparoCircular();
                }
            }
            updateAnimacion();
        }
        else {
            if(primerTurnoF) {
                if(contador == 0) {
                    super.setCooldown(1001);
                    posSombra = 5;
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
                    numColisiones = 0;
                    tiempo = 0;
                    numColisiones = 0;
                    super.getSprite().setPosicion(900, 450);
                    primerTurnoF = false;
                }
            }
            else {
                if(super.getCooldown() > 1000) {
                    disparoBarreraR();
                    disparoBarreraL();
                    cambiarEleccionBarrera();
                    super.setCooldown(0);
                }
            }
        }
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
                super.getSprite().setPosicion(100, 100);
                super.resetDirecciones();
                primerTurno = false;
                disparoDireccional(6, 100, 100);
            }  
        }
        if(super.isColision()) {
            if(super.isUp()) {
                movY = 2;
                disparoDireccional(4, 1800, 100);
            }
            if(super.isDown()) {
                movY = 1;
                disparoDireccional(1, 960, 820);
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
                disparoDireccional(4, 1800, 100);
            }  
        }
        if(super.isColision()) {
            if(super.isUp()) {
                movY = 2;
                disparoDireccional(6, 100, 100);
            }
            if(super.isDown()) {
                movY = 1;
                disparoDireccional(1, 960, 820);
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
    
    public void ataque3(int delta) {
        if(primerTurno) {
            if(contador == 0) {
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                super.resetDirecciones();
                sombra.restart();
                saltando = false;
                super.setColision(false);
                if(eleccion2 == 0) {
                    super.getSprite().setPosicion(50, 50);
                    disparoDireccional(6, 100, 100);
                    posSombra = 3;
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 1) {
                    super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 21);
                    disparoDireccional(4, 1800, 100);
                    posSombra = 4;
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 2) {
                    super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 825);
                    disparoDireccional(2, 1800, 820);
                    posSombra = 2;
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 3) {
                    super.getSprite().setPosicion(21, 825);
                    disparoDireccional(8, 100, 820);
                    contador = 0;
                    eleccion2 ++;
                }
       
                primerTurno = false;
            }
        }
    }
    
    public void ataque4(int delta) {
        if(primerTurno) {
            if(contador == 0) {
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                super.resetDirecciones();
                sombra.restart();
                saltando = false;
                super.setColision(false);
                if(eleccion2 == 0) {
                    super.getSprite().setPosicion(50, 50);
                    disparoDireccional(6, 100, 100);
                    posSombra = 2;
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 3) {
                    super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 21);
                    disparoDireccional(4, 1800, 100);
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 2) {
                    super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 825);
                    disparoDireccional(2, 1800, 820);
                    posSombra = 3;
                    contador = 0;
                    eleccion2 ++;
                }
                else if(eleccion2 == 1) {
                    super.getSprite().setPosicion(21, 825);
                    disparoDireccional(8, 100, 820);
                    posSombra = 4;
                    contador = 0;
                    eleccion2 ++;
                }
       
                primerTurno = false;
            }
        }
    }
    
    public void lanzarHechizo() {
        if (super.getCooldown() > 1000) {
                float x = super.getHitbox().getCenterX();
                float y = super.getHitbox().getCenterY(); 
                int dirX = signo((int) (player.getPosicion().getX() + 48 - x));
                int dirY = signo((int) (player.getPosicion().getY() + 52 - y));
                
                if(dirY != 0 && dirX == 0) {
                    x -= 29;
                }
                if(dirX != 0 && dirY == 0) {
                    y -= 36;
                }
                
                crearProyectil(x, y, dirX, dirY);
                super.setCooldown(0);
            }
    }
    
    public void disparoCircular() {
            float x = super.getSprite().getPosicion().getX() + 48 - 58;
            float y = super.getSprite().getPosicion().getY() + 52 - 72;
            if(eleccionCirculo == 1) {
                crearProyectilG(x, y, 0, -1);  
                crearProyectilG(x, y, -1, 0);  
                crearProyectilG(x, y, 0, 1);  
                crearProyectilG(x, y, 1, 0);  
            }
            else if(eleccionCirculo == 2) {
                crearProyectilG(x, y, -1, -1);
                crearProyectilG(x, y, -1, 1);
                crearProyectilG(x, y, 1, 1); 
                crearProyectilG(x, y, 1, -1); 
            }
    }
    
    public void disparoDireccional(int tipo) {
        /*  1 -> UP
            2 -> UP-L
            3 -> L
            4 -> DOWN-L
            5 -> DOWN
            6 -> DOWN-R
            7 -> R
            8 -> UP-R
        */
        float x = super.getSprite().getPosicion().getX() + 48 - 58;
        float y = super.getSprite().getPosicion().getY() + 52 - 72;
        switch(tipo) {
            case 1: 
                crearProyectilG(x, y, -1, -1);
                crearProyectilG(x, y, 0, -1);   
                crearProyectilG(x, y, 1, -1);
                break;
            case 2:
                crearProyectilG(x, y, -1, -1);
                crearProyectilG(x, y, 0, -1);   
                crearProyectilG(x, y, -1, 0);
                break;
            case 3: 
                crearProyectilG(x, y, -1, -1);
                crearProyectilG(x, y, -1, 0);
                crearProyectilG(x, y, -1, 1);   
                break;
            case 4:
                crearProyectilG(x, y, -1, 0);    
                crearProyectilG(x, y, -1, 1);
                crearProyectilG(x, y, 0, 1);
                break;
            case 5:
                crearProyectilG(x, y, -1, 1);    
                crearProyectilG(x, y, 0, 1);
                crearProyectilG(x, y, 1, 1);
                break;
            case 6:
                crearProyectilG(x, y, 0, 1);    
                crearProyectilG(x, y, 1, 1);
                crearProyectilG(x, y, 1, 0);
                break;
            case 7:
                crearProyectilG(x, y, 1, -1);    
                crearProyectilG(x, y, 1, 0);
                crearProyectilG(x, y, 1, 1);
                break;
            case 8:
                crearProyectilG(x, y, 0, -1);    
                crearProyectilG(x, y, 1, -1);
                crearProyectilG(x, y, 1, 0);
                break;
        }
    }
    
    public void disparoDireccional(int tipo, float posX, float posY) {
        /*  1 -> UP
            2 -> UP-L
            3 -> L
            4 -> DOWN-L
            5 -> DOWN
            6 -> DOWN-R
            7 -> R
            8 -> UP-R
        */
        float x = posX - 58;
        float y = posY - 72;
        switch(tipo) {
            case 1: 
                crearProyectilG2(x, y, -1, -1);
                crearProyectilG2(x, y, 0, -1);   
                crearProyectilG2(x, y, 1, -1);
                break;
            case 2:
                crearProyectilG2(x, y, -1, -1);
                crearProyectilG2(x, y, 0, -1);   
                crearProyectilG2(x, y, -1, 0);
                break;
            case 3: 
                crearProyectilG2(x, y, -1, -1);
                crearProyectilG2(x, y, -1, 0);
                crearProyectilG2(x, y, -1, 1);   
                break;
            case 4:
                crearProyectilG2(x, y, -1, 0);    
                crearProyectilG2(x, y, -1, 1);
                crearProyectilG2(x, y, 0, 1);
                break;
            case 5:
                crearProyectilG2(x, y, -1, 1);    
                crearProyectilG2(x, y, 0, 1);
                crearProyectilG2(x, y, 1, 1);
                break;
            case 6:
                crearProyectilG2(x, y, 0, 1);    
                crearProyectilG2(x, y, 1, 1);
                crearProyectilG2(x, y, 1, 0);
                break;
            case 7:
                crearProyectilG2(x, y, 1, -1);    
                crearProyectilG2(x, y, 1, 0);
                crearProyectilG2(x, y, 1, 1);
                break;
            case 8:
                crearProyectilG2(x, y, 0, -1);    
                crearProyectilG2(x, y, 1, -1);
                crearProyectilG2(x, y, 1, 0);
                break;
        }
    }
    
    public void disparoBarreraR() {
        float x = super.getSprite().getPosicion().getX() + super.getHitbox().getWidth();
 
        for (int i = 0; i < 9; i++) {
            if(i != eleccionBarrera && i+1 != eleccionBarrera) {
                crearProyectilLento(x, 45 + 100*i, 1, 0); 
            }
        }
    }
    
    public void disparoBarreraL() {
        float x = super.getSprite().getPosicion().getX();
        
        for (int i = 0; i < 9; i++) {
            if(i != eleccionBarrera && i+1 != eleccionBarrera) {
                crearProyectilLento(x, 45 + 100*i, -1, 0); 
            }
        }
    }
    
    public void crearProyectil(float x, float y, float dirX, float dirY) {
        proyectiles.addProyectil("Fire_5.png", x, y, 58, 72, 1f, 500*dirX, 500*dirY, 2, 1);
    }
    public void crearProyectilLento(float x, float y, float dirX, float dirY) {
        proyectiles.addProyectil("Fire_5.png", x, y, 58, 72, 1f, 300*dirX, 300*dirY, 2, 1);
    }
    
    public void crearProyectilG(float x, float y, float dirX, float dirY) {
        proyectiles.addProyectil("Fire_4.png", x, y, 114, 144, 1f, 300*dirX, 300*dirY, 2, 1);
    }
    
    public void crearProyectilG2(float x, float y, float dirX, float dirY) {
        proyectiles.addProyectil("Fire_4.png", x, y, 114, 144, 1f, 500*dirX, 500*dirY, 2, 1);
    }
    
    @Override
    public void avanzar(int delta) {
        switch(movX){
            case 1:
                super.getSprite().moverX(-super.getVelocidad() * 2 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(super.getVelocidad() * 2 * ((float) delta / 1000));
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
                super.getSprite().moverY(-super.getVelocidad() * 2 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(super.getVelocidad() * 2 * ((float) delta / 1000));
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
        if (!colision.isPlayer() && colision.isProyectile() == 0 && tiempo > 7000) {
            numColisiones += 1;
        }
    }
    
    public int signo(int num) {
        if(num > 0) {
            return 1;
        }
        else if(num == 0) {
            return 0;
        }
        else {
            return -1;
        }
    }
    
    public int signo(float num) {
        return signo((int) num);
    }
    
    public void cambiarEleccionBarrera() {
        /*if(subiendo) {
            eleccionBarrera ++;
        }
        else {
            eleccionBarrera --;
        }
        if(eleccionBarrera == 9) {
            eleccionBarrera --;
            subiendo = false;
        }
        else if(eleccionBarrera == -1) {
            eleccionBarrera ++;
            subiendo = true;
        }*/
        eleccionBarrera = (int) (Math.random() * 7);
        /*if(eleccionBarrera == 0) {
            eleccionBarrera = 8;
        }
        else {
            eleccionBarrera = 0;
        }*/
    }
    
    public void cambiarEleccionCirculo() {
        if(eleccionCirculo == 1) {
            eleccionCirculo = 2;
        }
        else if(eleccionCirculo == 2){
            eleccionCirculo = 1;
        }
    }
}
