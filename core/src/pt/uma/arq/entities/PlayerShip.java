package pt.uma.arq.entities;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Ship;
import java.awt.*;



public class PlayerShip extends Ship {

    /**
     * Atributos da classe PlayerShip
     */

    private int PlayerSpeed = 7;

    private int PlayerSpeedUpandDown= 5;

    /**
     * Construtor PlayerShip com parametros
     */

    public PlayerShip(int x, int y, SpriteBatch batch) {
        super(x, y, batch, "ship.png", 5, 2,"PlayerShip");
        this.batch = batch;
        this.boundingBox = new Rectangle(x, y, animator.getWidth(), animator.getHeight());
    }


    /**
     * Funcao onde vai fazer o jogar se mexer para a direita e para esquerda
     */

    public void MovimentOfPlayer() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x >= 10) {
            this.x -= PlayerSpeed;
            this.boundingBox.setLocation(x, y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x <= 560) {
            this.x += PlayerSpeed;
            this.boundingBox.setLocation(x, y);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && y <= 90){
            this.y+=PlayerSpeedUpandDown;
            this.boundingBox.setLocation(x,y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && y >=5){
            this.y-=PlayerSpeedUpandDown;
            this.boundingBox.setLocation(x,y);

        }
    }


    /**
     * Funcao onde vai "renderizar" tudo o que criamos e o que queremos renderizar.
     * Aqui chamamos as funcoes que criamos para determinada situação, como por exemplo o MovimentOfPlayer
     */

    public void render() {
        MovimentOfPlayer();
        shoot();
        this.animator.render(this.x, this.y);


        for (Laser renderOneByOneLaser : lasersArrayList) {
            if (renderOneByOneLaser.getShow()) {
                renderOneByOneLaser.render();
            }
        }


    }





    /**
     * Funcao do tiro do PlayerShip
     */

    @Override
    public void shoot() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            laserPlayership = new Laser(this.x, this.y +20 , batch, true); //y + 20  é a posição onde a bola vai sair (onde está na posicao 0 )
            lasersArrayList.add(laserPlayership);


            Music musicLaser = Gdx.audio.newMusic(Gdx.files.internal("lasersound.mp3"));
            musicLaser.play();
            musicLaser.setVolume(0.1f);

        }

    }


    /**
     * Seletores para aceder aos atributos
     */

    @Override
    public int getX() {

        return x;
    }

    @Override
    public int getY() {
        return y ;
    }
}








