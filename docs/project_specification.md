# Git GUI with Commit Quality Insight — Project Specification

## 🧭 Objective

Build a JavaFX-based Git GUI front-end using JGit that enhances normal Git operations with commit quality insights. It should guide developers to create clean, atomic, well-documented commits.

---

## 🏗️ Application FeaturesSetup JavaFX, JGit, config files

### 🔹 Core Git Features

- Open and monitor local Git repositories
- Git Add, Commit, Push, Pull
- Show history and logs
- Diff viewer for file changes

### 🔹 Commit Quality Engine

- Quality score = `lines_added + lines_removed + (files_changed * 5)`
- Scores mapped to emojis and messages:

| Score  | Emoji | Message                                          |
| ------ | ----- | ------------------------------------------------ |
| 90–100 | 😄    | Perfect size. Just like grandma used to commit.  |
| 70–89  | 🙂    | Solid job! Clean and focused.                    |
| 50–69  | 😐    | Bit chunky. Can you split this next time?        |
| 30–49  | 😕    | Too many changes at once. Risky to review.       |
| <30    | 😟    | This commit needs a timeout. Consider splitting. |

- Exceptions allowed for commit messages with:
  - `refactor:`
  - `bulk rename`
  - `initial commit`

### 🔹 Mini UI (Default View)

- Shows quality (emoji + % + message)
- Commit message box
- Commit button
- Toggle to full UI

### 🔹 Full UI

- File staging area: unstaged / staged views
- Diff viewer (side-by-side)
- Commit message box
- Commit quality panel
- Commit log/history
- Toggle to mini UI

### 🔹 Project Dashboard

- Shows cards for multiple open projects
- Quality summary per repo

### 🔹 Profiles

- Per-project profile (`.gitgui_config.json`)
- User-level profile (`~/.gitgui/user-settings.json`)
  - Includes quality scoring preferences
  - UI display themes (light/dark)
  - Font sizes, colors for diff view

### 🔹 Watcher + Auto Update

- File watcher monitors all changes in working tree (recursive)
- Ignores paths via `.gitignore`
- Updates score live when files change

### 🔹 Bug Tracking

- `bugs/bug-XXX.md` for each known issue
- Contains repro steps, status, attempted fixes
- Bug status updated per sprint automatically

### 🔹 Output Packaging

- JAR + `run.sh` / `run.bat`
- ZIP placed in `target/`
- Fully portable project with README and docs

---

## 📦 Sprint Plan

### ✅ Sprint 0: Bootstrap

- Create project structure and Git repo
- Setup JavaFX, JGit, config files
- `README.md`, `docs/architecture.md`

### ✅ Sprint 1: Repo Picker + History

- UI to open a repo
- Save recent list
- Create persistent storage for it

### ✅ Sprint 2: Mini UI + Quality Engine

- Score calculator logic
- Smiley + % + message
- Commit message field + commit button
- Toggle to full UI

### ✅ Sprint 3: Full UI Layout

- File staging (unstaged / staged)
- Diff viewer
- Commit history view
- Live quality integration

### ✅ Sprint 4: Profile Configs

- Read/write `.gitgui_config.json`
- Theme preferences
- User settings in `~/.gitgui/`

### ✅ Sprint 5: Quality Dashboard

- Shows all open projects
- List with project name + current quality
- Minimalist panel

### ✅ Sprint 6: File Watcher

- Recursive file change watcher (Java NIO)
- Apply `.gitignore`
- Trigger score recomputation

### ✅ Sprint 7: Bug + Log System

- `bugs/` folder with Markdown issue files
- `logs/` folder with per-sprint summary
- Logging integrated with commit process

### ✅ Sprint 8: Commit Profiles + Badges

- Store per-user stats in `~/.gitgui/`
- Award badges for good streaks, improvements

### ✅ Sprint 9: Display Theme Editor

- UI to configure color schemes, fonts
- Save and load display profiles

### ✅ Sprint 10: Build & Final Test

- Create ZIP package with runnable JAR
- Run through fake Git repo and simulate test flow
- Final `final-summary.md` with known bugs

---

## 📄 Additional Expectations

### 🧪 Testing

- Tests for scoring, config loading, file watching
- Store results in `metrics/test-results.json`

### 📚 Docs

- `docs/architecture.md`: UI + module breakdown
- `docs/ui-flows.md`: UI navigation
- `docs/design-decisions.md`: Why + how
- `metrics/project-quality.json`: All scoring data

---

## 🧾 Final Deliverables

- Fully working JavaFX + JGit Git client
- Commit scoring engine and visual indicator
- Profiles, settings, logs, bugs, metrics
- Git commit history reflecting sprints and decisions
- A developer should be able to pick this up and continue without questions.

---

End of specification.

