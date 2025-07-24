# GitCodeQuality

GitCodeQuality is a desktop helper for developers striving to craft small, well‑defined Git commits. It wraps the Git CLI with a minimal JavaFX front‑end and provides real‑time feedback on the size of your staged changes. By nudging you to keep commits focused, reviewers can more easily understand your work.

## Features

- **Repository Picker:** On launch you are prompted to choose a local Git repository. The most recently opened paths are stored under `~/.gitgui/recent_repos.json`.
- **Commit Quality Meter:** A banner in the main window shows an emoji, percentage and message describing the quality of your staged changes. Quality is computed using the formula described in the project specification【61889244827154†L18-L30】.
- **Commit Button:** Stage all modified files, commit them using your message, then recompute quality. Exception keywords (`refactor:`, `bulk rename`, `initial commit`) bypass the scoring and award a perfect rating.
- **History Viewer:** Click the “Advanced” button to view the last 50 commits in the current repository.

## Usage

To run the application you need a Java 17 runtime and JavaFX modules. Compile the sources under `src/main/java` and launch `com.voidtoverse.Main`. When prompted, select a Git repository. Make changes, stage and commit them via the GUI. The quality banner will update after each commit.

> **Note:** This project uses the system `git` executable to compute diffs and make commits. Ensure `git` is available on your PATH and that your name and email are configured (e.g. via `git config --global user.name`).

## Structure

```
GitCodeQuality/
├── src/main/java/           # Application code
├── src/test/java/           # Test harnesses
├── docs/                    # Specifications and architecture
├── logs/                    # Sprint logs
├── metrics/                 # Quality and test metrics
├── bugs/                    # Bug reports (none yet)
└── target/                  # Build outputs
```

See `docs/project_specification.md` for the full roadmap and feature list.