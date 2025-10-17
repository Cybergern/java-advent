package aoc.problem4;

import aoc.util.Grid;
import aoc.util.ProblemException;

public class Part1 {
    public static Integer doWordSearch(Grid<Character> wordGrid, String wordToSearch) throws ProblemException {
        var result = 0;
        for (int i=0; i < wordGrid.getHeight(); i++) {
            for (int j=0; j < wordGrid.getWidth(); j++) {
                var cur = wordGrid.get(i, j);
                if (cur.value.equals(wordToSearch.charAt(0))) {
                    for (Grid.Direction dir : Grid.Direction.values()) {
                        if (isWordFound(wordGrid, wordToSearch, cur, dir)) {
                            result += 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean isWordFound(Grid<Character> wordGrid,
                                      String wordToSearch,
                                      Grid<Character>.GridValue start,
                                      Grid.Direction dir) {
        var gridPos = start;
        for (int i=0; i < wordToSearch.length(); i++) {
            if (!gridPos.value.equals(wordToSearch.charAt(i)))
                return false;
            gridPos = wordGrid.getAdjacent(gridPos, dir);
        }
        return true;
    }
}
