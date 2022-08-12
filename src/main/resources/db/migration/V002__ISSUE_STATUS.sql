alter table if exists issues add column status varchar(255);
alter table if exists issues add column text varchar(255);
alter table if exists issues drop column status_id;
drop table if exists statuses;


