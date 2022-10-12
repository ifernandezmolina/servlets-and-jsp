CREATE TABLE PLAYER
(
    id      serial PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    votes   NUMERIC     NOT NULL,
    jpgName VARCHAR(50) NOT NULL
);