package com.voidtoverse.ui;

import com.voidtoverse.persistence.Persistence;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a simple repository picker using a {@link DirectoryChooser}.
 *
 * <p>This initial implementation displays the native directory chooser to let
 * the user select a Git repository folder. Once a directory is selected, the
 * path is added to the recent list stored in {@code ~/.gitgui/recent_repos.json}
 * via {@link Persistence}. Duplicate entries are removed and the list is
 * limited to a maximum of five paths.</p>
 */
public final class RepoPicker {
    private RepoPicker() {}

    /**
     * Show a system directory chooser and return the selected repository.
     *
     * @param owner the parent window for modality
     * @return the chosen repository folder, or {@code null} if cancelled
     */
    public static File chooseRepository(Stage owner) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Git Repository");
        File dir = chooser.showDialog(owner);
        if (dir != null) {
            List<String> recents = Persistence.loadRecentRepositories();
            List<String> updated = new ArrayList<>();
            String path = dir.getAbsolutePath();
            updated.add(path);
            for (String existing : recents) {
                if (!existing.equals(path) && updated.size() < 5) {
                    updated.add(existing);
                }
            }
            Persistence.saveRecentRepositories(updated);
        }
        return dir;
    }
}