-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: counsellor_splex
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `counsellor_tbl`
--

DROP TABLE IF EXISTS `counsellor_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counsellor_tbl` (
  `counsellor_id` int NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phno` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`counsellor_id`),
  UNIQUE KEY `UK1mqc1i5ja6eb0j9vdah3tbk58` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counsellor_tbl`
--

LOCK TABLES `counsellor_tbl` WRITE;
/*!40000 ALTER TABLE `counsellor_tbl` DISABLE KEYS */;
INSERT INTO `counsellor_tbl` VALUES (13,'2026-10-05','krishna.webart@gmail.com','Krishna Dandu','1234567789','$2a$10$z9SDWuxVMZ/uuQcq9epnReKuLlmUXfmH9RhxCueZCQoCerygn9XWO','2026-10-05'),(14,'2026-10-05','splexBiz@gmail.com','Splex Innovation ','1234567789','$2a$10$XXmnVNCjMMJ1WoJ7Oe5GjOuqwKoWex1yt99QokRH5yGwqLUYtiN1O','2026-10-05');
/*!40000 ALTER TABLE `counsellor_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-13 21:15:05
