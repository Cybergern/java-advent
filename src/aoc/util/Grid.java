package aoc.util;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {
    protected ArrayList<ArrayList<T>> rows;

    public Grid(int numRows) {
        this.rows = new ArrayList<>();
        for (int i=0; i < numRows; i++) {
            this.rows.add(new ArrayList<>());
        }
    }

    public int getHeight() {
        return this.rows.size();
    }

    public int getWidth() {
        return this.rows.getFirst().size();
    }

    public void fillGrid(List<List<T>> lines) throws ProblemException {
        if (lines.size() != this.rows.size()) {
            throw new ProblemException("Source size does not match grid size");
        }
        if (! lines.stream().allMatch(l -> l.size() == lines.getFirst().size())) {
            throw new ProblemException("All lines are not the same length");
        }
        for (int i=0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().size(); j++) {
                rows.get(i).add(lines.get(i).get(j));
            }
        }
    }

    public GridValue get(int rowNum, int colNum) throws ProblemException {
        if (invalid(rowNum, colNum)) {
            throw new ProblemException(String.format("%s, %s is outside the grid.", rowNum, colNum));
        }
        return new GridValue(rowNum, colNum, rows.get(rowNum).get(colNum));
    }

    public void set(int rowNum, int colNum, T newValue) throws ProblemException {
        if (invalid(rowNum, colNum)) {
            throw new ProblemException(String.format("%s, %s is outside the grid.", rowNum, colNum));
        }
        rows.get(rowNum).set(colNum, newValue);
    }

    public boolean invalid(int rowNum, int colNum) {
        return rowNum < 0 || rowNum >= rows.size() || colNum < 0 || colNum > rows.getFirst().size();
    }

    public GridValue getAdjacent(GridValue gridValue, Direction direction) {
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
            if (gridValue.colNum >= this.rows.getFirst().size() - 1)
                return false;
        }
        if (List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT).contains(direction)) {
            if (gridValue.rowNum >= this.rows.size() - 1)
                return false;
        }
        if (List.of(Direction.LEFT, Direction.UP_LEFT, Direction.DOWN_LEFT).contains(direction)) {
            return gridValue.colNum != 0;
        }
        return true;
    }

    private GridValue transformCoord(GridValue coords, int rowMod, int colMod) {
        var newRow = coords.rowNum + rowMod;
        var newCol = coords.colNum + colMod;
        return new GridValue(newRow, newCol, this.rows.get(newRow).get(newCol));
    }

    public class GridValue {
        public GridValue(int rowNum, int colNum, T value) {
            this.rowNum = rowNum;
            this.colNum = colNum;
            this.value = value;
        }
        public final int rowNum;
        public final int colNum;
        public final T value;
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
        for (ArrayList<T> row : this.rows) {
            for (T cur : row) {
                builder.append(cur.toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}


