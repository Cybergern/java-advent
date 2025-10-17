package aoc.problem3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static Integer scanMemory(String input) {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("mul\\(\\d+,\\d+\\)").matcher(input);
        while(m.find()) {
            allMatches.add(m.group());
        }
        return allMatches.stream()
                .map(mul -> {
                    var arguments = mul.substring(4).replaceAll("\\)", "").split(",");
                    return Integer.parseInt(arguments[0]) * Integer.parseInt(arguments[1]);
                })
                .reduce(0, Integer::sum);
    }
}
