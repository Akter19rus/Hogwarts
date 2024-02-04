package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByNameOrColorIgnoreCase(String name, String color);
    Faculty findFacultyByColorIgnoreCase(String color);
    Faculty findFacultyByNameIgnoreCase(String color);
}
