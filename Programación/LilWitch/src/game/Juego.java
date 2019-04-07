package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import logic.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author alvar
 */
public class Juego extends BasicGameState{
    
    private SpriteAnimado link;
    private SpriteSheet tileSet;
    private Input entrada;
    Image[] i1 = new Image[10];
    Image[] i2 = new Image[10];
    Image[] i3 = new Image[10];
    Image[] i4 = new Image[10];
    Animation up, down, l, r;

    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        entrada = container.getInput();
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
        link = new SpriteAnimado(animaciones, tileSet.getSprite(0, 0), 1000, 500);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(entrada.isKeyDown(Input.KEY_LEFT)) {
            link.drawL();
        }
        else if(entrada.isKeyDown(Input.KEY_RIGHT)) {
            link.drawR();
        }
        else if(entrada.isKeyDown(Input.KEY_UP)) {
            link.drawUp();
        }
        else if(entrada.isKeyDown(Input.KEY_DOWN)) {
           link.drawDown();
        }
        else {
            link.draw();
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        updateTeclado();
    }
    
    private void updateTeclado() {
        if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(-0.25f);
            link.startL();
            link.stopR();
            link.stopUp();
            link.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(0.25f);
            link.stopL();
            link.startR();
            link.stopUp();
            link.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverY(-0.25f);
            link.stopL();
            link.stopR();
            link.startUp();
            link.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverY(0.25f);
            link.stopL();
            link.stopR();
            link.stopUp();
            link.startDown();
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(-0.25f);
            link.moverY(-0.25f);
            link.startL();
            link.stopR();
            link.stopUp();
            link.stopDown();
            
        }
        else if(entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(-0.25f);
            link.moverY(0.25f);
            link.startL();
            link.stopR();
            link.stopUp();
            link.stopDown();
            
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(0.25f);
            link.moverY(-0.25f);
            link.stopL();
            link.startR();
            link.stopUp();
            link.stopDown();
        }
        else if(!entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT) && !entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN)) {
            link.moverX(0.25f);
            link.moverY(0.25f);
            link.stopL();
            link.startR();
            link.stopUp();
            link.stopDown();
        }
    }
}
