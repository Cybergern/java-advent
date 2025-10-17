package aoc.problem2;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(4, getSafeReports("test/data/problem2/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(373, getSafeReports("test/data/problem2/big_input.txt"));
    }

    long getSafeReports(String path) throws FileNotFoundException {
        return FileToStringList.readFileToStringList(path)
                .stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList())
                .map(Part2::isSafeTolerant)
                .filter(b -> b)
                .count();
    }
}