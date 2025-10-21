package aoc.problem8;

import aoc.util.FileToStringList;
import aoc.util.CharacterGrid;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void small() throws FileNotFoundException, ProblemException {
        assertEquals(14L, handleInput("test/data/problem8/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException, ProblemException {
        assertEquals(341L, handleInput("test/data/problem8/big_input.txt"));
    }

    Long handleInput(String path) throws FileNotFoundException, ProblemException {
        var contents = FileToStringList.readFileToStringList(path);
        var rows = new ArrayList<List<Character>>();
        for (String line : contents) {
            var newRow = new ArrayList<Character>();
            rows.add(newRow);
            for (Character ch : line.toCharArray()) {
                newRow.add(ch);
            }
        }
        var grid = new CharacterGrid(rows.size(), rows.getFirst().size());
        grid.fillGrid(rows);
        return Part1.locateAntinodes(grid);
    }

}