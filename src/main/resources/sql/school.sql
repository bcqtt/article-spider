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

-- 导出 school 的数据库结构
DROP DATABASE IF EXISTS `school`;
CREATE DATABASE IF NOT EXISTS `school` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `school`;


-- 导出  表 school.class 结构
DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `caption` varchar(32) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  school.class 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`cid`, `caption`) VALUES
	(1, '三年二班'),
	(2, '三年三班'),
	(3, '一年二班'),
	(4, '二年九班');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;


-- 导出  表 school.course 结构
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(32) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `fk_course_teacher` (`teacher_id`),
  CONSTRAINT `fk_course_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  school.course 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`cid`, `cname`, `teacher_id`) VALUES
	(1, '生物', 1),
	(2, '物理', 2),
	(3, '体育', 3),
	(4, '美术', 2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


-- 导出  表 school.score 结构
DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `fk_score_student` (`student_id`),
  KEY `fk_score_course` (`course_id`),
  CONSTRAINT `fk_score_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`cid`),
  CONSTRAINT `fk_score_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- 正在导出表  school.score 的数据：~48 rows (大约)
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` (`sid`, `student_id`, `course_id`, `num`) VALUES
	(1, 1, 1, 10),
	(2, 1, 2, 9),
	(5, 1, 4, 66),
	(6, 2, 1, 8),
	(8, 2, 3, 68),
	(9, 2, 4, 99),
	(10, 3, 1, 77),
	(11, 3, 2, 66),
	(12, 3, 3, 87),
	(13, 3, 4, 99),
	(14, 4, 1, 79),
	(15, 4, 2, 11),
	(16, 4, 3, 67),
	(17, 4, 4, 100),
	(18, 5, 1, 79),
	(19, 5, 2, 11),
	(20, 5, 3, 67),
	(21, 5, 4, 100),
	(22, 6, 1, 9),
	(23, 6, 2, 100),
	(24, 6, 3, 67),
	(25, 6, 4, 100),
	(26, 7, 1, 9),
	(27, 7, 2, 100),
	(28, 7, 3, 67),
	(29, 7, 4, 88),
	(30, 8, 1, 9),
	(31, 8, 2, 100),
	(32, 8, 3, 67),
	(33, 8, 4, 88),
	(34, 9, 1, 91),
	(35, 9, 2, 88),
	(36, 9, 3, 67),
	(37, 9, 4, 22),
	(38, 10, 1, 90),
	(39, 10, 2, 77),
	(40, 10, 3, 43),
	(41, 10, 4, 87),
	(42, 11, 1, 90),
	(43, 11, 2, 77),
	(44, 11, 3, 43),
	(45, 11, 4, 87),
	(46, 12, 1, 90),
	(47, 12, 2, 77),
	(48, 12, 3, 43),
	(49, 12, 4, 87),
	(52, 13, 3, 87);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;


-- 导出  表 school.student 结构
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `gender` char(1) NOT NULL,
  `class_id` int(11) NOT NULL,
  `sname` varchar(32) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `fk_class` (`class_id`),
  CONSTRAINT `fk_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在导出表  school.student 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`sid`, `gender`, `class_id`, `sname`) VALUES
	(1, '男', 1, '理解'),
	(2, '女', 1, '钢蛋'),
	(3, '男', 1, '张三'),
	(4, '男', 1, '张一'),
	(5, '女', 1, '张二'),
	(6, '男', 1, '张四'),
	(7, '女', 2, '铁锤'),
	(8, '男', 2, '李三'),
	(9, '男', 2, '李一'),
	(10, '女', 2, '李二'),
	(11, '男', 2, '李四'),
	(12, '女', 3, '如花'),
	(13, '男', 3, '刘三'),
	(14, '男', 3, '刘一'),
	(15, '女', 3, '刘二'),
	(16, '男', 3, '刘四');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- 导出  表 school.teacher 结构
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(32) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  school.teacher 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`tid`, `tname`) VALUES
	(1, '张磊老师'),
	(2, '李平老师'),
	(3, '刘海燕老师'),
	(4, '朱云海老师'),
	(5, '李白老师');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
