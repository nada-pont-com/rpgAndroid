-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 01-Jun-2019 às 02:57
-- Versão do servidor: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rpg_android`
--
CREATE DATABASE IF NOT EXISTS `rpg_android` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `rpg_android`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `habilidades`
--

CREATE TABLE `habilidades` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `tipo` char(1) NOT NULL,
  `valor` int(10) UNSIGNED NOT NULL,
  `nunberAtk` int(10) UNSIGNED NOT NULL,
  `aumento` int(10) UNSIGNED NOT NULL,
  `nocalte` int(10) UNSIGNED NOT NULL,
  `extra` varchar(20) DEFAULT NULL,
  `pontos` int(10) UNSIGNED NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `loads`
--

CREATE TABLE `loads` (
  `id` tinyint(3) UNSIGNED NOT NULL,
  `nome` varchar(45) CHARACTER SET latin1 NOT NULL,
  `tempo` varchar(255) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `persos`
--

CREATE TABLE `persos` (
  `id` int(11) UNSIGNED NOT NULL,
  `nome` varchar(40) NOT NULL,
  `level` int(10) UNSIGNED NOT NULL,
  `experiencia` int(10) UNSIGNED NOT NULL,
  `pontosExp` int(10) UNSIGNED NOT NULL,
  `pontosHab` int(10) UNSIGNED NOT NULL,
  `loads_id` tinyint(3) UNSIGNED NOT NULL,
  `classe` varchar(20) NOT NULL,
  `rank` char(1) NOT NULL,
  `rankExp` int(10) UNSIGNED NOT NULL,
  `vida` int(10) UNSIGNED NOT NULL,
  `vidaMax` int(10) UNSIGNED NOT NULL,
  `mp` int(10) UNSIGNED NOT NULL,
  `mpMax` int(10) UNSIGNED NOT NULL,
  `atk` int(10) UNSIGNED NOT NULL,
  `def` int(10) UNSIGNED NOT NULL,
  `agi` int(10) UNSIGNED NOT NULL,
  `atkM` int(10) UNSIGNED NOT NULL,
  `defM` int(10) UNSIGNED NOT NULL,
  `vit` int(10) UNSIGNED NOT NULL,
  `inteli` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `habilidades`
--
ALTER TABLE `habilidades`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loads`
--
ALTER TABLE `loads`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indexes for table `persos`
--
ALTER TABLE `persos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `loads_id` (`loads_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loads`
--
ALTER TABLE `loads`
  MODIFY `id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `persos`
--
ALTER TABLE `persos`
  ADD CONSTRAINT `persos_ibfk_1` FOREIGN KEY (`loads_id`) REFERENCES `loads` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
