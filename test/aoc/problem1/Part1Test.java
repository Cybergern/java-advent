package aoc.problem1;

import aoc.util.FileToStringList;
import aoc.util.ProblemException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Part1Test {

    @Test
    void getTotalDistanceSmall() throws ProblemException, FileNotFoundException {
        assertEquals(11, getTotalDistance("test/data/problem1/small_input.txt"));
    }

    @Test
    void getTotalDistanceBig() throws ProblemException, FileNotFoundException {
        assertEquals(1941353, getTotalDistance("test/data/problem1/big_input.txt"));
    }

    private int getTotalDistance(String path) throws ProblemException, FileNotFoundException {
        var comparator = new Part1();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        var contents = FileToStringList.readFileToStringList(path);
        for (String line: contents) {
            var parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
        return comparator.getTotalDistance(left, right);
    }
}