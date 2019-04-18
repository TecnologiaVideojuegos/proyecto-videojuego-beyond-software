package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import logic.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author alvar
 */
public class Juego extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private GestorColision gestor;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        entrada = container.getInput();
        player = new Jugador();
        gestor = new GestorColision();
        gestor.registrarCuerpo(player);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        player.draw(entrada);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        player.update(entrada);
    }
    
    
}
