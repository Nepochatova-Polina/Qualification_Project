create table route
(
    id           serial primary key,
    departure     varchar(255) not null ,
    destination  varchar(255) not null ,
    distance     integer,
    transit_time integer
);
alter table route
    owner to "user";
