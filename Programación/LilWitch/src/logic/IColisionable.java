/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.newdawn.slick.geom.Shape;

/**
 *
 * @author alvar
 */
public interface IColisionable {
    public Shape getHitbox();
    public Shape getVisionRange();
    public void alColisionar(IColisionable colision);
    public void alDetectar(IColisionable colision);
    public void sincronizarArea();
    public boolean isHostile();
    public boolean isGate();
    public boolean isPlayer();
    public boolean isEnemy();
    public int isProyectile(); //0 si no es un proyectil, 1 si el pryectil es enemigo, 2 si el proyectil es del jugador
    public int getDir();
    public int getSalaDestino();
    public int getAtaque();
    public int getVida();
    public Punto getPosicion();
}
