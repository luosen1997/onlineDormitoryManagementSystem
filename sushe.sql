/*
Navicat MySQL Data Transfer

Source Server         : sql
Source Server Version : 50610
Source Host           : localhost:3307
Source Database       : sushe

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2020-09-23 16:24:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dorm
-- ----------------------------
DROP TABLE IF EXISTS `dorm`;
CREATE TABLE `dorm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `people_count` varchar(255) DEFAULT NULL COMMENT '可居住人数',
  `dorm_towerId` int(11) DEFAULT NULL COMMENT '宿舍楼id',
  `score` varchar(255) DEFAULT NULL COMMENT '得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='宿舍';

-- ----------------------------
-- Records of dorm
-- ----------------------------
INSERT INTO `dorm` VALUES ('9', '一号楼', '1-255', '2020-04-25 15:03:09', '5', '7', '2');
INSERT INTO `dorm` VALUES ('10', '二号楼', '2-255', '2020-09-23 15:32:49', '5', '7', null);

-- ----------------------------
-- Table structure for dorm_tower
-- ----------------------------
DROP TABLE IF EXISTS `dorm_tower`;
CREATE TABLE `dorm_tower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '楼名称',
  `number` varchar(11) DEFAULT NULL COMMENT '楼号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `score` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='宿舍楼';

-- ----------------------------
-- Records of dorm_tower
-- ----------------------------
INSERT INTO `dorm_tower` VALUES ('7', '仙居', '1', '2020-04-25 15:02:47', '0', '15');

-- ----------------------------
-- Table structure for dorm_usersid
-- ----------------------------
DROP TABLE IF EXISTS `dorm_usersid`;
CREATE TABLE `dorm_usersid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dormId` int(11) DEFAULT NULL COMMENT '宿舍',
  `usersId` int(11) DEFAULT NULL COMMENT '用户',
  `dorm_towerId` int(11) DEFAULT NULL COMMENT '宿舍楼',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='宿舍用户关联表';

-- ----------------------------
-- Records of dorm_usersid
-- ----------------------------
INSERT INTO `dorm_usersid` VALUES ('16', '7', '10', '5');

-- ----------------------------
-- Table structure for exchange
-- ----------------------------
DROP TABLE IF EXISTS `exchange`;
CREATE TABLE `exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL COMMENT '原因',
  `usId` int(11) DEFAULT NULL COMMENT '调换申请人',
  `create_time` datetime DEFAULT NULL,
  `state` int(1) DEFAULT '0' COMMENT '1：调换成功，2：调换失败,0：未审核',
  `agree` int(11) DEFAULT '0' COMMENT '1：同意，2：不同意，0：未审核',
  `proposer` varchar(255) DEFAULT NULL COMMENT '1:申请人，2：接受人',
  `otherId` int(11) DEFAULT NULL COMMENT '受邀人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='调换表';

-- ----------------------------
-- Records of exchange
-- ----------------------------
INSERT INTO `exchange` VALUES ('1', null, '10', '2020-05-11 10:00:29', '0', '0', null, '10');
INSERT INTO `exchange` VALUES ('2', null, '23', '2020-09-23 15:25:31', '1', '1', null, '23');
INSERT INTO `exchange` VALUES ('3', null, '23', '2020-09-23 15:26:54', '1', '1', null, '23');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('2', '公告', '下周一大扫除检查', '2020-05-08 15:32:44');
INSERT INTO `notice` VALUES ('3', '通知', '下周五检查\n', '2020-05-09 14:53:01');
INSERT INTO `notice` VALUES ('4', '通知', '大扫除检查', '2020-09-23 15:24:59');

-- ----------------------------
-- Table structure for out
-- ----------------------------
DROP TABLE IF EXISTS `out`;
CREATE TABLE `out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `dormname` varchar(255) DEFAULT NULL COMMENT '宿舍名称',
  `dorm_towername` varchar(255) DEFAULT NULL COMMENT '宿舍楼名称',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='迁出表';

-- ----------------------------
-- Records of out
-- ----------------------------
INSERT INTO `out` VALUES ('1', '1', '1', '1', null);
INSERT INTO `out` VALUES ('2', '学生03', 'dddd', 'asdfss', '2020-05-09 19:35:18');

-- ----------------------------
-- Table structure for performance
-- ----------------------------
DROP TABLE IF EXISTS `performance`;
CREATE TABLE `performance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` varchar(255) DEFAULT '0',
  `usersId` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL COMMENT '原因',
  `pass` int(1) DEFAULT NULL COMMENT '0：重复，1：不重复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of performance
-- ----------------------------
INSERT INTO `performance` VALUES ('1', '11', '2', '2020-04-12 01:22:54', null, null);
INSERT INTO `performance` VALUES ('3', '555', '8', '2020-04-12 15:24:50', null, null);
INSERT INTO `performance` VALUES ('4', '22', '13', '2020-04-12 14:27:21', null, null);
INSERT INTO `performance` VALUES ('9', '50', '10', '2020-04-12 17:04:49', null, null);
INSERT INTO `performance` VALUES ('10', null, '17', '2020-04-13 15:13:16', '重复', '0');
INSERT INTO `performance` VALUES ('18', null, '16', '2020-04-13 15:15:47', null, '1');
INSERT INTO `performance` VALUES ('19', '44', '15', '2020-04-13 15:16:03', '重复', '0');
INSERT INTO `performance` VALUES ('20', null, '13', '2020-04-13 15:21:44', '重复', '0');
INSERT INTO `performance` VALUES ('21', null, '10', '2020-04-13 15:21:49', null, '1');
INSERT INTO `performance` VALUES ('22', '33', '8', '2020-04-13 15:26:38', '重复', '0');

-- ----------------------------
-- Table structure for repairs
-- ----------------------------
DROP TABLE IF EXISTS `repairs`;
CREATE TABLE `repairs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL COMMENT '原因',
  `article` varchar(255) DEFAULT NULL COMMENT '物品',
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT '0' COMMENT '是否成功0：未审核，1：成功，2：失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='报修';

-- ----------------------------
-- Records of repairs
-- ----------------------------
INSERT INTO `repairs` VALUES ('2', 'dddd', 'sss', '19', '2020-04-25 14:10:09', '1');
INSERT INTO `repairs` VALUES ('3', '1', '1', '10', '2020-09-19 13:55:59', '2');
INSERT INTO `repairs` VALUES ('4', '床板坏哦了', '床', '23', '2020-09-23 15:26:02', '1');

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `usersId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '2020-05-09 14:45:00', '10');
INSERT INTO `sign` VALUES ('2', '2020-05-11 10:02:53', '10');
INSERT INTO `sign` VALUES ('3', '2020-09-23 14:04:58', '22');
INSERT INTO `sign` VALUES ('4', '2020-09-23 15:25:42', '23');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '账户',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `role` varchar(255) DEFAULT NULL COMMENT '角色 0-超级管理员 1-宿管 2-学生',
  `nickname` varchar(255) DEFAULT NULL COMMENT '姓名/昵称',
  `tel` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `sex` int(1) DEFAULT NULL COMMENT '性别 1-男 2-女',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `fid` int(11) DEFAULT NULL COMMENT '父级id',
  `academy` varchar(255) DEFAULT NULL COMMENT '院校',
  `class_grade` varchar(255) DEFAULT NULL COMMENT '班级',
  `birthplace` varchar(255) DEFAULT NULL COMMENT '生源地',
  `smoke` int(1) DEFAULT NULL COMMENT '1:吸烟，2：不吸烟',
  `photo` varchar(255) DEFAULT NULL COMMENT '上传照片',
  `state` varchar(255) DEFAULT '1' COMMENT '是否在校，0：否，1：是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '超管', '123456', '0', 'admin', null, null, '0', '2020-04-03 18:09:12', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('8', 'www', '123456', '1', 'www', '15513064584', '123655@225.com', '1', '2020-04-11 14:42:01', '2', null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('10', '学生', '123456', '2', 'ssss', '15513065489', 'ssss@131.com', '1', '2020-04-12 11:51:20', '2', 'sdfas', 'dsfs', 'sdf', '1', null, '1');
INSERT INTO `users` VALUES ('12', '超管2', '123456', '0', '超管', '15513098456', '15151@122.com', '1', '2020-04-12 14:09:57', null, null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('14', '老师2', '123456', '1', '老师2', '15513064589', '111111@22.com', '1', '2020-04-12 14:11:10', null, null, null, null, '1', null, '1');
INSERT INTO `users` VALUES ('15', '小雪', '123456', '1', '小雪', '13699056485', '4444@11.com', '2', '2020-04-12 16:57:50', '2', null, null, null, null, null, '1');
INSERT INTO `users` VALUES ('17', '老李', '123456', '1', '老李', '15513066445', 'sss@111.com', '1', '2020-04-12 16:59:06', '2', 'kkkk', 'ddd', 'ssss', '1', 'http://localhost:8080/hc-sys/files/20200424091823837W08.jpg', '1');
INSERT INTO `users` VALUES ('18', '宿管01', '123456', '1', '宿管', '15513098156', '1111@qq.com', '1', null, null, 'sss', 'ddd', 'aaa', '1', null, '1');
INSERT INTO `users` VALUES ('21', '宿管0', '123456', '1', '宿管', '15513065458', '111@qq.com', '1', '2020-04-25 14:30:38', null, 'sadsf', 'sdf', 'df', '1', 'http://localhost:8080/hc-sys/files/20200425143011628E0S.jpg', '1');
INSERT INTO `users` VALUES ('22', '小王', '123123', '2', '王宇', '10158794125', '150@qq.com', '1', '2020-09-23 14:04:26', null, '1', '202007', '北京', '2', 'http://localhost:8080/ss/files/2020092314034003435P.png', '1');
INSERT INTO `users` VALUES ('23', '小李', '123123', '2', '李玉坤', '15935748620', '155@qq.com', '2', '2020-09-23 15:24:11', null, '北大', '1', '5', '2', 'http://localhost:8080/ss/files/2020092315234398755f.png', '1');
