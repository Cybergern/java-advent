package aoc.problem2;

import java.util.List;
import java.util.stream.IntStream;

public class Part1 {
    public static Boolean isSafe(List<Integer> report) {
        var result = IntStream.range(1, report.size())
                .map(i -> report.get(i-1) - report.get(i))
                .boxed()
                .toList();
        var allSameSign = result.stream().allMatch(i -> i < 0) || result.stream().allMatch(i -> i > 0);
        var stepsNotTooBig = result.stream().map(Math::abs).allMatch(i -> i > 0 && i < 4);
        return allSameSign && stepsNotTooBig;
    }
}
