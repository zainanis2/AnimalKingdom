import AnimalKingdom.*;

public class LivingthingFactory {

    public LivingThing getLivingThing(String livingthingname) {

        if (livingthingname.equalsIgnoreCase("plant")) {
            return new Plants();
        } else if (livingthingname.equalsIgnoreCase("herbivore")) {
            return new Herbivore();

        } else if (livingthingname.equalsIgnoreCase("carnivore")) {
            return new Carnivore();

        } else if (livingthingname.equalsIgnoreCase("canabil")) {
            return new Canibal();

        } else {
            return null;
        }


    }
}
