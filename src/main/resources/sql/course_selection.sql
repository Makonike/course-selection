/*
 Navicat Premium Data Transfer

 Source Server         : Makonike
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : course_selection

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 09/09/2021 19:32:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for conn_class_grade
-- ----------------------------
DROP TABLE IF EXISTS `conn_class_grade`;
CREATE TABLE `conn_class_grade`  (
  `class_grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  PRIMARY KEY (`class_grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conn_class_grade
-- ----------------------------

-- ----------------------------
-- Table structure for conn_class_institute
-- ----------------------------
DROP TABLE IF EXISTS `conn_class_institute`;
CREATE TABLE `conn_class_institute`  (
  `class_institute_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `institute_id` int(11) NOT NULL,
  PRIMARY KEY (`class_institute_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conn_class_institute
-- ----------------------------

-- ----------------------------
-- Table structure for conn_course_coursedel
-- ----------------------------
DROP TABLE IF EXISTS `conn_course_coursedel`;
CREATE TABLE `conn_course_coursedel`  (
  `course_coursedel_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `coursedel_id` int(11) NOT NULL,
  PRIMARY KEY (`course_coursedel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conn_course_coursedel
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `removed` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `class_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名',
  `institute_id` int(11) NOT NULL COMMENT '学院id',
  `grade_id` int(11) NOT NULL COMMENT '年级id',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `removed` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES (1, '计科2班', 0, 0, '2021-09-09 19:19:08', 0);

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程类型:人文社科/自然科学',
  `course_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课时间段',
  `course_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_credit` float(2, 2) NOT NULL DEFAULT 0.00 COMMENT '学分',
  `course_hour` int(11) NOT NULL DEFAULT 0 COMMENT '学时',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `removed` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------

-- ----------------------------
-- Table structure for t_coursedel
-- ----------------------------
DROP TABLE IF EXISTS `t_coursedel`;
CREATE TABLE `t_coursedel`  (
  `coursedel_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `exp_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `max_num` int(11) NOT NULL DEFAULT 0,
  `now_num` int(11) NOT NULL DEFAULT 0,
  `coursedel_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选课信息详情',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '选课信息状态 0-生效 1-过期',
  PRIMARY KEY (`coursedel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_coursedel
-- ----------------------------

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`  (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_grade
-- ----------------------------

-- ----------------------------
-- Table structure for t_institute
-- ----------------------------
DROP TABLE IF EXISTS `t_institute`;
CREATE TABLE `t_institute`  (
  `institute_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院',
  `institute_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `institute_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`institute_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_institute
-- ----------------------------

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_no` int(20) NOT NULL COMMENT '学号',
  `student_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sutdent_sex` tinyint(2) NOT NULL COMMENT '学生性别 0-男 1-女',
  `studnet_class_id` int(11) NOT NULL COMMENT '班级',
  `selected_course_count` int(11) NOT NULL DEFAULT 0 COMMENT '已选课程数',
  `removed` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
