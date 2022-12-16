package pt.uma.arq.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Ship;
public class MediumShip extends Ship {

    /**
     * Construtor MediumShip por parametros
     */

    public MediumShip(int x, int y, SpriteBatch batch) {
        super(x, y, batch, "enemy-medium.png", 2, 1,"MediumShip");
        this.batch = batch;
        this.create();
    }


    public void render() {
        this.animator.render(this.x, this.y);

        for (Laser renderOneByOneLaser : lasersEnemyArrayList) {
            renderOneByOneLaser.render();
        }
    }


    /**
     * Funcao Funcao do tiro da MediumShip
     */
    @Override
    public void shoot() {


        laserShip = new Laser(this.x, this.y, batch, false);
        lasersEnemyArrayList.add(laserShip);
    }
}