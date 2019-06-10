/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author alvar
 */
public class SalaCofre extends Sala {
    
    public SalaCofre(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Enemigo> enemigos, Objeto objeto, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagen, paredes, puertas, enemigos, null, jugador, proyectiles);
    }
    
}
