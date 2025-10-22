package aoc.problem9;

import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static String getRepresentation(String input) {
        var builder = new StringBuilder();
        var repr = buildRepresentation(input);
        for (Integer s : repr) {
            if (s < 0) {
                builder.append(".");
            } else {
                builder.append(s);
            }
        }
        return builder.toString();
    }

    private static List<Integer> buildRepresentation(String input) {
        var repr = new ArrayList<Integer>();
        var id = 0;
        var i = 0;
        while(true) {
            repr.addAll(getIntegerList(id, input.charAt(i)));
            id++;
            i++;
            if (i >= input.length()) {
                break;
            }
            repr.addAll(getIntegerList(-1, input.charAt(i)));
            i++;
        }
        return repr;
    }

    public static Long calculateChecksum(String input) {
        var repr = compress(buildRepresentation(input));
        var sum = 0L;
        for(int i=0; i < repr.size(); i++) {
            if (repr.get(i) == -1) {
                break;
            } else {
                sum += repr.get(i) * i;
            }
        }
        return sum;
    }

    private static List<Integer> compress(List<Integer> repr) {
        var firstEmpty = getIndexOfFirstEmpty(repr);
        var lastId = getIndexOfLastId(repr);
        while (firstEmpty < lastId) {
            repr.set(firstEmpty, repr.get(lastId));
            repr.set(lastId, -1);
            firstEmpty = getIndexOfFirstEmpty(repr);
            lastId = getIndexOfLastId(repr);
        }
        return repr;
    }

    private static int getIndexOfFirstEmpty(List<Integer> repr) {
        return repr.indexOf(-1);
    }

    private static int getIndexOfLastId(List<Integer> repr) {
        var i = repr.size() - 1;
        while(repr.get(i) == -1) {
            i--;
        }
        return i;
    }

    private static List<Integer> getIntegerList(Integer intToAdd, Character n) {
        var list = new ArrayList<Integer>();
        var count = Integer.parseInt(String.valueOf(n));
        for (int j=0; j < count; j++) {
            list.add(intToAdd);
        }
        return list;
    }

}

