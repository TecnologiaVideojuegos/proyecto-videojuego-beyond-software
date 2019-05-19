/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 *
 * @author alvar
 */
abstract class Enemigo implements IColisionable {
    private SpriteAnimado sprite;
    private Rectangle hitbox, visionRange;;
    private boolean up, down, r, l, stop;
    private int vida, ataque, cooldown;
    private Punto playerPosition;

    public Enemigo(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        SpriteSheet tileSet;
        Animation up, down, l, r;
        tileSet = new SpriteSheet("resources/enemigos/" + filename, ancho, alto);
        int numSprites = tileSet.getHorizontalCount();
        Image[] i1 = new Image[numSprites];
        Image[] i2 = new Image[numSprites];
        Image[] i3 = new Image[numSprites];
        Image[] i4 = new Image[numSprites];
        
        for (int i = 0; i < numSprites; i++) {
            i1[i] = tileSet.getSprite(i, 0);
            i2[i] = tileSet.getSprite(i, 1);
            i3[i] = tileSet.getSprite(i, 2);
            i4[i] = tileSet.getSprite(i, 3);
        }
        
        up = new Animation(i1, 100);
        down = new Animation(i3, 100);
        l = new Animation(i4, 100);
        r = new Animation(i2, 100);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        
        SpriteAnimado sprite = new SpriteAnimado(animaciones, tileSet.getSprite(2, 1), tileSet.getSprite(0, 1), tileSet.getSprite(1, 1), tileSet.getSprite(4, 1), x, y);
        Rectangle hitbox = new Rectangle(sprite.getPosicion().getX(), sprite.getPosicion().getY(), sprite.getStaticDown().getWidth()-40, sprite.getStaticDown().getHeight()-40);
              
        this.sprite = sprite;
        this.hitbox = hitbox;
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.stop = false;
        this.vida = vida;
        this.ataque = ataque;
        this.cooldown = 500;
    }
    
    public Enemigo(String filename, int ancho, int alto, int x, int y, int distanciaVision, int vida, int ataque) throws SlickException {
        SpriteSheet tileSet;
        Animation up, down, l, r;
        tileSet = new SpriteSheet("resources/enemigos/" + filename, ancho, alto);
        int numSprites = tileSet.getHorizontalCount();
        Image[] i1 = new Image[numSprites];
        Image[] i2 = new Image[numSprites];
        Image[] i3 = new Image[numSprites];
        Image[] i4 = new Image[numSprites];
        
        for (int i = 0; i < numSprites; i++) {
            i1[i] = tileSet.getSprite(i, 0);
            i2[i] = tileSet.getSprite(i, 1);
            i3[i] = tileSet.getSprite(i, 2);
            i4[i] = tileSet.getSprite(i, 3);
        }
        
        up = new Animation(i1, 100);
        down = new Animation(i3, 100);
        l = new Animation(i4, 100);
        r = new Animation(i2, 100);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        
        SpriteAnimado sprite = new SpriteAnimado(animaciones, tileSet.getSprite(2, 1), tileSet.getSprite(0, 1), tileSet.getSprite(1, 1), tileSet.getSprite(4, 1), x, y);
        Rectangle hitbox = new Rectangle(sprite.getPosicion().getX(), sprite.getPosicion().getY(), sprite.getStaticDown().getWidth()-40, sprite.getStaticDown().getHeight()-40);
        
        this.visionRange = new Rectangle(hitbox.getX() - distanciaVision, hitbox.getY() - distanciaVision, 2*distanciaVision + hitbox.getWidth(), 2*distanciaVision + hitbox.getHeight());      
        this.sprite = sprite;
        this.hitbox = hitbox;
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.stop = false;
        this.vida = vida;
        this.ataque = ataque;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void draw() {
        if(r) {
            sprite.drawR();
        }
        else if(l) {
            sprite.drawL();
        }
        else if(up) {
            sprite.drawUp();
        }
        else {
            sprite.drawDown();
        }
    }
    
    public void update(int delta) {
        cooldown += delta;
        atacar();
        sincronizarArea();
    }
    
    abstract void atacar();
    abstract void avanzar();

    @Override
    public void alColisionar(IColisionable colision) {
        if(up) {
            sprite.moverY(1f);
        }
        if(down) {
            sprite.moverY(-1f);
        }
        if(r) {
            sprite.moverX(-1f);
        }
        if(l) {
            sprite.moverX(1f);
        }
        if (colision.isProyectile() == 2) {
            vida -= colision.getAtaque();
        }
    }

    public SpriteAnimado getSprite() {
        return sprite;
    }

    public void setSprite(SpriteAnimado sprite) {
        this.sprite = sprite;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isL() {
        return l;
    }

    public void setL(boolean l) {
        this.l = l;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    @Override
    public void sincronizarArea() {
        hitbox.setX(sprite.getPosicion().getX());
        hitbox.setY(sprite.getPosicion().getY());
    }

    @Override
    public boolean isHostile() {
        return true;
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
    public int isProyectile() {
        return 0;
    }
    
    @Override
    public int getDir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getSalaDestino() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public int getAtaque() {
        return ataque;
    } 
    
    @Override
    public Shape getVisionRange() {
        if(visionRange != null) {
            return visionRange;
        }
        else {
            return hitbox; 
        }
    }

    @Override
    public void alDetectar(IColisionable colision) {
        playerPosition = colision.getPosicion();
    }
    
    @Override
    public Punto getPosicion() {
        return sprite.getPosicion();
    }

    public Punto getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Punto playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
