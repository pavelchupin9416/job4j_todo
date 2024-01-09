package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Task task, List<Integer> categories);

    boolean deleteById(int id);

    boolean update(Task task, List<Integer> categories);

    boolean execute(Task task);

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findNewOrDone(boolean done);
}
