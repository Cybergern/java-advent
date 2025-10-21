package aoc.problem8;

import aoc.util.CharacterGrid;
import aoc.util.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {
    public static long locateAntinodes(CharacterGrid map) {
        var uniqueChars = map.getUniqueChars();
        var allAntinodes = new HashSet<Position>();
        for (Character frequency : uniqueChars) {
            allAntinodes.addAll(getAntinodes(map, frequency));
        }
        return allAntinodes.size();
    }

    public static Set<Position> getAntinodes(CharacterGrid map, Character antenna) {
        var antinodes = new HashSet<Position>();
        var antennaLocations = map.getAll(antenna);
        for (int i=0; i < map.getHeight(); i++) {
            for (int j=0; j < map.getWidth(); j++) {
                if (isAntinode(new Position(i, j), antennaLocations.stream().toList())) {
                    antinodes.add(new Position(i, j));
                }
            }
        }
        return antinodes;
    }

    public static boolean isAntinode(Position cur, List<Position> locations) {
        if (locations.contains(cur)) {
            return false;
        }
        for (Position loc : locations) {
            var doubleDistancePosition = getDoubleDistance(cur, loc);
            if (locations.contains(doubleDistancePosition)) {
                return true;
            }
        }
        return false;
    }

   private static Position getDoubleDistance(Position start, Position next) {
        return new Position(start.row() + ((next.row() - start.row()) * 2),
                start.col() + ((next.col() - start.col()) * 2));
    }

}

