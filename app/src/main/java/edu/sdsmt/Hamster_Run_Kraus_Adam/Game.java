package edu.sdsmt.Hamster_Run_Kraus_Adam;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Areas.Tube;

public class Game {
    private int moves;
    private int energy;
    private int food;
    private int stores;
    private int zoom;
    private Position pos;
    private Tube[][] gameArea;

    public Game() {
        reset();
        setGameArea();
    }

    public void reset() {
        moves = 0;
        energy = 10;
        food = 0;
        stores = 0;
        zoom = 0;
        pos = new Position();
    }

    public void setGameArea() {
        gameArea = new Tube[5][5];
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

    public int getHomeStores() {
        return stores;
    }

    public int getFood() {
        return food;
    }

    public Position getPlayerLocation() {
        return pos;
    }

    public void eat() {
        if(food >= 1) {
            food -= 1;
            energy = Math.min(20, energy+5);
        }
    }

    public void move(int dx, int dy) {
        // illegal move
        if(dx+dy > 2) return;
        int nx = pos.x + dx, ny = pos.y + dy;
        // moves outside of game area
        if(nx < 0 || nx > 4 || ny < 0 || ny > 4) return;
        pos.x = nx;
        pos.y = ny;
        moves++;
    }

    public void pickup() {
    }

    public boolean isLost() {
        return false;
    }

    public boolean isWon() {
        return false;
    }
}
