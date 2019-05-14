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
public class Nivel3 extends BasicGameState{
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
        mapa = new SpriteSheet("resources/Nivel 3.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall agua1_1 = new Wall(new float[]{20, 20, 240, 20, 240, 120, 120, 120, 120, 240, 20, 240});
        Wall agua1_2 = new Wall(new float[]{240, 720, 360, 720, 360, 360, 720, 360, 720, 240, 840, 240, 840, 840, 240, 840});
        Wall agua1_3 = new Wall(new float[]{1200, 480, 1320, 480, 1320, 840, 1200, 840});
        Wall agua1_4 = new Wall(new float[]{1440, 120, 1800, 120, 1800, 360, 1680, 360, 1680, 240, 1440, 240});
        Wall agua1_5 = new Wall(new float[]{1440, 720, 1800, 720, 1800, 840, 1440, 840});
        Wall agua2_1 = new Wall(new float[]{480, 600, 480, 720, 600, 720, 600, 600, 1320, 600, 1320, 360, 1440, 360, 1440, 240, 1320, 240, 1320, 360, 600, 360, 600, 600});
        Wall agua3_1 = new Wall(new float[]{120, 20, 120, 480, 240, 480,240, 240, 360, 240, 360, 120, 480, 120, 480, 20});
        Wall agua3_2 = new Wall(new float[]{120, 720, 480, 720, 480, 840, 120, 840});
        Wall agua3_3 = new Wall(new float[]{840, 480, 840, 600, 960, 600, 960, 480, 1080, 480, 1080, 360, 960, 360, 960, 480});
        Wall agua3_4 = new Wall(new float[]{1560, 360, 1560, 480, 1440, 480, 1440, 720, 1320, 720, 1320, 840, 1800, 840, 1800, 360});
        Wall agua4_1 = new Wall(new float[]{20, 360, 120, 360, 120, 240, 240, 240, 240, 120, 360, 120, 360, 20, 240, 20, 240, 120, 120, 120, 120, 240, 20, 240});
        Wall agua4_2 = new Wall(new float[]{120, 480, 480, 480, 480, 600, 120, 600});
        Wall agua4_3 = new Wall(new float[]{20, 720, 120, 720, 120, 840, 20, 840});
        Wall agua4_4 = new Wall(new float[]{240, 840, 360, 840, 360, 940, 240, 940});
        Wall agua4_5 = new Wall(new float[]{1590, 840, 1680, 840, 1680, 940, 1560, 940});
        Wall agua4_6 = new Wall(new float[]{1800, 720, 1900, 720, 1900, 840, 1800, 840});
        Wall agua4_7 = new Wall(new float[]{1440, 480, 1800, 480, 1800, 600, 1440, 600});
        Wall agua4_8 = new Wall(new float[]{1560, 20, 1560, 120, 1680, 120, 1680, 240, 1800, 240, 1800, 360, 1900, 360, 1900, 240, 1800, 240, 1800, 120, 1680, 120, 1680, 20});
        Wall agua4_9 = new Wall(new float[]{720, 240, 1200, 240, 1200, 720, 720, 720});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p22 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p32 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        Puerta p41 = new Puerta(840, 0, 240, 5, 0, 2, 3);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(agua1_1);
        walls1.add(agua1_2);
        walls1.add(agua1_3);
        walls1.add(agua1_4);
        walls1.add(agua1_5);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(agua2_1);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(agua3_1);
        walls3.add(agua3_2);
        walls3.add(agua3_3);
        walls3.add(agua3_4);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        puertas3.add(p32);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(agua4_1);
        walls4.add(agua4_2);
        walls4.add(agua4_3);
        walls4.add(agua4_4);
        walls4.add(agua4_5);
        walls4.add(agua4_6);
        walls4.add(agua4_7);
        walls4.add(agua4_8);
        walls4.add(agua4_9);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p41);
        
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
