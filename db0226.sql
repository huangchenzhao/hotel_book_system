/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022-02-26 19:40:47                          */
/*==============================================================*/


drop table if exists address;

drop table if exists detail;

drop table if exists hotel;

drop table if exists photo;

drop table if exists room;

drop table if exists user;

drop table if exists userorder;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   a_id                 bigint not null,
   country              varchar(30),
   province             varchar(30),
   city                 varchar(30),
   district             varchar(30),
   primary key (a_id)
);

/*==============================================================*/
/* Table: detail                                                */
/*==============================================================*/
create table detail
(
   r_date               date not null,
   r_id                 bigint not null,
   remain               int,
   primary key (r_date, r_id)
);

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
create table hotel
(
   h_id                 bigint not null,
   a_id                 bigint,
   longitude            double,
   latitude             double,
   star                 int,
   name                 varchar(30),
   primary key (h_id)
);

/*==============================================================*/
/* Table: photo                                                 */
/*==============================================================*/
create table photo
(
   p_id                 bigint not null,
   r_id                 bigint,
   h_id                 bigint,
   photo_url            varchar(30),
   primary key (p_id)
);

/*==============================================================*/
/* Table: room                                                  */
/*==============================================================*/
create table room
(
   r_id                 bigint not null,
   h_id                 bigint,
   price                float,
   roomtype             varchar(30),
   amount               int,
   max_people           int,
   primary key (r_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   u_id                 bigint not null,
   username             varchar(30),
   password             varchar(30),
   photo_url            varchar(30),
   mail                 varchar(30),
   usertype             int,
   primary key (u_id)
);

/*==============================================================*/
/* Table: userorder                                             */
/*==============================================================*/
create table userorder
(
   o_id                 bigint not null,
   r_id                 bigint,
   u_id                 bigint,
   h_id                 bigint,
   check_in             date,
   check_out            date,
   totalprice           float,
   quantity             int,
   state                int,
   comment              varchar(30),
   primary key (o_id)
);

alter table detail add constraint FK_room_detail foreign key (r_id)
      references room (r_id) on delete restrict on update restrict;

alter table hotel add constraint FK_address_hotel foreign key (a_id)
      references address (a_id) on delete restrict on update restrict;

alter table photo add constraint FK_hotel_photo foreign key (h_id)
      references hotel (h_id) on delete restrict on update restrict;

alter table photo add constraint FK_room_photo foreign key (r_id)
      references room (r_id) on delete restrict on update restrict;

alter table room add constraint FK_hotel_room foreign key (h_id)
      references hotel (h_id) on delete restrict on update restrict;

alter table userorder add constraint FK_order_hotel foreign key (h_id)
      references hotel (h_id) on delete restrict on update restrict;

alter table userorder add constraint FK_order_room foreign key (r_id)
      references room (r_id) on delete restrict on update restrict;

alter table userorder add constraint FK_user_order foreign key (u_id)
      references user (u_id) on delete restrict on update restrict;

