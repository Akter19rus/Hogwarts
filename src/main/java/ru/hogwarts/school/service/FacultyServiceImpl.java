package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl {
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultet() {
        logger.info("Was invoked method for get all faculty");
        return facultyRepository.findAll();
    }

    public Faculty getColorFacultet(String color) {
        logger.info("Was invoked method for get color faculty");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public Faculty findFacultyByName(String name) {
        logger.info("Was invoked method for find faculty by name");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    public String getLongestName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparingInt(f -> f.getName().length()))
                .get().getName();
    }

    public int summa() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }

    public int parallelSumma() {
        return IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .sum();
    }
}
