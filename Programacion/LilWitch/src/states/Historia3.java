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
public class Historia3 extends BasicGameState{
    private Image image;
    
    @Override
    public int getID() {
        return 22;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        image = new Image("resources/historia/fuego.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        g.drawImage(image,0, 0);
        g.drawString("CONSEJO: Cuidado con el fuego", 810, 20);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            game.enterState(6);
        }
    }
    
    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        container.getInput().clearKeyPressedRecord();
        init(container, game); 
    }
}
