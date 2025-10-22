package aoc.problem9;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part1Test {

    @Test
    void very_small_repr() throws FileNotFoundException {
        assertEquals("0..111....22222", getRepresentation("test/data/problem9/very_small_input.txt"));
    }

    @Test
    void small_repr() throws FileNotFoundException {
        assertEquals("00...111...2...333.44.5555.6666.777.888899", getRepresentation("test/data/problem9/small_input.txt"));
    }

    @Test
    void big_repr() throws FileNotFoundException {
        assertEquals(239879L, getRepresentation("test/data/problem9/big_input.txt").length());
    }

    @Test
    void very_small() throws FileNotFoundException {
        assertEquals(60L, getChecksum("test/data/problem9/very_small_input.txt"));
    }

    @Test
    void small() throws FileNotFoundException {
        assertEquals(1928L, getChecksum("test/data/problem9/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(6344673854800L, getChecksum("test/data/problem9/big_input.txt"));
    }

    String getRepresentation(String path) throws FileNotFoundException {
        var contents = FileToStringList.readFileToStringList(path);
        return Part1.getRepresentation(contents.getFirst());
    }

    Long getChecksum(String path) throws FileNotFoundException {
        var contents = FileToStringList.readFileToStringList(path);
        return Part1.calculateChecksum(contents.getFirst());
    }

}