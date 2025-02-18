package com.example.j2eeassignment1;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Use do get and do post methods + action to determine what happens
@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private TodoDAO todoDAO;

    @Override
    public void init() {
        todoDAO = new TodoDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Todo> todos = todoDAO.getAllTodos();
        request.setAttribute("todos", todos);
        request.getRequestDispatcher("todo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            Todo newTodo = new Todo(0, title, description, status);
            todoDAO.addTodo(newTodo);
        }
        else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String status = request.getParameter("status");

            Todo updatedTodo = new Todo(id, title, description, status);
            todoDAO.updateTodo(updatedTodo);
        }
        else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            todoDAO.deleteTodo(id);
        }

        response.sendRedirect("todo");
    }
}