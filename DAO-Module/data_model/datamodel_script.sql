-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `previllege` VARCHAR(45) NOT NULL COMMENT '',
  `contactemail` VARCHAR(100) NULL COMMENT '',
  `contactnumber` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`visitors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`visitors` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `pagepath` VARCHAR(100) NULL COMMENT '',
  `count` BIGINT(100) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `comments` VARCHAR(255) NOT NULL COMMENT '',
  `username` VARCHAR(45) NULL COMMENT '',
  `contactnumber` VARCHAR(45) NULL COMMENT '',
  `contactemail` VARCHAR(45) NULL COMMENT '',
  `acknowledge` TINYINT(1) NULL DEFAULT 0 COMMENT '',
  `status` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `emailaddress` VARCHAR(45) NOT NULL COMMENT '',
  `contactnumber` VARCHAR(45) NULL COMMENT '',
  `lastlogindate` TIMESTAMP NOT NULL COMMENT '',
  `subscription_type` VARCHAR(255) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
