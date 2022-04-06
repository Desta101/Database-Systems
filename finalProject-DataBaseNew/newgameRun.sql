
-- Create schema mydb_game and all the table
CREATE SCHEMA mydb_game;
USE mydb_game;

-- Create players teble
CREATE TABLE players (
  Player_id int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  idstate varchar(45) NOT NULL,
  PRIMARY KEY (Player_id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;


-- Create scores teble
CREATE TABLE scores (
  scores_id int NOT NULL AUTO_INCREMENT,
  Player_id varchar(45) NOT NULL,
  Number_of_wins_in_soccer int NOT NULL,
  Number_of_wins_in_tennis int NOT NULL,
  Number_of_wins_in_basketball int NOT NULL,
  Total_wins varchar(45) NOT NULL,
  PRIMARY KEY (scores_id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;


-- Create matches teble
CREATE TABLE matches (
  id_matches int NOT NULL AUTO_INCREMENT,
  Player_id varchar(45) NOT NULL,
  Total_championship_wins varchar(45) NOT NULL,
  idstate varchar(45) NOT NULL,
  PRIMARY KEY (id_matches)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ;

-- Create games teble
CREATE TABLE games (
  idgames int NOT NULL AUTO_INCREMENT,
  Player_id varchar(45) NOT NULL,
  type varchar(45) NOT NULL,
  numofplay varchar(45) NOT NULL,
  PRIMARY KEY (idgames)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;


-- Create state teble
CREATE TABLE state (
  idstate int NOT NULL AUTO_INCREMENT,
  statename varchar(45) NOT NULL,
  PRIMARY KEY (idstate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


CREATE TABLE typegmae (
  idtypegmae int NOT NULL AUTO_INCREMENT,
  namegame varchar(45) NOT NULL,
  PRIMARY KEY (idtypegmae)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;



-- Insert players
INSERT INTO players (Player_id, name,idstate) VALUES
('1', 'gal', 'Usa'),
('2', 'adam', 'Russia'),
('3', 'tal', 'Uk'),
('4', 'shimon', 'Israel'),
('5', 'ron', 'Germany'),
('6', 'ben', 'France'),
('7', 'zeev', 'Japan'),
('8', 'tom', 'South Africa'),
('9', 'chen', 'Austria'),
('10', 'loki', 'Italy');

-- Insert scores
INSERT INTO scores (scores_id, Player_id, Number_of_wins_in_soccer,Number_of_wins_in_tennis, Number_of_wins_in_basketball,Total_wins) VALUES 
('1', '1', '0', '0', '0' , '0'),
('2', '2', '0', '0', '0', '0' ),
('3', '3', '0', '0', '0', '0' ),
('4', '4', '0', '0', '0', '0' ),
('5', '5', '0', '0', '0', '0' ),
('6', '6', '0', '0', '0', '0' ),
('7', '7', '0', '0', '0', '0' ),
('8', '8', '0', '0', '0', '0' ),
('9', '9', '0', '0', '0', '0' ),
('10', '10', '0', '0','0', '0');

-- Insert matches
INSERT INTO matches (id_matches, Player_id,Total_championship_wins, idstate) VALUES 
('1', '1', '0', 'Usa'),
('2', '2', '0', 'Russia'),
('3', '3', '0', 'Uk'),
('4', '4', '0', 'Israel'),
('5', '5', '0', 'Germany'),
('6', '6', '0', 'France'),
('7', '7', '0', 'Japan'),
('8', '8', '0', 'South Africa'),
('9', '9', '0', 'Austria'),
('10', '10', '0', 'Italy');

-- Insert games
INSERT INTO games (idgames,Player_id, type, numofplay) VALUES 
('1', '1', 'Soccer', '0'),
('2', '2', 'Soccer', '0'),
('3', '3', 'Soccer', '0'),
('4', '4', 'Soccer', '0'),
('5', '5', 'Soccer', '0'),
('6', '6', 'Soccer', '0'),
('7', '7', 'Soccer', '0'),
('8', '8', 'Soccer', '0'),
('9', '9', 'Soccer', '0'),
('10', '10', 'Soccer', '0');

-- Insert state
INSERT INTO state (idstate, statename) VALUES 
('1', 'Usa'),
('3', 'Russia'),
('4', 'Uk'),
('5', 'Israel'),
('6', 'Germany'),
('7', 'France'),
('8', 'Italy'),
('9', 'Austria'),
('10', 'South Africa'),
('11', 'Japan');

-- Insert typegmae
INSERT INTO typegmae (idtypegmae,namegame) VALUES
('1', 'soccer'),
('2', 'tennis'),
('3', 'basketball');