/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : DB_BUSINESS_PLATFORM

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-05-08 15:17:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for T_AUCTION_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_AUCTION_INFO`;
CREATE TABLE `T_AUCTION_INFO` (
  `auction_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_number` varchar(50) NOT NULL DEFAULT '',
  `deadline` int(11) NOT NULL DEFAULT '0',
  `add_price` double NOT NULL DEFAULT '1' COMMENT '最小加价1元',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`auction_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_AUCTION_RECORD
-- ----------------------------
DROP TABLE IF EXISTS `T_AUCTION_RECORD`;
CREATE TABLE `T_AUCTION_RECORD` (
  `auction_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_number` varchar(50) NOT NULL DEFAULT '',
  `price` double NOT NULL DEFAULT '0' COMMENT '竞拍价',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '提交竞价的用户',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`auction_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_CART
-- ----------------------------
DROP TABLE IF EXISTS `T_CART`;
CREATE TABLE `T_CART` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_number` varchar(50) NOT NULL DEFAULT '',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '加入购物车的某商品数量',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_LOGISTIC_INFO
-- ----------------------------
DROP TABLE IF EXISTS `T_LOGISTIC_INFO`;
CREATE TABLE `T_LOGISTIC_INFO` (
  `logistic_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`logistic_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_LOGISTIC_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for T_ORDER
-- ----------------------------
DROP TABLE IF EXISTS `T_ORDER`;
CREATE TABLE `T_ORDER` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) NOT NULL DEFAULT '' COMMENT '系统生成',
  `deal_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `prod_number` varchar(50) NOT NULL DEFAULT '',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '下订单的某商品数量',
  `price` double NOT NULL DEFAULT '0' COMMENT '某订单中某商品的总价',
  `logistic_info_code` varchar(5) DEFAULT '' COMMENT '物流信息编码',
  `is_paid` tinyint(4) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建订单的用户id',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_PRODUCT
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT`;
CREATE TABLE `T_PRODUCT` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) NOT NULL DEFAULT '' COMMENT '系统生成的商品编号',
  `name` varchar(100) NOT NULL DEFAULT '',
  `image_main` varchar(100) NOT NULL DEFAULT '' COMMENT '展示的图片文件名',
  `description` text,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `price` double NOT NULL DEFAULT '0',
  `type_code` varchar(5) NOT NULL DEFAULT '' COMMENT '商品类别编码',
  `sale_way_code` varchar(5) NOT NULL DEFAULT '' COMMENT '出售形式编码',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '发布该商品的用户(一般为管理员)',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sell_status` int(11) NOT NULL DEFAULT '-1' COMMENT '-1 ：下架 0 : 已售出 1 ：上架',
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_PRODUCT_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT_TYPE`;
CREATE TABLE `T_PRODUCT_TYPE` (
  `prod_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`prod_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_PRODUCT_TYPE
-- ----------------------------
INSERT INTO `T_PRODUCT_TYPE` VALUES ('1', 'MEATS', '精选肉类', '2019-05-01 20:44:56');
INSERT INTO `T_PRODUCT_TYPE` VALUES ('2', 'VGTBS', '新鲜蔬菜', '2019-05-01 20:44:56');
INSERT INTO `T_PRODUCT_TYPE` VALUES ('3', 'FRUIT', '新鲜水果', '2019-05-01 20:44:56');
INSERT INTO `T_PRODUCT_TYPE` VALUES ('4', 'GRAIN', '粮油副食', '2019-05-01 20:44:56');
INSERT INTO `T_PRODUCT_TYPE` VALUES ('5', 'DRYFD', '精选干货', '2019-05-01 20:44:56');
INSERT INTO `T_PRODUCT_TYPE` VALUES ('6', 'NUTEG', '营养蛋品', '2019-05-01 20:44:56');

-- ----------------------------
-- Table structure for T_SALE_WAY
-- ----------------------------
DROP TABLE IF EXISTS `T_SALE_WAY`;
CREATE TABLE `T_SALE_WAY` (
  `sale_way_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`sale_way_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_SALE_WAY
-- ----------------------------
INSERT INTO `T_SALE_WAY` VALUES ('1', 'NORML', '正常出售', '2019-05-01 20:44:56');
INSERT INTO `T_SALE_WAY` VALUES ('2', 'AUCTI', '竞拍', '2019-05-01 20:44:56');

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `power` varchar(20) NOT NULL DEFAULT '' COMMENT '公司主体账号/超级管理员/管理员/用户',
  `score` int(11) DEFAULT '0',
  `balance` double NOT NULL DEFAULT '0' COMMENT '余额',
  `address` varchar(1000) DEFAULT '' COMMENT '收货地址，通过'',''分割',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;