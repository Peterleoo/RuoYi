-- ----------------------------
-- 门户网站新增表结构
-- ----------------------------

-- ----------------------------
-- 1、杂志管理表
-- ----------------------------
drop table if exists portal_magazine;
create table portal_magazine (
  magazine_id         bigint(20)      not null auto_increment    comment '杂志ID',
  magazine_title      varchar(255)    not null                   comment '杂志标题',
  magazine_cover      varchar(255)    default ''                 comment '杂志封面',
  magazine_issue      varchar(50)     default ''                 comment '期号',
  publish_date        date                                       comment '出版日期',
  magazine_content    text                                       comment '杂志内容',
  magazine_pdf        varchar(255)    default ''                 comment 'PDF链接',
  status              char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)    default null               comment '备注',
  primary key (magazine_id)
) engine=innodb auto_increment=100 comment = '杂志管理表';

-- ----------------------------
-- 2、会员管理表
-- ----------------------------
drop table if exists portal_member;
create table portal_member (
  member_id           bigint(20)      not null auto_increment    comment '会员ID',
  member_name         varchar(50)     not null                   comment '会员姓名',
  member_type         char(1)         default '0'                comment '会员类型（0普通会员 1金卡会员）',
  phone               varchar(20)     default ''                 comment '联系电话',
  email               varchar(50)     default ''                 comment '邮箱',
  address             varchar(255)    default ''                 comment '地址',
  join_date           date                                       comment '加入日期',
  expire_date         date                                       comment '过期日期',
  status              char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)    default null               comment '备注',
  primary key (member_id)
) engine=innodb auto_increment=100 comment = '会员管理表';

-- ----------------------------
-- 3、菜单管理表
-- ----------------------------
drop table if exists portal_menu;
create table portal_menu (
  menu_id             bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name           varchar(50)     not null                   comment '菜单名称',
  parent_id           bigint(20)      default 0                  comment '父菜单ID',
  order_num           int(4)          default 0                  comment '显示顺序',
  url                 varchar(200)    default '#'                comment '请求地址',
  menu_type           char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible             char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  perms               varchar(100)    default null               comment '权限标识',
  icon                varchar(100)    default '#'                comment '菜单图标',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单管理表';

-- ----------------------------
-- 4、内容管理表
-- ----------------------------
drop table if exists portal_content;
create table portal_content (
  content_id          bigint(20)      not null auto_increment    comment '内容ID',
  title               varchar(255)    not null                   comment '标题',
  content             text                                       comment '内容',
  summary             varchar(500)    default ''                 comment '摘要',
  cover_image         varchar(255)    default ''                 comment '封面图片',
  category            varchar(50)     default ''                 comment '分类',
  author              varchar(50)     default ''                 comment '作者',
  view_count          int(11)         default 0                  comment '浏览次数',
  status              char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)    default null               comment '备注',
  media_type          varchar(20)     default ''                 comment '媒体类型：video, audio, pdf, text',
  media_url           varchar(255)    default ''                 comment '媒体文件URL',
  media_duration      varchar(20)     default ''                 comment '媒体时长',
  media_size          varchar(20)     default ''                 comment '媒体文件大小',
  primary key (content_id)
) engine=innodb auto_increment=3000 comment = '内容管理表';

-- ----------------------------
-- 初始化-菜单管理表数据
-- ----------------------------
insert into portal_menu values('1', '门户网站', '0', '1', '#',                'M', '0', '', 'fa fa-home',           'admin', sysdate(), '', null, '门户网站目录');
insert into portal_menu values('100',  '首页', '1', '1', '/index',          'C', '0', '', 'fa fa-home',          'admin', sysdate(), '', null, '首页菜单');
insert into portal_menu values('101',  '杂志天地', '1', '2', '/magazine',          'C', '0', '', 'fa fa-book',     'admin', sysdate(), '', null, '杂志天地菜单');
insert into portal_menu values('102',  '大学联盟', '1', '3', '/university',          'C', '0', '', 'fa fa-graduation-cap',         'admin', sysdate(), '', null, '大学联盟菜单');
insert into portal_menu values('103',  '会员中心', '1', '4', '/member',          'C', '0', '', 'fa fa-user',         'admin', sysdate(), '', null, '会员中心菜单');
insert into portal_menu values('104',  '银龄动态', '1', '5', '/news',          'C', '0', '', 'fa fa-newspaper-o',  'admin', sysdate(), '', null, '银龄动态菜单');
insert into portal_menu values('105',  '老干部大学协会', '1', '6', '/association',        'C', '0', '', 'fa fa-users',           'admin', sysdate(), '', null, '老干部大学协会菜单');
insert into portal_menu values('106',  '联系我们', '1', '7', '/contact',        'C', '0', '', 'fa fa-phone',        'admin', sysdate(), '', null, '联系我们菜单');
