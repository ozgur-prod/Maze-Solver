package pgdp.maze;

public final class MazeSolver {

    // Man soll von außen keine Objekte der Klasse erzeugen können. Alle Methoden
    // sind static. Daher ist der Konstruktor private
    private MazeSolver() {

    }

    // TODO: Implementiere diese Methode, sodass sie einen Pfad vom Eingang
    // ('entrance') zum Ausgang ('exit')
    // des übergebenen Labyrinths 'maze' zurückgibt.
    public static Path solveMaze(Maze maze) {
        if (maze == null){
            return null;
        }
        if (maze.getEntrance() == null){
            return null;
        }
        if (maze.getExit() == null){
            return null;
        }

        Path path = new Path();
        Position position = maze.getEntrance();
        int step = 0;
        maze.mark(maze.getEntrance());

        while (!path.toPositionSet(maze.getEntrance()).contains(maze.getExit())){

            if (maze.isEmptyTile(position.getPositionOneTile(Direction.UP))){
                path.add(Direction.UP);
                position = position.getPositionOneTile(Direction.UP);
                maze.mark(position);
                step++;
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.RIGHT))){
                path.add(Direction.RIGHT);
                position = position.getPositionOneTile(Direction.RIGHT);
                maze.mark(position);
                step++;
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.DOWN))){
                path.add(Direction.DOWN);
                position = position.getPositionOneTile(Direction.DOWN);
                maze.mark(position);
                step++;
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.LEFT))){
                path.add(Direction.LEFT);
                position = position.getPositionOneTile(Direction.LEFT);
                maze.mark(position);
                step++;
            }
            else {
                position = switch (path.getStep(step - 1)) {
                    case UP -> position.getPositionOneTile(Direction.DOWN);
                    case RIGHT -> position.getPositionOneTile(Direction.LEFT);
                    case DOWN -> position.getPositionOneTile(Direction.UP);
                    case LEFT -> position.getPositionOneTile(Direction.RIGHT);
                };
                path.remove(step-1);
                step--;
                if (step == -1){
                    return null;
                }
            }
        }
        return path;
    }






    // TODO: Implementiere diese Methode, sodass sie einen Pfad vom der übergebenen
    // Position 'position'
    // zum Ausgang ('exit') des übergebenen Labyrinths 'maze' zurückgibt.
    // Sie muss rekursiv implementiert werden.
    public static Path solveMazeFrom(Maze maze, Position position) {
        if (maze == null){
            return null;
        }
        if (!maze.isEmptyTile(position)){
            return null;
        }
        if (maze.getExit() == null){
            return null;
        }

        Path path = new Path();
        int step = 0;
        return helpRec(maze,position,path,step);
    }

    private static Path helpRec(Maze maze, Position position, Path path, int step){

        maze.mark(position);
        if (!position.equals(maze.getExit())){
            if (maze.isEmptyTile(position.getPositionOneTile(Direction.UP))){
                path.add(Direction.UP);
                step++;
                return helpRec(maze,position.getPositionOneTile(Direction.UP),path,step);
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.RIGHT))){
                path.add(Direction.RIGHT);
                step++;
                return helpRec(maze,position.getPositionOneTile(Direction.RIGHT),path,step);
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.DOWN))){
                path.add(Direction.DOWN);
                step++;
                return helpRec(maze,position.getPositionOneTile(Direction.DOWN),path,step);
            }
            else if (maze.isEmptyTile(position.getPositionOneTile(Direction.LEFT))){
                path.add(Direction.LEFT);
                step++;
                return helpRec(maze,position.getPositionOneTile(Direction.LEFT),path,step);
            }
            else {
                position = switch (path.getStep(step - 1)) {
                    case UP -> position.getPositionOneTile(Direction.DOWN);
                    case RIGHT -> position.getPositionOneTile(Direction.LEFT);
                    case DOWN -> position.getPositionOneTile(Direction.UP);
                    case LEFT -> position.getPositionOneTile(Direction.RIGHT);
                };
                path.remove(step-1);
                step--;
                if (step == -1){
                    return null;
                }
                else {
                    return helpRec(maze,position,path,step);
                }
            }
        }
        else{
            return path;
        }
    }










    // Zum Testen
    public static void main(String[] args) {
        /*Maze maze = MazeParser.parseFromFile("Maze.txt");
        if (maze == null) {
            return;
        }*/
        String[] array = {"WWWWWWWWWW", "W  X      ", "WWWWW WWWW", "W      W W", "W WWWWWW E", "W        W", "WWWWWWWWWW"};

        Maze maze = MazeParser.mazeFromStringArray(array);

        System.out.println(maze);

        Path path = solveMaze(maze);
        System.out.println(path);
        System.out.println();
        System.out.println(maze.toString(path));
    }

}
