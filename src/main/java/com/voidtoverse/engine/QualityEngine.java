package com.voidtoverse.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Computes commit quality scores based on the size of staged changes.
 *
 * <p>The scoring formula is defined by the project specification: lines added
 * plus lines removed plus five times the number of files changed【61889244827154†L18-L29】.</p>
 */
public final class QualityEngine {
    private QualityEngine() {}

    /**
     * Holds the results of a quality computation.
     */
    public static class Result {
        public final int quality;
        public final int score;
        public Result(int quality, int score) {
            this.quality = quality;
            this.score = score;
        }
    }

    /**
     * Run `git diff --shortstat --cached` and parse the file, insertion and deletion counts.
     *
     * <p>If the command fails or produces no output, zeros are returned.</p>
     *
     * @param repo the repository directory
     * @return an array where index 0 is files changed, 1 is insertions, 2 is deletions
     */
    private static int[] parseDiffStats(File repo) {
        int[] stats = new int[] {0, 0, 0};
        try {
            ProcessBuilder pb = new ProcessBuilder("git", "diff", "--shortstat", "--cached");
            pb.directory(repo);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line != null) {
                    Pattern pattern = Pattern.compile("(\\d+) file[s]? changed(, (\\d+) insertion[s]?\\(\\+\\))?(, (\\d+) deletion[s]?\\(-\\))?");
                    Matcher matcher = pattern.matcher(line.trim());
                    if (matcher.find()) {
                        stats[0] = Integer.parseInt(matcher.group(1));
                        if (matcher.group(3) != null) {
                            stats[1] = Integer.parseInt(matcher.group(3));
                        }
                        if (matcher.group(5) != null) {
                            stats[2] = Integer.parseInt(matcher.group(5));
                        }
                    }
                }
            }
        } catch (IOException e) {
            // ignore and return zeros
        }
        return stats;
    }

    /**
     * Compute a quality score for the staged changes in the given repository.
     *
     * <p>The raw score is calculated as the sum of insertions, deletions and
     * five times the number of files changed. A quality percentage is then
     * derived by subtracting the raw score from 100 and clamping the result
     * between 0 and 100.</p>
     *
     * @param repo the repository directory
     * @return a {@link Result} containing both quality percentage and raw score
     */
    public static Result calculateQuality(File repo) {
        int[] stats = parseDiffStats(repo);
        int filesChanged = stats[0];
        int insertions = stats[1];
        int deletions = stats[2];
        int score = insertions + deletions + (filesChanged * 5);
        int quality = 100 - score;
        if (quality < 0) {
            quality = 0;
        } else if (quality > 100) {
            quality = 100;
        }
        return new Result(quality, score);
    }
}