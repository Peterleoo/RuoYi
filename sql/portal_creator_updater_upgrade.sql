-- portal_* 创建者升级脚本
-- 目标：
-- 1) 新增 creator_id / updater_id（BIGINT）
-- 2) 保留 create_by / update_by（用户名）
-- 3) 回填历史数据并建立索引
-- 4) 可重复执行

SET NAMES utf8mb4;

DROP PROCEDURE IF EXISTS sp_portal_add_col_if_missing;
DROP PROCEDURE IF EXISTS sp_portal_add_idx_if_missing;

DELIMITER $$

CREATE PROCEDURE sp_portal_add_col_if_missing(
    IN p_table VARCHAR(64),
    IN p_column VARCHAR(64),
    IN p_column_ddl TEXT
)
BEGIN
    DECLARE v_cnt INT DEFAULT 0;
    SELECT COUNT(1) INTO v_cnt
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = p_table
      AND COLUMN_NAME = p_column;

    IF v_cnt = 0 THEN
        SET @sql = CONCAT('ALTER TABLE `', p_table, '` ADD COLUMN ', p_column_ddl);
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;
END$$

CREATE PROCEDURE sp_portal_add_idx_if_missing(
    IN p_table VARCHAR(64),
    IN p_index VARCHAR(64),
    IN p_index_ddl TEXT
)
BEGIN
    DECLARE v_cnt INT DEFAULT 0;
    SELECT COUNT(1) INTO v_cnt
    FROM information_schema.STATISTICS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = p_table
      AND INDEX_NAME = p_index;

    IF v_cnt = 0 THEN
        SET @sql = CONCAT('ALTER TABLE `', p_table, '` ADD INDEX `', p_index, '` ', p_index_ddl);
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
    END IF;
END$$

DELIMITER ;

-- 1) 加列
CALL sp_portal_add_col_if_missing('portal_school', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_school', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_teacher', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_teacher', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_course', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_course', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_content', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_content', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_activity', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_activity', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_magazine', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_magazine', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');
CALL sp_portal_add_col_if_missing('portal_media', 'creator_id', '`creator_id` BIGINT NULL COMMENT ''创建者ID''');
CALL sp_portal_add_col_if_missing('portal_media', 'updater_id', '`updater_id` BIGINT NULL COMMENT ''更新者ID''');

-- 2) 加索引
CALL sp_portal_add_idx_if_missing('portal_school', 'idx_portal_school_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_teacher', 'idx_portal_teacher_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_course', 'idx_portal_course_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_content', 'idx_portal_content_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_activity', 'idx_portal_activity_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_magazine', 'idx_portal_magazine_creator_id', '(`creator_id`)');
CALL sp_portal_add_idx_if_missing('portal_media', 'idx_portal_media_creator_id', '(`creator_id`)');

-- 3) 历史回填（优先数字，再按 login_name 匹配）
-- portal_school
UPDATE portal_school
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_school p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_school
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_school p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_teacher
UPDATE portal_teacher
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_teacher p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_teacher
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_teacher p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_course
UPDATE portal_course
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_course p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_course
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_course p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_content
UPDATE portal_content
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_content p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_content
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_content p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_activity
UPDATE portal_activity
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_activity p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_activity
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_activity p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_magazine
UPDATE portal_magazine
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_magazine p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_magazine
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_magazine p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- portal_media
UPDATE portal_media
SET creator_id = CAST(create_by AS UNSIGNED)
WHERE creator_id IS NULL AND create_by REGEXP '^[0-9]+$';
UPDATE portal_media p JOIN sys_user u ON u.login_name = p.create_by
SET p.creator_id = u.user_id
WHERE p.creator_id IS NULL AND p.create_by IS NOT NULL AND p.create_by <> '';
UPDATE portal_media
SET updater_id = CAST(update_by AS UNSIGNED)
WHERE updater_id IS NULL AND update_by REGEXP '^[0-9]+$';
UPDATE portal_media p JOIN sys_user u ON u.login_name = p.update_by
SET p.updater_id = u.user_id
WHERE p.updater_id IS NULL AND p.update_by IS NOT NULL AND p.update_by <> '';

-- 4) 规范化 create_by/update_by 为登录名（若当前为空或数字）
UPDATE portal_school p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_school p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_teacher p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_teacher p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_course p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_course p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_content p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_content p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_activity p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_activity p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_magazine p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_magazine p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

UPDATE portal_media p JOIN sys_user u ON u.user_id = p.creator_id
SET p.create_by = u.login_name
WHERE p.creator_id IS NOT NULL AND (p.create_by IS NULL OR p.create_by = '' OR p.create_by REGEXP '^[0-9]+$');
UPDATE portal_media p JOIN sys_user u ON u.user_id = p.updater_id
SET p.update_by = u.login_name
WHERE p.updater_id IS NOT NULL AND (p.update_by IS NULL OR p.update_by = '' OR p.update_by REGEXP '^[0-9]+$');

DROP PROCEDURE IF EXISTS sp_portal_add_col_if_missing;
DROP PROCEDURE IF EXISTS sp_portal_add_idx_if_missing;
