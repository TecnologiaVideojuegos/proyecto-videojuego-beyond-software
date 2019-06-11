package states;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import rooms.Sala;
import serialization.UtilJugador;
import collisions.Wall;
import collisions.Puerta;
import entities.Jugador;
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
public class Nivel2 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 5;
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego","Volver al inicio"};
    private int selected;
    private boolean paused = false;
    private Image image;
    private Music nivel2;
    private Sound select;
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        UtilJugador.cargarDatos();
        player = UtilJugador.retrieveJugador(1000, 400, proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 2_v1.png", 1920, 1080);
        image = new Image("resources/intro/fondo_5.png");
        select = new Sound("resources/sonidos/Select.ogg");
        nivel2 = new Music("resources/sonidos/planta.ogg");
        nivel2.loop();
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840,20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_5 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_6 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20});
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
        //Wall pinchos4_1 = new Wall(new float[]{20, 20, 20, 220, 110, 220, 110, 110, 220, 110, 220, 20});
        //Wall pinchos4_2 = new Wall(new float[]{20, 740, 110, 740, 110, 850, 220, 850, 220, 940, 20, 940});
        //Wall pinchos4_3 = new Wall(new float[]{1700, 940, 1700, 850, 1810, 850, 1810, 740, 1900, 740, 1900, 940});
        //Wall pinchos4_4 = new Wall(new float[]{1700, 20, 1700, 110, 1810, 110, 1810, 220, 1900, 220, 1900, 20});
        //Wall pinchos4_5 = new Wall(new float[]{620, 260, 700, 260, 700, 340, 620, 340});
        //Wall pinchos4_6 = new Wall(new float[]{1220, 260, 1300, 260, 1300, 340, 1220, 340});
        //Wall pinchos4_7 = new Wall(new float[]{1220, 740, 1300, 740, 1300, 820, 1220, 820});
        //Wall pinchos4_8 = new Wall(new float[]{620, 740, 700, 740, 700, 820, 620, 820});
        Wall pinchos5_1 = new Wall(new float[]{140, 620, 220, 620, 220, 700, 140, 700});
        Wall pinchos5_2 = new Wall(new float[]{1820, 740, 1820, 860, 1700, 860, 1700, 940, 1900, 940,  1900, 740, 1820, 740});
        Wall pinchos5_3 = new Wall(new float[]{1690, 620, 1690, 700, 1780, 700, 1780, 620});
        Wall pinchos6_1 = new Wall(new float[]{0, 740, 340, 740, 340, 820, 230, 820, 230, 940, 0, 940});
        Wall pinchos6_2 = new Wall(new float[]{260, 140, 260, 460, 340, 460, 340, 140});
        Wall pinchos6_3 = new Wall(new float[]{1710, 20, 1710, 100, 1820, 100, 1820, 220, 1900, 220, 1900, 20});
        Wall pinchos6_4 = new Wall(new float[]{1580, 620, 1580, 840, 1660, 840, 1660, 620});
        Puerta p1_1 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p2_1 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p2_2 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p2_3 = new Puerta(840, 0, 240, 5, 0, 6, 0);
        Puerta p3_1 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p3_2 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        //Puerta p4_1 = new Puerta(840, 0, 240, 5, 0, 2, 3);
        Puerta p5_1 = new Puerta(1915, 360, 5, 240, 1, 6, 0);
        Puerta p6_1 = new Puerta(0, 360, 5, 240, 3, 5, 0);
        Puerta p6_2 = new Puerta(840, 960, 240, 5, 2, 2, 0);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(pinchos1_1);
        walls1.add(pinchos1_2);
        walls1.add(pinchos1_3);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p1_1);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(pinchos2_1);
        walls2.add(pinchos2_2);
        walls2.add(pinchos2_3);
        walls2.add(pinchos2_4);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p2_1);
        puertas2.add(p2_2);
        puertas2.add(p2_3);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(pinchos3_1);
        walls3.add(pinchos3_2);
        walls3.add(pinchos3_3);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p3_1);
        puertas3.add(p3_2);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        //walls4.add(pinchos4_1);
        //walls4.add(pinchos4_2);
        //walls4.add(pinchos4_3);
        //walls4.add(pinchos4_4);
        //walls4.add(pinchos4_5);
        //walls4.add(pinchos4_6);
        //walls4.add(pinchos4_7);
        //walls4.add(pinchos4_8);
        ArrayList<Puerta> puertas4 = new ArrayList();
        //puertas4.add(p4_1);
        
        ArrayList<Wall> walls5 = new ArrayList<>();
        walls5.add(limites_5);
        walls5.add(pinchos5_1);
        walls5.add(pinchos5_2);
        walls5.add(pinchos5_3);
        ArrayList<Puerta> puertas5 = new ArrayList();
        puertas5.add(p5_1);
        
        ArrayList<Wall> walls6 = new ArrayList<>();
        walls6.add(limites_6);
        walls6.add(pinchos6_1);
        walls6.add(pinchos6_2);
        walls6.add(pinchos6_3);
        walls6.add(pinchos6_4);
        ArrayList<Puerta> puertas6 = new ArrayList();
        puertas6.add(p6_1);
        puertas6.add(p6_2);
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, null, null, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, null, null, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, null, null, player, proyectiles);
        Sala sala4 = new Sala(mapa.getSubImage(2, 0), walls4, puertas4, null, null, player, proyectiles);
        Sala sala5 = new Sala(mapa.getSubImage(0, 0), walls5, puertas5, null, null, player, proyectiles);
        Sala sala6 = new Sala(mapa.getSubImage(1, 0), walls6, puertas6, null, null, player, proyectiles);
        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
        salas.add(sala5);
        salas.add(sala6);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        if(container.isPaused())
        {
            g.setBackground(Color.black);
            g.drawImage(image, 0, 0);
            g.setColor(Color.white);
            g.drawString("PAUSA", 955, 400);
            g.setColor(Color.white);
            
            
            for (int i=0;i<options.length;i++) {
			g.drawString(options[i], 920, 475+(i*50));
			if (selected == i) {
				g.drawRect(890, 470+(i*50),200,30);
			}
		}
        }else{
        salas.get(salaActual-1).draw(g, entrada);
        }
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if(container.isPaused()) {
                nivel2.resume();
            }else nivel2.pause();
            container.setPaused(!container.isPaused());
            paused=!paused;
        }
        
        if(container.isPaused()) {
            if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
            switch(selected) {
                case 0:
                    container.setPaused(!container.isPaused());
                    paused=!paused;
                    break;
                case 2:
                    game.enterState(2);
                    break;
                }
            }   
        }
        if(player.getVida() <= 0) {
            game.enterState(17);
        }
        else {
            int n = salas.get(salaActual-1).update(entrada, delta);
            if(n!=0) {
                if(n != salaActual) {
                    salas.get(salaActual-1).getGestor().resetProyectiles();
                }
                if(n == 99){
                    UtilJugador.guardarDatos(player, 3);
                    game.addState(new Nivel3());
                    game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }
                else {
                    salaActual = n;
                }
            }
        }
    }
    
    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        container.getInput().clearKeyPressedRecord();
        init(container, game);     
    }
    
    @Override
    public void keyReleased(int key, char c) {
        if(paused)
        {
		if (key == Input.KEY_S) {
                        select.play();
			selected++;
			if (selected >= options.length) {
				selected = 0;
			}
		}
		if (key == Input.KEY_W) {
                        select.play();
			selected--;
			if (selected < 0) {
				selected = options.length - 1;
			}
		}
        }
	} 
    
    
}
