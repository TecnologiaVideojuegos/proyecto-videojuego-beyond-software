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
public class JugadorPreuba implements IColisionable {
    private SpriteAnimado personaje;
    private Rectangle hitbox;
    private boolean up, down, r, l, colisionUp, colisionDown, colisionR, colisionL;

    public JugadorPreuba() throws SlickException {
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
        
        this.personaje = new SpriteAnimado(animaciones, tileSet.getSprite(0, 0), tileSet.getSprite(0, 2), tileSet.getSprite(0, 3), tileSet.getSprite(0, 1), 1000, 400);
        this.hitbox = new Rectangle(personaje.getPosicion().getX(), personaje.getPosicion().getY(), personaje.getStaticDown().getWidth(), personaje.getStaticDown().getHeight());
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.colisionUp = false;
        this.colisionDown = false;
        this.colisionR = false;
        this.colisionL = false;
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
        if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Izquierda
            if(!colisionL) {
                personaje.moverX(-0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = false;
                this.l = true;
            }
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Derecha
            if(!colisionR) {
                personaje.moverX(0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = true;
                this.l = false;
            }
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba
            if(!colisionUp) {
                personaje.moverY(-0.25f);
                personaje.stopL();
                personaje.stopR();
                personaje.startUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = false;
            }
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo
            if(!colisionDown) {
                personaje.moverY(0.25f);
                personaje.stopL();
                personaje.stopR();
                personaje.stopUp();
                personaje.startDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = false;
            }
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba-Izquierda
            if(!colisionUp && !colisionL) {
                personaje.moverX(-0.25f);
                personaje.moverY(-0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = true;
            }
            else if(colisionUp) {
                personaje.moverX(-0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = false;
                this.l = true;
            }
            else if(colisionL) {
                personaje.moverY(-0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = false;
            }
            
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo-Izquierda
            if(!colisionDown && !colisionL) {
                personaje.moverX(-0.25f);
                personaje.moverY(0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = true;
            }
            else if(colisionDown) {
                personaje.moverX(0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = false;
                this.l = true;
            }
            else if(colisionL) {
                personaje.moverY(-0.25f);
                personaje.startL();
                personaje.stopR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = false;
            }
            
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) { //Arriba-Derecha
            if(!colisionUp && !colisionL) {
                personaje.moverX(0.25f);
                personaje.moverY(-0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = true;
                this.l = false;
            }
            else if(colisionUp) {
                personaje.moverX(0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = true;
                this.l = false;
            }
            else if(colisionR) {
                personaje.moverY(-0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = true;
                this.down = false;
                this.r = false;
                this.l = false;
            }
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) { //Abajo-Derecha
            if(!colisionDown && !colisionL) {
                personaje.moverX(0.25f);
                personaje.moverY(0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = true;
                this.l = false;
            }
            else if(colisionDown) {
                personaje.moverX(0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = false;
                this.r = true;
                this.l = false;
            }
            else if(colisionL) {
                personaje.moverY(0.25f);
                personaje.stopL();
                personaje.startR();
                personaje.stopUp();
                personaje.stopDown();
                this.up = false;
                this.down = true;
                this.r = false;
                this.l = false;
            }
        }
    }
    
    public void resetDirecciones() {
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
    }
    
    public void resetColisiones() {
        this.colisionUp = false;
        this.colisionDown = false;
        this.colisionR = false;
        this.colisionL = false;
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(up) {
            this.colisionUp = true;
        }
        else if(down) {
            this.colisionDown = true;
        }
        else if(r) {
            this.colisionR = true;
        }
        else if(l) {
            this.colisionL = true;
        }
    }

    @Override
    public void sincronizarArea() {
        hitbox.setX(personaje.getPosicion().getX());
        hitbox.setY(personaje.getPosicion().getY());
    }

    @Override
    public boolean isHostile() {
        return false;
    }

    public SpriteAnimado getPersonaje() {
        return personaje;
    }

    public void setPersonaje(SpriteAnimado personaje) {
        this.personaje = personaje;
    }
    
    @Override
    public boolean isGate() {
        return false;
    }
    
    @Override
    public boolean isPlayer() {
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
