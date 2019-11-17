USE TWITTERTEST;

DROP TABLE IF EXISTS status; 

CREATE TABLE status (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    date_created DATETIME NOT NULL,
    text VARCHAR(280) NOT NULL,
    profile_image MEDIUMBLOB,
    is_liked BOOLEAN
) ENGINE=InnoDB;

INSERT INTO status (id, name, date_created, text,  is_liked) VALUES 
(34532643, 'Evan Greenstein', '2019-11-09 12:34:56', 'Testing 1 2 3', true),
(49867839, 'Mikey1891', '2019-11-11 16:52:28', 'Moose caboose', false );

