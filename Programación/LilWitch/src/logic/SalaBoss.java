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
    ArrayList<Cofre> cofres;

    public SalaBoss(Image imagen, ArrayList<Wall> paredes, ArrayList<Puerta> puertas, ArrayList<Objeto> objetos, Jugador jugador, ControladorProyectiles proyectiles) {
        super(imagen, paredes, puertas, null, jugador, proyectiles);
        this.objetos = objetos;
        cofres = new ArrayList<>();
        for (int i = 0; i < this.objetos.size(); i++) {
            //super.getGestor().registrarCuerpo(this.objetos.get(i));
            try {
                Cofre c = new Cofre(this.objetos.get(i), this.objetos.get(i).getPosicion().getX(), this.objetos.get(i).getPosicion().getY() + 120, i % 7);
                cofres.add(c);
                super.getGestor().registrarCuerpo(c);
            } catch(Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }

    @Override
    public int update(Input entrada, int delta) {
        int n = super.update(entrada, delta);
        for (int j = 0; j < cofres.size(); j++) {
            cofres.get(j).update(delta);            
        }
        return n;
    }
    
    

    @Override
    public void draw(Graphics g, Input entrada) {
        super.draw(g, entrada);
        /*for (int i = 0; i < objetos.size(); i++) {
            objetos.get(i).draw(); 
        }*/
        for (int j = 0; j < cofres.size(); j++) {
            cofres.get(j).draw();            
        }
    }
    
    
}
