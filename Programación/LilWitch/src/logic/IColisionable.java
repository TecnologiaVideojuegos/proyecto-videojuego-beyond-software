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
    public void alColisionar(IColisionable colision);
    public void sincronizarArea();
    public boolean isHostile();
}
