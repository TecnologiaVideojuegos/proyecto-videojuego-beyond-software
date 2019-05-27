/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author alvar
 */
public class Vector {
    Punto origen, destino;

    public Vector(Punto origen, Punto destino) {
        this.origen = origen;
        this.destino = destino;
    }
    
    public Vector(Punto destino) {
        this.origen = new Punto(0, 0);
        this.destino = destino;
    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }

    public Punto getDestino() {
        return destino;
    }

    public void setDestino(Punto destino) {
        this.destino = destino;
    }
    
    public float getX() {
        return destino.getX() - origen.getX();
    }
    
    public float getY() {
        return destino.getY() - origen.getY();
    }
    
    public void setX(float x) {
        this.destino.setX(x);
    }
    
    public void setY(float y) {
        this.destino.setY(y);
    }
    
    public float getModulo() {
        double x = (double) this.getX();
        double y = (double) this.getY();
        return (float) Math.sqrt(x*x + y*y);
    }
}
