-- Utilisateurs
INSERT INTO utilisateur (email, nom, password) VALUES ('jocelyn.ntakpe@soprasteria.com', 'jntakpe', '$2a$10$h2Q5T3fvVpW8FchnKoX1CeU9lIkv3GLfLFex4k4q0A1vw1C13FEpu');

-- Rôles
INSERT INTO role (nom) VALUES ('ROLE_ADMIN');
INSERT INTO role (nom) VALUES ('ROLE_USER');

-- Rôles associés aux utilisateurs
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='jocelyn.ntakpe@soprasteria.com'), (SELECT nom FROM role WHERE nom='ROLE_USER'));
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='jocelyn.ntakpe@soprasteria.com'), (SELECT nom FROM role WHERE nom='ROLE_ADMIN'));