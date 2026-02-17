package com.example.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.TodoService;
import com.example.todo.dto.TodoRequest;
import com.example.todo.dto.TodoResponse;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@CrossOrigin
public class TodoController {

    private final TodoService service;

    @PostMapping
    public TodoResponse create(@Valid @RequestBody TodoRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<TodoResponse> getAll(@RequestParam(required = false) Boolean completed) {
        return service.getAll(completed);
    }

    @PatchMapping("/{id}")
    public TodoResponse update(@PathVariable Long id,
                               @RequestBody TodoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
