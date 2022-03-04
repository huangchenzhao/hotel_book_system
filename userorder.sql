/*
 Navicat Premium Data Transfer

 Source Server         : database
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : localhost:3306
 Source Schema         : hotelbook

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 04/03/2022 16:59:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userorder
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder`  (
  `o_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NULL DEFAULT NULL,
  `u_id` bigint(20) NULL DEFAULT NULL,
  `h_id` bigint(20) NULL DEFAULT NULL,
  `check_in` date NULL DEFAULT NULL,
  `check_out` date NULL DEFAULT NULL,
  `totalprice` float NULL DEFAULT NULL,
  `quantity` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `comment` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `truename` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`o_id`) USING BTREE,
  INDEX `FK_order_hotel`(`h_id`) USING BTREE,
  INDEX `FK_order_room`(`r_id`) USING BTREE,
  INDEX `FK_user_order`(`u_id`) USING BTREE,
  CONSTRAINT `FK_order_hotel` FOREIGN KEY (`h_id`) REFERENCES `hotel` (`h_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_order_room` FOREIGN KEY (`r_id`) REFERENCES `room` (`r_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_user_order` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder` VALUES (1, 3, 6, 2, '2022-03-04', '2022-03-05', 160, 1, 0, '暂无评论', NULL, NULL, NULL, NULL);
INSERT INTO `userorder` VALUES (2, 1, 6, 1, '2022-03-04', '2022-03-05', 150, 1, 0, '暂无评论', NULL, NULL, NULL, NULL);
INSERT INTO `userorder` VALUES (3, 2, 6, 1, '2022-03-04', '2022-03-05', 240, 2, 0, '暂无评论', NULL, NULL, NULL, NULL);
INSERT INTO `userorder` VALUES (4, 3, 1, 2, '2022-03-04', '2022-03-05', 160, 1, 0, '暂无评论', NULL, NULL, NULL, NULL);
INSERT INTO `userorder` VALUES (5, 1, 1, 1, '2022-03-04', '2022-03-05', 150, 1, 0, '暂无评论', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
