CREATE TABLE professor
(
    id             UUID         NOT NULL,
    name           VARCHAR(100) NOT NULL,
    age            INTEGER      NOT NULL,
    is_coordinator BOOLEAN      NOT NULL,
    created        TIMESTAMP WITHOUT TIME ZONE,
    updated        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_professor PRIMARY KEY (id)
);