#创建系统用户表
DROP TABLE IF EXISTS system_user;
CREATE TABLE system_user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    code VARCHAR(8) NOT NULL COMMENT '用户编码',
    user_name VARCHAR(20) NOT NULL COMMENT '用户名',
    password VARCHAR(32) NOT NULL COMMENT '密码',
    mobile VARCHAR(20) COMMENT '手机号码',
    email VARCHAR(20) COMMENT '邮箱',
    power_station_id BIGINT COMMENT '发电站ID',
    user_type TINYINT NOT NULL COMMENT '用户类型,1-超级管理员，2-普通用户',
    last_login_time DATETIME COMMENT '最后登录时间',
    last_login_ip_address VARCHAR(20) COMMENT '最后一次登录IP地址',
    login_count INT NOT NULL DEFAULT 0 COMMENT '登录次数',
    enabled TINYINT NOT NULL DEFAULT 0 COMMENT '账户是否启用，0-未启用，1-启用',
    create_time DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    last_update_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后更新时间',
    last_update_user_id BIGINT NOT NULL COMMENT '最后更新人id',
    last_update_remark VARCHAR(255) COMMENT '最后更新备注',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除'
);

#创建权限表
DROP TABLE IF EXISTS system_privilege;
CREATE TABLE system_privilege
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    privilege_code VARCHAR(20) NOT NULL COMMENT '权限编码',
    privilege_type TINYINT NOT NULL COMMENT '1-开放权限，2-需要权限，3-需要认证',
    service_name VARCHAR(50) NOT NULL COMMENT '服务名称',
    resource_name VARCHAR(20) NOT NULL COMMENT '资源名称',
    controller_name VARCHAR(50) COMMENT 'controller name',
    operate_name VARCHAR(20) COMMENT '操作名称',
    action_name VARCHAR(50) COMMENT 'action name',
    parent_id BIGINT NOT NULL COMMENT '父级权限ID',
    remark VARCHAR(255) COMMENT '备注',
    create_time DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    last_update_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后更新时间',
    last_update_user_id BIGINT NOT NULL COMMENT '最后更新人id',
    last_update_remark VARCHAR(255) COMMENT '最后更新备注',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除'
);

#创建系统角色表
DROP TABLE IF EXISTS system_role;
CREATE TABLE system_role (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    application_name VARCHAR(20) NOT NULL COMMENT '系统名称',
    role_code VARCHAR(8) NOT NULL COMMENT '角色编码',
    role_name VARCHAR(20) NOT NULL COMMENT '角色名称',
    create_time DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    last_update_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后更新时间',
    last_update_user_id BIGINT NOT NULL COMMENT '最后更新人id',
    last_update_remark VARCHAR(255) COMMENT '最后更新备注',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除'
);

#创建角色和权限的关联表
DROP TABLE IF EXISTS system_role_privilege_r;
CREATE TABLE system_role_privilege_r (
  role_id BIGINT NOT NULL COMMENT '角色id',
  privilege_id BIGINT NOT NULL COMMENT '权限id',
  PRIMARY KEY (role_id, privilege_id)
);

#创建用户和角色的关联表
DROP TABLE IF EXISTS system_user_role_r;
CREATE TABLE system_user_role_r (
  user_id BIGINT NOT NULL COMMENT '用户id',
  role_id BIGINT NOT NULL COMMENT '角色id',
  PRIMARY KEY (user_id,role_id)
);

#创建发电站表
DROP TABLE IF EXISTS power_station;
CREATE TABLE power_station (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    `code` VARCHAR(20) NOT NULL COMMENT '发电站编码',
    `name` VARCHAR(20) NOT NULL COMMENT '发电站名称',
    address VARCHAR(50) NOT NULL COMMENT '发电站地址',
    parent_id BIGINT COMMENT '上级发电站ID',
    can_input_data TINYINT NOT NULL DEFAULT 0 COMMENT '是否能录入数据，0-否，1-是',
    is_photovoltaic TINYINT NOT NULL DEFAULT 0 COMMENT '是否为光电站，0-否，1-是',
    is_wind_power TINYINT NOT NULL DEFAULT 0 COMMENT '是否为风电站，0-否，1-是',
    photovoltaic_installed_capacity DECIMAL(11, 3) COMMENT '光伏装机容量',
    wind_power_installed_capacity DECIMAL(11, 3) COMMENT '风电装机容量',
    create_time DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    last_update_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后更新时间',
    last_update_user_id BIGINT NOT NULL COMMENT '最后更新人id',
    last_update_remark VARCHAR(255) COMMENT '最后更新备注',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除'
);

#创建序列表
DROP TABLE IF EXISTS sequence;
CREATE TABLE sequence (
    `name` VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '序列名称',
    current_value INT(11) UNSIGNED NOT NULL COMMENT '当前值',
    increment INT(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '每次增长的值'
);
INSERT  INTO sequence(`name`, current_value, increment)
VALUES ('power_station_code', 80000000, 1), ('system_user_code', 80000000, 1);

#创建获取当前值的存储函数
DELIMITER $$
DROP FUNCTION IF EXISTS current_value$$
CREATE FUNCTION current_value(sequence_name VARCHAR(20)) RETURNS INT(11)
BEGIN
    DECLARE `value` INT;
    SET `value` = 0;
    SELECT current_value INTO `value` FROM sequence WHERE `name` = sequence_name;
    IF `value` = 0 THEN
        SET `value` = 1;
        INSERT INTO `sequence`(`name`, `current_value`) VALUES(sequence_name, `value`);
    END IF;
    RETURN `value`;
END$$
DELIMITER ;

#创建获取下一个值的存储过程
DELIMITER $$
DROP FUNCTION IF EXISTS next_value$$
CREATE FUNCTION next_value(sequence_name VARCHAR(20)) RETURNS INT(11)
BEGIN
    UPDATE sequence SET current_value = current_value + increment WHERE `name` = sequence_name;
    RETURN current_value(sequence_name);
END$$

DELIMITER ;

DROP TABLE IF EXISTS configuration;
CREATE TABLE configuration (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    deployment_environment VARCHAR(20) NOT NULL COMMENT '部署环境',
    partition_code VARCHAR(20) NOT NULL COMMENT '分区码',
    service_name VARCHAR(20) NOT NULL COMMENT '服务名称',
    configuration_key VARCHAR(200) COMMENT '配置key',
    configuration_value VARCHAR(200) COMMENT '配置value',
    create_time DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    last_update_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后更新时间',
    last_update_user_id BIGINT NOT NULL COMMENT '最后更新人id',
    last_update_remark VARCHAR(255) COMMENT '最后更新备注',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除'
) COMMENT = '配置表';