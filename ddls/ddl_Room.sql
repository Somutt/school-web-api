CREATE TABLE room
(
    id       UUID        NOT NULL,
    name     VARCHAR(30) NOT NULL,
    capacity INTEGER     NOT NULL,
    created  TIMESTAMP WITHOUT TIME ZONE,
    updated  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_room PRIMARY KEY (id)
);

ALTER TABLE room
    ADD CONSTRAINT uc_room_name UNIQUE (name);