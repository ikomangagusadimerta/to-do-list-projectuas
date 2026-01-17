package controller;

import database.TaskDatabase;
import model.Task;
import java.util.List;

public class TaskController {
    private final TaskDatabase db = new TaskDatabase();

    public List<Task> getAll() {
        return db.findAll();
    }

    public void create(String title) {
        Task t = new Task(0, title, false);
        db.insert(t);
    }

    public void update(Task t) {
        db.update(t);
    }

    public void delete(int id) {
        db.delete(id);
    }
}
