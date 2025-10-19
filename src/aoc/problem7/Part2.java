package aoc.problem7;

import aoc.util.ProblemException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {
    public static List<String> OPERATORS = List.of("+", "*", "||");

    public static Long handleEquations(List<Equation> equations) throws ProblemException {
        var sum = 0L;
        for (Equation eq : equations) {
            var permutations = makeAllPermutations(OPERATORS, eq.terms().size() - 1L);
            for (List<String> ops : permutations) {
                if (getResult(eq.terms(), ops).equals(eq.result())) {
                    sum += eq.result();
                    break;
                }
            }
        }
        return sum;
    }

    private static Long getResult(List<Long> terms, List<String> operators) throws ProblemException {
        if (terms.size() - operators.size() != 1) {
            throw new ProblemException("The number of terms should be one more than the number of operators");
        }
        return getResult(terms.subList(1, terms.size()), operators, terms.getFirst());
    }

    private static Long getResult(List<Long> terms, List<String> operators, Long start) {
        if (terms.isEmpty() && operators.isEmpty()) {
            return start;
        } else {
            return getResult(terms.subList(1, terms.size()),
                    operators.subList(1, operators.size()),
                    calculate(start, operators.getFirst(), terms.getFirst()));
        }
    }

    private static Long calculate(Long left, String op, Long right) {
        return switch(op) {
            case "*" -> left * right;
            case "+" -> left + right;
            case "||" -> Long.parseLong(left.toString() + right.toString());
            default -> 0L;
        };
    }

    public static List<List<String>> makeAllPermutations(List<String> choices, Long length) {
        return makeAllPermutations(choices, length, Collections.emptyList());
    }

    private static List<List<String>> makeAllPermutations(List<String> choices, Long remaining, List<String> start) {
        if (remaining == 0) {
            return List.of(start);
        } else {
            var results = new ArrayList<List<String>>();
            for (String choice : choices) {
                var newStart = new ArrayList<>(start);
                newStart.add(choice);
                results.addAll(makeAllPermutations(choices, remaining-1, newStart));
            }
            return results;
        }
    }
}

