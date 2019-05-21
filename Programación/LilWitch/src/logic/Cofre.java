/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public class Cofre implements IColisionable {
    private int vida;
    private Sprite spriteCerrado, spriteAbierto;
    private Rectangle hitbox;
    private boolean abierto;
    private Objeto contenido;

    public Cofre(Objeto contenido, float x, float y) throws SlickException {
        Image imagenAbierto, imagenCerrado;
        imagenAbierto = new Image("/resources/objetos/cofreAbierto.png");
        imagenCerrado = new Image("/resources/objetos/cofreCerrado.png");
        this.spriteAbierto = new Sprite(imagenAbierto, x, y, 1);
        this.spriteCerrado = new Sprite(imagenCerrado, x, y, 1);
        this.hitbox = new Rectangle(x, y, imagenCerrado.getWidth(), imagenCerrado.getHeight());
        
        this.vida = 99;
        this.contenido = contenido;
        this.abierto = false;
        this.hitbox = hitbox;
    }
    
    public void draw() {
        if(abierto) {
            spriteAbierto.draw();
        }
        else {
            spriteCerrado.draw();
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
}
