# Sprint 3 Log

- Added `QualityEngine` with a helper to parse `git diff --shortstat` output and compute a raw change score. A separate method now derives a quality percentage from this score and clamps it between 0 and 100.
- Added `QualityDescriptor` to map quality percentages to emojis and human‑readable messages as defined in the specification【61889244827154†L23-L30】.
- Updated the UI to display the computed quality for the selected repository and refreshed it after each commit. Added an exception check so that commits containing `refactor:`, `bulk rename` or `initial commit` always receive a perfect rating.
- Implemented a `HistoryViewer` accessible via the Advanced button to show the last 50 commits using `git log --oneline`.
- Implemented commit functionality: the Commit button stages all changes (`git add -A`), commits them with the user‑provided message and updates the quality banner.
- Added a basic test harness for the quality descriptor mapping and recorded the results in `metrics/test-results.json`.
- Wrote a comprehensive README explaining usage, features and project structure.