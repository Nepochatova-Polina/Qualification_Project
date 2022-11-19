create table route
(
    id           serial primary key,
    depature     varchar(255),
    destination  varchar(255),
    distance     integer,
    transit_time integer
);
alter table route
    owner to "user";
