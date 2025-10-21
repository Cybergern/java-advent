package aoc.util;

import java.util.*;

public class Combinatorics<T> {
    public List<List<T>> makeAllPermutations(List<T> choices, Long length) {
        return makeAllPermutations(choices, length, Collections.emptyList());
    }

    private List<List<T>> makeAllPermutations(List<T> choices, Long remaining, List<T> start) {
        if (remaining == 0) {
            return List.of(start);
        } else {
            var results = new ArrayList<List<T>>();
            for (T choice : choices) {
                var newStart = new ArrayList<>(start);
                newStart.add(choice);
                results.addAll(makeAllPermutations(choices, remaining-1, newStart));
            }
            return results;
        }
    }

    public Set<Set<T>> getAllSubsets(Set<T> masterSet) {
        Set<Set<T>> allSubsets = new HashSet<>();
        var masterList = masterSet.stream().toList();
        int max = 1 << masterSet.size();             //there are 2 power n different subsets

        for (int i = 0; i < max; i++) {
            ArrayList<T> subset = new ArrayList<>();
            for (int j = 0; j < masterSet.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subset.add(masterList.get(j));
                }
            }
            allSubsets.add(new HashSet<>(subset));
        }
        return allSubsets;
    }
}
