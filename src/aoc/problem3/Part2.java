package aoc.problem3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static Integer scanMemory(String input) {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("(mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\))").matcher(input);
        while(m.find()) {
            allMatches.add(m.group());
        }
        var result = 0;
        var enabled = true;
        for (String match : allMatches) {
            if (match.startsWith("do(")) {
                enabled = true;
            } else if (match.startsWith("don")) {
                enabled = false;
            } else {
                if (enabled) {
                    var arguments = match.substring(4).replaceAll("\\)", "").split(",");
                    result += Integer.parseInt(arguments[0]) * Integer.parseInt(arguments[1]);
                }
            }
        }
        return result;
    }
}
