drop table orders;
create table orders
(
    id      serial primary key,
    ship_id bigint not null ,
    user_id bigint not null ,
    status_id bigint not null ,
    constraint ships
        foreign key (ship_id)
            REFERENCES ships (id),
    constraint users foreign key (user_id)
        REFERENCES users (id),
    constraint status
        foreign key (status_id)
            REFERENCES status (id)

);
alter table orders
owner to "user";