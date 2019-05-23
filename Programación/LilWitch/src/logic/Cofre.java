/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public class Cofre implements IColisionable {
    private int vida, delay;
    private Sprite spriteCerrado, spriteAbierto;
    private Rectangle hitbox;
    private boolean abierto;
    private Objeto contenido;

    public Cofre(Objeto contenido, float x, float y, int tipo) throws SlickException {
        String filename;
        switch(tipo) {
            case 0:
               filename = "cofre_1.png"; 
               break;
            case 1:
                filename = "cofre_2.png";
                break;
            case 2:
                filename = "cofre_3.png";
                break;
            case 3:
                filename = "cofre_4.png";
                break;
            case 4:
                filename = "cofre_5.png";
                break;
            default:
                filename = "cofre_5.png";
                break;
        }
        
        Image imagenAbierto, imagenCerrado;
        SpriteSheet sprites = new SpriteSheet("resources/objetos/" + filename, 120, 120);
        imagenAbierto = sprites.getSprite(0, 1);
        imagenCerrado = sprites.getSprite(0, 0);
        
        this.spriteAbierto = new Sprite(imagenAbierto, x, y, 1);
        this.spriteCerrado = new Sprite(imagenCerrado, x, y, 1);
        this.hitbox = new Rectangle(x, y, imagenCerrado.getWidth(), imagenCerrado.getHeight());
        
        this.vida = 99;
        this.contenido = contenido;
        this.abierto = false;
        this.hitbox = hitbox;
        this.delay = 0;
    }
    
    public void draw() {
        if(abierto) {
            spriteAbierto.draw();
        }
        else {
            spriteCerrado.draw();
            if(delay < 5) {
                contenido.draw();
            }
            delay++;
        }
    }

    @Override
    public Shape getHitbox() {
        return hitbox;
    }

    @Override
    public Shape getVisionRange() {
        return hitbox;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(colision.isPlayer() && colision.isProyectile() == 0) {
           abierto = true;
           contenido.setPosicion(spriteAbierto.getPosicion().getX(), spriteAbierto.getPosicion().getY() - 20);
        }
    }

    @Override
    public void alDetectar(IColisionable colision) {}

    @Override
    public void sincronizarArea() {}

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
        return false;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    @Override
    public int isProyectile() {
        return 0;
    }

    @Override
    public int getDir() {
        return 0;
    }

    @Override
    public int getSalaDestino() {
        return 0;
    }

    @Override
    public int getAtaque() {
        return 0;
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public Punto getPosicion() {
        return spriteCerrado.getPosicion();
    }
    
    @Override
    public int isObjeto() {
        return contenido.isObjeto();
    }
}
