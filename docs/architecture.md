# Architecture Overview

This document describes the planned modules for the GitCodeQuality application. The project is a JavaFX desktop client that wraps JGit to provide a commit quality helper. Sprints follow `docs/project_specification.md`.

## Modules

- **UI Launcher** — starts the JavaFX application and shows the first window.
- **Repository Picker** — allows the user to select a local Git repo and stores recently used paths.
- **History Viewer** — displays commit history once a repo is loaded.
- **Persistence** — writes the recent repo list to `~/.gitgui/` as JSON.
- **Quality Engine** — calculates commit size scores. Not active in Sprint 1 but planned.

## Flow

1. The main class invokes JavaFX.
2. The picker lists recent repositories from storage.
3. Choosing a repository loads the history view.

Additional modules such as staging and scoring will be added in later sprints.
