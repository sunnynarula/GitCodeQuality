package com.voidtoverse.engine;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.errors.LargeObjectException;
import org.eclipse.jgit.patch.FileHeader;

import java.io.File;
import java.util.List;

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
            Git git = Git.open(repo);
            // Obtain the list of diffs between HEAD and the index (staged changes)
            List<DiffEntry> diffs = git.diff().setCached(true).call();
            // Prepare a DiffFormatter to compute insertions and deletions per file
            DiffFormatter formatter = new DiffFormatter(new java.io.ByteArrayOutputStream());
            formatter.setRepository(git.getRepository());
            int files = 0;
            int insertions = 0;
            int deletions = 0;
            for (DiffEntry diff : diffs) {
                files++;
                try {
                    FileHeader header = formatter.toFileHeader(diff);
                    EditList edits = header.toEditList();
                    for (org.eclipse.jgit.diff.Edit edit : edits) {
                        insertions += edit.getEndB() - edit.getBeginB();
                        deletions += edit.getEndA() - edit.getBeginA();
                    }
                } catch (LargeObjectException e) {
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