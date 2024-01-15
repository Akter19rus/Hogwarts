package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentAlreadyAddedException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {
    Map<Long, Student> studentMap = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        studentMap.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return studentMap.remove(id);
    }

    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }

    public Collection<Student> getAgeStudent(int age) {
        return getAllStudents().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }
}
