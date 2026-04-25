-- =============================================
-- 门户扩展：内容=文章、杂志扩展、多媒体、活动
-- 适用数据库：dev_db (MySQL 8+)
-- =============================================
USE dev_db;

-- =========================
-- 1) 扩展内容表（作为文章表）
-- =========================
ALTER TABLE portal_content
    ADD COLUMN IF NOT EXISTS category_id BIGINT NULL COMMENT '分类ID' AFTER content_id,
    ADD COLUMN IF NOT EXISTS school_id BIGINT NULL COMMENT '所属学校ID' AFTER category_id,
    ADD COLUMN IF NOT EXISTS sub_title VARCHAR(255) NULL COMMENT '副标题' AFTER title,
    ADD COLUMN IF NOT EXISTS summary VARCHAR(500) NULL COMMENT '摘要' AFTER content,
    ADD COLUMN IF NOT EXISTS cover_image VARCHAR(255) NULL COMMENT '封面图' AFTER summary,
    ADD COLUMN IF NOT EXISTS source VARCHAR(255) NULL COMMENT '来源' AFTER author,
    ADD COLUMN IF NOT EXISTS audit_status CHAR(1) DEFAULT '0' COMMENT '审核状态(0待审 1通过 2驳回)' AFTER view_count,
    ADD COLUMN IF NOT EXISTS publish_time DATETIME NULL COMMENT '发布时间' AFTER audit_status,
    ADD COLUMN IF NOT EXISTS media_type VARCHAR(20) NULL COMMENT '媒体类型(text/video/audio/pdf)' AFTER remark,
    ADD COLUMN IF NOT EXISTS media_url VARCHAR(500) NULL COMMENT '媒体URL' AFTER media_type,
    ADD COLUMN IF NOT EXISTS media_duration VARCHAR(50) NULL COMMENT '媒体时长' AFTER media_url,
    ADD COLUMN IF NOT EXISTS media_size VARCHAR(50) NULL COMMENT '媒体大小' AFTER media_duration;

CREATE INDEX idx_portal_content_school_id ON portal_content(school_id);
CREATE INDEX idx_portal_content_publish_time ON portal_content(publish_time);
CREATE INDEX idx_portal_content_audit_status ON portal_content(audit_status);

-- =========================
-- 2) 扩展杂志表
-- =========================
ALTER TABLE portal_magazine
    ADD COLUMN IF NOT EXISTS publish_year INT NULL COMMENT '年份' AFTER magazine_issue,
    ADD COLUMN IF NOT EXISTS h5_url VARCHAR(500) NULL COMMENT 'H5阅读链接' AFTER magazine_pdf,
    ADD COLUMN IF NOT EXISTS introduction TEXT NULL COMMENT '简介' AFTER h5_url;

CREATE INDEX idx_portal_magazine_publish_year ON portal_magazine(publish_year);

-- =========================
-- 3) 新增多媒体资源表
-- =========================
CREATE TABLE IF NOT EXISTS portal_media (
  media_id        BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '资源ID',
  title           VARCHAR(255) NOT NULL COMMENT '标题',
  media_type      CHAR(1) NOT NULL COMMENT '类型(1音频 2视频)',
  cover_image     VARCHAR(255) DEFAULT '' COMMENT '封面图',
  media_url       VARCHAR(500) NOT NULL COMMENT '文件/云盘链接',
  duration        VARCHAR(50) DEFAULT '' COMMENT '时长',
  article_id      BIGINT NULL COMMENT '关联文章ID',
  course_id       BIGINT NULL COMMENT '关联课程ID',
  status          CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by       VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time     DATETIME NULL COMMENT '创建时间',
  update_by       VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time     DATETIME NULL COMMENT '更新时间',
  remark          VARCHAR(500) DEFAULT NULL COMMENT '备注',
  KEY idx_media_type (media_type),
  KEY idx_media_article (article_id),
  KEY idx_media_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='多媒体资源表';

-- =========================
-- 4) 新增活动信息表
-- =========================
CREATE TABLE IF NOT EXISTS portal_activity (
  activity_id             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '活动ID',
  school_id               BIGINT NOT NULL COMMENT '所属学校ID',
  activity_name           VARCHAR(255) NOT NULL COMMENT '活动名称',
  category                VARCHAR(50) DEFAULT '' COMMENT '活动分类(讲座/比赛/游学/演出)',
  cover_image             VARCHAR(255) DEFAULT '' COMMENT '活动封面图',
  activity_content        LONGTEXT COMMENT '活动详情(RichText)',
  start_time              DATETIME NULL COMMENT '活动开始时间',
  end_time                DATETIME NULL COMMENT '活动结束时间',
  registration_deadline   DATETIME NULL COMMENT '报名截止时间',
  location                VARCHAR(255) DEFAULT '' COMMENT '活动地点',
  capacity                INT DEFAULT 0 COMMENT '人数限制',
  current_registrations   INT DEFAULT 0 COMMENT '当前报名人数',
  activity_status         CHAR(1) DEFAULT '0' COMMENT '活动状态(0预热中 1进行中 2已结束)',
  status                  CHAR(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by               VARCHAR(64) DEFAULT '' COMMENT '创建者',
  create_time             DATETIME NULL COMMENT '创建时间',
  update_by               VARCHAR(64) DEFAULT '' COMMENT '更新者',
  update_time             DATETIME NULL COMMENT '更新时间',
  remark                  VARCHAR(500) DEFAULT NULL COMMENT '备注',
  KEY idx_activity_school (school_id),
  KEY idx_activity_status (activity_status),
  KEY idx_activity_start_time (start_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动信息表';
