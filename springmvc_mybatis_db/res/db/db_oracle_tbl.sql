create table "M_CODE" (
"CODE_DIV" VARCHAR2(20) not null ,
"KEY" VARCHAR2(3) not null ,
"VAL" VARCHAR2(256) null ,
"DELETE_FLG" CHAR(1) not null ,
"CREATE_TIME" DATE null ,
"CREATE_USER_ID" VARCHAR2(20) null ,
"UPDATE_TIME" DATE null ,
"UPDATE_USER_ID" VARCHAR2(20) null );
comment on table "M_CODE" is 'マスタ';
comment on column "M_CODE"."CODE_DIV" is 'コード区分';
comment on column "M_CODE"."KEY" is 'キー';
comment on column "M_CODE"."VAL" is '値';
comment on column "M_CODE"."DELETE_FLG" is '削除フラグ';
comment on column "M_CODE"."CREATE_TIME" is '登録日時';
comment on column "M_CODE"."CREATE_USER_ID" is '登録者';
comment on column "M_CODE"."UPDATE_TIME" is '更新日時';
comment on column "M_CODE"."UPDATE_USER_ID" is '更新者';
alter table "M_CODE" add constraint pk_M_CODE primary key ("CODE_DIV","KEY");
create table "T_ITEM" (
"ITEM_ID" VARCHAR2(20) not null ,
"ITEM_NAME" VARCHAR2(32) not null ,
"ITEM_PRICE" NUMBER(10,1) not null ,
"ITEM_DETAIL" VARCHAR2(200) null ,
"ITEM_PIC" VARCHAR2(64) null ,
"ITEM_PRODUCT_DATE" DATE not null ,
"ITEM_TYPE" CHAR(3) null ,
"DELETE_FLG" CHAR(1) not null ,
"CREATE_TIME" DATE null ,
"CREATE_USER_ID" VARCHAR2(20) null ,
"UPDATE_TIME" DATE null ,
"UPDATE_USER_ID" VARCHAR2(20) null );
comment on table "T_ITEM" is '商品';
comment on column "T_ITEM"."ITEM_ID" is '商品ID';
comment on column "T_ITEM"."ITEM_NAME" is '商品名';
comment on column "T_ITEM"."ITEM_PRICE" is '商品価格';
comment on column "T_ITEM"."ITEM_DETAIL" is '商品紹介';
comment on column "T_ITEM"."ITEM_PIC" is '商品画像格納パス';
comment on column "T_ITEM"."ITEM_PRODUCT_DATE" is '生産日付';
comment on column "T_ITEM"."ITEM_TYPE" is '商品種別';
comment on column "T_ITEM"."DELETE_FLG" is '削除フラグ';
comment on column "T_ITEM"."CREATE_TIME" is '登録日時';
comment on column "T_ITEM"."CREATE_USER_ID" is '登録者';
comment on column "T_ITEM"."UPDATE_TIME" is '更新日時';
comment on column "T_ITEM"."UPDATE_USER_ID" is '更新者';
alter table "T_ITEM" add constraint pk_T_ITEM primary key ("ITEM_ID");
create table "T_ORDER_DETAIL" (
"ORDERS_NO" CHAR(20) not null ,
"SUB_ID" CHAR(3) not null ,
"ITEM_ID" VARCHAR2(20) not null ,
"ITEM_NUM" NUMBER(11) null ,
"DELETE_FLG" CHAR(1) not null ,
"CREATE_TIME" DATE null ,
"CREATE_USER_ID" VARCHAR2(20) null ,
"UPDATE_TIME" DATE null ,
"UPDATE_USER_ID" VARCHAR2(20) null );
comment on table "T_ORDER_DETAIL" is '子注文';
comment on column "T_ORDER_DETAIL"."ORDERS_NO" is '注文番号';
comment on column "T_ORDER_DETAIL"."SUB_ID" is '子注文子ID';
comment on column "T_ORDER_DETAIL"."ITEM_ID" is '商品ID';
comment on column "T_ORDER_DETAIL"."ITEM_NUM" is '商品購入数量';
comment on column "T_ORDER_DETAIL"."DELETE_FLG" is '削除フラグ';
comment on column "T_ORDER_DETAIL"."CREATE_TIME" is '登録日時';
comment on column "T_ORDER_DETAIL"."CREATE_USER_ID" is '登録者';
comment on column "T_ORDER_DETAIL"."UPDATE_TIME" is '更新日時';
comment on column "T_ORDER_DETAIL"."UPDATE_USER_ID" is '更新者';
alter table "T_ORDER_DETAIL" add constraint pk_T_ORDER_DETAIL primary key ("ORDERS_NO","SUB_ID");
create table "T_ORDER" (
"ORDERS_NO" CHAR(20) not null ,
"USER_ID" VARCHAR2(20) not null ,
"CREATETIME" DATE not null ,
"NOTE" VARCHAR2(100) null ,
"DELETE_FLG" CHAR(1) not null ,
"CREATE_TIME" DATE null ,
"CREATE_USER_ID" VARCHAR2(20) null ,
"UPDATE_TIME" DATE null ,
"UPDATE_USER_ID" VARCHAR2(20) null );
comment on table "T_ORDER" is '注文';
comment on column "T_ORDER"."ORDERS_NO" is '注文番号';
comment on column "T_ORDER"."USER_ID" is 'ユーザID';
comment on column "T_ORDER"."CREATETIME" is '注文作成日時';
comment on column "T_ORDER"."NOTE" is 'コメント';
comment on column "T_ORDER"."DELETE_FLG" is '削除フラグ';
comment on column "T_ORDER"."CREATE_TIME" is '登録日時';
comment on column "T_ORDER"."CREATE_USER_ID" is '登録者';
comment on column "T_ORDER"."UPDATE_TIME" is '更新日時';
comment on column "T_ORDER"."UPDATE_USER_ID" is '更新者';
alter table "T_ORDER" add constraint pk_T_ORDER primary key ("ORDERS_NO");
create table "T_USER" (
"ID" VARCHAR2(20) not null ,
"USERNAME" VARCHAR2(32) not null ,
"BIRTHDAY" DATE null ,
"SEX" CHAR(1) null ,
"ADDRESS" VARCHAR2(256) null ,
"PASSWORD" VARCHAR2(32) not null ,
"TEL" VARCHAR2(20) null ,
"MAIL" VARCHAR2(100) null ,
"DELETE_FLG" CHAR(1) not null ,
"CREATE_TIME" DATE null ,
"CREATE_USER_ID" VARCHAR2(20) null ,
"UPDATE_TIME" DATE null ,
"UPDATE_USER_ID" VARCHAR2(20) null );
comment on table "T_USER" is 'ユーザ';
comment on column "T_USER"."ID" is 'ユーザID';
comment on column "T_USER"."USERNAME" is 'ユーザ名（表示用）';
comment on column "T_USER"."BIRTHDAY" is '誕生日';
comment on column "T_USER"."SEX" is '性別';
comment on column "T_USER"."ADDRESS" is '住所';
comment on column "T_USER"."PASSWORD" is 'パスワード';
comment on column "T_USER"."TEL" is '携帯';
comment on column "T_USER"."MAIL" is 'メール';
comment on column "T_USER"."DELETE_FLG" is '削除フラグ';
comment on column "T_USER"."CREATE_TIME" is '登録日時';
comment on column "T_USER"."CREATE_USER_ID" is '登録者';
comment on column "T_USER"."UPDATE_TIME" is '更新日時';
comment on column "T_USER"."UPDATE_USER_ID" is '更新者';
alter table "T_USER" add constraint pk_T_USER primary key ("ID");
