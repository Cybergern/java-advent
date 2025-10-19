package aoc.problem7;

import aoc.util.FileToStringList;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    @Test
    void small() throws FileNotFoundException, ProblemException {
        assertEquals(11387L, handleInput("test/data/problem7/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException, ProblemException {
        assertEquals(97902809384118L, handleInput("test/data/problem7/big_input.txt"));
    }

    @Test
    void permutation() {
        assertEquals(8L, Part1.makeAllPermutations(List.of("A", "B"), 3L).size());
        assertEquals(81L, Part1.makeAllPermutations(List.of("A", "B", "C"), 4L).size());
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
        return Part2.handleEquations(equations);
    }

}