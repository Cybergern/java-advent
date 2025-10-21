package aoc.problem7;

import aoc.util.FileToStringList;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void small() throws FileNotFoundException, ProblemException {
        assertEquals(3749L, handleInput("test/data/problem7/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException, ProblemException {
        assertEquals(1153997401072L, handleInput("test/data/problem7/big_input.txt"));
    }

    Long handleInput(String path) throws FileNotFoundException, ProblemException {
        var contents = FileToStringList.readFileToStringList(path);
        var equations = new ArrayList<Equation>();
        for (String line : contents) {
            var parts = line.split(":");
            var result = Long.parseLong(parts[0]);
            var terms = Arrays.stream(parts[1].trim().split(" ")).map(p -> Long.parseLong(p.trim())).toList();
            equations.add(new Equation(result, terms));
        }
        return Part1.handleEquations(equations);
    }

}