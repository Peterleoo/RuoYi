-- Portal 数据权限：历史创建人字段迁移脚本
-- 目的：将 create_by / update_by 中的登录名迁移为 user_id 字符串，匹配“仅本人数据权限”
-- 适用：MySQL 8.x

update portal_school ps
join sys_user su on su.login_name = ps.create_by
set ps.create_by = cast(su.user_id as char)
where ps.create_by is not null
  and ps.create_by <> ''
  and ps.create_by regexp '[^0-9]';

update portal_school ps
join sys_user su on su.login_name = ps.update_by
set ps.update_by = cast(su.user_id as char)
where ps.update_by is not null
  and ps.update_by <> ''
  and ps.update_by regexp '[^0-9]';

update portal_teacher t
join sys_user su on su.login_name = t.create_by
set t.create_by = cast(su.user_id as char)
where t.create_by is not null
  and t.create_by <> ''
  and t.create_by regexp '[^0-9]';

update portal_teacher t
join sys_user su on su.login_name = t.update_by
set t.update_by = cast(su.user_id as char)
where t.update_by is not null
  and t.update_by <> ''
  and t.update_by regexp '[^0-9]';

update portal_course c
join sys_user su on su.login_name = c.create_by
set c.create_by = cast(su.user_id as char)
where c.create_by is not null
  and c.create_by <> ''
  and c.create_by regexp '[^0-9]';

update portal_course c
join sys_user su on su.login_name = c.update_by
set c.update_by = cast(su.user_id as char)
where c.update_by is not null
  and c.update_by <> ''
  and c.update_by regexp '[^0-9]';

update portal_activity a
join sys_user su on su.login_name = a.create_by
set a.create_by = cast(su.user_id as char)
where a.create_by is not null
  and a.create_by <> ''
  and a.create_by regexp '[^0-9]';

update portal_activity a
join sys_user su on su.login_name = a.update_by
set a.update_by = cast(su.user_id as char)
where a.update_by is not null
  and a.update_by <> ''
  and a.update_by regexp '[^0-9]';

update portal_magazine m
join sys_user su on su.login_name = m.create_by
set m.create_by = cast(su.user_id as char)
where m.create_by is not null
  and m.create_by <> ''
  and m.create_by regexp '[^0-9]';

update portal_magazine m
join sys_user su on su.login_name = m.update_by
set m.update_by = cast(su.user_id as char)
where m.update_by is not null
  and m.update_by <> ''
  and m.update_by regexp '[^0-9]';

update portal_media m
join sys_user su on su.login_name = m.create_by
set m.create_by = cast(su.user_id as char)
where m.create_by is not null
  and m.create_by <> ''
  and m.create_by regexp '[^0-9]';

update portal_media m
join sys_user su on su.login_name = m.update_by
set m.update_by = cast(su.user_id as char)
where m.update_by is not null
  and m.update_by <> ''
  and m.update_by regexp '[^0-9]';
