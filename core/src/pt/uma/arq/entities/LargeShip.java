package pt.uma.arq.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Ship;

public class LargeShip extends Ship {

    /**
     * Construtor LargeShip com parametros - Vai guardar em memória algumas variaveis de modo a serem reutilizáveis
     * <p>
     * O super vais buscar informação a classe Ship evitando assim a repeticao de codigo
     * <p>
     * Em vez de criar um função invocamos o create() que está criado tambem no Ship
     */

    public LargeShip(int x, int y, SpriteBatch batch) {
        super(x, y, batch, "enemy-big.png", 2, 1,"LargeShip");
        this.batch = batch;
        this.create();
    }


    /**
     * Funcao onde vai "renderizar" tudo o que criamos e o que queremos renderizar.
     */
    @Override
    public void render() {
        this.animator.render(this.x, this.y);
        for (Laser renderOneByOneLaser : lasersEnemyArrayList) {
            renderOneByOneLaser.render();
        }

    }

    /**
     * Funcao do tiro da LargeShip
     */
    @Override
    public void shoot() {

        laserShip = new Laser(this.x, this.y, batch, false);
        lasersEnemyArrayList.add(laserShip);

    }
}
