ALTER TABLE student
    ALTER COLUMN age CHECK (age > 15);
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;
ALTER TABLE student
    ADD CONSTRAINT nameUniq UNIQUE (name);
ALTER TABLE faculty
    ADD CONSTRAINT NameAndColorUniq UNIQUE (name, color);
ALTER TABLE student
    ADD CONSTRAINT ageIfNull DEFAULT 20 FOR age;
