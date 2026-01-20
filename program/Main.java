package program;

import ui.ToDoListApp;

public class Main {
    public static void main(String[] args) {
         // Menjalankan GUI di Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Membuat instance aplikasi To-Do List
            new ToDoListApp().setVisible(true);
        });
    }
}
