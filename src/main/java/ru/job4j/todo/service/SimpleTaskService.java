package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskRepository taskRepository;

    private final CategoryService categoryService;

    public Task setCategory(Task task, List<Integer> categoryId){
        List<Category> cat = new ArrayList<>();
        for (Integer id : categoryId) {

            cat.add(categoryService.findById(id).get());
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
