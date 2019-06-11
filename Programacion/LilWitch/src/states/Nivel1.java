package states;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import rooms.Sala;
import rooms.SalaBoss;
import rooms.SalaCofre;
import entities.Enemigo;
import serialization.UtilJugador;
import collisions.Wall;
import collisions.Puerta;
import collisions.Objeto;
import entities.Jugador;
import entities.Slime;
import entities.Murcielago;
import java.io.File;
import java.util.ArrayList;
import logic.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


/**
 *
 * @author alvar
 */
public class Nivel1 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual;
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego","Controles", "Menú Principal"};
    private int selected, contadorMusica;
    private boolean paused = false;
    private Image fondoPausa;
    private Image controles;
    private Music nivel1, jefe;
    private Sound select;
    
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        salaActual = 6;
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador(920, 700, proyectiles);
        UtilJugador.guardarDatos(player, 1);
        mapa = new SpriteSheet("resources/niveles/Nivel 1_v1.png", 1920, 1080);
        fondoPausa = new Image("resources/intro/fondo_5.png");
        controles = new Image("resources/intro/controles.png");
        nivel1 = new Music("resources/sonidos/Caves.ogg");
        nivel1.loop();
        select = new Sound("resources/sonidos/Select.ogg");
        jefe = new Music("resources/sonidos/Boss_Music.ogg");
        this.contadorMusica = 0;
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940,1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_4 = new Wall(new float[]{20,20, 20,940, 840,940, 840,960, 1080,960, 1080,940, 1900,940, 1900,20, 1080,20, 1080,0, 840,0, 840,20});
        Wall limites_5 = new Wall(new float[]{20, 20, 20, 940, 1900, 940, 1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites_6 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 1900, 940, 1900, 20});
        Wall abismo1_1 = new Wall(new float[]{600,0, 600,120, 600,240, 480,240, 480,360, 360,360, 360,480, 0,480, 0,960, 1920,960, 1920,0});
        Wall abismo1_2 = new Wall(new float[]{1320, 240,1320,360, 1200,360, 1200,480, 1080,480, 1080,600, 1320,600, 1320,480, 1440,480, 1440,360, 1800,360, 1800,240});
        Wall abismo1_3 = new Wall(new float[]{1680,600, 1680,720, 1560,720, 1560,840, 1800,840, 1800,600});
        Wall abismo2_1 = new Wall(new float[]{0,0, 0,960, 1920,960, 1920,0});
        Wall abismo2_2 = new Wall(new float[]{360,240, 360,480, 600,480, 600,240});
        Wall abismo2_3 = new Wall(new float[]{1320,480, 1320,720, 1560,720, 1560,480});
        Wall abismo3_1 = new Wall(new float[]{840, 0, 840, 360, 600, 360, 600, 240, 720, 240, 720, 0, 840, 0});
        Wall abismo3_2 = new Wall(new float[]{0, 0, 0, 960, 1920, 960, 1920, 0, 0, 0});
        Wall abismo3_3 = new Wall(new float[]{0, 600, 120, 600, 120, 480, 240, 480, 240, 600, 360, 600, 360, 720, 720, 720, 720, 960, 0, 960});
        Wall abismo3_4 = new Wall(new float[]{1920, 960, 1800, 960, 1800, 840, 1560, 840, 1560, 720, 1800, 720, 1800, 840, 1920, 840});
        Wall abismo3_5 = new Wall(new float[]{1080, 0, 1080, 360, 1200, 360, 1200, 480, 1320, 480, 1680, 480, 1680, 360, 1560, 360, 1560, 240, 1440, 240, 1440, 120, 1320, 120, 1320,0});
        //Wall abismo4_1 = new Wall(new float[]{20, 360, 240, 360, 240, 480, 360, 480, 360, 600, 600, 600, 600, 720, 720, 720, 720, 840, 840, 840, 840, 940, 20, 940});
        //Wall abismo4_2 = new Wall(new float[]{1080, 940, 1080, 720, 1200, 720, 1200, 600, 1560,  600, 1560, 480, 1680, 480, 1680, 360, 1900, 360, 1900, 940});
        Wall abismo5_1 = new Wall(new float[]{20, 840, 20, 940, 120, 940, 120, 840, 360, 840, 360, 720, 120, 720, 120, 840});
        Wall abismo5_2 = new Wall(new float[]{1220, 840, 1220, 940, 1320, 940, 1320, 840});
        Wall abismo5_3 = new Wall(new float[]{1800, 620, 1800, 680, 1900, 680, 1900, 620});
        Wall abismo5_4 = new Wall(new float[]{1080, 20, 1080, 360, 1320, 360, 1320, 240,1200, 240, 1200, 20});
        Wall abismo5_5 = new Wall(new float[]{720, 20, 720, 120, 840, 120, 840, 20});
        Wall abismo5_6 = new Wall(new float[]{480, 20, 480, 120, 600, 120, 600, 20});
        Wall abismo6_1 = new Wall(new float[]{900, 460, 900, 540, 1010, 540, 1010, 460});
        Puerta p1_1 = new Puerta(1915, 360, 5, 240, 1, 2, 1);
        Puerta p2_1 = new Puerta(1915, 360, 5, 240, 1, 3, 1);
        Puerta p2_2 = new Puerta(840, 955, 240, 5, 2, 5, 1);
        Puerta p2_3 = new Puerta(0, 360, 5, 240, 3, 1, 1);
        Puerta p3_1 = new Puerta(0, 360, 5, 240, 3, 2, 1);
        Puerta p3_2 = new Puerta(840, 0, 240, 5, 0, 4, 2);
        Puerta p4_1 = new Puerta(840, 955, 240, 5, 2, 3, 1);
        Puerta p4_2 = new Puerta(840, 0, 240, 5, 5, 99, 1);
        Puerta p5_1 = new Puerta(840, 0, 240, 5, 0, 2, 1);
        Puerta p5_2 = new Puerta(1915, 360, 5, 240, 1, 6, 1);
        Puerta p6_1 = new Puerta(0, 360, 5, 240, 3, 5, 1);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(abismo1_1);
        walls1.add(abismo1_2);
        walls1.add(abismo1_3);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p1_1);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(abismo2_1);
        walls2.add(abismo2_2);
        walls2.add(abismo2_3);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p2_1);
        puertas2.add(p2_2);
        puertas2.add(p2_3);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(abismo3_1);
        walls3.add(abismo3_2);
        walls3.add(abismo3_3);
        walls3.add(abismo3_4);
        walls3.add(abismo3_5);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p3_1);
        puertas3.add(p3_2);    
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        //walls4.add(abismo4_1);
        //walls4.add(abismo4_2);
        ArrayList<Puerta> puertas4 = new ArrayList();
        puertas4.add(p4_1);
        puertas4.add(p4_2);
        ArrayList<Image> imagenes4 = new ArrayList<>();
        imagenes4.add(mapa.getSubImage(2, 0));
        imagenes4.add(new Image("resources/niveles/Nivel 1_v1_final.png"));
        
        ArrayList<Wall> walls5 = new ArrayList<>();
        walls5.add(limites_5);
        walls5.add(abismo5_1);
        walls5.add(abismo5_2);
        walls5.add(abismo5_3);
        walls5.add(abismo5_4);
        walls5.add(abismo5_5);
        walls5.add(abismo5_6);
        ArrayList<Puerta> puertas5 = new ArrayList();
        puertas5.add(p5_1);
        puertas5.add(p5_2);
        
        ArrayList<Wall> walls6 = new ArrayList<>();
        walls6.add(limites_6);
        //walls6.add(abismo6_1);
        ArrayList<Puerta> puertas6 = new ArrayList();
        puertas6.add(p6_1);
        ArrayList<Objeto> objetos = new ArrayList();
        objetos.add(new Objeto(1, 910, 420));
        
        ArrayList<Enemigo> enemigos1 = new ArrayList<>();
        Murcielago m1_1 = new Murcielago("bat.png", 120, 120, 960, 400, 3, 1);
        Murcielago m1_3 = new Murcielago("bat.png", 120, 120, 1080, 800, 3, 1);
        Slime s1_1 = new Slime("slime_gris.png", 120, 150, 1000, 200, 4, 1);
        enemigos1.add(s1_1);
        enemigos1.add(m1_1);
        
        enemigos1.add(m1_3);
        
        ArrayList<Enemigo> enemigos2 = new ArrayList<>();
        Murcielago m2_1 = new Murcielago("bat.png", 120, 120, 960, 400, 3, 1);
        Murcielago m2_2 = new Murcielago("bat.png", 120, 120, 200, 200, 3, 1);
        Slime s2_1 = new Slime("slime_gris.png", 120, 150, 1000, 200, 4, 1);
        
        enemigos2.add(s2_1);
        enemigos2.add(m2_1);
        enemigos2.add(m2_2);
        
        ArrayList<Enemigo> enemigos3 = new ArrayList<>();
        Murcielago m3_1 = new Murcielago("bat.png", 120, 120, 960, 400, 5, 1);
        Murcielago m3_2 = new Murcielago("bat.png", 120, 120, 1160, 720, 5, 1);
        Slime s3_1 = new Slime("slime_gris.png", 120, 150, 120, 200, 4, 1);
        Slime s3_2 = new Slime("slime_gris.png", 120, 150, 720, 720, 4, 1);
        Slime s3_3 = new Slime("slime_gris.png", 120, 150, 960, 200, 4, 1);        
        enemigos3.add(s3_1);
        enemigos3.add(s3_2);
        enemigos3.add(s3_3);
        enemigos3.add(m3_1);
        enemigos3.add(m3_2);
        
        ArrayList<Enemigo> enemigos5 = new ArrayList<>();
        Murcielago m5_1 = new Murcielago("bat.png", 120, 120, 720, 720, 5, 1);
         enemigos5.add(m5_1);

        SalaCofre sala1 = new SalaCofre(mapa.getSubImage(0, 1), walls1, puertas1, enemigos1, player, proyectiles, 8, 0, 120, 720);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, enemigos2, null, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, enemigos3, null, player, proyectiles);
        SalaBoss sala4 = new SalaBoss(imagenes4, walls4, puertas4, 1, player, proyectiles);
        Sala sala5 = new Sala(mapa.getSubImage(0, 0), walls5, puertas5, enemigos5, null, player, proyectiles);
        Sala sala6 = new Sala(mapa.getSubImage(1, 0), walls6, puertas6, null, objetos, player, proyectiles);
        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
        salas.add(sala5);
        salas.add(sala6);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {   
        if(container.isPaused()) {
            g.setBackground(Color.black);
            g.drawImage(fondoPausa, 0, 0);
            g.setColor(Color.white);
            g.drawString("PAUSA", 955, 400);
            g.setColor(Color.white);
            
            for (int i=0;i<options.length;i++) {
			g.drawString(options[i], 920, 475+(i*50));
			if (selected == i) {
				g.drawRect(890, 470+(i*50),200,30);
			}
		}
        } 
        else{
            salas.get(salaActual-1).draw(g, entrada);
        } 
    }
    
    public void updateMusica() {
        if(salaActual == 4 && contadorMusica == 0) {
            nivel1.stop();
            jefe.loop();
            contadorMusica ++;
        }
        else if(salas.get(4-1).getvidaBoss() <= 0) {
            if(!nivel1.playing()) {
                jefe.stop();
                nivel1.loop();
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        updateMusica();
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if(container.isPaused()) {
                nivel1.resume();
            }else nivel1.pause();
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
            jefe.stop();
            nivel1.stop();
            game.enterState(17);
        }
        else {
            int n = salas.get(salaActual-1).update(entrada, delta);
            if(n!=0) {
                if(n != salaActual) {
                    salas.get(salaActual-1).getGestor().resetProyectiles();
                }
                if(n == 99){
                    nivel1.stop();
                    UtilJugador.guardarDatos(player, 2);
                    game.addState(new Nivel2());
                    game.enterState(20, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
        if(paused) {
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
