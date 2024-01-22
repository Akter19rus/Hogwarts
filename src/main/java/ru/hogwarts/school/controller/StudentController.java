package ru.hogwarts.school.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentServiceImpl studentService;

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

    @GetMapping("/findBetween")
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
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("age")
    public Collection<Student> getAgeStudents(@PathVariable int age) {
        return studentService.getAgeStudent(age);
    }
}
