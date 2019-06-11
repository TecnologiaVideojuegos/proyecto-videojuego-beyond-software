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
import rooms.SalaBoss;


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
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego","Volver al inicio"};
    private int selected;
    private boolean paused = false;
    private Image image;
    private Music nivel5;
    private Sound select;
    
    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        UtilJugador.cargarDatos();
        player = UtilJugador.retrieveJugador(1000, 400, proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 5_v1.png", 1920, 1080);
        image = new Image("resources/intro/fondo_5.png");
        nivel5 = new Music("resources/sonidos/oscuridad.ogg");
        //nivel5.loop();
        select = new Sound("resources/sonidos/Select.ogg");
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_5 = new Wall(new float[]{20, 20, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_6 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall oscuridad1_1 = new Wall(new float[]{120, 120, 120, 840, 240, 840, 240, 480, 360, 480, 360, 360, 480, 360, 480, 240, 600, 240, 600, 120, 480, 120, 480, 240, 360, 240, 360, 360, 240, 360, 240, 120});
        Wall oscuridad1_2 = new Wall(new float[]{1200, 120, 1200, 240, 1560, 240, 1560, 120});
        Wall oscuridad1_3 = new Wall(new float[]{1080, 360, 1080, 720, 1200, 720, 1200, 840, 1560, 840, 1560, 360, 1440, 360, 1440, 720, 1200, 720, 1200, 360});
        Wall oscuridad2_1 = new Wall(new float[]{240, 120, 240, 360, 360, 360, 360, 480, 240, 480, 240, 600, 360, 600, 360, 480, 480, 480, 480, 120});
        Wall oscuridad2_2 = new Wall(new float[]{1080, 120, 1080, 600, 1440, 600, 1440, 480, 1560, 480, 1560, 120, 1440, 120, 1440, 480, 1200, 480, 1200, 120});
        Wall oscuridad3_1 = new Wall(new float[]{240, 120, 240, 600, 360, 600, 360, 360, 480, 360, 480, 240, 720, 240, 720, 120, 480, 120, 480, 240, 360, 240, 360, 120});
        Wall oscuridad3_2 = new Wall(new float[]{1200, 120, 1200, 480, 1320, 480, 1320, 600, 1560, 600, 1560, 480, 1320, 480, 1320, 360, 1560, 360, 1560, 120});
        Wall oscuridad6_1 = new Wall(new float[]{600, 360, 600, 480, 1320, 480, 1320, 360});
        Wall oscuridad6_2 = new Wall(new float[]{600, 600, 600, 720, 1320, 720, 1320, 600});
        Puerta p1_1 = new Puerta(1915, 360, 5, 240, 1, 2, 0);
        Puerta p2_1 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p2_2 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p3_1 = new Puerta(0, 360, 5, 240, 3, 2, 0);
        Puerta p3_2 = new Puerta(840, 0, 240, 5, 0, 6, 0);
        Puerta p4_1 = new Puerta(840, 955, 240, 5, 2, 6, 1);
        Puerta p4_2 = new Puerta(840, 0, 240, 5, 5, 99, 1);
        Puerta p4_3 = new Puerta(20, 420, 5, 120, 3, 5, 0);
        Puerta p5_1 = new Puerta(1915, 360, 5, 240, 1, 4, 0);
        Puerta p6_1 = new Puerta(840, 960, 240, 5, 2, 3, 0);
        Puerta p6_2 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(oscuridad1_1);
        walls1.add(oscuridad1_2);
        walls1.add(oscuridad1_3);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p1_1);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(oscuridad2_1);
        walls2.add(oscuridad2_2);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p2_1);
        puertas2.add(p2_2);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(oscuridad3_1);
        walls3.add(oscuridad3_2);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p3_1);
        puertas3.add(p3_2);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p4_1);
        puertas4.add(p4_2);
        puertas4.add(p4_3);
        ArrayList<Image> imagenes4 = new ArrayList<>();
        imagenes4.add(mapa.getSubImage(1, 0));
        imagenes4.add(new Image("resources/niveles/Nivel 5_v1_final.png"));
        
        ArrayList<Wall> walls5 = new ArrayList<>();
        walls5.add(limites_5);
        ArrayList<Puerta> puertas5 = new ArrayList(); 
        puertas5.add(p5_1);
        ArrayList<Image> imagenes5 = new ArrayList<>();
        imagenes5.add(mapa.getSubImage(0, 0));
        imagenes5.add(mapa.getSubImage(0, 0));
        
        ArrayList<Wall> walls6 = new ArrayList<>();
        walls6.add(limites_6);
        walls6.add(oscuridad6_1);
        walls6.add(oscuridad6_2);
        ArrayList<Puerta> puertas6 = new ArrayList();
        puertas6.add(p6_1);
        puertas6.add(p6_2);
        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, null, null, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, null, null, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, null, null, player, proyectiles);
        SalaBoss sala4 = new SalaBoss(imagenes4, walls4, puertas4, 5, player, proyectiles);
        SalaBoss sala5 = new SalaBoss(imagenes5, walls5, puertas5, 6, player, proyectiles);
        Sala sala6 = new Sala(mapa.getSubImage(2, 0), walls6, puertas6, null, null, player, proyectiles);
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
                nivel5.resume();
            }else nivel5.pause();
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
                    game.addState(new Historia5());
                    game.enterState(24, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
