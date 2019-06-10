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
public class Objeto implements IColisionable{
    private int vida, tipo;
    private Sprite sprite;
    private Rectangle hitbox;

    public Objeto(int tipo, int x, int y) throws SlickException {
        Image imagen;
        
        switch(tipo) {
            case 1:
               imagen = new Image("/resources/varitas/varita_normal.png"); 
               break;
            case 2:
               imagen = new Image("/resources/varitas/varita_1.png"); 
               break;
            case 3:
                imagen = new Image("/resources/varitas/varita_2.png"); 
                break;
            case 4:
                imagen = new Image("/resources/varitas/varita_3.png");
                break;
            case 5:
                imagen = new Image("/resources/objetos/botas.png");
                break;
            case 6:
                imagen = new Image("/resources/objetos/botas-rojo.png");
                break;
            case 7:
                imagen = new Image("/resources/objetos/pocion_1.png");
                break;
            case 8:
                imagen = new Image("/resources/objetos/pocion_2.png");
                break;
            case 9:
                imagen = new Image("/resources/objetos/corazon-lleno.png");
                break;
            default:
                imagen = null;
                break;
        }
        
        this.tipo = tipo;
        this.vida = 99;
        this.sprite = new Sprite(imagen, x, y, 1);
        this.hitbox = this.hitbox = new Rectangle(x, y, imagen.getWidth(), imagen.getHeight());
    }
    
    public void draw() {
        if(vida > 0) {
            sprite.draw();
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
    public void alColisionar(IColisionable colision, int delta) {
        if(colision.isPlayer() && colision.isProyectile() == 0) {
           vida = 0; 
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
        return sprite.getPosicion();
    }
    
    public void setPosicion(float x, float y) {
        sprite.setPosicion(x, y);
    }

    @Override
    public int isObjeto() {
        return tipo;
    }
    
    @Override
    public boolean isWall() {
        return false;
    }
}
