/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : younuo

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-05-03 11:45:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dishe
-- ----------------------------
DROP TABLE IF EXISTS `dishe`;
CREATE TABLE `dishe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dishe
-- ----------------------------
INSERT INTO `dishe` VALUES ('1', '0');
INSERT INTO `dishe` VALUES ('2', '0');
INSERT INTO `dishe` VALUES ('3', '0');
INSERT INTO `dishe` VALUES ('4', '0');
INSERT INTO `dishe` VALUES ('5', '0');
INSERT INTO `dishe` VALUES ('6', '0');
INSERT INTO `dishe` VALUES ('7', '0');
INSERT INTO `dishe` VALUES ('8', '0');
INSERT INTO `dishe` VALUES ('9', '0');
INSERT INTO `dishe` VALUES ('10', '0');
INSERT INTO `dishe` VALUES ('11', '0');
INSERT INTO `dishe` VALUES ('12', '0');
INSERT INTO `dishe` VALUES ('13', '0');
INSERT INTO `dishe` VALUES ('14', '0');
INSERT INTO `dishe` VALUES ('15', '0');
INSERT INTO `dishe` VALUES ('16', '0');
INSERT INTO `dishe` VALUES ('17', '0');
INSERT INTO `dishe` VALUES ('18', '0');
INSERT INTO `dishe` VALUES ('19', '0');
INSERT INTO `dishe` VALUES ('20', '0');
INSERT INTO `dishe` VALUES ('21', '0');
INSERT INTO `dishe` VALUES ('22', '0');
INSERT INTO `dishe` VALUES ('23', '0');
INSERT INTO `dishe` VALUES ('24', '0');
INSERT INTO `dishe` VALUES ('25', '0');
INSERT INTO `dishe` VALUES ('26', '0');
INSERT INTO `dishe` VALUES ('27', '0');
INSERT INTO `dishe` VALUES ('28', '0');
INSERT INTO `dishe` VALUES ('29', '0');
INSERT INTO `dishe` VALUES ('30', '0');
INSERT INTO `dishe` VALUES ('31', '0');
INSERT INTO `dishe` VALUES ('32', '0');
INSERT INTO `dishe` VALUES ('33', '0');
INSERT INTO `dishe` VALUES ('34', '0');
INSERT INTO `dishe` VALUES ('35', '0');
INSERT INTO `dishe` VALUES ('36', '0');
INSERT INTO `dishe` VALUES ('37', '0');
INSERT INTO `dishe` VALUES ('38', '0');
INSERT INTO `dishe` VALUES ('39', '0');
INSERT INTO `dishe` VALUES ('40', '0');
INSERT INTO `dishe` VALUES ('41', '0');
INSERT INTO `dishe` VALUES ('42', '0');
INSERT INTO `dishe` VALUES ('43', '0');
INSERT INTO `dishe` VALUES ('44', '0');
INSERT INTO `dishe` VALUES ('45', '0');
INSERT INTO `dishe` VALUES ('46', '0');
INSERT INTO `dishe` VALUES ('47', '0');
INSERT INTO `dishe` VALUES ('48', '0');
INSERT INTO `dishe` VALUES ('49', '0');
INSERT INTO `dishe` VALUES ('50', '0');

-- ----------------------------
-- Table structure for lottery_prize
-- ----------------------------
DROP TABLE IF EXISTS `lottery_prize`;
CREATE TABLE `lottery_prize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `lottery_name` varchar(255) DEFAULT NULL,
  `take_time` datetime DEFAULT NULL,
  `dishe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lottery_prize
-- ----------------------------

-- ----------------------------
-- Table structure for lottery_rule
-- ----------------------------
DROP TABLE IF EXISTS `lottery_rule`;
CREATE TABLE `lottery_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(255) DEFAULT NULL,
  `odds` int(11) DEFAULT NULL,
  `prize_code` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `valid_flag` char(2) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lottery_rule
-- ----------------------------
INSERT INTO `lottery_rule` VALUES ('1', '免单', '5', '/wechat/winning4', '2017-04-25 11:58:53', '2017-04-25 11:58:56', 'Y');
INSERT INTO `lottery_rule` VALUES ('2', '立减100', '15', '/wechat/winning1', '2017-04-25 11:59:46', '2017-04-25 11:59:49', 'Y');
INSERT INTO `lottery_rule` VALUES ('3', '立减50', '30', '/wechat/winning3', '2017-04-25 12:00:35', '2017-04-25 12:00:39', 'Y');
INSERT INTO `lottery_rule` VALUES ('4', '点心三选一', '450', '/wechat/winning5', '2017-04-25 12:01:01', '2017-04-25 12:01:04', 'Y');
INSERT INTO `lottery_rule` VALUES ('5', '甜品三选一', '500', '/wechat/winning6', '2017-04-25 12:03:26', '2017-04-25 12:03:29', 'Y');
INSERT INTO `lottery_rule` VALUES ('6', '免单', '5', '/wechat/winning4', '2017-04-25 12:03:54', '2017-04-25 12:03:56', 'N');
INSERT INTO `lottery_rule` VALUES ('7', '立减200', '10', '/wechat/winning2', '2017-04-25 12:04:18', '2017-04-25 12:04:21', 'N');
INSERT INTO `lottery_rule` VALUES ('8', '立减100', '25', '/wechat/winning1', '2017-04-25 12:04:40', '2017-04-25 12:04:43', 'N');
INSERT INTO `lottery_rule` VALUES ('9', '点心三选二', '460', '/wechat/winning7', '2017-04-25 12:05:20', '2017-04-25 12:05:22', 'N');
INSERT INTO `lottery_rule` VALUES ('10', '甜品三选二', '500', '/wechat/winning8', '2017-04-25 12:05:48', '2017-04-25 12:05:50', 'N');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for wx_config
-- ----------------------------
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE `wx_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `access_token` varchar(255) DEFAULT NULL COMMENT '微信AccessToken',
  `js_api_ticket` varchar(255) DEFAULT NULL COMMENT '微信JsApiTicket',
  `expires_in` bigint(11) DEFAULT NULL COMMENT '刷新时间',
  `refresh_time` bigint(20) DEFAULT NULL COMMENT '刷新时间',
  `corp_id` varchar(50) DEFAULT NULL COMMENT 'appId',
  `corp_secret` varchar(100) DEFAULT NULL COMMENT 'appSecret',
  `pram_token` varchar(255) DEFAULT NULL COMMENT '标示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_config
-- ----------------------------
INSERT INTO `wx_config` VALUES ('1', 'Y1ud6w3HAeUv25nydwR07y03B4teuVHVEy_yEQaflXY7Y77E7_2GT7cXylNFvG4rDfBBA18ciCV8LbDJVmsVyQCP-VauRPXMjk-YARFXpJXBO_ZVxpDVU_sZNm1peoXeEYDgAGARLQ', 'kgt8ON7yVITDhtdwci0qeZpOd3Yf5wWpz03vI4skAZRcETnL2HnYm6EeXEDw2WLtDkgz274nDJerns1xMPe4Eg', '7000', '1493262554', 'wx35eed173d89a147d', '7d843ccad16898bea30809a71ae69f70', 'yn');
