package aoc.problem3;

import aoc.util.FileToStringList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Part2Test {

    @Test
    void small() throws FileNotFoundException {
        assertEquals(48, getInstructionResult("test/data/problem3/small_input_2.txt"));
    }

    @Test
    void big() throws FileNotFoundException {
        assertEquals(106780429, getInstructionResult("test/data/problem3/big_input.txt"));
    }

    int getInstructionResult(String path) throws FileNotFoundException {
        return Part2.scanMemory(FileToStringList.readFileToStringList(path)
                .stream()
                .reduce("", (subtotal, line) -> subtotal + line));
    }

}