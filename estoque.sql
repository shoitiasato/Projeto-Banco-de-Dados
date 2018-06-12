-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 11-Jun-2018 às 20:39
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `estoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--
CREATE DATABASE estoque;

USE estoque;

CREATE TABLE IF NOT EXISTS `categoria` (
  `cat_cod` int(11) NOT NULL AUTO_INCREMENT,
  `cat_nome` varchar(30) NOT NULL,
  PRIMARY KEY (`cat_cod`),
  UNIQUE KEY `cat_nome` (`cat_nome`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `entrada`
--

CREATE TABLE IF NOT EXISTS `entrada` (
  `ent_cod` int(11) NOT NULL AUTO_INCREMENT,
  `fun_cod` int(11) NOT NULL,
  `ent_data` date DEFAULT NULL,
  PRIMARY KEY (`ent_cod`),
  KEY `fun_cod` (`fun_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE IF NOT EXISTS `fornecedor` (
  `for_cod` int(11) NOT NULL AUTO_INCREMENT,
  `pes_codigo` int(11) NOT NULL,
  `for_nome` varchar(50) NOT NULL,
  `for_cnpj` char(18) DEFAULT NULL,
  `for_contato` varchar(50) NOT NULL,
  `for_telefone` varchar(14) NOT NULL,
  `for_celular` varchar(15) DEFAULT NULL,
  `for_email` varchar(50) DEFAULT NULL,
  `for_site` varchar(50) DEFAULT NULL,
  `for_dataCad` date NOT NULL,
  PRIMARY KEY (`for_cod`),
  UNIQUE KEY `for_cnpj` (`for_cnpj`),
  KEY `pes_codigo` (`pes_codigo`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE IF NOT EXISTS `funcionario` (
  `fun_cod` int(11) NOT NULL AUTO_INCREMENT,
  `pes_codigo` int(11) NOT NULL,
  `fun_nome` varchar(50) NOT NULL,
  `fun_rg` varchar(12) NOT NULL,
  `fun_cpf` varchar(15) NOT NULL,
  `fun_telefone` varchar(14) NOT NULL,
  `fun_celular` varchar(15) DEFAULT NULL,
  `fun_nascimento` date NOT NULL,
  `fun_dataD` date DEFAULT NULL,
  `fun_dataE` date DEFAULT NULL,
  `fun_cargo` varchar(30) NOT NULL,
  PRIMARY KEY (`fun_cod`),
  UNIQUE KEY `fun_cpf` (`fun_cpf`),
  KEY `pes_codigo` (`pes_codigo`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_entrada`
--

CREATE TABLE IF NOT EXISTS `item_entrada` (
  `itement_cod` int(11) NOT NULL AUTO_INCREMENT,
  `ent_cod` int(11) NOT NULL,
  `pro_cod` int(11) NOT NULL,
  `itemEnt_qnt` int(11) NOT NULL,
  `entValor_unitario` decimal(8,2) NOT NULL,
  `entValor_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`itement_cod`),
  KEY `ent_cod` (`ent_cod`),
  KEY `pro_cod` (`pro_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_saida`
--

CREATE TABLE IF NOT EXISTS `item_saida` (
  `itemsai_cod` int(11) NOT NULL AUTO_INCREMENT,
  `pro_cod` int(11) NOT NULL,
  `sai_cod` int(11) NOT NULL,
  `itemsai_qnt` int(11) NOT NULL,
  `saiValor_uni` decimal(8,2) NOT NULL,
  `saiValor_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`itemsai_cod`),
  KEY `pro_cod` (`pro_cod`),
  KEY `sai_cod` (`sai_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE IF NOT EXISTS `pessoa` (
  `pes_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `end_cep` varchar(11) NOT NULL,
  `end_cidade` varchar(40) NOT NULL,
  `end_uf` char(2) NOT NULL,
  `end_rua` varchar(50) NOT NULL,
  `end_num` char(10) NOT NULL,
  `end_complemento` varchar(20) DEFAULT NULL,
  `pe_situacao` varchar(9) NOT NULL,
  PRIMARY KEY (`pes_codigo`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `pro_cod` int(11) NOT NULL AUTO_INCREMENT,
  `cat_cod` int(11) NOT NULL,
  `pro_nome` varchar(40) NOT NULL,
  `pro_uniMedida` varchar(25) NOT NULL,
  `pro_valor` decimal(8,2) NOT NULL,
  `pro_descricao` text,
  `pro_estmin` double DEFAULT NULL,
  `pro_validade` date DEFAULT NULL,
  `pro_estoque` double NOT NULL,
  PRIMARY KEY (`pro_cod`),
  KEY `cat_cod` (`cat_cod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_fornecedor`
--

CREATE TABLE IF NOT EXISTS `produto_fornecedor` (
  `pro_cod` int(11) NOT NULL,
  `for_cod` int(11) NOT NULL,
  `produto_for_cod` varchar(20) NOT NULL,
  PRIMARY KEY (`pro_cod`,`for_cod`),
  KEY `for_cod` (`for_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `saida`
--

CREATE TABLE IF NOT EXISTS `saida` (
  `sai_cod` int(11) NOT NULL AUTO_INCREMENT,
  `fun_cod` int(11) NOT NULL,
  `sai_data` date DEFAULT NULL,
  PRIMARY KEY (`sai_cod`),
  KEY `fun_cod` (`fun_cod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
