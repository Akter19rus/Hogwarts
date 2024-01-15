package ru.hogwarts.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.Objects;
@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String color;

    public Faculty(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.color = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return color == faculty.color && Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + color +
                '}';
    }
}
