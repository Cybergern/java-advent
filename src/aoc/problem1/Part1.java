package aoc.problem1;

import aoc.util.ProblemException;

import java.util.List;

public class Part1 {
    public int getTotalDistance(List<Integer> left, List<Integer> right) throws ProblemException {
        if (left.size() != right.size()) {
            throw new ProblemException("Lists are different size");
        }
        var result = 0;
        var leftSorted = left.stream().sorted().toList();
        var rightSorted = right.stream().sorted().toList();
        for(int i=0; i < leftSorted.size(); i++) {
            result += Math.abs(leftSorted.get(i) - rightSorted.get(i));
        }
        return result;
    }
}
