/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 *
 * @author alvar
 */
public class Jugador implements IColisionable {
    private SpriteAnimado personaje;
    private Rectangle hitbox;
    private boolean up, down, r, l, stop;
    private ControladorProyectiles proyectiles;
    private int vida;

    public Jugador(ControladorProyectiles proyectiles) throws SlickException {
        SpriteSheet tileSet;
        Image[] i1 = new Image[10];
        Image[] i2 = new Image[10];
        Image[] i3 = new Image[10];
        Image[] i4 = new Image[10];
        Animation up, down, l, r;
        tileSet = new SpriteSheet("resources/minish.png", 96, 104);
        for (int i = 0; i < 10; i++) {
            i1[i] = tileSet.getSprite(i, 4);
            i2[i] = tileSet.getSprite(i, 5);
            i3[i] = tileSet.getSprite(i, 6);
            i4[i] = tileSet.getSprite(i, 7);
        }
        up = new Animation(i3, 100);
        down = new Animation(i1, 100);
        l = new Animation(i2, 100);
        r = new Animation(i4, 100);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        
        personaje = new SpriteAnimado(animaciones, tileSet.getSprite(0, 0), tileSet.getSprite(0, 2), tileSet.getSprite(0, 3), tileSet.getSprite(0, 1), 1000, 400);
        hitbox = new Rectangle(personaje.getPosicion().getX(), personaje.getPosicion().getY(), personaje.getStaticDown().getWidth()-40, personaje.getStaticDown().getHeight()-40);
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.stop = false;
        this.proyectiles = proyectiles;
        vida = 6;
    }
    
    public void draw(Input entrada) {
        if(entrada.isKeyDown(Input.KEY_LEFT)) {
            personaje.drawL();
        }
        else if(entrada.isKeyDown(Input.KEY_RIGHT)) {
            personaje.drawR();
        }
        else if(entrada.isKeyDown(Input.KEY_UP)) {
            personaje.drawUp();
        }
        else if(entrada.isKeyDown(Input.KEY_DOWN)) {
           personaje.drawDown();
        }
        else if(r) {
            personaje.drawStaticR();
        }
        else if(l) {
            personaje.drawStaticL();
        }
        else if(up) {
            personaje.drawStaticUp();
        }
        else {
            personaje.draw();
        }
    }
    
    public void update(Input entrada) {
        updateTeclado(entrada);
        sincronizarArea();
    }
    
    private void updateTeclado(Input entrada) {
        if(!stop){
            if(entrada.isKeyPressed(Input.KEY_SPACE)) {
                atacar();
            }
            if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-0.25f*3);
                } 
                else {
                    personaje.moverX(-0.25f);
                }
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = false;
                this.l = true;
            }
            
            else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(0.25f*3);
                } 
                else {
                    personaje.moverX(0.25f);
                }
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = true;
                this.l = false;
            }
        
            else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverY(-0.25f*3);
                }
                else {
                    personaje.moverY(-0.25f);   
                }
                personaje.stopL();
                personaje.stopR();
                personaje.startUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = false;
            }
        
            else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverY(0.25f*3);
                }
                else{
                    personaje.moverY(0.25f);
                }
                personaje.stopL();
                personaje.stopR();
                personaje.stopUp();
                personaje.startDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = false;
            }
        
            else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba-Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-0.25f*3);
                    personaje.moverY(-0.25f*3);
                }
                else {
                    personaje.moverX(-0.25f);
                    personaje.moverY(-0.25f);
                }
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = true;
            }
        
            else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo-Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-0.25f*3);
                    personaje.moverY(0.25f*3);
                }
                else{
                    personaje.moverX(-0.25f);
                    personaje.moverY(0.25f);
                }
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = true;
            }
            
            else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba-Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(0.25f*3);
                    personaje.moverY(-0.25f*3);
                }
                else{
                    personaje.moverX(0.25f);
                    personaje.moverY(-0.25f);
                }
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = true;
                this.l = false;
            }
        
            else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo-Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(0.25f*3);
                    personaje.moverY(0.25f*3);
                }
                else {
                    personaje.moverX(0.25f);
                    personaje.moverY(0.25f);
                }
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = true;
                this.l = false;
            }
        }
    }
    
    public void atacar() {
        float x = personaje.getPosicion().getX();
        float y = personaje.getPosicion().getY();
        float vX = 0;
        float vY = 0;
        if(r) {
            x += personaje.getStaticDown().getWidth();
            y += (personaje.getStaticDown().getHeight() / 2) - 36;
            vX = 200;
        }
        else if(l) {
            x -= 58;
            y += (personaje.getStaticDown().getHeight() / 2) - 36;
            vX = -200;
        }
        else if(up) {
            x += (personaje.getStaticDown().getWidth() / 2) - 29;
            y -= 72;
            vY = -200;
        }
        else {
            x += (personaje.getStaticDown().getWidth() / 2) - 29;
            y += personaje.getStaticDown().getHeight();
            vY = 200;     
        }  
        proyectiles.addProyectil(x, y, vX, vY);
    }
    
    public void resetDirecciones() {
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(!colision.isGate()) {
            if(up) {
                personaje.moverY(1f);
            }
            if(down) {
                personaje.moverY(-1f);
            }
            if(r) {
                personaje.moverX(-1f);
            }
            if(l) {
                personaje.moverX(1f);
            }
        }
        else {
            this.stop = true;
            switch(colision.getDir()) {
                case 0:
                    personaje.setPosicion(2000, 2000);
                    personaje.setPosicion(900, 840);
                    this.up = true;
                    this.down = false;
                    this.r = false;
                    this.l = false;
                    break;
                case 1:
                    personaje.setPosicion(2000, 2000);
                    personaje.setPosicion(10, 420);
                    this.up = false;
                    this.down = false;
                    this.r = true;
                    this.l = false;
                    break;
                case 2:
                    personaje.setPosicion(2000, 2000);
                    personaje.setPosicion(900, 10);
                    this.up = false;
                    this.down = true;
                    this.r = false;
                    this.l = false;
                    break;
                case 3:
                    personaje.setPosicion(2000, 2000);
                    personaje.setPosicion(1800, 420);
                    this.up = false;
                    this.down = false;
                    this.r = false;
                    this.l = true;
                    break;
            }
            this.stop = false;
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
    @Override
    public void sincronizarArea() {
        hitbox.setX(personaje.getPosicion().getX()+20);
        hitbox.setY(personaje.getPosicion().getY()+40);
    }

    @Override
    public boolean isHostile() {
        return false;
    }

    @Override
    public boolean isGate() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isProyectile() {
        return false;
    }
    
    @Override
    public int getDir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSalaDestino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
