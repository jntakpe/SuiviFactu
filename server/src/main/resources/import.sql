-- Utilisateurs
INSERT INTO utilisateur (id, email, nom, prenom, telephone) VALUES (1, 'jntakpe@mail.com', 'NTAKPE', 'Jocelyn', '1234567890');

-- Rôles
INSERT INTO role (nom) VALUES ('ROLE_ADMIN');
INSERT INTO role (nom) VALUES ('ROLE_USER');

-- Rôles associés aux utilisateurs
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='jntakpe@mail.com'), (SELECT nom FROM role WHERE nom='ROLE_ADMIN'))