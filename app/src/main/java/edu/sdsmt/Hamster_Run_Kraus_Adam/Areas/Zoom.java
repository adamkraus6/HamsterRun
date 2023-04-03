package edu.sdsmt.Hamster_Run_Kraus_Adam.Areas;

import edu.sdsmt.Hamster_Run_Kraus_Adam.Game;

public class Zoom implements GameArea {
    @Override
    public void pickup(Game g) {
        g.addZoom();
    }

    @Override
    public void enter(Game g) {

    }
}
