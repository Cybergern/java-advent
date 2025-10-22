package aoc.problem9;

import java.util.ArrayList;
import java.util.List;

public class Part2 {

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
            if (repr.get(i) != -1) {
                sum += repr.get(i) * i;
            }
        }
        return sum;
    }

    private static List<Integer> compress(List<Integer> repr) {
        var curId = getLastId(repr);
        while (curId >= 0) {
            var fileSize = getSize(repr, curId);
            var emptyIndex = getFirstEmptySpaceOfSize(repr, fileSize);
            if (emptyIndex != -1) {
                var fileIndex = repr.indexOf(curId);
                if (emptyIndex < fileIndex) {
                    for (int i = 0; i < fileSize; i++) {
                        repr.set(emptyIndex + i, curId);
                        repr.set(fileIndex + i, -1);
                    }
                }
            }
            curId--;
        }
        return repr;
    }

    private static int getFirstEmptySpaceOfSize(List<Integer> repr, int size) {
        var sizeCount = 0;
        for (int i=0; i < repr.size();i++) {
            if (sizeCount == size) {
                return i - size;
            } else if (repr.get(i) == -1) {
                sizeCount++;
            } else {
                sizeCount = 0;
            }
        }
        return -1;
    }

    private static int getSize(List<Integer> repr, int fileId) {
        var start = repr.indexOf(fileId);
        var size = 0;
        for (int i=start; i < repr.size();i++) {
            if (repr.get(i) == fileId) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }

    private static Integer getLastId(List<Integer> repr) {
        var i = repr.size() - 1;
        while(repr.get(i) == -1) {
            i--;
        }
        return repr.get(i);
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

