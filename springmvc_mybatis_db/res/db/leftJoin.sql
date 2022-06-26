drop table `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(20) NOT NULL COMMENT 'ユーザid',
  `username` varchar(32) NOT NULL COMMENT 'ユーザ名（表示用）',
  `birthday` datetime DEFAULT NULL COMMENT '誕生日',
  `sex` char(1) DEFAULT NULL COMMENT '性別',
  `address` varchar(256) DEFAULT NULL COMMENT '住所',
  `password` varchar(32) NOT NULL COMMENT 'パスワード',
  `tel` varchar(20) DEFAULT NULL COMMENT '携帯',
  `mail` varchar(100) DEFAULT NULL COMMENT 'メール',
  `dept_id` varchar(100) DEFAULT NULL COMMENT '部門ID',
  `delete_flg` char(1) NOT NULL COMMENT '削除フラグ',
  `create_time` datetime DEFAULT NULL COMMENT '登録日時',
  `create_user_id` varchar(20) DEFAULT NULL COMMENT '登録者',
  `update_time` datetime DEFAULT NULL COMMENT '更新日時',
  `update_user_id` varchar(20) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ユーザ'



CREATE TABLE `t_dept` (
  `dept_id` varchar(20) NOT NULL COMMENT '部門ID',
  `dept_name` varchar(20) NOT NULL COMMENT '部門名称',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部門';


delete from `t_user` where 1=1 and `id` = 'tanaka';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('tanaka','田中',null,'2',null,'123456','08014345691','1@gmail.com','0','2019/08/13','system','2019/08/13','system');
delete from `t_user` where 1=1 and `id` = 'suzuki';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('suzuki','鈴木','2001/08/13','1','大連','123456','08082245608','2@gmail.com','0','2019/08/13','system','2019/08/13','system');
delete from `t_user` where 1=1 and `id` = 'sakawa';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('sakawa','佐川',null,'2','上海','123456',null,'3@gmail.com','0','2019/08/13','system','2019/08/13','system');



///////////////////

//内联接
select
  u.username
  , d.dept_name
from
  t_user u
  , t_dept d
where
u.dept_id = d.dept_id

//左联接
select
  u.username
  , d.dept_name
from
  t_user u
  left outer join t_dept d
    on u.dept_id = d.dept_id and


// 如果加WHERE条件的话，左联接的检索条件要写到ON后面

// 左联接正确写法
select
  u.username
  , d.dept_name
from
  t_user u
  left outer join t_dept d
    on u.dept_id = d.dept_id and dept_name == 'xxx'

// 左联接错误写法
select
  u.username
  , d.dept_name
from
  t_user u
  left outer join t_dept d
    on u.dept_id = d.dept_id
where
dept_name == 'xxx'
