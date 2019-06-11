/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collisions;

/**
 *
 * @author alvar
 */
public class FireWall extends Wall {
    
    public FireWall(float[] puntos) {
        super(puntos);
    }

    @Override
    public boolean isHostile() {
        return true; 
    }

    @Override
    public int getAtaque() {
        return 1;
    }
}
