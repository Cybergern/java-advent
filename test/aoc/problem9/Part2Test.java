package aoc.problem9;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(2858L, getChecksum("test/data/problem9/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(6360363199987L, getChecksum("test/data/problem9/big_input.txt"));
    }

    Long getChecksum(String path) throws FileNotFoundException {
        var contents = FileToStringList.readFileToStringList(path);
        return Part2.calculateChecksum(contents.getFirst());
    }

}