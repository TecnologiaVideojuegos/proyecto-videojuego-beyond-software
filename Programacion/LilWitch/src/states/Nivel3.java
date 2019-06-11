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
import entities.Enemigo;
import entities.Esqueleto;
import entities.Jugador;
import entities.Murcielago;
import entities.Slime;
import java.util.ArrayList;
import logic.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import rooms.SalaBoss;
import rooms.SalaCofre;


/**
 *
 * @author alvar
 */
public class Nivel3 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 2;
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego", "Controles", "Volver al inicio"};
    private int selected, contadorMusica;
    private boolean paused = false, mostrarControles;
    private Image image, controles;
    private Music nivel3, jefe;
    private Sound select;
    
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        UtilJugador.cargarDatos();
        player = UtilJugador.retrieveJugador(900, 700, proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 3_v1.png", 1920, 1080);
        image = new Image("resources/intro/fondo_5.png");
        select = new Sound("resources/sonidos/Select.ogg");
        nivel3 = new Music("resources/sonidos/agua.ogg");
        nivel3.loop();
        jefe = new Music("resources/sonidos/Boss_Music.ogg");
        this.contadorMusica = 0;
        this.mostrarControles = false;
        controles = new Image("resources/intro/controles.png");
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_5 = new Wall(new float[]{20, 20, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_6 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20});
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
        Wall agua5_1 = new Wall(new float[]{240, 240, 240, 360, 360, 360, 360, 240});
        Wall agua5_2 = new Wall(new float[]{240, 480, 240, 600, 360, 600, 360, 480});
        Wall agua5_3 = new Wall(new float[]{240, 720, 240, 840, 360, 840, 360, 720});
        Wall agua5_4 = new Wall(new float[]{840, 360, 840, 480, 960, 480, 960, 600, 1080, 600, 1080, 480, 960, 480, 960, 360});
        Wall agua5_5 = new Wall(new float[]{1440, 720, 1440, 840, 1800, 840, 1800, 720});
        Wall agua5_6 = new Wall(new float[]{1440, 20, 1440, 120, 1560, 120, 1560, 240, 1680, 240, 1680, 120, 1800, 120, 1800, 20});
        Wall agua6_1 = new Wall(new float[]{120, 120, 120, 240, 480, 240, 480, 120});
        Wall agua6_2 = new Wall(new float[]{120, 840, 120, 940, 480, 940, 480, 840, 360, 840, 360, 720, 240, 720, 240, 840});
        Wall agua6_3 = new Wall(new float[]{840, 360, 840, 480, 960, 480, 960, 600, 1080, 600, 1080, 480, 960, 480, 960, 360});
        Puerta p1_1 = new Puerta(1915, 360, 5, 240, 1, 3, 0);
        Puerta p1_2 = new Puerta(840, 960, 240, 5, 2, 2, 0);
        Puerta p2_1 = new Puerta(840, 0, 240, 5, 0, 1, 0);
        Puerta p3_1 = new Puerta(0, 360, 5, 240, 3, 1, 0);
        Puerta p3_2 = new Puerta(840, 0, 240, 5, 0, 6, 0);
        Puerta p4_1 = new Puerta(840, 955, 240, 5, 2, 5, 1);
        Puerta p4_2 = new Puerta(840, 0, 240, 5, 5, 99, 1);
        Puerta p5_1 = new Puerta(840, 0, 240, 5, 0, 4, 0);
        Puerta p5_2 = new Puerta(1915, 360, 5, 240, 1, 6, 0);
        Puerta p6_1 = new Puerta(0, 360, 5, 240, 3, 5, 0);
        Puerta p6_2 = new Puerta(840, 960, 240, 5, 2, 3, 0);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(agua1_1);
        walls1.add(agua1_2);
        walls1.add(agua1_3);
        walls1.add(agua1_4);
        walls1.add(agua1_5);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p1_1);
        puertas1.add(p1_2);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(agua2_1);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p2_1);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(agua3_1);
        walls3.add(agua3_2);
        walls3.add(agua3_3);
        walls3.add(agua3_4);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p3_1);
        puertas3.add(p3_2);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p4_1);
        puertas4.add(p4_2);
        ArrayList<Image> imagenes4 = new ArrayList<>();
        imagenes4.add(mapa.getSubImage(0, 0));
        imagenes4.add(new Image("resources/niveles/Nivel 3_v1_final.png"));
        
        ArrayList<Wall> walls5 = new ArrayList<>();
        walls5.add(limites_5);
        walls5.add(agua5_1);
        walls5.add(agua5_2);
        walls5.add(agua5_3);
        walls5.add(agua5_4);
        walls5.add(agua5_5);
        walls5.add(agua5_6);
        ArrayList<Puerta> puertas5 = new ArrayList();
        puertas5.add(p5_1);
        puertas5.add(p5_2);
        
        ArrayList<Wall> walls6 = new ArrayList<>();
        walls6.add(limites_6);
        walls6.add(agua6_1);
        walls6.add(agua6_2);
        walls6.add(agua6_3);
        ArrayList<Puerta> puertas6 = new ArrayList();
        puertas6.add(p6_1);
        puertas6.add(p6_2);
        
        ArrayList<Enemigo> enemigos1 = new ArrayList<>();
        Slime s1_1 = new Slime("slime_2.png", 120, 150, 125, 240, 4, 1);
        Slime s1_2 = new Slime("slime_2.png", 120, 150, 1080, 240, 4, 1);
        enemigos1.add(s1_1);
        enemigos1.add(s1_2);
        
        ArrayList<Enemigo> enemigos3 = new ArrayList<>();
        Esqueleto e3_1 = new Esqueleto("skeleton_3.png", 120, 150, 480, 360, 1000, 10, 1, proyectiles);
        Esqueleto e3_2 = new Esqueleto("skeleton_3.png", 120, 150, 1200, 480, 1000, 10, 1, proyectiles);
        enemigos3.add(e3_1);
        enemigos3.add(e3_2);
        
        ArrayList<Enemigo> enemigos5 = new ArrayList<>();
        Esqueleto e5_1 = new Esqueleto("skeleton_3.png", 120, 150, 480, 360, 1000, 10, 1, proyectiles);
        Esqueleto e5_2 = new Esqueleto("skeleton_3.png", 120, 150, 480, 600, 1000, 10, 1, proyectiles);
        Slime s5_1 = new Slime("slime_2.png", 120, 150, 1200, 480, 4, 1);
        enemigos5.add(e5_1);
        enemigos5.add(e5_2);
        enemigos5.add(s5_1);
        
        ArrayList<Enemigo> enemigos6 = new ArrayList<>();
        Esqueleto e6_1 = new Esqueleto("skeleton_3.png", 120, 150, 600, 480, 1000, 10, 1, proyectiles);
        Esqueleto e6_2 = new Esqueleto("skeleton_3.png", 120, 150, 1440, 240, 1000, 10, 1, proyectiles);
        Esqueleto e6_3 = new Esqueleto("skeleton_3.png", 120, 150, 1440, 600, 1000, 10, 1, proyectiles);
        enemigos6.add(e6_1);
        enemigos6.add(e6_2);
        enemigos6.add(e6_3);
        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, enemigos1, null, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, null, null, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, enemigos3, null, player, proyectiles);
        SalaBoss sala4 = new SalaBoss(imagenes4, walls4, puertas4, 3, player, proyectiles);
        Sala sala5 = new Sala(mapa.getSubImage(1, 0), walls5, puertas5, enemigos5, null, player, proyectiles);
        SalaCofre sala6 = new SalaCofre(mapa.getSubImage(2, 0), walls6, puertas6, enemigos6, player, proyectiles, 8, 1 , 240, 480);
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
            if(mostrarControles) {
                controles.draw(0,0);
            }
        }else{
        salas.get(salaActual-1).draw(g, entrada);
        }
        
    }
    
    public void updateMusica() {
        if(salaActual == 4 && contadorMusica == 0) {
            nivel3.stop();
            jefe.loop();
            contadorMusica ++;
        }
        else if(salas.get(4-1).getvidaBoss() <= 0) {
            if(!nivel3.playing()) {
                jefe.stop();
                nivel3.loop();
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        updateMusica();
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if(container.isPaused()) {
                nivel3.resume();
            }else nivel3.pause();
            container.setPaused(!container.isPaused());
            paused=!paused;
        }
        
        if(container.isPaused()) {
            if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
                switch(selected) {
                        case 0:
                            if(!mostrarControles) {
                                container.setPaused(!container.isPaused());
                                paused=!paused;
                            }
                            else {
                                mostrarControles = !mostrarControles;
                            }
                            break;
                        case 1:
                            mostrarControles = !mostrarControles;
                            break;
                        case 2:
                            if(!mostrarControles) {
                                game.enterState(2);
                            }
                            else {
                                mostrarControles = !mostrarControles;
                            }
                            break;
                }
            }
        }
        if(player.getVida() <= 0) {
            jefe.stop();
            nivel3.stop();
            game.enterState(17);
        }
        else {
            int n = salas.get(salaActual-1).update(entrada, delta);
            if(n!=0) {
                if(n != salaActual) {
                    salas.get(salaActual-1).getGestor().resetProyectiles();
                }
                if(n == 99){
                    nivel3.stop();
                    UtilJugador.guardarDatos(player, 4);
                    game.addState(new Nivel4());
                    game.enterState(22, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
