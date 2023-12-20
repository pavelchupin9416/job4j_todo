package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);

    boolean deleteById(int id);

    boolean update(Task task);

    boolean execute(Task task);

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findNewOrDone(boolean done);
}
