-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jmusiclibDB
-- ------------------------------------------------------
-- Server version	5.5.43-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jmusiclibDB
-- ------------------------------------------------------
-- Server version	5.5.43-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion` (
  `tituloDisco` varchar(150) DEFAULT NULL,
  `idCancion` int(11) NOT NULL AUTO_INCREMENT,
  `tituloCancion` varchar(150) NOT NULL,
  `track` int(3) NOT NULL,
  `ruta` varchar(150) NOT NULL,
  PRIMARY KEY (`idCancion`),
  UNIQUE KEY `tituloCancion` (`tituloCancion`),
  KEY `tituloDisco` (`tituloDisco`),
  CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`tituloDisco`) REFERENCES `disco` (`tituloDisco`)
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` VALUES ('World Of Warcraft Mists Of Pandaria',2,'Why Do We Fight?',2,'/home/juanjo/Música/Azeroth Music - Blizzard/World Of Warcraft Mists Of Pandaria/02 Why Do We Fight.mp3'),('World Of Warcraft Mists Of Pandaria',3,'The Wandering Isle',3,'/home/juanjo/Música/Azeroth Music - Blizzard/World Of Warcraft Mists Of Pandaria/03 The Wandering Isle.mp3'),('Blood Of The Saints (Limited Edition)',4,'Opening: Agnus Dei',1,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/01 - Opening_ Agnus Dei.mp3'),('Blood Of The Saints (Limited Edition)',5,'Sanctified With Dynamite',2,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/02 - Sanctified With Dynamite.mp3'),('Blood Of The Saints (Limited Edition)',6,'We Drink Your Blood',3,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/03 - We Drink Your Blood.mp3'),('Blood Of The Saints (Limited Edition)',7,'Murder At Midnight',4,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/04 - Murder At Midnight.mp3'),('Blood Of The Saints (Limited Edition)',8,'All We Need Is Blood',5,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/05 - All We Need Is Blood.mp3'),('Blood Of The Saints (Limited Edition)',9,'Son Of A Wolf',7,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/07 - Son Of A Wolf.mp3'),('Blood Of The Saints (Limited Edition)',10,'Night Of The Werewolves',8,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/08 - Night Of The Werewolves.mp3'),('Blood Of The Saints (Limited Edition)',11,'Phantom Of The Funeral',9,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/09 - Phantom Of The Funeral.mp3'),('Blood Of The Saints (Limited Edition)',12,'Die, Die, Crucified',10,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/10 - Die, Die, Crucified.mp3'),('Blood Of The Saints (Limited Edition)',13,'Ira Sancti (When The Saints Are Going Wild)',11,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/11 - Ira Sancti (When The Saints Are Going Wild).mp3'),('Blood Of The Saints (Limited Edition)',14,'Raise Your Fist, Evangelist (Orchestral Version)',12,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/12 - Raise Your Fist, Evangelist (Orchestral Version).mp3'),('Blood Of The Saints (Limited Edition)',15,'In Blood We Trust (Orchestral Version)',13,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/13 - In Blood We Trust (Orchestral Version).mp3'),('Blood Of The Saints (Limited Edition)',16,'Sanctified With Dynamite (Orchestral Version)',14,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/14 - Sanctified With Dynamite (Orchestral Version).mp3'),('Blood Of The Saints (Limited Edition)',17,'Ira Sancti (When The Saints Are Going Wild) (Orchestral Version)',15,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/15 - Ira Sancti (When The Saints Are Going Wild) (Orchestral Version).mp3'),('Blood Of The Saints (Limited Edition)',18,'Moscow After Dark (Orchestral Version)',16,'/home/juanjo/Música/Powerwolf/Blood Of The Saints (Limited Edition)/16 - Moscow After Dark (Orchestral Version).mp3'),('Lupus Dei',19,'Lupus Daemonis (Intro)',1,'/home/juanjo/Música/Powerwolf/Lupus Dei/01 - Lupus Daemonis (Intro).mp3'),('Lupus Dei',20,'We Take It From The Living',2,'/home/juanjo/Música/Powerwolf/Lupus Dei/02 - We Take It From The Living.mp3'),('Lupus Dei',21,'Prayer In The Dark',3,'/home/juanjo/Música/Powerwolf/Lupus Dei/03 - Prayer In The Dark.mp3'),('Lupus Dei',22,'Saturday Satan',4,'/home/juanjo/Música/Powerwolf/Lupus Dei/04 - Saturday Satan.mp3'),('Lupus Dei',23,'In Blood We Trust',5,'/home/juanjo/Música/Powerwolf/Lupus Dei/05 - In Blood We Trust.mp3'),('Lupus Dei',24,'Behind The Leathermask',6,'/home/juanjo/Música/Powerwolf/Lupus Dei/06 - Behind The Leathermask.mp3'),('Lupus Dei',25,'When The Moon Shines Red',8,'/home/juanjo/Música/Powerwolf/Lupus Dei/08 - When The Moon Shines Red.mp3'),('Lupus Dei',26,'Mother Mary Is A Bird Of Prey',9,'/home/juanjo/Música/Powerwolf/Lupus Dei/09 - Mother Mary Is A Bird Of Prey.mp3'),('Lupus Dei',27,'Tiger Of Sabrod',10,'/home/juanjo/Música/Powerwolf/Lupus Dei/10 - Tiger Of Sabrod.mp3'),('Lupus Dei',28,'Lupus Dei',11,'/home/juanjo/Música/Powerwolf/Lupus Dei/11 - Lupus Dei.mp3'),('Return In Bloodred',29,'Mr. Sinister',1,'/home/juanjo/Música/Powerwolf/Return In Bloodred/01 - Mr. Sinister.mp3'),('Return In Bloodred',30,'We Came To Take Your Souls',2,'/home/juanjo/Música/Powerwolf/Return In Bloodred/02 - We Came To Take Your Souls.mp3'),('Return In Bloodred',31,'Kiss Of The Cobra King',3,'/home/juanjo/Música/Powerwolf/Return In Bloodred/03 - Kiss Of The Cobra King.mp3'),('Return In Bloodred',32,'Black Mass Hysteria',4,'/home/juanjo/Música/Powerwolf/Return In Bloodred/04 - Black Mass Hysteria.mp3'),('Return In Bloodred',33,'Demons And Diamonds',5,'/home/juanjo/Música/Powerwolf/Return In Bloodred/05 - Demons And Diamonds.mp3'),('Return In Bloodred',34,'Montecore',6,'/home/juanjo/Música/Powerwolf/Return In Bloodred/06 - Montecore.mp3'),('Return In Bloodred',35,'The Evil Made Me Do It',7,'/home/juanjo/Música/Powerwolf/Return In Bloodred/07 - The Evil Made Me Do It.mp3'),('Return In Bloodred',36,'Lucifer In Starlight',8,'/home/juanjo/Música/Powerwolf/Return In Bloodred/08 - Lucifer In Starlight.mp3'),('Return In Bloodred',37,'Son Of The Morning Star',9,'/home/juanjo/Música/Powerwolf/Return In Bloodred/09 - Son Of The Morning Star.mp3'),('Invincible',38,'To Glory',21,'/home/juanjo/Música/Thomas Bergersen/Two Steps from Hell/Invincible/Two Steps From Hell - To Glory.mp3'),('Invincible',39,'Heart of Courage',2,'/home/juanjo/Música/Thomas Bergersen/Two Steps from Hell/Invincible/Two Steps from Hell - Heart of Courage.mp3'),('Beyond The Underworld',40,'Aliens',1,'/home/juanjo/Música/VALHALLA/Beyond The Underworld/Valhalla - 01 - Aliens.mp3'),('Beyond The Underworld',41,'Rohirrim',2,'/home/juanjo/Música/VALHALLA/Beyond The Underworld/Valhalla - 02 - Rohirrim.mp3'),('Beyond The Underworld',416,'Eclipse',3,'/home/juanjo/Música/VALHALLA/Beyond The Underworld/Valhalla - 03 - Eclipse.mp3'),('World Of Warcraft Mists Of Pandaria',667,'Heart Of Pandaria',1,'/home/juanjo/Música/Azeroth Music - Blizzard/World Of Warcraft Mists Of Pandaria/01 Heart Of Pandaria.mp3');
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `idEmpleo` int(11) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fechaEmpleo` date DEFAULT NULL,
  KEY `idEmpleo` (`idEmpleo`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`idEmpleo`) REFERENCES `empleo` (`idEmpleo`) ON DELETE CASCADE,
  CONSTRAINT `contrato_ibfk_2` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`) ON DELETE CASCADE,
  CONSTRAINT `contrato_ibfk_3` FOREIGN KEY (`idUsuario`) REFERENCES `demandante` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato_test`
--

DROP TABLE IF EXISTS `contrato_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato_test` (
  `idEmpleo` int(11) DEFAULT NULL,
  `idEmpresa` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fechaEmpleo` date DEFAULT NULL,
  KEY `idEmpleo` (`idEmpleo`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `contrato_test_ibfk_1` FOREIGN KEY (`idEmpleo`) REFERENCES `empleo_test` (`idEmpleo`) ON DELETE CASCADE,
  CONSTRAINT `contrato_test_ibfk_2` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa_test` (`idEmpresa`) ON DELETE CASCADE,
  CONSTRAINT `contrato_test_ibfk_3` FOREIGN KEY (`idUsuario`) REFERENCES `demandante_test` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato_test`
--

LOCK TABLES `contrato_test` WRITE;
/*!40000 ALTER TABLE `contrato_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandante`
--

DROP TABLE IF EXISTS `demandante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demandante` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `dniUsuario` varchar(9) DEFAULT NULL,
  `nomUsuario` varchar(100) DEFAULT NULL,
  `apellUsuario` varchar(100) DEFAULT NULL,
  `direcUsuario` varchar(100) DEFAULT NULL,
  `telfUsuario` varchar(14) DEFAULT NULL,
  `emailUsuario` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandante`
--

LOCK TABLES `demandante` WRITE;
/*!40000 ALTER TABLE `demandante` DISABLE KEYS */;
/*!40000 ALTER TABLE `demandante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandante_test`
--

DROP TABLE IF EXISTS `demandante_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demandante_test` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `dniUsuario` varchar(9) DEFAULT NULL,
  `nomUsuario` varchar(100) DEFAULT NULL,
  `apellUsuario` varchar(100) DEFAULT NULL,
  `direcUsuario` varchar(100) DEFAULT NULL,
  `telfUsuario` varchar(14) DEFAULT NULL,
  `emailUsuario` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandante_test`
--

LOCK TABLES `demandante_test` WRITE;
/*!40000 ALTER TABLE `demandante_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `demandante_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disco`
--

DROP TABLE IF EXISTS `disco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disco` (
  `tituloDisco` varchar(150) NOT NULL DEFAULT '',
  `fechaLanza` int(4) DEFAULT NULL,
  `nombreGrupo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`tituloDisco`),
  KEY `nombreGrupo` (`nombreGrupo`),
  CONSTRAINT `disco_ibfk_1` FOREIGN KEY (`nombreGrupo`) REFERENCES `grupo` (`nombreGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disco`
--

LOCK TABLES `disco` WRITE;
/*!40000 ALTER TABLE `disco` DISABLE KEYS */;
INSERT INTO `disco` VALUES ('Beyond The Underworld',2000,'VALHALLA'),('Blood Of The Saints (Limited Edition)',2011,'Powerwolf'),('Invincible',2010,'Thomas Bergersen/Two Steps from Hell'),('Lupus Dei',2007,'Powerwolf'),('Remix',2003,'DarkeSword'),('Return In Bloodred',2005,'Powerwolf'),('World Of Warcraft Mists Of Pandaria',2012,'Azeroth Music - Blizzard');
/*!40000 ALTER TABLE `disco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleo`
--

DROP TABLE IF EXISTS `empleo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleo` (
  `idEmpleo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fechaEmpleo` date DEFAULT NULL,
  `sector` varchar(150) DEFAULT NULL,
  `vacantes` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmpleo`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `empleo_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`) ON DELETE CASCADE,
  CONSTRAINT `empleo_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `demandante` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleo`
--

LOCK TABLES `empleo` WRITE;
/*!40000 ALTER TABLE `empleo` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleo_test`
--

DROP TABLE IF EXISTS `empleo_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleo_test` (
  `idEmpleo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpresa` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fechaEmpleo` date DEFAULT NULL,
  `sector` varchar(150) DEFAULT NULL,
  `vacantes` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEmpleo`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `empleo_test_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa_test` (`idEmpresa`) ON DELETE CASCADE,
  CONSTRAINT `empleo_test_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `demandante_test` (`idUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleo_test`
--

LOCK TABLES `empleo_test` WRITE;
/*!40000 ALTER TABLE `empleo_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleo_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `cifEmpresa` varchar(9) DEFAULT NULL,
  `nomEmpresa` varchar(150) DEFAULT NULL,
  `resEmpresa` varchar(150) DEFAULT NULL,
  `direcEmpersa` varchar(150) DEFAULT NULL,
  `telfEmpresa` varchar(14) DEFAULT NULL,
  `emailEmpresa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa_test`
--

DROP TABLE IF EXISTS `empresa_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa_test` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `cifEmpresa` varchar(9) DEFAULT NULL,
  `nomEmpresa` varchar(150) DEFAULT NULL,
  `resEmpresa` varchar(150) DEFAULT NULL,
  `direcEmpersa` varchar(150) DEFAULT NULL,
  `telfEmpresa` varchar(14) DEFAULT NULL,
  `emailEmpresa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa_test`
--

LOCK TABLES `empresa_test` WRITE;
/*!40000 ALTER TABLE `empresa_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `nombreGrupo` varchar(150) NOT NULL,
  PRIMARY KEY (`nombreGrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES ('Azeroth Music - Blizzard'),('DarkeSword'),('Linkin Park'),('Powerwolf'),('Thomas Bergersen/Two Steps from Hell'),('VALHALLA');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-20 15:11:20
