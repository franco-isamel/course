create database course;
\c course;

-- create table Coure(
--     idCourse SERIAL PRIMARY KEY,
--     nomCourse VARCHAR
-- );

create table Etape(
    idEtape SERIAL PRIMARY KEY,
    nomEtape VARCHAR,
    longueur NUMERIC,
    nbCoureur INT,
    rangEtape INT
);

create table Equipe(
    idEquipe SERIAL PRIMARY KEY,
    nomEquipe VARCHAR,
    loginEquipe VARCHAR,
    pwdEquipe VARCHAR
);

create table Categorie(
    idCategorie SERIAL PRIMARY KEY,
    nomCategorie VARCHAR
);

create table Coureur(
    idCoureur SERIAL PRIMARY KEY,
    nomCoureur VARCHAR,
    numero INT,
    genre CHAR,
    DateNaissance Date,
    idEquipe INT,
    FOREIGN KEY( idEquipe ) REFERENCES Equipe( idEquipe ) ON DELETE CASCADE
);

create table CoureurCategorie(
    idCoureur INT,
    idCategorie INT,
    FOREIGN KEY( idCoureur ) REFERENCES Coureur( idCoureur ) ON DELETE CASCADE,
    FOREIGN KEY( idCategorie ) REFERENCES Categorie( idCategorie ) ON DELETE CASCADE
);

create table AdminCourse(
    idAdmin SERIAL PRIMARY KEY,
    loginAdmin VARCHAR,
    pwdLogin VARCHAR
);
create table Ad(
    idAdmin SERIAL PRIMARY KEY,
    loginAdmin VARCHAR,
    pwdLogin VARCHAR
);
insert into AdminCourse (loginAdmin,pwdLogin) VALUES('adminLogin','pwd');

create table EtapeCoureur(
    idEtape INT,
    idCoureur INT,
    dateDepart TIMESTAMP DEFAULT NULL,
    dateArrivee TIMESTAMP DEFAULT NULL,
    FOREIGN KEY( idEtape ) REFERENCES Etape( idEtape ) ON DELETE CASCADE,
    FOREIGN KEY( idCoureur ) REFERENCES Coureur( idCoureur ) ON DELETE CASCADE
);

create table score(
    idScore SERIAL PRIMARY KEY,
    valeur INT
);
insert into score (valeur) VALUES
(10),(6),(4),(2),(1);


-- Insertion des données des équipes
INSERT INTO Equipe (nomEquipe, loginEquipe, pwdEquipe) VALUES
('Equipe A', 'loginA', 'pwdA'),
('Equipe B', 'loginB', 'pwdB'),
('Equipe C', 'loginC', 'pwdC'),
('Equipe D', 'loginD', 'pwdD'),
('Equipe E', 'loginE', 'pwdE');

-- Insertion des catégories
INSERT INTO Categorie (nomCategorie) VALUES
('Homme'),
('Femme'),
('Junior'),
('Senior');

-- Insertion des coureurs
INSERT INTO Coureur (nomCoureur, numero, genre, dateNaissance, idEquipe) VALUES
('Lova', 1, 'M', '1990-01-01', 1),
('Sabrina', 2, 'F', '1992-02-02', 1),
('Justin', 3, 'M', '1988-03-03', 2),
('Vero', 4, 'F', '1991-04-04', 2),
('John', 5, 'M', '1993-05-05', 3),
('Jill', 6, 'F', '1989-06-06', 3),
('Victor', 7, 'M', '1994-07-07', 1);

-- Association des coureurs aux catégories
INSERT INTO CoureurCategorie (idCoureur, idCategorie) VALUES
(1, 1), (1, 4), -- Lova: Homme, Senior
(2, 2), (2, 4), -- Sabrina: Femme, Senior
(3, 1), (3, 4), -- Justin: Homme, Senior
(4, 2), (4, 4), -- Vero: Femme, Senior
(5, 1), (5, 4), -- John: Homme, Senior
(6, 2), (6, 4), -- Jill: Femme, Senior
(7, 1), (7, 4); -- Victor: Homme, Senior

SELECT
co.idCoureur,
co.nomCoureur,
c.idCategorie,
c.nomCategorie
FROM CoureurCategorie cc
JOIN Categorie c on cc.idCategorie = c.idCategorie
JOIN Coureur co on co.idCoureur = cc.idCoureur;

-- Insertion des étapes
INSERT INTO Etape (nomEtape, longueur, nbCoureur, rangEtape) VALUES
('Betsizaraina', 10.5, 2, 1),
('Ampasimbe', 5.0, 1, 3);

-- Insertion des informations de départ et d'arrivée des étapes
INSERT INTO EtapeCoureur (idEtape, idCoureur, dateDepart, dateArrivee) VALUES
(1, 1, '2023-06-01 09:00:00', '2023-06-01 10:00:00'),
(1, 2, '2023-06-01 09:00:00', '2023-06-01 10:05:00'),
(1, 3, '2023-06-01 09:00:00', '2023-06-01 09:55:00'),
(1, 4, '2023-06-01 09:00:00', '2023-06-01 10:10:00'),
(1, 5, '2023-06-01 09:00:00', '2023-06-01 10:15:00'),
(1, 6, '2023-06-01 09:00:00', '2023-06-01 10:20:00'),
(2, 7, '2023-06-02 09:00:00', '2023-06-02 09:30:00'),
(2, 3, '2023-06-02 09:00:00', '2023-06-02 09:25:00'),
(2, 6, '2023-06-02 09:00:00', '2023-06-02 09:35:00');

create or replace view EtapeCoureurView as
SELECT
co.idCoureur,
co.nomCoureur,
e.idEtape,
e.nomEtape,
eq.idEquipe,
eq.nomEquipe,
ec.dateDepart,
ec.dateArrivee
FROM EtapeCoureur ec
JOIN Etape e on ec.idEtape = e.idEtape
JOIN Coureur co on co.idCoureur = ec.idCoureur
JOIN Equipe eq ON eq.idEquipe = co.idEquipe;

create or replace view classement as
SELECT
co.idCoureur,
co.nomCoureur,
e.idEtape,
e.nomEtape,
eq.idEquipe,
eq.nomEquipe,
ec.dateDepart,
ec.dateArrivee,
ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) as rangCoureur,
CASE
    WHEN ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) = 1 THEN 10
    WHEN ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) = 2 THEN 6
    WHEN ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) = 3 THEN 4
    WHEN ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) = 4 THEN 2
    WHEN ROW_NUMBER() OVER (PARTITION BY e.idEtape ORDER BY ec.dateArrivee) = 5 THEN 1
    ELSE 0
END AS points
FROM EtapeCoureur ec
JOIN Etape e on ec.idEtape = e.idEtape
JOIN Coureur co on co.idCoureur = ec.idCoureur
JOIN Equipe eq ON eq.idEquipe = co.idEquipe
ORDER BY ec.dateArrivee asc;

-- select * from classement;

create or replace view classement as
SELECT
ROW_NUMBER() OVER (SUM(c.points)) as rang,
c.idEquipe,
c.nomEquipe,
SUM(c.points) as points
FROM classement c
GROUP BY c.idEquipe
ORDER BY SUM(c.points) ASC;

create or replace view classementGeneral as
SELECT
c.idEquipe,
c.nomEquipe,
SUM(c.points) as points
FROM classement c
GROUP BY c.idEquipe,c.nomEquipe
ORDER BY SUM(c.points) DESC;



\i 'D:/Dev/framework/spring/EVAL_S6/eval_2/course/src/main/sql/course.sql';