package aoc.problem5;

import java.util.List;

public class Part2 {
    List<Limitation> limitations;

    public Part2(List<Limitation> limitations) {
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

    public List<Integer> fixOrder(List<Integer> pages) {
        var relevantLimitations = limitations.stream()
                .filter(lim -> pages.contains(lim.before()) && pages.contains(lim.after()))
                .toList();
        return pages.stream()
                .sorted((o1, o2) -> countBefores(relevantLimitations, o1).compareTo(countBefores(relevantLimitations, o2)))
                .toList();
    }

    private Long countBefores(List<Limitation> limitations, Integer page) {
        return limitations.stream()
                .filter(lim -> lim.before().equals(page))
                .count();
    }

}

