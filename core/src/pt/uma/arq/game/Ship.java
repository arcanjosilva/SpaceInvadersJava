package pt.uma.arq.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.Laser;
import java.awt.*;
import java.util.ArrayList;

public abstract class Ship {


    /**
     * Atributos da Classe Ship
     */
    protected int health = 100;
    protected SpriteBatch batch;
    private boolean show = true;

    public Animator animator;
    protected Laser laserShip;
    protected Laser laserPlayership;
    protected ArrayList<Laser> lasersArrayList = new ArrayList<Laser>();

    protected ArrayList<Laser> lasersEnemyArrayList = new ArrayList<Laser>();
    protected int x, y;
    protected Rectangle boundingBox;



    protected String typeOfShip;

    protected int damage;
    protected int score;


    /**
     * Construtor Ship com parametros
     */

    public Ship(int x, int y, SpriteBatch batch, String path, int columns, int rows, String typeOfShip) {
        this.x = x;
        this.y = y;
        this.typeOfShip = typeOfShip;
        this.animator = new Animator(batch, path, columns, rows);
    }


    /**
     * Funcao onde vai "criar" tudo o que precisamos
     */

    public void create() {
        animator.create();
        boundingBox = new Rectangle(x, y, animator.getWidth(), animator.getHeight());
    }


    public void render() {
        this.animator.render(this.x, this.y);
    }


    /**
     * Seletores para aceder aos atributos
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    protected ArrayList<Laser> getLasersArrayList() {
        return lasersArrayList;
    }


    public abstract void shoot();

    protected ArrayList<Laser> getLasersEnemyArrayList() {
        return lasersEnemyArrayList;
    }


    public String getTypeOfShip() {
        return typeOfShip;
    }


    public int getDamage() {


        return damage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int points) {
        this.score = points;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void setShow(boolean show) {
        this.show = show;
    }
}



