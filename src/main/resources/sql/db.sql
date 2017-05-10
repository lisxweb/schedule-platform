/*!40101 SET NAMES utf8 */;

DROP TABLE IF EXISTS `shift`;
CREATE TABLE `shift` (
	shift_id int(11) NOT NULL AUTO_INCREMENT,
	shift_name varchar(50),
	shift_num varchar(50),
	shift_color  varchar(20),
	start_at  varchar(20),
	end_at  varchar(20),
	total_at int(11) default 0,
	interval_at int(11) default 0,
	relevance  varchar(20),
	station  varchar(20),
	stationArea  varchar(20),
	creator_id int(11) default 0,
	updator_id int(11) default 0,
	if_use int(1) default 1,
	created_at datetime,
	updated_at datetime,
  PRIMARY KEY (shift_id)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `post_setting`;
CREATE TABLE `post_setting` (
	post_id int(11) NOT NULL AUTO_INCREMENT,
	post_name varchar(50),
	post_code varchar(50),
	post_month  int(11) default 0,
	post_year  int(11) default 0,
	post_weekly  int(11) default 0,
	creator_id int(11) default 0,
	updator_id int(11) default 0,
	if_use int(1) default 1,
	created_at datetime,
	updated_at datetime,
  PRIMARY KEY (post_id)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS grouping;
CREATE TABLE grouping (
	`group_id` int(11) NOT NULL AUTO_INCREMENT,
	group_name varchar(50),
	group_code varchar(50),
	group_order  int(11) default 0,
	creator_id int(11) default 0,
	updator_id int(11) default 0,
	if_use int(1) default 1,
	created_at datetime,
	updated_at datetime,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单、连接、按钮权限',
  `permission_str` varchar(50) DEFAULT NULL COMMENT '菜单权限辨识字符串',
  `parent_permission_id` int(11) DEFAULT NULL COMMENT '父级菜单ID',
  `parent_permission_name` varchar(50) DEFAULT NULL COMMENT '父级菜单名字',
  `comment` text COMMENT '备注,说明',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` varchar(10) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `permission` VALUES ('1', '权限基础字段', '权限基础字段', '1', null, null, '1', '2016-10-31 10:37:47', '0');
INSERT INTO `permission` VALUES ('2', '系统管理', '系统管理', '1', null, null, '1', '2016-10-31 10:39:45', '0');
INSERT INTO `permission` VALUES ('3', '人员管理', '系统管理-人员管理', '2', '系统管理', null, '1', '2016-10-31 10:40:30', '0');
INSERT INTO `permission` VALUES ('4', '角色管理', '系统管理-角色管理', '2', '系统管理', null, '1', '2016-10-31 10:40:59', '0');
INSERT INTO `permission` VALUES ('5', '新增', '人员管理-新增', '3', '人员管理', null, '1', '2016-10-31 11:47:35', '0');
INSERT INTO `permission` VALUES ('6', '编辑', '人员管理-编辑', '3', '人员管理', null, '1', '2016-10-31 11:47:37', '0');
INSERT INTO `permission` VALUES ('7', '停用', '人员管理-停用', '3', '人员管理', null, '1', '2016-10-31 11:48:36', '0');
INSERT INTO `permission` VALUES ('8', '新增', '角色管理-新增', '4', '角色管理', null, '1', '2016-10-31 11:49:40', '0');
INSERT INTO `permission` VALUES ('9', '人员', '角色管理-人员', '4', '角色管理', null, '1', '2016-10-31 11:50:19', '0');
INSERT INTO `permission` VALUES ('10', '权限配置', '角色管理-权限配置', '4', '角色管理', null, '1', '2016-10-31 11:50:49', '0');
INSERT INTO `permission` VALUES ('11', '停用', '角色管理-停用', '4', '角色管理', null, '1', '2016-10-31 11:51:45', '0');


DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_deleted` varchar(10) DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


INSERT INTO `role` (`role_id`, `role_name`, `comment`, `creator_id`, `created_at`, `is_deleted`) VALUES ('1', 'admin', '最高权限', '1', '2016-09-07 10:09:07', '0');


DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '9');
INSERT INTO `role_permission` VALUES ('1', '10');
INSERT INTO `role_permission` VALUES ('1', '11');


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) NOT NULL,
  `user_pass` varchar(100) NOT NULL,
  `secret_key` varchar(64) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL COMMENT '工号',
  `user_job` varchar(64) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `station_area` varchar(30) DEFAULT NULL,
  `station` varchar(30) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `creator_id` varchar(30) default null,
  `is_deleted` int(1) DEFAULT '0',
  `is_admin` int(1) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


INSERT INTO `user` (`user_id`, `password`,`user_pass`, `secret_key`, `user_code`,`user_job`, `user_name`, `station_area`, `station`, `creator_id`, `created_at`, `is_deleted`,`is_admin`) VALUES
('999', 'e7b68248d0782b49f0d4efaefb0e508c','123456', 'cb7e52304f0d11e6965c00ff2c2e2b3f', 'admin', '系统管理员', '人名', '站区', '站点', 1, '2015-10-10 12:14:17', 0 , 0);



DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('999', '1');
