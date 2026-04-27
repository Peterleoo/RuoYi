-- =============================================
-- 资讯/杂志菜单按钮归位 + 初始化数据（独立脚本）
-- 适用库：dev_db
-- 说明：
-- 1) 将“资讯管理、杂志管理”挂到“内容管理”下
-- 2) 补齐两者增删查改按钮权限（F 按钮）
-- 3) 资讯/杂志各插入 20 条演示数据
-- =============================================

USE dev_db;

-- ---------------------------------------------
-- A. 菜单与按钮（兼容旧版 sys_menu: url/target/is_refresh）
-- ---------------------------------------------

-- 内容管理菜单ID（优先按权限找，其次按名称）
SET @content_parent_id = (
    SELECT m.menu_id
    FROM sys_menu m
    WHERE m.perms = 'portal:content:view'
    ORDER BY m.menu_id
    LIMIT 1
);
SET @content_parent_id = IFNULL(
    @content_parent_id,
    (
        SELECT m.menu_id
        FROM sys_menu m
        WHERE m.menu_name = '内容管理' AND m.menu_type = 'C'
        ORDER BY m.menu_id
        LIMIT 1
    )
);

-- 若仍为空，兜底创建“内容管理”菜单（挂到学校课程管理 3210，或系统管理 1）
SET @fallback_parent = (
    SELECT m.menu_id
    FROM sys_menu m
    WHERE m.menu_id = 3210
    LIMIT 1
);
SET @fallback_parent = IFNULL(@fallback_parent, 1);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3270, '内容管理', @fallback_parent, 6, '/admin/portal/content', '', 'C', '0', '1', 'portal:content:view', 'fa fa-file-text-o', 'admin', SYSDATE(), '', NULL, '内容管理菜单'
FROM DUAL
WHERE @content_parent_id IS NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:content:view');

SET @content_parent_id = IFNULL(
    @content_parent_id,
    (
        SELECT m.menu_id
        FROM sys_menu m
        WHERE m.perms = 'portal:content:view'
        ORDER BY m.menu_id
        LIMIT 1
    )
);

-- 资讯管理菜单（按 portal:content:view 作为资讯页入口）
UPDATE sys_menu
SET parent_id = @content_parent_id,
    order_num = 1,
    url = '/admin/portal/content',
    menu_name = '资讯管理',
    icon = 'fa fa-newspaper-o',
    update_by = 'admin',
    update_time = SYSDATE()
WHERE perms = 'portal:content:view'
  AND @content_parent_id IS NOT NULL;

-- 杂志管理菜单
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3280, '杂志管理', @content_parent_id, 2, '/admin/portal/magazine', '', 'C', '0', '1', 'portal:magazine:view', 'fa fa-book', 'admin', SYSDATE(), '', NULL, '杂志管理菜单'
FROM DUAL
WHERE @content_parent_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:magazine:view');

UPDATE sys_menu
SET parent_id = @content_parent_id,
    order_num = 2,
    url = '/admin/portal/magazine',
    menu_name = '杂志管理',
    icon = 'fa fa-book',
    update_by = 'admin',
    update_time = SYSDATE()
WHERE perms = 'portal:magazine:view'
  AND @content_parent_id IS NOT NULL;

-- 资讯管理按钮（F）
SET @news_menu_id = (
    SELECT m.menu_id
    FROM sys_menu m
    WHERE m.perms = 'portal:content:view'
    ORDER BY m.menu_id
    LIMIT 1
);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3271, '资讯查询', @news_menu_id, 1, '#', '', 'F', '0', '1', 'portal:content:list', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @news_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:content:list');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3272, '资讯新增', @news_menu_id, 2, '#', '', 'F', '0', '1', 'portal:content:add', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @news_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:content:add');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3273, '资讯修改', @news_menu_id, 3, '#', '', 'F', '0', '1', 'portal:content:edit', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @news_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:content:edit');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3274, '资讯删除', @news_menu_id, 4, '#', '', 'F', '0', '1', 'portal:content:remove', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @news_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:content:remove');

-- 杂志管理按钮（F）
SET @mag_menu_id = (
    SELECT m.menu_id
    FROM sys_menu m
    WHERE m.perms = 'portal:magazine:view'
    ORDER BY m.menu_id
    LIMIT 1
);

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3281, '杂志查询', @mag_menu_id, 1, '#', '', 'F', '0', '1', 'portal:magazine:list', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @mag_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:magazine:list');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3282, '杂志新增', @mag_menu_id, 2, '#', '', 'F', '0', '1', 'portal:magazine:add', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @mag_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:magazine:add');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3283, '杂志修改', @mag_menu_id, 3, '#', '', 'F', '0', '1', 'portal:magazine:edit', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @mag_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:magazine:edit');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3284, '杂志删除', @mag_menu_id, 4, '#', '', 'F', '0', '1', 'portal:magazine:remove', '#', 'admin', SYSDATE(), '', NULL, ''
FROM DUAL
WHERE @mag_menu_id IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:magazine:remove');

-- 超级管理员授权（role_id=1）
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, m.menu_id
FROM sys_menu m
WHERE m.perms IN (
    'portal:content:view','portal:content:list','portal:content:add','portal:content:edit','portal:content:remove',
    'portal:magazine:view','portal:magazine:list','portal:magazine:add','portal:magazine:edit','portal:magazine:remove'
)
AND NOT EXISTS (
    SELECT 1 FROM sys_role_menu rm WHERE rm.role_id = 1 AND rm.menu_id = m.menu_id
);

-- ---------------------------------------------
-- B. 初始化数据：资讯 20 条、杂志 20 条
-- ---------------------------------------------

INSERT INTO portal_content
(
    title, category, author, content, view_count, status,
    create_by, create_time, update_by, update_time, remark
)
VALUES
('银龄课堂快讯01', '政策动态', '运营中心', '示例资讯内容01：围绕老年教育服务优化进行说明。', 126, '0', 'admin', DATE_SUB(NOW(), INTERVAL 20 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 20 DAY), '初始化数据'),
('银龄课堂快讯02', '校园新闻', '编辑部', '示例资讯内容02：本周课程安排与活动预告。', 88, '0', 'admin', DATE_SUB(NOW(), INTERVAL 19 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 19 DAY), '初始化数据'),
('银龄课堂快讯03', '健康讲堂', '医养组', '示例资讯内容03：春季养生科普专栏。', 205, '0', 'admin', DATE_SUB(NOW(), INTERVAL 18 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 18 DAY), '初始化数据'),
('银龄课堂快讯04', '文体活动', '活动组', '示例资讯内容04：书法摄影作品征集。', 64, '0', 'admin', DATE_SUB(NOW(), INTERVAL 17 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 17 DAY), '初始化数据'),
('银龄课堂快讯05', '政策动态', '运营中心', '示例资讯内容05：智慧助老专项行动公告。', 172, '0', 'admin', DATE_SUB(NOW(), INTERVAL 16 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 16 DAY), '初始化数据'),
('银龄课堂快讯06', '校园新闻', '编辑部', '示例资讯内容06：新学期开班仪式报道。', 93, '0', 'admin', DATE_SUB(NOW(), INTERVAL 15 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 15 DAY), '初始化数据'),
('银龄课堂快讯07', '科技课堂', '教务组', '示例资讯内容07：手机摄影进阶课程上线。', 144, '0', 'admin', DATE_SUB(NOW(), INTERVAL 14 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 14 DAY), '初始化数据'),
('银龄课堂快讯08', '健康讲堂', '医养组', '示例资讯内容08：慢病管理专题课堂总结。', 231, '0', 'admin', DATE_SUB(NOW(), INTERVAL 13 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 13 DAY), '初始化数据'),
('银龄课堂快讯09', '文体活动', '活动组', '示例资讯内容09：合唱团展演时间通知。', 75, '0', 'admin', DATE_SUB(NOW(), INTERVAL 12 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 12 DAY), '初始化数据'),
('银龄课堂快讯10', '校园新闻', '编辑部', '示例资讯内容10：讲师团队新增成员介绍。', 109, '0', 'admin', DATE_SUB(NOW(), INTERVAL 11 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 11 DAY), '初始化数据'),
('银龄课堂快讯11', '政策动态', '运营中心', '示例资讯内容11：学习积分规则优化说明。', 167, '0', 'admin', DATE_SUB(NOW(), INTERVAL 10 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 10 DAY), '初始化数据'),
('银龄课堂快讯12', '科技课堂', '教务组', '示例资讯内容12：短视频剪辑训练营报名开启。', 312, '0', 'admin', DATE_SUB(NOW(), INTERVAL 9 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 9 DAY), '初始化数据'),
('银龄课堂快讯13', '健康讲堂', '医养组', '示例资讯内容13：中医养生直播回放发布。', 186, '0', 'admin', DATE_SUB(NOW(), INTERVAL 8 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 8 DAY), '初始化数据'),
('银龄课堂快讯14', '文体活动', '活动组', '示例资讯内容14：太极晨练社团招新。', 97, '0', 'admin', DATE_SUB(NOW(), INTERVAL 7 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 7 DAY), '初始化数据'),
('银龄课堂快讯15', '校园新闻', '编辑部', '示例资讯内容15：学员风采专题更新。', 134, '0', 'admin', DATE_SUB(NOW(), INTERVAL 6 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 6 DAY), '初始化数据'),
('银龄课堂快讯16', '政策动态', '运营中心', '示例资讯内容16：社区联学合作计划发布。', 58, '0', 'admin', DATE_SUB(NOW(), INTERVAL 5 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 5 DAY), '初始化数据'),
('银龄课堂快讯17', '科技课堂', '教务组', '示例资讯内容17：AI 助老应用体验课开讲。', 275, '0', 'admin', DATE_SUB(NOW(), INTERVAL 4 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 4 DAY), '初始化数据'),
('银龄课堂快讯18', '健康讲堂', '医养组', '示例资讯内容18：睡眠改善课程上线。', 143, '0', 'admin', DATE_SUB(NOW(), INTERVAL 3 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 3 DAY), '初始化数据'),
('银龄课堂快讯19', '文体活动', '活动组', '示例资讯内容19：广场舞公开课安排。', 121, '0', 'admin', DATE_SUB(NOW(), INTERVAL 2 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 2 DAY), '初始化数据'),
('银龄课堂快讯20', '校园新闻', '编辑部', '示例资讯内容20：本月学习榜单发布。', 266, '0', 'admin', DATE_SUB(NOW(), INTERVAL 1 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 1 DAY), '初始化数据');

INSERT INTO portal_magazine
(
    magazine_title, magazine_cover, magazine_issue, publish_date, magazine_content,
    magazine_pdf, status, create_by, create_time, update_by, update_time, remark
)
VALUES
('银龄月刊', '/profile/upload/magazine/cover_01.jpg', '2026年第01期', DATE_SUB(CURDATE(), INTERVAL 20 MONTH), '杂志示例内容01：老年教育年度计划专刊。', '/profile/upload/magazine/issue_01.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 20 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 20 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_02.jpg', '2026年第02期', DATE_SUB(CURDATE(), INTERVAL 19 MONTH), '杂志示例内容02：名师公开课精选。', '/profile/upload/magazine/issue_02.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 19 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 19 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_03.jpg', '2026年第03期', DATE_SUB(CURDATE(), INTERVAL 18 MONTH), '杂志示例内容03：健康养生专题。', '/profile/upload/magazine/issue_03.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 18 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 18 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_04.jpg', '2026年第04期', DATE_SUB(CURDATE(), INTERVAL 17 MONTH), '杂志示例内容04：老年大学风采。', '/profile/upload/magazine/issue_04.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 17 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 17 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_05.jpg', '2026年第05期', DATE_SUB(CURDATE(), INTERVAL 16 MONTH), '杂志示例内容05：政策解读合集。', '/profile/upload/magazine/issue_05.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 16 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 16 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_06.jpg', '2026年第06期', DATE_SUB(CURDATE(), INTERVAL 15 MONTH), '杂志示例内容06：智慧助老案例。', '/profile/upload/magazine/issue_06.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 15 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 15 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_07.jpg', '2026年第07期', DATE_SUB(CURDATE(), INTERVAL 14 MONTH), '杂志示例内容07：银龄讲师访谈。', '/profile/upload/magazine/issue_07.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 14 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 14 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_08.jpg', '2026年第08期', DATE_SUB(CURDATE(), INTERVAL 13 MONTH), '杂志示例内容08：线上课堂运营报告。', '/profile/upload/magazine/issue_08.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 13 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 13 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_09.jpg', '2026年第09期', DATE_SUB(CURDATE(), INTERVAL 12 MONTH), '杂志示例内容09：学习达人故事。', '/profile/upload/magazine/issue_09.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 12 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 12 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_10.jpg', '2026年第10期', DATE_SUB(CURDATE(), INTERVAL 11 MONTH), '杂志示例内容10：课程共建专栏。', '/profile/upload/magazine/issue_10.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 11 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 11 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_11.jpg', '2026年第11期', DATE_SUB(CURDATE(), INTERVAL 10 MONTH), '杂志示例内容11：传统文化传承。', '/profile/upload/magazine/issue_11.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 10 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 10 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_12.jpg', '2026年第12期', DATE_SUB(CURDATE(), INTERVAL 9 MONTH), '杂志示例内容12：老年旅行课堂。', '/profile/upload/magazine/issue_12.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 9 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 9 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_13.jpg', '2026年第13期', DATE_SUB(CURDATE(), INTERVAL 8 MONTH), '杂志示例内容13：摄影课程成果展。', '/profile/upload/magazine/issue_13.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 8 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 8 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_14.jpg', '2026年第14期', DATE_SUB(CURDATE(), INTERVAL 7 MONTH), '杂志示例内容14：书法课堂专辑。', '/profile/upload/magazine/issue_14.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 7 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 7 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_15.jpg', '2026年第15期', DATE_SUB(CURDATE(), INTERVAL 6 MONTH), '杂志示例内容15：音乐与合唱。', '/profile/upload/magazine/issue_15.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 6 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 6 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_16.jpg', '2026年第16期', DATE_SUB(CURDATE(), INTERVAL 5 MONTH), '杂志示例内容16：智能手机课堂。', '/profile/upload/magazine/issue_16.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 5 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 5 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_17.jpg', '2026年第17期', DATE_SUB(CURDATE(), INTERVAL 4 MONTH), '杂志示例内容17：短视频创作指南。', '/profile/upload/magazine/issue_17.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 4 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 4 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_18.jpg', '2026年第18期', DATE_SUB(CURDATE(), INTERVAL 3 MONTH), '杂志示例内容18：银龄志愿服务。', '/profile/upload/magazine/issue_18.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 3 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 3 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_19.jpg', '2026年第19期', DATE_SUB(CURDATE(), INTERVAL 2 MONTH), '杂志示例内容19：活动赛事回顾。', '/profile/upload/magazine/issue_19.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 2 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 2 DAY), '初始化数据'),
('银龄月刊', '/profile/upload/magazine/cover_20.jpg', '2026年第20期', DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '杂志示例内容20：年度成果汇编。', '/profile/upload/magazine/issue_20.pdf', '0', 'admin', DATE_SUB(NOW(), INTERVAL 1 DAY), 'admin', DATE_SUB(NOW(), INTERVAL 1 DAY), '初始化数据');

