package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskRepository taskRepository;


    public Task setCategory(Task task, List<Integer> categoryId) {
        Set<Category> cat = new HashSet<>();
        for (Integer id : categoryId) {
            Category category = new Category();
            category.setId(id);
            cat.add(category);
        }
        task.setCategories(cat);
        return task;
    }

    @Override
    public Task save(Task task, List<Integer> categories) {
        return taskRepository.save(setCategory(task, categories));
    }

    @Override
    public boolean deleteById(int id) {
        return taskRepository.deleteById(id);
    }

    @Override
    public boolean update(Task task, List<Integer> categories) {
        return taskRepository.update(setCategory(task, categories));
    }

    @Override
    public boolean execute(Task task) {
        return taskRepository.execute(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Collection<Task> findNewOrDone(boolean done) {
        return taskRepository.findNewOrDone(done);
    }
}
