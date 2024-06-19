CREATE TABLE `short_url` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lurl` varchar(150) NOT NULL,
  `surl` varchar(10) NOT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_surl` (`surl`),
  KEY `idx_lurl` (`lurl`)
) ENGINE=InnoDB AUTO_INCREMENT=15536 DEFAULT CHARSET=utf8;