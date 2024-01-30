package ru.hogwarts.school.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    Student student;
}
