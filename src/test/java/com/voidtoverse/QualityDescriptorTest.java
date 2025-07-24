package com.voidtoverse;

import com.voidtoverse.engine.QualityDescriptor;

/**
 * Simple sanity tests for the {@link QualityDescriptor} helper.
 *
 * <p>This test harness does not rely on JUnit and instead prints results to stdout.
 */
public final class QualityDescriptorTest {
    public static void main(String[] args) {
        int passed = 0;
        int total = 5;
        passed += check(95, "\uD83D\uDE04");
        passed += check(80, "\uD83D\uDE42");
        passed += check(60, "\uD83D\uDE10");
        passed += check(40, "\uD83D\uDE15");
        passed += check(10, "\uD83D\uDE1F");
        System.out.println("QualityDescriptor tests: " + passed + "/" + total + " passed");
    }

    private static int check(int quality, String expectedEmoji) {
        String[] res = QualityDescriptor.describe(quality);
        if (!res[0].equals(expectedEmoji)) {
            System.out.println("Failed for quality " + quality + ": expected " + expectedEmoji + ", got " + res[0]);
            return 0;
        }
        return 1;
    }
}