/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author corte
 */
public class Slime extends Enemigo{

    public Slime(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque, 250, 20, 40 , 40, 80);
    }
}
