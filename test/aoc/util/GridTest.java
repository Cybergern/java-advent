package aoc.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    void testSmallGrid() throws ProblemException {
        var subject = new Grid<Character>(3);
        List<List<Character>> rows = new ArrayList<>();
        rows.add(List.of('A', 'B', 'C'));
        rows.add(List.of('D', 'E', 'F'));
        rows.add(List.of('G', 'H', 'I'));
        subject.fillGrid(rows);
        assertEquals('A', subject.get(0, 0).value);
        assertFalse(subject.validDirection(subject.get(0, 0), Grid.Direction.UP));
        assertFalse(subject.validDirection(subject.get(0, 0), Grid.Direction.UP_LEFT));
        assertFalse(subject.validDirection(subject.get(0, 0), Grid.Direction.UP_RIGHT));
        assertTrue(subject.validDirection(subject.get(0, 0), Grid.Direction.DOWN));
        assertTrue(subject.validDirection(subject.get(0, 0), Grid.Direction.DOWN_RIGHT));
        assertFalse(subject.validDirection(subject.get(0, 0), Grid.Direction.DOWN_LEFT));
        assertFalse(subject.validDirection(subject.get(0, 0), Grid.Direction.LEFT));
        assertTrue(subject.validDirection(subject.get(0, 0), Grid.Direction.RIGHT));
        assertEquals('B', subject.getAdjacent(subject.get(0, 0), Grid.Direction.RIGHT).value);
        assertEquals('D', subject.getAdjacent(subject.get(0, 0), Grid.Direction.DOWN).value);
        assertEquals('E', subject.getAdjacent(subject.get(0, 0), Grid.Direction.DOWN_RIGHT).value);
        assertEquals('A', subject.getAdjacent(subject.get(0, 0), Grid.Direction.LEFT).value);
        assertEquals('I', subject.get(2, 2).value);
        assertTrue(subject.validDirection(subject.get(2, 2), Grid.Direction.UP));
        assertTrue(subject.validDirection(subject.get(2, 2), Grid.Direction.UP_LEFT));
        assertFalse(subject.validDirection(subject.get(2, 2), Grid.Direction.UP_RIGHT));
        assertFalse(subject.validDirection(subject.get(2, 2), Grid.Direction.DOWN));
        assertFalse(subject.validDirection(subject.get(2, 2), Grid.Direction.DOWN_RIGHT));
        assertFalse(subject.validDirection(subject.get(2, 2), Grid.Direction.DOWN_LEFT));
        assertTrue(subject.validDirection(subject.get(2, 2), Grid.Direction.LEFT));
        assertFalse(subject.validDirection(subject.get(2, 2), Grid.Direction.RIGHT));
    }

}
