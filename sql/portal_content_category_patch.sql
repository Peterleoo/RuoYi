-- =============================================
-- 内容分类表补丁
-- 适用数据库：dev_db
-- =============================================
USE dev_db;

CREATE TABLE IF NOT EXISTS portal_content_category (
  category_id   BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
  category_name VARCHAR(100) NOT NULL COMMENT '分类名称',
  sort_num      INT DEFAULT 10 COMMENT '排序',
  status        CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by     VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time   DATETIME NULL COMMENT '创建时间',
  update_by     VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time   DATETIME NULL COMMENT '更新时间',
  remark        VARCHAR(500) DEFAULT NULL COMMENT '备注',
  UNIQUE KEY uk_portal_content_category_name (category_name),
  KEY idx_portal_content_category_sort (sort_num)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='内容分类表';

