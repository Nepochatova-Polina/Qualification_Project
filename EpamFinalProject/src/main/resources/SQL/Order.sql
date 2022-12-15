create table if not exists orders
(
    id      serial primary key,
    ship_id bigint not null references ships (id) on delete cascade,
    user_id bigint not null references users (id) on delete cascade,
    status varchar(255)
);
alter table orders
owner to "user";