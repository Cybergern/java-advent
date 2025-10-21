package aoc.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterGrid {
    protected char[][] rows;

    public CharacterGrid(int height, int width) {
        assert height > 0;
        assert width > 0;
        this.rows = new char[height][width];
    }

    public int getHeight() {
        return this.rows.length;
    }

    public int getWidth() {
        return this.rows[0].length;
    }

    public void fillGrid(List<List<Character>> lines) throws ProblemException {
        if (lines.size() != getHeight()) {
            throw new ProblemException("Source size does not match grid size");
        }
        if (!lines.stream().allMatch(l -> l.size() == lines.getFirst().size())) {
            throw new ProblemException("All lines are not the same length");
        }
        for (int i=0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().size(); j++) {
                set(i, j, lines.get(i).get(j));
            }
        }
    }

    public GridValue get(int rowNum, int colNum) throws ProblemException {
        if (invalid(rowNum, colNum)) {
            throw new ProblemException(String.format("%s, %s is outside the grid.", rowNum, colNum));
        }
        return new GridValue(rowNum, colNum, rows[rowNum][colNum]);
    }

    public Set<Position> getAll(char value) {
        var matches = new HashSet<Position>();
        for (int i=0; i < getHeight(); i++) {
            for (int j=0; j < getWidth(); j++) {
                var cur = rows[i][j];
                if (cur == value) {
                    matches.add(new Position(i, j));
                }
            }
        }
        return matches;
    }

    public void set(int rowNum, int colNum, Character newValue) throws ProblemException {
        if (invalid(rowNum, colNum)) {
            throw new ProblemException(String.format("%s, %s is outside the grid.", rowNum, colNum));
        }
        rows[rowNum][colNum] = newValue;
    }

    public boolean invalid(int rowNum, int colNum) {
        return rowNum < 0 || rowNum >= getHeight() || colNum < 0 || colNum > getWidth();
    }

    public GridValue getAdjacent(GridValue gridValue, Direction direction) throws ProblemException {
        if (!validDirection(gridValue, direction))
            return gridValue;
        return switch (direction) {
            case UP_LEFT -> transformCoord(gridValue, -1, -1);
            case UP -> transformCoord(gridValue, -1, 0);
            case UP_RIGHT -> transformCoord(gridValue, -1, 1);
            case RIGHT -> transformCoord(gridValue, 0, 1);
            case DOWN_RIGHT -> transformCoord(gridValue, 1, 1);
            case DOWN -> transformCoord(gridValue, 1, 0);
            case DOWN_LEFT -> transformCoord(gridValue, 1, -1);
            case LEFT -> transformCoord(gridValue, 0, -1);
        };
    }

    public boolean validDirection(GridValue gridValue, Direction direction) {
        if (List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT).contains(direction)) {
            if (gridValue.rowNum == 0)
                return false;
        }
        if (List.of(Direction.RIGHT, Direction.UP_RIGHT, Direction.DOWN_RIGHT).contains(direction)) {
            if (gridValue.colNum >= getWidth() - 1)
                return false;
        }
        if (List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT).contains(direction)) {
            if (gridValue.rowNum >= getHeight() - 1)
                return false;
        }
        if (List.of(Direction.LEFT, Direction.UP_LEFT, Direction.DOWN_LEFT).contains(direction)) {
            return gridValue.colNum != 0;
        }
        return true;
    }

    private GridValue transformCoord(GridValue coords, int rowMod, int colMod) throws ProblemException {
        var newRow = coords.rowNum + rowMod;
        var newCol = coords.colNum + colMod;
        return get(newRow, newCol);
    }

    public Set<Character> getUniqueChars() {
        var uniqueChars = new HashSet<Character>();
        for (int i=0; i < getHeight(); i++) {
            for (int j=0; j < getWidth(); j++) {
                var cur = rows[i][j];
                if (cur != '.') {
                    uniqueChars.add(cur);
                }
            }
        }
        return uniqueChars;
    }

    public record GridValue(int rowNum, int colNum, char value) {
    }

    public enum Direction {
        UP_LEFT,
        UP,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN,
        DOWN_LEFT,
        LEFT
    }

    public String toString() {
        var builder = new StringBuilder();
        for (char[] row : this.rows) {
            for (char cur : row) {
                builder.append(cur);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}


