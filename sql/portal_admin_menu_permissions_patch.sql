-- =============================================
-- 门户后台菜单与权限补丁（学校/教师/课程）
-- 适用场景：已有库增量执行，不重建表
-- 建议库：dev_db
-- =============================================

USE dev_db;

-- 目录：学校课程管理（挂在“系统管理”下，parent_id=1）
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3210, '学校课程管理', 1, 10, '#', '', 'M', '0', '0', '', 'fa fa-university', 'admin', SYSDATE(), '', NULL, '学校课程管理目录'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id = 3210);

-- 学校管理
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3211, '学校管理', 3210, 1, '/admin/portal/school', '', 'C', '0', '0', 'portal:school:view', 'fa fa-building-o', 'admin', SYSDATE(), '', NULL, '学校管理菜单'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:school:view');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3212, '学校查询', 3211, 1, '#', '', 'F', '0', '0', 'portal:school:list', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:school:list');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3213, '学校新增', 3211, 2, '#', '', 'F', '0', '0', 'portal:school:add', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:school:add');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3214, '学校修改', 3211, 3, '#', '', 'F', '0', '0', 'portal:school:edit', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:school:edit');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3215, '学校删除', 3211, 4, '#', '', 'F', '0', '0', 'portal:school:remove', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:school:remove');

-- 教师管理
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3221, '教师管理', 3210, 2, '/admin/portal/teacher', '', 'C', '0', '0', 'portal:teacher:view', 'fa fa-user-circle-o', 'admin', SYSDATE(), '', NULL, '教师管理菜单'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:teacher:view');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3222, '教师查询', 3221, 1, '#', '', 'F', '0', '0', 'portal:teacher:list', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:teacher:list');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3223, '教师新增', 3221, 2, '#', '', 'F', '0', '0', 'portal:teacher:add', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:teacher:add');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3224, '教师修改', 3221, 3, '#', '', 'F', '0', '0', 'portal:teacher:edit', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:teacher:edit');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3225, '教师删除', 3221, 4, '#', '', 'F', '0', '0', 'portal:teacher:remove', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:teacher:remove');

-- 课程管理
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3231, '课程管理', 3210, 3, '/admin/portal/course', '', 'C', '0', '0', 'portal:course:view', 'fa fa-book', 'admin', SYSDATE(), '', NULL, '课程管理菜单'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:course:view');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3232, '课程查询', 3231, 1, '#', '', 'F', '0', '0', 'portal:course:list', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:course:list');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3233, '课程新增', 3231, 2, '#', '', 'F', '0', '0', 'portal:course:add', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:course:add');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3234, '课程修改', 3231, 3, '#', '', 'F', '0', '0', 'portal:course:edit', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:course:edit');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3235, '课程删除', 3231, 4, '#', '', 'F', '0', '0', 'portal:course:remove', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:course:remove');

-- 多媒体资源管理
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3241, '多媒体资源', 3210, 4, '/admin/portal/media', '', 'C', '0', '0', 'portal:media:view', 'fa fa-play-circle-o', 'admin', SYSDATE(), '', NULL, '多媒体资源菜单'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:media:view');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3242, '多媒体查询', 3241, 1, '#', '', 'F', '0', '0', 'portal:media:list', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:media:list');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3243, '多媒体新增', 3241, 2, '#', '', 'F', '0', '0', 'portal:media:add', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:media:add');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3244, '多媒体修改', 3241, 3, '#', '', 'F', '0', '0', 'portal:media:edit', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:media:edit');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3245, '多媒体删除', 3241, 4, '#', '', 'F', '0', '0', 'portal:media:remove', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:media:remove');

-- 活动管理
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3251, '活动管理', 3210, 5, '/admin/portal/activity', '', 'C', '0', '0', 'portal:activity:view', 'fa fa-calendar', 'admin', SYSDATE(), '', NULL, '活动管理菜单'
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:activity:view');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3252, '活动查询', 3251, 1, '#', '', 'F', '0', '0', 'portal:activity:list', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:activity:list');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3253, '活动新增', 3251, 2, '#', '', 'F', '0', '0', 'portal:activity:add', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:activity:add');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3254, '活动修改', 3251, 3, '#', '', 'F', '0', '0', 'portal:activity:edit', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:activity:edit');

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
SELECT 3255, '活动删除', 3251, 4, '#', '', 'F', '0', '0', 'portal:activity:remove', '#', 'admin', SYSDATE(), '', NULL, ''
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE perms = 'portal:activity:remove');

-- 授权超级管理员角色(role_id=1)
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, m.menu_id
FROM sys_menu m
WHERE m.menu_id IN (3210,3211,3212,3213,3214,3215,3221,3222,3223,3224,3225,3231,3232,3233,3234,3235)
  AND NOT EXISTS (
    SELECT 1 FROM sys_role_menu rm WHERE rm.role_id = 1 AND rm.menu_id = m.menu_id
  );

INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, m.menu_id
FROM sys_menu m
WHERE m.menu_id IN (3241,3242,3243,3244,3245,3251,3252,3253,3254,3255)
  AND NOT EXISTS (
    SELECT 1 FROM sys_role_menu rm WHERE rm.role_id = 1 AND rm.menu_id = m.menu_id
  );
