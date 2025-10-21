package aoc.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import aoc.util.CharacterGrid.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    void testSmallGrid() throws ProblemException {
        var subject = new CharacterGrid(3, 3);
        List<List<Character>> rows = new ArrayList<>();
        rows.add(List.of('A', 'B', 'C'));
        rows.add(List.of('D', 'E', 'F'));
        rows.add(List.of('G', 'H', 'I'));
        subject.fillGrid(rows);
        assertEquals('A', subject.get(0, 0).value());
        assertFalse(subject.validDirection(subject.get(0, 0), Direction.UP));
        assertFalse(subject.validDirection(subject.get(0, 0), Direction.UP_LEFT));
        assertFalse(subject.validDirection(subject.get(0, 0), Direction.UP_RIGHT));
        assertTrue(subject.validDirection(subject.get(0, 0), Direction.DOWN));
        assertTrue(subject.validDirection(subject.get(0, 0), Direction.DOWN_RIGHT));
        assertFalse(subject.validDirection(subject.get(0, 0), Direction.DOWN_LEFT));
        assertFalse(subject.validDirection(subject.get(0, 0), Direction.LEFT));
        assertTrue(subject.validDirection(subject.get(0, 0), Direction.RIGHT));
        assertEquals('B', subject.getAdjacent(subject.get(0, 0), Direction.RIGHT).value());
        assertEquals('D', subject.getAdjacent(subject.get(0, 0), Direction.DOWN).value());
        assertEquals('E', subject.getAdjacent(subject.get(0, 0), Direction.DOWN_RIGHT).value());
        assertEquals('A', subject.getAdjacent(subject.get(0, 0), Direction.LEFT).value());
        assertEquals('I', subject.get(2, 2).value());
        assertTrue(subject.validDirection(subject.get(2, 2), Direction.UP));
        assertTrue(subject.validDirection(subject.get(2, 2), Direction.UP_LEFT));
        assertFalse(subject.validDirection(subject.get(2, 2), Direction.UP_RIGHT));
        assertFalse(subject.validDirection(subject.get(2, 2), Direction.DOWN));
        assertFalse(subject.validDirection(subject.get(2, 2), Direction.DOWN_RIGHT));
        assertFalse(subject.validDirection(subject.get(2, 2), Direction.DOWN_LEFT));
        assertTrue(subject.validDirection(subject.get(2, 2), Direction.LEFT));
        assertFalse(subject.validDirection(subject.get(2, 2), Direction.RIGHT));
    }

}
