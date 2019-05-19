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
    private ControladorProyectiles proyectiles;
    
    public GestorColision(ControladorProyectiles proyectiles) {
        lista = new ArrayList<IColisionable>();
        this.proyectiles = proyectiles;
    }
    
    public void registrarCuerpo(IColisionable cuerpo) {
        if(!lista.contains(cuerpo)) lista.add(cuerpo);
    }
    
    public void addProyectil(String filename, float x, float y, int width, int height, float escala, float vX, float vY, int daño, int tipo) {
        proyectiles.addProyectil(filename, x, y, width, height, escala, vX, vY, daño, tipo);
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
            buscarColisionProyectil(i);
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
        else if(lista.get(i) instanceof Jugador && lista.get(j) instanceof Enemigo) {
            if(lista.get(j).getVisionRange().intersects(lista.get(i).getHitbox())) {
                lista.get(j).alDetectar(lista.get(i));
            }
            return 0;
        }
        else if(lista.get(i) instanceof Enemigo && lista.get(j) instanceof Jugador) {
            if(lista.get(i).getVisionRange().intersects(lista.get(j).getHitbox())) {
                lista.get(i).alDetectar(lista.get(j));
            }
            return 0;
        }
        else {
            return 0;
        }
    }
    
    private void buscarColisionProyectil(int i) {
        for (int j = 0; j < proyectiles.getProyectiles().size(); j++) {
            if(proyectiles.get(j).getHitbox().intersects(lista.get(i).getHitbox())) {
                proyectiles.get(j).alColisionar(lista.get(i));
                lista.get(i).alColisionar(proyectiles.get(j));
                if (!lista.get(i).isPlayer() && proyectiles.get(j).isProyectile() >  2 || !lista.get(i).isEnemy() && proyectiles.get(j).isProyectile() != 1) {
                    proyectiles.removeProyectil(j);
                }
                if (lista.get(i).getVida() <= 0) {
                    lista.remove(i);
                }
            }       
        }
    }
    
    public void drawHitboxes(Graphics g) {
        for (int i = 0; i < lista.size(); i++) {
            g.draw(lista.get(i).getHitbox());
            if(lista.get(i) instanceof Enemigo) {
                g.draw(lista.get(i).getVisionRange());
            }
            
        }
        for (int j = 0; j < proyectiles.getProyectiles().size(); j++) {
            g.draw(proyectiles.get(j).getHitbox());;    
        }
    }
    
    public void updateProyectiles(int delta) {
        proyectiles.update(delta);
    }
    
    public void drawProyectiles() {
        proyectiles.draw();
    }
}
