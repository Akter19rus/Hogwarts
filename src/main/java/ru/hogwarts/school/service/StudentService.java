package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public interface StudentService {
    public Student createStudent(Student student);

    public Student findStudent(long id);

    public Student editStudent(Student student);

    public Student deleteStudent(long id);

    public Collection<Student> getAllStudents();

    public Collection<Student> getAgeStudent(int age);
}
