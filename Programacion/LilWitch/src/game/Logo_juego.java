package game;

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
public class Logo_juego extends BasicGameState{
private Image image;
private Music historia;
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        image = new Image("resources/logos/logo_1.png");
        historia = new Music("resources/sonidos/historia.ogg");
        historia.loop();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        //g.drawString("Logo del juego", 50, 50);
        g.setBackground(Color.white);
        g.drawImage(image,960-(image.getWidth()/2),480-(image.getHeight()/2));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            game.enterState(2,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
    
}
