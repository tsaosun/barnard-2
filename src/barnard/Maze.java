package barnard;

import java.util.*;

public class Maze {
    public Maze(String[] maze) {
        this.maze = maze;
    }

    String[] maze;

    public String solve() {
        Position start = null, end = null;
        for(int r = 0; r != maze.length; ++r) {
            for (int c = 0; c != maze[r].length(); ++c) {
                Position p = new Position(r, c);
                switch(p.tile()) {
                    case START:
                        start = p;
                        break;
                    case END:
                        end = p;
                        break;
                    default: break;
                }
            }
        }


        return this.path(start, end);
    }

    public String path(Position start, Position end) {
        HashSet<Position> crumbs = new HashSet<Position>();
        LinkedList<Trail> queue = new LinkedList<Trail>();
        queue.add(new Trail(start, ""));

        while (!queue.isEmpty()) {
            Trail t = queue.poll();

            // If we've been here before, bail out.
            if (crumbs.contains(t.end)) continue;

            // Drop a breadcrumb.
            crumbs.add(t.end);

            // Have we found the treasure? If so, return the path
            // we took to get here.
            if (t.end.equals(end)) {
                return t.path;
            }

            // Otherwise, add all possible forks to the queue.
            queue.addAll(t.forks());
        }

        return null;
    }

    static class Trail {
        Trail(Position end, String path) {
            this.end = end;
            this.path = path;
        }

        Position end;
        String path;

        Trail north() {
            return new Trail(end.north(), path + 'N');
        }

        Trail south() {
            return new Trail(end.south(), path + 'S');
        }

        Trail east() {
            return new Trail(end.east(), path + 'E');
        }

        Trail west() {
            return new Trail(end.west(), path + 'W');
        }

        List<Trail> forks() {
            LinkedList<Trail> forks = new LinkedList<Trail>();
            if (end.north().tile() != Tile.WALL) forks.add(north());
            if (end.south().tile() != Tile.WALL) forks.add(south());
            if (end.east().tile() != Tile.WALL) forks.add(east());
            if (end.west().tile() != Tile.WALL) forks.add(west());
            return forks;
        }
    }

    enum Tile {
        START, HALL, WALL, END;

        static Tile from(char c) {
            switch(c) {
                case '#':
                    return WALL;
                case ' ':
                    return HALL;
                case '*':
                    return START;
                case 'X':
                    return END;
                default:
                    return WALL;
            }
        }
    };


    class Position {
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Position north() { return new Position(r - 1, c); }
        Position south() { return new Position(r + 1, c); }
        Position east() { return new Position(r, c + 1); }
        Position west() { return new Position(r, c - 1); }

        Position[] neighbors() {
            return new Position[] { this.north(), this.south(), this.east(), this.west() };
        }

        Tile tile() {
            if (r < 0 || c < 0) return Tile.WALL;
            if (r >= maze.length) return Tile.WALL;
            if (c >= maze[r].length()) return Tile.WALL;
            return Tile.from(maze[r].charAt(c));
        }

        @Override
        public int hashCode() {
            return r * maze[0].length() + c;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Position)) return false;
            Position p = (Position) obj;
            return r == p.r && c == p.c;
        }

        public int r, c;
    }

    static String[] example = {
            "#*##########",
            "#  ####  ###",
            "##     #####",
            "## #########",
            "## ###   X##",
            "##     #####",
            "############"
    };

    public static void main(String... args) {
        System.out.println(
                new Maze(example).solve());
    }

}
