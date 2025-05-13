CREATE TABLE student
(
    id       UUID         NOT NULL,
    name     VARCHAR(100) NOT NULL,
    registry VARCHAR(6)   NOT NULL,
    grade    VARCHAR(2),
    created  TIMESTAMP WITHOUT TIME ZONE,
    updated  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

ALTER TABLE student
    ADD CONSTRAINT uc_student_registry UNIQUE (registry);