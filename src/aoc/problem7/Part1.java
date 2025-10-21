package aoc.problem7;

import aoc.util.Combinatorics;
import aoc.util.ProblemException;

import java.util.List;

public class Part1 {
    public static List<String> OPERATORS = List.of("+", "*");

    public static Long handleEquations(List<Equation> equations) throws ProblemException {
        var sum = 0L;
        var combinatorics = new Combinatorics<String>();
        for (Equation eq : equations) {
            var permutations = combinatorics.makeAllPermutations(OPERATORS, eq.terms().size() - 1L);
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
            default -> 0L;
        };
    }


}

