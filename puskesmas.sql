-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2025 at 03:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `puskesmas`
--

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` int(11) NOT NULL,
  `nama_dokter` varchar(30) NOT NULL,
  `poli` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` int(11) NOT NULL,
  `nama_obat` varchar(30) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `jenis` enum('Kapsul','Tablet','Sirup','Salep','Injeksi') NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` varchar(200) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `bpjs_status` varchar(15) NOT NULL,
  `nip` varchar(500) NOT NULL,
  `input_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama_obat`, `kategori`, `jenis`, `stok`, `harga`, `brand`, `bpjs_status`, `nip`, `input_date`, `edit_date`) VALUES
(1, 'adad', 'edadsd', 'Kapsul', 10, '144444', 'ade', 'tercover', '2323232323', '2025-03-21 15:32:03', '2025-03-21 15:32:03'),
(2, 'asdasdfasfas', 'Obat Ringan', 'Kapsul', 2323, '14124242424', 'asfasfsdf', 'Tercover', '202503210002', '2025-03-21 15:35:49', '2025-03-21 15:35:49'),
(3, 'asdas', 'Obat Ringan', 'Kapsul', 23232, '42424242424', 'adgsdgsdgsdg', 'Tercover', '202503210002', '2025-03-21 15:41:19', '2025-03-21 15:41:19'),
(4, 'sdsadasd', 'Obat Ringan', 'Kapsul', 232, '111232323', 'adedad', 'Tercover', '202503210002', '2025-03-21 15:42:05', '2025-03-21 15:42:05'),
(5, 'Paracetamol', 'Obat Keras', 'Kapsul', 10, '150000', 'ame', 'Tercover', '202503210002', '2025-03-21 15:42:29', '2025-03-21 15:42:29'),
(6, 'Bodrex', 'Obat Sangat Keras', 'Kapsul', 10, '130500', 'bodrex', 'Tercover', '202503210002', '2025-03-21 15:46:16', '2025-03-21 15:46:16'),
(7, 'asdasd', 'Obat Sangat Keras', 'Kapsul', 14, '144444', 'agsdgsdgsdg', 'Tidak Dicover', '202503210002', '2025-03-21 15:46:36', '2025-03-21 15:46:36'),
(8, 'Paracetamol', 'Obat Ringan', 'Kapsul', 50, 'Rp. 10.000,00', 'Paraceta', 'Tercover', '202503210002', '2025-04-28 10:50:10', '2025-04-28 10:50:10'),
(9, 'Sucralfate', 'Obat Ringan', 'Kapsul', 10, 'Rp. 30.000,00', 'Supensi', 'Tercover', '202503210002', '2025-04-28 10:56:10', '2025-04-28 10:56:10'),
(10, 'Sucralfate', 'Obat Ringan', 'Kapsul', 10, 'Rp. 30.000,00', 'Suspensi', 'Tercover', '202503210002', '2025-04-28 10:57:38', '2025-04-28 10:57:38'),
(14, 'nbjh', 'Obat Ringan', 'Kapsul', 1234, '7879', 'hgj', 'Tercover', 'nip', '2025-04-28 12:03:10', '2025-04-28 12:03:10'),
(17, 'paramex', 'Obat Ringan', 'Kapsul', 10, '19000', 'idrisfams', 'Tercover', '202243500081', '2025-04-28 13:36:23', '2025-04-28 13:36:23'),
(18, 'Aripirazol', 'Obat Sangat Keras', 'Kapsul', 10, 'Rp. 400.000,00', 'Arinia', 'Tidak Dicover', '202243500030', '2025-04-29 02:31:05', '2025-04-29 02:31:05'),
(19, 'Paracetamol', 'Obat Ringan', 'Kapsul', 15, 'Rp. 10.000,00', 'Paraceta', 'Tercover', '202243500081', '2025-04-29 02:34:25', '2025-04-29 02:34:25'),
(20, 'NDKSN', 'Obat Ringan', 'Kapsul', 12, '12345', 'DAD', 'Tercover', '202243500030', '2025-04-29 02:58:20', '2025-04-29 02:58:20'),
(21, 'gatau', 'Obat Ringan', 'Kapsul', 10, '10000', 'hajhs', 'Tercover', '202503210002', '2025-05-23 08:14:14', '2025-05-23 08:14:14'),
(22, 'Clozapine', 'Obat Sangat Keras', 'Kapsul', 20, 'Rp. 100.000', 'Zapine', 'Tercover', '202243500030', '2025-05-29 12:38:14', '2025-05-29 12:38:14'),
(23, 'Alprazolam', 'Obat Sangat Keras', 'Sirup', 5, 'Rp 50.000,00', 'Arinia', 'Tercover', '202243500030', '2025-05-30 13:50:33', '2025-05-30 13:50:33');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` int(11) NOT NULL,
  `nama_pasien` varchar(40) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `no_bpjs` varchar(13) NOT NULL,
  `no_rekam` varchar(50) NOT NULL,
  `jenis_kelamin` enum('Perempuan','Laki-Laki','','') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `alamat` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `nama_pasien`, `nik`, `no_bpjs`, `no_rekam`, `jenis_kelamin`, `tanggal_lahir`, `no_telepon`, `alamat`) VALUES
(1, 'andre', '32760165', '28654879', 'R01', 'Perempuan', '2025-03-13', '080808', 'Jl.Kaso 1'),
(2, 'Dihar', '202243', '5000', '30', 'Perempuan', '2025-06-25', '0896348011', 'Cibubur Village');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` varchar(50) NOT NULL,
  `id_resep` varchar(50) NOT NULL,
  `total_biaya` varchar(50) NOT NULL,
  `metode_pembayaran` varchar(30) NOT NULL,
  `tanggal_bayar` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `id_resep`, `total_biaya`, `metode_pembayaran`, `tanggal_bayar`) VALUES
('PB001', 'RS001', 'Rp.100.000,00', 'Tunai', '2025-06-05');

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran_pasien`
--

CREATE TABLE `pendaftaran_pasien` (
  `id_pendaftaran` int(11) NOT NULL,
  `id_pasien` int(11) NOT NULL,
  `id_dokter` int(11) NOT NULL,
  `poli` varchar(20) NOT NULL,
  `tanggal_kunjungan` date NOT NULL,
  `status_perawatan` enum('Rawat Jalan','Rawat Inap','','') NOT NULL,
  `status` enum('Selesai','Menunggu','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pendaftaran_pasien`
--

INSERT INTO `pendaftaran_pasien` (`id_pendaftaran`, `id_pasien`, `id_dokter`, `poli`, `tanggal_kunjungan`, `status_perawatan`, `status`) VALUES
(1, 1, 1, 'Bedah', '2025-06-10', 'Rawat Jalan', 'Selesai'),
(2, 2, 2, 'Penyakit Dalam', '2025-06-28', 'Rawat Jalan', 'Menunggu'),
(3, 3, 3, 'Psikiatri', '2025-06-05', 'Rawat Inap', 'Menunggu');

-- --------------------------------------------------------

--
-- Table structure for table `poli`
--

CREATE TABLE `poli` (
  `id_poli` int(11) NOT NULL,
  `id_dokter` int(11) NOT NULL,
  `nama_poli` varchar(20) NOT NULL,
  `ruang_poli` varchar(100) NOT NULL,
  `tanggal_praktek` date NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `poli`
--

INSERT INTO `poli` (`id_poli`, `id_dokter`, `nama_poli`, `ruang_poli`, `tanggal_praktek`, `jam_mulai`, `jam_selesai`) VALUES
(1, 2, 'Bedah', '', '2025-06-14', '10.00', '13.00'),
(123, 1, 'Psikiatri', '', '2025-06-11', '08.00', '12.00');

-- --------------------------------------------------------

--
-- Table structure for table `rekam_medis`
--

CREATE TABLE `rekam_medis` (
  `id_rekam` int(11) NOT NULL,
  `nama_pasien` varchar(100) NOT NULL,
  `id_dokter` varchar(50) NOT NULL,
  `keluhan` text NOT NULL,
  `diagnosa` varchar(100) NOT NULL,
  `tindakan` varchar(50) NOT NULL,
  `catatan_dokter` varchar(150) NOT NULL,
  `tanggal_kunjungan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `id_resep` int(11) NOT NULL,
  `id_dokter` int(11) NOT NULL,
  `id_rekam` int(11) NOT NULL,
  `nama_pasien` varchar(100) NOT NULL,
  `tanggal_resep` date NOT NULL,
  `id_obat` int(11) NOT NULL,
  `nama_obat` varchar(100) NOT NULL,
  `jumlah_obat` int(11) NOT NULL,
  `catatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`id_resep`, `id_dokter`, `id_rekam`, `nama_pasien`, `tanggal_resep`, `id_obat`, `nama_obat`, `jumlah_obat`, `catatan`) VALUES
(11, 112, 1112, 'Andin', '2025-05-30', 1113, 'Clozapine', 7, 'satu strip berisi 7 yang diminum 2 x sehari'),
(202243, 5000, 30, 'Nur Izdihar Idris Yunus', '2025-05-31', 30, 'Alprazolam', 14, '1 x 1 hari');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `nip` varchar(500) NOT NULL,
  `nama` varchar(500) NOT NULL,
  `jenis_kelamin` enum('Laki - Laki','Perempuan') NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(500) NOT NULL,
  `tanggal_daftar` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status_akun` enum('Activated','Deactivated') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`nip`, `nama`, `jenis_kelamin`, `alamat`, `jabatan`, `email`, `password`, `tanggal_daftar`, `status_akun`) VALUES
('202243500030', 'Nur Izdihar Idris Yunus', 'Laki - Laki', 'Jl. Jambu 1 No.95 Kel. Depok Jaya Kec. Pancoran Mas', 'Kepala Puskesmas', 'nzdihar@gmail.com', 'L1lqb9RiGstTnr8BokwHDw==', '2025-04-28 10:48:37', 'Activated'),
('202243500081', 'Satria Mandala Putra', 'Laki - Laki', 'Depok, Beji', 'none', 'satriaput17@gmail.com', 'L1lqb9RiGstTnr8BokwHDw==', '2025-04-28 12:01:14', 'Activated');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`),
  ADD UNIQUE KEY `no_telepon` (`no_telepon`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD UNIQUE KEY `id_pendaftaran` (`id_resep`);

--
-- Indexes for table `pendaftaran_pasien`
--
ALTER TABLE `pendaftaran_pasien`
  ADD PRIMARY KEY (`id_pendaftaran`),
  ADD UNIQUE KEY `id_pasien` (`id_pasien`),
  ADD UNIQUE KEY `iddokter` (`id_dokter`);

--
-- Indexes for table `poli`
--
ALTER TABLE `poli`
  ADD PRIMARY KEY (`id_poli`),
  ADD UNIQUE KEY `UNIQUE` (`id_dokter`);

--
-- Indexes for table `rekam_medis`
--
ALTER TABLE `rekam_medis`
  ADD PRIMARY KEY (`id_rekam`),
  ADD UNIQUE KEY `id_pendaftaran` (`id_dokter`);

--
-- Indexes for table `resep`
--
ALTER TABLE `resep`
  ADD PRIMARY KEY (`id_resep`),
  ADD UNIQUE KEY `id_rekam` (`id_rekam`),
  ADD UNIQUE KEY `id_obat` (`id_obat`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`nip`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `obat`
--
ALTER TABLE `obat`
  MODIFY `id_obat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `pendaftaran_pasien`
--
ALTER TABLE `pendaftaran_pasien`
  MODIFY `id_pasien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `poli`
--
ALTER TABLE `poli`
  MODIFY `id_dokter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `resep`
--
ALTER TABLE `resep`
  MODIFY `id_resep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=202244;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
