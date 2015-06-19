-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.19 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4796
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ucc 的数据库结构
CREATE DATABASE IF NOT EXISTS `ucc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ucc`;


-- 导出  表 ucc.mapping 结构
CREATE TABLE IF NOT EXISTS `mapping` (
  `id` int(11) DEFAULT NULL,
  `sys` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `data` varchar(50) DEFAULT NULL,
  `desc` varchar(50) DEFAULT NULL,
  `biz` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ucc.mapping 的数据：~2 rows (大约)
DELETE FROM `mapping`;
/*!40000 ALTER TABLE `mapping` DISABLE KEYS */;
INSERT INTO `mapping` (`id`, `sys`, `code`, `data`, `desc`, `biz`) VALUES
	(1, 'XJK', '201', NULL, '小金库充值', 'ORDER'),
	(1, 'RWS', '20100', NULL, '充值', 'ORDER');
/*!40000 ALTER TABLE `mapping` ENABLE KEYS */;


-- 导出  表 ucc.tb_app 结构
CREATE TABLE IF NOT EXISTS `tb_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appName` varchar(50) NOT NULL DEFAULT '0' COMMENT 'app 名称',
  `description` varchar(50) NOT NULL DEFAULT '0' COMMENT 'app 描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='应用描述';

-- 正在导出表  ucc.tb_app 的数据：~8 rows (大约)
DELETE FROM `tb_app`;
/*!40000 ALTER TABLE `tb_app` DISABLE KEYS */;
INSERT INTO `tb_app` (`id`, `appName`, `description`) VALUES
	(1, 'ucc', '配置管理'),
	(2, 'wallet', '钱包'),
	(3, 'pay-center', '支付中心'),
	(4, 'test', '应用管理添加test'),
	(5, 'test', '应用管理添加test'),
	(6, 'test1', '应用管理添加test'),
	(7, 'test2', '应用管理添加test'),
	(8, 'test3', '应用管理添加test');
/*!40000 ALTER TABLE `tb_app` ENABLE KEYS */;


-- 导出  表 ucc.tb_field 结构
CREATE TABLE IF NOT EXISTS `tb_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL DEFAULT '0' COMMENT 'service id',
  `fieldName` varchar(50) NOT NULL DEFAULT '0' COMMENT '字段名',
  `fieldType` varchar(512) NOT NULL DEFAULT '0' COMMENT '字段类型',
  `path` varchar(50) NOT NULL DEFAULT '0' COMMENT 'zk 路径',
  `update` char(50) NOT NULL DEFAULT '0' COMMENT '是否自动更新',
  `resolverClass` varchar(512) NOT NULL DEFAULT '0' COMMENT '解析类',
  `tempkey` varchar(50) NOT NULL DEFAULT '0' COMMENT '扩展配置信息存放地址',
  `extendClass` varchar(512) NOT NULL DEFAULT '0' COMMENT '扩展配置处理类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字段';

-- 正在导出表  ucc.tb_field 的数据：~0 rows (大约)
DELETE FROM `tb_field`;
/*!40000 ALTER TABLE `tb_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_field` ENABLE KEYS */;


-- 导出  表 ucc.tb_service 结构
CREATE TABLE IF NOT EXISTS `tb_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL DEFAULT '0' COMMENT 'app 外键',
  `serviceName` varchar(50) NOT NULL DEFAULT '0' COMMENT '服务名称',
  `serviceType` varchar(512) NOT NULL DEFAULT '0' COMMENT '服务类',
  `path` varchar(50) NOT NULL DEFAULT '0' COMMENT 'zk path',
  `servers` varchar(50) NOT NULL DEFAULT '0' COMMENT 'zk servers',
  `useOwnServers` char(2) NOT NULL DEFAULT '0' COMMENT '是否使用当前配置servers',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='服务';

-- 正在导出表  ucc.tb_service 的数据：~2 rows (大约)
DELETE FROM `tb_service`;
/*!40000 ALTER TABLE `tb_service` DISABLE KEYS */;
INSERT INTO `tb_service` (`id`, `aid`, `serviceName`, `serviceType`, `path`, `servers`, `useOwnServers`) VALUES
	(1, 1, '实名证件', 'Certificate', '/cert', '127.0.0.1:2181', '1'),
	(2, 1, 'TTA', 'CC', 'CCC', '', ''),
	(3, 1, 'CASCA', 'CSACA', 'ASVAVA', '', ''),
	(4, 2, 'acasca', 'casca', 'vasva', '', '');
/*!40000 ALTER TABLE `tb_service` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
