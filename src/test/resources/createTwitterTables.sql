USE TWITTER;

DROP TABLE IF EXISTS status; 

CREATE TABLE status (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    date_created DATETIME NOT NULL,
    text VARCHAR(280) NOT NULL,
    profile_image MEDIUMBLOB NOT NULL,
    is_liked BOOLEAN
) ENGINE=InnoDB;