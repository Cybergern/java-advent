package aoc.problem5;

import java.util.List;

public class Part1 {
    List<Limitation> limitations;

    public Part1(List<Limitation> limitations) {
        this.limitations = limitations;
    }

    public boolean verify(List<Integer> pages) {
        return limitations.stream()
                .filter(lim -> pages.contains(lim.before()) && pages.contains(lim.after()))
                .allMatch(lim -> lim.checkLimitation(pages));
    }

    public Integer getMiddleValue(List<Integer> pages) {
        return pages.get((pages.size() - 1)/ 2);
    }

}

