package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskForm extends JDialog {
    private final JTextField titleField = new JTextField(25);
    private final JCheckBox doneCheck = new JCheckBox("Selesai");
    private boolean confirmed = false;

    public TaskForm(Frame owner, String title, Task existing) {
        super(owner, title, true);
        setLayout(new GridLayout(3, 2, 8, 8));

        add(new JLabel("Judul Tugas:"));
        add(titleField);
        add(new JLabel("Status:"));
        add(doneCheck);

        JButton ok = new JButton("Simpan");
        JButton cancel = new JButton("Batal");
        add(ok); add(cancel);

        if (existing != null) {
            titleField.setText(existing.getTitle());
            doneCheck.setSelected(existing.isDone());
        }

        ok.addActionListener(e -> {
            if (titleField.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Judul tidak boleh kosong.");
                return;
            }
            confirmed = true;
            dispose();
        });
        cancel.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isConfirmed() { return confirmed; }

    public Task toTask(Integer id) {
        Task t = new Task(id, titleField.getText().trim(), doneCheck.isSelected());
        return t;
    }
}
