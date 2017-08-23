# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.20)
# Database: spray
# Generation Time: 2017-08-23 08:09:32 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table config
# ------------------------------------------------------------

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;

INSERT INTO `config` (`id`, `create_date`, `modify_date`, `code`, `info`, `content`, `status`)
VALUES
	(2,'2016-08-30 17:36:39','2016-08-30 17:36:39','DevelopersScheduled','开发者头条定时任务','1',1),
	(3,'2016-08-30 17:37:17','2016-08-30 17:37:17','WallScheduled','美图3G站定时任务','1',1),
	(4,'2016-09-09 02:53:31','2016-09-09 02:53:31','WallScheduledNum','3G定时任务起始数','223',1),
	(5,'2016-09-05 12:45:21','2016-09-05 12:45:21','DuanZiGeScheduled','段子哥笑话定时任务','1',1),
	(6,'2016-08-30 07:45:49','2016-08-30 07:45:49','DuanZiGeScheduledNum','段子哥笑话定时任务起始数','351',1),
	(7,'2016-09-09 02:09:49','2016-09-09 02:09:49','JianShuScheduled','简书定时任务','1',1),
	(8,'2016-09-09 02:10:16','2016-09-09 02:10:16','ZhiHuScheduled','知乎定时任务','1',1);

/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table permission
# ------------------------------------------------------------



# Dump of table role
# ------------------------------------------------------------

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`, `status`, `create_date`, `modify_date`)
VALUES
	(1,'系统管理员',1,'2015-08-20 11:14:39','2015-08-20 11:14:41'),
	(2,'管理员',1,'2015-08-27 14:18:13','2015-08-27 14:18:15'),
	(3,'银行机构',1,'2015-10-28 16:31:08','2015-10-28 16:31:08');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role_permission
# ------------------------------------------------------------



# Dump of table sources
# ------------------------------------------------------------

LOCK TABLES `sources` WRITE;
/*!40000 ALTER TABLE `sources` DISABLE KEYS */;

INSERT INTO `sources` (`id`, `create_date`, `modify_date`, `source`, `source_image`, `status`)
VALUES
	(1,'2016-08-30 00:19:08','2016-08-30 00:19:08','今日头条','http://img0.imgtn.bdimg.com/it/u=747388847,1799141437&fm=21&gp=0.jpg',1),
	(2,'2016-08-30 00:17:30','2016-08-30 00:17:30','开发者头条','http://img0.imgtn.bdimg.com/it/u=3098333594,1568416040&fm=21&gp=0.jpg',1),
	(4,'2016-08-13 11:13:40','2016-08-13 11:13:40','3G壁纸站','http://www.3gbizhi.com/favicon.ico',1),
	(5,'2016-08-30 00:18:27','2016-08-30 00:18:27','简书','http://img2.imgtn.bdimg.com/it/u=762703104,3048150584&fm=21&gp=0.jpg',1),
	(6,'2016-08-30 07:59:03','2016-08-30 07:59:03','段子哥','http://img1.imgtn.bdimg.com/it/u=1670364414,4086235619&fm=21&gp=0.jpg',1),
	(7,'2016-09-09 02:03:31','2016-09-09 02:03:31','知乎','http://img0.imgtn.bdimg.com/it/u=4076855879,1704731933&fm=21&gp=0.jpg',2),
	(8,'2016-09-09 02:03:31','2016-09-09 02:03:31','WallHaven','https://static.wallhaven.cc/images/layout/logo_sm.png',1);

/*!40000 ALTER TABLE `sources` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table type
# ------------------------------------------------------------

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;

INSERT INTO `type` (`id`, `name`, `info`, `sort`, `status`, `tid`, `create_date`, `modify_date`)
VALUES
	(2,'开发者头条','开发者头条',2,1,0,'2016-10-17 16:54:17','2016-10-17 16:54:19'),
	(5,'简书','简书',1,1,0,'2016-10-17 16:54:01','2016-10-17 16:54:03'),
	(7,'知乎','知乎',3,1,0,'2016-10-17 16:54:37','2016-10-17 16:54:39');

/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `name`, `password`, `salt`, `mobile`, `status`, `create_date`, `modify_date`, `role`)
VALUES
	(1,'admin','admin','62172abfe8b858c1333d57586ec82c16','LXqJ9PV9Y0wWeC8s','admin',0,'2015-08-20 11:13:00','2015-08-20 11:13:02',NULL),
	(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_date`, `modify_date`)
VALUES
	(1,1,1,'2015-08-20 11:14:49',NULL),
	(2,2,2,'2015-08-27 11:07:59',NULL),
	(3,4,3,'2015-10-28 16:31:40',NULL),
	(4,5,1,'2015-12-09 14:27:41',NULL),
	(5,6,3,'2015-12-14 17:11:22',NULL),
	(6,7,2,'2015-12-17 10:13:16',NULL),
	(7,8,3,'2015-12-21 16:49:32',NULL),
	(8,9,3,'2015-12-21 16:49:38',NULL),
	(9,10,3,'2015-12-21 16:49:42',NULL),
	(10,11,1,'2016-01-07 15:49:29',NULL);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
