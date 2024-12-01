-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table documents.account: ~3 rows (approximately)
REPLACE INTO `account` (`Account`, `Password`, `Email`, `SDT`, `UserName`, `Type`) VALUES
	('admin', '1WYl99nyCIEy7AkB6TdCM/R8Cmb3K1Ct4OlnRgs7DNM=', 'admin123@gmail.com', '0344002108', 'admin', 'admin'),
	('tuan', 'P8rZm7B0dkVRAx93eQGtj8FH3mbdpOYXeqwMhcxHZXY=', 'tuan123@gmail.com', '0344002108', 'tuan', 'user'),
	('tung', '6BRYEJe5/OUs8/ObW3aiM9tgMp1ZzdKq6GrW8MfZZF8=', 'tung2982004@gmail.com', '0344002108', 'tung', 'user');

-- Dumping data for table documents.cminfor: ~2 rows (approximately)
REPLACE INTO `cminfor` (`IdCM`, `IdFile`, `UserName`, `Account`, `CmText`, `Date`) VALUES
	(7, 2, 'tung', 'tung', 'Tài liệu này hay quá, cảm ơn bạn nhé', '2024-11-18 13:39:30'),
	(8, 2, 'tuan', 'tuan', 'Vâng không có đâu ạ', '2024-11-18 13:39:46');

-- Dumping data for table documents.fileinfor: ~3 rows (approximately)
REPLACE INTO `fileinfor` (`Id`, `NamePoster`, `Class`, `NameDocument`, `Object`, `NameFile`, `Account`, `Status`) VALUES
	(2, 'tung', 'Lớp 12', 'a', 'Toán', 'Công nghệ web.docx', 'tung', 'Unlocked'),
	(3, 'tung', 'Lớp 12', 'b', 'Toán', '3shs-toan-6-tap-1_69202112.pdf', 'tung', 'Unlocked'),
	(4, 'admin', 'Lớp 12', 'ảnh', 'Toán', '366219193_666952742130754_6264550150611254291_n.jpg', '', 'Unlocked');

-- Dumping data for table documents.report: ~5 rows (approximately)
REPLACE INTO `report` (`IDReport`, `NameDocument`, `Account`, `Username`, `ReportContent`, `Response`, `Status`, `Seen`) VALUES
	(1, 'toán', 'tung', 'tung', 'abc', 'oke', 'Done', 'OK'),
	(4, 'toán', 'tung', 'tung', 'lỗi', 'oke', 'Done', 'OK'),
	(5, 'toán', 'tung', 'tung', 'chào admin', 'oke', 'Done', 'OK'),
	(7, 'toán', 'tung', 'tung', 'a', 'Admin đã tiếp nhận và sẽ sửa cho bạn', 'Done', 'OK'),
	(11, 'b', 'tung', 'tung', 'ádasd', 'No', 'No', 'No');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
