# Sprint 2 Log

- Added `Persistence` utility to load and save the list of recent repositories to `~/.gitgui/recent_repos.json`. The class stores paths in a minimal JSON array without external dependencies.
- Implemented a simple `RepoPicker` using JavaFX's `DirectoryChooser` to allow users to pick a local Git repository. The selected path is inserted into the recent list and truncated to five entries.
- Modified the `Main` class to prompt for a repository before showing the commit quality window. If the user cancels, the application closes without showing the main UI.
- Committed documentation and code in small increments per the developer behaviour specification.