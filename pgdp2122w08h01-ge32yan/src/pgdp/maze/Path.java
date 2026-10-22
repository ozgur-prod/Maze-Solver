package pgdp.maze;

import java.util.*;


// TODO: Implementiere diese Klasse, sodass sie eine Sequenz von Directions repräsentiert
public class Path {
    // TODO: Attribut(e)
    private final List<Direction> path;

    // TODO: Soll einen leeren Pfad erzeugen
    public Path() {
        path = new ArrayList<>();
    }


    // TODO: Soll ein HashSet<Position> mit allen Positionen zurückgeben, die man beim Ablaufen des Pfades 'this'
    //  besucht, wenn man bei der Position 'start' beginnt (ungeachtet irgendwelcher WALLs o.Ä.)
    public Set<Position> toPositionSet(Position start) {

        ArrayList<Position> list = new ArrayList<>();
        list.add(start);
        int index = 0;

        for (Direction d : path){
            list.add(list.get(index).getPositionOneTile(d));
            index++;
        }

        return new HashSet<>(list);
    }

    // TODO: Soll die übergebene Richtung 'direction' vorne in die bisherige Sequenz einfügen
    public void prepend(Direction direction) {
        path.add(0,direction);
    }

    // TODO: Soll die Richtung am übergebenen 'index' zurückgeben
    public Direction getStep(int index) {
        return path.get(index);
    }

    // TODO: Soll eine String-Repräsentation des Pfades 'this' zurückgeben, wie in der Aufgabenstellung beschrieben
    @Override
    public String toString() {
        return String.valueOf(path);
    }

    public void add(Direction direction){
        path.add(direction);
    }

    public void remove(int index){
        path.remove(index);
    }

}
