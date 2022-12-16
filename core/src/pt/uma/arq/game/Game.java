package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.*;

public class Game extends ApplicationAdapter {

    /**
     * Atributos da funcao Game
     */
    private SpriteBatch batch;

    private BackgroundManagement backgroundManagement;
    private BitmapFont font;
    private PlayerShip playerShip;

    private String myText;
    private String myText2;

    private String myText3;
    private String myText4;
    private Fleet fleet;



    /**
     * Funcao onde vamos criar a frota,o playerShip, font
     */
    public void create() {
        Gdx.graphics.setWindowedMode(600, 800);


        batch = new SpriteBatch();


        fleet = new Fleet(batch);
        playerShip = new PlayerShip(300, 10, batch);

        fleet.create();
        playerShip.create();


        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"));

        Music background = Gdx.audio.newMusic(Gdx.files.internal("backgroundsound.mp3"));


        background.setVolume(0.7f);
        background.setLooping(true);
        background.play();




        backgroundManagement = new BackgroundManagement(batch);


    }

    @Override
    public void render() {

        fleet.handleCollisions(playerShip);
        fleet.handleCollisionPlayer(playerShip);


        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //batch.disableBlending();

        myText = "Score:" + playerShip.getScore();
        myText2 = "Vida:" + playerShip.getHealth();
        myText3 = "GANHOU O JOGO";
        myText4 = "Perdeu o JOGO";





        backgroundManagement.render();



        font.draw(batch, myText, 450, 770);
        font.draw(batch, myText2, 20, 770);



        if (this.playerShip.getScore() == 280){
            font.draw(batch,myText3, 200,500);

        }
        else if (this.playerShip.getHealth()<=0){
            font.draw(batch,myText4, 200,500);
        }

        if(this.playerShip.getScore() != 280 && this.playerShip.getHealth()>0){
            playerShip.render();

            fleet.render();
        }


        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}