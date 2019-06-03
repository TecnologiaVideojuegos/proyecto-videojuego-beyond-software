/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author alvar
 */
public class Boss extends Enemigo {
    private ArrayList<Image> barraVida;
    private int porcentajeVida, vidaTotal;
    
    
    public Boss(String filename, int ancho, int alto, int x, int y, int vida, int ataque, int velocidad, Jugador player) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque, velocidad);
        this.vidaTotal = vida;
        this.porcentajeVida = 100;
        this.barraVida = new ArrayList<>();
        SpriteSheet sprites = new SpriteSheet("resources/enemigos/barra_vida_2.png", 480, 30);
        for (int i = 0; i < 11; i++) {
            barraVida.add(sprites.getSubImage(0, i));
        }
    }
    
    public Boss(String filename, int ancho, int alto, int x, int y, int vida, int ataque, int velocidad, Jugador player, int offsetX, int offsetY, int offsetWidth, int offsetHeight) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque, velocidad, offsetX, offsetY, offsetWidth, offsetHeight);
        this.vidaTotal = vida;
        this.porcentajeVida = 100;
        this.barraVida = new ArrayList<>();
        SpriteSheet sprites = new SpriteSheet("resources/enemigos/barra_vida_2.png", 480, 30);
        for (int i = 0; i < 11; i++) {
            barraVida.add(sprites.getSubImage(0, i));
        }
    }
    
    public void updatePorcentajeVida() {
        porcentajeVida = super.getVida() * 100 / vidaTotal;
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        updatePorcentajeVida();
        
    }

    @Override
    public void draw() {
        if(super.getVida() > 0) {
            super.draw();
            switch((porcentajeVida / 10) + 1) {
                case 11:
                    barraVida.get(0).draw(720, 30);
                    break;
                case 10:
                    barraVida.get(0).draw(720, 30);
                    break;
                case 9:
                    barraVida.get(1).draw(720, 30);
                    break;
                case 8:
                    barraVida.get(2).draw(720, 30);
                    break;
                case 7:
                    barraVida.get(3).draw(720, 30);
                    break;
                case 6:
                    barraVida.get(4).draw(720, 30);
                    break;
                case 5:
                    barraVida.get(5).draw(720, 30);
                    break;
                case 4:
                    barraVida.get(6).draw(720, 30);
                    break;
                case 3:
                    barraVida.get(7).draw(720, 30);
                    break;
                case 2:
                    barraVida.get(8).draw(720, 30);
                    break;
                case 1:
                    barraVida.get(9).draw(720, 30);
                    break;
            }
        }
    }
    
    
    
}
