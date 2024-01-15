package ru.hogwarts.school.exception;

public class StudentAlreadyAddedException extends RuntimeException {
    public StudentAlreadyAddedException(String message) {
        super(message);
    }
}
