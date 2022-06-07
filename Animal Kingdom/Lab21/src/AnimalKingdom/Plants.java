package AnimalKingdom;

import java.awt.*;
import java.util.LinkedList;

public class Plants extends LivingThing {


    private int size = 6;


    public Plants() {
        super(5, 0, 0, new Point((int) (Math.random() * 700), (int) (Math.random() * 700)), Color.green, true, 10);
        setHasReproduced(false);
    }

    public void increaseSize() {
        if (size <= getMaxSize()) {
            this.setSize(size++);
        }
    }

    public LinkedList<Plants> Reproduceplants() {

        super.setHasReproduced(true);
        LinkedList<Plants> child = new LinkedList<Plants>();
        for (int i = 0; i < 1; i++) {
            child.add(new Plants());
        }

        return child;
    }

    public static LinkedList<Plants> recreateplants() {

        LinkedList<Plants> recreate = new LinkedList<Plants>();
        for (int i = 0; i < 100; i++) {
            recreate.add(new Plants());
        }

        return recreate;
    }


}
