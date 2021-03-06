-- MySQL Script generated by MySQL Workbench
-- Fri May 31 20:48:01 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema RPG_and
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RPG_and
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RPG_and` DEFAULT CHARACTER SET utf8 ;
USE `RPG_and` ;

-- -----------------------------------------------------
-- Table `RPG_and`.`lodas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RPG_and`.`lodas` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `tempo` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RPG_and`.`persos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RPG_and`.`persos` (
  `id` INT NOT NULL,
  `persoscol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
