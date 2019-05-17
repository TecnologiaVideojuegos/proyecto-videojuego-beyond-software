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
import org.newdawn.slick.tests.states.TestState1;
import org.newdawn.slick.tests.states.TestState2;

/**
 *
 * @author alvar
 */
public class Menu extends BasicGameState{
private int selected;
private String[] options = new String[] {"Nueva Partida","Cargar Partida","Highscores","Salir"};
private StateBasedGame game;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        g.setColor(Color.blue);
        g.drawString("Menú del Juego", 200, 50);
        //g.setBackground(Color.white);
        g.setColor(Color.black);
        
        for (int i=0;i<options.length;i++) {
			g.drawString(options[i], 400, 200+(i*50));
			if (selected == i) {
				g.drawRect(200,190+(i*50),400,50);
			}
		}
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_1)){
            game.enterState(0);
        }
        
    }
    public void keyReleased(int key, char c) {
		if (key == Input.KEY_DOWN) {
			selected++;
			if (selected >= options.length) {
				selected = 0;
			}
		}
		if (key == Input.KEY_UP) {
			selected--;
			if (selected < 0) {
				selected = options.length - 1;
			}
		}
		/*if (key == Input.KEY_1) {
			game.enterState(0);
		}
		if (key == Input.KEY_2) {
			game.enterState(1);
		}*/
	}
    
    
}
