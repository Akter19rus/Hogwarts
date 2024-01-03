package ru.hogwarts.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class Student {
    private Long id;
    private String name;
    private int age;
    private static int count = 0;

    public Student () {
        this.setId((long) count++);
    }
}
