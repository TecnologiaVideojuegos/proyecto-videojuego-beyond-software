/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import logic.ControladorAnimacion;
import logic.ControladorProyectiles;
import collisions.IColisionable;
import logic.Inventario;
import logic.Punto;
import logic.SpriteAnimado;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 *
 * @author alvar
 */
public class Jugador implements IColisionable {
    private SpriteAnimado personaje;
    private Rectangle hitbox, ladoUp, ladoDown, ladoL, ladoR;
    private boolean up, down, r, l, stop, stopDialogo, hit;
    private ControladorProyectiles proyectiles;
    private int vida, vidaTotal, cooldown, varitaActual, tiempoInvencibilidad, flickerTime, contadorDialogo;
    private Image corazonVacio, corazonLleno, corazonMedio;
    private Inventario inventario;
    private Sound select;
    private Sound pocion;
    private SpriteSheet dialogos;

    public Jugador(float x, float y, ControladorProyectiles proyectiles) throws SlickException {
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
        
        personaje = new SpriteAnimado(animaciones, tileSet.getSprite(0, 0), tileSet.getSprite(0, 2), tileSet.getSprite(0, 3), tileSet.getSprite(0, 1), x, y);
        hitbox = new Rectangle(personaje.getPosicion().getX(), personaje.getPosicion().getY(), personaje.getStaticDown().getWidth()-40, personaje.getStaticDown().getHeight()-15);
        ladoUp = new Rectangle(hitbox.getX() + 8, hitbox.getY() - 6, hitbox.getWidth() - 16, 6);
        ladoDown = new Rectangle(hitbox.getX() + 8, hitbox.getY() + hitbox.getHeight(), hitbox.getWidth() - 16, 6);
        ladoL = new Rectangle(hitbox.getX() - 6, hitbox.getY() + 8, 6, hitbox.getHeight() - 16);
        ladoR = new Rectangle(hitbox.getX() + hitbox.getWidth(), hitbox.getY() + 8, 6, hitbox.getHeight() - 16);
        this.up = false;
        this.down = false;
        this.r = false;
        this.l = false;
        this.stop = false;
        this.proyectiles = proyectiles;
        this.vida = 6;
        this.vidaTotal = 6;
        this.cooldown = 1000;
        this.tiempoInvencibilidad = 350;
        this.corazonLleno = new Image("resources/objetos/corazon-lleno.png");
        this.corazonMedio = new Image("resources/objetos/corazon-medio.png");
        this.corazonVacio = new Image("resources/objetos/corazon-vacio.png");
        this.inventario = new Inventario();
        this.hit = false;
        this.flickerTime = 0;
        this.stopDialogo = false;
        this.contadorDialogo = 0;
        dialogos = new SpriteSheet("resources/historia/Dialogo_1.png", 700, 70);
        
        select = new Sound("resources/sonidos/Select.ogg");
        pocion = new Sound("resources/sonidos/pocion.ogg");
    }
    
    public void draw(Input entrada, Graphics g) {
        //drawLados(g);
        if(!hit && !stopDialogo) {
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
        }
        if(stopDialogo) {
            personaje.draw();
            switch(contadorDialogo) {
                case 0:
                    dialogos.getSubImage(0, 0).draw(610, 840);
                    break;
                case 1:
                    dialogos.getSubImage(0, 1).draw(610, 840);
                    break;
                case 2:
                    dialogos.getSubImage(0, 2).draw(610, 840);
                    break;
                case 3:
                    dialogos.getSubImage(0, 3).draw(610, 840);
                    break;
                default:
                    stopDialogo = false;
                    break;
            }
        }
        drawCorazones();
        inventario.draw(g);
    }
    
    public void drawLados(Graphics g) {
        g.draw(ladoUp);
        g.draw(ladoDown);
        g.draw(ladoL);
        g.draw(ladoR);
    }
    
    public void drawCorazones() {
        int numLlenos = vida;
        int contador = 0;
        boolean medio;
        
        if(vida % 2 == 1) {
            medio = true;
            numLlenos--;
        }
        else {
            medio = false;
        }
        
        numLlenos = numLlenos / 2;
        int numVacios = vidaTotal/2 - numLlenos;
        if(medio) numVacios --;
        
        for (int i = 0; i < numLlenos; i++) {
            corazonLleno.draw(24 + i * 56, 10);
            contador++;        
        }
        if(medio) {
            corazonMedio.draw(24 + contador * 56, 10);
            contador++;
        }
        for (int j = 0; j < numVacios; j++) {
            corazonVacio.draw(24 + contador * 56, 10);
            contador++;        
        }
    }
    
    public void update(Input entrada, int delta) {
        if(stopDialogo) {
            if(entrada.isKeyPressed(Input.KEY_ENTER)) {
                contadorDialogo ++;
            }
        }
        if(hit) {
            flickerTime += delta;
            if(flickerTime > 50) {
                hit = false;
                flickerTime = 0;
            }
        }
        cooldown += delta;
        tiempoInvencibilidad += delta;
        updateTeclado(entrada, delta);
        sincronizarArea();
    }
    
    private void updateTeclado(Input entrada, int delta) {
        if(!stop && !stopDialogo){
            if(entrada.isKeyPressed(Input.KEY_LCONTROL)) {
                select.play();
                inventario.cambiarVaritaL();
            }
            if(entrada.isKeyPressed(Input.KEY_SPACE)) {
                select.play();
                inventario.cambiarVaritaR();
            }
            if(entrada.isKeyPressed(Input.KEY_E)) {
                if(inventario.usarPocion()) {
                    pocion.play();
                    heal(2); 
                } 
            }
            if(entrada.isKeyPressed(Input.KEY_R)) {
                if(inventario.usarPocionG()) {
                    pocion.play();
                    heal(6);
                }
                
            }
            
            atacar(entrada);
            
            if(entrada.isKeyDown(Input.KEY_A) && !entrada.isKeyDown(Input.KEY_D) && !entrada.isKeyDown(Input.KEY_W) && !entrada.isKeyDown(Input.KEY_S)) { //Izquierda
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(-100*5 * ((float) delta / 1000));
                } 
                else {
                    personaje.moverX(-100 *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(100f*5 * ((float) delta / 1000));
                } 
                else {
                    personaje.moverX(100f *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverY(-100f*5 * ((float) delta / 1000));
                }
                else {
                    personaje.moverY(-100f *3* ((float) delta / 1000));   
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverY(100f*5 * ((float) delta / 1000));
                }
                else{
                    personaje.moverY(100f *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(-100f*5 * ((float) delta / 1000));
                    personaje.moverY(-100f*5 * ((float) delta / 1000));
                }
                else {
                    personaje.moverX(-100f *3* ((float) delta / 1000));
                    personaje.moverY(-100f *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(-100f*5 * ((float) delta / 1000));
                    personaje.moverY(100f*5 * ((float) delta / 1000));
                }
                else{
                    personaje.moverX(-100f *3* ((float) delta / 1000));
                    personaje.moverY(100f *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(100f*5 * ((float) delta / 1000));
                    personaje.moverY(-100f*5 * ((float) delta / 1000));
                }
                else{
                    personaje.moverX(100f *3* ((float) delta / 1000));
                    personaje.moverY(-100f *3* ((float) delta / 1000));
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
                if(entrada.isKeyDown(Input.KEY_LSHIFT) && inventario.isBotas()){
                    personaje.moverX(100f*5 * ((float) delta / 1000));
                    personaje.moverY(100f*5 * ((float) delta / 1000));
                }
                else {
                    personaje.moverX(100f *3* ((float) delta / 1000));
                    personaje.moverY(100f *3* ((float) delta / 1000));
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
        if (cooldown > getCooldownActual()) {
            float x = personaje.getPosicion().getX();
            float y = personaje.getPosicion().getY();
            float dirX = 0;
            float dirY = 0;
            if (!entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += personaje.getStaticDown().getWidth();
                y += (personaje.getStaticDown().getHeight() / 2) - 36;
                dirX = 1;
                crearProyectil(x, y, dirX, dirY);
                cooldown = 0;
            }
            else if (!entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x -= 58;
                y += (personaje.getStaticDown().getHeight() / 2) - 36;
                dirX = -1;
                crearProyectil(x, y, dirX, dirY);
                cooldown = 0;
            }
            else if (entrada.isKeyDown(Input.KEY_UP) && !entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += (personaje.getStaticDown().getWidth() / 2) - 29;
                y -= 72;
                dirY = -1;
                crearProyectil(x, y, dirX, dirY);
                cooldown = 0;
            }
            else if (!entrada.isKeyDown(Input.KEY_UP) && entrada.isKeyDown(Input.KEY_DOWN) && !entrada.isKeyDown(Input.KEY_LEFT) && !entrada.isKeyDown(Input.KEY_RIGHT)) {
                x += (personaje.getStaticDown().getWidth() / 2) - 29;
                y += personaje.getStaticDown().getHeight();
                dirY = 1; 
                crearProyectil(x, y, dirX, dirY);
                cooldown = 0;
            }     
        }
    }
    
    public int getCooldownActual() {
        switch (inventario.getVaritaActiva()) {
            case 0:
                return 750;
            case 1:
                return 1250;
            case 2:
                return 1000;
            default:
                return 750;          
        }
    }
    
    public void crearProyectil(float x, float y, float dirX, float dirY) {
        switch(inventario.getVaritaActiva()) {
            case 0:
                if(inventario.isVaritaLuz()) {
                    if(dirX == 0) {
                        x -= 25;
                    }
                    proyectiles.addProyectil("luz_2_110_110.png", x, y, 110, 110, 1f, 600*dirX, 600*dirY, 5, 5);
                }
                else if (inventario.isVaritaNormal()) {
                    proyectiles.addProyectil("luz_1.png", hitbox.getCenterX()-20, hitbox.getCenterY()-20, 45, 45, 1f, 600*dirX, 600*dirY, 2, 2);
                }
                break;
            case 1:
                proyectiles.addProyectil("Fire.png", x, y, 58, 72, 1f, 400*dirX, 400*dirY, 4, 3);
                break;
            case 2:
                proyectiles.addProyectil("tornado.png", x, y, 50, 60, 1f, 500*dirX, 500*dirY, 3, 4);
                break;
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
    public void alColisionar(IColisionable colision, int delta) {
        if(!colision.isGate()) {
            if(colision.isProyectile() < 2) {
                if(colision.isObjeto() > 0) {
                    switch(colision.isObjeto()) {
                        case 1:
                           inventario.setVaritaNormal(true);
                           stopDialogo = true;
                           break;
                        case 2:
                           inventario.setVaritaLuz(true);
                           break;
                        case 3:
                            inventario.setVaritaFuego(true);
                            break;
                        case 4:
                            inventario.setVaritaAgua(true);
                            break;
                        case 5:
                            inventario.setBotas(true);
                            break;
                        case 6:
                            inventario.setBotasFuego(true);
                            break;
                        case 7:
                            inventario.setPociones(inventario.getPociones() + 1);
                            break;
                        case 8:
                            inventario.setPocionesG(inventario.getPocionesG() + 1);
                            break;
                        case 9:
                            vidaTotal += 2;
                            vida = vidaTotal;
                            break;
                    }
                }
                
                if (colision.isEnemy()) {
                    if(colision.isProyectile() == 1) {
                        vida -= colision.getAtaque();
                        hit = true;
                    }
                    else {
                        if(tiempoInvencibilidad > 500) {
                            vida -= colision.getAtaque();
                            hit = true;
                            tiempoInvencibilidad = 0;
                        }
                    }    
                }
                else {
                    if(up && colision.getHitbox().intersects(ladoUp)) {
                        personaje.moverY(500f * (float) delta / 1000);
                    }
                    if(down && colision.getHitbox().intersects(ladoDown)) {
                        personaje.moverY(-500f * (float) delta / 1000);
                    }
                    if(r && colision.getHitbox().intersects(ladoR)) {
                        personaje.moverX(-500f * (float) delta / 1000);
                    }
                    if(l && colision.getHitbox().intersects(ladoL)) {
                        personaje.moverX(500f * (float) delta / 1000);
                    }
                    if(colision.isWall() && colision.getAtaque() > 0 && tiempoInvencibilidad > 500 && !inventario.isBotasFuego()) {
                            vida -= colision.getAtaque();
                            hit = true;
                            tiempoInvencibilidad = 0;
                    } 
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
    
    public void heal(int n) {
        vida+=n;
        if(vida>vidaTotal) {
            vida = vidaTotal;
        }
    }
    
    @Override
    public void sincronizarArea() {
        hitbox.setX(personaje.getPosicion().getX()+20);
        hitbox.setY(personaje.getPosicion().getY()+15);
        ladoUp.setLocation(hitbox.getX() + 8, hitbox.getY() - 3); 
        ladoDown.setLocation(hitbox.getX() + 8, hitbox.getY() + hitbox.getHeight() - 3);
        ladoL.setLocation(hitbox.getX() - 3, hitbox.getY() + 8); 
        ladoR.setLocation(hitbox.getX() + hitbox.getWidth() - 3, hitbox.getY() + 8);
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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    @Override
    public int isObjeto() {
        return 0;
    }

    public int getVidaTotal() {
        return vidaTotal;
    }

    public void setVidaTotal(int vidaTotal) {
        this.vidaTotal = vidaTotal;
    }
    
    @Override
    public boolean isWall() {
        return false;
    }
}
