
-- Insertion d'utilisateurs
INSERT INTO users (username, nom, prenom, password) VALUES ('john_doe', 'doe', 'John', '$2a$10$4nRwFl8pQsJroLDg9/AsbePLQp1G9m5T3K00klc1.AFM6hR.Gy7DK');
INSERT INTO users (username, nom, prenom, password) VALUES ('jane_smith', 'smith', 'Jane', '$2a$10$U2mDsTCD5E0OXQ1APBTGo.yGHG.QGr7NnBBGn0h3GSbObuT1ucaZq');
INSERT INTO users (username, nom, prenom, password) VALUES ('bob_jones', 'jones', 'Bob', '$2a$10$w1nl9rG/iRB0lue0HjDVvebI8MZLVY98LtN5RHzB9VzRAIi8Hb.AK');

-- Insertion de séries
INSERT INTO series (title, description) VALUES ('Série 1', 'Description de la série 1');
INSERT INTO series (title, description) VALUES ('Série 2', 'Description de la série 2');
INSERT INTO series (title, description) VALUES ('Série 3', 'Description de la série 3');

-- Insertion d'événements
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-01', 10.0, 'Tag1', 1);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-02', 15.0, 'Tag2', 1);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-03', 20.0, 'Tag1', 1);

INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-01', 5.0, 'Tag3', 2);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-02', 10.0, 'Tag2', 2);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-03', 8.0, 'Tag1', 2);

INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-01', 7.0, 'Tag2', 3);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-02', 12.0, 'Tag3', 3);
INSERT INTO evenements (date, value, tag, series_id) VALUES ('2023-01-03', 18.0, 'Tag1', 3);

