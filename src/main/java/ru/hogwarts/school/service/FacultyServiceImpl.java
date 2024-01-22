package ru.hogwarts.school.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class FacultyServiceImpl {
    @Autowired
    private final FacultyRepository facultyRepository;

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultet() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getColorFacultet(String color) {

        return facultyRepository.findAll().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Collection<Faculty> findFacultyByNameOrColor(String color, String name) {
        return facultyRepository.findFacultyByNameOrColorIgnoreCase(color, name);
    }
}
