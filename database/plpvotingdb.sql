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
INSERT INTO `tblcandidates` VALUES (1,'Candidate A','United Students Alliance',1,'Dummy credentials for testing.','Dummy platform for testing.','imgCandidates\\WIN_20260408_13_31_12_Pro.jpg'),(2,'Joseph C. Ytac','Progressive Youth Coalition',1,'Dummy credentials for testing.','Dummy platform for testing.','imgCandidates\\WIN_20260408_13_31_01_Pro.jpg'),(3,'Abstain','Test Party2',1,'Try','Try',NULL),(4,'Candidate C','United Students Alliance',2,'try','test',NULL),(5,'Candidate D','Progressive Youth Coalition',2,'try','test',NULL),(6,'Abstain','TestParty 2',2,'try','test',NULL),(9,'Candidate E','United Students Alliance',3,'try','try',NULL),(10,'Candidate F','Progressive Youth Coalition',3,'try','try',NULL),(11,'Abstain','T',3,'try','try',NULL),(12,'Candidate G','United Students Alliance',4,'hi','hello',NULL),(13,'Candidate H','Progressive Youth Coalition',4,'test','hi',NULL),(14,'Abstain','testParty3',4,'hi','test',NULL),(15,'Candidate I','United Students Alliance',5,'he','his',NULL),(16,'Candidate J','Independent',5,'she','her',NULL),(17,'Abstain','testParty3',5,'she','his',NULL),(18,'Candidate K','United Students Alliance',6,'hea','shea',NULL),(20,'Abstain','testParty7',6,'hia','shia',NULL),(25,'Candidate KL','Progressive Youth Coalition',6,'• Perfect Attendance','• Clean Air \n• Clean Water','imgCandidates\\plpIcon.png');
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
INSERT INTO `tblelectionstatus` VALUES (1,'OPEN');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpositions`
--

LOCK TABLES `tblpositions` WRITE;
/*!40000 ALTER TABLE `tblpositions` DISABLE KEYS */;
INSERT INTO `tblpositions` VALUES (1,'President',1),(2,'Vice President',1),(3,'Secretary',1),(4,'Treasurer',1),(5,'Auditor',1),(6,'P.R.O',1);
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
INSERT INTO `tblvoters` VALUES ('12345678','testpass123',0,'BSIT','1','A'),('23-00212','6(FnYWsT',0,'BSIT','3','C'),('232','test',1,'BSIT','1','A'),('25-00694','bBa)m&M)',0,'BSIT','1','E'),('25-00703','s*X*VJS4',0,'BSIT','1','A'),('25-00705','sg&9wzE^',0,'BSIT','1','A'),('25-00710','63Q7QJl$',0,'BSIT','1','E'),('25-00722','p@$nA81q',1,'BSIT','1','A'),('25-00723','JSuihsHv',0,'BSIT','1','E'),('25-00724','S)6(8ByY',0,'BSIT','1','A'),('25-00726','XMrQ3vWJ',0,'BSIT','1','E');
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
  `vote_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblvotes`
--

LOCK TABLES `tblvotes` WRITE;
/*!40000 ALTER TABLE `tblvotes` DISABLE KEYS */;
INSERT INTO `tblvotes` VALUES (1,1,'8851ce091b2d15762905d105f5408b58421e01f2101b6e2be24160c0fe05d0b6'),(2,5,'cd4b34b72dcb3f2f9a47e8556765ae7ff6591c9a6dbddc05189cb381ede5a49d'),(3,10,'4879ca1ef24c971b7a03421b7762a982994bda332c5a195ffefa0dea85847820'),(4,12,'44291d2e165ba6dce4db0e9f4082aa6a1f3c57c57bbaa1e5ab6e35bc5ef4d7db'),(5,16,'5dc15434a833f4b2c0e025434cdfb4549110809a29df39ffc06ad0f868d6df9f'),(6,20,'dede4e7cece421bed5d33bee64684930d4a564df575a8dd3cabc360e38bf57a3'),(7,1,'42bb970e4d0b78528379c62762eafa2d20aa98d47ea6caaab3d0102def57fff5'),(8,5,'c7cdf15309b85c2157ef16c0708a593d0845866b45ca7b67f3af5c44dd4caeea'),(9,11,'80fb230e16680951d4c90e35c0b26d6600f280492f3276d597a80c61703562ae'),(10,12,'ec9fa8f0d777aab349dc1a32165401ffb83fdab56021e2c7a5321814c076c704'),(11,16,'b68b1e1dd3a975981d6af8561b70725cd29e4e49f2336ce1b5a0c5dd3b934ed3'),(12,25,'d640ff2661b9a38e25edadcbce6afbe70c34d1487bf1ab0cdc3100f1529ef6b9');
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

-- Dump completed on 2026-04-16 13:09:20
