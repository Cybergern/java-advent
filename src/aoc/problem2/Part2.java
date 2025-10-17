package aoc.problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {
    public static Boolean isSafeTolerant(List<Integer> report) {
        var variations = IntStream
                .range(0, report.size())
                .mapToObj(i -> {
                    var copy = new ArrayList<>(List.copyOf(report));
                    copy.remove(i);
                    return copy;
                })
                .toList();
        return variations.stream().anyMatch(Part1::isSafe);
    }
}
