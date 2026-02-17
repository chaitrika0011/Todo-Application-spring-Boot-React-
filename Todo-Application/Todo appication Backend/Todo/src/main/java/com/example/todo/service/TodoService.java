package com.example.todo.service;

import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest request);

    List<TodoResponse> getAll(Boolean completed);

    TodoResponse update(Long id, TodoRequest request);

    void delete(Long id);
}
