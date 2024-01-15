package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    public Faculty createFaculty(Faculty faculty);

    public Faculty findFaculty(long id);

    public Faculty editFaculty(Faculty faculty);

    public Faculty deleteFaculty(long id);

    public Collection<Faculty> getAllFacultys();

    public Collection<Faculty> getColorFacultys(String color);
}
