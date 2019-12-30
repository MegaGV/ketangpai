/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : ketangpai

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-12-30 16:20:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(255) NOT NULL COMMENT '课程名称',
  `introduce` varchar(255) NOT NULL DEFAULT '' COMMENT '课程简介',
  `code` varchar(255) NOT NULL COMMENT '加课码',
  `teacher` int(11) NOT NULL COMMENT '教师',
  `assistants` varchar(255) NOT NULL DEFAULT '',
  `students` varchar(255) NOT NULL DEFAULT '' COMMENT '学生列表',
  `homeworks` varchar(255) NOT NULL DEFAULT '' COMMENT '作业列表',
  PRIMARY KEY (`id`),
  KEY `teacher` (`teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'JavaEE', '117030802', 'TY94UW', '5', '6', '1,2', '1,2,11');
INSERT INTO `course` VALUES ('2', 'UML', '1738-2', 'MHZ96P', '6', '', '1', '');
INSERT INTO `course` VALUES ('3', 'Alogrim', '117030801、02', 'NXWR4W', '5', '', '', '');
INSERT INTO `course` VALUES ('4', '软件测试', 'software test', 'AS8C9W', '6', '5', '', '');

-- ----------------------------
-- Table structure for homework_content
-- ----------------------------
DROP TABLE IF EXISTS `homework_content`;
CREATE TABLE `homework_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `introduce` varchar(255) NOT NULL DEFAULT '',
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `course` int(11) NOT NULL,
  `reviewed` int(1) NOT NULL DEFAULT '0',
  `uploaded` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course` (`course`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of homework_content
-- ----------------------------
INSERT INTO `homework_content` VALUES ('1', '实验1', '提交demo1', '2019-12-16 16:09:33', '2019-12-22 16:09:36', '1', '2', '2');
INSERT INTO `homework_content` VALUES ('2', '实验2', 'demo2', '2019-12-24 00:23:24', '2019-12-31 00:23:31', '1', '0', '1');
INSERT INTO `homework_content` VALUES ('11', 'a', 'a', '2019-12-28 00:00:00', '2019-12-30 00:00:00', '1', '0', '0');

-- ----------------------------
-- Table structure for homework_upload
-- ----------------------------
DROP TABLE IF EXISTS `homework_upload`;
CREATE TABLE `homework_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL DEFAULT '',
  `file` varchar(255) NOT NULL DEFAULT '',
  `student` int(11) NOT NULL,
  `homework` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `complete` tinyint(1) NOT NULL DEFAULT '0',
  `review` tinyint(1) NOT NULL DEFAULT '0',
  `upload_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `homework` (`homework`),
  KEY `student` (`student`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_upload
-- ----------------------------
INSERT INTO `homework_upload` VALUES ('1', 'this is the homework!', 'http://127.0.0.1:8080/1577693857610.zip', '1', '1', '90', '1', '1', '2019-12-30 08:17:39');
INSERT INTO `homework_upload` VALUES ('2', 'asdasdasd', '', '2', '1', '60', '1', '1', '2019-12-28 13:26:00');
INSERT INTO `homework_upload` VALUES ('3', 'daas', '', '1', '2', '0', '1', '0', '2019-12-28 13:28:28');
INSERT INTO `homework_upload` VALUES ('4', '', '', '2', '2', '0', '0', '0', null);
INSERT INTO `homework_upload` VALUES ('9', '', '', '1', '11', '0', '0', '0', null);
INSERT INTO `homework_upload` VALUES ('10', '', '', '2', '11', '0', '0', '0', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `nameZh` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_student', '学生角色');
INSERT INTO `role` VALUES ('2', 'ROLE_teacher', '教师角色');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `identity` varchar(255) NOT NULL,
  `school` varchar(255) NOT NULL,
  `schoolID` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `courses` varchar(255) NOT NULL DEFAULT '',
  `fieldcourses` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'stu', '123456', '张三', 'student', '重庆理工大学', '11703080201', '111@qq.com', '123', '1,2', '3');
INSERT INTO `user` VALUES ('2', 'stu2', '123456', '张三2', 'student', '重庆理工大学', '11703080202', '222@qq.com', '234', '1', '');
INSERT INTO `user` VALUES ('3', 'stu3', '123456', '张三3', 'student', '重庆理工大学', '11703080203', '', '', '', '');
INSERT INTO `user` VALUES ('4', 'stu4', '123456', '张三4', 'student', '重庆理工大学', '11703080204', '', '', '', '');
INSERT INTO `user` VALUES ('5', 'xcy', '123456', '徐传运', 'teacher', '重庆理工大学', '', '123@qq.com', '1234567890123', '1,3', '');
INSERT INTO `user` VALUES ('6', 'xcy2', '123456', '徐传运2', 'teacher', '重庆理工大学', '', '456@qq.com', '', '2', '');
INSERT INTO `user` VALUES ('11', 'stu5', '123456', '张三5', 'student', '重庆大学', '', '111@qq.com', '22222222', '', '');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '3', '1');
INSERT INTO `user_role` VALUES ('4', '4', '1');
INSERT INTO `user_role` VALUES ('5', '5', '2');
INSERT INTO `user_role` VALUES ('6', '6', '2');
