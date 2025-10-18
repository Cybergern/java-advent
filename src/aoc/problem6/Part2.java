package aoc.problem6;

import aoc.util.Grid;
import aoc.util.ProblemException;

import java.util.HashSet;
import java.util.Set;

public class Part2 {
    public static Set<Position> findValidObstructions(GuardGrid grid) throws ProblemException {
        var validObstructions = new HashSet<Position>();
        var originalGuardPosition = grid.guardPos;
        var originalGuardDirection = grid.guardDir;
        for (int x=0; x < grid.getHeight(); x++) {
            for (int y=0; y < grid.getWidth(); y++) {
                if (grid.get(x, y).value == '.') {
                    grid.set(x, y, '#');
                    if (testForLoop(grid)) {
                        validObstructions.add(new Position(x, y));
                    }
                    grid.set(x, y, '.');
                    grid.guardPos = originalGuardPosition;
                    grid.guardDir = originalGuardDirection;
                }
            }
        }
        return validObstructions;
    }

    public static boolean testForLoop(GuardGrid grid) throws ProblemException {
        var validMove = true;
        var previousVectors = new HashSet<GuardVector>();
        while(validMove) {
            if (previousVectors.contains(makeVector(grid))) {
                return true;
            } else {
                previousVectors.add(makeVector(grid));
            }
            validMove = grid.move();
        }
        return false;
    }

    private static GuardVector makeVector(GuardGrid grid) {
        return new GuardVector(grid.guardPos.row(), grid.guardPos.col(), grid.guardDir);
    }

    record GuardVector(int rowNum, int colNum, Grid.Direction dir) {}
}
