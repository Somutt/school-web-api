CREATE TABLE classroom
(
    id           UUID                        NOT NULL,
    code         VARCHAR(5)                  NOT NULL,
    schedule     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    professor_id UUID,
    room_id      UUID,
    CONSTRAINT pk_classroom PRIMARY KEY (id)
);

ALTER TABLE classroom
    ADD CONSTRAINT uc_classroom_code UNIQUE (code);

ALTER TABLE classroom
    ADD CONSTRAINT FK_CLASSROOM_ON_PROFESSOR FOREIGN KEY (professor_id) REFERENCES professor (id);

ALTER TABLE classroom
    ADD CONSTRAINT FK_CLASSROOM_ON_ROOM FOREIGN KEY (room_id) REFERENCES room (id);