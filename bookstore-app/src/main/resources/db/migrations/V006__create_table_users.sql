CREATE TABLE IF NOT EXISTS user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(255) DEFAULT NULL,
  full_name VARCHAR(255) DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL,
  account_non_expired BOOLEAN DEFAULT true,
  account_non_locked BOOLEAN DEFAULT true,
  credentials_non_expired BOOLEAN DEFAULT true,
  enabled BOOLEAN DEFAULT true,
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_name (user_name)
) ENGINE=InnoDB;