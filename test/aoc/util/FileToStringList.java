package aoc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToStringList {
    public static List<String> readFileToStringList(String path) throws FileNotFoundException {
        File listSource = new File(path);
        var allLines = new ArrayList<String>();
        try (Scanner myReader = new Scanner(listSource)) {
            while (myReader.hasNextLine()) {
                allLines.add(myReader.nextLine());
            }
        }
        return allLines;
    }
}
