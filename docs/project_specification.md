# Git GUI with Commit Quality Insight — Project Specification

## 🧭 Objective

Build a JavaFX-based Git GUI front-end using JGit that enhances normal Git operations with commit quality insights. It should guide developers to create clean, atomic, well‑documented commits【61889244827154†L0-L6】.

---

## 🏗️ Application Features

The project will be based on JavaFX and JGit

### 🔹 Core Git Features

- Open and monitor local Git repositories【61889244827154†L13-L16】
- Git Add, Commit, Push, Pull【61889244827154†L13-L17】
- Show history and logs【61889244827154†L13-L17】
- Diff viewer for file changes【61889244827154†L13-L17】

### 🔹 Commit Quality Engine

- Quality score = `lines_added + lines_removed + (files_changed * 5)`【61889244827154†L18-L23】
- Scores mapped to emojis and messages【61889244827154†L20-L29】:

| Score  | Emoji | Message                                          |
|--------|-------|--------------------------------------------------|
| 90–100 | 😄    | Perfect size. Just like grandma used to commit.  |
| 70–89  | 🙂    | Solid job! Clean and focused.                    |
| 50–69  | 😐    | Bit chunky. Can you split this next time?        |
| 30–49  | 😕    | Too many changes at once. Risky to review.       |
| <30    | 😟    | This commit needs a timeout. Consider splitting. |

- Exceptions allowed for commit messages with:
  - `refactor:`【61889244827154†L31-L34】
  - `bulk rename`【61889244827154†L31-L34】
  - `initial commit`【61889244827154†L31-L35】

### 🔹 Mini UI (Default View)

- Shows quality (emoji + % + message)【61889244827154†L36-L40】
- Commit message box【61889244827154†L36-L41】
- Commit button【61889244827154†L36-L41】
- Toggle to full UI【61889244827154†L40-L43】

### 🔹 Full UI

- File staging area: unstaged / staged views【61889244827154†L44-L49】
- Diff viewer (side‑by‑side)【61889244827154†L44-L49】
- Commit message box【61889244827154†L44-L49】
- Commit quality panel【61889244827154†L44-L49】
- Commit log/history【61889244827154†L44-L49】
- Toggle to mini UI【61889244827154†L49-L50】

### 🔹 Project Dashboard

- Shows cards for multiple open projects【61889244827154†L52-L56】
- Quality summary per repo【61889244827154†L52-L56】

### 🔹 Profiles

- Per‑project profile (`.gitgui_config.json`)【61889244827154†L57-L61】
- User‑level profile (`~/.gitgui/user-settings.json`)【61889244827154†L58-L61】
  - Includes quality scoring preferences【61889244827154†L61-L63】
  - UI display themes (light/dark)【61889244827154†L58-L63】
  - Font sizes, colors for diff view【61889244827154†L62-L63】

### 🔹 Watcher + Auto Update

- File watcher monitors all changes in working tree (recursive)【61889244827154†L65-L69】
- Ignores paths via `.gitignore`【61889244827154†L65-L69】
- Updates score live when files change【61889244827154†L67-L70】

### 🔹 Bug Tracking

- `bugs/bug-XXX.md` for each known issue【61889244827154†L72-L75】
- Contains repro steps, status, attempted fixes【61889244827154†L72-L75】
- Bug status updated per sprint automatically【61889244827154†L72-L75】

### 🔹 Output Packaging

- JAR + `run.sh` / `run.bat`【61889244827154†L77-L80】
- ZIP placed in `target/`【61889244827154†L78-L80】
- Fully portable project with README and docs【61889244827154†L79-L82】

---

## 📦 Sprint Plan

### ✅ Sprint 0: Bootstrap【61889244827154†L85-L93】

- Create project structure and Git repo【61889244827154†L87-L93】
- Setup JavaFX, JGit, config files【61889244827154†L87-L93】
- `README.md`, `docs/architecture.md`【61889244827154†L87-L93】

### ✅ Sprint 1: Repo Picker + History【61889244827154†L93-L99】

- UI to open a repo【61889244827154†L93-L99】
- Save recent list【61889244827154†L93-L99】
- Create persistent storage for it【61889244827154†L93-L99】

### ✅ Sprint 2: Mini UI + Quality Engine【61889244827154†L99-L106】

- Score calculator logic【61889244827154†L100-L103】
- Smiley + % + message【61889244827154†L100-L104】
- Commit message field + commit button【61889244827154†L100-L104】
- Toggle to full UI【61889244827154†L103-L105】

### ✅ Sprint 3: Full UI Layout【61889244827154†L106-L112】

- File staging (unstaged / staged)【61889244827154†L108-L110】
- Diff viewer【61889244827154†L108-L110】
- Commit history view【61889244827154†L108-L110】
- Live quality integration【61889244827154†L109-L112】

### ✅ Sprint 4: Profile Configs【61889244827154†L113-L118】

- Read/write `.gitgui_config.json`【61889244827154†L114-L116】
- Theme preferences【61889244827154†L114-L117】
- User settings in `~/.gitgui/`【61889244827154†L114-L117】

### ✅ Sprint 5: Quality Dashboard【61889244827154†L119-L124】

- Shows all open projects【61889244827154†L120-L123】
- List with project name + current quality【61889244827154†L120-L123】
- Minimalist panel【61889244827154†L120-L123】

### ✅ Sprint 6: File Watcher【61889244827154†L125-L130】

- Recursive file change watcher (Java NIO)【61889244827154†L126-L128】
- Apply `.gitignore`【61889244827154†L126-L128】
- Trigger score recomputation【61889244827154†L127-L129】

### ✅ Sprint 7: Bug + Log System【61889244827154†L131-L135】

- `bugs/` folder with Markdown issue files【61889244827154†L132-L135】
- `logs/` folder with per-sprint summary【61889244827154†L132-L135】
- Logging integrated with commit process【61889244827154†L132-L135】

### ✅ Sprint 8: Commit Profiles + Badges【61889244827154†L136-L142】

- Store per-user stats in `~/.gitgui/`【61889244827154†L139-L141】
- Award badges for good streaks, improvements【61889244827154†L139-L141】

### ✅ Sprint 9: Display Theme Editor【61889244827154†L142-L146】

- UI to configure color schemes, fonts【61889244827154†L142-L145】
- Save and load display profiles【61889244827154†L142-L145】

### ✅ Sprint 10: Build & Final Test【61889244827154†L147-L151】

- Create ZIP package with runnable JAR【61889244827154†L147-L149】
- Run through fake Git repo and simulate test flow【61889244827154†L147-L151】
- Final `final-summary.md` with known bugs【61889244827154†L147-L151】

---

## 📄 Additional Expectations

### 🧪 Testing【61889244827154†L155-L160】

- Tests for scoring, config loading, file watching【61889244827154†L157-L160】
- Store results in `metrics/test-results.json`【61889244827154†L157-L160】

### 📚 Docs【61889244827154†L162-L169】

- `docs/architecture.md`: UI + module breakdown【61889244827154†L163-L165】
- `docs/ui-flows.md`: UI navigation【61889244827154†L162-L166】
- `docs/design-decisions.md`: Why + how【61889244827154†L162-L166】
- `metrics/project-quality.json`: All scoring data【61889244827154†L166-L168】

---

## 🧾 Final Deliverables【61889244827154†L171-L177】

- Fully working JavaFX + JGit Git client【61889244827154†L173-L176】
- Commit scoring engine and visual indicator【61889244827154†L173-L176】
- Profiles, settings, logs, bugs, metrics【61889244827154†L173-L176】
- Git commit history reflecting sprints and decisions【61889244827154†L173-L177】
- A developer should be able to pick this up and continue without questions【61889244827154†L176-L177】.

---

End of specification.

