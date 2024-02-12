package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getAgeStudent(int age) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        return studentRepository.getReferenceById(id).getFaculty();
    }

    public Integer getAllByInSchool() {
        return studentRepository.getAllByInSchool();
    }

    public Integer getAverageAgeStudent() {
        return studentRepository.getAverageAgeStudent();
    }

    public List<Student> getLatestFiveStudent() {
        return studentRepository.getLatestFiveStudent();
    }
}
