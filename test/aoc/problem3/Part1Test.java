package aoc.problem3;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Part1Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(161, getInstructionResult("test/data/problem3/small_input.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(196826776, getInstructionResult("test/data/problem3/big_input.txt"));
    }

    int getInstructionResult(String path) throws FileNotFoundException {
        return Part1.scanMemory(FileToStringList.readFileToStringList(path)
                .stream()
                .reduce("", (subtotal, line) -> subtotal + line));
    }

}