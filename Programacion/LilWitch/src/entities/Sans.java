/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Jugador;
import entities.Boss;
import logic.ControladorProyectiles;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author alvar
 */
public class Sans extends Boss {
    
    private Jugador player;
    private ControladorProyectiles proyectiles;

    public Sans(Jugador player, ControladorProyectiles proyectiles) throws SlickException {
        super("Sans", "skeleton_4.png", 120, 150, 840, 120, 50, 4, 900, player);
        this.player = player;
        this.proyectiles = proyectiles;
    }

    @Override
    public void atacar(int delta) {
        avanzar(delta);
        if (super.getCooldown() > 500) {
            float x = super.getPosicion().getX();
            float y = super.getPosicion().getY();

            int vX, vY;
            float dirX = player.getPosicion().getX() + 48 - x;
            float dirY = player.getPosicion().getY() + 52 - y;
            if (dirX < 0) {
                vX = -300;
            }
            else {
                vX = 300;
            }
            if (dirY < 0) {
                vY = -300;
            }
            else {
                vY = 300;
            }
            proyectiles.addProyectil("Hueso_g.png", x, y, 120, 120, 0.25f, vX, vY, 1, 1);
            super.setCooldown(0);
        }
    }
}
