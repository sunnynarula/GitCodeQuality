package com.voidtoverse.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Handles reading and writing simple configuration files for the GitCodeQuality
 * application.
 *
 * <p>This class currently supports persistence of the list of recently opened
 * repositories. To avoid external JSON dependencies we store the list in a
 * minimal JSON array format. The file is located under the user’s home
 * directory in <code>~/.gitgui/recent_repos.json</code>. If the file does not
 * exist, an empty list will be returned.</p>
 */
public final class Persistence {
    private static final String CONFIG_DIR = System.getProperty("user.home") + "/.gitgui";
    private static final String RECENT_FILE = CONFIG_DIR + "/recent_repos.json";

    // file storing the last opened repository path
    private static final String USER_SETTINGS_FILE = CONFIG_DIR + "/user-settings.json";

    private Persistence() {}

    /**
     * Load the list of recent repository paths from the JSON file.
     *
     * @return a list of absolute paths to recently opened Git repositories
     */
    public static List<String> loadRecentRepositories() {
        Path path = Paths.get(RECENT_FILE);
        List<String> repos = new ArrayList<>();
        if (!Files.exists(path)) {
            return repos;
        }
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String json = reader.readLine();
            // very simple parser: remove brackets and quotes, then split by comma
            if (json != null && json.length() > 2) {
                String content = json.substring(1, json.length() - 1);
                for (String item : content.split(",")) {
                    String trimmed = item.trim();
                    if (trimmed.length() >= 2) {
                        repos.add(trimmed.substring(1, trimmed.length() - 1));
                    }
                }
            }
        } catch (IOException e) {
            // ignore malformed file and return empty list
        }
        return repos;
    }

    /**
     * Persist the given list of recent repository paths.
     *
     * <p>This method writes the provided list to a JSON file under
     * {@code ~/.gitgui/recent_repos.json}. A simple array of quoted paths is
     * generated without relying on external JSON libraries. The parent
     * directory is created on demand.</p>
     *
     * @param repos the list of paths to write
     */
    public static void saveRecentRepositories(List<String> repos) {
        try {
            Path dir = Paths.get(CONFIG_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path file = Paths.get(RECENT_FILE);
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                StringJoiner joiner = new StringJoiner(",", "[", "]");
                for (String repo : repos) {
                    joiner.add("\"" + repo + "\"");
                }
                writer.write(joiner.toString());
            }
        } catch (IOException e) {
            // silently ignore write failures
        }
    }

    /**
     * Load the last selected repository path from the user settings file.
     *
     * @return the path to the last repository, or {@code null} if none is stored
     */
    public static String loadLastRepository() {
        Path path = Paths.get(USER_SETTINGS_FILE);
        if (!Files.exists(path)) {
            return null;
        }
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            if (line != null && !line.isBlank()) {
                return line.trim();
            }
        } catch (IOException e) {
            // ignore and return null
        }
        return null;
    }

    /**
     * Persist the path of the last selected repository.
     *
     * @param repo absolute path of the selected repository
     */
    public static void saveLastRepository(String repo) {
        try {
            Path dir = Paths.get(CONFIG_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path file = Paths.get(USER_SETTINGS_FILE);
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write(repo == null ? "" : repo);
            }
        } catch (IOException e) {
            // ignore write failures
        }
    }

}