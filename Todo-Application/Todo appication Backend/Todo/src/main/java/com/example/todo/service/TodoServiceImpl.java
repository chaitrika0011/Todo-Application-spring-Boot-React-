package com.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.Todo;
import com.example.todo.dto.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Override
    public TodoResponse create(TodoRequest request) {

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(todo);

        return mapToResponse(todo);
    }

    @Override
    public List<TodoResponse> getAll(Boolean completed) {

        List<Todo> todos = completed == null
                ? repository.findAll()
                : repository.findByCompleted(completed);

        return todos.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TodoResponse update(Long id, TodoRequest request) {

        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (request.getTitle() != null)
            todo.setTitle(request.getTitle());

        if (request.getDescription() != null)
            todo.setDescription(request.getDescription());

        if (request.getCompleted() != null)
            todo.setCompleted(request.getCompleted());

        repository.save(todo);

        return mapToResponse(todo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TodoResponse mapToResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
