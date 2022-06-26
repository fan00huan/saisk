delete from `m_code` where 1=1 and `code_div` = 'item_type' and `key` = '001';
insert into `m_code` (`code_div`, `key`, `val`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item_type','001','衣類','0','2019/08/13','system','2019/08/13','system');
delete from `m_code` where 1=1 and `code_div` = 'item_type' and `key` = '002';
insert into `m_code` (`code_div`, `key`, `val`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item_type','002','食べ物','0','2019/08/13','system','2019/08/13','system');
delete from `m_code` where 1=1 and `code_div` = 'item_type' and `key` = '003';
insert into `m_code` (`code_div`, `key`, `val`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item_type','003','日用品','0','2019/08/13','system','2019/08/13','system');
delete from `m_code` where 1=1 and `code_div` = 'user_sex' and `key` = '1';
insert into `m_code` (`code_div`, `key`, `val`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('user_sex','1','男','0','2019/08/13','system','2019/08/13','system');
delete from `m_code` where 1=1 and `code_div` = 'user_sex' and `key` = '2';
insert into `m_code` (`code_div`, `key`, `val`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('user_sex','2','女','0','2019/08/13','system','2019/08/13','system');
delete from `t_item` where 1=1 and `item_id` = 'item001';
insert into `t_item` (`item_id`, `item_name`, `item_price`, `item_detail`, `item_pic`, `item_product_date`, `item_type`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item001','bag',200,'ブランド品質',null,'2015/06/21','003','0','2019/08/13','system','2019/08/13','system');
delete from `t_item` where 1=1 and `item_id` = 'item002';
insert into `t_item` (`item_id`, `item_name`, `item_price`, `item_detail`, `item_pic`, `item_product_date`, `item_type`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item002','mac pc',3000,'非常いい',null,'2017/08/13','003','0','2019/08/13','system','2019/08/13','system');
delete from `t_item` where 1=1 and `item_id` = 'item003';
insert into `t_item` (`item_id`, `item_name`, `item_price`, `item_detail`, `item_pic`, `item_product_date`, `item_type`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('item003','thinkpad x390',6000,'good!!','b5ca4321-4488-457c-8a40-ad68b5fdc5d3.jpg','2012/01/01','003','0','2019/08/13','system','2019/08/13','system');
delete from `t_order` where 1=1 and `orders_no` = '20190813_00000000001';
insert into `t_order` (`orders_no`, `user_id`, `createtime`, `note`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('20190813_00000000001','tanaka','2019/08/13 13:13:09.0',null,'0','2019/08/13','system','2019/08/13','system');
delete from `t_order_detail` where 1=1 and `orders_no` = '20190813_00000000001' and `sub_id` = '1  ';
insert into `t_order_detail` (`orders_no`, `sub_id`, `item_id`, `item_num`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('20190813_00000000001','1  ','item001',1,'0','2019/08/13','system','2019/08/13','system');
delete from `t_order_detail` where 1=1 and `orders_no` = '20190813_00000000001' and `sub_id` = '2  ';
insert into `t_order_detail` (`orders_no`, `sub_id`, `item_id`, `item_num`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('20190813_00000000001','2  ','item002',3,'0','2019/08/13','system','2019/08/13','system');
delete from `t_user` where 1=1 and `id` = 'tanaka';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('tanaka','田中',null,'2',null,'123456','08014345691','1@gmail.com','0','2019/08/13','system','2019/08/13','system');
delete from `t_user` where 1=1 and `id` = 'suzuki';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('suzuki','鈴木','2001/08/13','1','大連','123456','08082245608','2@gmail.com','0','2019/08/13','system','2019/08/13','system');
delete from `t_user` where 1=1 and `id` = 'sakawa';
insert into `t_user` (`id`, `username`, `birthday`, `sex`, `address`, `password`, `tel`, `mail`, `delete_flg`, `create_time`, `create_user_id`, `update_time`, `update_user_id`) values ('sakawa','佐川',null,'2','上海','123456',null,'3@gmail.com','0','2019/08/13','system','2019/08/13','system');
