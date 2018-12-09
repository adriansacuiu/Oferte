-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema oferte
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oferte
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oferte` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `oferte` ;

-- -----------------------------------------------------
-- Table `oferte`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Users` (
  `ID_USER` BIGINT(20) NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(225) NULL,
  `FIRST_NAME` VARCHAR(225) NULL,
  `LAST_NAME` VARCHAR(225) NULL,
  `PASSWORD` VARCHAR(225) NULL,
  `EMAIL` VARCHAR(225) NULL,
  `ROLE` VARCHAR(225) NULL,
  PRIMARY KEY (`ID_USER`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Clients` (
  `ID_CLIENT` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` VARCHAR(225) NULL,
  `FIRST_NAME` VARCHAR(225) NULL,
  `LAST_NAME` VARCHAR(225) NULL,
  `PHONE` VARCHAR(225) NULL,
  `EMAIL` VARCHAR(225) NULL,
  `ADDRESS` VARCHAR(225) NULL,
  PRIMARY KEY (`ID_CLIENT`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Cars` (
  `ID_CAR` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `BRAND` VARCHAR(225) NULL,
  `MODEL` VARCHAR(225) NULL,
  PRIMARY KEY (`ID_CAR`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Quotes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Quotes` (
  `ID_QUOTE` BIGINT(20) NOT NULL,
  `ID_CLIENT` BIGINT(20) NULL,
  `ID_CAR` BIGINT(20) NULL,
  `REQUEST` VARCHAR(225) NULL,
  `CREATED_BY` BIGINT(20) NULL,
  PRIMARY KEY (`ID_QUOTE`),
  INDEX `ID_CLIENT_idx` (`ID_CLIENT` ASC),
  INDEX `CREATED_BY_idx` (`CREATED_BY` ASC),
  INDEX `ID_CAR_idx` (`ID_CAR` ASC),
  CONSTRAINT `ID_CLIENT`
    FOREIGN KEY (`ID_CLIENT`)
    REFERENCES `oferte`.`Clients` (`ID_CLIENT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CREATED_BY`
    FOREIGN KEY (`CREATED_BY`)
    REFERENCES `oferte`.`Users` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_CAR`
    FOREIGN KEY (`ID_CAR`)
    REFERENCES `oferte`.`Cars` (`ID_CAR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Products` (
  `ID_PRODUCT` BIGINT(20) NOT NULL,
  `TYPE` VARCHAR(225) NULL,
  `NAME` VARCHAR(225) NULL,
  `DESCRIPTION` VARCHAR(225) NULL,
  `PRICE` INT NULL,
  `PRICE_2` INT NULL,
  PRIMARY KEY (`ID_PRODUCT`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Quote_Products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Quote_Products` (
  `ID_QUOTE` BIGINT(20) NOT NULL,
  `ID_PRODUCT` BIGINT(20) NOT NULL,
  INDEX `ID_QUOTE_idx` (`ID_QUOTE` ASC),
  INDEX `ID_PRODUCT_idx` (`ID_PRODUCT` ASC),
  PRIMARY KEY (`ID_QUOTE`, `ID_PRODUCT`),
  CONSTRAINT `ID_QUOTE`
    FOREIGN KEY (`ID_QUOTE`)
    REFERENCES `oferte`.`Quotes` (`ID_QUOTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_PRODUCT`
    FOREIGN KEY (`ID_PRODUCT`)
    REFERENCES `oferte`.`Products` (`ID_PRODUCT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Datatracking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Datatracking` (
  `ID_DATATRACKING` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `TARGET_TABLE` VARCHAR(225) NULL,
  `TARGET_ID` BIGINT(20) NULL,
  `ACTION_TYPE` VARCHAR(225) NULL,
  `DESCRIPTION` VARCHAR(225) NULL,
  `MADE_BY` BIGINT(20) NULL,
  PRIMARY KEY (`ID_DATATRACKING`),
  INDEX `ID_USER_idx` (`MADE_BY` ASC),
  CONSTRAINT `MADE_BY`
    FOREIGN KEY (`MADE_BY`)
    REFERENCES `oferte`.`Users` (`ID_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`Carge_Rates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`Carge_Rates` (
  `ID_CHARGE_RATE` INT NOT NULL AUTO_INCREMENT,
  `CHARGE_RATE` INT NULL,
  PRIMARY KEY (`ID_CHARGE_RATE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oferte`.`CAR_CHARGE_RATES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oferte`.`CAR_CHARGE_RATES` (
  `ID_CAR` BIGINT(20) NOT NULL,
  `ID_CHARGE_RATE` INT NOT NULL,
  INDEX `ID_CAR_idx` (`ID_CAR` ASC),
  INDEX `ID_CHARGE_RATE_idx` (`ID_CHARGE_RATE` ASC),
  PRIMARY KEY (`ID_CAR`, `ID_CHARGE_RATE`),
  CONSTRAINT `ID_X`
    FOREIGN KEY (`ID_CAR`)
    REFERENCES `oferte`.`Cars` (`ID_CAR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ID_Y`
    FOREIGN KEY (`ID_CHARGE_RATE`)
    REFERENCES `oferte`.`Carge_Rates` (`ID_CHARGE_RATE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
