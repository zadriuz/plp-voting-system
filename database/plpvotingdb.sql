CREATE DATABASE  IF NOT EXISTS `plpvotingdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `plpvotingdb`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: plpvotingdb
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbladmins`
--

DROP TABLE IF EXISTS `tbladmins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbladmins` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbladmins`
--

LOCK TABLES `tbladmins` WRITE;
/*!40000 ALTER TABLE `tbladmins` DISABLE KEYS */;
INSERT INTO `tbladmins` VALUES (1,'admin_plp','masterpassword123'),(2,'admin','test');
/*!40000 ALTER TABLE `tbladmins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcandidates`
--

DROP TABLE IF EXISTS `tblcandidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcandidates` (
  `candidate_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `party_list` varchar(255) NOT NULL,
  `position_id` int DEFAULT NULL,
  `credentials` text,
  `plataporma` text,
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcandidates`
--

LOCK TABLES `tblcandidates` WRITE;
/*!40000 ALTER TABLE `tblcandidates` DISABLE KEYS */;
INSERT INTO `tblcandidates` VALUES (1,'Candidate A','United Students Alliance',1,'Dummy credentials for testing.','Dummy platform for testing.',NULL),(2,'Candidate B','Progressive Youth Coalition',1,'Dummy credentials for testing.','Dummy platform for testing.',NULL),(3,'Abstain','Test Party2',1,'Try','Try',NULL),(4,'Candidate C','United Students Alliance',2,'try','test',NULL),(5,'Candidate D','Progressive Youth Coalition',2,'try','test',NULL),(6,'Abstain','TestParty 2',2,'try','test',NULL),(9,'Candidate E','United Students Alliance',3,'try','try',NULL),(10,'Candidate F','Progressive Youth Coalition',3,'try','try',NULL),(11,'Abstain','T',3,'try','try',NULL),(12,'Candidate G','United Students Alliance',4,'hi','hello',NULL),(13,'Candidate H','Progressive Youth Coalition',4,'test','hi',NULL),(14,'Abstain','testParty3',4,'hi','test',NULL),(15,'Candidate I','United Students Alliance',5,'he','his',NULL),(16,'Candidate J','Independent',5,'she','her',NULL),(17,'Abstain','testParty3',5,'she','his',NULL),(18,'Candidate K','United Students Alliance',6,'hea','shea',NULL),(20,'Abstain','testParty7',6,'hia','shia',NULL),(25,'Candidate KL','Progressive Youth Coalition',6,'• Perfect Attendance','• Clean Air \n• Clean Water','imgCandidates\\plpIcon.png');
/*!40000 ALTER TABLE `tblcandidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblelectionstatus`
--

DROP TABLE IF EXISTS `tblelectionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblelectionstatus` (
  `status_id` int NOT NULL,
  `current_phase` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblelectionstatus`
--

LOCK TABLES `tblelectionstatus` WRITE;
/*!40000 ALTER TABLE `tblelectionstatus` DISABLE KEYS */;
INSERT INTO `tblelectionstatus` VALUES (1,'CLOSED');
/*!40000 ALTER TABLE `tblelectionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpartylists`
--

DROP TABLE IF EXISTS `tblpartylists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpartylists` (
  `party_name` varchar(100) NOT NULL,
  `party_description` text,
  PRIMARY KEY (`party_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpartylists`
--

LOCK TABLES `tblpartylists` WRITE;
/*!40000 ALTER TABLE `tblpartylists` DISABLE KEYS */;
INSERT INTO `tblpartylists` VALUES ('Independent','Independent candidates running on individual platforms without party affiliation.'),('Progressive Youth Coalition','Empowering students to create meaningful change and advance progressive initiatives for all.'),('United Students Alliance','Building a stronger, more inclusive university community through collaboration and innovation.');
/*!40000 ALTER TABLE `tblpartylists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpositions`
--

DROP TABLE IF EXISTS `tblpositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpositions` (
  `position_id` int NOT NULL AUTO_INCREMENT,
  `position_name` varchar(255) NOT NULL,
  `max_votes` int NOT NULL,
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `position_name` (`position_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpositions`
--

LOCK TABLES `tblpositions` WRITE;
/*!40000 ALTER TABLE `tblpositions` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblpositions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblvoters`
--

DROP TABLE IF EXISTS `tblvoters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblvoters` (
  `student_number` varchar(8) NOT NULL,
  `passcode` varchar(255) NOT NULL,
  `has_voted` tinyint(1) DEFAULT NULL,
  `course` varchar(10) NOT NULL,
  `year_level` varchar(1) NOT NULL,
  `section` varchar(255) NOT NULL,
  PRIMARY KEY (`student_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblvoters`
--

LOCK TABLES `tblvoters` WRITE;
/*!40000 ALTER TABLE `tblvoters` DISABLE KEYS */;
INSERT INTO `tblvoters` VALUES ('12345678','testpass123',0,'BSIT','1','A'),('232','test',0,'BSIT','1','A'),('25-00722','p@$nA81q',0,'BSIT','1','A'),('25-00723','JSuihsHv',0,'BSIT','1','E'),('25-00724','S)6(8ByY',0,'BSIT','1','A'),('25-00726','XMrQ3vWJ',0,'BSIT','1','E');
/*!40000 ALTER TABLE `tblvoters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblvotes`
--

DROP TABLE IF EXISTS `tblvotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblvotes` (
  `vote_id` int NOT NULL AUTO_INCREMENT,
  `candidate_id` int DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblvotes`
--

LOCK TABLES `tblvotes` WRITE;
/*!40000 ALTER TABLE `tblvotes` DISABLE KEYS */;
INSERT INTO `tblvotes` VALUES (1,1),(3,3),(4,2),(5,6),(6,2),(7,6),(8,2),(9,5),(10,1),(11,5),(12,9),(13,2),(14,5),(15,11),(16,2),(17,6),(18,9),(19,2),(20,6),(21,9),(22,2),(23,6),(24,9),(25,2),(26,5),(27,10),(28,2),(29,5),(30,10),(31,2),(32,4),(33,10),(34,2),(35,4),(36,11),(37,2),(38,4),(39,11),(40,2),(41,4),(42,11),(43,3),(44,5),(45,11),(46,3),(47,5),(48,11),(49,3),(50,5),(51,11),(52,2),(53,4),(54,10),(55,12),(56,17),(57,19),(58,1),(59,4),(60,10),(61,14),(62,15),(63,20),(64,3),(65,5),(66,10),(67,13),(68,16),(69,20),(70,2),(71,6),(72,11),(73,14),(74,15),(75,19),(76,3),(77,6),(78,10),(79,12),(80,16),(81,18),(82,1),(83,4),(84,11),(85,13),(86,16),(87,18);
/*!40000 ALTER TABLE `tblvotes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-05 18:38:31
