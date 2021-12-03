package ch.epfl.cs107.play.game.icwars.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Unit extends ICWarsActor{


    String name;

    Unit(String name, Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs ){
        super(owner, orientation, coordinates, belongs);

        int Hp;
        int damage;


    }

    public class Soldier extends Unit{

        Soldier(String name, Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs) {
            super(name, owner, orientation, coordinates, belongs);
        }

    }


    public String getName() {
        return name;
    }




}
