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

/**
 *
 * @author alvar
 */
public class Nivel1 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 1;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador();
        mapa = new SpriteSheet("resources/Mapa Completo_v1.png", 1920, 1080);
        Wall limites1 = new Wall(new float[]{480,0, 480,120, 600,120, 600,240, 480,240, 480,360, 360,360, 360,480, 240,480, 240,600, 0,600, 0,1080, 1920,1080, 1920,0});
        Wall abismo11 = new Wall(new float[]{1320,360, 1320,480, 1200,480, 1200,600, 1080,600, 1080,720, 1320,720, 1320,600, 1440,600, 1440,480, 1800,480, 1800,360});
        Wall abismo12 = new Wall(new float[]{1680,720, 1680,840, 1560,840, 1560,960, 1800,960, 1800,720});
        Wall limites2 = new Wall(new float[]{0,0, 0,1080, 1920,1080, 1920,0});
        Wall abismo21 = new Wall(new float[]{360,240, 360,480, 600,480, 600,240});
        Wall abismo22 = new Wall(new float[]{1320,600, 1320,840, 1560,840, 1560,600});
        Wall planta2 = new Wall(new float[]{1320,240, 1320,360, 1440,360, 1440,240});
        Puerta p11 = new Puerta(1915, 480, 5, 240, 1, 2);
        Puerta p21 = new Puerta(1915, 480, 5, 240, 1, 3);
        Puerta p22 = new Puerta(840, 1075, 240, 5, 2, 0);
        Puerta p23 = new Puerta(0, 480, 5, 240, 3, 1);
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites1);
        walls1.add(abismo11);
        walls1.add(abismo12);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites2);
        walls2.add(abismo21);
        walls2.add(abismo22);
        walls2.add(planta2);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        puertas2.add(p23);
        Sala sala1 = new Sala(mapa.getSubImage(0, 10), walls1, puertas1, player);
        Sala sala2 = new Sala(mapa.getSubImage(1, 10), walls2, puertas2, player);
        salas.add(sala1);
        salas.add(sala2);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        salas.get(salaActual-1).draw(g, entrada);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        int n = salas.get(salaActual-1).update(entrada);
        System.out.println(n);
        if(n!=0) salaActual = n;
    }
    
    
}
