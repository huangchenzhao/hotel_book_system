/*
 Navicat Premium Data Transfer

 Source Server         : 云服务器
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : 101.35.31.134:3306
 Source Schema         : hotelbook

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 12/03/2022 22:48:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `a_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(11) NULL DEFAULT NULL,
  `province` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `district` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 510116, '四川省', '成都市', '双流区', '四川省成都市双流区江安校区101号');
INSERT INTO `address` VALUES (2, 510107, '四川省', '成都市', '武侯区', '四川省成都市武侯区望江校区202号');
INSERT INTO `address` VALUES (5, 110105, '北京市', '市辖区', '朝阳区', '北京市朝阳区北大路222号');
INSERT INTO `address` VALUES (6, 230101, '黑龙江省', '哈尔滨市', '道里区', '黑龙江省哈尔滨市道里区小吃街404号');
INSERT INTO `address` VALUES (7, 350103, '福建省', '福州市', '台江区', '福建省福州市台江区友谊路155号');
INSERT INTO `address` VALUES (8, 420102, '湖北省', '武汉市', '江岸区', '湖北省武汉市江岸区社会大道333号');
INSERT INTO `address` VALUES (9, 310101, '上海市', '市辖区', '黄浦区', '上海市外滩路101号');
INSERT INTO `address` VALUES (10, NULL, NULL, NULL, NULL, '');

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `r_date` date NOT NULL,
  `r_id` bigint(20) NOT NULL,
  `remain` int(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`r_date`, `r_id`) USING BTREE,
  INDEX `FK_room_detail`(`r_id`) USING BTREE,
  CONSTRAINT `FK_room_detail` FOREIGN KEY (`r_id`) REFERENCES `room` (`r_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES ('2022-03-01', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-01', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-02', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-03', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-04', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-05', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-06', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-07', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-08', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 1, 8);
INSERT INTO `detail` VALUES ('2022-03-09', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 3, 13);
INSERT INTO `detail` VALUES ('2022-03-09', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-09', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 1, 9);
INSERT INTO `detail` VALUES ('2022-03-10', 2, 17);
INSERT INTO `detail` VALUES ('2022-03-10', 3, 17);
INSERT INTO `detail` VALUES ('2022-03-10', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-10', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-10', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-11', 1, 8);
INSERT INTO `detail` VALUES ('2022-03-11', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 3, 11);
INSERT INTO `detail` VALUES ('2022-03-11', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-11', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-11', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-11', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-12', 1, 6);
INSERT INTO `detail` VALUES ('2022-03-12', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 3, 5);
INSERT INTO `detail` VALUES ('2022-03-12', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-12', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-12', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-12', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-12', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-12', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-13', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 3, 3);
INSERT INTO `detail` VALUES ('2022-03-13', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-13', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-13', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-13', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-13', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-13', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-14', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-14', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-14', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-14', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-14', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-14', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-15', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-15', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-15', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-15', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-15', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-15', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-16', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-16', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-16', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-16', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-16', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-16', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-17', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-17', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-17', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-17', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-17', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-17', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-18', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-18', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-18', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-18', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-18', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-18', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-19', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-19', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-19', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-19', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-19', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-19', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-20', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-20', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-20', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-20', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-20', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-20', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-21', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-21', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-21', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-21', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-21', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-21', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-22', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-22', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-22', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-22', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-22', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-22', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-23', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-23', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-23', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-23', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-23', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-23', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-24', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-24', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-24', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-24', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-24', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-24', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-25', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-25', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-25', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-25', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-25', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-25', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-26', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-26', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-26', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-26', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-26', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-26', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-27', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-27', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-27', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-27', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-27', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-27', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-28', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-28', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-28', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-28', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-28', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-28', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-29', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-29', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-29', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-29', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-29', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-29', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-30', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-30', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-30', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-30', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-30', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-30', 29, 3);
INSERT INTO `detail` VALUES ('2022-03-31', 1, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 2, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 3, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 9, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 10, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 11, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 12, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 13, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 14, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 15, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 16, 10);
INSERT INTO `detail` VALUES ('2022-03-31', 25, 2);
INSERT INTO `detail` VALUES ('2022-03-31', 26, 6);
INSERT INTO `detail` VALUES ('2022-03-31', 27, 1);
INSERT INTO `detail` VALUES ('2022-03-31', 28, 5);
INSERT INTO `detail` VALUES ('2022-03-31', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-01', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-01', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-01', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-01', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-01', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-02', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-02', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-02', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-02', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-02', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-03', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-03', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-03', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-03', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-03', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-04', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-04', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-04', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-04', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-04', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-05', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-05', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-05', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-05', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-05', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-06', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-06', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-06', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-06', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-06', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-07', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-07', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-07', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-07', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-07', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-08', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-08', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-08', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-08', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-08', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-09', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-09', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-09', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-09', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-09', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-10', 25, 2);
INSERT INTO `detail` VALUES ('2022-04-10', 26, 6);
INSERT INTO `detail` VALUES ('2022-04-10', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-10', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-10', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-11', 27, 1);
INSERT INTO `detail` VALUES ('2022-04-11', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-11', 29, 3);
INSERT INTO `detail` VALUES ('2022-04-12', 28, 5);
INSERT INTO `detail` VALUES ('2022-04-12', 29, 3);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `h_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `a_id` bigint(20) NULL DEFAULT NULL,
  `longitude` double NULL DEFAULT NULL,
  `latitude` double NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(10) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`h_id`) USING BTREE,
  INDEX `FK_address_hotel`(`a_id`) USING BTREE,
  CONSTRAINT `FK_address_hotel` FOREIGN KEY (`a_id`) REFERENCES `address` (`a_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, 1, 104.001, 30.553, 3, '如家连锁酒店', 0);
INSERT INTO `hotel` VALUES (2, 2, 104.082, 30.627, 3, '七天快捷酒店', 0);
INSERT INTO `hotel` VALUES (5, 5, 116.404, 39.915, 4, '速八快捷酒店', 0);
INSERT INTO `hotel` VALUES (6, 6, 126.63, 45.752, 1, '城市快捷酒店', 0);
INSERT INTO `hotel` VALUES (7, 7, 119.198, 26.059, 5, '希尔顿酒店', 0);
INSERT INTO `hotel` VALUES (8, 8, 116.254342, 39.944822, 2, '维也纳酒店', 1);
INSERT INTO `hotel` VALUES (15, 8, 114.299, 30.584, 4, '武汉睡馆', 1);
INSERT INTO `hotel` VALUES (16, 9, 121.498908, 31.241877, 4, '格林豪泰酒店', 1);
INSERT INTO `hotel` VALUES (17, 10, 116.404, 39.915, 0, '', 1);
INSERT INTO `hotel` VALUES (18, 9, 121.481201, 31.236287, 5, '上海酒店（市政府店）', 1);

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NULL DEFAULT NULL,
  `h_id` bigint(20) NULL DEFAULT NULL,
  `photo_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE,
  INDEX `FK_hotel_photo`(`h_id`) USING BTREE,
  INDEX `FK_room_photo`(`r_id`) USING BTREE,
  CONSTRAINT `FK_hotel_photo` FOREIGN KEY (`h_id`) REFERENCES `hotel` (`h_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_room_photo` FOREIGN KEY (`r_id`) REFERENCES `room` (`r_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES (1, NULL, 1, './image/202203/-5dd2f9516ca3478990d3785381f0c469.jpeg');
INSERT INTO `photo` VALUES (2, NULL, 2, './image/202203/-b62bb3e586744a74afb6c24a84f02d41.jpeg');
INSERT INTO `photo` VALUES (3, NULL, 5, './image/202203/-36263fe7412d4c3eb57e4e0251b14109.jpeg');
INSERT INTO `photo` VALUES (4, NULL, 6, './image/202203/-a71468dc968f4e11af9fdec9e8164118.jpeg');
INSERT INTO `photo` VALUES (5, NULL, 7, './image/202203/-5853fa1bc49f40ef886ad5d7239a5669.jpeg');
INSERT INTO `photo` VALUES (6, NULL, 8, './image/202203/-8117a97a02964df98f1c38be2e8cb75e.jpeg');
INSERT INTO `photo` VALUES (7, 1, 1, './image/202203/-7777d346b3ae4d5e89df3fc5eac5dc64.jpeg');
INSERT INTO `photo` VALUES (8, 2, 1, './image/202203/-799c0af5d10744bbb8f7058366599947.jpeg');
INSERT INTO `photo` VALUES (9, 3, 2, './image/202203/-f26cc27102394616b06d9f0bc0ff27d3.jpeg');
INSERT INTO `photo` VALUES (10, 9, 5, './image/202203/-7644fc154daa45eca2373630e282f827.jpeg');
INSERT INTO `photo` VALUES (11, 10, 5, './image/202203/-95f4fb367b484d35b3b0e4ff071dfdc2.jpeg');
INSERT INTO `photo` VALUES (12, 11, 6, './image/202203/-2ad9c71b119a4eeba5a4d436021fac91.jpeg');
INSERT INTO `photo` VALUES (13, 12, 6, './image/202203/-d67334b1c31647caa70e6fda882d64f0.jpeg');
INSERT INTO `photo` VALUES (14, 13, 7, './image/202203/-141cd3909d66418ab040f73f475c28d7.jpeg');
INSERT INTO `photo` VALUES (15, 14, 7, './image/202203/-2159f5a98b774acfb8e77be30e008616.jpeg');
INSERT INTO `photo` VALUES (16, 15, 8, './image/202203/-5de96d61a6a04928a3fac22dfaf179ea.jpeg');
INSERT INTO `photo` VALUES (17, 16, 8, './image/202203/-4a115b6e1931455c80ba55a53760c2c3.jpeg');
INSERT INTO `photo` VALUES (18, NULL, 15, '');
INSERT INTO `photo` VALUES (19, 25, 15, '');
INSERT INTO `photo` VALUES (20, NULL, 16, '');
INSERT INTO `photo` VALUES (21, 26, 16, '');
INSERT INTO `photo` VALUES (22, NULL, 17, '');
INSERT INTO `photo` VALUES (23, 27, 17, '');
INSERT INTO `photo` VALUES (24, NULL, 18, './image/202203/-b32dcde4de4043289b9cb49b598f051f.jpeg');
INSERT INTO `photo` VALUES (25, 28, 18, './image/202203/-2e3fc934e7074c5bb997672568ca28d1.jpeg');
INSERT INTO `photo` VALUES (26, 29, 18, './image/202203/-dc37170feb694c5bbb572fadfbc8f84b.jpeg');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `h_id` bigint(20) NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `roomtype` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `max_people` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE,
  INDEX `FK_hotel_room`(`h_id`) USING BTREE,
  CONSTRAINT `FK_hotel_room` FOREIGN KEY (`h_id`) REFERENCES `hotel` (`h_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 1, 299, '标准间', 50, 2);
INSERT INTO `room` VALUES (2, 1, 120, '大床房', 50, 2);
INSERT INTO `room` VALUES (3, 2, 160, '标准间', 50, 2);
INSERT INTO `room` VALUES (9, 5, 180, '标准间', 50, 2);
INSERT INTO `room` VALUES (10, 5, 150, '总统套房', 50, 4);
INSERT INTO `room` VALUES (11, 6, 189, '双人间', 50, 2);
INSERT INTO `room` VALUES (12, 6, 279, '亲子房', 50, 3);
INSERT INTO `room` VALUES (13, 7, 166, '大床房', 50, 2);
INSERT INTO `room` VALUES (14, 7, 299, '亲子房', 50, 3);
INSERT INTO `room` VALUES (15, 8, 180, '标准间', 50, 2);
INSERT INTO `room` VALUES (16, 8, 167, '双人间', 50, 2);
INSERT INTO `room` VALUES (25, 15, 222, '标准间', 50, NULL);
INSERT INTO `room` VALUES (26, 16, 300, '总统套房', 6, NULL);
INSERT INTO `room` VALUES (27, 17, 1, '总统套房', 1, NULL);
INSERT INTO `room` VALUES (28, 18, 150, '大床房', 5, NULL);
INSERT INTO `room` VALUES (29, 18, 250, '亲子房', 3, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertype` int(11) NULL DEFAULT NULL,
  `is_deleted` tinyint(10) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '1', './image/202203/-22275f12265949a08ef45b765b0e0e63.jpeg', '3932047@qq.com', 1, 0);
INSERT INTO `user` VALUES (2, '2', '2', './image/202203/-98fe3cc2862b471190894cec29e9fce1.jpeg', '234878295@qq.com', 1, 0);
INSERT INTO `user` VALUES (3, '3', '3', './image/202203/-54c04b1f063443639ceefdf2134578fb.jpeg', '329847279@qq.com', 1, 0);
INSERT INTO `user` VALUES (4, '4', '4', './image/202203/-c98938cb17a14f46ab18657e50c92491.jpeg', '905489823@qq.com', 1, 0);
INSERT INTO `user` VALUES (5, '5', '5', './image/202203/-fb8c126f3bae4c03b2f0d6fad9aec3d5.jpeg', '649082380@qq.com', 1, 0);
INSERT INTO `user` VALUES (6, '6', '6', './image/202203/-6253bd3c14244253ad82ba71cb6d9edf.jpeg', '9871309123@qq.com', 1, 0);
INSERT INTO `user` VALUES (7, '7', '7', './image/202203/-25570cd4b0404af2bfb1c76455e88051.jpeg', '2497127390@qq.com', 1, 0);
INSERT INTO `user` VALUES (8, '8', '8', './image/202203/-c3c3f8a3be5e4d6fb565b81456ec28e8.jpeg', '0293712308@qq.com', 1, 0);
INSERT INTO `user` VALUES (9, '9', '9', './image/202203/-57ef00dd1111457e8d5c0de80935df88.jpeg', '5289749782@qq.com', 1, 0);
INSERT INTO `user` VALUES (10, '10', '10', './image/202203/-5b709a6fee874d699d10146bac8f41ae.jpeg', '3240983579@qq.com', 1, 0);
INSERT INTO `user` VALUES (16, 'admin', 'admin', '', NULL, 0, 0);
INSERT INTO `user` VALUES (17, 'xiaoming', 'xiaoming', './image/202203/-88ec4f2214b24bd7aaab49cf810b2a93.jpeg', '18292166117@163.com', 1, 0);
INSERT INTO `user` VALUES (18, 'Sylvia', '123', './image/202203/-2fc97e2f616845f0b0d95a82c95c5bd7.jpeg', '986347623@qq.com', 1, 0);
INSERT INTO `user` VALUES (19, 'hcz', '1', './image/202203/-3c27fae6e0d1466e87eb9ca1d476d4d5.jpeg', '949412843@qq.com', 1, 1);
INSERT INTO `user` VALUES (25, '张iahfha', '456', './image/202203/-352ab266285b4dd28e22dafe5c1b2e9f.jpeg', '123gag@qq.com', 1, 1);
INSERT INTO `user` VALUES (26, 'jjj', 'jjj', './image/202203/-85ac41be56bd4412bda758f1874d7a11.jpeg', '949412843@qq.com', 1, 1);
INSERT INTO `user` VALUES (27, '121431', '123', './image/202203/-22275f12265949a08ef45b765b0e0e63.jpeg', '949412843333@qq.com', 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder` VALUES (1, 1, 6, 1, '2022-03-01', '2022-03-02', 600, 4, 1, '服务不错', '张三', '110121199003078000', '18843628604', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (2, 2, 7, 1, '2022-03-01', '2022-03-02', 480, 4, 1, '环境挺好的', '柳依依', '110131199003078000', '19964820482', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (3, 3, 2, 2, '2022-03-01', '2022-03-02', 640, 4, 1, '干净卫生', '说句话', '110141199003078000', '15198742360', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (4, 9, 4, 5, '2022-03-01', '2022-03-02', 180, 1, 1, '老顾客了，蛮喜欢的', '金克拉', '110151199003078000', '15198742361', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (5, 10, 1, 5, '2022-03-01', '2022-03-02', 222, 2, 1, '还不戳', '很惊讶', '110161199003078000', '15198742362', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (6, 11, 2, 6, '2022-03-01', '2022-03-02', 756, 4, 1, '服务不错', '刚好', '110171199003078000', '15198742363', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (7, 12, 4, 6, '2022-03-01', '2022-03-02', 340, 2, 1, '环境挺好的', '迷你', '110181199003078000', '15198742364', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (8, 13, 8, 7, '2022-03-01', '2022-03-02', 1064, 4, 1, '干净卫生', '暗示', '110191199003078000', '15198742365', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (9, 14, 4, 7, '2022-03-01', '2022-03-02', 1196, 4, 1, '老顾客了，蛮喜欢的', '这是', '110201199003078000', '15198742366', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (10, 15, 1, 8, '2022-03-01', '2022-03-02', 900, 5, 1, '出差一直来这家', '前往', '110211199003078000', '15198742367', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (11, 16, 7, 8, '2022-03-01', '2022-03-02', 334, 2, 1, '服务不错', '回复', '110221199003078000', '15198742368', '2022-03-01 00:00:00');
INSERT INTO `userorder` VALUES (12, 1, 9, 1, '2022-03-02', '2022-03-03', 600, 4, 1, '环境挺好的', '委托', '110231199003078000', '15198742369', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (13, 2, 7, 1, '2022-03-02', '2022-03-03', 600, 5, 1, '干净卫生', '张三', '110241199003078000', '15198742370', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (14, 3, 3, 2, '2022-03-02', '2022-03-03', 800, 5, 1, '老顾客了，蛮喜欢的', '柳依依', '110251199003078000', '15198742371', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (15, 9, 2, 5, '2022-03-02', '2022-03-03', 900, 5, 1, '出差一直来这家', '说句话', '110261199003078000', '15198742372', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (16, 10, 1, 5, '2022-03-02', '2022-03-03', 444, 4, 1, '服务不错', '金克拉', '110271199003078000', '15198742373', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (17, 11, 3, 6, '2022-03-02', '2022-03-03', 189, 1, 1, '环境挺好的', '很惊讶', '110281199003078000', '15198742374', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (18, 12, 6, 6, '2022-03-02', '2022-03-03', 510, 3, 1, '干净卫生', '刚好', '110291199003078000', '15198742375', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (19, 13, 2, 7, '2022-03-02', '2022-03-03', 1330, 5, 1, '老顾客了，蛮喜欢的', '迷你', '110301199003078000', '15198742376', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (20, 14, 4, 7, '2022-03-02', '2022-03-03', 299, 1, 1, '出差一直来这家', '暗示', '110311199003078000', '15198742377', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (21, 15, 5, 8, '2022-03-02', '2022-03-03', 720, 4, 1, '服务不错', '这是', '110321199003078000', '15198742378', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (22, 16, 3, 8, '2022-03-02', '2022-03-03', 835, 5, 1, '环境挺好的', '前往', '110331199003078000', '15198742379', '2022-03-02 00:00:00');
INSERT INTO `userorder` VALUES (23, 1, 7, 1, '2022-03-03', '2022-03-04', 600, 4, 1, '干净卫生', '回复', '110341199003078000', '15198742380', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (24, 2, 2, 1, '2022-03-03', '2022-03-04', 240, 2, 1, '老顾客了，蛮喜欢的', '委托', '110351199003078000', '15198742381', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (25, 3, 3, 2, '2022-03-03', '2022-03-04', 640, 4, 1, '出差一直来这家', '张三', '110361199003078000', '15198742382', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (26, 9, 8, 5, '2022-03-03', '2022-03-04', 180, 1, 1, '服务不错', '柳依依', '110371199003078000', '15198742383', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (27, 10, 1, 5, '2022-03-03', '2022-03-04', 111, 1, 1, '环境挺好的', '说句话', '110381199003078000', '15198742384', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (28, 11, 9, 6, '2022-03-03', '2022-03-04', 756, 4, 1, '干净卫生', '金克拉', '110391199003078000', '15198742385', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (29, 12, 10, 6, '2022-03-03', '2022-03-04', 510, 3, 1, '老顾客了，蛮喜欢的', '很惊讶', '110401199003078000', '15198742386', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (30, 13, 10, 7, '2022-03-03', '2022-03-04', 798, 3, 1, '出差一直来这家', '刚好', '110411199003078000', '15198742387', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (31, 14, 7, 7, '2022-03-03', '2022-03-04', 897, 3, 1, '服务不错', '迷你', '110421199003078000', '15198742388', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (32, 15, 3, 8, '2022-03-03', '2022-03-04', 180, 1, 1, '环境挺好的', '暗示', '110431199003078000', '15198742389', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (33, 16, 7, 8, '2022-03-03', '2022-03-04', 167, 1, 1, '干净卫生', '这是', '110441199003078000', '15198742390', '2022-03-03 00:00:00');
INSERT INTO `userorder` VALUES (34, 1, 4, 1, '2022-03-04', '2022-03-05', 450, 3, 1, '老顾客了，蛮喜欢的', '前往', '110451199003078000', '15198742391', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (35, 2, 1, 1, '2022-03-04', '2022-03-05', 240, 2, 1, '出差一直来这家', '回复', '110461199003078000', '15198742392', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (36, 3, 2, 2, '2022-03-04', '2022-03-05', 800, 5, 1, '服务不错', '委托', '110471199003078000', '15198742393', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (37, 9, 9, 5, '2022-03-04', '2022-03-05', 180, 1, 1, '环境挺好的', '张三', '110481199003078000', '15198742394', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (38, 10, 6, 5, '2022-03-04', '2022-03-05', 222, 2, 1, '干净卫生', '柳依依', '110491199003078000', '15198742395', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (39, 11, 3, 6, '2022-03-04', '2022-03-05', 756, 4, 1, '老顾客了，蛮喜欢的', '说句话', '110501199003078000', '15198742396', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (40, 12, 2, 6, '2022-03-04', '2022-03-05', 680, 4, 1, '出差一直来这家', '金克拉', '110511199003078000', '15198742397', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (41, 13, 6, 7, '2022-03-04', '2022-03-05', 1064, 4, 1, '服务不错', '很惊讶', '110521199003078000', '15198742398', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (42, 14, 3, 7, '2022-03-04', '2022-03-05', 598, 2, 1, '环境挺好的', '刚好', '110531199003078000', '15198742399', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (43, 15, 2, 8, '2022-03-04', '2022-03-05', 540, 3, 1, '干净卫生', '迷你', '110541199003078000', '15198742400', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (44, 16, 4, 8, '2022-03-04', '2022-03-05', 501, 3, 1, '老顾客了，蛮喜欢的', '暗示', '110551199003078000', '15198742401', '2022-03-04 00:00:00');
INSERT INTO `userorder` VALUES (45, 1, 10, 1, '2022-03-05', '2022-03-06', 150, 1, 1, '出差一直来这家', '这是', '110561199003078000', '15198742402', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (46, 2, 9, 1, '2022-03-05', '2022-03-06', 480, 4, 1, '服务不错', '前往', '110571199003078000', '15198742403', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (47, 3, 3, 2, '2022-03-05', '2022-03-06', 320, 2, 1, '环境挺好的', '回复', '110581199003078000', '15198742404', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (48, 9, 8, 5, '2022-03-05', '2022-03-06', 360, 2, 1, '干净卫生', '委托', '110591199003078000', '15198742405', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (49, 10, 1, 5, '2022-03-05', '2022-03-06', 222, 2, 1, '老顾客了，蛮喜欢的', '张三', '110601199003078000', '15198742406', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (50, 11, 2, 6, '2022-03-05', '2022-03-06', 756, 4, 1, '出差一直来这家', '柳依依', '110611199003078000', '15198742407', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (51, 12, 2, 6, '2022-03-05', '2022-03-06', 680, 4, 1, '服务不错', '说句话', '110621199003078000', '15198742408', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (52, 13, 10, 7, '2022-03-05', '2022-03-06', 1064, 4, 1, '环境挺好的', '金克拉', '110631199003078000', '15198742409', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (53, 14, 3, 7, '2022-03-05', '2022-03-06', 299, 1, 1, '干净卫生', '很惊讶', '110641199003078000', '15198742410', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (54, 15, 6, 8, '2022-03-05', '2022-03-06', 540, 3, 1, '老顾客了，蛮喜欢的', '刚好', '110651199003078000', '15198742411', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (55, 16, 9, 8, '2022-03-05', '2022-03-06', 501, 3, 1, '出差一直来这家', '迷你', '110661199003078000', '15198742412', '2022-03-05 00:00:00');
INSERT INTO `userorder` VALUES (56, 1, 4, 1, '2022-03-06', '2022-03-07', 300, 2, 1, '服务不错', '暗示', '110671199003078000', '15198742413', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (57, 2, 1, 1, '2022-03-06', '2022-03-07', 360, 3, 1, '环境挺好的', '这是', '110681199003078000', '15198742414', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (58, 3, 6, 2, '2022-03-06', '2022-03-07', 640, 4, 1, '干净卫生', '前往', '110691199003078000', '15198742415', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (59, 9, 2, 5, '2022-03-06', '2022-03-07', 540, 3, 1, '老顾客了，蛮喜欢的', '回复', '110701199003078000', '15198742416', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (60, 10, 4, 5, '2022-03-06', '2022-03-07', 111, 1, 1, '出差一直来这家', '委托', '110711199003078000', '15198742417', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (61, 11, 6, 6, '2022-03-06', '2022-03-07', 945, 5, 1, '服务不错', '张三', '110721199003078000', '15198742418', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (62, 12, 8, 6, '2022-03-06', '2022-03-07', 510, 3, 1, '环境挺好的', '柳依依', '110731199003078000', '15198742419', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (63, 13, 6, 7, '2022-03-06', '2022-03-07', 266, 1, 1, '干净卫生', '说句话', '110741199003078000', '15198742420', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (64, 14, 5, 7, '2022-03-06', '2022-03-07', 299, 1, 1, '老顾客了，蛮喜欢的', '金克拉', '110751199003078000', '15198742421', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (65, 15, 10, 8, '2022-03-06', '2022-03-07', 900, 5, 1, '出差一直来这家', '很惊讶', '110761199003078000', '15198742422', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (66, 16, 8, 8, '2022-03-06', '2022-03-07', 668, 4, 1, '服务不错', '刚好', '110771199003078000', '15198742423', '2022-03-06 00:00:00');
INSERT INTO `userorder` VALUES (67, 1, 5, 1, '2022-03-07', '2022-03-08', 750, 5, 1, '环境挺好的', '迷你', '110781199003078000', '15198742424', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (68, 2, 2, 1, '2022-03-07', '2022-03-08', 360, 3, 1, '干净卫生', '暗示', '110791199003078000', '15198742425', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (69, 3, 7, 2, '2022-03-07', '2022-03-08', 320, 2, 1, '老顾客了，蛮喜欢的', '这是', '110801199003078000', '15198742426', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (70, 9, 7, 5, '2022-03-07', '2022-03-08', 180, 1, 1, '出差一直来这家', '前往', '110811199003078000', '15198742427', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (71, 10, 6, 5, '2022-03-07', '2022-03-08', 555, 5, 1, '服务不错', '回复', '110821199003078000', '15198742428', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (72, 11, 3, 6, '2022-03-07', '2022-03-08', 756, 4, 1, '环境挺好的', '委托', '110831199003078000', '15198742429', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (73, 12, 5, 6, '2022-03-07', '2022-03-08', 510, 3, 1, '干净卫生', '张三', '110841199003078000', '15198742430', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (74, 13, 10, 7, '2022-03-07', '2022-03-08', 1330, 5, 1, '老顾客了，蛮喜欢的', '柳依依', '110851199003078000', '15198742431', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (75, 14, 10, 7, '2022-03-07', '2022-03-08', 1196, 4, 1, '出差一直来这家', '说句话', '110861199003078000', '15198742432', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (76, 15, 9, 8, '2022-03-07', '2022-03-08', 360, 2, 1, '服务不错', '金克拉', '110871199003078000', '15198742433', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (77, 16, 4, 8, '2022-03-07', '2022-03-08', 334, 2, 1, '环境挺好的', '很惊讶', '110881199003078000', '15198742434', '2022-03-07 00:00:00');
INSERT INTO `userorder` VALUES (78, 1, 1, 1, '2022-03-08', '2022-03-09', 450, 3, 1, '干净卫生', '刚好', '110891199003078000', '15198742435', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (79, 2, 2, 1, '2022-03-08', '2022-03-09', 600, 5, 1, '老顾客了，蛮喜欢的', '迷你', '110901199003078000', '15198742436', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (80, 3, 8, 2, '2022-03-08', '2022-03-09', 480, 3, 1, '出差一直来这家', '暗示', '110911199003078000', '15198742437', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (81, 9, 6, 5, '2022-03-08', '2022-03-09', 360, 2, 1, '服务不错', '这是', '110921199003078000', '15198742438', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (82, 10, 4, 5, '2022-03-08', '2022-03-09', 444, 4, 1, '环境挺好的', '前往', '110931199003078000', '15198742439', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (83, 11, 1, 6, '2022-03-08', '2022-03-09', 945, 5, 1, '干净卫生', '回复', '110941199003078000', '15198742440', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (84, 12, 10, 6, '2022-03-08', '2022-03-09', 680, 4, 1, '老顾客了，蛮喜欢的', '委托', '110951199003078000', '15198742441', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (85, 13, 4, 7, '2022-03-08', '2022-03-09', 532, 2, 1, '出差一直来这家', '张三', '110961199003078000', '15198742442', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (86, 14, 10, 7, '2022-03-08', '2022-03-09', 598, 2, 1, '服务不错', '柳依依', '110971199003078000', '15198742443', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (87, 15, 10, 8, '2022-03-08', '2022-03-09', 720, 4, 1, '环境挺好的', '说句话', '110981199003078000', '15198742444', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (88, 16, 7, 8, '2022-03-08', '2022-03-09', 334, 2, 1, '干净卫生', '金克拉', '110991199003078000', '15198742445', '2022-03-08 00:00:00');
INSERT INTO `userorder` VALUES (89, 1, 5, 1, '2022-03-09', '2022-03-10', 450, 3, 1, '老顾客了，蛮喜欢的', '很惊讶', '111001199003078000', '15198742446', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (90, 2, 2, 1, '2022-03-09', '2022-03-10', 120, 1, 1, '出差一直来这家', '刚好', '111011199003078000', '15198742447', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (91, 3, 3, 2, '2022-03-09', '2022-03-10', 480, 3, 1, '服务不错', '迷你', '111021199003078000', '15198742448', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (92, 9, 2, 5, '2022-03-09', '2022-03-10', 540, 3, 1, '环境挺好的', '暗示', '111031199003078000', '15198742449', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (93, 10, 2, 5, '2022-03-09', '2022-03-10', 222, 2, 1, '干净卫生', '这是', '111041199003078000', '15198742450', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (94, 11, 3, 6, '2022-03-09', '2022-03-10', 189, 1, 1, '老顾客了，蛮喜欢的', '前往', '111051199003078000', '15198742451', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (95, 12, 7, 6, '2022-03-09', '2022-03-10', 510, 3, 1, '出差一直来这家', '回复', '111061199003078000', '15198742452', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (96, 13, 9, 7, '2022-03-09', '2022-03-10', 798, 3, 1, '服务不错', '委托', '111071199003078000', '15198742453', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (97, 14, 1, 7, '2022-03-09', '2022-03-10', 598, 2, 1, '环境挺好的', '张三', '111081199003078000', '15198742454', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (98, 15, 9, 8, '2022-03-09', '2022-03-10', 360, 2, 1, '干净卫生', '柳依依', '111091199003078000', '15198742455', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (99, 16, 10, 8, '2022-03-09', '2022-03-10', 835, 5, 1, '老顾客了，蛮喜欢的', '说句话', '111101199003078000', '15198742456', '2022-03-09 00:00:00');
INSERT INTO `userorder` VALUES (100, 1, 9, 1, '2022-03-10', '2022-03-11', 600, 4, 1, '出差一直来这家', '金克拉', '111111199003078000', '15198742457', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (101, 2, 7, 1, '2022-03-10', '2022-03-11', 600, 5, 1, '服务不错', '很惊讶', '111121199003078000', '15198742458', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (102, 3, 2, 2, '2022-03-10', '2022-03-11', 640, 4, 1, '环境挺好的', '刚好', '111131199003078000', '15198742459', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (103, 9, 9, 5, '2022-03-10', '2022-03-11', 360, 2, 1, '干净卫生', '迷你', '111141199003078000', '15198742460', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (104, 10, 9, 5, '2022-03-10', '2022-03-11', 444, 4, 1, '老顾客了，蛮喜欢的', '暗示', '111151199003078000', '15198742461', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (105, 11, 6, 6, '2022-03-10', '2022-03-11', 189, 1, 1, '出差一直来这家', '这是', '111161199003078000', '15198742462', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (106, 12, 5, 6, '2022-03-10', '2022-03-11', 510, 3, 1, '服务不错', '前往', '111171199003078000', '15198742463', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (107, 13, 2, 7, '2022-03-10', '2022-03-11', 798, 3, 1, '环境挺好的', '回复', '111181199003078000', '15198742464', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (108, 14, 3, 7, '2022-03-10', '2022-03-11', 598, 2, 1, '干净卫生', '委托', '111191199003078000', '15198742465', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (109, 15, 5, 8, '2022-03-10', '2022-03-11', 540, 3, 1, '老顾客了，蛮喜欢的', '张三', '111201199003078000', '15198742466', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (110, 16, 10, 8, '2022-03-10', '2022-03-11', 334, 2, 1, '出差一直来这家', '柳依依', '111211199003078000', '15198742467', '2022-03-10 00:00:00');
INSERT INTO `userorder` VALUES (111, 1, 9, 1, '2022-03-11', '2022-03-12', 450, 3, 1, '服务不错', '说句话', '111221199003078000', '15198742468', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (112, 2, 3, 1, '2022-03-11', '2022-03-12', 480, 4, 1, '环境挺好的', '金克拉', '111231199003078000', '15198742469', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (113, 3, 10, 2, '2022-03-11', '2022-03-12', 480, 3, 1, '干净卫生', '很惊讶', '111241199003078000', '15198742470', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (114, 9, 4, 5, '2022-03-11', '2022-03-12', 900, 5, 1, '老顾客了，蛮喜欢的', '刚好', '111251199003078000', '15198742471', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (115, 10, 9, 5, '2022-03-11', '2022-03-12', 444, 4, 1, '出差一直来这家', '迷你', '111261199003078000', '15198742472', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (116, 11, 6, 6, '2022-03-11', '2022-03-12', 567, 3, 1, '服务不错', '暗示', '111271199003078000', '15198742473', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (117, 12, 7, 6, '2022-03-11', '2022-03-12', 850, 5, 1, '环境挺好的', '这是', '111281199003078000', '15198742474', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (118, 13, 6, 7, '2022-03-11', '2022-03-12', 1330, 5, 1, '干净卫生', '前往', '111291199003078000', '15198742475', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (119, 14, 4, 7, '2022-03-11', '2022-03-12', 1196, 4, 1, '老顾客了，蛮喜欢的', '回复', '111301199003078000', '15198742476', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (120, 15, 5, 8, '2022-03-11', '2022-03-12', 900, 5, 1, '出差一直来这家', '委托', '111311199003078000', '15198742477', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (121, 16, 6, 8, '2022-03-11', '2022-03-12', 668, 4, 1, '服务不错', '张三', '111321199003078000', '15198742478', '2022-03-11 00:00:00');
INSERT INTO `userorder` VALUES (122, 1, 3, 1, '2022-03-12', '2022-03-13', 600, 4, 1, '环境挺好的', '柳依依', '111331199003078000', '15198742479', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (123, 2, 6, 1, '2022-03-12', '2022-03-13', 240, 2, 1, '干净卫生', '说句话', '111341199003078000', '15198742480', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (124, 3, 10, 2, '2022-03-12', '2022-03-13', 480, 3, 1, '老顾客了，蛮喜欢的', '金克拉', '111351199003078000', '15198742481', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (125, 9, 9, 5, '2022-03-12', '2022-03-13', 540, 3, 1, '出差一直来这家', '很惊讶', '111361199003078000', '15198742482', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (126, 10, 2, 5, '2022-03-12', '2022-03-13', 444, 4, 1, '服务不错', '刚好', '111371199003078000', '15198742483', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (127, 11, 4, 6, '2022-03-12', '2022-03-13', 378, 2, 1, '环境挺好的', '迷你', '111381199003078000', '15198742484', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (128, 12, 1, 6, '2022-03-12', '2022-03-13', 510, 3, 1, '干净卫生', '暗示', '111391199003078000', '15198742485', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (129, 13, 6, 7, '2022-03-12', '2022-03-13', 798, 3, 1, '老顾客了，蛮喜欢的', '这是', '111401199003078000', '15198742486', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (130, 14, 9, 7, '2022-03-12', '2022-03-13', 897, 3, 1, '出差一直来这家', '前往', '111411199003078000', '15198742487', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (131, 15, 9, 8, '2022-03-12', '2022-03-13', 720, 4, 1, '服务不错', '回复', '111421199003078000', '15198742488', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (132, 16, 7, 8, '2022-03-12', '2022-03-13', 167, 1, 1, '环境挺好的', '委托', '111431199003078000', '15198742489', '2022-03-12 00:00:00');
INSERT INTO `userorder` VALUES (133, 1, 9, 1, '2022-03-13', '2022-03-14', 750, 5, 1, '干净卫生', '张三', '111441199003078000', '15198742490', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (134, 2, 2, 1, '2022-03-13', '2022-03-14', 120, 1, 1, '老顾客了，蛮喜欢的', '柳依依', '111451199003078000', '15198742491', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (135, 3, 1, 2, '2022-03-13', '2022-03-14', 800, 5, 1, '出差一直来这家', '说句话', '111461199003078000', '15198742492', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (136, 9, 2, 5, '2022-03-13', '2022-03-14', 900, 5, 1, '服务不错', '金克拉', '111471199003078000', '15198742493', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (137, 10, 1, 5, '2022-03-13', '2022-03-14', 555, 5, 1, '环境挺好的', '很惊讶', '111481199003078000', '15198742494', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (138, 11, 5, 6, '2022-03-13', '2022-03-14', 378, 2, 1, '干净卫生', '刚好', '111491199003078000', '15198742495', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (139, 12, 3, 6, '2022-03-13', '2022-03-14', 170, 1, 1, '老顾客了，蛮喜欢的', '迷你', '111501199003078000', '15198742496', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (140, 13, 8, 7, '2022-03-13', '2022-03-14', 532, 2, 1, '出差一直来这家', '暗示', '111511199003078000', '15198742497', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (141, 14, 2, 7, '2022-03-13', '2022-03-14', 1196, 4, 1, '服务不错', '这是', '111521199003078000', '15198742498', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (142, 15, 9, 8, '2022-03-13', '2022-03-14', 900, 5, 1, '环境挺好的', '前往', '111531199003078000', '15198742499', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (143, 16, 7, 8, '2022-03-13', '2022-03-14', 167, 1, 1, '干净卫生', '回复', '111541199003078000', '15198742500', '2022-03-13 00:00:00');
INSERT INTO `userorder` VALUES (144, 1, 5, 1, '2022-03-14', '2022-03-15', 750, 5, 1, '老顾客了，蛮喜欢的', '委托', '111551199003078000', '15198742501', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (145, 2, 5, 1, '2022-03-14', '2022-03-15', 360, 3, 1, '出差一直来这家', '张三', '111561199003078000', '15198742502', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (146, 3, 3, 2, '2022-03-14', '2022-03-15', 160, 1, 1, '服务不错', '柳依依', '111571199003078000', '15198742503', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (147, 9, 6, 5, '2022-03-14', '2022-03-15', 900, 5, 1, '环境挺好的', '说句话', '111581199003078000', '15198742504', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (148, 10, 5, 5, '2022-03-14', '2022-03-15', 555, 5, 1, '干净卫生', '金克拉', '111591199003078000', '15198742505', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (149, 11, 1, 6, '2022-03-14', '2022-03-15', 756, 4, 1, '老顾客了，蛮喜欢的', '很惊讶', '111601199003078000', '15198742506', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (150, 12, 1, 6, '2022-03-14', '2022-03-15', 170, 1, 1, '出差一直来这家', '刚好', '111611199003078000', '15198742507', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (151, 13, 4, 7, '2022-03-14', '2022-03-15', 1064, 4, 1, '服务不错', '迷你', '111621199003078000', '15198742508', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (152, 14, 9, 7, '2022-03-14', '2022-03-15', 1495, 5, 1, '环境挺好的', '暗示', '111631199003078000', '15198742509', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (153, 15, 1, 8, '2022-03-14', '2022-03-15', 900, 5, 1, '干净卫生', '这是', '111641199003078000', '15198742510', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (154, 16, 6, 8, '2022-03-14', '2022-03-15', 501, 3, 1, '老顾客了，蛮喜欢的', '前往', '111651199003078000', '15198742511', '2022-03-14 00:00:00');
INSERT INTO `userorder` VALUES (155, 1, 1, 1, '2022-03-09', '2022-03-10', 150, 1, 1, '价格有点贵', '张三', '111661199003078000', '13044659875', '2022-03-09 18:26:00');
INSERT INTO `userorder` VALUES (156, 1, 1, 1, '2022-03-09', '2022-03-10', 150, 1, 1, '还凑合', '谷子昕', '111671199003078000', '13274544523', '2022-03-09 19:00:00');
INSERT INTO `userorder` VALUES (158, 1, 1, 1, '2022-03-11', '2022-03-12', 398, 2, 0, '暂无评论', '黄晨钊', '420102200105011111', '18971519112', '2022-03-11 15:07:04');
INSERT INTO `userorder` VALUES (159, 1, 26, 1, '2022-03-12', '2022-03-13', 796, 4, 1, '好', '黄晨钊', '420102200105010000', '18971519112', '2022-03-12 15:21:03');

SET FOREIGN_KEY_CHECKS = 1;
