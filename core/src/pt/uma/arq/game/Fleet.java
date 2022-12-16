package pt.uma.arq.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import pt.uma.arq.entities.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Random;


public class Fleet {


    /**
     * Atributos da funcao Fleet
     */
    private ArrayList<Laser> lasersArrayList = new ArrayList<Laser>();

    private ArrayList<Laser> lasersEnemyArrayList = new ArrayList<Laser>();
    private ArrayList<Ship> frotas;
    private SpriteBatch batch;

    private Music musicCollided;
    Timer timer;


    /**
     * Construtor Fleet com parametros
     * <p>
     * O Timer é a funcao onde faz com que as naves disparem de x em x segundos
     */
    public Fleet(SpriteBatch batch) {
        this.batch = batch;
        this.frotas = new ArrayList<Ship>();

        // FUNCAO COM QUE FAZ AS NAVES DISPARAREM ALEATORIO

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                performRandomShoot();
            }
        }, 0, 1, -1); // ao fim de 10 segundos pomos no primeiro 1 - 10 segundos
        // -1 -> infinito de vezes
    }


    /**
     * Funcao onde aleatoriamente escolhe uma nave para disparar
     */


    public void performRandomShoot() {
        Random r = new Random();
        int index = r.nextInt(frotas.size());
        frotas.get(index).shoot();
    }


    /**
     * Funcao onde vamos criar a frota
     */
    public void create() {


        int x_small = 150;
        int x_medium = 70;
        int x_large = 90;

        for (int i = 0; i < 8; i++) {
            Ship small = new SmallShip(x_small, 420, batch);
            frotas.add(small);


            x_small += 40;
        }

        for (int i = 0; i < 8; i++) {
            Ship medium = new MediumShip(x_medium, 520, batch);
            frotas.add(medium);
            x_medium += 60;
        }

        for (int i = 0; i < 8; i++) {

            Ship large = new LargeShip(x_large, 620, batch);
            frotas.add(large);

            x_large += 55;

        }


    }


    public void render() {

        for (Ship oneship : frotas) {
            oneship.render();

        }

    }


    /**
     * Funcao onde vai verificar se o laser bate na frota e declarar a pontuacao ganha
     * pelo abate de x nave
     */



    public void handleCollisions(PlayerShip playerShip) {
        Iterator<Ship> iterator = frotas.iterator();
        while (iterator.hasNext()) {
            Ship ship = iterator.next();
            for (Laser laserFromPlayerShip : playerShip.getLasersArrayList()) {

                if (laserFromPlayerShip.getBoundingBox().intersects(ship.getBoundingBox())) {

                    musicCollided = Gdx.audio.newMusic(Gdx.files.internal("bombsound.mp3"));
                    musicCollided.play();
                    musicCollided.setVolume(0.2f);


                    iterator.remove();
                    laserFromPlayerShip.setShow(false);
                    if (ship.getTypeOfShip() == "SmallShip") {
                        playerShip.setScore(playerShip.getScore() + 5);

                    } else if (ship.getTypeOfShip() == "MediumShip") {
                        playerShip.setScore(playerShip.getScore() + 10);
                    } else {
                        playerShip.setScore(playerShip.getScore() + 20);
                    }


                }
            }
        }
    }



    public void handleCollisionPlayer(PlayerShip playerShip) {
        for (Ship ship : frotas) {
            for (Laser laserFromShip : ship.getLasersEnemyArrayList()) {
                if (laserFromShip.getBoundingBox().intersects(playerShip.getBoundingBox())) {

                    Music attackedplayer = Gdx.audio.newMusic(Gdx.files.internal("somcoracao.mp3"));
                    attackedplayer.play();
                    attackedplayer.setVolume(0.5f);

                    if (ship.getTypeOfShip() == "SmallShip") {
                        playerShip.setHealth(playerShip.getHealth() - (ship.getDamage() + 5));

                    } else if (ship.getTypeOfShip() == "MediumShip") {
                        playerShip.setHealth(playerShip.getHealth() - (ship.getDamage() + 10));
                    } else {
                        playerShip.setHealth(playerShip.getHealth() - (ship.getDamage() + 20));
                    }

                    for (Laser laserEnemyRemove  : ship.getLasersEnemyArrayList()) {
                        laserEnemyRemove.getBoundingBox().setBounds(0, 0, 0, 0);
                    }
                }
            }


        }
    }
}









