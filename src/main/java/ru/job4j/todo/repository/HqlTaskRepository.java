package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.*;

@Repository
@AllArgsConstructor
public class HqlTaskRepository implements TaskRepository {

    private final CrudRepository crudRepository;

    @Override
    public Task save(Task task) {
        return crudRepository.run(session -> session.save(task)) ? task : null;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run(
                "DELETE Task  WHERE id = :fId", Map.of("fId", id));
    }

    @Override
    public boolean update(Task task) {
        return crudRepository.run(session -> session.update(task));
    }

    @Override
    public boolean execute(Task task) {
        return crudRepository.run(
                "UPDATE Task SET done = :fDone WHERE id = :fId",
                Map.of("fId", task.getId(), "fDone", !task.isDone()));
    }

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional("from Task t JOIN FETCH t.priority JOIN FETCH t.categories WHERE t.id = :fId", Task.class, Map.of("fId", id));
    }

    @Override
    public Collection<Task> findAll() {
        return crudRepository.query("from Task t JOIN FETCH t.priority", Task.class);
    }

    @Override
    public Collection<Task> findNewOrDone(boolean done) {
        return crudRepository.query("from Task t JOIN FETCH t.priority where done = :fDone", Task.class, Map.of("fDone", done));
    }


}
