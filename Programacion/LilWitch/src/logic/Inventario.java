/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author alvar
 */
public class Inventario {
    private boolean varitaNormal, varitaFuego, varitaAgua, varitaLuz, botas, botasFuego;
    private int pociones, pocionesG, varitaActiva;
    private ArrayList<Sprite> sprites;
    /*varitaActiva
        0 -> varita estándar y varita de luz
        1 -> varita de fuego 
        2 -> varita de agua
    */
    public Inventario() throws SlickException {
        this.varitaNormal = false;
        this.varitaFuego = false;
        this.varitaAgua = false;
        this.varitaLuz = false;
        this.botas = false;
        this.botasFuego = false;
        this.pociones = 0;
        this.pocionesG = 0;
        this.varitaActiva = 0;
        sprites = new ArrayList<Sprite>();
        initSprites();
        
    }
    
    public Inventario(boolean debug)  throws SlickException {
        if (debug) {
            this.varitaNormal = true;
            this.varitaFuego = true;
            this.varitaAgua = true;
            this.varitaLuz = true;
            this.botas = true;
            this.botasFuego = true;
            this.pociones = 99;
            this.pocionesG = 99;
            this.varitaActiva = 0;
        }
        else {
            this.varitaNormal = false;
            this.varitaFuego = false;
            this.varitaAgua = false;
            this.varitaLuz = false;
            this.botas = false;
            this.botasFuego = false;
            this.pociones = 0;
            this.pocionesG = 0;
            this.varitaActiva = 0;
        }
        sprites = new ArrayList<Sprite>();
        initSprites();
    }
    
    public void initSprites() throws SlickException {
        Image imagen;
        float x = 0;
        float y = 0;
        for (int i = 0; i < 8; i++) {
            switch(i) {
            case 0:
               imagen = new Image("/resources/varitas/varita_normal.png");
               x = 907;
               y = 979;
               break;
            case 1:
                imagen = new Image("/resources/varitas/varita_fuego.png"); 
                x = 907;
                y = 979;
                break;
            case 2:
                imagen = new Image("/resources/varitas/varita_agua.png");
                x = 907;
                y = 979;
                break;
            case 3:
                imagen = new Image("/resources/varitas/varita_luz.png");
                x = 907;
                y = 979;
                break;
            case 4:
                imagen = new Image("/resources/objetos/bota_1_p.png");
                x = 636;
                y = 1006;
                break;
            case 5:
                imagen = new Image("/resources/objetos/botas_2_p.png");
                x = 687;
                y = 1006;
                break;
            case 6:
                imagen = new Image("/resources/objetos/pocion_1_p.png");
                x = 1195;
                y = 1006;
                break;
            case 7:
                imagen = new Image("/resources/objetos/pocion_2_p.png");
                x = 1246;
                y = 1006;
                break;        
            default:
                imagen = null;
                break;
            }
            sprites.add(new Sprite(imagen, x, y, 1));
        }
    }
    
    public void draw(Graphics g) {
        if(botas) {
            sprites.get(4).draw();
        }
        if(botasFuego) {
            sprites.get(5).draw();
        }
        switch(varitaActiva) {
            case 0:
                if(varitaNormal) {
                    sprites.get(0).draw();
                }
                else if(varitaLuz) {
                    sprites.get(3).draw(); 
                }
                break;
            case 1:
                sprites.get(1).draw();
                break;
            case 2:
                sprites.get(2).draw();
                break;
        }
        if(pociones > 0) {
            sprites.get(6).draw();
            g.drawString(pociones+"", 1195, 1006);
        }
        if(pocionesG > 0) {
            sprites.get(7).draw();
            g.drawString(pocionesG+"", 1246, 1006);
        }
    }
    
    public void cambiarVaritaR() {
        if (varitaActiva == 0 && varitaFuego) {
            varitaActiva ++;
        }
        else if (varitaActiva == 1 && varitaAgua) {
            varitaActiva ++;
        }
        else if (varitaActiva == 2 || varitaActiva == 1) {
            varitaActiva = 0;
        }
    }
    
    public void cambiarVaritaL() {
        if (varitaActiva == 0 && varitaAgua) {
            varitaActiva = 2;
        }
        else if (varitaActiva == 0 && varitaFuego) {
            varitaActiva = 1;
        }
        else if (varitaActiva != 0) {
            varitaActiva --;
        }
    }
    
    public boolean usarPocion() {
        if(pociones > 0) {
            pociones --;
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean usarPocionG() {
        if(pocionesG > 0) {
            pocionesG --;
            return true;
        }
        else {
            return false;
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

    public boolean isBotasFuego() {
        return botasFuego;
    }

    public void setBotasFuego(boolean botasFuego) {
        this.botasFuego = botasFuego;
    }

    public int getPocionesG() {
        return pocionesG;
    }

    public void setPocionesG(int pocionesG) {
        this.pocionesG = pocionesG;
    }

    public boolean isVaritaNormal() {
        return varitaNormal;
    }

    public void setVaritaNormal(boolean varitaNormal) {
        this.varitaNormal = varitaNormal;
    }
}
