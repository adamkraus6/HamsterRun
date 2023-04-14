package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Barrier;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Bars;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Food;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.GameArea;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Home;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Person;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Tube;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Zoom;

public class Game {
    private int moves;
    private int energy;
    private int food;
    private int stores;
    private int zoom;
    private int energy_to_move;
    private Position pos;
    private boolean lost;
    private boolean won;
    private GameArea[][] gameArea;
    public static final int GRID_SIZE = 5;
    public static final int MAX_FOOD = 20;
    public static final int START_ENERGY = 10;
    public static final int MAX_ENERGY = 15;
    public static final int WIN_STORES = 15;

    public Game() {
        reset();
    }

    public void reset() {
        // set initial values
        moves = 0;
        energy = START_ENERGY;
        food = 0;
        stores = 0;
        zoom = 0;
        energy_to_move = 1;
        pos = new Position();
        lost = false;
        won = false;

        setGameArea();
    }

    public void setGameArea() {
        gameArea = new GameArea[GRID_SIZE][GRID_SIZE];

        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                gameArea[i][j] = new Tube();
            }
        }

        // person at (2, 3)
        gameArea[2][3] = new Person();

        // zoom at (0, 4) and (2, 1)
        gameArea[0][4] = new Zoom();
        gameArea[2][1] = new Zoom();

        // food x1 at (0, 1)
        gameArea[0][1] = new Food(1);
        // food x2 at (0, 3)
        gameArea[0][3] = new Food(2);
        // food x5 at (2, 2)
        gameArea[2][2] = new Food(5);
        // food x10 at (3, 0)
        gameArea[3][0] = new Food(10);

        // bars at (2, 0), (3, 3), (4, 3), (2, 4)
        // gameArea[2][0] = new Bars();
        gameArea[3][3] = new Bars();
        // gameArea[4][3] = new Bars();
        // gameArea[2][4] = new Bars();

        // home at (4, 4)
        gameArea[4][4] = new Home();

        // barriers at (2, 0), (2, 4), (4, 3)
        // these take place of 3 bar locations
        gameArea[2][0] = new Barrier();
        gameArea[2][4] = new Barrier();
        gameArea[4][3] = new Barrier();
    }

    public int getZoomsLeft() {
        return zoom;
    }

    public int getMoves() {
        return moves;
    }

    public int getEnergy() {
        return energy;
    }

    public void addZoom() {
        if(zoom == 0)
            zoom = 1;
    }

    public int getHomeStores() {
        return stores;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int food) {
        this.food += food;
        this.food = Math.min(this.food, MAX_FOOD);
    }

    public Position getPlayerLocation() {
        return pos;
    }

    public void eat() {
        // no food to eat
        if(food == 0) return;
        food--;

        // add 5, max of 15
        energy = Math.min(MAX_ENERGY, energy+5);
    }

    public void move(int dx, int dy) {
        // original position
        int ox = pos.x, oy = pos.y;
        // new position
        int nx = ox + dx, ny = oy + dy;

        // moves outside of game area
        if(nx < 0 || nx > GRID_SIZE-1 || ny < 0 || ny > GRID_SIZE-1) return;

        // extension: check if barrier
        if(gameArea[nx][ny] instanceof Barrier) return;

        // use energy and increment moves;
        energy -= energy_to_move;
        moves++;
        if(energy == 0)
            lost = true;

        pos.x = nx;
        pos.y = ny;

        gameArea[pos.x][pos.y].enter(this);
    }

    public void pickup() {
        gameArea[pos.x][pos.y].pickup(this);
    }

    public void loseGame() {
        lost = true;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isWon() {
        return won;
    }

    public void storeFood(int toStore) {
        stores += toStore;
        if(stores >= WIN_STORES)
            won = true;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setHomeStores(int stores) {
        this.stores = stores;
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public void setEnergyToMove(int energy) {
        this.energy_to_move = energy;
    }
}