-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S1 Data Definition Language
--
-- (c) 2020 Hogeschool Utrecht
-- Tijmen Muller (tijmen.muller@hu.nl)
-- André Donk (andre.donk@hu.nl)
--
--
-- Opdracht schrijf SQL-queries om onderstaande resultaten op te vragen,
-- aan te maken, verwijderen of aan te passen in de database van de
-- bedrijfscasus.
--
-- Codeer je uitwerking onder de regel 'DROP VIEW ...' (bij een SELECT)
-- of boven de regel 'ON CONFLICT DO NOTHING;' (bij een INSERT)
-- Je kunt deze eigen query selecteren en los uitvoeren, en wijzigen tot
-- je tevreden bent.
--
-- Vervolgens kun je je uitwerkingen testen door de testregels
-- (met [TEST] erachter) te activeren (haal hiervoor de commentaartekens
-- weg) en vervolgens het hele bestand uit te voeren. Hiervoor moet je de
-- testsuite in de database hebben geladen (bedrijf_postgresql_test.sql).
-- NB niet alle opdrachten hebben testregels.
--
-- Lever je werk pas in op Canvas als alle tests slagen. Draai daarna
-- alle wijzigingen in de database terug met de queries helemaal onderaan.
-- ------------------------------------------------------------------------


-- S1.1. Geslacht
--
-- Voeg een kolom `geslacht` toe aan de medewerkerstabel.
-- Voeg ook een beperkingsregel `m_geslacht_chk` toe aan deze kolom,
-- die ervoor zorgt dat alleen 'M' of 'V' als geldige waarde wordt
-- geaccepteerd. Test deze regel en neem de gegooide foutmelding op als
-- commentaar in de uitwerking.
ALTER TABLE medewerkers ADD geslacht varchar(1);
ALTER TABLE medewerkers 
ADD CONSTRAINT m_geslacht_chk CHECK (geslacht = 'm' OR geslacht = 'v');

-- S1.2. Nieuwe afdeling
--
-- Het bedrijf krijgt een nieuwe onderzoeksafdeling 'ONDERZOEK' in Zwolle.
-- Om de onderzoeksafdeling op te zetten en daarna te leiden wordt de
-- nieuwe medewerker A DONK aangenomen. Hij krijgt medewerkersnummer 8000
-- en valt direct onder de directeur.
-- Voeg de nieuwe afdeling en de nieuwe medewerker toe aan de database.
INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm, afd, geslacht)
VALUES (8000, 'DONK', 'A', 'OZ', 7839, '1972-11-17', 6000, 200, NULL, 'm');
INSERT INTO afdelingen (anr, naam, locatie, hoofd) VALUES (50,'ONDERZOEK', 'UTRECHT',8000)

-- S1.3. Verbetering op afdelingentabel
--
-- We gaan een aantal verbeteringen doorvoeren aan de tabel `afdelingen`
--   a) Maak een sequence die afdelingsnummers genereert. Denk aan de beperking
--      dat afdelingsnummers veelvouden van 10 zijn.
--   b) Voeg een aantal afdelingen toe aan de tabel, maak daarbij gebruik van
--      de nieuwe sequence.
--   c) Op enig moment gaat het mis. De betreffende kolommen zijn te klein voor
--      nummers van 3 cijfers. Los dit probleem op.
CREATE SEQUENCE afdelingen_nummer_seq
    INCREMENT BY 10
    START WITH 60;
    INSERT INTO afdelingen (anr, naam, locatie, hoofd) VALUES ( nextval('afdelingen_nummer_seq'),'ONDERZOEK', 'UTRECHT',8000)
ALTER TABLE afdelingen
DROP CONSTRAINT a_anr_chk;

-- S1.4. Adressen
--
-- Maak een tabel `adressen`, waarin de adressen van de medewerkers worden
-- opgeslagen (inclusief adreshistorie). De tabel bestaat uit onderstaande
-- kolommen. Voeg minimaal één rij met adresgegevens van A DONK toe.
--
--    postcode      PK, bestaande uit 6 karakters (4 cijfers en 2 letters)
--    huisnummer    PK
--    ingangsdatum  PK
--    einddatum     moet na de ingangsdatum liggen
--    telefoon      10 cijfers, uniek
--    med_mnr       FK, verplicht
CREATE TABLE adressen (
    postcode varchar(6),
    huisnummer varchar(120),
    ingangsdatum date,
    einddatum date,
    telefoon INT,
    med_mnr int,
    CONSTRAINT pk_adressen PRIMARY KEY (postcode, huisnummer, ingangsdatum),
    CONSTRAINT datum_chk CHECK (einddatum > ingangsdatum),
    CONSTRAINT fk_medewerkers FOREIGN KEY (med_mnr) REFERENCES medewerkers (mnr)
);

-- S1.5. Commissie
--
-- De commissie van een medewerker (kolom `comm`) moet een bedrag bevatten als de medewerker een functie als
-- 'VERKOPER' heeft, anders moet de commissie NULL zijn. Schrijf hiervoor een beperkingsregel. Gebruik onderstaande
-- 'illegale' INSERTs om je beperkingsregel te controleren.

INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm)
VALUES (8001, 'MULLER', 'TJ', 'TRAINER', 7566, '1982-08-18', 2000, 500);

INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm)
VALUES (8002, 'JANSEN', 'M', 'VERKOPER', 7698, '1981-07-17', 1000, NULL);

ALTER TABLE medewerkers
ADD CONSTRAINT functie_commissie_chk CHECK (
    NOT (functie = 'VERKOPER' AND commissie IS NULL)
);

-- -------------------------[ HU TESTRAAMWERK ]--------------------------------
-- Met onderstaande query kun je je code testen. Zie bovenaan dit bestand
-- voor uitleg.

SELECT  FROM test_exists('S1.1', 1) AS resultaat
UNION
SELECT  FROM test_exists('S1.2', 1) AS resultaat
UNION
SELECT 'S1.3 wordt niet getest geen test mogelijk.' AS resultaat
UNION
SELECT  FROM test_exists('S1.4', 6) AS resultaat
UNION
SELECT 'S1.5 wordt niet getest handmatige test beschikbaar.' AS resultaat
ORDER BY resultaat;


-- Draai alle wijzigingen terug om conflicten in komende opdrachten te voorkomen.
DROP TABLE IF EXISTS adressen;
UPDATE medewerkers SET afd = NULL WHERE mnr  7369 OR mnr  7934;
UPDATE afdelingen SET hoofd = NULL WHERE anr  40;
DELETE FROM afdelingen WHERE anr  40;
DELETE FROM medewerkers WHERE mnr  7369 OR mnr  7934;
ALTER TABLE medewerkers DROP CONSTRAINT IF EXISTS m_geslacht_chk;
ALTER TABLE medewerkers DROP COLUMN IF EXISTS geslacht;