-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.37-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para case_vsm
CREATE DATABASE IF NOT EXISTS `case_vsm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `case_vsm`;

-- Copiando estrutura para tabela case_vsm.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(200) NOT NULL,
  `rg` varchar(200) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `ativo` varchar(1) NOT NULL,
  `d_e_l_e_t_e` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela case_vsm.cliente: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`codigo`, `nome`, `cpf`, `rg`, `sexo`, `ativo`, `d_e_l_e_t_e`) VALUES
	(13, 'MARIA ', '111.111.111-11', '22.222.222-2', 'F', '1', NULL),
	(14, 'JOAO', '111.111.111-12', '11.111.111-1', 'M', '1', NULL),
	(15, 'JOSE', '111.111.113-33', '11.111.111-1', 'M', '1', NULL),
	(16, 'JOANA', '566.565.656-56', '65.656.565-6', 'F', '1', NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Copiando estrutura para tabela case_vsm.endereco
CREATE TABLE IF NOT EXISTS `endereco` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL DEFAULT '0',
  `endereco` varchar(255) NOT NULL DEFAULT '0',
  `bairro` varchar(50) NOT NULL DEFAULT '0',
  `cidade` varchar(50) NOT NULL DEFAULT '0',
  `cep` varchar(30) NOT NULL DEFAULT '0',
  `uf` varchar(2) NOT NULL DEFAULT '0',
  `codigo_cliente` int(11) NOT NULL DEFAULT '0',
  `d_e_l_e_t_e` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_endereco_cliente` (`codigo_cliente`),
  CONSTRAINT `FK_endereco_cliente` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela case_vsm.endereco: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` (`codigo`, `tipo`, `endereco`, `bairro`, `cidade`, `cep`, `uf`, `codigo_cliente`, `d_e_l_e_t_e`) VALUES
	(1, 'APARTAMENTO  13', 'RUA  PRINCIPAL ', 'APARTAMENTO  13', 'ASSIS', '11111-111', 'SP', 13, NULL),
	(2, 'CASA', 'RUA LATERAL ', 'CASA', 'TARUMA', '11111-111', 'SP', 14, NULL),
	(3, 'RURAL', 'FAZENDA TARUMA  S/N', 'RURAL', 'TARUMÃ', '00000-000', 'SP', 14, NULL),
	(4, 'CASA', 'RUA LATERAL', 'CASA', 'ASSI', '20000-000', 'SP', 15, NULL),
	(5, 'APARTAMENTO 15', 'RUA LATERA 2', 'APARTAMENTO 15', 'ASSIS', '19800-000', 'SP', 16, NULL);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;

-- Copiando estrutura para tabela case_vsm.telefone
CREATE TABLE IF NOT EXISTS `telefone` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL DEFAULT '0',
  `numero` varchar(50) NOT NULL DEFAULT '0',
  `codigo_cliente` int(11) NOT NULL DEFAULT '0',
  `d_e_l_e_t_e` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_telefone_cliente` (`codigo_cliente`),
  CONSTRAINT `FK_telefone_cliente` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela case_vsm.telefone: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` (`codigo`, `tipo`, `numero`, `codigo_cliente`, `d_e_l_e_t_e`) VALUES
	(1, 'RESIDENCIAL', '(18) 0000-0000', 13, NULL),
	(2, 'CELULAR', '(18) 000-0000', 13, NULL),
	(3, 'CELULAR', '(18) 0000-0000', 16, NULL),
	(4, 'FAX', '(18) 3333-3333', 15, NULL),
	(5, 'fixo', '665454', 14, NULL);
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;

-- Copiando estrutura para tabela case_vsm.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `ativo` varchar(1) NOT NULL,
  `nivel` varchar(1) NOT NULL COMMENT '1 - admin / 2 usuario',
  `d_e_l_e_t_e` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela case_vsm.usuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`codigo`, `usuario`, `senha`, `nome`, `ativo`, `nivel`, `d_e_l_e_t_e`) VALUES
	(1, 'admin', 'YWRtaW4=', 'Administrador', '1', '1', NULL),
	(2, 'usuario', 'dXN1YXJpbw==', 'Usuário', '1', '2', NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
