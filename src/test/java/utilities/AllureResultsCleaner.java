package utilities;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class AllureResultsCleaner {

    private static final String ALLURE_RESULTS_PATH = "/Users/subrat/Desktop/LatestCucumberProject/allure-results"; // specify the path to your allure-results directory

    public static void cleanAllureResults() {
        Path directory = Paths.get(ALLURE_RESULTS_PATH);

        if (Files.exists(directory)) {
            try {
                Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println("Allure results directory cleaned successfully.");
            } catch (IOException e) {
                System.err.println("Failed to clean allure-results directory: " + e.getMessage());
            }
        } else {
            System.out.println("Allure results directory does not exist or has already been cleaned.");
        }
    }
}
