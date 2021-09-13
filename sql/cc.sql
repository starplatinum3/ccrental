/*
Navicat MySQL Data Transfer

Source Server         : booklib
Source Server Version : 50514
Source Host           : localhost:3307
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2021-09-13 08:59:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for allocation
-- ----------------------------
DROP TABLE IF EXISTS `allocation`;
CREATE TABLE `allocation` (
`alloc_time`  datetime NULL DEFAULT NULL ,
`alloc_id`  int(11) NOT NULL AUTO_INCREMENT ,
`car_id`  int(11) NULL DEFAULT NULL ,
`net_in_id`  int(11) NULL DEFAULT NULL ,
`net_out_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`alloc_id`),
FOREIGN KEY (`car_id`) REFERENCES `car_info` (`car_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`net_in_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`net_out_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_19` (`car_id`) USING BTREE ,
INDEX `FK_Relationship_21` (`net_in_id`) USING BTREE ,
INDEX `FK_Relationship_22` (`net_out_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='allocation'
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of allocation
-- ----------------------------
BEGIN;
INSERT INTO `allocation` VALUES ('2021-09-09 14:24:04', '2', '1', '2', '1'), ('2021-09-11 14:50:32', '3', '21', '2', '1'), ('2021-09-12 11:51:45', '4', '35', '5', '6'), ('2021-09-12 12:08:45', '5', '34', '6', '5'), ('2021-09-12 14:41:18', '6', '34', '6', '6'), ('2021-09-13 08:54:16', '7', '50', '5', '6');
COMMIT;

-- ----------------------------
-- Table structure for car_category
-- ----------------------------
DROP TABLE IF EXISTS `car_category`;
CREATE TABLE `car_category` (
`cat_id`  int(11) NOT NULL AUTO_INCREMENT ,
`cat_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`cat_descrip`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`cat_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='car_category'
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of car_category
-- ----------------------------
BEGIN;
INSERT INTO `car_category` VALUES ('1', '1', '经济型'), ('7', '牛车', '牛车'), ('9', '9', '9'), ('10', '10', '555'), ('12', '12', 'jo护车'), ('13', '13', '跑车'), ('14', '14', '特斯拉'), ('15', '宝马', '宝马'), ('16', '豪华型', '豪华型');
COMMIT;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info` (
`car_id`  int(11) NOT NULL AUTO_INCREMENT ,
`net_id`  int(11) NULL DEFAULT NULL ,
`type_id`  int(11) NULL DEFAULT NULL ,
`license`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`car_status`  int(11) NULL DEFAULT NULL COMMENT 'l  车辆信息（至少包括：车辆编号，牌照号，\n所属车型，所在网点，\n当前状态（空闲 1，在途 2，修理3  报废4 等）\n等信息）\n\n当前状态（空闲 1，在途 2，报废3  ' ,
PRIMARY KEY (`car_id`),
FOREIGN KEY (`net_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`type_id`) REFERENCES `car_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_15` (`net_id`) USING BTREE ,
INDEX `FK_Relationship_23` (`type_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='car_info'
AUTO_INCREMENT=51

;

-- ----------------------------
-- Records of car_info
-- ----------------------------
BEGIN;
INSERT INTO `car_info` VALUES ('1', '2', '1', '4', '3'), ('17', '2', '1', '1', '1'), ('18', '2', '1', '1', '3'), ('19', '2', '1', '1', '3'), ('21', '2', '2', '333', '3'), ('22', '2', '3', '666', '3'), ('23', '1', '4', '7777', '2'), ('24', '1', '2', '浙A-5555', '1'), ('25', '1', '2', '浙A-5555', '1'), ('26', '2', '2', '6', '3'), ('27', '2', '2', '6', '1'), ('28', '1', '6', '浙江A-8888', '3'), ('29', '1', '7', 'longzelora', '2'), ('30', '1', '8', '浙A-53535', '3'), ('31', '5', '8', '杜王町A-111', '2'), ('32', '4', '7', '埃及A-111', '2'), ('33', '4', '6', '凯菲特A-111', '2'), ('34', '6', '9', '浙A-6666', '2'), ('35', '5', '9', '浙A-888', '2'), ('36', '6', '9', '浙A-999', '1'), ('37', '6', '9', '浙A-333', '1'), ('38', '6', '9', '浙A-555', '1'), ('39', '5', '9', '东京A-555', '1'), ('40', '5', '9', '东京A-111', '2'), ('41', '5', '8', 'djA-666', '2'), ('42', '5', '8', 'djA-777', '1'), ('43', '5', '8', 'djA-888', '1'), ('44', '7', '9', '浙A-9999', '1'), ('45', '7', '9', '浙A-11111', '2'), ('46', '7', '9', '浙A-22222', '1'), ('47', '7', '9', '浙A-33333', '1'), ('48', '6', '9', '浙A-55555', '1'), ('49', '6', '9', '浙A-555555', '1'), ('50', '5', '9', '浙A-5555555', '1');
COMMIT;

-- ----------------------------
-- Table structure for car_type
-- ----------------------------
DROP TABLE IF EXISTS `car_type`;
CREATE TABLE `car_type` (
`brand`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type_id`  int(11) NOT NULL AUTO_INCREMENT ,
`cat_id`  int(11) NULL DEFAULT NULL ,
`type_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`displacement`  decimal(10,2) NULL DEFAULT NULL ,
`gear`  int(11) NULL DEFAULT NULL ,
`seat_num`  int(11) NULL DEFAULT NULL ,
`price`  decimal(10,2) NULL DEFAULT NULL ,
`pic`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`type_id`),
FOREIGN KEY (`cat_id`) REFERENCES `car_category` (`cat_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_18` (`cat_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='car_type'
AUTO_INCREMENT=10

;

-- ----------------------------
-- Records of car_type
-- ----------------------------
BEGIN;
INSERT INTO `car_type` VALUES ('大众', '1', '1', '朗逸', '1.00', '1', '1', '1.00', '1'), ('1', '2', '1', '1', '1.00', '1', '1', '1.00', '1'), ('3', '3', '7', '19', '3.00', '2', '3', '3.00', '3'), ('3', '4', '9', '20', '3.00', '2', '3', '3.00', '3'), ('宝马', '5', '7', '宝马', '5.00', '1', '5', '5.00', 'img/baomax4.jpg'), ('浙A-9999', '6', '7', '特斯拉', '1.00', '1', '4', '2.00', 'img/tesla.jfif'), ('longzelora', '7', '1', '压路机', '2.00', '1', '2', '2.00', 'img/longzerora.jpg'), ('joseph', '8', '12', '载具杀手', '4.00', '1', '4', '4.00', 'img/longshelan.jfif'), ('奔驰', '9', '16', '奔驰A级(进口)', '2.00', '1', '4', '2.00', 'img/benchi_a.jpg');
COMMIT;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
`coupon_id`  int(11) NOT NULL AUTO_INCREMENT ,
`content`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`deduc_amou`  decimal(10,2) NULL DEFAULT NULL ,
`start_time`  datetime NULL DEFAULT NULL ,
`end_time`  datetime NULL DEFAULT NULL ,
`user_id`  int(11) NULL DEFAULT NULL ,
`net_id`  int(11) NULL DEFAULT NULL COMMENT '网点' ,
`status`  int(11) NULL DEFAULT 1 COMMENT '状态（未被领取1，已经被领取2，已经使用3）' ,
PRIMARY KEY (`coupon_id`),
FOREIGN KEY (`net_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `coupon_net_info_net_id_fk` (`net_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='coupon'
AUTO_INCREMENT=50

;

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
INSERT INTO `coupon` VALUES ('1', '1', '1.00', '1970-01-01 00:00:00', '2021-09-21 20:45:20', '1', '1', '3'), ('2', '123132', '1.00', '2021-09-06 00:00:00', '2021-09-08 00:00:00', '0', '1', '2'), ('3', '55555', '3.00', '2021-09-20 00:00:00', '2021-09-30 00:00:00', '1', '1', '2'), ('4', '111', '1.00', '2021-09-10 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('5', '111', '1.00', '2021-09-10 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('6', '111', '1.00', '2021-09-10 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('7', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', null, '1', '1'), ('8', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '2'), ('9', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '2'), ('10', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '2'), ('11', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('12', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('13', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('14', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('15', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('16', '111', '1.00', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '1', '1', '3'), ('17', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('18', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('19', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('20', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('21', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('22', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('23', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('24', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('25', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', '1', '5', '3'), ('26', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('27', '！！！！', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '5', '1'), ('28', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('29', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', '1', '4', '3'), ('30', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('31', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('32', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('33', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('34', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('35', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('36', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('37', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('38', 'ddddd', '1.00', '2021-09-11 00:00:00', '2021-09-20 00:00:00', null, '4', '1'), ('39', '优惠券', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '1', '6', '3'), ('40', '优惠券', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '1', '6', '3'), ('41', '优惠券', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1'), ('42', '优惠券', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '1', '6', '3'), ('43', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '1', '6', '3'), ('44', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1'), ('45', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1'), ('46', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1'), ('47', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1'), ('48', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '1', '6', '3'), ('49', '1111', '1.00', '2021-09-12 00:00:00', '2021-09-20 00:00:00', null, '6', '1');
COMMIT;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
`emp_id`  int(11) NOT NULL AUTO_INCREMENT ,
`net_id`  int(11) NULL DEFAULT NULL ,
`emp_name`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`emp_id`),
FOREIGN KEY (`net_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_20` (`net_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='emp'
AUTO_INCREMENT=10

;

-- ----------------------------
-- Records of emp
-- ----------------------------
BEGIN;
INSERT INTO `emp` VALUES ('1', '1', '1', '1'), ('2', '2', '2', '2'), ('3', null, '3', '3'), ('4', '1', '4', '4'), ('5', '4', null, null), ('6', '5', '5', '5'), ('7', '4', '6', '6'), ('8', '6', '8', '8'), ('9', '7', '小王', '123');
COMMIT;

-- ----------------------------
-- Table structure for net_info
-- ----------------------------
DROP TABLE IF EXISTS `net_info`;
CREATE TABLE `net_info` (
`net_id`  int(11) NOT NULL AUTO_INCREMENT ,
`net_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`city`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`addr`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`net_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='net_info'
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of net_info
-- ----------------------------
BEGIN;
INSERT INTO `net_info` VALUES ('1', 'net', '21', '1', '1'), ('2', '2', '2222', '2', '2'), ('3', '3', '3', '3', '3'), ('4', '鱼死网破', '阿尔匹斯', '诺桑德', '18232144324'), ('5', '杜王町停车场', '东京', '杜王町', '18288888888'), ('6', '城站', '杭州', '杭州东', '1333'), ('7', '杭州西', '杭州', '杭州西', '18344444444'), ('8', '5555', '555', '555', '18344444444');
COMMIT;

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
`prom_id`  int(11) NOT NULL AUTO_INCREMENT ,
`type_id`  int(11) NULL DEFAULT NULL ,
`net_id`  int(11) NULL DEFAULT NULL ,
`discount`  decimal(10,2) NULL DEFAULT NULL ,
`prom_quantity`  int(11) NULL DEFAULT NULL ,
`start_time`  datetime NULL DEFAULT NULL ,
`end_time`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`prom_id`),
FOREIGN KEY (`type_id`) REFERENCES `car_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`net_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_17` (`type_id`) USING BTREE ,
INDEX `FK_Relationship_6` (`net_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='promotion'
AUTO_INCREMENT=68

;

-- ----------------------------
-- Records of promotion
-- ----------------------------
BEGIN;
INSERT INTO `promotion` VALUES ('1', '1', '1', '1.00', '0', '1970-01-01 00:00:00', '2021-09-18 12:56:17'), ('2', '1', '1', '1.00', '1', '2021-01-01 00:00:00', '2021-01-05 00:00:00'), ('3', '4', '1', '1.00', '0', '2021-05-05 00:00:00', '2021-09-19 00:00:00'), ('4', '2', '1', '9.00', '2', '2021-09-10 00:00:00', '2021-09-20 00:00:00'), ('5', '2', '1', '8.00', '2', '2021-09-20 00:00:00', '2021-09-30 00:00:00'), ('6', '2', '1', '5.00', '2', '2021-09-09 00:00:00', '2021-09-10 00:00:00'), ('7', '1', '1', '5.00', '0', '2021-09-09 00:00:00', '2021-09-10 00:00:00'), ('8', '1', '1', '4.00', '1', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('9', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('10', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('11', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('12', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('13', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('14', '1', '1', '6.00', '2', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('15', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('16', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('17', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('18', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('19', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('20', '1', '1', '6.00', '3', '2021-09-09 00:00:00', '2021-09-20 00:00:00'), ('21', '8', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('22', '8', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('23', '8', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('24', '8', '5', '6.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('25', '8', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('26', '8', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('27', '7', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('28', '7', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('29', '7', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('30', '7', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('31', '7', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('32', '6', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('33', '6', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('34', '6', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('35', '6', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('36', '5', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('37', '5', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('38', '5', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('39', '5', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('40', '5', '5', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('41', '7', '4', '7.00', '6', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('42', '7', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('43', '7', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('44', '8', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('45', '8', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('46', '8', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('47', '5', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('48', '5', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('49', '5', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('50', '5', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('51', '6', '4', '7.00', '6', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('52', '6', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('53', '6', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('54', '1', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('55', '1', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('56', '1', '4', '7.00', '7', '2021-09-11 00:00:00', '2021-09-20 00:00:00'), ('57', '7', '4', '7.30', '1', '2021-09-11 00:00:00', '2021-09-11 00:00:00'), ('58', '9', '6', '6.00', '3', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('59', '9', '6', '6.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('60', '9', '6', '6.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('61', '9', '6', '6.00', '6', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('62', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('63', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('64', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('65', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('66', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00'), ('67', '8', '6', '7.00', '5', '2021-09-12 00:00:00', '2021-09-20 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for scrap
-- ----------------------------
DROP TABLE IF EXISTS `scrap`;
CREATE TABLE `scrap` (
`crap_id`  int(11) NOT NULL AUTO_INCREMENT ,
`car_id`  int(11) NULL DEFAULT NULL ,
`emp_id`  int(11) NULL DEFAULT NULL ,
`op_time`  datetime NULL DEFAULT NULL ,
`remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`crap_id`),
FOREIGN KEY (`car_id`) REFERENCES `car_info` (`car_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`emp_id`) REFERENCES `emp` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_11` (`car_id`) USING BTREE ,
INDEX `FK_Relationship_12` (`emp_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='scrap'
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of scrap
-- ----------------------------
BEGIN;
INSERT INTO `scrap` VALUES ('1', '1', '3', '2021-09-07 20:15:06', ''), ('2', '1', '3', '2021-09-07 20:16:51', ''), ('5', '18', '3', '2021-09-08 13:36:07', '412414'), ('8', '19', '3', '2021-09-08 13:38:30', ''), ('11', '22', '3', '2021-09-09 15:28:09', '3333'), ('13', '28', '4', '2021-09-10 11:42:39', ''), ('15', '26', '3', '2021-09-11 14:43:42', '报废空闲车辆'), ('16', '30', '3', '2021-09-11 14:44:16', '报废空闲车辆');
COMMIT;

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
`order_id`  int(11) NOT NULL AUTO_INCREMENT ,
`prom_id`  int(11) NULL DEFAULT NULL ,
`net_in_id`  int(11) NULL DEFAULT NULL ,
`user_id`  int(11) NULL DEFAULT NULL ,
`car_id`  int(11) NULL DEFAULT NULL ,
`net_out_id`  int(11) NULL DEFAULT NULL ,
`coupon_id`  int(11) NULL DEFAULT NULL ,
`get_time`  datetime NULL DEFAULT NULL ,
`ret_time`  datetime NULL DEFAULT NULL ,
`rental_dur`  bigint(20) NULL DEFAULT NULL ,
`raw_price`  decimal(10,2) NULL DEFAULT NULL ,
`final_price`  decimal(10,2) NULL DEFAULT NULL ,
`status`  int(11) NULL DEFAULT NULL COMMENT '\n订单状态（0 下单，1 提车，2还车，3取消等）' ,
PRIMARY KEY (`order_id`),
FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`car_id`) REFERENCES `car_info` (`car_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`net_out_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`prom_id`) REFERENCES `promotion` (`prom_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`net_in_id`) REFERENCES `net_info` (`net_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Relationship_10` (`coupon_id`) USING BTREE ,
INDEX `FK_Relationship_14` (`car_id`) USING BTREE ,
INDEX `FK_Relationship_16` (`net_out_id`) USING BTREE ,
INDEX `FK_Relationship_24` (`prom_id`) USING BTREE ,
INDEX `FK_Relationship_8` (`user_id`) USING BTREE ,
INDEX `FK_Relationship_9` (`net_in_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='tbl_order'
AUTO_INCREMENT=39

;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
BEGIN;
INSERT INTO `tbl_order` VALUES ('1', '1', '1', '1', '1', '1', '1', '2021-01-01 00:00:00', '2021-01-02 00:00:00', '1', '1.00', '0.00', '2'), ('2', '1', '1', '1', '1', '2', '1', '2021-01-01 00:00:00', '2021-01-05 00:00:00', '4', '4.00', '0.30', '1'), ('3', '1', '1', '1', '1', '1', '1', '2021-04-05 00:00:00', '2021-04-06 00:00:00', '1', '1.00', '0.00', '2'), ('4', '1', '2', '1', '1', '1', '1', '2021-01-01 00:00:00', '2021-01-06 00:00:00', '5', '5.00', '0.40', '3'), ('5', '1', '2', '1', '1', '2', '1', '2021-05-05 00:00:00', '2021-05-08 00:00:00', '3', '3.00', '0.20', '1'), ('6', '1', '2', '1', '1', '2', '1', '2021-06-06 00:00:00', '2021-06-07 00:00:00', '1', '1.00', '0.00', '1'), ('7', '1', '2', '1', '1', '1', '1', '2021-05-05 00:00:00', '2021-05-11 00:00:00', '6', '6.00', '0.50', '4'), ('8', '1', '1', '1', '1', '2', '1', '2022-05-05 00:00:00', '2022-06-06 00:00:00', '32', '32.00', '3.20', '1'), ('10', '1', '2', '1', '21', '1', '1', '2021-09-09 00:00:00', '2021-09-10 00:00:00', '1', '1.00', '1.00', '4'), ('11', '7', '1', '1', '23', '2', '1', '2021-09-09 00:00:00', '2021-10-10 00:00:00', '31', '31.00', '15.00', '1'), ('12', '7', '1', '1', '23', '2', '1', '2021-09-09 00:00:00', '2021-10-10 00:00:00', '31', '31.00', '15.00', '1'), ('13', null, '1', '1', '23', '1', null, '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '2.00', '2.00', '1'), ('14', null, '1', '1', '23', '2', '4', '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '2.00', '1.00', '1'), ('15', null, '1', '1', '23', '2', '4', '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '2.00', '1.00', '1'), ('16', null, '1', '1', '23', '2', '4', '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '2.00', '1.00', '1'), ('17', null, '1', '1', '23', '2', '5', '2021-09-10 00:00:00', '2021-09-11 00:00:00', '1', '1.00', '0.00', '1'), ('18', null, '1', '1', '23', '2', '6', '2021-09-10 00:00:00', '2021-09-11 00:00:00', '1', '1.00', '0.00', '1'), ('19', '8', '1', '1', '23', '2', '1', '2021-09-09 00:00:00', '2021-09-10 00:00:00', '1', '1.00', '0.00', '1'), ('20', '14', '1', '1', '23', '2', '15', '2021-09-09 00:00:00', '2021-09-20 00:00:00', '11', '11.00', '6.00', '1'), ('21', null, '1', '1', '29', '1', '13', '2021-09-10 00:00:00', '2021-09-20 00:00:00', '10', '20.00', '19.00', '1'), ('23', null, '1', '1', '29', '1', '4', '2021-09-10 00:00:00', '2021-09-11 00:00:00', '1', '2.00', '1.00', '1'), ('24', null, '1', '1', '29', '3', '11', '2021-09-10 00:00:00', '2021-09-11 00:00:00', '1', '2.00', '1.00', '1'), ('25', null, '1', '1', '29', '2', '14', '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '4.00', '3.00', '1'), ('26', null, '1', '1', '28', '2', '16', '2021-09-10 00:00:00', '2021-09-12 00:00:00', '2', '4.00', '3.00', '1'), ('27', '3', '1', '1', '23', '1', '12', '2021-09-10 00:00:00', '2021-09-11 00:00:00', '1', '3.00', '0.20', '1'), ('28', '41', '4', '1', '32', '5', '29', '2021-09-11 00:00:00', '2021-09-20 00:00:00', '9', '18.00', '11.90', '1'), ('29', '51', '4', '1', '33', '2', null, '2021-09-11 00:00:00', '2021-09-20 00:00:00', '9', '18.00', '12.60', '1'), ('30', '58', '6', '1', '34', '5', '39', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '16.00', '9.00', '3'), ('31', null, '6', '1', '31', '5', null, '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '32.00', '32.00', '1'), ('32', '58', '5', '1', '35', '6', '40', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '16.00', '9.00', '3'), ('33', '59', '6', '1', '34', '6', '43', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '16.00', '9.00', '3'), ('34', null, '6', '1', '45', '7', null, '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '16.00', '16.00', '1'), ('35', '60', '5', '1', '50', '6', '48', '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '16.00', '9.00', '3'), ('36', '24', '5', '1', '41', '5', null, '2021-09-12 00:00:00', '2021-09-20 00:00:00', '8', '32.00', '19.20', '1'), ('37', null, '5', '1', '40', '5', '25', '2021-09-11 00:00:00', '2021-09-20 00:00:00', '9', '18.00', '17.00', '1'), ('38', '58', '7', '1', '34', '6', '42', '2021-09-13 00:00:00', '2021-09-20 00:00:00', '7', '14.00', '7.80', '1');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`user_id`  int(11) NOT NULL AUTO_INCREMENT ,
`user_name`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  int(11) NULL DEFAULT NULL COMMENT '性别 1男 2女，就没有 0 的默认值问题了' ,
`password`  varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`mailbox`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`city`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`reg_time`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='user'
AUTO_INCREMENT=7

;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '1', '1', '1', '10000', '1', '1', '2021-09-02 00:00:00'), ('2', '5', '2', '5', '1111', 'null', 'null', '2021-09-09 00:00:00'), ('3', '7', '2', '7', '131321', '2500000@qq.com', 'null', '2021-09-09 00:00:00'), ('4', 'joseph', '1', 'joseph', '412131', '1111', 'null', '2021-09-09 00:00:00'), ('5', '555', '1', '555', '18353242456', '4214121312@qq.com', '杭州', '2021-09-11 12:51:22'), ('6', 'steve', '2', 'steve', '18333333333', '253432@qq.com', '浙江', '2021-09-11 16:28:03');
COMMIT;

-- ----------------------------
-- Auto increment value for allocation
-- ----------------------------
ALTER TABLE `allocation` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for car_category
-- ----------------------------
ALTER TABLE `car_category` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for car_info
-- ----------------------------
ALTER TABLE `car_info` AUTO_INCREMENT=51;

-- ----------------------------
-- Auto increment value for car_type
-- ----------------------------
ALTER TABLE `car_type` AUTO_INCREMENT=10;

-- ----------------------------
-- Auto increment value for coupon
-- ----------------------------
ALTER TABLE `coupon` AUTO_INCREMENT=50;

-- ----------------------------
-- Auto increment value for emp
-- ----------------------------
ALTER TABLE `emp` AUTO_INCREMENT=10;

-- ----------------------------
-- Auto increment value for net_info
-- ----------------------------
ALTER TABLE `net_info` AUTO_INCREMENT=9;

-- ----------------------------
-- Auto increment value for promotion
-- ----------------------------
ALTER TABLE `promotion` AUTO_INCREMENT=68;

-- ----------------------------
-- Auto increment value for scrap
-- ----------------------------
ALTER TABLE `scrap` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for tbl_order
-- ----------------------------
ALTER TABLE `tbl_order` AUTO_INCREMENT=39;

-- ----------------------------
-- Auto increment value for user
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=7;



SELECT
tbl_order.order_id AS order_id,
tbl_order.prom_id AS prom_id,
tbl_order.net_in_id AS ret_net_id,
tbl_order.user_id,
tbl_order.car_id,
tbl_order.net_out_id AS get_net_id,
tbl_order.coupon_id,
tbl_order.get_time,
tbl_order.ret_time,
tbl_order.rental_dur,
tbl_order.raw_price,
tbl_order.final_price,
tbl_order.`status`,
promotion.discount,
`user`.user_name,
car_type.brand,
car_type.type_name,
coupon.deduc_amou,
car_info.license,
`user`.phone AS user_phone,
`user`.mailbox,
net_get.net_name AS get_net_name,
net_ret.net_name AS ret_net_name
FROM
tbl_order
left JOIN promotion ON tbl_order.prom_id = promotion.prom_id
left JOIN net_info AS net_get ON tbl_order.net_out_id = net_get.net_id
left JOIN `user` ON tbl_order.user_id = `user`.user_id
left JOIN car_info ON tbl_order.car_id = car_info.car_id
left JOIN coupon ON  tbl_order.coupon_id = coupon.coupon_id
left JOIN car_type ON  car_info.type_id = car_type.type_id
left JOIN net_info AS net_ret ON tbl_order.net_in_id = net_ret.net_id 
