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
    private SpriteSheet mapa;
    private Wall limites, abismo1, abismo2;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        entrada = container.getInput();
        player = new Jugador();
        mapa = new SpriteSheet("resources/Mapa Completo.png", 1920, 1080);
        limites = new Wall(new float[]{480,0, 480,120, 600,120, 600,240, 480,240, 480,360, 360,360, 360,480, 240,480, 240,600, 0,600, 0,1080, 1920,1080, 1920,0});
        abismo1 = new Wall(new float[]{1320,360, 1320,480, 1200,480, 1200,600, 1080,600, 1080,720, 1320,720, 1320,600, 1440,600, 1440,480, 1800,480, 1800,360});
        abismo2 = new Wall(new float[]{1680,720, 1680,840, 1560,840, 1560,960, 1800,960, 1800,720});
        gestor = new GestorColision();
        gestor.registrarCuerpo(player);
        gestor.registrarCuerpo(limites);
        gestor.registrarCuerpo(abismo1);
        gestor.registrarCuerpo(abismo2);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        mapa.getSubImage(0, 10).draw(0, 0);
        player.draw(entrada);
        g.draw(player.getHitbox());
        g.draw(limites.getHitbox());
        g.draw(abismo1.getHitbox());
        g.draw(abismo2.getHitbox());
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        player.update(entrada);
        gestor.comprobarColisiones();
    }
    
    
}
