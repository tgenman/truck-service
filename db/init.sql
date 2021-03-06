DROP SCHEMA IF EXISTS `school` ;

-- -----------------------------------------------------
-- Schema school
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `school` DEFAULT CHARACTER SET utf8 ;

USE `school` ;

create table Cargo
(
  id      bigint auto_increment
    primary key,
  deleted bit          null,
  name    varchar(255) null,
  type    varchar(255) null,
  weight  bigint       null,
  dropoff bigint       null
)
  engine = MyISAM;

create index FKgukpgv13nlmmidf7h4xoojpjl
on Cargo (dropoff);

create table City
(
  id         bigint auto_increment
    primary key,
  name       varchar(255) null,
  latitude   double       null,
  longtitude double       null
)
  engine = MyISAM;

create table Customer
(
  id      bigint auto_increment
    primary key,
  deleted bit          null,
  name    varchar(255) null
)
  engine = MyISAM;

create table Driver
(
  id         bigint auto_increment
    primary key,
  deleted    bit          null,
  first_name varchar(255) null,
  last_name  varchar(255) null,
  status     varchar(255) null,
  city_id    bigint       null,
  order_id   bigint       null,
  shift_id   bigint       null,
  truck_id   bigint       null,
  user_id    bigint       null
)
  engine = MyISAM;

create index FKcumthmpaotq826kc5gb3xqew
on Driver (order_id);

create index FKo39gmipbyte8wia1lr65gyc01
on Driver (city_id);

create index FKo8k16oadbw2i7f8u8rc0h0y20
on Driver (truck_id);

create index FKq2m6sy3r87b1p7m738ceuwx8l
on Driver (shift_id);

create index FKtiysvbaikv3lcytovlsop4c51
on Driver (user_id);

create table Manager
(
  id         bigint auto_increment
    primary key,
  deleted    bit          null,
  first_name varchar(255) null,
  last_name  varchar(255) null,
  user_id    bigint       null
)
  engine = MyISAM;

create index FKenlf34bodpcncbsu7hhi9wkmy
on Manager (user_id);

create table Order_
(
  id           bigint auto_increment
    primary key,
  timestamp    datetime     null,
  deleted      bit          null,
  type         varchar(255) null,
  customer_id  bigint       null,
  tempshift_id bigint       null,
  truck_id     bigint       null
)
  engine = MyISAM;

create index FK3apvl4i2kxxe6vrxkadesq4ox
on Order_ (customer_id);

create index FK3wr0w1ewueom1csee0y9ijv83
on Order_ (tempshift_id);

create index FKitl5tvevny4xj5b30172srmcf
on Order_ (truck_id);

create table Road
(
  id       bigint auto_increment
    primary key,
  distance bigint null,
  `from`   bigint null,
  `to`     bigint null
)
  engine = MyISAM;

create index FK2y96c5iv054jlq09nexp92itk
on Road (`to`);

create index FK3i3xmv4ybpx7m07tydvqusnn6
on Road (`from`);

create table RoutePoint
(
  id                   bigint auto_increment
    primary key,
  completed            bit          null,
  deleted              bit          null,
  route_sequence_index int          null,
  type                 varchar(255) null,
  cargo_id             bigint       null,
  city_id              bigint       null,
  order_id             bigint       null
)
  engine = MyISAM;

create index FKha4jq9wtwq92xrqw83gy859ue
on RoutePoint (order_id);

create index FKi1kk0w96x7yk3u3stvllx5w38
on RoutePoint (cargo_id);

create index FKiqne9awxa7vkvww39gv7secav
on RoutePoint (city_id);

create table Shift
(
  id                   bigint auto_increment
    primary key,
  month_end_at         datetime null,
  month_start_at       datetime null,
  time_monthly_elapsed bigint   null,
  time_weekly_elapsed  int      null,
  week_counter         int      null,
  weekly_rest          bit      null
)
  engine = MyISAM;

create table TempShift
(
  id               bigint auto_increment
    primary key,
  start_date       datetime null,
  start_temp_shift bit      null
)
  engine = MyISAM;

create table Truck
(
  id              bigint auto_increment
    primary key,
  brand           varchar(255) null,
  capacity        int          null,
  deleted         bit          null,
  free            bit          null,
  license_plate   varchar(255) null,
  max_drivers     int          null,
  model           varchar(255) null,
  type            varchar(255) null,
  working_session int          null,
  city_id         bigint       null
)
  engine = MyISAM;

create index FK7uu7ppufup2w86lldd065sfdq
on Truck (city_id);

create table User
(
  id        bigint auto_increment
    primary key,
  companyId varchar(255) null,
  password  varchar(255) null,
  role      varchar(255) null
)
  engine = MyISAM;

INSERT INTO school.City (name, latitude, longtitude) VALUES ('Reading', 51.457922, -0.973588);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('London', 51.509647, -0.132626);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Swindon', 51.560422, -1.77397);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Oxford', 51.741523, -1.242055);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Albans', 51.746969, -0.345176);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Luton', 51.890297, -0.41485);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Harlow', 51.775067, 0.110803);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Chelmsford', 51.752861, 0.494093);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Colchester', 51.873798, 0.917063);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Ipswich', 52.042064, 1.189703);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Bedford', 52.122892, -0.460695);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Northampton', 52.239545, -0.874447);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Kettering', 52.384066, -0.721865);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Birmingham', 52.47095, -1.866408);
INSERT INTO school.City (name, latitude, longtitude) VALUES ('Cheltenham', 51.885259, -2.052739);

INSERT INTO school.User (companyId, password, role) VALUES ('admin', '$2a$10$mT840tt0k2QN5tqvcgu4e.u1gD1FE2hKErrMgdxIzSwXU9LgtwXmy', 'ROLE_ADMIN');
INSERT INTO school.User (companyId, password, role) VALUES ('maxdriver', '$2a$10$QalZQy/inGJMox6FKU4ZneSU0.LLvBCYlQ5/P8JFWY7VtGAPlAEPS', 'ROLE_DRIVER');
INSERT INTO school.User (companyId, password, role) VALUES ('nickolasph', '$2a$10$7UVmEqVcAZ9kptND5ViOv.YtASg9oq5s.qshpTdtj95jc0e8Y7BC2', 'ROLE_DRIVER');
INSERT INTO school.User (companyId, password, role) VALUES ('harryold', '$2a$10$PW9SsebdqjMGbG..It/4heGOf.rCH5Em/.iXQqhLRcErKaEljxFm6', 'ROLE_DRIVER');
INSERT INTO school.User (companyId, password, role) VALUES ('nilson', '$2a$10$c0pSZgUCnkYSfDZA3MfjjeweSAgPP0tcQukpl54nZukXBR9OFdfha', 'ROLE_DRIVER');
INSERT INTO school.User (companyId, password, role) VALUES ('erickcrau', '$2a$10$3r1HKCn9BHgy/CD9/FBCa.ETaMD.EwsMjMXTNTi68223ah7doYFqG', 'ROLE_DRIVER');

INSERT INTO school.Road (distance, `from`, `to`) VALUES (41, 1, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (40, 2, 3);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (41, 2, 1);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (40, 3, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (61, 2, 4);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (61, 4, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (25, 2, 5);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (25, 5, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (12, 5, 6);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (12, 6, 5);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (28, 2, 7);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (28, 7, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (42, 2, 8);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (42, 8, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (27, 8, 9);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (27, 9, 8);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (17, 9, 10);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (17, 10, 9);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (18, 6, 11);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (18, 11, 6);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (23, 11, 12);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (23, 12, 11);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (18, 12, 13);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (18, 13, 12);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (58, 12, 14);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (58, 14, 12);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (44, 4, 15);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (44, 15, 4);

INSERT INTO school.Shift (month_end_at, month_start_at, time_monthly_elapsed, time_weekly_elapsed, week_counter, weekly_rest) VALUES ('2018-12-31 23:59:59', '2018-12-07 15:21:08', 0, 0, 1, false);
INSERT INTO school.Shift (month_end_at, month_start_at, time_monthly_elapsed, time_weekly_elapsed, week_counter, weekly_rest) VALUES ('2018-12-31 23:59:59', '2018-12-07 17:47:10', 0, 0, 1, false);
INSERT INTO school.Shift (month_end_at, month_start_at, time_monthly_elapsed, time_weekly_elapsed, week_counter, weekly_rest) VALUES ('2018-12-31 23:59:59', '2018-12-07 22:34:58', 0, 0, 1, false);
INSERT INTO school.Shift (month_end_at, month_start_at, time_monthly_elapsed, time_weekly_elapsed, week_counter, weekly_rest) VALUES ('2018-12-31 23:59:59', '2018-12-07 22:35:31', 0, 0, 1, false);
INSERT INTO school.Shift (month_end_at, month_start_at, time_monthly_elapsed, time_weekly_elapsed, week_counter, weekly_rest) VALUES ('2018-12-31 23:59:59', '2018-12-07 22:36:28', 0, 0, 1, false);

INSERT INTO school.Driver (deleted, first_name, last_name, status, city_id, order_id, shift_id, truck_id, user_id) VALUES (false, 'Max', 'Max', 'FREE', 2, null, 1, null, 2);
INSERT INTO school.Driver (deleted, first_name, last_name, status, city_id, order_id, shift_id, truck_id, user_id) VALUES (false, 'Nickolas', 'Phil', 'FREE', 2, null, 2, null, 3);
INSERT INTO school.Driver (deleted, first_name, last_name, status, city_id, order_id, shift_id, truck_id, user_id) VALUES (false, 'Harry', 'Oldman', 'FREE', 3, null, 3, null, 4);
INSERT INTO school.Driver (deleted, first_name, last_name, status, city_id, order_id, shift_id, truck_id, user_id) VALUES (false, 'Nilson', 'Mendes', 'FREE', 2, null, 4, null, 5);
INSERT INTO school.Driver (deleted, first_name, last_name, status, city_id, order_id, shift_id, truck_id, user_id) VALUES (false, 'Erick', 'Crause', 'FREE', 1, null, 5, null, 6);

INSERT INTO school.Truck (brand, capacity, deleted, free, license_plate, max_drivers, model, type, working_session, city_id) VALUES ('Mersedes', 30, false, true, 'JS89221', 2, 'Benz', 'OPERABLE', 150, 1);
INSERT INTO school.Truck (brand, capacity, deleted, free, license_plate, max_drivers, model, type, working_session, city_id) VALUES ('Scania', 20, false, true, 'UB88291', 2, 'R580', 'OPERABLE', 160, 1);
INSERT INTO school.Truck (brand, capacity, deleted, free, license_plate, max_drivers, model, type, working_session, city_id) VALUES ('DAF', 30, false, true, 'TP82943', 2, 'XF105', 'OPERABLE', 200, 1);
INSERT INTO school.Truck (brand, capacity, deleted, free, license_plate, max_drivers, model, type, working_session, city_id) VALUES ('IVECO', 20, false, true, 'DE22131', 2, '75E15', 'OPERABLE', 150, 1);

