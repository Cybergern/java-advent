package aoc.problem6;

import aoc.util.ProblemException;

import java.util.HashSet;
import java.util.Set;

public class Part1 {
    public static Set<Position> traverse(GuardGrid grid) throws ProblemException {
        var uniquePositions = new HashSet<Position>();
        var validMove = true;
        uniquePositions.add(grid.guardPos);
        while(validMove) {
            validMove = grid.move();
            uniquePositions.add(grid.guardPos);
        }
        return uniquePositions;
    }

}

