# Developer Behavior Specification for All Agent-Led Projects

This document outlines the expected behavior, practices, and work discipline for any autonomous developer (agent) participating in a software project.

## 🧑‍💻 General Behavior

1. **Work as a developer would** — commit code incrementally, test it, document it, and leave traceable logs.
2. **Never skip documentation** — if you write a line of code, you write a line of explanation.
3. **Don’t stop on errors** — record the bug, try to fix it, and continue with the next sprint.
4. **All actions should be committed to Git** — this includes logs, metrics, configs, and test results.
5. **You must work as if handing this project off to another developer.**

---

## 📦 Code Standards

- Limit logic per commit to **20 lines or fewer**.
- Use meaningful commit messages (`feat:`, `fix:`, `docs:`, `test:`).
- Write **1 line of documentation for every line of logic**, unless trivial.
- Document each external API (e.g., JGit) **once** thoroughly, and link to that comment in other places.

---

## 📂 Project Folder Expectations

The following structure is required:

```
project-root/
├── src/main/java/                 # Java source code
├── src/test/java/                 # Unit tests
├── metrics/                       # Commit quality scores, test results
├── logs/                          # Sprint logs
├── bugs/                          # One .md file per unresolved issue
├── docs/                          # Architecture, UI design, etc.
├── target/                        # Output ZIP & JAR
├── .gitgui_config.json            # Project-specific settings
```

Use `~/.gitgui/` for user-specific settings like themes and badge progress.

---

## 🧪 Testing & Bug Handling

- For every new module, write at least one test.
- On any failed test or runtime exception:
  - Create a file in `/bugs/` (e.g., `bug-002-npe-commit.md`)
  - Include:
    - Status: open/in-progress/fixed
    - What failed
    - Repro steps
    - Fix attempts so far
- Continue to next sprint even if bug not resolved.

---

## 🧠 Documentation Expectations

These must always be present and updated:

| File                           | Purpose                      |
| ------------------------------ | ---------------------------- |
| `README.md`                    | How to use the app           |
| `docs/architecture.md`         | Modules, UI flows, services  |
| `docs/design-decisions.md`     | Why choices were made        |
| `logs/sprint-#.md`             | What was done in that sprint |
| `metrics/project-quality.json` | Summary of scores            |
| `metrics/test-results.json`    | Test output per sprint       |
| `bugs/bug-###.md`              | Per-issue reports            |

Also include `final-summary.md` at the end, summarizing work done and remaining.

---

## 🧾 What You Always Commit

Each commit should include:

- Source code and config
- New/updated docs (if applicable)
- Any test results or metrics
- Logs for that sprint
- Bug files if created

No step should happen invisibly. Every decision must be **documented and traceable in Git**.

---

## ✅ Summary

This file defines how you work — not what you're building. Combine it with a project-specific spec file for each new project.

- Work in small, testable steps
- Document everything as you go
- Keep a changelog through Git
- Leave behind a fully understandable trail

Your work should feel like reading a good story in Git history.

