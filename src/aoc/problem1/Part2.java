package aoc.problem1;

import aoc.util.ProblemException;

import java.util.List;

public class Part2 {
    public int getTotalDistance(List<Integer> left, List<Integer> right) throws ProblemException {
        if (left.size() != right.size()) {
            throw new ProblemException("Lists are different size");
        }
        return left.stream()
                .reduce(0, (subtotal, i) ->
                    subtotal + (Math.toIntExact(right.stream().filter(x -> x.equals(i)).count())) * i);
    }

}
