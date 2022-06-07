package AnimalKingdom;

import java.awt.*;
import java.util.LinkedList;

public class Herbivore extends LivingThing {

    public Herbivore() {
        super(11, 40, 4, new Point((int) (Math.random() * 700), (int) (Math.random() * 700)), Color.CYAN, true, 20);
        setMaxHealth(10);
        setDeathCounter(getMaxHealth());
    }




    public Herbivore(int size, int deathCounter, int speed, Point center, Color color, boolean isAlive, int maxSize) {
        super(size, deathCounter, speed, center, color, isAlive, maxSize);
        setMaxHealth(10);
        setDeathCounter(getMaxHealth());
    }

    public LinkedList<Herbivore> reproduceHerbivore() {
        super.setHasReproduced(true);
        LinkedList<Herbivore> child = new LinkedList<Herbivore>();
        for (int i = 0; i < 8; i++) {
            child.add(new Herbivore());
        }

        return child;
    }


}
