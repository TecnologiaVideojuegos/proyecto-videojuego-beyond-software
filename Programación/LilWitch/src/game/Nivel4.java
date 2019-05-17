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
public class Nivel4 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 3;
    private ControladorProyectiles proyectiles;
    
    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador(proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 4.png", 1920, 1080);
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall pinchos1_1 = new Wall(new float[]{20, 20, 20, 220, 110, 220, 110, 110, 220, 110, 220, 20});
        Wall pinchos1_2 = new Wall(new float[]{140, 620, 220, 620, 220, 700, 140, 700});
        Wall pinchos1_3 = new Wall(new float[]{1700, 940, 1900, 940, 1900, 740, 1810, 740, 1810, 850, 1700, 850});
        Wall pinchos2_1 = new Wall(new float[]{260, 140, 340, 140, 340, 220, 260, 220});
        Wall pinchos2_2 = new Wall(new float[]{20, 740, 110, 740, 110, 850, 220, 850, 220, 940, 20, 940});
        Wall pinchos2_3 = new Wall(new float[]{1580, 740, 1660, 740, 1660, 820, 1580, 820});
        Wall pinchos2_4 = new Wall(new float[]{1700, 20, 1700, 110, 1810, 110, 1810, 220, 1900, 220, 1900, 20});
        Wall pinchos3_1 = new Wall(new float[]{20, 620, 110, 620, 110, 740, 220, 740, 220, 850, 340, 850, 340, 940, 20, 940});
        Wall pinchos3_2 = new Wall(new float[]{1460, 620, 1540, 620, 1540, 700, 1460, 700});
        Wall pinchos3_3 = new Wall(new float[]{1460, 20, 1460, 110, 1700, 110, 1700, 220, 1810, 220, 1810, 460, 1900, 460, 1900, 20});
        Wall pinchos4_1 = new Wall(new float[]{20, 20, 20, 220, 110, 220, 110, 110, 220, 110, 220, 20});
        Wall pinchos4_2 = new Wall(new float[]{20, 740, 110, 740, 110, 850, 220, 850, 220, 940, 20, 940});
        Wall pinchos4_3 = new Wall(new float[]{1700, 940, 1700, 850, 1810, 850, 1810, 740, 1900, 740, 1900, 940});
        Wall pinchos4_4 = new Wall(new float[]{1700, 20, 1700, 110, 1810, 110, 1810, 220, 1900, 220, 1900, 20});
        Wall pinchos4_5 = new Wall(new float[]{620, 260, 700, 260, 700, 340, 620, 340});
        Wall pinchos4_6 = new Wall(new float[]{1220, 260, 1300, 260, 1300, 340, 1220, 340});
        Wall pinchos4_7 = new Wall(new float[]{1220, 740, 1300, 740, 1300, 820, 1220, 820});
        Wall pinchos4_8 = new Wall(new float[]{620, 740, 700, 740, 700, 820, 620, 820});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p22 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p12 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        Puerta p41 = new Puerta(840, 0, 240, 5, 0, 2, 3);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(pinchos1_1);
        walls1.add(pinchos1_2);
        walls1.add(pinchos1_3);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        puertas1.add(p12);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(pinchos2_1);
        walls2.add(pinchos2_2);
        walls2.add(pinchos2_3);
        walls2.add(pinchos2_4);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(pinchos3_1);
        walls3.add(pinchos3_2);
        walls3.add(pinchos3_3);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(pinchos4_1);
        walls4.add(pinchos4_2);
        walls4.add(pinchos4_3);
        walls4.add(pinchos4_4);
        walls4.add(pinchos4_5);
        walls4.add(pinchos4_6);
        walls4.add(pinchos4_7);
        walls4.add(pinchos4_8);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p41);
        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, player, proyectiles);
        Sala sala4 = new Sala(mapa.getSubImage(0, 0), walls4, puertas4, player, proyectiles);
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
