/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import entities.Enemigo;
import collisions.Wall;
import collisions.Puerta;
import collisions.Cofre;
import collisions.Objeto;
import entities.Jugador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.ControladorProyectiles;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class SalaCofre extends Sala {
    private Cofre cofre;
    
    public SalaCofre(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Enemigo> enemigos, Jugador jugador, ControladorProyectiles proyectiles, int tipoObj, int tipoCofre, int x, int y) {
        super(imagen, paredes, puertas, enemigos, null, jugador, proyectiles);
        try {
            this.cofre = new Cofre(new Objeto(tipoObj, x ,y), x, y, tipoCofre);
            super.getGestor().registrarCuerpo(cofre);
        } catch (SlickException ex) {
            System.out.println("Error al crear el cofre");;
        }
    }

    @Override
    public int update(Input entrada, int delta) {
        int n = super.update(entrada, delta); 
        cofre.update(delta);
        return n;
    }

    @Override
    public void draw(Graphics g, Input entrada) {
        super.draw(g, entrada); 
        cofre.draw();
    }
    
    
    
}
