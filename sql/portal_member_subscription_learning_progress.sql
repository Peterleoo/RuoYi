-- Member magazine subscription
CREATE TABLE IF NOT EXISTS `portal_magazine_subscription` (
  `subscription_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Subscription ID',
  `member_id` BIGINT NOT NULL COMMENT 'Member ID',
  `magazine_id` BIGINT NOT NULL COMMENT 'Magazine ID',
  `status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '0-active 1-canceled',
  `subscribe_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Subscribe time',
  `cancel_time` DATETIME DEFAULT NULL COMMENT 'Cancel time',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT 'Created by',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT 'Updated by',
  `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`subscription_id`),
  UNIQUE KEY `uk_member_magazine` (`member_id`, `magazine_id`),
  KEY `idx_pms_member` (`member_id`),
  KEY `idx_pms_magazine` (`magazine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Magazine subscriptions';

-- Member course progress
CREATE TABLE IF NOT EXISTS `portal_course_learning_progress` (
  `progress_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Progress ID',
  `member_id` BIGINT NOT NULL COMMENT 'Member ID',
  `course_id` BIGINT NOT NULL COMMENT 'Course ID',
  `progress_percent` INT NOT NULL DEFAULT 0 COMMENT '0-100',
  `progress_status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '0-not started 1-learning 2-completed',
  `study_minutes` INT NOT NULL DEFAULT 0 COMMENT 'Study minutes',
  `last_study_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Last study time',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT 'Created by',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT 'Updated by',
  `update_time` DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`progress_id`),
  UNIQUE KEY `uk_member_course` (`member_id`, `course_id`),
  KEY `idx_pclp_member` (`member_id`),
  KEY `idx_pclp_course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Course learning progress';
