/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;

/**
 *
 * @author alvar
 */
public class GestorColision {
    private ArrayList<IColisionable> lista;
    
    public GestorColision() {
        lista = new ArrayList<IColisionable>();
    }
    
    public void registrarCuerpo(IColisionable cuerpo) {
        if(!lista.contains(cuerpo)) lista.add(cuerpo);
    }
    
    public void anularCuerpo(IColisionable cuerpo) {
        if(lista.contains(cuerpo)) lista.remove(cuerpo);
    }
    
    public void anularCuerpo(int i) {
        lista.remove(i);
    }
    
    public int comprobarColisiones() {
        int n = 0;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if(i != j) {
                    if(n == 0) {
                        n = buscarColision(i, j);
                    }
                    else {
                        buscarColision(i, j);
                    }
                    
                }  
            }  
        }
        return n;
    }
    
    private int buscarColision(int i, int j) {
        if(lista.get(i).getHitbox().intersects(lista.get(j).getHitbox())) {
            lista.get(i).alColisionar(lista.get(j));
            lista.get(j).alColisionar(lista.get(i));
            if(lista.get(i) instanceof Puerta && lista.get(j) instanceof Jugador) {
                return lista.get(i).getSalaDestino();
            }
            else if(lista.get(j) instanceof Puerta && lista.get(i) instanceof Jugador) {
                return lista.get(j).getSalaDestino();
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }
    
    public void drawHitboxes(Graphics g) {
        for (int i = 0; i < lista.size(); i++) {
            g.draw(lista.get(i).getHitbox());
            
        }
    }
}
