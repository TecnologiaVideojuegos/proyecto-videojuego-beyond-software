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
public class Creditos extends BasicGameState{
    private Image image;
    private Music fin;
    
    @Override
    public int getID() {
        return 29;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        image = new Image("resources/intro/Fondo_4.png");
        fin = new Music("resources/sonidos/fin.ogg");
        fin.loop();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        g.drawImage(image,0, 0);
        g.setBackground(Color.black);
        g.setColor(Color.white);
        g.drawString("CRÉDITOS", 930, 120);
        g.drawString("JEFE DE PROYECTO", 810, 240);
        g.drawString("Guillermo Palacios Limón", 985, 240);
        g.drawString("PROGRAMADOR & TESTER", 775, 300);
        g.drawString("Álvaro Barchín Rubio", 985, 300);
        g.drawString("Con la prácticamente inexistente colaboración de:", 770, 420);
        g.drawString("DISEÑADOR WEB", 835, 480);
        g.drawString("Eloy Rodriguez Jañez", 985, 480);
        g.drawString("DISEÑADOR GRÁFICO", 800, 540);
        g.drawString("Miguel Matellanes Ramón", 985, 540);
        g.drawString("DISEÑADOR GRÁFICO", 800, 620);
        g.drawString("Xabier Pérez López ", 985, 620);
        g.drawString("MENCIONES ESPECIALES", 890, 740);
        g.drawString("opengameart.org", 870, 800);
        g.drawString("Makigas", 1030, 800);
        g.drawString("¡GRACIAS POR JUGAR!", 890, 920);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            fin.stop();
            game.enterState(2);
        }
    }
}
