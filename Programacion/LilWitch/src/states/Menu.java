package states;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import serialization.UtilJugador;
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
private String[] options = new String[] {"","",""};
private StateBasedGame game;
private Image image;
private Sound sound;
private boolean nivel1 = false, nivel2 = false, nivel3 = false, nivel4 = false, nivel5 = false;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        image = new Image("resources/intro/fondo_1_v1.png");
        sound = new Sound("resources/sonidos/Select.ogg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        g.drawImage(image, 0, 0);
        //g.setColor(Color.blue);
        //g.drawString("Menú del Juego", 200, 50);
        //g.setBackground(Color.white);
        g.setColor(Color.white);
        
        for (int i=0;i<options.length;i++) {
            g.drawString(options[i], 1480, 870+(i*50));
            if (selected == i) {
                g.drawRect(1480, 865+(i*50),360,50);
            }
        }
        g.drawString("v1.0.3", 20, 1060);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            switch(selected) {
                case 0:
                    game.enterState(10, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    break;
                case 1:
                    if(UtilJugador.cargarDatos()) {
                        switch(UtilJugador.getNivelGuardado()) {
                            case 1:
                                game.addState(new Nivel1());
                                game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                                break;
                            case 2:
                                if(!nivel2) {
                                    game.addState(new Nivel2());
                                    nivel2 = true;
                                }
                                game.enterState(4, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                                break;
                            case 3:
                                if(!nivel3) {
                                    game.addState(new Nivel3());
                                    nivel3 = true;
                                }
                                game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                                break;
                            case 4:
                                if(!nivel4) {
                                    game.addState(new Nivel4());
                                    nivel4 = true;
                                }
                                game.enterState(6, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                                break;
                            case 5:
                                if(!nivel5) {
                                    game.addState(new Nivel5());
                                    nivel5 = true;
                                }
                                game.enterState(7, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                                break;
                        }
                    }
                    break;
                case 2:
                    container.exit();
                    break;
            }
        }   
    }
    
    @Override
    public void keyReleased(int key, char c) {
		if (key == Input.KEY_DOWN) {
			sound.play();
                        selected++;
			if (selected >= options.length) {
				selected = 0;
			}
		}
		if (key == Input.KEY_UP) {
			sound.play();
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
