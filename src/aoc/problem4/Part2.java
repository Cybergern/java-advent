package aoc.problem4;

import aoc.util.CharacterGrid;
import aoc.util.CharacterGrid.Direction;
import aoc.util.ProblemException;

import java.util.List;

public class Part2 {
    public static Integer findWordCross(CharacterGrid wordGrid) throws ProblemException {
        var result = 0;
        for (int i=0; i < wordGrid.getHeight(); i++) {
            for (int j=0; j < wordGrid.getWidth(); j++) {
                var cur = wordGrid.get(i, j);
                if (cur.value() == 'A') {
                    var dirsToSearch = List.of(Direction.DOWN_RIGHT, Direction.DOWN_LEFT, Direction.UP_LEFT, Direction.UP_RIGHT);
                    var adjacentChars = new StringBuilder();
                    if (dirsToSearch.stream().allMatch(d -> wordGrid.validDirection(cur, d))) {
                        for (Direction dir : dirsToSearch) {
                            adjacentChars.append(wordGrid.getAdjacent(cur, dir).value());
                        }
                        if ((adjacentChars.toString().contains("SS") || adjacentChars.toString().contains("MM"))
                                && countChars(adjacentChars.toString(), 'S') == 2
                                && countChars(adjacentChars.toString(), 'M') == 2) {
                            result += 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static int countChars(String s, Character charToCount) {
        var result = 0;
        for (Character c : s.toCharArray()) {
            if (c.equals(charToCount)) {
                result += 1;
            }
        }
        return result;
    }
}
