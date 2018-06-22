/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云-传创测试
Source Server Version : 50718
Source Host           : gz-cdb-b1xeazxc.sql.tencentcdb.com:63840
Source Database       : tautotest

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-13 16:24:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` int(11) DEFAULT NULL COMMENT '父id',
  `seq` int(5) DEFAULT NULL COMMENT '序号',
  `iconUrl` varchar(50) DEFAULT NULL COMMENT '图标',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `src` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `levelNum` int(1) DEFAULT NULL COMMENT '级别（辅助字段）',
  `isLeaf` bit(1) DEFAULT NULL COMMENT '是否叶子（辅助字段）',
  `remark` varchar(255) DEFAULT NULL COMMENT '接口备注',
  `nodePath` varchar(500) DEFAULT NULL,
  `isDelete` tinyint(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`menuId`),
  KEY `menuId` (`menuId`,`isDelete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '0', '1', null, '首页', '/management/admin/index', '0', '\0', null, null, '1');
INSERT INTO `system_menu` VALUES ('2', '1', '2', null, '用户列表', '/management/admin/userList', '1', '', null, null, '1');
INSERT INTO `system_menu` VALUES ('3', '2', '2', null, '用户添加或修改', '/management/admin/addUser', '2', '', null, null, '1');
INSERT INTO `system_menu` VALUES ('4', '2', '2', null, '用户禁用或启用', '/management/admin/userIsEnable', '2', '', null, null, '1');
INSERT INTO `system_menu` VALUES ('5', '277', '1', null, '基础设置', '', '0', '\0', null, null, '0');
INSERT INTO `system_menu` VALUES ('6', '280', '2', null, '系统组织管理', '', '1', '', null, null, '0');
INSERT INTO `system_menu` VALUES ('7', '6', '2', null, '组织架构列表', '/management/admin/organizationList', '1', '', null, null, '1');
INSERT INTO `system_menu` VALUES ('8', '6', '2', null, '组织机构添加或修改', '/management/admin/organizationEdit', '1', '', null, null, '1');
INSERT INTO `system_menu` VALUES ('50', '6', null, null, '组织禁用或开启', '/management/admin/organizationOnOff', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('51', '6', null, null, '自身或者下级架构列表', '/management/admin/carExpectWayList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('52', '6', null, null, '组织等级列表', '/management/admin/organizationLevelList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('53', '295', null, null, '仓库下拉列表', '/management/admin/organizationWarehouseList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('54', '6', null, null, '组织仓库编辑', '/management/admin/organizationWarehouseEdit', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('55', '6', null, null, '组织仓库删除', '/management/admin/organizationWarehouseDalete', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('56', '6', null, null, '获取组织详细信息', '/management/admin/organizationInfo', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('57', '280', null, null, '系统用户管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('58', '57', null, null, '用户登陆', '/management/admin/login', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('59', '57', null, null, '获取用户列表', '/management/admin/userList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('60', '57', null, null, '禁用或启用用户', '/management/admin/userIsEnable', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('61', '57', null, null, '添加用户', '/management/admin/addUser', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('62', '57', null, null, '登出', '/management/admin/loginOut', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('63', '57', null, null, '销售顾问列表', '/management/admin/salesList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('64', '57', null, null, '修改自身密码', '/management/admin/changePassword', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('65', '57', null, null, '重置其他用户的密码', '/management/admin/changeOtherPassword', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('66', '280', null, null, '系统角色管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('67', '66', null, null, '获取角色列表', '/management/admin/roleList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('68', '66', null, null, '角色添加修改', '/management/admin/roleEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('69', '66', null, null, '给角色分配菜单', '/management/admin/setRoleMenu', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('70', '66', null, null, '获取角色下拉列表', '/management/admin/roleListList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('71', '5', null, null, '权限菜单管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('72', '71', null, null, '获取树形菜单', '/management/admin/menuListTree', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('73', '71', null, null, '添加新菜单', '/management/admin/editMenu', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('74', '71', null, null, '删除菜单', '/management/admin/deleteMenu', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('75', '5', null, null, '车型资料管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('76', '75', null, null, '车辆类型', ' ', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('77', '75', null, null, '添加或修改车型', '/management/admin/carsEdit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('78', '75', null, null, '品牌数据的添加或修改', '/management/admin/carsBrandEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('79', '75', null, null, '车系的添加或修改', '/management/admin/carsFamilyEdit', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('80', '75', null, null, '车系的添加或修改', '/management/admin/carsStyleEdit', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('81', '75', null, null, '车辆品牌下拉列表', '/management/admin/carsBrandList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('82', '75', null, null, '获取车系下拉列表', '/management/admin/carsFamilyList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('83', '75', null, null, '获取车等级的下拉（高中低配）', '/management/admin/carsStyleList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('84', '75', null, null, '获取车系分页列表', '/management/admin/carsFamilyListPage', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('85', '75', null, null, '车型下拉列表', '/management/admin/carsListList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('86', '75', null, null, '购车方式下拉列表', '/management/admin/carExpectWayList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('87', '75', null, null, '获取品牌列表（含分页）', '/management/admin/carsBrandPageList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('88', '284', null, null, '获取车身颜色列表', '/management/admin/carColourGetByBrand', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('89', '284', null, null, '获取车身颜色照片', '/management/admin/carColourImageGetByCarColour', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('90', '284', null, null, '获取内饰颜色列表', '/management/admin/carInteriorGetByBrand', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('91', '284', null, null, '添加或修改车身颜色', '/management/admin/carColourEdit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('92', '284', null, null, '删除车身颜色', '/management/admin/carColourDelete', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('93', '284', null, null, '添加或修改内饰颜色', '/management/admin/carInteriorEdit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('94', '284', null, null, '给颜色添加图片', '/management/admin/carColourImageAdd', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('95', '75', null, null, '删除车身颜色对应图片', '/management/admin/carColourImageDelete', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('96', '280', null, null, '供应商管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('97', '96', null, null, '供应商列表', '/management/admin/supplierList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('98', '96', null, null, '供应商列表(下拉)', '/management/admin/supplierListList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('99', '96', null, null, '供应商新增/编辑', '/management/admin/supplierEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('100', '96', null, null, '供应商删除', '/management/admin/supplierDelete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('101', '277', null, null, '库存管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('102', '295', null, null, '入库单列表', '/management/admin/storageList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('103', '295', null, null, '入库单编辑', '/management/admin/storageEdit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('104', '295', null, null, '入库单详情', '/management/admin/storageInfo', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('105', '295', null, null, '入库单车辆列表', '/management/admin/storageCarList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('106', '295', null, null, '入库单车辆删除', '/management/admin/storageCarDelete', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('107', '295', null, null, '入库单车辆编辑', '/management/admin/storageCarEdit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('108', '295', null, null, '车辆库存列表', '/management/admin/stockCarList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('109', '101', null, null, '修改库存车辆信息（修改库存车辆的定金/优惠/是否线上展示等）', '/management/admin/stockCarEdit', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('110', '101', null, null, '库存车辆查看明细', '/management/admin/stockCarInfo', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('111', '101', null, null, '订车单列表(功能已屏蔽)', '/management/admin/stockOrderList', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('112', '101', null, null, '订车单详情(功能已屏蔽)', '/management/admin/stockOrderInfo', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('113', '101', null, null, '创建订车单(功能已屏蔽)', '/management/admin/stockOrderCreate', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('114', '101', null, null, '取消订车单(功能已屏蔽)', '/management/admin/stockOrderCancel', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('115', '101', null, null, '签收并自动入库(功能已屏蔽)', '/management/admin/stockOrderSign', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('116', '101', null, null, '二级组织更改尾款', '/management/admin/stockOrderchangebalancePrice', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('117', '101', null, null, '车辆出库(功能已屏蔽)', '/management/admin/stockOrderStorageOut', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('118', '101', null, null, '通知有车前置(功能已屏蔽)', '/management/admin/stockOrderNoticeBefor', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('119', '101', null, null, '车辆出库前置(功能已屏蔽)', '/management/admin/stockOrderStorageOutBefor', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('120', '277', null, null, '客户管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('121', '120', null, null, '客户列表', '/management/admin/customerOrgList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('122', '120', null, null, '追踪客户列表(功能已屏蔽)', '/management/admin/trackCustomerOrgList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('123', '120', null, null, '预约客户列表(功能已屏蔽)', '/management/admin/bespeakCustomerOrgList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('124', '120', null, null, '标记客户为追踪客户', '/management/admin/trackCustomerOrg', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('125', '120', null, null, '标记客户为不购车客户(功能已屏蔽)', '/management/admin/notBuyCustomerOrg', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('126', '120', null, null, '编辑或添加用户到店预约', '/management/admin/bespeakChangeCustomerOrg', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('127', '120', null, null, '查看客户信息', '/management/admin/customerUsersrInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('128', '120', null, null, '新增客户', '/management/admin/addCustomerUsersr', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('129', '281', null, null, '上传图片File', '/management/admin/uploadImage', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('130', '6', null, null, '门店列表', '/management/admin/organizationList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('131', '6', null, null, '门店等级', '/management/admin/organizationLevelListByLevel', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('132', '75', null, null, '车型信息(指导价等)', '/management/admin/carsInfo', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('133', '101', null, null, '三级订车单支付', '/management/admin/stockOrderPay', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('134', '101', null, null, '车辆出库通知有车(功能已屏蔽)', '/management/admin/stockOrderNotice', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('135', '75', null, null, '删除车型', '/management/admin/carsDelete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('136', '284', null, null, '删除内饰颜色', '/management/admin/carInteriorDelete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('137', '101', null, null, '三级订车单获取车辆定金(功能已屏蔽)', '/management/admin/carDepositPrice', null, null, '', null, '1');
INSERT INTO `system_menu` VALUES ('138', '281', null, null, '上传图片Base64', '/management/admin/uploadImageBase64', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('139', '120', null, null, '修改用户车辆信息', '/management/admin/changeUserCarInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('140', '120', null, null, '修改用户个人信息', '/management/admin/changeUserInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('141', '120', null, null, '添加备注', '/management/admin/addCustomerRemarks', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('142', '277', null, null, '银行审核', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('143', '142', null, null, '银行审核列表', '/management/admin/bankToExamineOrderList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('144', '142', null, null, '银行审核结果', '/management/admin/bankToExamineOrder', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('145', '120', null, null, '支付车辆', '/management/admin/turnOverVehicle', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('146', '120', null, null, '修改成全款', '/management/admin/changeFullPayment', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('147', '120', null, null, '银行审核', '/management/admin/bankApprovalPass', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('148', '120', null, null, '订单详情', '/management/admin/customerOrderInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('149', '120', null, null, '订单支付', '/management/admin/payInOrder', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('150', '120', null, null, '编辑订单', '/management/admin/editCustomerOrder', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('151', '120', null, null, '修改用户车辆信息', '/management/admin/changeUserCarInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('152', '120', null, null, '修改用户个人信息', '/management/admin/changeUserInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('153', '120', null, null, '销售顾问列表', '/management/admin/salesList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('154', '120', null, null, '更改销售顾问', '/management/admin/systenUserChangeCustomerOrg', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('155', '296', null, null, '出库列表', '/management/admin/customerOrderList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('156', '296', null, null, '出库车辆', '/management/admin/customerOrderStorageOut', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('157', '296', null, null, '出库车辆前置', '/management/admin/customerOrderStorageOutBefor', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('158', '120', null, null, '客户订单支付', '/management/admin/payInOrder', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('159', '120', null, null, '客户订单支付记录', '/management/admin/orderPayList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('160', '120', null, null, '客户订单支付各项记录', '/management/admin/orderPiaceList', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('161', '66', null, null, '获取组别列表', '/management/admin/systemGroupingList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('162', '66', null, null, '获取组别成员列表', '/management/admin/systemUserGroupingList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('163', '66', null, null, '编辑分组自身信息', '/management/admin/systemGroupingEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('164', '66', null, null, '编辑组内成员', '/management/admin/systemUserGroupingEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('165', '66', null, null, '删除组内成员', '/management/admin/systemUserGroupingDalete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('166', '66', null, null, '删除组', '/management/admin/systemGroupingDalete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('167', '66', null, null, '获取自身组织的成员', '/management/admin/orgOneSelfList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('169', '0', null, null, '小程序权限', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('170', '169', null, null, '个人中心', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('171', '170', null, null, '预约客户列表', '/emInterface/employee/bespeakCustomerOrgList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('172', '170', null, null, '小程序登录', 'login', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('173', '170', null, null, '退出', 'loginOut', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('174', '170', null, null, '更改密码', 'changePassword', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('175', '169', null, null, '客户管理', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('176', '175', null, null, '输入电话号码搜索电话号码列表', 'customerPhoneSearchList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('177', '175', null, null, '添加新用户', 'bespeakCustomerOrgList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('178', '175', null, null, '落定客户/贷款通过客户/待完款客户/待加装上牌客户/待提车客户列表', 'orderStateCustomerList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('179', '175', null, null, '预约客户列表', 'addCustomerUsersr', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('180', '175', null, null, '客户详细信息', 'customerUsersrInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('181', '175', null, null, '添加备注', 'addCustomerRemarks', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('182', '175', null, null, '上传文件', 'uploadFile', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('183', '169', null, null, '车辆库存及出库', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('184', '183', null, null, '车辆库存车辆列表（车辆库存）', 'stockCarList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('185', '183', null, null, '获取车辆品牌列表（车辆库存）', 'carsBrandList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('186', '183', null, null, '查看车辆类型具体库存（车辆库存）', 'stockCarInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('187', '183', null, null, '获取自身或者下级架构列表（下拉列表）', 'organizationLevelList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('188', '183', null, null, '获取车辆品牌', 'carsBrandList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('189', '183', null, null, '获取车大类列表（下拉）', 'carsListList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('190', '183', null, null, '获取车辆车系列表(下拉)', 'carsFamilyList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('191', '183', null, null, '获取待出库订单列表', 'customerOrderList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('192', '183', null, null, '获取待出库订单详情', 'customerOrderInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('193', '183', null, null, '根据订单查看库存车辆', 'customerOrderStockCar', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('194', '183', null, null, '出库', 'customerOrderStockCarPutout', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('195', '169', null, null, '二级入库', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('196', '195', null, null, '二级入库单列表', 'storageList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('197', '195', null, null, '组织仓库列表', 'organizationWarehouseList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('198', '195', null, null, '二级组织入库单编辑', 'storageEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('199', '195', null, null, '二级组织入库单详情', 'storageInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('200', '195', null, null, '根据二级组织入库单获取车辆列表', 'storageCarList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('201', '195', null, null, '删除', 'storageCarDelete', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('202', '195', null, null, '编辑二级入库的具体车辆', 'storageCarEdit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('203', '195', null, null, '获取用户本身组织的所有人员', 'orgOneSelfList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('205', '169', null, null, '加装上牌', ' ', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('206', '205', null, null, '待上牌列表', 'orderLicensePlateList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('207', '205', null, null, '上牌完成', 'licensePlateDone', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('208', '205', null, null, '待加装精品列表', 'carsProductsList', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('209', '205', null, null, '精品详情', 'carsProductsInfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('210', '205', null, null, '加装精品完成', 'carsProductsDone', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('211', '205', null, null, '添加预计加装精品时间', 'addCarsProductsEstimateDate', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('212', '0', null, null, '报表', ' ', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('213', '212', null, null, '获取打印信息（PC）', '/management/admin/customerOrderPrint', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('214', '76', null, null, '车辆类型列表', '/management/admin/carsList', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('215', '0', null, null, '报表', ' ', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('217', '101', null, null, '库存列表', 'backend/stockcar/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('218', '217', null, null, '库存编辑', 'backend/stockcar/edit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('219', '217', null, null, '库存详情', 'backend/stockcar/detail', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('220', '217', null, null, '导出库存', 'backend/stockcar/export', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('221', '277', null, null, '文章管理', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('222', '221', null, null, '文章列表', 'backend/article/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('223', '222', null, null, '编辑文章', 'backend/article/edit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('224', '222', null, null, '添加文章', 'backend/article/create', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('225', '222', null, null, '文章详情', 'backend/article/detail', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('226', '222', null, null, '发布文章', 'backend/article/publish', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('227', '222', null, null, '禁用/启用文章', 'backend/article/remove', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('228', '66', null, null, '角色列表', 'backend/role/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('229', '228', null, null, '添加角色', 'backend/role/create', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('230', '228', null, null, '编辑角色', 'backend/role/edit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('231', '228', null, null, '删除角色', 'backend/role/remove', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('232', '228', null, null, '配置权限', 'backend/roleaccess/addauth', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('233', '57', null, null, '系统用户列表', 'backend/systemuser/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('234', '233', null, null, '添加系统用户', 'backend/systemuser/create', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('237', '233', null, null, '用户上级列表', 'backend/systemuser/higherups', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('238', '228', null, null, '角色选择', 'backend/role/lists', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('239', '233', null, null, '编辑系统用户', 'backend/systemuser/edit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('240', '233', null, null, '系统用户详情', 'backend/systemuser/detail', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('241', '233', null, null, '禁用启用用户', 'backend/systemuser/remove', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('242', '66', null, null, '权限列表', 'backend/roleaccess/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('243', '242', null, null, '给角色配置权限', 'backend/roleaccess/addauth', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('245', '6', null, null, '组织列表', 'backend/organization/index', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('246', '6', null, null, '添加组织', 'backend/organization/create', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('247', '6', null, null, '编辑组织', 'backend/organization/edit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('248', '6', null, null, '组织详情', 'backend/organization/detail', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('249', '6', null, null, '启用禁用门店', 'backend/organization/remove', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('250', '228', null, null, '获取角色列表', 'backend/role/lists', null, null, null, null, '1');
INSERT INTO `system_menu` VALUES ('251', '6', null, null, '组织下拉列表', 'backend/organization/getorg', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('253', '277', null, null, '资源订单', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('254', '71', null, null, '添加菜单', 'backend/menu/create', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('255', '71', null, null, '编辑菜单', 'backend/menu/edit', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('256', '71', null, null, '删除菜单', 'backend/menu/remove', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('263', '71', null, null, '菜单列表', 'backend/menu/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('267', '221', null, null, '留言列表', 'backend/note/index', null, null, '这是备注', null, '0');
INSERT INTO `system_menu` VALUES ('268', '75', null, null, '车型列表', 'backend/car/index', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('269', '284', null, null, '颜色及内饰列表', 'backend/car/family', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('270', '96', null, null, '供应商列表', 'backend/supplier/index', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('271', '281', null, null, '品牌下拉列表', 'backend/publics/brand', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('272', '120', null, null, '客户订单列表', 'backend/customerorder/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('273', '120', null, null, '客户订单详情', 'backend/customerorder/detail', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('274', '233', null, null, '系统用户信息', 'backend/systemuser/userinfo', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('275', '253', null, null, '资源订单详情', 'backend/consumerorder/consumerdetail', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('276', '253', null, null, '资源订单列表', 'backend/consumerorder/index', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('277', '0', null, null, 'PC后台权限', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('280', '277', null, null, '系统管理', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('281', '0', null, null, '通用权限', '', null, null, null, null, '0');
INSERT INTO `system_menu` VALUES ('284', '5', null, null, '颜色及内饰管理', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('285', '253', null, null, '资源订单导出', 'backend/consumerorder/export', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('286', '253', null, null, '获取支付信息', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('287', '253', null, null, '收取定金或尾款', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('288', '120', null, null, '客户订单支付', 'backend/customerorder/pay', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('289', '253', null, null, '获取资源订单支付信息', 'backend/consumerorder/getpayinfo', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('290', '253', null, null, '资源订单支付', 'backend/consumerorder/pay', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('291', '280', null, null, '仓库管理', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('292', '291', null, null, '仓库列表', 'backend/stock/index', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('293', '291', null, null, '添加仓库', 'backend/stock/create', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('294', '291', null, null, '编辑仓库', 'backend/stock/edit', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('295', '101', null, null, '车辆入库', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('296', '101', null, null, '车辆出库', '', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('297', '291', null, null, '删除仓库', 'backend/stock/remove', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('298', '96', null, null, '添加/编辑供应商', 'backend/supplier/create', null, null, '', null, '0');
INSERT INTO `system_menu` VALUES ('299', '96', null, null, '删除供应商', 'backend/supplier/remove', null, null, '', null, '0');
