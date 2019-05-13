/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import logic.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author cxabi
 */
public class Nivel4 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 4;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador();
        mapa = new SpriteSheet("resources/Nivel 4.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{20, 240, 20, 1080, 1680, 1080, 1680, 960, 1800, 960, 1800, 840, 1900 , 840, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20, 240, 20, 240, 120, 120, 120, 120, 240});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 840, 120, 840, 120, 960, 240, 960, 240, 1080, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1920, 360, 1920, 240, 1800, 240, 1800, 120, 1680, 120, 1680, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 120, 600, 120, 720, 120, 840, 240, 840, 240, 960, 360 , 960, 360, 1080, 840, 1080, 1080, 1080, 1080, 940, 1900, 940, 1900, 480, 1920, 480, 1800, 480, 1800, 240, 1680, 240, 1680, 120, 1440, 120, 1440, 20});
        Wall limites_4 = new Wall(new float[]{20, 240, 20, 840, 0, 840, 120, 840, 120, 960, 240, 960, 240, 1080, 840, 1080, 1080, 1080, 1680, 1080, 1680, 960, 1080, 960, 1080, 840, 1920, 840, 1920, 840, 1800, 240, 1800, 120, 1680, 120, 1680, 20, 240, 20, 240, 120, 120, 120, 120, 240});
        Wall arbol1_1 = new Wall(new float[]{120, 840, 240, 840, 240, 960, 120, 960});
        Wall arbol2_1 = new Wall(new float[]{1560, 840, 1680, 840, 1680, 960, 1560, 960});
        Wall arbol3_1 = new Wall(new float[]{1440, 720, 1560, 720, 1560, 840, 1440, 840});
        Wall arbol4_1 = new Wall(new float[]{600, 240, 720, 240, 720, 360, 600, 360});
        Wall arbol4_2 = new Wall(new float[]{1200, 240, 1320, 240, 1320, 360, 1200, 360});
        Wall arbol4_3 = new Wall(new float[]{600, 720, 720, 720, 720, 840, 600, 840});
        Wall arbol4_4 = new Wall(new float[]{1200, 720, 1320, 720, 1320, 840, 1200, 840});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p12 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p22 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p41 = new Puerta(840, 0, 240, 5, 0, 2, 4);
        
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(arbol1_1);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        puertas1.add(p12);
        
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(arbol2_1);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        
        
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(arbol4_1);
        walls4.add(arbol4_2);
        walls4.add(arbol4_3);
        walls4.add(arbol4_4);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p41);
        
        
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

        
    
    
    
  
   
