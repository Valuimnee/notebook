DROP SCHEMA IF EXISTS `notebook` ;

CREATE SCHEMA IF NOT EXISTS `notebook` DEFAULT CHARACTER SET utf8 ;
USE `notebook` ;

DROP TABLE IF EXISTS `notebook`.`user` ;

CREATE TABLE IF NOT EXISTS `notebook`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID пользователя\nСоздается при добавлении пользователя в систему.',
  `login` VARCHAR(20) NOT NULL COMMENT 'Логин\nЛогин,указываемый при авторизации. Должен быть уникальным.',
  `password` VARCHAR(40) NOT NULL COMMENT 'Пароль\nЗашифрованный пароль, необходимый для авторизации в систему.',
  `salt` VARCHAR(40) NOT NULL,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0;

DROP TABLE IF EXISTS `notebook`.`note` ;

CREATE TABLE IF NOT EXISTS `notebook`.`note` (
  `note_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `title` VARCHAR(80) NULL,
  `content` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`note_id`),
  INDEX `fk_note_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_note_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `notebook`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;