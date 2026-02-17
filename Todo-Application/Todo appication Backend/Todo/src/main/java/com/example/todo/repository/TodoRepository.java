package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCompleted(boolean completed);

}
