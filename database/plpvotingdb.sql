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
INSERT INTO `tblcandidates` VALUES (1,'Maria Santos','United Students Alliance',1,'BS Computer Science, Dean\'s Lister, Former Class President','Focus on digital infrastructure improvement, enhanced student welfare programs, and transparent governance. Committed to making education accessible for all students.','SystemIcons\\independentIcon.png'),(2,'Isabella Cruz','Progressive Youth Coalition',1,'BS Political Science, Student Activist, Youth Leader','Champion student rights, promote environmental sustainability on campus, and advocate for more student-centered policies and programs.','SystemIcons\\independentIcon.png'),(3,'Abstain','Test Party2',1,'Try','Try',NULL),(4,'Juan Dela Cruz','United Students Alliance',2,'BS Business Administration, Student Council Member, Debate Team Captain','Strengthen student organizations, promote academic excellence through tutoring programs, and establish better communication channels between students and administration.','SystemIcons\\independentIcon.png'),(5,'Ramon Gonzales','Progressive Youth Coalition',2,'BS Engineering, Innovation Hub Leader','Support innovation and entrepreneurship, develop mentorship programs, and bridge the gap between students and industry professionals.','SystemIcons\\independentIcon.png'),(6,'Abstain','TestParty 2',2,'try','test',NULL),(9,'Anna Reyes','United Students Alliance',3,'BS Psychology, Former Secretary of Student Council','Digitize student records and documentation, improve transparency in council proceedings, and maintain accurate records of all student activities.','SystemIcons\\independentIcon.png'),(10,'Elena Ramos','Progressive Youth Coalition',3,'BS Information Technology, Tech Society Officer','Modernize documentation systems, create accessible digital archives, and ensure efficient communication across all student organizations.','SystemIcons\\independentIcon.png'),(11,'Abstain','T',3,'try','try',NULL),(12,'Carlos Garcia','United Students Alliance',4,'BS Accounting, Finance Club President','Ensure financial transparency, maximize student budget allocation, and implement cost-effective programs that benefit the entire student body.','SystemIcons\\independentIcon.png'),(13,'Diego Fernandez','Progressive Youth Coalition',4,'BS Economics, Investment Club President','Optimize budget allocation for maximum impact, explore alternative funding sources, and ensure equitable distribution of resources.','SystemIcons\\independentIcon.png'),(14,'Abstain','testParty3',4,'hi','test',NULL),(15,'Sofia Martinez','United Students Alliance',5,'BS Accountancy, Cum Laude Graduate','Conduct regular audits of student funds, ensure compliance with financial regulations, and promote accountability in all financial transactions.','SystemIcons\\independentIcon.png'),(16,'Luis Mendoza','Independent',5,'BS Journalism, Campus Newsletter Editor','Create engaging content that resonates with students, establish better feedback mechanisms, and ensure transparent communication of all initiatives.','SystemIcons\\independentIcon.png'),(17,'Abstain','testParty3',5,'she','his',NULL),(18,'Miguel Torres','United Students Alliance',6,'BS Mass Communication, Campus Radio Host','Enhance student engagement through social media, improve information dissemination, and create platforms for student voices to be heard.','SystemIcons\\independentIcon.png'),(20,'Abstain','testParty7',6,'hia','shia',NULL),(25,'Gabriela Santos','Progressive Youth Coalition',6,'BS Accounting, Internal Audit Intern','Implement rigorous financial oversight, promote ethical financial practices, and ensure every peso is accounted for and used properly.','SystemIcons\\independentIcon.png');
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
INSERT INTO `tblvoters` VALUES ('23-00212','6(FnYWsT',0,'BSIT','3','C'),('23-00729','V4XVY)^4',1,'BSIT','1','E'),('232','test',1,'BSIT','1','A'),('25-00134','qsa%#@hu',1,'BSHM','3','2'),('25-00694','bBa)m&M)',0,'BSIT','2','E'),('25-00703','s*X*VJS4',0,'BSIT','2','A'),('25-00705','sg&9wzE^',0,'BSIT','1','A'),('25-00710','63Q7QJl$',0,'BSIT','1','E'),('25-00722','1g!iccSp',1,'BSIT','2','E'),('25-00723','uwzqtLzO',1,'BSIT','1','E'),('25-00724','S)6(8ByY',0,'BSIT','1','A'),('25-00726','XMrQ3vWJ',0,'BSIT','1','E'),('25-00727','3i&hHgQ7',1,'BSIT','1','E'),('25-00730','oyYADeLj',0,'BSIT','1','E'),('25-00784','AGh*KXPQ',0,'BSIT','1','E'),('25-01336','fYuOEMu6',1,'BSIT','1','E');
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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblvotes`
--

LOCK TABLES `tblvotes` WRITE;
/*!40000 ALTER TABLE `tblvotes` DISABLE KEYS */;
INSERT INTO `tblvotes` VALUES (1,1,'8851ce091b2d15762905d105f5408b58421e01f2101b6e2be24160c0fe05d0b6'),(2,5,'cd4b34b72dcb3f2f9a47e8556765ae7ff6591c9a6dbddc05189cb381ede5a49d'),(3,10,'4879ca1ef24c971b7a03421b7762a982994bda332c5a195ffefa0dea85847820'),(4,12,'44291d2e165ba6dce4db0e9f4082aa6a1f3c57c57bbaa1e5ab6e35bc5ef4d7db'),(5,16,'5dc15434a833f4b2c0e025434cdfb4549110809a29df39ffc06ad0f868d6df9f'),(6,20,'dede4e7cece421bed5d33bee64684930d4a564df575a8dd3cabc360e38bf57a3'),(7,1,'42bb970e4d0b78528379c62762eafa2d20aa98d47ea6caaab3d0102def57fff5'),(8,5,'c7cdf15309b85c2157ef16c0708a593d0845866b45ca7b67f3af5c44dd4caeea'),(9,11,'80fb230e16680951d4c90e35c0b26d6600f280492f3276d597a80c61703562ae'),(10,12,'ec9fa8f0d777aab349dc1a32165401ffb83fdab56021e2c7a5321814c076c704'),(11,16,'b68b1e1dd3a975981d6af8561b70725cd29e4e49f2336ce1b5a0c5dd3b934ed3'),(12,25,'d640ff2661b9a38e25edadcbce6afbe70c34d1487bf1ab0cdc3100f1529ef6b9'),(13,3,'a1b3f1151d8370cccca8d50ee3f95a310d1f1b2ef2786cd322ae7566cc25a414'),(14,5,'4388e66334c2bdaee323507e6e7301e8d15087f50461850f6006edaf99071cf2'),(15,10,'8b7b38ede4587e53f1af55dab7badfb34cca9c5e9f23ac336feb29f9472786c5'),(16,13,'0750fce1c4d4b0e85bd52271584428ef9278a510ae75ade80e0fbb7be038b334'),(17,16,'077108b1479bd75fd8cfb869acb719632b48b368a1b04f22dd8e4c3255584cf6'),(18,18,'03b7e7e9ef2ad021763ca257935411af13a45200a494de056603585773fcebcb'),(19,1,'1d76d43e2336726473abd38328b2d307157372bd69ade18ac716481aabb04df8'),(20,4,'ae14b607d2510126c941b7311f4158978d570da527f301579fe17e9cb8d05792'),(21,9,'12855af51e7c5df6704dc4a4a30a3b31e8d91de1fb1b05e6287a8fe212ad8090'),(22,14,'9bb9971b83acbf6ed01eabb980a19e8cb86eb64ae12152a078f674ebf37ffcea'),(23,16,'59e9de5d25f61777bccf8963fd99ec190212aba0ed557b3bda288fdb3e381f38'),(24,18,'c0f889283ffdb7951b0f7b59117f8d2ed964f074187da72f782bebd0f754a8c0'),(25,3,'6b19034250384b93c3f01f9fb8e805ce52a516e219d74d8ceb402d80ab8da1b0'),(26,4,'086ed5e108b1456bfbdd35429af07c9ea2f986f4fc4bcab88b85766d9a992112'),(27,10,'c8665d26a1b5fc4f8c059d73691fcaf734f99a71974fbd9647d19049909d9b3a'),(28,13,'ed227bed8dd36d02c99c3b565ee9dd922fe67e9ea28e1097acd758789ec31a2d'),(29,17,'5884cd2ee36c25745744e02e2957aec2f84762f2e6c46dbca0c1e0e84d36b5de'),(30,25,'4dbacd9d9ca4372ca9d779533c9df926549e406eac4d86631e53f10a491a3afd'),(31,1,'98581c8ff6c9fba8cfd6b36e5db4b069428c16ba859fd4fa3790fa4a4dfae9ea'),(32,6,'77ffeec5698f9ef858fe7a22cf4c8904f84f5435c349d324e90a56178b678e5c'),(33,9,'a9933f3c8a495e14459ce470d8814ab9566b0f2952512b49734504e6460595a1'),(34,13,'e198d33ebc8076a6d02940c8d33560b2dd8328cbddc388070cdbb660a75402c3'),(35,15,'e3e8b61a5d2225ce33d625129973789547037af7c6f5e4852d1eac84d7a3de34'),(36,25,'626aa0fdd084d73663b21f9c1bbb7ce19454d1745d51a5d779dcda1d37444f2f'),(37,1,'68f08f3bd2dd29a0866d7ac5bc5b6123780c7bfa944787a6b53a8b1de3c0121f'),(38,4,'c492d73ac9ec042efacd565bf6bf1c6a2db5271f9e47cc12aaf9386e79827d5a'),(39,10,'3c6614c91c5c30685e1fd86d0fe7e07be76106e6e2555d4924b339402d97cada'),(40,13,'aae9ceb0c5912554a381955cdce5d83c4d88874c28d9f9ed53a69ca0bae5651e'),(41,15,'d5328bbb60d1681f6dce4b584ff89434514582ee81f98183750819ead952377e'),(42,25,'7cb030fe67ce1d4e629dd6557feea080e665405cd2ea472364afd6d7cfe50c67'),(43,1,'5b1d23a810cfa88d2ae78e33e67d2c39b1bcb2b72d02d2bea428e7d0a1ef0975'),(44,4,'3289089b18a263a2beeb8a2efb53d3dc2c390991b9e10f317267863e1b2f4221'),(45,10,'1d9dfe5705b035fe64472b8cd1d5504a66e1bbe73fec4ffb92976c72c65abde8'),(46,13,'dad1166974d47b4499e052a461fd6940b28b720be97ece74cf0a19d241af3b22'),(47,15,'8e594531151dd40dbdf4d23f5ccd4c21001de9718c80475db88a790a51e21a6b'),(48,25,'2007386c519fd3c0571b9e1eb12666965a8b90a4775aaed78d13a77e16f570c4'),(49,2,'3f79083578f570cf1ae3306ecf32c87aee827f2b742f141d0b27eba20b5b717b'),(50,4,'3ac3404a6d6dd822b8d0e4fefb18ee81c8dd04edaaf5448d1a0ddc201598f382'),(51,10,'b6abd8cb712febf7836b37b740e51510549efaaed01265bcad63f24948dade5e'),(52,13,'97f4c2e66538c6b3723750bffed825ab99e62077dbf93d9f8c8ac0acbbbdc4cb'),(53,15,'d05b804365f6ee10dad8a4a3853932f9ec7d8b4ad5d1cb83edb9da7acfce0bdd'),(54,25,'548e47464bde662119ff8ff87eca1c3003a7244361f4a8b76dbac51c02c6e237'),(55,1,'40bec9bf03f69a786ae3be477173ea2cfaf0bc638ecc91dd0af175515ebd5945'),(56,4,'5047056d83bf97af862caa0b46e6432316112007219debae2fe0b24dd3615d61'),(57,10,'ded5d45c56e61f9e0fe50dac6f4008ffbadb2f4b4ae3d50646927c9795f18c9a'),(58,13,'58acc967891bcb7e31249e48661ca18d9d97c0b8792c88c38ba516b6293ab5ca'),(59,15,'33c73ed028b66cf67e48f03fd44a75d1ec7f56d2eba0ae088aabcc8f52ea2c40'),(60,25,'6aded56511cb6d3b13624e9a996323e6339e85654eb4484feb03137bc32ae1eb');
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

-- Dump completed on 2026-05-05 15:27:31
