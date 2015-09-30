-- Utilisateurs
--INSERT INTO utilisateur (email, nom, password, activated) VALUES ('jocelyn.ntakpe@soprasteria.com', 'jntakpe', '$2a$10$h2Q5T3fvVpW8FchnKoX1CeU9lIkv3GLfLFex4k4q0A1vw1C13FEpu', true);
INSERT INTO public.utilisateur (activated, activationkey, email, nom, password) VALUES (false, 'y9vWWo5b3AmYTKJrjCiy', 'nassim.boutouta@soprasteria.com', 'nboutouta', '$2a$10$zhQYFnIxb.8bOrSsGnkr1OUJXFPO1wIWfiZVt1YQQZkg1QPxPnseO');

-- Rôles
INSERT INTO role (nom) VALUES ('ROLE_ADMIN');
INSERT INTO role (nom) VALUES ('ROLE_USER');

-- Rôles associés aux utilisateurs
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='jocelyn.ntakpe@soprasteria.com'), (SELECT nom FROM role WHERE nom='ROLE_USER'));
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='jocelyn.ntakpe@soprasteria.com'), (SELECT nom FROM role WHERE nom='ROLE_ADMIN'));
INSERT INTO utilisateur_role (utilisateur_id, role_nom) VALUES ((SELECT id FROM utilisateur WHERE email='nassim.boutouta@soprasteria.com'), (SELECT nom FROM role WHERE nom='ROLE_USER'));