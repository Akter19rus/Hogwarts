select *
from student;

select *
from student
where age > 23 and age < 26;

select name from student;

select *
from student
where name like '%д%';

select *
from student
where age < student.id;

select *
from student
order by age;

