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

INSERT INTO school.User (companyId, password, role) VALUES ('admin', 'admin', 'ROLE_ADMIN');

INSERT INTO school.Road (distance, `from`, `to`) VALUES (41, 1, 2);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (40, 2, 3);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (41, 2, 1);
INSERT INTO school.Road (distance, `from`, `to`) VALUES (40, 3, 2);



