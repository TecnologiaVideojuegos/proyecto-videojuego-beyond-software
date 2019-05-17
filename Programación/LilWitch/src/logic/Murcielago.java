/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author corte
 */
public class Murcielago  extends Enemigo{

    public Murcielago(SpriteAnimado sprite, Rectangle hitbox, int vida, int ataque) {
        super(sprite, hitbox, vida, ataque);
    }
    
    
    @Override
    void atacar(Jugador j) {
        j.setVida(j.getVida()-super.getAtaque());
    }

    @Override
    public boolean isProyectile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void avanzar() {
        
        int rx = (int) (Math.random() * 3+1);
        int ry = (int) (Math.random() * 3+1);
        switch(rx){
            case 1:
                super.getSprite().moverX((float)-0.25);
                break;
            case 2:
                super.getSprite().moverX((float)0);
                break;
            case 3:
                super.getSprite().moverX((float)0.25);
                break;  
        }
        switch(ry){
            case 1:
                super.getSprite().moverY((float)-0.25);
                break;
            case 2:
                super.getSprite().moverY((float)0);
                break;
            case 3:
                super.getSprite().moverY((float)0.25);
                break;   
        }
        
    }

    
}
