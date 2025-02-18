<%@ page import="java.util.*, com.example.j2eeassignment1.Todo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo List</title>
    <!--Using bootstrap so I don't have to do styles myself-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h2 class="mb-0"><%= (request.getParameter("id") != null) ? "Edit Todo" : "Add Todo" %></h2>
        </div>
        <div class="card-body">
            <!--For editable todos-->
            <%
                Todo todoToEdit = null;
                if (request.getParameter("id") != null) {
                    int editId = Integer.parseInt(request.getParameter("id"));
                    List<Todo> todoList = (List<Todo>) request.getAttribute("todos");
                    if (todoList != null) {
                        for (Todo t : todoList) {
                            if (t.id() == editId) {
                                todoToEdit = t;
                                break;
                            }
                        }
                    }
                }
            %>

            <!-- Use todos to edit to determine if we'll use add or update action -->
            <form action="todo" method="post">
                <% if (todoToEdit != null) { %>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= todoToEdit.id() %>">
                <% } else { %>
                <input type="hidden" name="action" value="add">
                <% } %>

                <!--Fill in information/leave it empty depending on if the todoToEdit is not null or not-->
                <div class="mb-3">
                    <label class="form-label">Title</label>
                    <input type="text" class="form-control" name="title" value="<%= (todoToEdit != null) ? todoToEdit.title() : "" %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <input type="text" class="form-control" name="description" value="<%= (todoToEdit != null) ? todoToEdit.description() : "" %>">
                </div>

                <div class="mb-3">
                    <label class="form-label">Status</label>
                    <select class="form-select" name="status">
                        <option value="Pending" <%= (todoToEdit != null && "Pending".equals(todoToEdit.status())) ? "selected" : "" %>>Pending</option>
                        <option value="Completed" <%= (todoToEdit != null && "Completed".equals(todoToEdit.status())) ? "selected" : "" %>>Completed</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-success"><%= (todoToEdit != null) ? "Update Todo" : "Add Todo" %></button>
            </form>
        </div>
    </div>

    <div class="mt-4 card shadow-lg">
        <div class="card-header bg-secondary text-white">
            <h3 class="mb-0">Tasks</h3>
        </div>
        <div class="card-body">
            <!--Fill in table with info-->
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Todo> todos = (List<Todo>) request.getAttribute("todos");
                    if (todos != null) {
                        for (Todo t : todos) {
                %>
                <tr>
                    <td><%= t.id() %></td>
                    <td><%= t.title() %></td>
                    <td><%= t.description() %></td>
                    <td>
                            <span class="badge bg-<%= t.status().equals("Completed") ? "success" : "warning" %>">
                                <%= t.status() %>
                            </span>
                    </td>
                    <td>
                        <form action="todo" method="post" class="d-inline">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= t.id() %>">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>

                        <form action="todo" method="get" class="d-inline">
                            <input type="hidden" name="id" value="<%= t.id() %>">
                            <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                        </form>
                    </td>
                </tr>
                <% }} %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
