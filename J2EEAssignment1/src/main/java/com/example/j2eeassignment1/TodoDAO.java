package com.example.j2eeassignment1;

import java.util.List;

// Basic interface for more oopy logic
public interface TodoDAO {
    void addTodo(Todo todo);
    List<Todo> getAllTodos();
    void updateTodo(Todo todo);
    void deleteTodo(int id);
}
