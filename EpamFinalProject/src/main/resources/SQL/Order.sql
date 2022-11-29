create table if not exists orders
(
    id      serial primary key,
    ship_id bigint not null references ships (id) on delete cascade,
    user_id bigint not null references users (id) on delete cascade,
    status_id bigint not null references status (id)
);
alter table orders
owner to "user";