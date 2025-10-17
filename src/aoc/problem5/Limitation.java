package aoc.problem5;

import java.util.List;

public record Limitation(Integer before, Integer after) {
    public boolean checkLimitation(List<Integer> sequence) {
        return sequence.indexOf(before) < sequence.indexOf(after);
    }
}
