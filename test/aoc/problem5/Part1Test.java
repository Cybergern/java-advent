package aoc.problem5;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(143, handleInput("test/data/problem5/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(5248, handleInput("test/data/problem5/big_input.txt"));
    }

    int handleInput(String path) throws FileNotFoundException {
        var contents = FileToStringList.readFileToStringList(path);
        var rowIndex = 0;
        var limitations = new ArrayList<Limitation>();
        while (!contents.get(rowIndex).isEmpty()) {
            var parts = contents.get(rowIndex).split(Pattern.quote("|"));
            limitations.add(new Limitation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            rowIndex++;
        }
        rowIndex++;
        var middleSum = 0;
        var checker = new Part1(limitations);
        while (rowIndex < contents.size()) {
            var pages = Arrays.stream(contents.get(rowIndex).split(","))
                    .map(Integer::parseInt)
                    .toList();
            if (checker.verify(pages)) {
                middleSum += checker.getMiddleValue(pages);
            }
            rowIndex++;
        }
        return middleSum;
    }

}