package aoc.problem4;

import aoc.util.FileToStringList;
import aoc.util.Grid;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    @Test
    void small() throws FileNotFoundException, ProblemException {
        assertEquals(9, getWordSearchResult("test/data/problem4/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException, ProblemException {
        assertEquals(1950, getWordSearchResult("test/data/problem4/big_input.txt"));
    }

    int getWordSearchResult(String path) throws FileNotFoundException, ProblemException {
        var contents = FileToStringList.readFileToStringList(path);
        var rows = new ArrayList<List<Character>>();
        for (String line : contents) {
            var newRow = new ArrayList<Character>();
            rows.add(newRow);
            for (Character ch : line.toCharArray()) {
                newRow.add(ch);
            }
        }
        var grid = new Grid<Character>(rows.size());
        grid.fillGrid(rows);
        return Part2.findWordCross(grid);
    }

}