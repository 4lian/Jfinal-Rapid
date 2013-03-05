DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity` (
	`id` int(10) NOT NULL COMMENT 'id' AUTO_INCREMENT,
	`name` varchar(20)  COMMENT '表名' ,
	`type` int(10)  COMMENT '类型' ,
	`label` varchar(200)  COMMENT '描述' ,
	`service_id` int(10)  COMMENT '所属业务' ,
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='entity';

DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
	`id` int(10) NOT NULL COMMENT 'id' AUTO_INCREMENT,
	`name` varchar(20)  COMMENT '字段名' ,
	`label` varchar(50)  COMMENT '描述' ,
	`display` int(10)  COMMENT '是否显示' ,
	`isPrimaryKey` int(10)  COMMENT '是否为主键' ,
	`search` int(10)  COMMENT '查询类型' ,
	`isReaderOnly` int(10)  COMMENT '是否只读' ,
	`isHidder` int(10)  COMMENT '是否隐藏' ,
	`defaultValue` varchar(20)  COMMENT '默认值' ,
	`validator` int(10)  COMMENT '校验类型' ,
	`length` varchar(10)  COMMENT '长度' ,
	`scale` varchar(10)  COMMENT '精度' ,
	`fieldType` varchar(10)  COMMENT '数据库字段类型' ,
	`javaType` varchar(10)  COMMENT 'java数据类型' ,
	`entity_id` int(10)  COMMENT '所属实体' ,
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='field';

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
	`id` int(10) NOT NULL COMMENT 'id' AUTO_INCREMENT,
	`name` varchar(20)  COMMENT '项目名' ,
	`desc` varchar(200)  COMMENT '项目描述' ,
	`workspace` varchar(200)  COMMENT '工作空间路径' ,
	`viewFramework` varchar(20)  COMMENT '试图风格' ,
	`dbPool` varchar(20)  COMMENT '数据链接池' ,
	`ip` varchar(32)  COMMENT 'IP' ,
	`port` varchar(10)  COMMENT '端口' ,
	`templates` varchar(200)  COMMENT '模版文件路径' ,
	`webRoot` varchar(200)  COMMENT 'webRoot根路径' ,
	`src` varchar(200)  COMMENT 'src原代码路径' ,
	`config` varchar(200)  COMMENT '配置文件路径' ,
	`dbType` varchar(20)  COMMENT '数据库类型' ,
	`jdbcurl` varchar(100)  COMMENT 'DBurl' ,
	`username` varchar(20)  COMMENT 'DBuser' ,
	`password` varchar(20)  COMMENT 'DBpwd' ,
	`packageName` varchar(50)  COMMENT '包名' ,
	`viewType` varchar(20)  COMMENT '模版引擎类型' ,
	`compileLeve` varchar(4)  COMMENT '编译级别' ,
	`embedded` int(10)  COMMENT '是否内嵌生成器' ,
	`build` varchar(20)  COMMENT '构建方式' ,
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='project';

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
	`id` int(10) NOT NULL COMMENT 'id' AUTO_INCREMENT,
	`name` varchar(20)  COMMENT '业务模块名称' ,
	`code` int(10)  COMMENT '编号' ,
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='service';

