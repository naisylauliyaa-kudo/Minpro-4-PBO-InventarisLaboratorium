-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 03:37 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventaris_laboratorium`
--

-- --------------------------------------------------------

--
-- Table structure for table `alat_lab`
--

CREATE TABLE `alat_lab` (
  `id` int NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jumlah` int NOT NULL,
  `kondisi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `alat_lab`
--

INSERT INTO `alat_lab` (`id`, `nama`, `jumlah`, `kondisi`) VALUES
(101, 'Mikroskop Cahaya', 2, 'Baik'),
(102, 'Gelas Ukur 100ml', 12, 'Baik'),
(103, 'Neraca Analitik', 3, 'Baik'),
(104, 'Tabung Reaksi', 28, 'Cukup'),
(105, 'Sapu Ijuk', 3, 'Baik'),
(106, 'Kursi Goyang', 4, 'Baik'),
(107, 'Meja', 4, 'Baik'),
(108, 'Baju APD', 5, 'Baik');

-- --------------------------------------------------------

--
-- Table structure for table `bahan_lab`
--

CREATE TABLE `bahan_lab` (
  `id` int NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jumlah` int NOT NULL,
  `satuan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bahan_lab`
--

INSERT INTO `bahan_lab` (`id`, `nama`, `jumlah`, `satuan`) VALUES
(201, 'Alkohol 96%', 10, '100 ml'),
(202, 'Asam Klorida (HCl)', 5, 'Liter'),
(203, 'Aquades', 20, 'Liter'),
(204, 'Indikator Phenolphthalein', 2, 'Botol'),
(205, 'Air', 10, 'Liter'),
(206, 'Natrium', 7, 'gram');

-- --------------------------------------------------------

--
-- Table structure for table `keperluan_lab`
--

CREATE TABLE `keperluan_lab` (
  `id` int NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jumlah` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `keperluan_lab`
--

INSERT INTO `keperluan_lab` (`id`, `nama`, `jumlah`) VALUES
(301, 'Masker Medis', 100),
(302, 'Sarung Tangan Lateks', 50),
(303, 'Tisu Laboratorium', 15);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int NOT NULL,
  `nama_peminjam` varchar(50) NOT NULL,
  `id_alat_pinjam` int NOT NULL,
  `jumlah_pinjam` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `nama_peminjam`, `id_alat_pinjam`, `jumlah_pinjam`) VALUES
(1, 'Naisyla', 101, 1),
(2, 'Martin', 102, 2),
(3, 'Keonho', 103, 1),
(10, 'Juhoon', 101, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alat_lab`
--
ALTER TABLE `alat_lab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bahan_lab`
--
ALTER TABLE `bahan_lab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keperluan_lab`
--
ALTER TABLE `keperluan_lab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
