# Developer Behavior Specification for All Agent‑Led Projects

This document outlines the expected behavior, practices, and work discipline for any autonomous developer (agent) participating in a software project【615593083309513†L0-L4】.

## 🧑‍💻 General Behavior

1. **Work as a developer would** — commit code incrementally, test it, document it, and leave traceable logs【615593083309513†L5-L11】.
2. **Never skip documentation** — if you write a line of code, you write a line of explanation【615593083309513†L8-L9】.
3. **Don’t stop on errors** — record the bug, try to fix it, and continue with the next sprint【615593083309513†L9-L10】.
4. **All actions should be committed to Git** — this includes logs, metrics, configs, and test results【615593083309513†L10-L11】.
5. **You must work as if handing this project off to another developer**【615593083309513†L11-L12】.

---

## 📦 Code Standards

- Limit logic per commit to **20 lines or fewer**【615593083309513†L15-L19】.
- Use meaningful commit messages (`feat:`, `fix:`, `docs:`, `test:`)【615593083309513†L17-L19】.
- Write **1 line of documentation for every line of logic**, unless trivial【615593083309513†L17-L20】.
- Document each external API (e.g., JGit) **once** thoroughly, and link to that comment in other places【615593083309513†L19-L20】.

---

## 📂 Project Folder Expectations【615593083309513†L24-L41】

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
├── .gitgui_config.json            # Project‑specific settings
```

Use `~/.gitgui/` for user‑specific settings like themes and badge progress【615593083309513†L39-L41】.

---

## 🧪 Testing & Bug Handling【615593083309513†L44-L55】

- For every new module, write at least one test【615593083309513†L46-L47】.
- On any failed test or runtime exception:
  - Create a file in `/bugs/` (e.g., `bug‑002‑npe‑commit.md`)【615593083309513†L48-L54】
  - Include:
    - Status: open/in-progress/fixed【615593083309513†L48-L53】
    - What failed【615593083309513†L50-L53】
    - Repro steps【615593083309513†L50-L53】
    - Fix attempts so far【615593083309513†L50-L53】
  - Continue to next sprint even if bug not resolved【615593083309513†L53-L55】.

---

## 🧠 Documentation Expectations【615593083309513†L58-L70】

These must always be present and updated:

| File                           | Purpose                      |
| ------------------------------ | ---------------------------- |
| `README.md`                    | How to use the app           |
| `docs/architecture.md`         | Modules, UI flows, services  |
| `docs/design-decisions.md`     | Why + how                    |
| `logs/sprint-#.md`             | What was done in that sprint |
| `metrics/project-quality.json` | Summary of scores            |
| `metrics/test-results.json`    | Test output per sprint       |
| `bugs/bug-###.md`              | Per‑issue reports            |

Also include `final-summary.md` at the end, summarizing work done and remaining【615593083309513†L70-L72】.

---

## 🧾 What You Always Commit【615593083309513†L76-L87】

Each commit should include:

- Source code and config【615593083309513†L80-L82】
- New/updated docs (if applicable)【615593083309513†L80-L82】
- Any test results or metrics【615593083309513†L80-L83】
- Logs for that sprint【615593083309513†L80-L83】
- Bug files if created【615593083309513†L80-L83】

No step should happen invisibly. Every decision must be **documented and traceable in Git**【615593083309513†L84-L86】.

---

## ✅ Summary【615593083309513†L90-L101】

This file defines how you work — not what you're building. Combine it with a project‑specific spec file for each new project【615593083309513†L91-L93】.

- Work in small, testable steps【615593083309513†L95-L97】
- Document everything as you go【615593083309513†L95-L97】
- Keep a changelog through Git【615593083309513†L95-L97】
- Leave behind a fully understandable trail【615593083309513†L96-L98】

Your work should feel like reading a good story in Git history【615593083309513†L90-L101】.