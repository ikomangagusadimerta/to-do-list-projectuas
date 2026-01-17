package ui;

import model.Task;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private final String[] cols = {"ID", "Judul", "Selesai"};
    private List<Task> data;

    public TaskTableModel(List<Task> data) { this.data = data; }
    public void setData(List<Task> data) { this.data = data; fireTableDataChanged(); }

    @Override public int getRowCount() { return data == null ? 0 : data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int col) { return cols[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Task t = data.get(row);
        return switch (col) {
            case 0 -> t.getId();
            case 1 -> t.getTitle();
            case 2 -> t.isDone() ? "Ya" : "Belum";
            default -> "";
        };
    }

    public Task getAt(int row) { return data.get(row); }
}
