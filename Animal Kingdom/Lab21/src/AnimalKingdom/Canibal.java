package AnimalKingdom;

import java.awt.*;
import java.util.LinkedList;

public class Canibal extends Carnivore {
    public Canibal() {
        super(30, 20, 5, new Point((int) (Math.random() * 700), (int) (Math.random() * 700)), Color.orange, true, 40);

        setMaxHealth(10);
        setDeathCounter(getMaxHealth());
    }



    public LinkedList<Canibal> reproduceCannibal() {
        super.setHasReproduced(true);
        LinkedList<Canibal> child = new LinkedList<Canibal>();
        for (int i = 0; i < 2; i++) {
            child.add(new Canibal());
        }

        return child;
    }
}
