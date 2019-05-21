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
public class Nivel1 extends BasicGameState{
    private Input entrada;
    private Jugador player;
    private SpriteSheet mapa;
    private ArrayList<Sala> salas;
    private int salaActual = 2;
    private ControladorProyectiles proyectiles;
    private String[] options = new String[] {"Volver al juego","Volver al inicio"};
    private int selected;
    private boolean paused = false;
    private Image fondoPausa;
    private Music music;
    private Sound sound;
    
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        proyectiles = new ControladorProyectiles();
        salas = new ArrayList<>();
        entrada = container.getInput();
        player = new Jugador(proyectiles);
        mapa = new SpriteSheet("resources/niveles/Nivel 1_v1.png", 1920, 1080);
        fondoPausa = new Image("resources/intro/fondo_5.png");
        music = new Music("resources/sonidos/Caves.ogg");
        music.setVolume(0.5f);
        music.loop();
        Wall limites_1 = new Wall(new float[]{20, 20, 20, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites1 = new Wall(new float[]{480,0, 480,120, 600,120, 600,240, 480,240, 480,360, 360,360, 360,480, 0,480, 0,960, 1920,960, 1920,0});
        Wall abismo11 = new Wall(new float[]{1320, 240,1320,360, 1200,360, 1200,480, 1080,480, 1080,600, 1320,600, 1320,480, 1440,480, 1440,360, 1800,360, 1800,240});
        Wall abismo12 = new Wall(new float[]{1680,600, 1680,720, 1560,720, 1560,840, 1800,840, 1800,600});
        Wall limites_2 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940,1900, 600, 1920, 600, 1920, 360, 1900, 360, 1900, 20});
        Wall limites2 = new Wall(new float[]{0,0, 0,960, 1920,960, 1920,0});
        Wall abismo21 = new Wall(new float[]{360,240, 360,480, 600,480, 600,240});
        Wall abismo22 = new Wall(new float[]{1320,480, 1320,720, 1560,720, 1560,480});
        Wall planta2 = new Wall(new float[]{1340, 260, 1340, 340, 1420, 340, 1420, 260});
        Wall limites_3 = new Wall(new float[]{20, 20, 20, 360, 0, 360, 0, 600, 20, 600, 20, 940,1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall limites31 = new Wall(new float[]{840, 0, 840, 360, 600, 360, 600, 240, 720, 240, 720, 0, 840, 0});
        Wall limites32 = new Wall(new float[]{0, 0, 0, 960, 1920, 960, 1920, 0, 0, 0});
        Wall abismo31 = new Wall(new float[]{0, 600, 120, 600, 120, 480, 240, 480, 240, 600, 360, 600, 360, 720, 720, 720, 720, 960, 0, 960});
        Wall abismo32 = new Wall(new float[]{1920, 960, 1800, 960, 1800, 840, 1560, 840, 1560, 720, 1800, 720, 1800, 840, 1920, 840});
        Wall abismo33 = new Wall(new float[]{1080, 0, 1080, 360, 1200, 360, 1200, 480, 1320, 480, 1680, 480, 1680, 360, 1560, 360, 1560, 240, 1440, 240, 1440, 120, 1320, 120, 1320,0});
        Wall limites_4 = new Wall(new float[]{20, 20, 20, 940, 840, 940, 840, 960, 1080, 960, 1080, 940, 1900, 940, 1900, 20, 1080, 20, 1080, 0, 840, 0, 840, 20});
        Wall abismo41 = new Wall(new float[]{20, 360, 240, 360, 240, 480, 360, 480, 360, 600, 600, 600, 600, 720, 720, 720, 720, 840, 840, 840, 840, 940, 20, 940});
        Wall abismo42 = new Wall(new float[]{1080, 940, 1080, 720, 1200, 720, 1200, 600, 1560,  600, 1560, 480, 1680, 480, 1680, 360, 1900, 360, 1900, 940});
        Puerta p11 = new Puerta(1915, 360, 5, 240, 1, 2, 1);
        Puerta p21 = new Puerta(1915, 360, 5, 240, 1, 3, 1);
        Puerta p22 = new Puerta(840, 955, 240, 5, 2, 2, 1);
        Puerta p23 = new Puerta(0, 360, 5, 240, 3, 1, 1);
        Puerta p31 = new Puerta(0, 360, 5, 240, 3, 2, 1);
        Puerta p32 = new Puerta(840, 0, 240, 5, 0, 4, 2);
        
        ArrayList<Wall> walls1 = new ArrayList<>();
        walls1.add(limites_1);
        walls1.add(limites1);
        walls1.add(abismo11);
        walls1.add(abismo12);
        ArrayList<Puerta> puertas1 = new ArrayList<>();
        puertas1.add(p11);
        
        ArrayList<Wall> walls2 = new ArrayList<>();
        walls2.add(limites_2);
        walls2.add(limites2);
        walls2.add(abismo21);
        walls2.add(abismo22);
        walls2.add(planta2);
        ArrayList<Puerta> puertas2 = new ArrayList<>();
        puertas2.add(p21);
        puertas2.add(p22);
        puertas2.add(p23);
        
        ArrayList<Wall> walls3 = new ArrayList<>();
        walls3.add(limites_3);
        walls3.add(limites31);
        walls3.add(limites32);
        walls3.add(abismo31);
        walls3.add(abismo32);
        walls3.add(abismo33);
        ArrayList<Puerta> puertas3 = new ArrayList();
        puertas3.add(p31);
        puertas3.add(p32);
        
        ArrayList<Wall> walls4 = new ArrayList<>();
        walls4.add(limites_4);
        walls4.add(abismo41);
        walls4.add(abismo42);
        ArrayList<Puerta> puertas4 = new ArrayList();
        
        ArrayList<Esqueleto> esqueletos1 = new ArrayList<>();
        Esqueleto e1 = new Esqueleto("skeleton.png", 120, 150, 200, 200, 240, 5, 1, proyectiles);
        esqueletos1.add(e1);

        
        Sala sala1 = new Sala(mapa.getSubImage(0, 1), walls1, puertas1, null, player, proyectiles);
        Sala sala2 = new Sala(mapa.getSubImage(1, 1), walls2, puertas2, esqueletos1, player, proyectiles);
        Sala sala3 = new Sala(mapa.getSubImage(2, 1), walls3, puertas3, null, player, proyectiles);
        Sala sala4 = new Sala(mapa.getSubImage(2, 0), walls4, puertas4, null, player, proyectiles);
        salas.add(sala1);
        salas.add(sala2);
        salas.add(sala3);
        salas.add(sala4);
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

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
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
                case 1:
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
                salaActual = n;
            }
        }
    }
    
    @Override
    public void keyReleased(int key, char c) {
        if(paused) {
            if (key == Input.KEY_DOWN) {
                    selected++;
                    if (selected >= options.length) {
                            selected = 0;
                    }
            }
            if (key == Input.KEY_UP) {
                    selected--;
                    if (selected < 0) {
                            selected = options.length - 1;
                    }
            }
        }
    } 
    
}
