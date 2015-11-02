
-- PLAYER
DROP TABLE IF EXISTS player CASCADE;

CREATE TABLE player (
  wallet_id BIGSERIAL,
  name      VARCHAR(50) UNIQUE NOT NULL ,
  money     INT NOT NULL ,

  PRIMARY KEY (wallet_id)
);

INSERT INTO player(name, money) VALUES (
  'Bender Rodriguez', 1000
);

-- MONEY INCOME
DROP TABLE IF EXISTS money_income CASCADE;

CREATE TABLE money_income (
  operation_id   BIGSERIAL,
  operation_date TIMESTAMP NOT NULL ,
  player         BIGINT NOT NULL ,
  income         INT NOT NULL ,

  PRIMARY KEY (operation_id),
  FOREIGN KEY (player) REFERENCES player ON UPDATE CASCADE ON DELETE CASCADE
);

-- GAME HISTORY
DROP TABLE IF EXISTS game_history CASCADE;

CREATE TABLE game_history (
  game_id    BIGSERIAL,
  start_date TIMESTAMP NOT NULL ,
  player     BIGINT NOT NULL ,
  deck_seed  BIGINT NOT NULL ,
  bet        SMALLINT NOT NULL ,
  hits_count SMALLINT NOT NULL ,

  PRIMARY KEY (game_id),
  FOREIGN KEY (player) REFERENCES player ON UPDATE CASCADE ON DELETE CASCADE
);

