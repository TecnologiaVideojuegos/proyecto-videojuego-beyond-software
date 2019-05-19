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

    public ControladorProyectiles() {
        proyectiles = new ArrayList<Proyectil>();
    }
    
    public void addProyectil(String filename, float x, float y, int width, int height, float escala, float vX, float vY, int daño, int tipo) {
        proyectiles.add(new Proyectil(filename, x, y, width, height, escala, vX, vY, daño, tipo));
    }
    
    public void removeProyectil(int i) {
        Proyectil p = proyectiles.remove(i);
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
    
    public Proyectil get(int i) {
        return proyectiles.get(i);
    }

    public ArrayList<Proyectil> getProyectiles() {
        return proyectiles;
    }

    public void setProyectiles(ArrayList<Proyectil> proyectiles) {
        this.proyectiles = proyectiles;
    }
}
