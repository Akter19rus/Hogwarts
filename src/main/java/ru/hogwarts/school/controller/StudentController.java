package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/find-between")
    public ResponseEntity<?> findStudentByAgeBetween(@RequestParam(required = false) int min,
                                                     @RequestParam(required = false) int max) {
        if (min > max) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> findFacultyById(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        Faculty faculty = studentService.findFacultyByStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);

    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/age/{age}")
    public Collection<Student> getAgeStudents(@PathVariable int age) {
        return studentService.getAgeStudent(age);
    }

    @GetMapping("/counts")
    public ResponseEntity<Integer> getAllByInSchool() {
        return ResponseEntity.ok(studentService.getAllByInSchool());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Integer> getAverageAgeStudent() {
        return ResponseEntity.ok(studentService.getAverageAgeStudent());
    }

    @GetMapping("/latest-five")
    public ResponseEntity<List<Student>> getLatestFiveStudent() {
        return ResponseEntity.ok(studentService.getLatestFiveStudent());
    }

    @GetMapping("/only-name-letter-A")
    public ResponseEntity<Collection<Student>> getStudentsLetterA(){
        return ResponseEntity.ok(studentService.getStudentsLetterA());
    }

    @GetMapping("/only-middle-age")
    public ResponseEntity<Double> getMiddleAgeByStudents(){
        return ResponseEntity.ok(studentService.getMiddleAgeByStudents());
    }
}
