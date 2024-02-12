package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT (name) FROM Student", nativeQuery = true)
    Integer getAllByInSchool();

    @Query(value = "SELECT AVG (age) FROM Student ", nativeQuery = true)
    Integer getAverageAgeStudent();

    @Query(value = "SELECT * FROM Student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLatestFiveStudent();
}
