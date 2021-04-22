SET FOREIGN_KEY_CHECKS = 0;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '主控面板', `menu_code` = 'blackboard', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 10.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '', `layui_icon` = 'layui-icon-theme', `antdv_router` = '/dashboard', `antdv_icon` = 'dashboard', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 20:52:34', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639301;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639301, `menu_pids` = '[-1],[1339550467939639301],', `menu_name` = '工作台', `menu_code` = 'board_platform', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 10.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/dashboard/workplace', `layui_icon` = 'layui-icon-rate-solid', `antdv_router` = '/dashboard/workplace', `antdv_icon` = 'shop', `antdv_component` = '/dashboard/workplace', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = 1339550467939639299, `update_time` = '2021-01-08 20:52:34', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639302;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639301, `menu_pids` = '[-1],[1339550467939639301],', `menu_name` = '分析页', `menu_code` = 'board_analyse', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 10.20, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/dashboard/analysis', `layui_icon` = NULL, `antdv_router` = '/dashboard/analysis', `antdv_icon` = 'rocket', `antdv_component` = '/dashboard/analysis', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 20:52:34', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639303;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '用户权限', `menu_code` = 'user_role', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '#', `layui_icon` = 'layui-icon-template-1', `antdv_router` = '/orginfo', `antdv_icon` = 'apartment', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:17:55', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639304;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '用户管理', `menu_code` = 'org_user', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/user', `layui_icon` = NULL, `antdv_router` = '/orginfo/user', `antdv_icon` = 'user', `antdv_component` = '/orginfo/user', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639305;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '职位管理', `menu_code` = 'org_position', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.20, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/position', `layui_icon` = '', `antdv_router` = '/orginfo/position', `antdv_icon` = 'solution', `antdv_component` = '/orginfo/position', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:27:51', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639307;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '应用管理', `menu_code` = 'auth_app', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.30, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/app', `layui_icon` = '', `antdv_router` = '/orginfo/application', `antdv_icon` = 'appstore', `antdv_component` = '/orginfo/application', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:28:13', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639309;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '菜单管理', `menu_code` = 'auth_menu', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.50, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/menu', `layui_icon` = '', `antdv_router` = '/orginfo/menu', `antdv_icon` = 'menu', `antdv_component` = '/orginfo/menu', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:29:05', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639310;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '角色管理', `menu_code` = 'auth_role', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.40, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/role', `layui_icon` = '', `antdv_router` = '/orginfo/role', `antdv_icon` = 'slack', `antdv_component` = '/orginfo/role', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:28:36', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639311;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639304, `menu_pids` = '[-1],[1339550467939639304],', `menu_name` = '资源管理', `menu_code` = 'auth_resource', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 20.60, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/resource', `layui_icon` = '', `antdv_router` = '/orginfo/resource', `antdv_icon` = 'bars', `antdv_component` = '/orginfo/resource', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-02-19 22:29:13', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639312;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '基础数据', `menu_code` = 'base', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 40.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '', `layui_icon` = 'layui-icon-component', `antdv_router` = '/base', `antdv_icon` = 'coffee', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 16:47:41', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639313;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639313, `menu_pids` = '[-1],[1339550467939639313],', `menu_name` = '系统配置', `menu_code` = 'base_sysconfig', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 40.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/config', `layui_icon` = NULL, `antdv_router` = '/base/sysconfig', `antdv_icon` = 'file-done', `antdv_component` = '/base/sysconfig', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639314;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639313, `menu_pids` = '[-1],[1339550467939639313],', `menu_name` = '字典管理', `menu_code` = 'base_dict', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 40.20, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/dictType', `layui_icon` = NULL, `antdv_router` = '/base/dict', `antdv_icon` = 'icon-default', `antdv_component` = '/base/dict', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639315;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639313, `menu_pids` = '[-1],[1339550467939639313],', `menu_name` = '接口文档', `menu_code` = 'base_apis', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 40.30, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/api', `layui_icon` = NULL, `antdv_router` = '/base/api', `antdv_icon` = 'api', `antdv_component` = '/base/api', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639316;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '系统功能', `menu_code` = 'sys', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '', `layui_icon` = 'layui-icon-set', `antdv_router` = '/system', `antdv_icon` = 'thunderbolt', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 16:47:49', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639317;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '文件管理', `menu_code` = 'sys_file', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/file', `layui_icon` = NULL, `antdv_router` = '/system/file', `antdv_icon` = 'tags', `antdv_component` = '/system/file', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639318;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '操作日志', `menu_code` = 'operate_log', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.20, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/log', `layui_icon` = NULL, `antdv_router` = '/system/logs', `antdv_icon` = 'global', `antdv_component` = '/system/logs', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639319;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '在线用户', `menu_code` = 'sys_online', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.30, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/onlineUser', `layui_icon` = NULL, `antdv_router` = '/system/online', `antdv_icon` = 'laptop', `antdv_component` = '/system/online', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639320;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '定时任务', `menu_code` = 'sys_timer', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.40, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/sysTimers', `layui_icon` = NULL, `antdv_router` = '/system/timer', `antdv_icon` = 'inbox', `antdv_component` = '/system/timer', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639321;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '通知管理', `menu_code` = 'notice', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 60.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '', `layui_icon` = 'layui-icon-tips', `antdv_router` = '/notice', `antdv_icon` = 'bell', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 16:47:56', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639322;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639322, `menu_pids` = '[-1],[1339550467939639322],', `menu_name` = '通知发布', `menu_code` = 'notice_update', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 60.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/notice', `layui_icon` = NULL, `antdv_router` = '/notice/noticePublish', `antdv_icon` = 'cloud', `antdv_component` = '/notice/noticePublish', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639323;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639322, `menu_pids` = '[-1],[1339550467939639322],', `menu_name` = '我的消息', `menu_code` = 'notice_find', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 60.20, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/message_list', `layui_icon` = NULL, `antdv_router` = '/notice/noticeLook', `antdv_icon` = 'sound', `antdv_component` = '/notice/noticeLook', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639324;

UPDATE `sys_menu` SET `menu_parent_id` = -1, `menu_pids` = '[-1],', `menu_name` = '监控管理', `menu_code` = 'monitor', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 70.00, `status_flag` = 1, `remark` = NULL, `layui_path` = '', `layui_icon` = 'layui-icon-console', `antdv_router` = '/monitor', `antdv_icon` = 'monitor', `antdv_component` = NULL, `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = '2021-01-08 16:48:52', `update_user` = 1339550467939639299 WHERE `menu_id` = 1339550467939639325;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639325, `menu_pids` = '[-1],[1339550467939639325],', `menu_name` = 'SQL监控', `menu_code` = 'monitor_druid', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 70.10, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/monitor/druid', `layui_icon` = NULL, `antdv_router` = '/monitor/druid', `antdv_icon` = 'database', `antdv_component` = '/monitor/druid', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639326;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639325, `menu_pids` = '[-1],[1339550467939639325],', `menu_name` = '服务器信息', `menu_code` = 'monitor_server', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 70.50, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/monitor/systemInfo', `layui_icon` = NULL, `antdv_router` = '/monitor/server', `antdv_icon` = 'desktop', `antdv_component` = '/monitor/server', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639330;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '登录日志', `menu_code` = 'login_log', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.21, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/loginLog', `layui_icon` = NULL, `antdv_router` = '/system/loginLog', `antdv_icon` = 'global', `antdv_component` = '/system/loginLog', `antdv_link_open_type` = NULL, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2020-12-29 19:51:14', `create_user` = NULL, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1339550467939639334;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '多数据源', `menu_code` = 'datasources', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.50, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/datasource', `layui_icon` = 'layui-icon-star-fill', `antdv_router` = '/system/datasources', `antdv_icon` = 'icon-default', `antdv_component` = '/system/datasources', `antdv_link_open_type` = 0, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2021-01-23 21:08:22', `create_user` = 1339550467939639299, `update_time` = NULL, `update_user` = NULL WHERE `menu_id` = 1352966403623342082;

UPDATE `sys_menu` SET `menu_parent_id` = 1339550467939639317, `menu_pids` = '[-1],[1339550467939639317],', `menu_name` = '多语言配置', `menu_code` = 'languages', `app_code` = 'system', `visible` = 'Y', `menu_sort` = 50.60, `status_flag` = 1, `remark` = NULL, `layui_path` = '/view/i18n', `layui_icon` = 'layui-icon-star-fill', `antdv_router` = '/system/languages', `antdv_icon` = 'icon-default', `antdv_component` = '/system/languages', `antdv_link_open_type` = 0, `antdv_link_url` = NULL, `antdv_uid_url` = NULL, `del_flag` = 'N', `create_time` = '2021-01-23 21:17:23', `create_user` = 1339550467939639299, `update_time` = '2021-01-25 21:59:08', `update_user` = 1339550467939639299 WHERE `menu_id` = 1352968673144459265;

SET FOREIGN_KEY_CHECKS = 1;