package edu.sdsmt.Hamster_Run_Kraus_Adam;

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
    private Position pos;
    private boolean lost;
    private boolean won;
    private GameArea[][] gameArea;

    public Game() {
        reset();
    }

    public void reset() {
        // set initial values
        moves = 0;
        energy = 10;
        food = 0;
        stores = 0;
        zoom = 0;
        pos = new Position();
        lost = false;
        won = false;

        setGameArea();
    }

    public void setGameArea() {
        gameArea = new GameArea[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
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
        gameArea[2][0] = new Bars();
        gameArea[3][3] = new Bars();
        gameArea[4][3] = new Bars();
        gameArea[2][4] = new Bars();

        // home at (4, 4)
        gameArea[4][4] = new Home();
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
        this.food = Math.min(this.food, 20);
    }

    public Position getPlayerLocation() {
        return pos;
    }

    public void eat() {
        // no food to eat
        if(food == 0) return;
        food--;

        // add 5, max of 15
        energy = Math.min(15, energy+5);
    }

    public void move(int dx, int dy) {
        // original position
        int ox = pos.x, oy = pos.y;
        // new position
        int nx = ox + dx, ny = oy + dy;

        // moves outside of game area
        if(nx < 0 || nx > 4 || ny < 0 || ny > 4) return;

        // use energy and increment moves;
        energy--;
        if(energy < 0)
            lost = true;
        moves++;

        pos.x = nx;
        pos.y = ny;

        gameArea[pos.x][pos.y].enter(this);
        pickup();
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
        if(stores >= 30)
            won = true;
    }
}