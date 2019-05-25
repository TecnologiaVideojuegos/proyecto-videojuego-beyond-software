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
public class ReySlime extends Boss {
    private Jugador player;

    public ReySlime(Jugador player) throws SlickException {
        super("slime_boss_2.png", 360, 450, 960, 120, 100, 2, 100, player);
        this.player = player;
    }
    
}
