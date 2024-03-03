package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

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

    public List<Student> getAllStudents() {
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

    public Collection<Student> getStudentsLetterA() {
        return studentRepository.findAll().stream()
                .parallel()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public Double getMiddleAgeByStudents() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }

    public void printName(int i) {
        System.out.println(getAllStudents().get(i).getName());
    }

    public void printNameParallel() {
        printName(0);
        new Thread(() -> {
            printName(2);
            printName(3);
        }).start();
        new Thread(() -> {
            printName(4);
            printName(5);
        }).start();
        printName(1);
    }

    public synchronized void printNameSynchronized(int i) {
        System.out.println(getAllStudents().get(i).getName());
    }

    public void printNameParallelSynchronized() {
        printNameSynchronized(0);
        new Thread(() -> {
            printNameSynchronized(2);
            printNameSynchronized(3);
        }).start();
        new Thread(() -> {
            printNameSynchronized(4);
            printNameSynchronized(5);
        }).start();
        printNameSynchronized(1);
    }
}
