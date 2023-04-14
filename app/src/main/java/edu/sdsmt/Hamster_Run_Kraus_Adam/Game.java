package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Barrier;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Bars;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Food;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.GameArea;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Home;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Person;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Tube;
import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Zoom;

/**
 * Game object lcass
 */
public class Game {
    public static final int GRID_SIZE = 5;
    public static final int MAX_FOOD = 20;
    public static final int START_ENERGY = 10;
    public static final int MAX_ENERGY = 15;
    public static final int WIN_STORES = 15;
    private int moves;
    private int energy;
    private int food;
    private int stores;
    private int zoom;
    private int zoomMove;
    private int energy_to_move;
    private Position pos;
    private boolean lost;
    private boolean won;
    private GameArea[][] gameArea;

    public Game() {
        reset();
    }

    /**
     * Sets game variables to initial values
     */
    public void reset() {
        // set initial values
        moves = 0;
        energy = START_ENERGY;
        food = 0;
        stores = 0;
        zoom = 0;
        zoomMove = 0;
        energy_to_move = 1;
        pos = new Position();
        lost = false;
        won = false;

        setGameArea();
    }

    /**
     * Sets the game area locations
     */
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

    /**
     * Gets zoom powerups left
     * @return zoom powers
     */
    public int getZoomsLeft() {
        return zoom;
    }

    /**
     * Gets number of moves
     * @return moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Sets the number of moves
     * @param moves new move number
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Gets energy left
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Sets energy left
     * @param energy new energy number
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Adds 1 zoom powerup
     */
    public void addZoom() {
        zoom++;
    }

    /**
     * Gets number of food in home stores
     * @return stores
     */
    public int getHomeStores() {
        return stores;
    }

    /**
     * Sets the food in home stores
     * @param stores food in stores
     */
    public void setHomeStores(int stores) {
        this.stores = stores;
    }

    /**
     * Gets food in pouches
     * @return food
     */
    public int getFood() {
        return food;
    }

    /**
     * Sets food in pouches
     * @param food new food number
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * Adds food to pouches
     * @param food food to add
     */
    public void addFood(int food) {
        this.food += food;
        this.food = Math.min(this.food, MAX_FOOD);
    }

    /**
     * Gets current location
     * @return position
     */
    public Position getPlayerLocation() {
        return pos;
    }

    /**
     * Eats food and gains energy
     */
    public void eat() {
        // no food to eat
        if(food == 0) return;
        food--;

        // add 5, max of 15
        energy = Math.min(MAX_ENERGY, energy+5);
    }

    /**
     * Move to new location
     * @param dx change in x position
     * @param dy change in y position
     */
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
        if(energy < 0)
            lost = true;

        pos.x = nx;
        pos.y = ny;

        gameArea[pos.x][pos.y].enter(this);
    }

    /**
     * Pickup at current location
     */
    public void pickup() {
        gameArea[pos.x][pos.y].pickup(this);
    }

    /**
     * Sets lost to true
     */
    public void loseGame() {
        lost = true;
    }

    /**
     * Check if game is lost
     * @return lost
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * Check if game is won
     * @return won
     */
    public boolean isWon() {
        return won;
    }

    /**
     * Adds food to home stores
     * @param toStore
     */
    public void storeFood(int toStore) {
        stores += toStore;
        if(stores >= WIN_STORES)
            won = true;
    }

    /**
     * Gets number of zoom moves left
     * @return zoom moves
     */
    public int getZoomMove() {
        return zoomMove;
    }

    /**
     * Sets number of zoom moves
     * @param zoomMove new zoom moves
     */
    public void setZoomMove(int zoomMove) {
        this.zoomMove = zoomMove;
    }

    /**
     * Sets new hamster location
     * @param pos new location
     */
    public void setPosition(Position pos) {
        this.pos = pos;
    }

    /**
     * Sets the energy needed to move
     * @param energy new energy to move
     */
    public void setEnergyToMove(int energy) {
        this.energy_to_move = energy;
    }

    /**
     * Removes a zoom powerup
     */
    public void removeZoom() {
        zoom--;
    }
}