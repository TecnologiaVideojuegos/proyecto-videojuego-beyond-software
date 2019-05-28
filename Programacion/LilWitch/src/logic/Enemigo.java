/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 *
 * @author alvar
 */
public class Enemigo implements IColisionable {
    private SpriteAnimado sprite;
    private Rectangle hitbox;
    private Circle visionRange;
    private boolean up, down, r, l, colision, hit;
    private int vida, ataque, cooldown, distanciaVision, movX, movY, velocidad, flickerTime;
    private Punto playerPosition;

    public Enemigo(String filename, int ancho, int alto, int x, int y, int vida, int ataque, int velocidad) throws SlickException {    
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
        
        this.sprite = new SpriteAnimado(animaciones, tileSet.getSprite(1, 2), tileSet.getSprite(1, 0), tileSet.getSprite(1, 1), tileSet.getSprite(1, 3), x, y);
        this.hitbox = new Rectangle(x, y, ancho, alto);
              
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.colision = false;
        this.vida = vida;
        this.ataque = ataque;
        this.cooldown = 1000;
        this.distanciaVision = 0;
        this.velocidad = velocidad;
        this.hit = false;
        this.flickerTime = 0;
        
        this.movX = (int) (Math.random() * 3+1);
        this.movY = (int) (Math.random() * 3+1);
        
        while(movX == 2 && movY == 2) {
            this.movX = (int) (Math.random() * 3+1);
            this.movY = (int) (Math.random() * 3+1);
        }
    }
    
    public Enemigo(String filename, int ancho, int alto, int x, int y, int distanciaVision, int vida, int ataque, int velocidad) throws SlickException {
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
        
        up = new Animation(i1, 200);
        down = new Animation(i3, 200);
        l = new Animation(i4, 200);
        r = new Animation(i2, 200);
        ControladorAnimacion animaciones = new ControladorAnimacion(up, down, l, r, 1f);
        
        this.sprite = new SpriteAnimado(animaciones, tileSet.getSprite(1, 2), tileSet.getSprite(1, 0), tileSet.getSprite(1, 1), tileSet.getSprite(1, 3), x, y);
        this.hitbox = new Rectangle(x, y, ancho, alto); 
        this.visionRange = new Circle(x + ancho / 2, y + alto / 2, distanciaVision); 
        
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.colision = false;
        this.vida = vida;
        this.ataque = ataque;
        this.cooldown = 1000;
        this.distanciaVision = distanciaVision;
        this.velocidad = velocidad;
        this.hit = false;
        this.flickerTime = 0;
        
        this.movX = (int) (Math.random() * 3+1);
        this.movY = (int) (Math.random() * 3+1);
        
        while(movX == 2 && movY == 2) {
            this.movX = (int) (Math.random() * 3+1);
            this.movY = (int) (Math.random() * 3+1);
        }
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void draw() {
        if(!hit) {
            if(down) {
                sprite.drawDown();
            }
            else if (up) {
                sprite.drawUp();
            }
            else if (l) {
                sprite.drawL();
            }
            else if (r) {
                sprite.drawR();
            }
            else {
                sprite.draw(); 
            }
        }
    }
    
    public void update(int delta) {
        if(hit) {
            flickerTime += delta;
            if(flickerTime > 100) {
                hit = false;
                flickerTime = 0;
            }
        }
        cooldown += delta;
        atacar(delta);
        sincronizarArea();
    }
    
    public void atacar(int delta) {
        avanzar(delta);   
    }
    public void avanzar(int delta) {
        if(colision) {
            if(up) {
                movX = (int) (Math.random() * 3 + 1);
                movY = 1;
            }
            if(down) {
                movX = (int) (Math.random() * 3 + 1);
                movY = 3;
            }
            if(r) {
                movX = 1;
                movY = (int) (Math.random() * 3 + 1);
            }
            else {
                movX = 3;
                movY = (int) (Math.random() * 3 + 1);
            }
            colision = false;
        }
        switch(movX){
            case 1:
                sprite.moverX(-velocidad * ((float) delta / 1000));
                l = true;
                r = false;
                break;
            case 2:
                sprite.moverX(0);
                l = false;
                r = false;
                break;
            case 3:
                sprite.moverX(velocidad * ((float) delta / 1000));
                l = false;
                r = true;
                break;  
        }
        switch(movY){
            case 1:
                sprite.moverY(-velocidad * ((float) delta / 1000));
                up = true;
                down = false;
                break;
            case 2:
                sprite.moverY(0);
                up = false;
                down = false;
                break;
            case 3:
                sprite.moverY(velocidad * ((float) delta / 1000));
                up = false;
                down = true;
                break;   
        }
        
        if (up) {
            sprite.stopL();
            sprite.stopR();
            sprite.startUp();
            sprite.stopDown();
        }
        else if (down) {
            sprite.stopL();
            sprite.stopR();
            sprite.stopUp();
            sprite.startDown();
        }
        else if (l) {
            sprite.startL();
            sprite.stopR();
            sprite.stopUp();
            sprite.stopDown();
        }
        else if (r) {
            sprite.stopL();
            sprite.startR();
            sprite.stopUp();
            sprite.stopDown();
        } 
    
    }

    @Override
    public void alColisionar(IColisionable colision, int delta) {
        if(colision.isProyectile() != 1) {
            if (!colision.isPlayer()) {
                this.colision = true;
                if(up) {
                    sprite.moverY(velocidad * (float) delta / 1000);
                }
                if(down) {
                    sprite.moverY(-velocidad * (float) delta / 1000);
                }
                if(r) {
                    sprite.moverX(-velocidad * (float) delta / 1000);
                }
                if(l) {
                    sprite.moverX(velocidad * (float) delta / 1000);
                }
            }
            if (colision.isProyectile() >= 2) {
                vida -= colision.getAtaque();
                hit = true;
            }
            
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

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }
    
    @Override
    public void sincronizarArea() {
        hitbox.setX(sprite.getPosicion().getX());
        hitbox.setY(sprite.getPosicion().getY());
        if(distanciaVision != 0) {
            visionRange.setCenterX(hitbox.getX() + hitbox.getWidth() / 2);
            visionRange.setCenterY(hitbox.getY() + hitbox.getHeight() / 2);
        }
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
    
    @Override
    public int isObjeto() {
        return 0;
    }

    public int getMovX() {
        return movX;
    }

    public void setMovX(int movX) {
        this.movX = movX;
    }

    public int getMovY() {
        return movY;
    }

    public void setMovY(int movY) {
        this.movY = movY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
