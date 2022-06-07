package AnimalKingdom;

import java.awt.*;
import java.util.LinkedList;

public class Carnivore extends LivingThing {
    public Carnivore() {
        super(25, 40, 5, new Point((int) (Math.random() * 700), (int) (Math.random() * 700)), Color.red, true, 40);
        setMaxHealth(10);
        setDeathCounter(getMaxHealth());
    }

    public Carnivore(int size, int deathCounter, int speed, Point center, Color color, boolean isAlive, int maxSize) {
        super(size, deathCounter, speed, center, color, isAlive, maxSize);
        setMaxHealth(10);
        setDeathCounter(getMaxHealth());
    }



    public LinkedList<Carnivore> reproduceCarnivore() {
        super.setHasReproduced(true);
        LinkedList<Carnivore> child = new LinkedList<Carnivore>();
        for (int i = 0; i < 4; i++) {
            child.add(new Carnivore());
        }

        return child;
    }
}
