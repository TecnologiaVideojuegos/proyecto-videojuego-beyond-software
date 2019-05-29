/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 *
 * @author alvar
 */
public class DatosJugador implements Serializable {
    private boolean varitaFuego, varitaAgua, varitaLuz, botas, botasFuego;
    private int nivel, vida, pociones, pocionesG;

    public DatosJugador(boolean varitaFuego, boolean varitaAgua, boolean varitaLuz, boolean botas, boolean botasFuego, int vida, int pociones, int pocionesG, int nivel) {
        this.varitaFuego = varitaFuego;
        this.varitaAgua = varitaAgua;
        this.varitaLuz = varitaLuz;
        this.botas = botas;
        this.botasFuego = botasFuego;
        this.vida = vida;
        this.pociones = pociones;
        this.pocionesG = pocionesG;
        this.nivel = nivel;
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

    public boolean isBotasFuego() {
        return botasFuego;
    }

    public void setBotasFuego(boolean botasFuego) {
        this.botasFuego = botasFuego;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPociones() {
        return pociones;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }

    public int getPocionesG() {
        return pocionesG;
    }

    public void setPocionesG(int pocionesG) {
        this.pocionesG = pocionesG;
    }
}
