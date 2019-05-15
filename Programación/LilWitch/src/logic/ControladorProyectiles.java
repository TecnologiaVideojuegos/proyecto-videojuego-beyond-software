/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;

/**
 *
 * @author alvar
 */
public class ControladorProyectiles {
    private ArrayList<Proyectil> proyectiles;

    public ControladorProyectiles(ArrayList<Proyectil> proyectiles) {
        this.proyectiles = proyectiles;
    }
    
    public void addProyectil(float x, float y, float vX, float vY) {
        proyectiles.add(new Proyectil("resources/Fire.png", x, y, 58, 72, 1, vX, vY));
    }
    
    public void update(int delta) {
        for (int i = 0; i < proyectiles.size(); i++) {
            proyectiles.get(i).update(delta);          
        }
    }
    
    public void draw() {
        for (int i = 0; i < proyectiles.size(); i++) {
            proyectiles.get(i).draw();          
        }
    }
}
