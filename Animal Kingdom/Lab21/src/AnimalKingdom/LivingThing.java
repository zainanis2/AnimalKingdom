package AnimalKingdom;

import java.awt.*;
import java.util.LinkedList;

public abstract class LivingThing {
    private int size, deathCounter, speed;
    private Point center;
    private Color color;
    private boolean isAlive;
    private boolean hasTarget;
    private LivingThing target;
    private int maxSize;
    private int maxHealth;

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private boolean hasReproduced;

    public boolean isHasReproduced() {
        return hasReproduced;
    }

    public void setHasReproduced(boolean hasReproduced) {
        this.hasReproduced = hasReproduced;
    }


    public boolean HasTarget() {
        return hasTarget;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 1) {
            this.size = size;
        } else {
            this.size = 1;
        }
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public void setDeathCounter(int deathCounter) {
        this.deathCounter = deathCounter;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public boolean isAlive() {
        return isAlive;
    }


    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public LivingThing getTarget() {
        return target;
    }

    //Constructor

    /**
     * @param size
     * @param deathCounter
     * @param speed
     * @param center
     * @param color
     * @param isAlive
     * @param maxSize
     */
    public LivingThing(int size, int deathCounter, int speed, Point center, Color color, boolean isAlive, int maxSize) {
        setSize(size);
        setDeathCounter(deathCounter);
        setSpeed(speed);
        setCenter(center);
        setAlive(isAlive);
        setColor(color);
        this.maxSize = maxSize;

    }


    //MethodOverLoaded for having 1 preys

    /**
     * if the animal has one prey enter its linked list
     *
     * @param livingThings
     */
    public void setTarget(LinkedList<? extends LivingThing> livingThings) {
        int[] info = getMinDist(getCenter(), livingThings);
        int targetIndex = info[0];
        target = livingThings.get(targetIndex);
        hasTarget = true;

    }

    /**
     * if the animal has two preys enter their linked lists
     *
     * @param livingThings
     * @param livingThings2
     */
    //MethodOverLoaded for having 2 preys
    public void setTarget(LinkedList<? extends LivingThing> livingThings, LinkedList<? extends LivingThing> livingThings2) {
        int minDist, minDist2;
        int targetIndex, targetIndex2;


        if (!livingThings.isEmpty() && !livingThings2.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];
            minDist = info[1];

            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];
            minDist2 = info2[1];
            if (minDist <= minDist2) {
                target = livingThings.get(targetIndex);
            } else {
                target = livingThings2.get(targetIndex2);
            }
        } else if (!livingThings.isEmpty() && livingThings2.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];

            target = livingThings.get(targetIndex);

        } else if (livingThings.isEmpty() && !livingThings2.isEmpty()) {
            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];

            target = livingThings2.get(targetIndex2);

        }


        hasTarget = true;

    }


    //MethodOverLoaded for having 3 preys

    /**
     * if the animal has three preys enter their linked lists
     *
     * @param livingThings
     * @param livingThings2
     * @param livingThings3
     */

    public void setTarget(LinkedList<? extends LivingThing> livingThings, LinkedList<? extends LivingThing> livingThings2, LinkedList<? extends LivingThing> livingThings3) {
        int minDist, minDist2, minDist3;
        int targetIndex, targetIndex2, targetIndex3;

        if (!livingThings.isEmpty() && !livingThings2.isEmpty() && !livingThings3.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];
            minDist = info[1];

            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];
            minDist2 = info2[1];

            int[] info3 = getMinDist(getCenter(), livingThings3);
            targetIndex3 = info3[0];
            minDist3 = info3[1];


            if (minDist <= minDist2 && minDist <= minDist3) {
                target = livingThings.get(targetIndex);
            } else if (minDist2 <= minDist && minDist2 <= minDist3) {
                target = livingThings2.get(targetIndex2);
            } else {
                target = livingThings3.get(targetIndex3);
            }
        } else if (!livingThings.isEmpty() && !livingThings2.isEmpty() && livingThings3.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];
            minDist = info[1];

            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];
            minDist2 = info2[1];
            if (minDist <= minDist2) {
                target = livingThings.get(targetIndex);
            } else {
                target = livingThings2.get(targetIndex2);
            }

        } else if (!livingThings.isEmpty() && livingThings2.isEmpty() && !livingThings3.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];
            minDist = info[1];

            int[] info3 = getMinDist(getCenter(), livingThings3);
            targetIndex3 = info3[0];
            minDist3 = info3[1];
            if (minDist <= minDist3) {
                target = livingThings.get(targetIndex);
            } else {
                target = livingThings3.get(targetIndex3);
            }

        } else if (livingThings.isEmpty() && !livingThings2.isEmpty() && !livingThings3.isEmpty()) {
            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];
            minDist2 = info2[1];

            int[] info3 = getMinDist(getCenter(), livingThings3);
            targetIndex3 = info3[0];
            minDist3 = info3[1];
            if (minDist2 <= minDist3) {
                target = livingThings2.get(targetIndex2);
            } else {
                target = livingThings3.get(targetIndex3);
            }

        } else if (!livingThings.isEmpty() && livingThings2.isEmpty() && livingThings3.isEmpty()) {
            int[] info = getMinDist(getCenter(), livingThings);
            targetIndex = info[0];


            target = livingThings.get(targetIndex);

        } else if (livingThings.isEmpty() && !livingThings2.isEmpty() && livingThings3.isEmpty()) {
            int[] info2 = getMinDist(getCenter(), livingThings2);
            targetIndex2 = info2[0];

            target = livingThings2.get(targetIndex2);


        } else if (livingThings.isEmpty() && livingThings2.isEmpty() && !livingThings3.isEmpty()) {
            int[] info3 = getMinDist(getCenter(), livingThings3);
            targetIndex3 = info3[0];

            target = livingThings3.get(targetIndex3);


        }


        hasTarget = true;

    }


    //Draws oval

    /**
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getCenter().x - getSize() / 2, getCenter().y - getSize() / 2, getSize(), getSize());
    }


    //moves the animal
    public void move() {
        //checks if the animal has reached its target if yes than it eats it
        if ((center.distance(target.getCenter()) - (int) ((target.size / 2) + (size / 2)) <= 0)) {
            eaten();
        }

        //keeps in check if the target is alive and not eaten by any other animal
        if (!target.isAlive) {
            hasTarget = false;
        }


        int moveX, moveY;
        moveX = center.x - getTarget().center.x;
        if (moveX < 0) {
            moveX = 1;
        } else {
            moveX = -1;
        }
        moveY = center.y - getTarget().center.y;
        if (moveY < 0) {
            moveY = 1;
        } else {
            moveY = -1;
        }
        center.translate(moveX * speed, moveY * speed);


    }

    //eats the target
    private void eaten() {
        hasTarget = false;
        target.setAlive(false);
        //size = size + target.size;
        if (Math.random() <= 0.5) {
            size = size + 2;
        } else {
            size = size + 1;
        }

        //sets the death counter back to max
        deathCounter = maxHealth;

    }

    //gets the distance between 2 animals

    /**
     * enter the predator and the linked list of its prey
     *
     * @param center
     * @param prey
     * @return distance b/w prey and predator
     */
    private int[] getMinDist(Point center, LinkedList<? extends LivingThing> prey) {
        int minDist = Integer.MAX_VALUE;
        int dist = 0;
        int targetIndex = 0;
        for (int i = 0; i < prey.size(); i++) {
            //checks if the target size is less than himself and that the target is not himself
            if (prey.get(i).getSize() < size && !prey.get(i).getCenter().equals(center)) {
                dist = (int) center.distance(prey.get(i).getCenter());
                if (dist < minDist) {
                    minDist = dist;
                    targetIndex = i;
                }
            }
        }
        int[] info = {targetIndex, minDist};
        return info;

    }


}
