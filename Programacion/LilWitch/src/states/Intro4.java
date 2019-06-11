package states;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import logic.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author alvar
 */
public class Intro4 extends BasicGameState{
private Image image;
private Music nivel1;
    
    @Override
    public int getID() {
        return 13;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        image = new Image("resources/intro/fondo_4_1.png");
        nivel1 = new Music("resources/sonidos/Caves.ogg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        g.drawImage(image,0, 0);
        g.drawString("Pulse ESPACIO para saltar", 840, 20);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            game.enterState(14);
        }
        if(container.getInput().isKeyPressed(Input.KEY_SPACE)){
            //nivel1.loop();
            //game.addState(new Controles());
            game.enterState(18);
        }
    }
    
    
}
