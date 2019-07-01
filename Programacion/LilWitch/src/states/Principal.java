package states;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
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
        contenedor.setIcon("/resources/logos/32x32.png");
        contenedor.setDisplayMode(1920, 1080, true);
        contenedor.setMouseGrabbed(true);
        contenedor.setShowFPS(false);
        contenedor.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Logo());
        this.addState(new Logo_juego());
        this.addState(new Menu());
        this.addState(new Intro1());
        this.addState(new Intro2());
        this.addState(new Intro3());
        this.addState(new Intro4());
        this.addState(new Intro5());
        this.addState(new Intro6());
        this.addState(new Intro7());
        this.addState(new Controles());
        this.addState(new Historia1());
        this.addState(new Historia2());
        this.addState(new Historia3());
        this.addState(new Historia4());
        this.addState(new Historia5());
        this.addState(new Historia6());
        this.addState(new Historia7());
        this.addState(new Historia8());
        this.addState(new GameOver());
    }
    
    public static void main(String args[]) {
        try {
            
            System.setProperty("java.library.path", new File("./Slick2D").getAbsolutePath());
            Principal main = new Principal(); 
        }
        catch(SlickException slick) {
            slick.printStackTrace();
            //System.exit(1);
        }
    }
}
