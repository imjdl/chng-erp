#创建系统用户表
DROP TABLE IF EXISTS system_user;
CREATE TABLE system_user (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    code VARCHAR(8) NOT NULL COMMENT '用户编码',
    user_name VARCHAR(20) NOT NULL COMMENT '用户名',
    password VARCHAR(32) NOT NULL COMMENT '密码',
    mobile VARCHAR(20) COMMENT '手机号码',
    email VARCHAR(20) COMMENT '邮箱',
    power_station_id INT COMMENT '发电站ID',
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