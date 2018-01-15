ALTER TABLE `car` DROP FOREIGN KEY `fk_car_account_1`;
ALTER TABLE `enter_person` DROP FOREIGN KEY `fk_enter_person_car_1`;
ALTER TABLE `enter_person` DROP FOREIGN KEY `fk_enter_person_bjjj_account_1`;
ALTER TABLE `member_info` DROP FOREIGN KEY `fk_member_info_bjjj_account_1`;
ALTER TABLE `apply_status` DROP FOREIGN KEY `fk_apply_status_car_1`;
ALTER TABLE `apply_status` DROP FOREIGN KEY `fk_apply_status_member_info_1`;
ALTER TABLE `virtual_device` DROP FOREIGN KEY `fk_virtual_device_bjjj_account_1`;

DROP TABLE `bjjj_account`;
DROP TABLE `car`;
DROP TABLE `enter_person`;
DROP TABLE `member_info`;
DROP TABLE `virtual_device`;
DROP TABLE `apply_status`;

CREATE TABLE `bjjj_account` (
`userid` varchar(256) NOT NULL,
`phone` varchar(64) NULL,
`name` varchar(64) NULL,
`sex` varchar(8) NULL,
`headurl` varchar(256) NULL,
`create_time` datetime NULL,
`update_time` datetime NULL,
PRIMARY KEY (`userid`) 
);

CREATE TABLE `car` (
`carid` varchar(256) NOT NULL,
`userid` varchar(256) NOT NULL,
`licenseno` varchar(256) NULL COMMENT '车牌号',
`engineno` varchar(256) NULL COMMENT '发动机号',
`carmodel` varchar(256) NULL COMMENT '车辆品牌型号',
`carregtime` varchar(256) NULL COMMENT '车辆注册日期 yyyy-MM-dd',
`vehicletype` varchar(8) NULL COMMENT '03 小型客车 04 小型货车 02 大型货车 52 小型新能源汽车 51 大型新能源汽车',
`cartype` varchar(8) NULL COMMENT '01 大型汽车 02小型汽车 03使馆汽车 04领馆汽车 05境外汽车 06外国汽车 26香港入出境车 27澳门入出境车',
`env_grade` varchar(255) NULL COMMENT '环保等级',
`create_time` datetime NULL,
`update_time` datetime NULL,
PRIMARY KEY (`carid`, `userid`) 
);

CREATE TABLE `enter_person` (
`userid` varchar(256) NOT NULL,
`carid` varchar(256) NOT NULL,
`driverlicenseno` varchar(256) NOT NULL,
`drivername` varchar(256) NOT NULL,
`drivingphoto` varchar(1024) NULL,
`carphoto` varchar(1024) NULL,
`driverphoto` varchar(1024) NULL,
`personphoto` varchar(1024) NULL,
`create_time` datetime NULL,
`update_time` datetime NULL,
PRIMARY KEY (`userid`, `carid`, `driverlicenseno`) 
);

CREATE TABLE `member_info` (
`memberid` varchar(256) NOT NULL,
`member_type` varchar(8) NULL COMMENT '"0" 试用 "1"正式 ',
`start_date` date NULL,
`end_date` date NULL,
`create_time` datetime NULL,
`update_time` datetime NULL,
PRIMARY KEY (`memberid`) 
);

CREATE TABLE `virtual_device` (
`deviceid` varchar(256) NOT NULL,
`userid` varchar(256) NULL,
`imei` varchar(256) NULL,
`imsi` varchar(256) NULL,
`gpslon` varchar(256) NULL,
`gpslat` varchar(256) NULL,
`create_time` datetime NULL,
`update_time` datetime NULL,
PRIMARY KEY (`deviceid`) 
);

CREATE TABLE `apply_status` (
`userid` varchar(256) NOT NULL,
`carid` varchar(256) NOT NULL,
`apply_status` varchar(8) NULL COMMENT '"0"审核失败 "1"审核成功  "2"审核中 "3"请求失败',
`enterbjstart` date NULL,
`enterbjend` date NULL,
`apply_time` datetime NULL,
`update_time` datetime NULL,
`create_time` datetime NULL,
PRIMARY KEY (`userid`, `carid`) 
);


ALTER TABLE `car` ADD CONSTRAINT `fk_car_account_1` FOREIGN KEY (`userid`) REFERENCES `bjjj_account` (`userid`);
ALTER TABLE `enter_person` ADD CONSTRAINT `fk_enter_person_car_1` FOREIGN KEY (`carid`) REFERENCES `car` (`carid`);
ALTER TABLE `enter_person` ADD CONSTRAINT `fk_enter_person_bjjj_account_1` FOREIGN KEY (`userid`) REFERENCES `bjjj_account` (`userid`);
ALTER TABLE `member_info` ADD CONSTRAINT `fk_member_info_bjjj_account_1` FOREIGN KEY (`memberid`) REFERENCES `bjjj_account` (`userid`);
ALTER TABLE `apply_status` ADD CONSTRAINT `fk_apply_status_car_1` FOREIGN KEY (`carid`) REFERENCES `car` (`carid`);
ALTER TABLE `apply_status` ADD CONSTRAINT `fk_apply_status_member_info_1` FOREIGN KEY (`userid`) REFERENCES `bjjj_account` (`userid`);
ALTER TABLE `virtual_device` ADD CONSTRAINT `fk_virtual_device_bjjj_account_1` FOREIGN KEY (`userid`) REFERENCES `bjjj_account` (`userid`);

