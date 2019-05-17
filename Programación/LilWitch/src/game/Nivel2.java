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
public class Nivel2 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 3;
    private ControladorProyectiles proyectiles;
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador(proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 2.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall fuego1_1 = new Wall(new float[]{120, 600, 240, 600, 240, 720, 120, 720});
        Wall fuego1_2 = new Wall(new float[]{360, 360, 480, 360, 480, 480, 360, 480});
        Wall fuego1_3 = new Wall(new float[]{720, 120, 1080, 120, 1080, 480, 720, 480});
        Wall fuego1_4 = new Wall(new float[]{720, 600, 1080, 600, 1080, 840, 720, 840});
        Wall fuego1_5 = new Wall(new float[]{1680, 120, 1800, 120, 1800, 240, 1680, 240});
        Wall fuego1_6 = new Wall(new float[]{1440, 720, 1560, 720, 1560, 840, 1440, 840});
        Wall fuego2_1 = new Wall(new float[]{120, 120, 840, 120, 840, 240, 720, 240, 720, 360, 120, 360});
        Wall fuego2_2 = new Wall(new float[]{120, 600, 240, 600, 240, 720, 120, 720});
        Wall fuego2_3 = new Wall(new float[]{360, 600, 600, 600, 600, 720, 960, 720, 960, 840, 360, 840});
        Wall fuego2_4 = new Wall(new float[]{1560, 720, 1680, 720, 1680, 600, 1800, 600, 1800, 840, 1560, 840});
        Wall fuego2_5 = new Wall(new float[]{840, 480, 960, 480, 960, 600, 840, 600});
        Wall fuego2_6 = new Wall(new float[]{1080, 120, 1800, 120, 1800, 360, 1560, 360, 1560, 240, 1440, 240, 1440, 360, 1320, 360, 1320, 240, 1200, 240, 1200, 360, 1080, 360});
        Wall fuego3_1 = new Wall(new float[]{120, 600, 360, 600, 360, 720, 480, 720, 480, 840, 120, 840});
        Wall fuego3_2 = new Wall(new float[]{480, 120, 720, 120, 720, 360, 480, 360});
        Wall fuego3_3 = new Wall(new float[]{1440, 120, 1800, 120, 1800, 360, 1560, 360, 1560, 240, 1440, 240});
        //Wall fuego3_4 = new Wall(new float[]{1320, 600, 1440, 600, 1440, 720, 1320, 720});
        Wall fuego4_1 = new Wall(new float[]{120, 120, 600, 120, 600, 240, 480, 240, 480, 360, 240, 360, 240, 480, 120, 480});
        Wall fuego4_2 = new Wall(new float[]{120, 600, 240, 600, 240, 720, 480, 720, 480, 840, 600, 840, 600, 940, 120, 940});
        Wall fuego4_3 = new Wall(new float[]{1320, 120, 1800, 120, 1800, 480, 1680, 480, 1680, 360, 1440, 360, 1440, 240, 1320, 240});
        Wall fuego4_4 = new Wall(new float[]{1680, 600, 1800, 600, 1800, 940, 1320, 940, 1320, 840, 1440, 840, 1440, 720, 1680, 720});
        Wall fuego4_5 = new Wall(new float[]{840, 240, 1080, 240, 1080, 360, 1200, 360, 1200, 600, 1080, 600, 1080, 720, 840, 720, 840, 600, 720, 600, 720, 360, 840, 360});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p22 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        Puerta p23 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p41 = new Puerta(840, 0, 240, 5, 0, 2, 3);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(fuego1_1);
        walls1.add(fuego1_2);
        walls1.add(fuego1_3);
        walls1.add(fuego1_4);
        walls1.add(fuego1_5);
        walls1.add(fuego1_6);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(fuego2_1);
        walls2.add(fuego2_2);
        walls2.add(fuego2_3);
        walls2.add(fuego2_4);
        walls2.add(fuego2_5);
        walls2.add(fuego2_6);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        puertas2.add(p23);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(fuego3_1);
        walls3.add(fuego3_2);
        walls3.add(fuego3_3);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(fuego4_1);
        walls4.add(fuego4_2);
        walls4.add(fuego4_3);
        walls4.add(fuego4_4);
        walls4.add(fuego4_5);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p41);
        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, player, proyectiles);
        Sala sala4 = new Sala(mapa.getSubImage(1, 0), walls4, puertas4, player, proyectiles);
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
        int n = salas.get(salaActual-1).update(entrada, delta);
        System.out.println(n);
        if(n!=0) salaActual = n;
    }
    
    
}
