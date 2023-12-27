/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : db_expressprint

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-11-21 11:30:01
*/


CREATE DATABASE db_ExpressPrint;
USE db_ExpressPrint;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_receivesendmessage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_receivesendmessage`;
CREATE TABLE `tb_receivesendmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sendName` varchar(20) DEFAULT NULL,
  `sendTelephone` varchar(30) DEFAULT NULL,
  `sendCompary` varchar(30) DEFAULT NULL,
  `sendAddress` varchar(100) DEFAULT NULL,
  `sendPostcode` varchar(10) DEFAULT NULL,
  `receiveName` varchar(20) DEFAULT NULL,
  `recieveTelephone` varchar(30) DEFAULT NULL,
  `recieveCompary` varchar(30) DEFAULT NULL,
  `receiveAddress` varchar(100) DEFAULT NULL,
  `receivePostcode` varchar(10) DEFAULT NULL,
  `ControlPosition` varchar(200) DEFAULT NULL,
  `expressSize` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_receivesendmessage
-- ----------------------------
INSERT INTO `tb_receivesendmessage` VALUES ('1', '辽服商店客服', '024-00000000', '辽服商店', '辽宁省沈阳市|沈北新区|蒲河路81号', '110000', '张三', '13000001234', '海川食品有限公司', '辽宁省沈阳市|百川街|101号', '110000', '150,114/347,114/150,141/115,179/114,205/114,230/366,256/151,311/349,312/150,339/115,377/115,404/116,430/366,456', '1011,509');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '123456');
INSERT INTO `tb_user` VALUES ('2', 'zzk', 'zzk');








