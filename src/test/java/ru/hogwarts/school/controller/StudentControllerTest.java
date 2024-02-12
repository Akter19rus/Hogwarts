package ru.hogwarts.school.controller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions
                .assertThat(studentController).isNotNull();
    }

    @Test
    public void getStudentTest() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void postStudentTest() throws Exception {
        Student student = new Student();
        student.setId(11L);
        student.setAge(82);
        student.setName("Альбус Дамблдор");
        student.setFaculty(null);
        Assertions
                .assertThat(this.restTemplate.postForObject(
                        "http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    void updateStudentTest() throws Exception {
        Student student = new Student(33L, "Аластор Грюм", 67);
        Student student1 = new Student(44L, "Годрик Гриффиндор", 585);
        long id = this.restTemplate.postForObject(
                        "http://localhost:" + port + "/student", student, Student.class)
                .getId();
        restTemplate.put(
                "http://localhost:" + port + "/student", student1);
        Assertions
                .assertThat((this.restTemplate.getForObject
                        ("http://localhost:" + port + "/student/" + id, String.class)))
                .contains("" + id);
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student(55L, "Гораций Слизнорт", 82);
        long id = this.restTemplate.postForObject(
                "http://localhost:" + port + "/student", student, Student.class)
                .getId();
        restTemplate.delete(
                "http://localhost:" + port + "/student/" + id);
        Assertions
                .assertThat((this.restTemplate.getForObject(
                        "http://localhost:" + port + "/students/" + id, String.class)))
                .toString().contains("500");
    }
}
