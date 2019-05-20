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
    private int vida, vidaTotal, cooldown;
    private int[] objetos, varitas;
    private Image corazonVacio, corazonLleno, corazonMedio;

    public Jugador(ControladorProyectiles proyectiles) throws SlickException {
        SpriteSheet tileSet;
        Image[] i1 = new Image[10];
        Image[] i2 = new Image[10];
        Image[] i3 = new Image[10];
        Image[] i4 = new Image[10];
        Animation up, down, l, r;
        tileSet = new SpriteSheet("resources/sprites/minish.png", 96, 104);
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
        this.vida = 6;
        this.vidaTotal = 6;
        this.cooldown = 1000;
        this.objetos = new int[6];
        this.varitas = new int[4];
        this.corazonLleno = new Image("resources/objetos/corazon-lleno.png");
        this.corazonMedio = new Image("resources/objetos/corazon-medio.png");
        this.corazonVacio = new Image("resources/objetos/corazon-vacio.png");
    }
    
    public void draw(Input entrada) {
        if(entrada.isKeyDown(Input.KEY_A)) {
            personaje.drawL();
        }
        else if(entrada.isKeyDown(Input.KEY_D)) {
            personaje.drawR();
        }
        else if(entrada.isKeyDown(Input.KEY_W)) {
            personaje.drawUp();
        }
        else if(entrada.isKeyDown(Input.KEY_S)) {
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
        
        int contador = vida;
        boolean medio;
        
        if(vida % 2 == 1) {
            medio = true;
            contador--;
        }
        else {
            medio = false;
        }
        
        contador = contador / 2;
        
        for (int i = 0; i < contador; i++) {
            corazonLleno.draw(24 + i * 56, 10); 
        }
        if(medio) {
            corazonMedio.draw(24 + contador * 56, 10); 
        }
        for (int j = contador+1; j < vidaTotal / 2; j++) {
            corazonVacio.draw(24 + j * 56, 10);    
        }
    }
    
    public void update(Input entrada, int delta) {
        cooldown += delta;
        updateTeclado(entrada, delta);
        sincronizarArea();
    }
    
    private void updateTeclado(Input entrada, int delta) {
        if(!stop){
            atacar(entrada);
            
            if(entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-100*3 * ((float) delta / 1000));
                } 
                else {
                    personaje.moverX(-100 * ((float) delta / 1000));
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
            
            else if(!entrada.isKeyDown(Input.KEY_A) && entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(100f*3 * ((float) delta / 1000));
                } 
                else {
                    personaje.moverX(100f * ((float) delta / 1000));
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
        
            else if(!entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Arriba
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverY(-100f*3 * ((float) delta / 1000));
                }
                else {
                    personaje.moverY(-100f * ((float) delta / 1000));   
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
        
            else if(!entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && entrada.isKeyDown(Input.KEY_S)) { //Abajo
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverY(100f*3 * ((float) delta / 1000));
                }
                else{
                    personaje.moverY(100f * ((float) delta / 1000));
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
        
            else if(entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Arriba-Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-100f*3 * ((float) delta / 1000));
                    personaje.moverY(-100f*3 * ((float) delta / 1000));
                }
                else {
                    personaje.moverX(-100f * ((float) delta / 1000));
                    personaje.moverY(-100f * ((float) delta / 1000));
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
        
            else if(entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && entrada.isKeyDown(Input.KEY_S)) { //Abajo-Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(-100f*3 * ((float) delta / 1000));
                    personaje.moverY(100f*3 * ((float) delta / 1000));
                }
                else{
                    personaje.moverX(-100f * ((float) delta / 1000));
                    personaje.moverY(100f * ((float) delta / 1000));
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
            
            else if(!entrada.isKeyDown(Input.KEY_A) && entrada.isKeyDown(Input.KEY_D) && entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Arriba-Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(100f*3 * ((float) delta / 1000));
                    personaje.moverY(-100f*3 * ((float) delta / 1000));
                }
                else{
                    personaje.moverX(100f * ((float) delta / 1000));
                    personaje.moverY(-100f * ((float) delta / 1000));
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
        
            else if(!entrada.isKeyDown(Input.KEY_A) && entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && entrada.isKeyDown(Input.KEY_S)) { //Abajo-Derecha
                if(entrada.isKeyDown(Input.KEY_LSHIFT)){
                    personaje.moverX(100f*3 * ((float) delta / 1000));
                    personaje.moverY(100f*3 * ((float) delta / 1000));
                }
                else {
                    personaje.moverX(100f * ((float) delta / 1000));
                    personaje.moverY(100f * ((float) delta / 1000));
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
    
    public void atacar(Input entrada) {
        if (cooldown > 1000) {
            float x = personaje.getPosicion().getX();
            float y = personaje.getPosicion().getY();
            float vX = 0;
            float vY = 0;
            if (!entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += personaje.getStaticDown().getWidth();
                y += (personaje.getStaticDown().getHeight() / 2) - 36;
                vX = 200;
                proyectiles.addProyectil("Fire.png", x, y, 58, 72, 0.5f, vX, vY, 2, 2);
                cooldown = 0;
            }
            else if (!entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x -= 58;
                y += (personaje.getStaticDown().getHeight() / 2) - 36;
                vX = -200;
                proyectiles.addProyectil("Fire.png", x, y, 58, 72, 0.5f, vX, vY, 2, 2);
                cooldown = 0;
            }
            else if (entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += (personaje.getStaticDown().getWidth() / 2) - 29;
                y -= 72;
                vY = -200;
                proyectiles.addProyectil("Fire.png", x, y, 58, 72, 0.5f, vX, vY, 2, 2);
                cooldown = 0;
            }
            else if (!entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += (personaje.getStaticDown().getWidth() / 2) - 29;
                y += personaje.getStaticDown().getHeight();
                vY = 200; 
                proyectiles.addProyectil("Fire.png", x, y, 58, 72, 0.5f, vX, vY, 2, 2);
                cooldown = 0;
            }     
        }
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
            if(colision.isProyectile() != 2) {
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
                if (colision.isEnemy()) {
                    vida -= colision.getAtaque();
                }
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

    @Override
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
        return false;
    } 
    
    @Override
    public int getAtaque() {
        return 0;
    }
    
    @Override
    public Shape getVisionRange() {
        return hitbox;
    }

    @Override
    public void alDetectar(IColisionable colision) {}

    @Override
    public Punto getPosicion() {
        return personaje.getPosicion();
    }

    public ControladorProyectiles getProyectiles() {
        return proyectiles;
    }

    public void setProyectiles(ControladorProyectiles proyectiles) {
        this.proyectiles = proyectiles;
    }
}
