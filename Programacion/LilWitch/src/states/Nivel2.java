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
public class Nivel2 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual;
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego", "Controles", "Volver al inicio"};
    private int selected, contadorMusica;
    private boolean paused = false, mostrarControles;
    private Image image, controles;
    private Music nivel2, jefe;
    private Music historia;
    private Sound select;
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salaActual = 5;
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        UtilJugador.cargarDatos();
        player = UtilJugador.retrieveJugador(1000, 400, proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 2_v1.png", 1920, 1080);
        image = new Image("resources/intro/fondo_5.png");
        select = new Sound("resources/sonidos/Select.ogg");
        nivel2 = new Music("resources/sonidos/planta.ogg");
        historia = new Music("resources/sonidos/historia.ogg");
        nivel2.loop();
        jefe = new Music("resources/sonidos/Boss_Music.ogg");
        this.contadorMusica = 0;
        controles = new Image("resources/intro/controles.png");
        this.mostrarControles = false;
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
        Puerta p4_1 = new Puerta(840, 955, 240, 5, 2, 3, 1);
        Puerta p4_2 = new Puerta(840, 0, 240, 5, 5, 99, 1);
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
        puertas4.add(p4_1);
        puertas4.add(p4_2);
        ArrayList<Image> imagenes4 = new ArrayList<>();
        imagenes4.add(mapa.getSubImage(2, 0));
        imagenes4.add(new Image("resources/niveles/Nivel 2_v1_final.png"));
        
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
        
        ArrayList<Enemigo> enemigos1 = new ArrayList<>();
        Esqueleto e1_1 = new Esqueleto("skeleton.png", 120, 150, 360, 240, 1000, 5, 1, proyectiles);
        Esqueleto e1_2 = new Esqueleto("skeleton.png", 120, 150, 1680, 240, 1000, 5, 1, proyectiles);
        enemigos1.add(e1_1);
        enemigos1.add(e1_2);
        
        ArrayList<Enemigo> enemigos2 = new ArrayList<>();
        Slime s2_1 = new Slime("slime_3.png", 120, 150, 480, 240, 4, 1);
        Slime s2_2 = new Slime("slime_3.png", 120, 150, 1680, 240, 4, 1);
        Slime s2_3 = new Slime("slime_3.png", 120, 150, 1680, 600, 4, 1);
        Slime s2_4 = new Slime("slime_3.png", 120, 150, 480, 600, 4, 1);
        enemigos2.add(s2_1);
        enemigos2.add(s2_2);
        enemigos2.add(s2_3);
        enemigos2.add(s2_4);
        
        ArrayList<Enemigo> enemigos3 = new ArrayList<>();
        Murcielago m3_1 = new Murcielago("bat.png", 120, 120, 600, 240, 3, 1);
        Murcielago m3_2 = new Murcielago("bat.png", 120, 120, 1560, 240, 3, 1);
        Murcielago m3_3 = new Murcielago("bat.png", 120, 120, 1560, 600, 3, 1);
        Murcielago m3_4 = new Murcielago("bat.png", 120, 120, 600, 600, 3, 1);
        enemigos3.add(m3_1);
        enemigos3.add(m3_2);
        enemigos3.add(m3_3);
        enemigos3.add(m3_4);
        
        ArrayList<Enemigo> enemigos6 = new ArrayList<>();
        Esqueleto e6_1 = new Esqueleto("skeleton.png", 120, 150, 960, 240, 1000, 5, 1, proyectiles);
        Slime s6_1 = new Slime("slime_3.png", 120, 150, 600, 600, 5, 1);
        Slime s6_2 = new Slime("slime_3.png", 120, 150, 1400, 600, 5, 1);
        enemigos6.add(e6_1);
        enemigos6.add(s6_1);
        enemigos6.add(s6_2);
        
        SalaCofre sala1 = new SalaCofre(mapa.getSubImage(0, 1), walls1, puertas1, enemigos1, player, proyectiles, 8, 2 , 900, 720);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, enemigos2, null, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, enemigos3, null, player, proyectiles);
        SalaBoss sala4 = new SalaBoss(imagenes4, walls4, puertas4, 2, player, proyectiles);
        Sala sala5 = new Sala(mapa.getSubImage(0, 0), walls5, puertas5, null, null, player, proyectiles);
        Sala sala6 = new Sala(mapa.getSubImage(1, 0), walls6, puertas6, enemigos6, null, player, proyectiles);
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
            nivel2.stop();
            jefe.loop();
            contadorMusica ++;
        }
        else if(salas.get(4-1).getvidaBoss() <= 0) {
            if(!nivel2.playing()) {
                jefe.stop();
                nivel2.loop();
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        updateMusica();
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if(container.isPaused()) {
                nivel2.resume();
                mostrarControles = false;
            }else nivel2.pause();
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
                                container.setPaused(!container.isPaused());
                                paused=!paused;
                                historia.loop();
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
            nivel2.stop();
            game.enterState(17);
        }
        else {
            int n = salas.get(salaActual-1).update(entrada, delta);
            if(n!=0) {
                if(n != salaActual) {
                    salas.get(salaActual-1).getGestor().resetProyectiles();
                }
                if(n == 99){
                    nivel2.stop();
                    UtilJugador.guardarDatos(player, 3);
                    game.addState(new Nivel3());
                    game.enterState(21, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
