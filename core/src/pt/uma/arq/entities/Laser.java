package pt.uma.arq.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import java.awt.*;


public class Laser {

    /**
     * Atributos da classe Laser
     */

    private final boolean isPlayer;
    private boolean show = true;

    private Rectangle boundingBox;
    private int x, y;
    private Animator animator;


    /**
     * Construtor Laser com parametros.
     */
    public Laser(int x, int y, SpriteBatch batch, boolean isPlayer) {
        this.animator = new Animator(batch, "laser-bolts.png", 2, 2);
        this.animator.create();
        this.x = x;
        this.y = y;
        this.isPlayer = isPlayer;
        this.boundingBox = new Rectangle(x, y, animator.getWidth(), animator.getHeight());
    }


    /**
     * Funcao onde vai "renderizar" tudo o que criamos e o que queremos renderizar.
     *
     * Ciclo criado para verificar a maneira da direcao do tiro.
     * Se for jogador (isPlay) o tiro será feito para cima
     * caso seja o oposto, para baixo
     *
     * É no render que atualizamos a localização do laser,neste caso.
     */
    public void render() {
        if (isPlayer) {
            this.y += 10;
        } else {
            this.y -= 10;
//            this.x+=10;
        }

        this.boundingBox.setLocation(x, y);
        this.animator.render(this.x, this.y);
    }


    /**
     * Seletores para aceder aos atributos
     */

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }



}