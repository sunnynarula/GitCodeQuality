# Architecture Overview

This document describes the planned modules for the GitCodeQuality application. The
project is a JavaFX desktop client that wraps JGit to provide a commit quality
helper. Sprints follow `docs/project_specification.md`【260669340579302†L0-L21】.

## Modules

- **UI Launcher** — starts the JavaFX application and shows the first window【260669340579302†L0-L21】.
- **Repository Picker** — allows the user to select a local Git repo and stores recently used paths【260669340579302†L8-L18】.
- **History Viewer** — displays commit history once a repo is loaded【260669340579302†L8-L18】.
- **Persistence** — writes the recent repo list to `~/.gitgui/` as JSON【260669340579302†L10-L11】.
- **Quality Engine** — calculates commit size scores. Not active in Sprint 1 but planned【260669340579302†L10-L12】.

## Flow

1. The main class invokes JavaFX【260669340579302†L14-L21】.
2. The picker lists recent repositories from storage【260669340579302†L14-L21】.
3. Choosing a repository loads the history view【260669340579302†L14-L21】.

Sprint 1 uses a placeholder mini UI displaying commit quality, a text area for
messages and three buttons (Commit, Advanced, Switch). The window layout lives
in `CommitQualityFrame` and is invoked from the main class【260669340579302†L19-L22】. Additional modules such
as staging and scoring will be added in later sprints【260669340579302†L19-L22】.