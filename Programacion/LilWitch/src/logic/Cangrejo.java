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
public class Cangrejo extends Boss {
    private Jugador player;
    private int movX, movY, dirXo, dirYo, tiempo, numColisiones, eleccion, eleccion2, contador, contadorAtaque, vidaCaparazon, tiempoVulnerable, caparazonRoto, tiempoAtaque, posSombra;
    private boolean primerTurno, saltando;
    private Animation sombra;
    private SpriteAnimado spriteSecundario;

    public Cangrejo(Jugador player) throws SlickException {
        super("Aequor, Guardián de los Mares", "cangrejo.png", 330, 330, 840, 120, 100, 1, 200, player, 30, 30, 60, 35);
        this.player = player;
        this.eleccion = (int) (Math.random() * 4+1);
        this.dirXo = 0;
        this.dirYo = 0;
        this.tiempo = 0;
        this.primerTurno = true;
        this.numColisiones = 0;
        this.saltando = false;
        this.contador = 0;
        this.vidaCaparazon = 4;
        this.tiempoVulnerable = 0;
        this.caparazonRoto = 0;
        this.tiempoAtaque = 0;
        this.contadorAtaque = 0;
        this.posSombra = 7;
        
        SpriteSheet spritesSombra;
        spritesSombra = new SpriteSheet("resources/sprites/Sombra.png", 330, 330);
        this.sombra = new Animation(spritesSombra, 150);
        this.sombra.setLooping(false);
        
        SpriteSheet s;
        Animation up, down, l, r;
        s = new SpriteSheet("resources/enemigos/cangrejo_2.png", 330, 330);
        int numSprites = s.getHorizontalCount();
        
        Image[] i1 = new Image[numSprites];
        Image[] i2 = new Image[numSprites];
        Image[] i3 = new Image[numSprites];
        Image[] i4 = new Image[numSprites];
        
        for (int i = 0; i < numSprites; i++) {
            i1[i] = s.getSprite(i, 0);
            i2[i] = s.getSprite(i, 1);
            i3[i] = s.getSprite(i, 2);
            i4[i] = s.getSprite(i, 3);
        }
        
        up = new Animation(i1, 100);
        down = new Animation(i3, 100);
        l = new Animation(i4, 100);
        r = new Animation(i2, 100);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        
        this.spriteSecundario = new SpriteAnimado(animaciones, s.getSprite(1, 2), s.getSprite(1, 0), s.getSprite(1, 1), s.getSprite(1, 3), 840, 120);
        
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
                    sombra.draw(800, 21);
                    break;
                case 4:
                    sombra.draw(800, 900 - super.getHitbox().getHeight());
                    break;
                case 5:
                    sombra.draw(21, 350);
                    break; 
                case 6:
                    sombra.draw(1850 - super.getHitbox().getWidth(), 350);
                    break;   
                case 7:
                    sombra.draw(840, 120);
                    break;   
            }
        }
    }

    @Override
    public void sincronizarArea() {
        super.sincronizarArea(); 
        spriteSecundario.setPosicion(super.getSprite().getPosicion().getX() + super.getOffsetX(), super.getSprite().getPosicion().getY() + super.getOffsetY());
    }
    
    public void actualizarSprite() {
        SpriteAnimado aux = super.getSprite();
        super.setSprite(spriteSecundario);
        spriteSecundario = aux;
    }

    @Override
    public void update(int delta) {
        if(vidaCaparazon == 0) {
            tiempoVulnerable += delta;
            caparazonRoto ++;
            if(caparazonRoto == 1) {
                actualizarSprite();
            }
        }
        if(tiempoVulnerable > 8000) {
            vidaCaparazon = 4;
            caparazonRoto = 0;
            tiempoVulnerable = 0;
            actualizarSprite();
        }
        super.update(delta);
        tiempo += delta;
    }
    
    

    @Override
    public void atacar(int delta) {
        if(tiempo < 8000) {
            perseguir(delta);
        }
        else if(eleccion == 1) {
            ataque1(delta);
        }
        else if(eleccion == 2) {
            ataque2(delta);
        }
        else if((eleccion == 3 || eleccion == 4) && numColisiones < 2) {
            ataque3(delta);
        }
        else {
            if(contador == 0) {
                posSombra = 7;
                contador ++;
                super.getSprite().setPosicion(2000, 2000);
                sombra.start();
                saltando = true;
            }
            if(sombra.isStopped()) {
                sombra.restart();
                contador = 0;
                saltando = false;
                primerTurno = true;
                tiempo = 0;
                numColisiones = 0;
                super.getSprite().setPosicion(840, 120);
                eleccion = (int) (Math.random() * 4+1);
            }
        }
        updateAnimacion();
    }
    
    public void ataque1(int delta) {
        System.out.println("Num colisiones: " + numColisiones);
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
                movY = 3;
                super.getSprite().setPosicion(20, 20);
                super.resetDirecciones();
                primerTurno = false;
            }  
        }
        
        if(movX == 3) {
            tiempoAtaque += delta;
            if(tiempoAtaque > 500) {
                movX = 1;
                movY = 3;
                tiempoAtaque = 0;
            }
        }
        if(super.isColision()) {
            if(super.isR() || super.isL()) {
                movX = 3;
                movY = 2; 
            }
            if((super.isDown() || super.isUp()) && numColisiones > 2) {
                movX = 3;
                movY = 3;
                eleccion = 99;
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
                movY = 3;
                super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 20);
                super.resetDirecciones();
                primerTurno = false;
            }  
        }
        
        if(movX == 3) {
            tiempoAtaque += delta;
            if(tiempoAtaque > 500) {
                movX = 2;
                movY = 3;
                tiempoAtaque = 0;
            }
        }
        if(super.isColision()) {
            if(super.isR() || super.isL()) {
                movX = 3;
                movY = 2; 
            }
            else if((super.isDown() || super.isUp()) && numColisiones > 2) {
                movX = 3;
                movY = 3;
                eleccion = 99;
            }
            super.setColision(false);
        }
        avanzar(delta);
    }
    
    public void ataque3(int delta) {
        if(primerTurno) {
            if(contador == 0) {
                eleccion2 = (int) (Math.random() * 2+1);
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
                if(eleccion == 3) {
                    posSombra = 3;
                    super.getSprite().setPosicion(800, 21);
                    movX = 3;
                    movY = 2;
                }
                else {
                    posSombra = 4;
                    super.getSprite().setPosicion(800, 900 - super.getHitbox().getHeight());
                    movX = 3;
                    movY = 1;
                }
                super.resetDirecciones();
                primerTurno = false;  
            }     
        }
        
        if(super.isColision()) {
            if(super.isUp() || super.isDown()) {
                if(contador == 0) {
                    if(eleccion2 == 1) {
                        posSombra = 5;
                    }
                    else {
                        posSombra = 6;
                    }
                    contador ++;
                    super.getSprite().setPosicion(2000, 2000);
                    sombra.start();
                    saltando = true;
                } 
            }
            super.setColision(false);
        }
        if(sombra.isStopped()) {
            super.resetDirecciones();
            sombra.restart();
            contador = 0;
            saltando = false;
            super.setColision(false);
            if(eleccion2 == 1) {
                super.getSprite().setPosicion(21, 350);
                movX = 2;
                movY = 3;
            }
            else {
                super.getSprite().setPosicion(1850 - super.getHitbox().getWidth(), 350);
                movX = 1;
                movY = 3;
            }
            super.resetDirecciones();
            primerTurno = false;
        }  
        avanzar(delta);
    }
    
    @Override
    public void avanzar(int delta) {
        switch(movX){
            case 1:
                super.getSprite().moverX(-super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setL(true);
                super.setR(false);
                break;
            case 2:
                super.getSprite().moverX(super.getVelocidad() * 3 * ((float) delta / 1000));
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
                super.getSprite().moverY(-super.getVelocidad() * 3 * ((float) delta / 1000));
                super.setUp(true);
                super.setDown(false);
                break;
            case 2:
                super.getSprite().moverY(super.getVelocidad() * 3 * ((float) delta / 1000));
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
        if(colision.isProyectile() != 1) {
            if (!colision.isPlayer()) {
                super.setColision(true);
                if(super.isUp()) {
                    super.getSprite().moverY(super.getVelocidad() * 3 * (float) delta / 1000);
                }
                if(super.isDown()) {
                    super.getSprite().moverY(-super.getVelocidad() * 3 * (float) delta / 1000);
                }
                if(super.isR()) {
                    super.getSprite().moverX(-super.getVelocidad() * 3 * (float) delta / 1000);
                }
                if(super.isL()) {
                    super.getSprite().moverX(super.getVelocidad() * 3 * (float) delta / 1000);
                }
                if (tiempo > 8000) {
                    numColisiones += 1;
                }
            }
            if (colision.isProyectile() == 3 && vidaCaparazon != 0) {
                vidaCaparazon --;
                super.setHit(true);
            }
            if (colision.isProyectile() >= 2 && vidaCaparazon == 0) {
                super.setVida(super.getVida() - colision.getAtaque());
                super.setHit(true);
            }
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
