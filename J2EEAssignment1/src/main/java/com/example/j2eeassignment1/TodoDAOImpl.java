package com.example.j2eeassignment1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementation class
public class TodoDAOImpl implements TodoDAO {
    private final Connection conn;

    // Get the connection
    public TodoDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    // Add this class by inserting the three values, title, description, status
    @Override
    public void addTodo(Todo todo) {
        String sql = "INSERT INTO todos (title, description, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, todo.title());
            ps.setString(2, todo.description());
            ps.setString(3, todo.status());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all the elements using the classic SELECT * FROM statement
    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                todos.add(new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    // Update sql by finding by ID with where
    @Override
    public void updateTodo(Todo todo) {
        String sql = "UPDATE todos SET title = ?, description = ?, status = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, todo.title());
            ps.setString(2, todo.description());
            ps.setString(3, todo.status());
            ps.setInt(4, todo.id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an element by ID, once again using WHERE
    @Override
    public void deleteTodo(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
