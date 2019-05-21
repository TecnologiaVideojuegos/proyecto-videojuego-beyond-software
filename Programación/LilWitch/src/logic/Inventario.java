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
public class Inventario {
    private boolean varitaFuego, varitaAgua, varitaLuz, botas;
    private int pociones, varitaActiva;
    /*varitaActiva
        2 -> varita estándar y varita de luz
        3 -> varita de fuego 
        4 -> varita de agua
    */
    public Inventario() {
        this.varitaFuego = false;
        this.varitaAgua = false;
        this.varitaLuz = false;
        this.botas = false;
        this.pociones = 0;
        this.varitaActiva = 2;
    }
    
    public Inventario(boolean debug) {
        if (debug) {
            this.varitaFuego = true;
            this.varitaAgua = true;
            this.varitaLuz = true;
            this.botas = true;
            this.pociones = 99;
            this.varitaActiva = 2;
        }
        else {
            this.varitaFuego = false;
            this.varitaAgua = false;
            this.varitaLuz = false;
            this.botas = false;
            this.pociones = 0;
            this.varitaActiva = 2;
        }
    }

    public boolean isVaritaFuego() {
        return varitaFuego;
    }

    public void setVaritaFuego(boolean varitaFuego) {
        this.varitaFuego = varitaFuego;
    }

    public boolean isVaritaAgua() {
        return varitaAgua;
    }

    public void setVaritaAgua(boolean varitaAgua) {
        this.varitaAgua = varitaAgua;
    }

    public boolean isVaritaLuz() {
        return varitaLuz;
    }

    public void setVaritaLuz(boolean varitaLuz) {
        this.varitaLuz = varitaLuz;
    }

    public boolean isBotas() {
        return botas;
    }

    public void setBotas(boolean botas) {
        this.botas = botas;
    }

    public int getPociones() {
        return pociones;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }

    public int getVaritaActiva() {
        return varitaActiva;
    }

    public void setVaritaActiva(int varitaActiva) {
        this.varitaActiva = varitaActiva;
    }
}
