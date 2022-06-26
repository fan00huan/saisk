
create table "m_code" (
"key" VARCHAR(3) not null ,
"code_div" VARCHAR(20) not null ,
"val" VARCHAR(256) null ,
"delete_flg" VARCHAR(1) not null ,
"create_time" DATE null ,
"create_user_id" VARCHAR(20) null ,
"update_time" DATE null ,
"update_user_id" VARCHAR(20) null ,
CONSTRAINT "m_code_pkey" primary key ("key","code_div"));
comment on table "m_code" is 'マスタ';
comment on column m_code."key" is 'キー';
comment on column m_code."code_div" is 'コード区分';
comment on column m_code."val" is '値';
comment on column m_code."delete_flg" is '削除フラグ';
comment on column m_code."create_time" is '登録日時';
comment on column m_code."create_user_id" is '登録者';
comment on column m_code."update_time" is '更新日時';
comment on column m_code."update_user_id" is '更新者';

create table "t_item" (
"item_id" VARCHAR(20) not null ,
"item_name" VARCHAR(32) not null ,
"item_price" NUMERIC(10,1) not null ,
"item_detail" VARCHAR(200) null ,
"item_pic" VARCHAR(64) null ,
"item_product_date" DATE not null ,
"item_type" VARCHAR(3) null ,
"delete_flg" VARCHAR(1) not null ,
"create_time" DATE null ,
"create_user_id" VARCHAR(20) null ,
"update_time" DATE null ,
"update_user_id" VARCHAR(20) null ,
CONSTRAINT "t_item_pkey" primary key ("item_id"));
comment on table "t_item" is '商品';
comment on column t_item."item_id" is '商品id';
comment on column t_item."item_name" is '商品名';
comment on column t_item."item_price" is '商品価格';
comment on column t_item."item_detail" is '商品紹介';
comment on column t_item."item_pic" is '商品画像格納パス';
comment on column t_item."item_product_date" is '生産日付';
comment on column t_item."item_type" is '商品種別';
comment on column t_item."delete_flg" is '削除フラグ';
comment on column t_item."create_time" is '登録日時';
comment on column t_item."create_user_id" is '登録者';
comment on column t_item."update_time" is '更新日時';
comment on column t_item."update_user_id" is '更新者';

create table "t_order" (
"orders_no" VARCHAR(20) not null ,
"user_id" VARCHAR(20) not null ,
"createtime" DATE not null ,
"note" VARCHAR(100) null ,
"delete_flg" VARCHAR(1) not null ,
"create_time" DATE null ,
"create_user_id" VARCHAR(20) null ,
"update_time" DATE null ,
"update_user_id" VARCHAR(20) null ,
CONSTRAINT "t_order_pkey" primary key ("orders_no"));
comment on table "t_order" is '注文';
comment on column t_order."orders_no" is '注文番号';
comment on column t_order."user_id" is 'ユーザid';
comment on column t_order."createtime" is '注文作成日時';
comment on column t_order."note" is 'コメント';
comment on column t_order."delete_flg" is '削除フラグ';
comment on column t_order."create_time" is '登録日時';
comment on column t_order."create_user_id" is '登録者';
comment on column t_order."update_time" is '更新日時';
comment on column t_order."update_user_id" is '更新者';

create table "t_order_detail" (
"orders_no" VARCHAR(20) not null ,
"orders_sub_no" VARCHAR(3) not null ,
"item_id" VARCHAR(20) not null ,
"item_num" NUMERIC(11,0) null ,
"delete_flg" VARCHAR(1) not null ,
"create_time" DATE null ,
"create_user_id" VARCHAR(20) null ,
"update_time" DATE null ,
"update_user_id" VARCHAR(20) null ,
CONSTRAINT "t_order_detail_pkey" primary key ("orders_no","orders_sub_no"));
comment on table "t_order_detail" is '子注文';
comment on column t_order_detail."orders_no" is '注文番号';
comment on column t_order_detail."orders_sub_no" is '子注文子id';
comment on column t_order_detail."item_id" is '商品id';
comment on column t_order_detail."item_num" is '商品購入数量';
comment on column t_order_detail."delete_flg" is '削除フラグ';
comment on column t_order_detail."create_time" is '登録日時';
comment on column t_order_detail."create_user_id" is '登録者';
comment on column t_order_detail."update_time" is '更新日時';
comment on column t_order_detail."update_user_id" is '更新者';

create table "t_user" (
"user_id" VARCHAR(20) not null ,
"user_name" VARCHAR(32) not null ,
"birthday" DATE null ,
"sex" VARCHAR(1) null ,
"address" VARCHAR(256) null ,
"password" VARCHAR(32) not null ,
"tel" VARCHAR(20) null ,
"mail" VARCHAR(100) null ,
"delete_flg" VARCHAR(1) not null ,
"create_time" DATE null ,
"create_user_id" VARCHAR(20) null ,
"update_time" DATE null ,
"update_user_id" VARCHAR(20) null ,
CONSTRAINT "t_user_pkey" primary key ("user_id"));
comment on table "t_user" is 'ユーザ';
comment on column t_user."user_id" is 'ユーザid';
comment on column t_user."user_name" is 'ユーザ名（表示用）';
comment on column t_user."birthday" is '誕生日';
comment on column t_user."sex" is '性別';
comment on column t_user."address" is '住所';
comment on column t_user."password" is 'パスワード';
comment on column t_user."tel" is '携帯';
comment on column t_user."mail" is 'メール';
comment on column t_user."delete_flg" is '削除フラグ';
comment on column t_user."create_time" is '登録日時';
comment on column t_user."create_user_id" is '登録者';
comment on column t_user."update_time" is '更新日時';
comment on column t_user."update_user_id" is '更新者';
