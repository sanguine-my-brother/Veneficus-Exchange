# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table expiration_settings (
  id                            bigint auto_increment not null,
  days                          integer not null,
  minutes                       integer not null,
  monday                        tinyint(1) default 0 not null,
  tuesday                       tinyint(1) default 0 not null,
  wednesday                     tinyint(1) default 0 not null,
  thursday                      tinyint(1) default 0 not null,
  friday                        tinyint(1) default 0 not null,
  saturday                      tinyint(1) default 0 not null,
  sunday                        tinyint(1) default 0 not null,
  cached                        datetime(6),
  constraint pk_expiration_settings primary key (id)
);

create table game (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  game_type                     integer,
  constraint ck_game_game_type check ( game_type in (0,1)),
  constraint pk_game primary key (id)
);

create table player (
  id                            bigint auto_increment not null,
  turn_order                    integer not null,
  constraint pk_player primary key (id)
);

create table turn (
  id                            bigint auto_increment not null,
  turn_number                   integer not null,
  started                       datetime(6),
  expires                       datetime(6),
  previous_player_skipped       tinyint(1) default 0 not null,
  first_turn                    tinyint(1) default 0 not null,
  save_file_path                varchar(255),
  constraint pk_turn primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  steamid                       bigint not null,
  player_name                   varchar(255),
  avatar_url                    varchar(255),
  client_code                   varchar(255),
  email                         varchar(255),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists expiration_settings;

drop table if exists game;

drop table if exists player;

drop table if exists turn;

drop table if exists user;

