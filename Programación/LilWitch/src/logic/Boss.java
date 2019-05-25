/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class Boss extends Enemigo {
    
    public Boss(String filename, int ancho, int alto, int x, int y, int vida, int ataque) throws SlickException {
        super(filename, ancho, alto, x, y, vida, ataque);
    }
    
}
