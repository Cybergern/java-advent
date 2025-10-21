package aoc.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CombinatoricsTest {
    @Test
    void permutation() {
        var subject = new Combinatorics<String>();
        assertEquals(8L, subject.makeAllPermutations(List.of("A", "B"), 3L).size());
        assertEquals(81L, subject.makeAllPermutations(List.of("A", "B", "C"), 4L).size());
    }

    @Test
    void combinations() {
        var subject = new Combinatorics<String>();
        var result = subject.getAllSubsets(Set.of("A", "B", "C"));
        assertEquals(8, result.size());
        assertTrue(result.contains(Set.of("A", "B", "C")));
        assertTrue(result.contains(Set.of("A", "B")));
        assertTrue(result.contains(Set.of("A", "C")));
        assertTrue(result.contains(Set.of("B", "C")));
        assertTrue(result.contains(Set.of("A")));
        assertTrue(result.contains(Set.of("B")));
        assertTrue(result.contains(Set.of("C")));
        assertTrue(result.contains(Set.of()));
    }
}