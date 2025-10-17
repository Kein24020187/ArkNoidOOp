package main;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "data/highscores.txt";

    public static void saveScore(String name, int score) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(name + ":" + score);
            writer.newLine();
        }
    }

    public static List<String> loadScores() throws IOException {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) scores.add(line);
        }
        return scores;
    }

    public static String searchScore(String name) throws IOException {
        for (String s : loadScores()) {
            if (s.startsWith(name + ":")) return s;
        }
        return "Not found";
    }
}
