package aoc.problem4;

import aoc.util.FileToStringList;
import aoc.util.Grid;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void small() throws FileNotFoundException, ProblemException {
        assertEquals(18, getWordSearchResult("test/data/problem4/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException, ProblemException {
        assertEquals(2593, getWordSearchResult("test/data/problem4/big_input.txt"));
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
        return Part1.doWordSearch(grid, "XMAS");
    }

}