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
        mapa = new SpriteSheet("resources/Nivel 1.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites1 = new Wall(new float[]{480,0, 480,120, 600,120, 600,240, 480,240, 480,360, 360,360, 360,480, 0,480, 0,960, 1920,960, 1920,0});
        Wall abismo11 = new Wall(new float[]{1320, 240,1320,360, 1200,360, 1200,480, 1080,480, 1080,600, 1320,600, 1320,480, 1440,480, 1440,360, 1800,360, 1800,240});
        Wall abismo12 = new Wall(new float[]{1680,600, 1680,720, 1560,720, 1560,840, 1800,840, 1800,600});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites2 = new Wall(new float[]{0,0, 0,960, 1920,960, 1920,0});
        Wall abismo21 = new Wall(new float[]{360,240, 360,480, 600,480, 600,240});
        Wall abismo22 = new Wall(new float[]{1320,480, 1320,720, 1560,720, 1560,480});
        Wall planta2 = new Wall(new float[]{1340, 260, 1340, 340, 1420, 340, 1420, 260});
        Wall limites31 = new Wall(new float[]{840, 0, 840, 360, 600, 360, 600, 240, 720, 240, 720, 0, 840, 0});
        Wall limites32 = new Wall(new float[]{0, 0, 0, 960, 1920, 960, 1920, 0, 0, 0});
        Wall abismo31 = new Wall(new float[]{0, 600, 120, 600, 120, 480, 240, 480, 240, 600, 360, 600, 360, 720, 720, 720, 720, 960, 0, 960});
        Wall abismo32 = new Wall(new float[]{1920, 960, 1800, 960, 1800, 840, 1560, 840, 1560, 720, 1800, 720, 1800, 840, 1920, 840});
        Wall abismo33 = new Wall(new float[]{1080, 0, 1080, 360, 1200, 360, 1200, 480, 1320, 480, 1680, 480, 1680, 360, 1560, 360, 1560, 240, 1440, 240, 1440, 120, 1320, 120, 1320,0});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3);
        Puerta p22 = new Puerta(840, 955, 240, 5, 2, 2);
        Puerta p23 = new Puerta(0, 360, 5, 240, 3, 1);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2);
        Puerta p32 = new Puerta(840, 0, 240, 5, 0, 4);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(limites1);
        walls1.add(abismo11);
        walls1.add(abismo12);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(limites2);
        walls2.add(abismo21);
        walls2.add(abismo22);
        walls2.add(planta2);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        puertas2.add(p23);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites31);
        walls3.add(limites32);
        walls3.add(abismo31);
        walls3.add(abismo32);
        walls3.add(abismo33);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        puertas3.add(p32);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        ArrayList<Puerta> puertas4 = new ArrayList();

        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, player);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, player);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, player);
        Sala sala4 = new Sala(mapa.getSubImage(2, 0), walls4, puertas4, player);
        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
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
