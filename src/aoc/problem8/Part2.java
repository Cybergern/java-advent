package aoc.problem8;

import aoc.util.CharacterGrid;
import aoc.util.Combinatorics;
import aoc.util.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part2 {
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
        var antennas = map.getAll(antenna);
        var combinatorics = new Combinatorics<Position>();
        var combinations = combinatorics.getAllSubsets(antennas).stream()
                .filter(set -> set.size() == 2)
                .collect(Collectors.toSet());
        for (Set<Position> pos : combinations) {
            antinodes.addAll(findAntinodes(pos.stream().toList(), map.getHeight(), map.getWidth()));
        }
        return antinodes;
    }

    private static Set<Position> findAntinodes(List<Position> antennas, int maxRow, int maxCol) {
        var allAntinodes = new HashSet<Position>();
        var i = 1;
        while(true) {
            var newPos = getMultipleDistance(antennas.get(0), antennas.get(1), i);
            if (newPos.col() < 0 || newPos.col() >= maxCol || newPos.row() < 0 || newPos.row() >= maxRow) {
                break;
            } else {
                allAntinodes.add(newPos);
                i++;
            }
        }
        i = 1;
        while(true) {
            var newPos = getMultipleDistance(antennas.get(1), antennas.get(0), i);
            if (newPos.col() < 0 || newPos.col() >= maxCol || newPos.row() < 0 || newPos.row() >= maxRow) {
                break;
            } else {
                allAntinodes.add(newPos);
                i++;
            }
        }
        return allAntinodes;

    }

    private static Position getMultipleDistance(Position start, Position next, Integer multiple) {
        return new Position(start.row() + ((next.row() - start.row()) * multiple),
                start.col() + ((next.col() - start.col()) * multiple));
    }

}

