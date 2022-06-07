import AnimalKingdom.Canibal;
import AnimalKingdom.Carnivore;
import AnimalKingdom.Herbivore;
import AnimalKingdom.Plants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 700;
    private final int B_HEIGHT = 700;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Graphics g;
    public static int framecount = 0;
    private LinkedList<Plants> plants = new LinkedList<Plants>();
    private LinkedList<Herbivore> herbivores = new LinkedList<Herbivore>();
    private LinkedList<Carnivore> carnivores = new LinkedList<Carnivore>();
    private LinkedList<Canibal> canibals = new LinkedList<Canibal>();
    private LivingthingFactory livingthingFactory = new LivingthingFactory();


    private Timer timer;
    private int x, y;

    public Board() {
        initBoard();
    }


    private void initBoard() {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        x = INITIAL_X;
        y = INITIAL_Y;


        for (int i = 0; i < 250; i++) {
            plants.add((Plants) livingthingFactory.getLivingThing("plant"));
        }

        for (int i = 0; i < 50; i++) {
            herbivores.add((Herbivore) livingthingFactory.getLivingThing("herbivore"));
        }

        for (int i = 0; i < 15; i++) {
            carnivores.add((Carnivore) livingthingFactory.getLivingThing("carnivore"));
        }

        for (int i = 0; i < 10; i++) {
            canibals.add((Canibal) livingthingFactory.getLivingThing("canabil"));
        }

        timer = new Timer(DELAY, this);
        timer.start();

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private void Draw(Graphics g) {


        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).isAlive()) {
                plants.get(i).draw(g);
            }

        }

        for (int i = 0; i < herbivores.size(); i++) {
            if (herbivores.get(i).isAlive()) {
                herbivores.get(i).draw(g);
            }
        }

        for (int i = 0; i < carnivores.size(); i++) {
            if (carnivores.get(i).isAlive()) {
                carnivores.get(i).draw(g);
            }
        }

        for (int i = 0; i < canibals.size(); i++) {
            if (canibals.get(i).isAlive()) {
                canibals.get(i).draw(g);
            }
        }


        Toolkit.getDefaultToolkit().sync();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        x += 1;
        y += 1;

        framecount++;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }


        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------PLANTS----------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------


        //checks if the plant is alive if not then it removes it from the list
        for (int i = 0; i < plants.size(); i++) {
            if (!plants.get(i).isAlive()) {
                plants.remove(i);
            }
        }

        if (framecount % 20 == 0) {


            for (int i = 0; i < plants.size(); i++) {

                //increases the size of the plant
                if (plants.get(i).getSize() <= plants.get(i).getMaxSize()) {
                    plants.get(i).increaseSize();
                }
                //reproduces the plant
                if (plants.get(i).getSize() == plants.get(i).getMaxSize() && !plants.get(i).isHasReproduced()) {
                    plants.addAll(plants.get(i).Reproduceplants());

                }

            }
        }


        //recreates the plant if their population goes below 100
        if (plants.size() < 100) {
            plants.addAll(Plants.recreateplants());

        }

        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------HERBIVORE-------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------

        //checks if the animal is alive if not then it removes it from the list
        for (int i = 0; i < herbivores.size(); i++) {
            if (!herbivores.get(i).isAlive()) {
                herbivores.remove(i);
            }
        }

        if (framecount % 3 == 0) {

            if (!plants.isEmpty()) {

                //sets target for the animal
                for (int i = 0; i < herbivores.size(); i++) {
                    if (!herbivores.get(i).HasTarget()) {
                        herbivores.get(i).setTarget(plants);
                    }
                }

                //moves the animal
                for (int i = 0; i < herbivores.size(); i++) {
                    if (herbivores.get(i).getTarget().getSize() < herbivores.get(i).getSize()) {
                        herbivores.get(i).move();
                    } else {
                        herbivores.get(i).setTarget(plants);
                    }


                }

                //checks if the animal is at the size where it can reproduce
                for (int i = 0; i < herbivores.size(); i++) {
                    if (herbivores.get(i).getSize() >= herbivores.get(i).getMaxSize() && !herbivores.get(i).isHasReproduced()) {
                        herbivores.addAll(herbivores.get(i).reproduceHerbivore());
                        herbivores.get(i).setAlive(false);
                    }
                }

            }

        }

        //checks if the animals death counter is at 0 if yes it dies
        if (framecount % 20 == 0) {
            for (int i = 0; i < herbivores.size(); i++) {
                herbivores.get(i).setDeathCounter(herbivores.get(i).getDeathCounter() - 1);
                if (herbivores.get(i).getDeathCounter() == 0) {
                    herbivores.get(i).setAlive(false);
                }
            }

        }


        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------CARIVORE--------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------

        //checks if the animal is alive if not then it removes it from the list
        for (int i = 0; i < carnivores.size(); i++) {
            if (!carnivores.get(i).isAlive()) {
                carnivores.remove(i);
            }
        }

        if (framecount % 3 == 0) {
            if (!herbivores.isEmpty() || !canibals.isEmpty()) {

                //sets target for the animal
                for (int i = 0; i < carnivores.size(); i++) {
                    if (!carnivores.get(i).HasTarget()) {
                        carnivores.get(i).setTarget(herbivores, canibals);
                    }
                }

                //moves the animal
                for (int i = 0; i < carnivores.size(); i++) {
                    if (carnivores.get(i).getTarget().getSize() <= carnivores.get(i).getSize()) {
                        carnivores.get(i).move();
                    } else {
                        carnivores.get(i).setTarget(herbivores, canibals);
                    }

                }

                //checks if the animal is at the size where it can reproduce
                for (int i = 0; i < carnivores.size(); i++) {
                    if (carnivores.get(i).getSize() >= carnivores.get(i).getMaxSize() && !carnivores.get(i).isHasReproduced()) {
                        carnivores.addAll(carnivores.get(i).reproduceCarnivore());
                        carnivores.get(i).setAlive(false);
                    }
                }

            }


        }
        //checks if the animals death counter is at 0 if yes it dies
        if (framecount % 20 == 0) {
            for (int i = 0; i < carnivores.size(); i++) {
                carnivores.get(i).setDeathCounter(carnivores.get(i).getDeathCounter() - 1);
                if (carnivores.get(i).getDeathCounter() == 0) {
                    carnivores.get(i).setAlive(false);
                }
            }
        }

        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------CANIBAL---------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------

        //checks if the animal is alive if not then it removes it from the list
        for (int i = 0; i < canibals.size(); i++) {
            if (!canibals.get(i).isAlive()) {
                canibals.remove(i);
            }
        }

        if (framecount % 3 == 0) {
            if (!carnivores.isEmpty() || !herbivores.isEmpty() || !canibals.isEmpty()) {
                //sets target for the animal
                for (int i = 0; i < canibals.size(); i++) {
                    if (!canibals.get(i).HasTarget()) {
                        canibals.get(i).setTarget(herbivores, carnivores, canibals);
                    }
                }

                //moves the animal
                for (int i = 0; i < canibals.size(); i++) {
                    if (canibals.get(i).getTarget().getSize() <= canibals.get(i).getSize()) {
                        canibals.get(i).move();
                    } else {
                        canibals.get(i).setTarget(herbivores, carnivores, canibals);
                    }

                }

                //checks if the animal is at the size where it can reproduce
                for (int i = 0; i < canibals.size(); i++) {
                    if (canibals.get(i).getSize() >= canibals.get(i).getMaxSize() && !canibals.get(i).isHasReproduced()) {
                        canibals.addAll(canibals.get(i).reproduceCannibal());
                        canibals.get(i).setAlive(false);
                    }
                }

            }


        }

        //checks if the animals death counter is at 0 if yes it dies
        if (framecount % 20 == 0) {
            for (int i = 0; i < canibals.size(); i++) {
                canibals.get(i).setDeathCounter(canibals.get(i).getDeathCounter() - 1);
                if (canibals.get(i).getDeathCounter() == 0) {
                    canibals.get(i).setAlive(false);
                }
            }

        }

        repaint();

    }
}
