package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author alvar
 */
public class Principal extends StateBasedGame{
    
    private AppGameContainer contenedor;
    
    public Principal() throws SlickException {
        super("Lil'Witch");
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1920, 1080, false);
        contenedor.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        /*this.addState(new Logo());
        this.addState(new Logo_juego());
        this.addState(new Menu());
        this.addState(new intro_1());
        this.addState(new intro_2());
        this.addState(new intro_3());
        this.addState(new intro_4());
        this.addState(new intro_5());
        this.addState(new intro_6());
        this.addState(new intro_7());*/
        this.addState(new Nivel1());
        this.addState(new Nivel2());
        this.addState(new Nivel3());
        this.addState(new Nivel4());
        this.addState(new Nivel5());
    }
    
    public static void main(String args[]) {
        try {
            Principal main = new Principal(); 
        }
        catch(SlickException slick) {
            slick.printStackTrace();
            System.exit(1);
        }
    }
}
