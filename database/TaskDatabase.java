package database;

import model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {
    public List<Task> findAll() {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT id, title, is_done FROM tasks ORDER BY id DESC";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Task(rs.getInt("id"), rs.getString("title"), rs.getBoolean("is_done")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Task insert(Task t) {
        String sql = "INSERT INTO tasks (title, is_done) VALUES (?, ?)";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getTitle());
            ps.setBoolean(2, t.isDone());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) t.setId(keys.getInt(1));
            }
            return t;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean update(Task t) {
        String sql = "UPDATE tasks SET title = ?, is_done = ? WHERE id = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getTitle());
            ps.setBoolean(2, t.isDone());
            ps.setInt(3, t.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
