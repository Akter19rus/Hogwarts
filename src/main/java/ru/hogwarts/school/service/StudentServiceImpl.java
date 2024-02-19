package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all student");
        return studentRepository.findAll();
    }

    public Collection<Student> getAgeStudent(int age) {
        logger.info("Was invoked method for get students for age");
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find student by age beetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        logger.info("Was invoked method for find faculty by student");
        return studentRepository.getReferenceById(id).getFaculty();
    }

    public Integer getAllByInSchool() {
        logger.info("Was invoked method for get the number of students in the school");
        return studentRepository.getAllByInSchool();
    }

    public Integer getAverageAgeStudent() {
        logger.info("Was invoked method for get average age student");
        return studentRepository.getAverageAgeStudent();
    }

    public List<Student> getLatestFiveStudent() {
        logger.info("Was invoked method for get latest five student");
        return studentRepository.getLatestFiveStudent();
    }
}
