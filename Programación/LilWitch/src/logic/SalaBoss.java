/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 *
 * @author alvar
 */
public class SalaBoss extends Sala {
    ArrayList<Objeto> objetos;

    public SalaBoss(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Objeto> objetos, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagen, paredes, puertas, null, jugador, proyectiles);
        this.objetos = objetos;
        for (int i = 0; i < this.objetos.size(); i++) {
            super.getGestor().registrarCuerpo(this.objetos.get(i));  
        }
    }

    @Override
    public void draw(Graphics g, Input entrada) {
        super.draw(g, entrada);
        for (int i = 0; i < objetos.size(); i++) {
            objetos.get(i).draw();  
        }
    }
    
    
}
