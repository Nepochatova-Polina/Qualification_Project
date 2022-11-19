drop table orders;
create table orders
(
    id      serial primary key,
    ship_id bigint,
    user_id bigint,
    constraint ships
        foreign key (ship_id)
            REFERENCES ships (id),
    constraint users foreign key (user_id)
        REFERENCES users (id)

);
alter table orders
owner to "user";