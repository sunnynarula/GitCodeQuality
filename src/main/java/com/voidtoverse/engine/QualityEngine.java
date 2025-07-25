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
     * Compute diff statistics for staged changes using the JGit library.
     *
     * <p>The returned array contains the number of files changed, insertions and deletions.
     * If JGit fails to compute the diff (e.g. repository not found or other errors), the
     * statistics will be zeroed.</p>
     *
     * @param repo the repository directory
     * @return an array where index 0 is files changed, 1 is insertions, 2 is deletions
     */
    private static int[] parseDiffStats(File repo) {
        int[] stats = new int[] {0, 0, 0};
        if (repo == null) {
            return stats;
        }
        try {
            // Open the repository using JGit
            org.eclipse.jgit.api.Git git = org.eclipse.jgit.api.Git.open(repo);
            // Obtain the list of diffs between HEAD and the index (staged changes)
            java.util.List<org.eclipse.jgit.diff.DiffEntry> diffs = git.diff().setCached(true).call();
            // Prepare a DiffFormatter to compute insertions and deletions per file
            org.eclipse.jgit.diff.DiffFormatter formatter = new org.eclipse.jgit.diff.DiffFormatter(new java.io.ByteArrayOutputStream());
            formatter.setRepository(git.getRepository());
            int files = 0;
            int insertions = 0;
            int deletions = 0;
            for (org.eclipse.jgit.diff.DiffEntry diff : diffs) {
                files++;
                try {
                    org.eclipse.jgit.diff.FileHeader header = formatter.toFileHeader(diff);
                    org.eclipse.jgit.diff.EditList edits = header.toEditList();
                    for (org.eclipse.jgit.diff.Edit edit : edits) {
                        insertions += edit.getEndB() - edit.getBeginB();
                        deletions += edit.getEndA() - edit.getBeginA();
                    }
                } catch (org.eclipse.jgit.errors.LargeObjectException e) {
                    // Skip large objects but count the file itself
                }
            }
            formatter.close();
            git.close();
            stats[0] = files;
            stats[1] = insertions;
            stats[2] = deletions;
        } catch (Exception e) {
            // ignore and leave stats zeroed
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