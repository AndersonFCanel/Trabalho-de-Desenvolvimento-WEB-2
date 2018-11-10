-- MySQL Script generated by MySQL Workbench
-- 11/10/18 15:42:12
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema dbweb2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbweb2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbweb2` DEFAULT CHARACTER SET utf8 ;
USE `dbweb2` ;

-- -----------------------------------------------------
-- Table `dbweb2`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbweb2`.`usuario` (
  `IDUSUARIO` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(25) NOT NULL,
  `SOBRENOME` VARCHAR(50) NOT NULL,
  `ENDERECO` VARCHAR(255) NOT NULL,
  `SENHA` VARCHAR(12) NOT NULL,
  `LOGIN` VARCHAR(20) NOT NULL,
  `DATANASCIMENTO` VARCHAR(12) NOT NULL,
  `isadm` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`IDUSUARIO`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbweb2`.`voo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbweb2`.`voo` (
  `idvoo` INT(11) NOT NULL AUTO_INCREMENT,
  `ida` DATETIME NOT NULL,
  `origem` VARCHAR(100) NOT NULL,
  `destino` VARCHAR(100) NOT NULL,
  `confirmacao` TINYINT(1) NOT NULL DEFAULT '0',
  `valorVoo` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idvoo`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbweb2`.`assento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbweb2`.`assento` (
  `idassento` INT(11) NOT NULL,
  `ocupado` TINYINT(1) NOT NULL DEFAULT '0',
  `voo_idvoo` INT(11) NOT NULL,
  `USUARIO_IDUSUARIO` INT(11) ZEROFILL NOT NULL,
  INDEX `fk_assento_voo1_idx` (`voo_idvoo` ASC),
  INDEX `fk_assento_USUARIO1_idx` (`USUARIO_IDUSUARIO` ASC),
  CONSTRAINT `fk_assento_USUARIO1`
    FOREIGN KEY (`USUARIO_IDUSUARIO`)
    REFERENCES `dbweb2`.`usuario` (`IDUSUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assento_voo1`
    FOREIGN KEY (`voo_idvoo`)
    REFERENCES `dbweb2`.`voo` (`idvoo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbweb2`.`cartaodecredito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbweb2`.`cartaodecredito` (
  `NUMEROCARTAO` VARCHAR(19) NOT NULL,
  `DATAVENCIMENTO` VARCHAR(12) NOT NULL,
  `USUARIO_IDUSUARIO` INT(11) NOT NULL,
  PRIMARY KEY (`NUMEROCARTAO`),
  INDEX `USUARIO_IDUSUARIO` (`USUARIO_IDUSUARIO` ASC),
  CONSTRAINT ``
    FOREIGN KEY (`USUARIO_IDUSUARIO`)
    REFERENCES `dbweb2`.`usuario` (`IDUSUARIO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dbweb2`.`compravoo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbweb2`.`compravoo` (
  `idcompraVoo` INT(11) NOT NULL AUTO_INCREMENT,
  `voo_idvoo` INT(11) NOT NULL,
  `voo_idvooVolta` INT(11) NULL,
  `usuario_IDUSUARIO` INT(11) NOT NULL,
  `cartaodecredito_NUMEROCARTAO` VARCHAR(19) NOT NULL,
  `valorTotalCompra` INT(11) NOT NULL DEFAULT '0',
  `horaDaCompra` DATETIME NOT NULL,
  PRIMARY KEY (`idcompraVoo`),
  INDEX `fk_compravoo_voo1_idx` (`voo_idvoo` ASC),
  INDEX `fk_compravoo_usuario1_idx` (`usuario_IDUSUARIO` ASC),
  INDEX `fk_compravoo_cartaodecredito1_idx` (`cartaodecredito_NUMEROCARTAO` ASC),
  INDEX `fk_compravoo_voo2_idx` (`voo_idvooVolta` ASC),
  CONSTRAINT `fk_compravoo_cartaodecredito1`
    FOREIGN KEY (`cartaodecredito_NUMEROCARTAO`)
    REFERENCES `dbweb2`.`cartaodecredito` (`NUMEROCARTAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compravoo_usuario1`
    FOREIGN KEY (`usuario_IDUSUARIO`)
    REFERENCES `dbweb2`.`usuario` (`IDUSUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compravoo_voo1`
    FOREIGN KEY (`voo_idvoo`)
    REFERENCES `dbweb2`.`voo` (`idvoo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compravoo_voo2`
    FOREIGN KEY (`voo_idvooVolta`)
    REFERENCES `dbweb2`.`voo` (`idvoo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;