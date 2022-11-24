drop table staff;
create table staff
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    ship_id    bigint,
    constraint role
        foreign key (ship_id)
            REFERENCES ships (id)
);
alter table staff
    owner to "user";