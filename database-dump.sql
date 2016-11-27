CREATE DATABASE  IF NOT EXISTS `db_estudo_java` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_estudo_java`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: db_estudo_java
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `tb_pessoa`
--

DROP TABLE IF EXISTS `tb_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT COMMENT 'CÓDIGO DA PESSOA',
  `nm_pessoa` varchar(70) NOT NULL COMMENT 'NOME DA PESSOA',
  `fl_sexo` char(1) NOT NULL COMMENT 'INFORMAR M OU F',
  `dt_cadastro` datetime NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
  `ds_email` varchar(80) NOT NULL COMMENT 'EMAIL DA PESSOA',
  `ds_endereco` varchar(200) NOT NULL COMMENT 'DESCRIÇÃO DO ENDEREÇO',
  `fl_origemCadastro` char(1) NOT NULL COMMENT 'ORIGEM DO CADASTRO (I) = INPUT OU (X) = XML',
  `id_usuario_cadastro` int(11) NOT NULL COMMENT 'USUÁRIO LOGADO QUE CADASTROU A PESSOA',
  PRIMARY KEY (`id_pessoa`),
  KEY `id_usuario_cadastro` (`id_usuario_cadastro`),
  CONSTRAINT `tb_pessoa_ibfk_1` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pessoa`
--

LOCK TABLES `tb_pessoa` WRITE;
/*!40000 ALTER TABLE `tb_pessoa` DISABLE KEYS */;
INSERT INTO `tb_pessoa` VALUES (1,'Daniela funfa pliiiisss','F','2016-11-27 13:41:19','k@k.com','rua x numero y','I',1),(4,'Kai Correia Ferreira','M','2016-11-27 15:52:36','KaiCorreiaFerreira@jourrapide.com','Praça Plácido de Castro, 1783','X',1),(5,'Camila Sousa Cavalcanti','F','2016-11-27 15:52:36','CamilaSousaCavalcanti@armyspy.com','Rua Francisco Vilani Bicudo, 167','X',1),(6,'Clara Santos Azevedo','F','2016-11-27 15:52:36','ClaraSantosAzevedo@rhyta.com','Rua Jorge Nunes, 1677','X',1),(7,'Lavinia Ferreira Cavalcanti','F','2016-11-27 15:52:36','LaviniaFerreiraCavalcanti@dayrep.com','Rua Doutor Luiz Osvaldo Aranha, 282','X',1),(8,'Luiza Martins Pereira','F','2016-11-27 15:52:36','LuizaMartinsPereira@dayrep.com','Ponta Grossa-PR,Rua Padre Nóbrega, 598','X',1),(9,'Cícero Ednilson','M','2016-11-27 15:52:36','ciceroednilson@gmail.com','São Paulo, São Paulo, Faria lima N°10','X',1),(10,'Vitór Cardoso Silva','M','2016-11-27 15:52:36','RafaelLimaMelo@dayrep.com','Beco dos Chagas, 1264','X',1),(11,'Kai Correia Ferreira','M','2016-11-27 15:52:36','KaiCorreiaFerreira@jourrapide.com','Praça Plácido de Castro, 1783','X',1),(12,'Camila Sousa Cavalcanti','F','2016-11-27 15:52:36','CamilaSousaCavalcanti@armyspy.com','Rua Francisco Vilani Bicudo, 167','X',1),(15,'Luiza Martins Pereira','F','2016-11-27 15:52:36','LuizaMartinsPereira@dayrep.com','Ponta Grossa-PR,Rua Padre Nóbrega, 598','X',1),(16,'Cícero Ednilson','M','2016-11-27 15:52:36','ciceroednilson@gmail.com','São Paulo, São Paulo, Faria lima N°10','X',1),(17,'Lucas  Silva','M','2016-11-27 15:52:36','RafaelLimaMelo@dayrep.com','Beco dos Chagas, 1264','X',1),(18,'Kai Correia Ferreira','M','2016-11-27 15:52:36','KaiCorreiaFerreira@jourrapide.com','Praça Plácido de Castro, 1783','X',1),(19,'Camila Sousa Cavalcanti','F','2016-11-27 15:52:36','CamilaSousaCavalcanti@armyspy.com','Rua Francisco Vilani Bicudo, 167','X',1),(20,'Clara Santos Azevedo','F','2016-11-27 15:52:36','ClaraSantosAzevedo@rhyta.com','Rua Jorge Nunes, 1677','X',1),(21,'Lavinia Ferreira Cavalcanti','F','2016-11-27 15:52:36','LaviniaFerreiraCavalcanti@dayrep.com','Rua Doutor Luiz Osvaldo Aranha, 282','X',1),(22,'Luiza Martins Pereira','F','2016-11-27 15:52:36','LuizaMartinsPereira@dayrep.com','Ponta Grossa-PR,Rua Padre Nóbrega, 598','X',1),(23,'Cícero Ednilson','M','2016-11-27 15:52:36','ciceroednilson@gmail.com','São Paulo, São Paulo, Faria lima N°10','X',1),(24,'Miguel Araujo Melo','M','2016-11-27 15:52:36','Miguel0002@armyspy.com','Travessa Quatro, 1540','X',1),(25,'Kai Correia Ferreira','M','2016-11-27 15:52:36','KaiCorreiaFerreira@jourrapide.com','Praça Plácido de Castro, 1783','X',1),(26,'Camila Sousa Cavalcanti','F','2016-11-27 15:52:36','CamilaSousaCavalcanti@armyspy.com','Rua Francisco Vilani Bicudo, 167','X',1),(27,'Clara Santos Azevedo','F','2016-11-27 15:52:36','ClaraSantosAzevedo@rhyta.com','Rua Jorge Nunes, 1677','X',1),(28,'Lavinia Ferreira Cavalcanti','F','2016-11-27 15:52:36','LaviniaFerreiraCavalcanti@dayrep.com','Rua Doutor Luiz Osvaldo Aranha, 282','X',1),(29,'Luiza Martins Pereira','F','2016-11-27 15:52:36','LuizaMartinsPereira@dayrep.com','Ponta Grossa-PR,Rua Padre Nóbrega, 598','X',1),(30,'Daniela f','F','2016-11-27 15:54:04','dani.sf@live.com','Endereco','X',1),(31,'Professor Dagostini','M','2016-11-27 15:54:04','dagostini@univel.br','Endereco','X',1);
/*!40000 ALTER TABLE `tb_pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT COMMENT 'CÓDIGO DO USUÁRIO',
  `ds_login` varchar(30) NOT NULL COMMENT 'LOGIN DO USUÁRIO PARA ACESSO AO SISTEMA',
  `ds_senha` varchar(30) NOT NULL COMMENT 'SENHA DO USUÁRIO PARA ACESSO AO SISTEMA',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'admin','123456');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-27 21:30:17
