# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table poi (
  id                        bigint not null,
  name                      varchar(255) not null,
  x                         integer,
  y                         integer,
  constraint pk_poi primary key (id))
;

create sequence poi_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists poi;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists poi_seq;

