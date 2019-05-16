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
public class Nivel5 extends BasicGameState{
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
        mapa = new SpriteSheet("resources/Nivel 5.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{100, 100, 100, 860, 840, 860, 840, 960, 1080, 960, 1080, 860, 1820, 860, 1820, 600, 1920, 600, 1920, 360, 1820, 360, 1820, 100});
        Wall limites_2 = new Wall(new float[]{100, 100, 100, 360, 0, 360, 0, 600, 100, 600, 100, 860, 1820, 860, 1820, 600, 1920, 600, 1920, 360, 1820, 360, 1820, 100, 1080, 100, 1080, 0, 840, 0, 840, 100});
        Wall limites_3 = new Wall(new float[]{100, 100, 100, 360, 0, 360, 0, 600, 100, 600, 100, 860, 1820, 860, 1820, 100});
        Wall limites_4 = new Wall(new float[]{100, 100, 100, 860, 840, 860, 840, 960, 1080, 960, 1080, 860, 1820, 860, 1820, 100});
        Wall oscuridad1_1 = new Wall(new float[]{360, 100, 360, 240, 240, 240, 240, 360, 100, 360});
        Wall oscuridad1_2 = new Wall(new float[]{100, 600, 240, 600, 240, 720, 360, 720, 360, 860, 100, 860});
        Wall oscuridad1_3 = new Wall(new float[]{1560, 860, 1560, 720, 1680, 720, 1680, 600, 1820, 600, 1820, 860});
        Wall oscuridad1_4 = new Wall(new float[]{1560, 100, 1560, 240, 1680, 240, 1680, 360, 1820, 360, 1820, 100});
        Wall oscuridad2_1 = new Wall(new float[]{360, 100, 360, 240, 240, 240, 240, 360, 100, 360});
        Wall oscuridad2_2 = new Wall(new float[]{100, 600, 240, 600, 240, 720, 360, 720, 360, 860, 100, 860});
        Wall oscuridad2_3 = new Wall(new float[]{1560, 860, 1560, 720, 1680, 720, 1680, 600, 1820, 600, 1820, 860});
        Wall oscuridad2_4 = new Wall(new float[]{1560, 100, 1560, 240, 1680, 240, 1680, 360, 1820, 360, 1820, 100});
        Wall oscuridad2_5 = new Wall(new float[]{840, 360, 840, 480, 720, 480, 720, 600, 840, 600, 840, 720, 1080, 720, 1080, 600, 1200, 600, 1200, 480, 1080, 480, 1080, 360});
        Wall oscuridad3_1 = new Wall(new float[]{100, 600, 240, 600, 240, 720, 360, 720, 360, 840});
        Wall oscuridad3_2 = new Wall(new float[]{600, 240, 600, 360, 480, 360, 480, 480, 600, 480, 600, 600, 720, 600, 720, 480, 840, 480, 840, 360, 720, 360, 720, 240});
        Wall oscuridad3_3 = new Wall(new float[]{1320, 100, 1320, 240, 1560, 240, 1560, 360, 1680, 360, 1680, 480, 1820, 480, 1820, 100 });
        Wall oscuridad3_4 = new Wall(new float[]{1200, 840,1200, 720, 1080, 720, 1080, 600, 1200, 600, 1200, 480, 1320, 480, 1320, 600, 1440, 600, 1440, 720, 1320, 720, 1320, 840});
        Wall oscuridad4_1 = new Wall(new float[]{840, 360, 840, 600, 1080, 600, 1080, 360});
        //Wall oscuridad4_2 = new Wall(new float[]{});
        //Wall oscuridad4_3 = new Wall(new float[]{});
        //Wall oscuridad4_4 = new Wall(new float[]{});
        //Wall oscuridad4_5 = new Wall(new float[]{});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p22 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p23 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(oscuridad1_1);
        walls1.add(oscuridad1_2);
        walls1.add(oscuridad1_3);
        walls1.add(oscuridad1_4);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(oscuridad2_1);
        walls2.add(oscuridad2_2);
        walls2.add(oscuridad2_3);
        walls2.add(oscuridad2_4);
        walls2.add(oscuridad2_5);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        puertas2.add(p23);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(oscuridad3_1);
        walls3.add(oscuridad3_2);
        walls3.add(oscuridad3_3);
        walls3.add(oscuridad3_4);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(oscuridad4_1);
        //walls4.add(oscuridad4_2);
        //walls4.add(oscuridad4_3);
        //walls4.add(oscuridad4_4);
        //walls4.add(oscuridad4_5);
        
        ArrayList<Puerta> puertas4 = new ArrayList(); 
        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, player);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, player);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, player);
        Sala sala4 = new Sala(mapa.getSubImage(1, 0), walls4, puertas4, player);
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
