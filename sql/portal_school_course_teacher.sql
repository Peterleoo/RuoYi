-- =============================================
-- 门户扩展：学校/教师/课程
-- 适配库：MySQL 5.7+
-- 执行前请确认当前数据库：USE dev_db;
-- =============================================

-- ----------------------------
-- 1) 学校/机构表
-- ----------------------------
DROP TABLE IF EXISTS portal_school;
CREATE TABLE portal_school (
  school_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '学校ID',
  dept_id                BIGINT(20)      DEFAULT NULL               COMMENT '所属部门ID(sys_dept.dept_id)',
  school_name            VARCHAR(200)    NOT NULL                   COMMENT '学校名称',
  logo                   VARCHAR(255)    DEFAULT ''                 COMMENT 'Logo',
  introduction           TEXT                                       COMMENT '简介',
  address                VARCHAR(255)    DEFAULT ''                 COMMENT '地址',
  longitude              DECIMAL(10,6)   DEFAULT NULL               COMMENT '经度',
  latitude               DECIMAL(10,6)   DEFAULT NULL               COMMENT '纬度',
  contact_phone          VARCHAR(30)     DEFAULT ''                 COMMENT '联系电话',
  transport_guide        VARCHAR(500)    DEFAULT ''                 COMMENT '交通指南',
  enrollment_brochure    LONGTEXT                                   COMMENT '招生简章(RichText)',
  cover_image            VARCHAR(255)    DEFAULT ''                 COMMENT '封面图',
  status                 CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by              VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time            DATETIME                                   COMMENT '创建时间',
  update_by              VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time            DATETIME                                   COMMENT '更新时间',
  remark                 VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (school_id),
  KEY idx_school_dept (dept_id),
  KEY idx_school_name (school_name)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='学校/机构表';

-- ----------------------------
-- 2) 教师表
-- ----------------------------
DROP TABLE IF EXISTS portal_teacher;
CREATE TABLE portal_teacher (
  teacher_id             BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '教师ID',
  school_id              BIGINT(20)      NOT NULL                   COMMENT '所属学校ID(portal_school.school_id)',
  teacher_name           VARCHAR(100)    NOT NULL                   COMMENT '姓名',
  photo                  VARCHAR(255)    DEFAULT ''                 COMMENT '照片',
  title                  VARCHAR(200)    DEFAULT ''                 COMMENT '职称/头衔',
  expertise              VARCHAR(255)    DEFAULT ''                 COMMENT '擅长领域',
  bio                    TEXT                                       COMMENT '个人简介',
  status                 CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by              VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time            DATETIME                                   COMMENT '创建时间',
  update_by              VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time            DATETIME                                   COMMENT '更新时间',
  remark                 VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (teacher_id),
  KEY idx_teacher_school (school_id),
  KEY idx_teacher_name (teacher_name)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='教师表';

-- ----------------------------
-- 3) 课程表
-- ----------------------------
DROP TABLE IF EXISTS portal_course;
CREATE TABLE portal_course (
  course_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '课程ID',
  school_id              BIGINT(20)      NOT NULL                   COMMENT '所属学校ID(portal_school.school_id)',
  course_name            VARCHAR(200)    NOT NULL                   COMMENT '课程名称',
  course_category        VARCHAR(100)    DEFAULT ''                 COMMENT '课程分类(艺术/科技等)',
  teacher_id             BIGINT(20)      DEFAULT NULL               COMMENT '讲师ID(portal_teacher.teacher_id)',
  cover_image            VARCHAR(255)    DEFAULT ''                 COMMENT '封面图',
  course_intro           TEXT                                       COMMENT '课程简介',
  class_schedule         TEXT                                       COMMENT '上课时间安排',
  replay_video_url       VARCHAR(500)    DEFAULT ''                 COMMENT '录播视频链接',
  tuition_fee            DECIMAL(10,2)   DEFAULT 0.00               COMMENT '学费',
  enrollment_status      CHAR(1)         DEFAULT '0'                COMMENT '招生状态（0招生中 1已招满 2已结束）',
  status                 CHAR(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  create_by              VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time            DATETIME                                   COMMENT '创建时间',
  update_by              VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time            DATETIME                                   COMMENT '更新时间',
  remark                 VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (course_id),
  KEY idx_course_school (school_id),
  KEY idx_course_teacher (teacher_id),
  KEY idx_course_category (course_category),
  KEY idx_course_enrollment_status (enrollment_status)
) ENGINE=InnoDB AUTO_INCREMENT=100 COMMENT='课程表';

-- =============================================
-- 初始化示例数据（可按需删除）
-- =============================================

INSERT INTO portal_school (
  school_id, dept_id, school_name, logo, introduction, address, longitude, latitude,
  contact_phone, transport_guide, enrollment_brochure, cover_image, status,
  create_by, create_time, update_by, update_time, remark
) VALUES
(100, 103, '长沙市老干部大学', '/profile/upload/school/logo-cs.png',
 '长沙市老干部大学致力于为老年群体提供高品质课程与活动。', '长沙市岳麓区麓山南路88号',
 112.938814, 28.228209, '0731-88886666',
 '可乘坐地铁2号线至溁湾镇站，步行约10分钟到达。',
 '<p>招生对象：50岁以上人群；报名方式：线上线下均可。</p>',
 '/profile/upload/school/cover-cs.jpg', '0',
 'admin', SYSDATE(), '', NULL, '示例数据'),
(101, 101, '株洲市老年大学', '/profile/upload/school/logo-zz.png',
 '株洲市老年大学开设书画、声乐、智能科技等课程。', '株洲市天元区长江北路66号',
 113.136905, 27.827450, '0731-88997766',
 '公交T26/T60可达，校区提供无障碍通道。',
 '<p>全年滚动招生，支持试听课程。</p>',
 '/profile/upload/school/cover-zz.jpg', '0',
 'admin', SYSDATE(), '', NULL, '示例数据');

INSERT INTO portal_teacher (
  teacher_id, school_id, teacher_name, photo, title, expertise, bio, status,
  create_by, create_time, update_by, update_time, remark
) VALUES
(100, 100, '周清风', '/profile/upload/teacher/zhou.png', '国画讲师',
 '国画、书法', '从事国画教学20余年，擅长花鸟与山水教学。', '0',
 'admin', SYSDATE(), '', NULL, '示例教师'),
(101, 100, '王建民', '/profile/upload/teacher/wang.png', '太极拳教练',
 '太极拳、养生功法', '国家一级社会体育指导员，专注中老年健身课程。', '0',
 'admin', SYSDATE(), '', NULL, '示例教师'),
(102, 101, '陈晓琳', '/profile/upload/teacher/chen.png', '新媒体讲师',
 '短视频、手机摄影', '长期从事老年数字素养培训，课程实操性强。', '0',
 'admin', SYSDATE(), '', NULL, '示例教师');

INSERT INTO portal_course (
  course_id, school_id, course_name, course_category, teacher_id, cover_image,
  course_intro, class_schedule, replay_video_url, tuition_fee, enrollment_status,
  status, create_by, create_time, update_by, update_time, remark
) VALUES
(100, 100, '水墨画零基础入门', '艺术', 100, '/profile/upload/course/course-ink.jpg',
 '面向零基础学员，学习笔法、构图与设色。', '每周二/周四 09:00-10:30',
 'https://example.com/video/course-ink', 299.00, '0',
 '0', 'admin', SYSDATE(), '', NULL, '示例课程'),
(101, 100, '太极拳二十四式教学', '体育', 101, '/profile/upload/course/course-taiji.jpg',
 '系统学习二十四式动作规范与呼吸配合。', '每周一/周三/周五 08:00-09:00',
 'https://example.com/video/course-taiji', 199.00, '0',
 '0', 'admin', SYSDATE(), '', NULL, '示例课程'),
(102, 101, '手机短视频剪辑指南', '科技', 102, '/profile/upload/course/course-video.jpg',
 '学习拍摄、剪辑与发布，帮助学员记录生活。', '每周六 14:30-16:30',
 'https://example.com/video/course-shortvideo', 399.00, '0',
 '0', 'admin', SYSDATE(), '', NULL, '示例课程');

-- =============================================
-- 后台菜单（学校/教师/课程）
-- 说明：
-- 1) parent_id=1（系统管理）；
-- 2) 使用 2100+ 菜单ID，避免与现有冲突；
-- 3) INSERT IGNORE 防止重复执行报错。
-- =============================================

-- 目录
INSERT IGNORE INTO sys_menu VALUES
('2100', '学校课程管理', '1', '10', '#', '', 'M', '0', '1', '', 'fa fa-university', 'admin', SYSDATE(), '', NULL, '学校课程管理目录');

-- 学校管理
INSERT IGNORE INTO sys_menu VALUES
('2101', '学校管理', '2100', '1', '/admin/portal/school', '', 'C', '0', '1', 'portal:school:view', 'fa fa-building-o', 'admin', SYSDATE(), '', NULL, '学校管理菜单'),
('2102', '学校查询', '2101', '1', '#', '', 'F', '0', '1', 'portal:school:list', '#', 'admin', SYSDATE(), '', NULL, ''),
('2103', '学校新增', '2101', '2', '#', '', 'F', '0', '1', 'portal:school:add', '#', 'admin', SYSDATE(), '', NULL, ''),
('2104', '学校修改', '2101', '3', '#', '', 'F', '0', '1', 'portal:school:edit', '#', 'admin', SYSDATE(), '', NULL, ''),
('2105', '学校删除', '2101', '4', '#', '', 'F', '0', '1', 'portal:school:remove', '#', 'admin', SYSDATE(), '', NULL, ''),
('2106', '学校导出', '2101', '5', '#', '', 'F', '0', '1', 'portal:school:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 教师管理
INSERT IGNORE INTO sys_menu VALUES
('2111', '教师管理', '2100', '2', '/admin/portal/teacher', '', 'C', '0', '1', 'portal:teacher:view', 'fa fa-user-circle-o', 'admin', SYSDATE(), '', NULL, '教师管理菜单'),
('2112', '教师查询', '2111', '1', '#', '', 'F', '0', '1', 'portal:teacher:list', '#', 'admin', SYSDATE(), '', NULL, ''),
('2113', '教师新增', '2111', '2', '#', '', 'F', '0', '1', 'portal:teacher:add', '#', 'admin', SYSDATE(), '', NULL, ''),
('2114', '教师修改', '2111', '3', '#', '', 'F', '0', '1', 'portal:teacher:edit', '#', 'admin', SYSDATE(), '', NULL, ''),
('2115', '教师删除', '2111', '4', '#', '', 'F', '0', '1', 'portal:teacher:remove', '#', 'admin', SYSDATE(), '', NULL, ''),
('2116', '教师导出', '2111', '5', '#', '', 'F', '0', '1', 'portal:teacher:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 课程管理
INSERT IGNORE INTO sys_menu VALUES
('2121', '课程管理', '2100', '3', '/admin/portal/course', '', 'C', '0', '1', 'portal:course:view', 'fa fa-book', 'admin', SYSDATE(), '', NULL, '课程管理菜单'),
('2122', '课程查询', '2121', '1', '#', '', 'F', '0', '1', 'portal:course:list', '#', 'admin', SYSDATE(), '', NULL, ''),
('2123', '课程新增', '2121', '2', '#', '', 'F', '0', '1', 'portal:course:add', '#', 'admin', SYSDATE(), '', NULL, ''),
('2124', '课程修改', '2121', '3', '#', '', 'F', '0', '1', 'portal:course:edit', '#', 'admin', SYSDATE(), '', NULL, ''),
('2125', '课程删除', '2121', '4', '#', '', 'F', '0', '1', 'portal:course:remove', '#', 'admin', SYSDATE(), '', NULL, ''),
('2126', '课程导出', '2121', '5', '#', '', 'F', '0', '1', 'portal:course:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 给“普通角色(2)”分配新菜单（若你不需要可删除）
INSERT IGNORE INTO sys_role_menu VALUES
('2', '2100'), ('2', '2101'), ('2', '2102'), ('2', '2103'), ('2', '2104'), ('2', '2105'), ('2', '2106'),
('2', '2111'), ('2', '2112'), ('2', '2113'), ('2', '2114'), ('2', '2115'), ('2', '2116'),
('2', '2121'), ('2', '2122'), ('2', '2123'), ('2', '2124'), ('2', '2125'), ('2', '2126');

