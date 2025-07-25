# GitCodeQuality

GitCodeQuality is a desktop helper for developers striving to craft small, well‑defined Git commits. It wraps the Git CLI with a minimal JavaFX front‑end and provides real‑time feedback on the size of your staged changes. By nudging you to keep commits focused, reviewers can more easily understand your work.

## Features

- **Repository Picker:** The application remembers the last repository you worked in and restores it on launch. If no repository has been selected yet, the main window displays “No git repository selected”. Use the **Switch** button to select from a list of recent repositories or browse to a new one. Recent paths are stored under `~/.gitgui/recent_repos.json` and the last selection is persisted in `~/.gitgui/user-settings.json`.

- **Commit Quality Meter:** A banner in the main window shows an emoji, percentage and message describing the quality of your staged changes. Quality is computed using the formula described in the project specification【61889244827154†L18-L30】.

- **Commit Button:** Stage all modified files, commit them using your message, then recompute quality. Exception keywords (`refactor:`, `bulk rename`, `initial commit`) bypass the scoring and award a perfect rating.

- **History Viewer:** Click the **Advanced** button to view the last 50 commits in the current repository.

- **JGit Integration:** Internally the application uses the [JGit](https://www.eclipse.org/jgit/) library for Git operations such as staging, committing, diffing and retrieving history. No external `git` binary is required at runtime.

## Usage

To run the application you need a Java 17 runtime, JavaFX modules and the JGit library on the classpath. Compile the sources under `src/main/java` and launch `com.voidtoverse.Main`. On first launch you will see “No git repository selected”. Click the **Switch** button to choose a repository. Once a repository is selected, make changes, stage and commit them via the GUI. The quality banner will update after each commit.

You can manage your Git user name and email via the standard `git config` commands. JGit honours your existing `.gitconfig` settings.

### Adding JGit to your build

GitCodeQuality depends on the JGit library to interact with repositories. If you are using Maven, include the following dependency:

```xml
<dependency>
  <groupId>org.eclipse.jgit</groupId>
  <artifactId>org.eclipse.jgit</artifactId>
  <version>6.8.0.20231129</version>
</dependency>
```

For Gradle, add:

```groovy
implementation 'org.eclipse.jgit:org.eclipse.jgit:6.8.0.20231129'
```

Alternatively, download the JGit jar from the Eclipse update site and add it to your project's classpath.

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