
CREATE DATABASE /*!32312 IF NOT EXISTS*/`local_resource_data` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `local_resource_data`;

DROP TABLE IF EXISTS `resource_locations_config`;
CREATE TABLE `resource_locations_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `config_name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置名称',
  `lm_location_path` varchar(200) NOT NULL DEFAULT '' COMMENT 'linux或Mac-本地地址',
  `wd_location_path` varchar(200) NOT NULL DEFAULT '' COMMENT 'windows-本地地址',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1-启用 2-禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='本地映射路径配置';


DROP TABLE IF EXISTS `resource_video`;
CREATE TABLE `resource_video` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `video_name` varchar(200) DEFAULT NULL COMMENT '视频名字',
  `video_path` varchar(2000) DEFAULT NULL COMMENT '视频地址',
  `video_size` varchar(100) DEFAULT NULL COMMENT '视频大小',
  `video_describe` varchar(200) DEFAULT NULL COMMENT '视频描述信息',
  `broadcast_number` int(11) DEFAULT NULL COMMENT '播放次数',
  `praise_number` int(11) DEFAULT NULL COMMENT '点赞次数',
  `service_id` bigint(20) DEFAULT NULL COMMENT '服务ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='本地视频表';


DROP TABLE IF EXISTS `service_path`;
CREATE TABLE `service_path` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `config_id` int(11) unsigned NOT NULL COMMENT '配置id',
  `service_name` varchar(200) DEFAULT NULL COMMENT '服务名称',
  `path_dir` varchar(300) DEFAULT NULL COMMENT '服务地址（废弃字段）',
  `service_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务地址信息';


DROP TABLE IF EXISTS `source_collections`;
CREATE TABLE `source_collections` (
  `id` bigint(20) DEFAULT NULL COMMENT 'id',
  `collection_name` varchar(100) DEFAULT NULL COMMENT '收藏名字',
  `collection_path` varchar(200) DEFAULT NULL COMMENT '收藏地址',
  `collection_type` tinyint(4) DEFAULT NULL COMMENT '收藏类型 1-视频',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

