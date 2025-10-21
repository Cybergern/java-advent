package aoc.problem6;

import aoc.util.CharacterGrid;
import aoc.util.Position;
import aoc.util.ProblemException;

import java.util.List;

public class GuardGrid extends CharacterGrid {
    Position guardPos;
    CharacterGrid.Direction guardDir;
    List<Character> GUARD_CHARS = List.of('<', '>', '^', 'v');

    public GuardGrid(int numRows, int numCols) {
        super(numRows, numCols);
    }

    @Override
    public void fillGrid(List<List<Character>> lines) throws ProblemException {
        if (lines.size() != getHeight()) {
            throw new ProblemException("Source size does not match grid size");
        }
        if (! lines.stream().allMatch(l -> l.size() == lines.getFirst().size())) {
            throw new ProblemException("All lines are not the same length");
        }
        for (int i=0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().size(); j++) {
                if (GUARD_CHARS.contains(lines.get(i).get(j))) {
                    guardPos = new Position(i, j);
                    guardDir = getDirection(lines.get(i).get(j));
                    rows[i][j] = '.';
                } else {
                    rows[i][j] = lines.get(i).get(j);
                }
            }
        }
    }

    private Direction getDirection(Character guardChar) {
        return switch (guardChar) {
            case '^' -> Direction.UP;
            case '<' -> Direction.LEFT;
            case '>' -> Direction.RIGHT;
            case 'v' -> Direction.DOWN;
            default -> Direction.UP_LEFT;
        };
    }

    public Character getGuard() {
        return switch (guardDir) {
            case UP -> '^';
            case RIGHT -> '>';
            case DOWN -> 'v';
            case LEFT -> '<';
            default -> '.';
        };
    }

    public void turnRight() {
        guardDir = switch(guardDir) {
            case Direction.UP -> Direction.RIGHT;
            case Direction.RIGHT -> Direction.DOWN;
            case Direction.DOWN -> Direction.LEFT;
            case Direction.LEFT -> Direction.UP;
            default -> Direction.UP_LEFT;
        };
    }

    public boolean move() throws ProblemException {
        var nextPos = getAdjacent(get(guardPos.row(), guardPos.col()), guardDir);
        if (nextPos.rowNum() == guardPos.row() && nextPos.colNum() == guardPos.col()) {
            return false;
        } else if (nextPos.value() == '#') {
            turnRight();
            return true;
        } else {
            guardPos = new Position(nextPos.rowNum(), nextPos.colNum());
            return true;
        }
    }

    public String toString() {
        var builder = new StringBuilder();
        for (int y=0; y < getHeight(); y++) {
            for (int x=0; x < getWidth(); x++) {
                if (y == guardPos.row() && x == guardPos.col()) {
                    builder.append(getGuard());
                } else {
                    try {
                        builder.append(get(x, y));
                    } catch (ProblemException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
