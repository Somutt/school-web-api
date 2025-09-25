CREATE TABLE classroom_student
(
    created      TIMESTAMP WITHOUT TIME ZONE,
    student_id   UUID NOT NULL,
    classroom_id UUID NOT NULL,
    CONSTRAINT pk_classroom_student PRIMARY KEY (student_id, classroom_id)
);

ALTER TABLE classroom_student
    ADD CONSTRAINT FK_CLASSROOM_STUDENT_ON_CLASSROOM FOREIGN KEY (classroom_id) REFERENCES classroom(id);

ALTER TABLE classroom_student
    ADD CONSTRAINT FK_CLASSROOM_STUDENT_ON_STUDENT FOREIGN KEY (student_id) REFERENCES student(id);