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
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class SalaBoss extends Sala {
    private ArrayList<Objeto> objetos;
    private ArrayList<Puerta> puertas;
    private boolean cleared;

    public SalaBoss(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, Objeto objeto, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagen, paredes, null, null, jugador, proyectiles);
        this.puertas = puertas;
        this.objetos = new ArrayList<>();
        try {
            objetos.add(new Objeto(8, 840, 420));
        }
        catch (SlickException se) {
            System.out.println("Error al crear la sala");
        }
        objetos.add(objeto);
        
        
        
        
    }

    @Override
    public int update(Input entrada, int delta) {
        int n = super.update(entrada, delta);
        
        return n;
    }
    
    @Override
    public void draw(Graphics g, Input entrada) {
        super.draw(g, entrada);
        /*for (int i = 0; i < objetos.size(); i++) {
            objetos.get(i).draw(); 
        }*/
    }
    
    public void habilitarPuertas() {
        for (int j = 0; j < puertas.size(); j++) {
            super.getGestor().registrarCuerpo(puertas.get(j));   
        }
    }
}
