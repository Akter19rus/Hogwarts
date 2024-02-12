package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void getFacultiesTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject
                        ("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    void postFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setColor("черный");
        faculty.setId(50L);
        faculty.setName("Последователи");
        faculty.setStudents(null);
        Assertions
                .assertThat(this.restTemplate
                        .postForObject("http://localhost:" + port + "/faculty", faculty, String.class));
    }

    @Test
    void updateFacultyTest() throws Exception {
        Faculty faculty = new Faculty(66L, "маглы", "белый", null);
        long id = this.restTemplate.postForObject(
                "http://localhost:" + port + "/faculty", faculty, Faculty.class).getId();
        Faculty faculty1 = new Faculty(77L, "драконы", "желтый", null);
        restTemplate.put("http://localhost:" + port + "/faculty", faculty1);
        Assertions
                .assertThat((this.restTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/" + id, String.class)))
                .contains("" + id);

    }

    @Test
    void deleteFacultyTest() throws Exception {
        Faculty faculty = new Faculty(88L, "Твари", "фиолетовый", null);
        long id = this.restTemplate.postForObject
                ("http://localhost:" + port + "/faculty", faculty, Faculty.class).getId();
        restTemplate.delete("http://localhost:" + port + "/faculty/" + id);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/faculty/" + id, String.class)))
                .toString().contains("500");
    }
}
