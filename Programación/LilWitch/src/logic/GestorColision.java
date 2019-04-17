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
    
    public void comprobarColisiones() {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if(i != j) buscarColision(i, j);  
            }  
        }
    }
    
    private void buscarColision(int i, int j) {
        if(lista.get(i).getAreaColision().intersects(lista.get(j).getAreaColision())) {
            lista.get(i).alColisionar(lista.get(j));
            lista.get(j).alColisionar(lista.get(i));      
        }
    }
}
