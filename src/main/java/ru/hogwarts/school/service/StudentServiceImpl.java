package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    Map<Long, Student> studentMap = new HashMap<>();
}
