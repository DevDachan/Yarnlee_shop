-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.10.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- shop_project 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `shop_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `shop_project`;

-- 테이블 shop_project.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL DEFAULT '',
  `hash_key` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.admin:~3 rows (대략적) 내보내기
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `password`, `hash_key`) VALUES
	('admin', 'password', 'hashKey'),
	('main', '0', '안녕하십니까!'),
	('order', '0', 'ㄴㅇㄴㅇ');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 테이블 shop_project.image 구조 내보내기
CREATE TABLE IF NOT EXISTS `image` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.image:~1 rows (대략적) 내보내기
DELETE FROM `image`;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` (`id`) VALUES
	(431969);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;

-- 테이블 shop_project.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `hits` int(11) NOT NULL,
  `title` varchar(50) NOT NULL DEFAULT '0',
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.notice:~0 rows (대략적) 내보내기
DELETE FROM `notice`;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 테이블 shop_project.ordert 구조 내보내기
CREATE TABLE IF NOT EXISTS `ordert` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `image_id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `order_address` varchar(255) DEFAULT NULL,
  `order_date` varchar(255) DEFAULT NULL,
  `order_name` varchar(255) DEFAULT NULL,
  `order_phone` varchar(255) DEFAULT NULL,
  `order_zonecode` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `total_cost` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `parcel_type` varchar(255) DEFAULT NULL,
  `parcel_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.ordert:~0 rows (대략적) 내보내기
DELETE FROM `ordert`;
/*!40000 ALTER TABLE `ordert` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordert` ENABLE KEYS */;

-- 테이블 shop_project.order_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_id` int(11) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.order_detail:~0 rows (대략적) 내보내기
DELETE FROM `order_detail`;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- 테이블 shop_project.phone_auth 구조 내보내기
CREATE TABLE IF NOT EXISTS `phone_auth` (
  `phone_num` varchar(50) NOT NULL,
  `check_auth` varchar(255) DEFAULT NULL,
  `secret_key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.phone_auth:~0 rows (대략적) 내보내기
DELETE FROM `phone_auth`;
/*!40000 ALTER TABLE `phone_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone_auth` ENABLE KEYS */;

-- 테이블 shop_project.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL,
  `delivery_time` varchar(255) DEFAULT NULL,
  `delivery_cost_general` int(11) NOT NULL,
  `delivery_cost_half` int(11) NOT NULL,
  `detail` text DEFAULT NULL,
  `image_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `sub_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.product:~2 rows (대략적) 내보내기
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `delivery_time`, `delivery_cost_general`, `delivery_cost_half`, `detail`, `image_id`, `name`, `position`, `price`, `sub_detail`) VALUES
	(1, '0일', 0, 0, '<p>아니 이게 맞나요!?</p>', '431969', 'name', 1, 0, 'sub detail');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- 테이블 shop_project.product_color 구조 내보내기
CREATE TABLE IF NOT EXISTS `product_color` (
  `product_id` int(11) NOT NULL,
  `color` varchar(50) NOT NULL,
  PRIMARY KEY (`product_id`,`color`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.product_color:~0 rows (대략적) 내보내기
DELETE FROM `product_color`;
/*!40000 ALTER TABLE `product_color` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_color` ENABLE KEYS */;

-- 테이블 shop_project.product_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `product_detail` (
  `product_id` varchar(50) NOT NULL,
  `product_detail` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.product_detail:~0 rows (대략적) 내보내기
DELETE FROM `product_detail`;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;

-- 테이블 shop_project.sms_content 구조 내보내기
CREATE TABLE IF NOT EXISTS `sms_content` (
  `id` varchar(50) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.sms_content:~0 rows (대략적) 내보내기
DELETE FROM `sms_content`;
/*!40000 ALTER TABLE `sms_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_content` ENABLE KEYS */;

-- 테이블 shop_project.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(50) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address_detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `zone_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 shop_project.user:~0 rows (대략적) 내보내기
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
