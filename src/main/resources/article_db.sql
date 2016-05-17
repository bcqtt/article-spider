-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 article 的数据库结构
DROP DATABASE IF EXISTS `article`;
CREATE DATABASE IF NOT EXISTS `article` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `article`;


-- 导出  表 article.article 结构
DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` varchar(32) NOT NULL COMMENT '文章id 32位 UUID',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `author` varchar(50) DEFAULT NULL COMMENT '文章作者',
  `content` text COMMENT '文章内容',
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '文章发表时间',
  `key_words` varchar(100) DEFAULT NULL COMMENT '文章关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章信息表';

-- 正在导出表  article.article 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;


-- 导出  表 article.crawler 结构
DROP TABLE IF EXISTS `crawler`;
CREATE TABLE IF NOT EXISTS `crawler` (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `start_url` varchar(200) DEFAULT NULL COMMENT '初始URL',
  `start_url_template` varchar(200) DEFAULT NULL COMMENT '开始URL模板（正则表达式）',
  `target_url_template` varchar(200) DEFAULT NULL COMMENT '目标URL模板（正则表达式）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='网络爬虫配置信息表';

-- 正在导出表  article.crawler 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `crawler` DISABLE KEYS */;
INSERT INTO `crawler` (`id`, `name`, `start_url`, `start_url_template`, `target_url_template`) VALUES
	(1, '1', '1', '1', '1'),
	(2, '2', '2', '2', '2'),
	(3, '3', '3', '3', '3'),
	(4, '4', '4', '4', '4'),
	(5, '5', '5', '5', '5'),
	(6, '6', '6', '6', '6'),
	(7, '7', '7', '7', '7'),
	(8, '8', '8', '8', '8'),
	(9, '9', '9', '9', '9'),
	(10, '10', '10', '10', '10'),
	(11, '11', '11', '11', '11'),
	(12, '12', '12', '12', '12'),
	(13, '13', '13', '13', '13'),
	(14, '14', '14', '14', '14'),
	(15, '15', '15', '15', '15'),
	(16, '16', '16', '16', '16'),
	(17, '17', '17', '17', '17'),
	(18, '18', '18', '18', '18');
/*!40000 ALTER TABLE `crawler` ENABLE KEYS */;


-- 导出  表 article.digest 结构
DROP TABLE IF EXISTS `digest`;
CREATE TABLE IF NOT EXISTS `digest` (
  `id` varchar(32) NOT NULL,
  `article_id` varchar(32) DEFAULT NULL,
  `digest` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章摘要';

-- 正在导出表  article.digest 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `digest` DISABLE KEYS */;
/*!40000 ALTER TABLE `digest` ENABLE KEYS */;


-- 导出  表 article.reviews 结构
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE IF NOT EXISTS `reviews` (
  `id` varchar(32) NOT NULL,
  `review` varchar(10000) DEFAULT NULL,
  `reviewer` varchar(100) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `helpful_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  article.reviews 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` (`id`, `review`, `reviewer`, `date`, `helpful_count`) VALUES
	('1', '今天天气很好', '啊次', '2016-05-16 17:05:56', 1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;


-- 导出  表 article.url 结构
DROP TABLE IF EXISTS `url`;
CREATE TABLE IF NOT EXISTS `url` (
  `id` varchar(32) NOT NULL,
  `url` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='爬取到的URL链接';

-- 正在导出表  article.url 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `url` DISABLE KEYS */;
/*!40000 ALTER TABLE `url` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
