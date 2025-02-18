package com.example.j2eeassignment1;

// This is my first time using a record, an immutable data classes that only need type and fields. Think of it as a built-in Java version of Lombok for this use case.
public record Todo(int id, String title, String description, String status) {
}

