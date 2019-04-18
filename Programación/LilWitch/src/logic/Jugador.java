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

    public Jugador() throws SlickException {
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
        personaje = new SpriteAnimado(animaciones, tileSet.getSprite(0, 0), 1000, 500);
        hitbox = new Rectangle(personaje.getPosicion().getX(), personaje.getPosicion().getY(), personaje.getQuieto().getWidth(), personaje.getQuieto().getHeight());
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
        else {
            personaje.draw();
        }
    }
    
    public void update(Input entrada) {
        updateTeclado(entrada);
    }
    
    private void updateTeclado(Input entrada) {
        if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(-0.25f);
            personaje.startL();
            personaje.stopR();
            personaje.stopUp();
            personaje.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(0.25f);
            personaje.stopL();
            personaje.startR();
            personaje.stopUp();
            personaje.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverY(-0.25f);
            personaje.stopL();
            personaje.stopR();
            personaje.startUp();
            personaje.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverY(0.25f);
            personaje.stopL();
            personaje.stopR();
            personaje.stopUp();
            personaje.startDown();
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(-0.25f);
            personaje.moverY(-0.25f);
            personaje.startL();
            personaje.stopR();
            personaje.stopUp();
            personaje.stopDown();
            
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(-0.25f);
            personaje.moverY(0.25f);
            personaje.startL();
            personaje.stopR();
            personaje.stopUp();
            personaje.stopDown();
            
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(0.25f);
            personaje.moverY(-0.25f);
            personaje.stopL();
            personaje.startR();
            personaje.stopUp();
            personaje.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            personaje.moverX(0.25f);
            personaje.moverY(0.25f);
            personaje.stopL();
            personaje.startR();
            personaje.stopUp();
            personaje.stopDown();
        }
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sincronizarArea() {
        hitbox.setX(personaje.getPosicion().getX());
        hitbox.setY(personaje.getPosicion().getY());
    }
}
