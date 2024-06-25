package hilos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessor {
    private static class FileStatistics {
        int lines;
        int words;
        int characters;

        void add(FileStatistics other) {
            this.lines += other.lines;
            this.words += other.words;
            this.characters += other.characters;
        }
    }

    private static FileStatistics processFile(String filename) throws IOException {
        FileStatistics stats = new FileStatistics();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Pattern wordPattern = Pattern.compile("\\p{Alpha}+");
            while ((line = reader.readLine()) != null) {
                stats.lines++;
                stats.characters += line.length();
                Matcher matcher = wordPattern.matcher(line);
                while (matcher.find()) {
                    stats.words++;
                }
            }
        }
        return stats;
    }

    private static void processFilesSequentially(String[] filenames) throws IOException {
        long startTime = System.nanoTime();
        FileStatistics totalStats = new FileStatistics();
        for (String filename : filenames) {
            FileStatistics stats = processFile(filename);
            totalStats.add(stats);
            System.out.println("File: " + filename + " - Lines: " + stats.lines + ", Words: " + stats.words + ", Characters: " + stats.characters);
        }
        long endTime = System.nanoTime();
        System.out.println("Total - Lines: " + totalStats.lines + ", Words: " + totalStats.words + ", Characters: " + totalStats.characters);
        System.out.println("Sequential processing time: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static void processFilesConcurrently(String[] filenames) throws InterruptedException {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(filenames.length);
        FileStatistics totalStats = new FileStatistics();

        for (String filename : filenames) {
            executor.submit(() -> {
                try {
                    FileStatistics stats = processFile(filename);
                    synchronized (totalStats) {
                        totalStats.add(stats);
                        System.out.println("File: " + filename + " - Lines: " + stats.lines + ", Words: " + stats.words + ", Characters: " + stats.characters);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        long endTime = System.nanoTime();
        System.out.println("Total - Lines: " + totalStats.lines + ", Words: " + totalStats.words + ", Characters: " + totalStats.characters);
        System.out.println("Concurrent processing time: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileProcessor <sequential|concurrent> <file1> <file2> ... <fileN>");
            return;
        }

        String mode = args[0];
        String[] filenames = new String[args.length - 1];
        System.arraycopy(args, 1, filenames, 0, args.length - 1);

        try {
            if (mode.equalsIgnoreCase("sequential")) {
                processFilesSequentially(filenames);
            } else if (mode.equalsIgnoreCase("concurrent")) {
                processFilesConcurrently(filenames);
            } else {
                System.out.println("Invalid mode: " + mode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

