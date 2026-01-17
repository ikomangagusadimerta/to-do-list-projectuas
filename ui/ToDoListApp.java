package ui;

import controller.TaskController;
import model.Task;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ToDoListApp extends JFrame {
    private final TaskController controller = new TaskController();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"ID", "Judul", "Selesai"}, 0
    ) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 2) return Boolean.class; // kolom status pakai checkbox
            return String.class;
        }
        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 1 || col == 2; // judul & status bisa diedit langsung
        }
    };

    private final JTable taskTable = new JTable(tableModel);
    private final JTextField txtTitle = new JTextField(20);

    public ToDoListApp() {
        setTitle("✨ To-Do List dengan Checkbox ✨");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taskTable.setRowHeight(25);
        taskTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnAdd = new JButton("Tambah");
        btnAdd.addActionListener(e -> {
            String title = txtTitle.getText();
            if (!title.isEmpty()) {
                controller.create(title);
                loadTasks();
                txtTitle.setText("");
            }
        });

        JButton btnSaveChanges = new JButton("Simpan Perubahan");
        btnSaveChanges.addActionListener(e -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                int id = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
                String title = tableModel.getValueAt(i, 1).toString();
                boolean done = (Boolean) tableModel.getValueAt(i, 2);
                Task t = new Task(id, title, done);
                controller.update(t); // update DB sesuai checkbox
            }
            loadTasks();
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            int row = taskTable.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                controller.delete(id);
                loadTasks();
            }
        });

        JPanel panelTop = new JPanel();
        panelTop.add(txtTitle);
        panelTop.add(btnAdd);
        panelTop.add(btnSaveChanges);
        panelTop.add(btnDelete);

        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        loadTasks();
    }

    private void loadTasks() {
        tableModel.setRowCount(0);
        List<Task> tasks = controller.getAll();
        for (Task t : tasks) {
            tableModel.addRow(new Object[]{t.getId(), t.getTitle(), t.isDone()});
        }
    }
}
