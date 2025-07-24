package com.voidtoverse.engine;

/**
 * Maps a quality percentage into an emoji and human‑readable message.
 *
 * <p>The ranges and associated messages are defined in the project
 * specification【61889244827154†L23-L30】. The quality percentage should already be clamped between
 * 0 and 100.</p>
 */
public final class QualityDescriptor {
    private QualityDescriptor() {}

    /**
     * Determine the emoji and message for a given quality score.
     *
     * @param quality a value between 0 and 100
     * @return a two‑element array where index 0 is an emoji and index 1 is a message
     */
    public static String[] describe(int quality) {
        if (quality >= 90) {
            return new String[] {"\uD83D\uDE04", "Perfect size. Just like grandma used to commit."};
        }
        if (quality >= 70) {
            return new String[] {"\uD83D\uDE42", "Solid job! Clean and focused."};
        }
        if (quality >= 50) {
            return new String[] {"\uD83D\uDE10", "Bit chunky. Can you split this next time?"};
        }
        if (quality >= 30) {
            return new String[] {"\uD83D\uDE15", "Too many changes at once. Risky to review."};
        }
        return new String[] {"\uD83D\uDE1F", "This commit needs a timeout. Consider splitting."};
    }
}