package aoc.problem2;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(2, getSafeReports("test/data/problem2/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(314, getSafeReports("test/data/problem2/big_input.txt"));
    }

    int getSafeReports(String path) throws FileNotFoundException {
        var contents = FileToStringList.readFileToStringList(path);
        var safeReports = 0;
        for (String line : contents) {
            var report = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
            if (Part1.isSafe(report)) {
                safeReports += 1;
            }
        }
        return safeReports;
    }
}