SELECT s.name, s.age, f.name
FROM Student s
         LEFT JOIN Faculty f ON s.facultyId = f.id;

SELECT s.name
FROM Avatar a
         INNER JOIN Student s ON a.studentId = s.id;