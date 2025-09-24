-- File import.sql corretto per Spring Boot + PostgreSQL
-- IMPORTANTE: Ogni comando deve terminare con ;

-- Pulizia in ordine inverso alle dipendenze
DELETE FROM prenotazione;
DELETE FROM statoposto;
DELETE FROM spettacolo;
DELETE FROM posto;
DELETE FROM film;
DELETE FROM sala;
DELETE FROM credentials;
DELETE FROM users;

-- 1. USERS (indipendenti)
INSERT INTO users (id, name, surname, email) VALUES (199, 'Mario', 'Rossi', 'mario.rossi@example.com');
INSERT INTO users (id, name, surname, email) VALUES (299, 'Giulia', 'Verdi', 'giulia.verdi@example.com');
INSERT INTO users (id, name, surname, email) VALUES (399, 'Luca', 'Bianchi', 'luca.bianchi@example.com');

-- 2. CREDENTIALS (dipendono da users)
INSERT INTO credentials (id, username, password, role, user_id) VALUES (199, 'mario.rossi', '$2a$10$wTf2M79m4l.5z7wQx9v1.u8M9J0K1L2P3O4Q5R6S7T8U9V0W1X2', 'ADMIN', 199);
INSERT INTO credentials (id, username, password, role, user_id) VALUES (299, 'giulia.verdi', '$2a$10$iM8G2C7yH3d.9a8b6c4.e2f1g3h4i5j6k7l8m9n0o1p2q3r4s5t6u7', 'DEFAULT', 299);
INSERT INTO credentials (id, username, password, role, user_id) VALUES (399, 'luca.bianchi', '$2a$10$tZ5V6b7N8d.9c0x1y2.z3a4b5c6d7e8f9g0h1i2j3k4l5m6n7o8p9', 'DEFAULT', 399);

-- 3. SALE (indipendenti)
INSERT INTO sala (id, codice, capienza, proiettore4k, audio_dolby) VALUES (19, 'Sala A', 3, true, true);
INSERT INTO sala (id, codice, capienza, proiettore4k, audio_dolby) VALUES (29, 'Sala B', 2, false, true);
INSERT INTO sala (id, codice, capienza, proiettore4k, audio_dolby) VALUES (39, 'Sala C', 2, true, false);

-- 4. FILM (indipendenti) - ATTENZIONE: questi ID devono esistere prima degli spettacoli
INSERT INTO film (id, titolo, immagine_copertina_url, eta_minima, regista, attori, durata_minuti, trama) VALUES (19, 'Dune: Parte Due', 'https://pad.mymovies.it/filmclub/2021/10/212/locandinapg2.jpg', 12, 'Denis Villeneuve', 'Timothée Chalamet, Zendaya', 166, 'Paul Atreides si unisce ai Fremen per vendicarsi dei cospiratori.');
INSERT INTO film (id, titolo, immagine_copertina_url, eta_minima, regista, attori, durata_minuti, trama) VALUES (29, 'Oppenheimer', 'https://static.rbcasting.com/Oppenheimer-poster-ita-2023-2222.jpeg', 16, 'Christopher Nolan', 'Cillian Murphy, Emily Blunt', 180, 'La storia di J. Robert Oppenheimer, padre della bomba atomica.');
INSERT INTO film (id, titolo, immagine_copertina_url, eta_minima, regista, attori, durata_minuti, trama) VALUES (39, 'Inside Out 2', 'https://pad.mymovies.it/filmclub/2022/09/094/locandinapg2.jpg', 6, 'Kelsey Mann', 'Amy Poehler, Maya Hawke', 96, 'Le emozioni di Riley tornano con una nuova avventura.');

-- 5. SPETTACOLI (dipendono da film e sale)
INSERT INTO spettacolo (id, film_id, sala_id, orario) VALUES (101, 19, 19, '2025-09-22 20:00:00');
INSERT INTO spettacolo (id, film_id, sala_id, orario) VALUES (102, 19, 19, '2025-09-23 22:30:00');
INSERT INTO spettacolo (id, film_id, sala_id, orario) VALUES (103, 29, 29, '2025-09-22 19:30:00');
INSERT INTO spettacolo (id, film_id, sala_id, orario) VALUES (104, 39, 39, '2025-09-22 18:00:00');

-- 6. POSTI (dipendono da sale)
INSERT INTO posto (id, codice, sala_id) VALUES (19, 'A1', 19);
INSERT INTO posto (id, codice, sala_id) VALUES (29, 'A2', 19);
INSERT INTO posto (id, codice, sala_id) VALUES (39, 'A3', 19);

-- Aggiungi più posti per le altre sale (Sala B, id 29 e Sala C, id 39)
-- Esempio per Sala B (id 29)
INSERT INTO posto (id, codice, sala_id) VALUES (40, 'B1', 29);
INSERT INTO posto (id, codice, sala_id) VALUES (41, 'B2', 29);
-- ... aggiungi tutti gli altri posti per Sala B...

-- Esempio per Sala C (id 39)
INSERT INTO posto (id, codice, sala_id) VALUES (50, 'C1', 39);
INSERT INTO posto (id, codice, sala_id) VALUES (51, 'C2', 39);
-- ... aggiungi tutti gli altri posti per Sala C...

-- Aggiungi gli stati dei posti per gli spettacoli mancanti (103 e 104)
-- Spettacolo 103 (Sala B)
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (70, 103, 40, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (71, 103, 41, false);
-- ... continua per tutti gli altri posti di Sala B ...

-- Spettacolo 104 (Sala C)
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (80, 104, 50, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (81, 104, 51, false);

-- 7. STATO POSTO (dipendono da spettacoli e posti)
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (19, 101, 19, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (29, 101, 29, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (39, 101, 39, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (49, 102, 19, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (59, 102, 29, false);
INSERT INTO statoposto (id, spettacolo_id, posto_id, occupato) VALUES (69, 102, 39, false);

-- 8. UPDATE stato posto
UPDATE statoposto SET occupato = true WHERE id = 19;

-- 9. PRENOTAZIONI (dipendono da tutto il resto)
INSERT INTO prenotazione (id, credentials_id, spettacolo_id, posto_id) VALUES (19, 199, 101, 19);
