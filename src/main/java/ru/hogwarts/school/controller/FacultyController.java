package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFacultet() {
        return ResponseEntity.ok(facultyService.getAllFacultet());
    }

    @GetMapping("/color")
    public ResponseEntity<Faculty> findFacultyByColor(@RequestParam String color) {
        return ResponseEntity.ok(facultyService.getColorFacultet(color));
    }

    @GetMapping("/byNameOrByColor")
    public ResponseEntity<?> getNameOrColor(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.getColorFacultet(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyByName(name));
        }
        return ResponseEntity.ok(facultyService.getAllFacultet());
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/longest-name")
    public ResponseEntity<String> getLongestName() {
        return ResponseEntity.ok(facultyService.getLongestName());
    }

    @GetMapping("/sum")
    public ResponseEntity<Integer> getSummaTime() {
        return ResponseEntity.ok(facultyService.summa());

    }
    @GetMapping("/parallel-sum")
    public ResponseEntity<Integer> getSummaTimeParallel(){
        return ResponseEntity.ok(facultyService.parallelSumma());
    }
}
