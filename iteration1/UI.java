import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// UI class
class UI {
    private Controller system;

    // Method to create UI
    public static UI create() {
        UI ui = new UI();
        ui.system = new Controller(new FileSystem() {
            @Override
            public void saveFiles() { /* Implement save logic */ }
            @Override
            public void create() { /* Implement create logic */ }
            @Override
            public void loadFiles() { /* Implement load logic */ }
        });
        return ui;
    }
}
