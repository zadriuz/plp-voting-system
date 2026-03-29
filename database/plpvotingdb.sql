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
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcandidates`
--

LOCK TABLES `tblcandidates` WRITE;
/*!40000 ALTER TABLE `tblcandidates` DISABLE KEYS */;
INSERT INTO `tblcandidates` VALUES (1,'Candidate A','Test Party',1,'Dummy credentials for testing.','Dummy platform for testing.'),(2,'Candidate B','Test Party',1,'Dummy credentials for testing.','Dummy platform for testing.'),(3,'Abstain','Test Party2',1,'Try','Try'),(4,'Candidate C','Test Party 2',2,'try','test'),(5,'Candidate D','Test Party 2',2,'try','test'),(6,'Abstain','TestParty 2',2,'try','test'),(9,'Candidate E','',3,'try','try'),(10,'Candidate F','a',3,'try','try'),(11,'Abstain','T',3,'try','try');
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
INSERT INTO `tblvoters` VALUES ('12345678','testpass123',1,'BSIT','1','A'),('232','test',0,'BSIT','1','A');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblvotes`
--

LOCK TABLES `tblvotes` WRITE;
/*!40000 ALTER TABLE `tblvotes` DISABLE KEYS */;
INSERT INTO `tblvotes` VALUES (1,1),(3,3),(4,2),(5,6),(6,2),(7,6),(8,2),(9,5),(10,1),(11,5),(12,9);
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

-- Dump completed on 2026-03-29 22:01:24
