package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Ship;

public class SmallShip extends Ship {


    /**
     * Construtor SmallShip com parametros
     *
     * O super vais buscar informação a classe Ship evitando assim a repeticao de codigo
     *
     * Em vez de criar um função invocamos o create() que está criado tambem no Ship
     */

    public SmallShip(int x, int y, SpriteBatch batch) {
        super(x, y, batch, "enemy-small.png", 2, 1, "SmallShip");
        this.batch = batch;
        this.create();

    }


    /**
     * Funcao onde vai "renderizar" tudo o que criamos e o que queremos renderizar.
     */

    @Override
    public void render() {
        this.animator.render(this.x, this.y);


        /**
         * Vai percorrer cada lazer dentro da lasersEnemyArrayList de modo a que depois seja renderizada.
         */

        for (Laser renderOneByOneLaser : lasersEnemyArrayList) {
            renderOneByOneLaser.render();
        }


    }


    /**
     * Funcao do tiro da SmallShip
     */
    @Override
    public void shoot() {

        /**
         * Aqui vair criar um laser que depois vai ser adicionado a array lasersEnemyArrayList
         */


        laserShip = new Laser(this.x, this.y, batch, false);
        lasersEnemyArrayList.add(laserShip);

    }

}
