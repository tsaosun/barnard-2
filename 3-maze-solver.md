# Prompt

Given a maze, solve it.

The maze is given to you as an array of Strings, which looks like this:

```java
{"#*##########",
 "#  ####  ###",
 "##     #####",
 "## #########",
 "## ###   X##",
 "##     #####",
 "############"}
```

You start at the `*` and `X` marks the spot. `#` denotes a wall, and spaces
are halls you can walk through.

You should return a set of instructions to get through the maze. This can just
be a string (or array) of north, south, east, and west commands. You can abbreviate
as N, S, E, and W.

For the example, the path is: SESSSSEEEENEEE

If the maze is unsolveable, return null.

The maze is guaranteed to be square. That is, the arrays will not be jagged.

## Solution

A solution is [here](src/barnard/Maze.java).