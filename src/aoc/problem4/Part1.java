package aoc.problem4;

import aoc.util.CharacterGrid;
import aoc.util.CharacterGrid.Direction;
import aoc.util.ProblemException;

public class Part1 {
    public static Integer doWordSearch(CharacterGrid wordGrid, String wordToSearch) throws ProblemException {
        var result = 0;
        for (int i=0; i < wordGrid.getHeight(); i++) {
            for (int j=0; j < wordGrid.getWidth(); j++) {
                var cur = wordGrid.get(i, j);
                if (cur.value() == wordToSearch.charAt(0)) {
                    for (Direction dir : Direction.values()) {
                        if (isWordFound(wordGrid, wordToSearch, cur, dir)) {
                            result += 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean isWordFound(CharacterGrid wordGrid,
                                      String wordToSearch,
                                      CharacterGrid.GridValue start,
                                      Direction dir) throws ProblemException {
        var gridPos = start;
        for (int i=0; i < wordToSearch.length(); i++) {
            if (!(gridPos.value() == wordToSearch.charAt(i)))
                return false;
            gridPos = wordGrid.getAdjacent(gridPos, dir);
        }
        return true;
    }
}
