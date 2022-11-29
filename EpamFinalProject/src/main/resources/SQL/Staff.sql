create table if not exists staff
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    ship_id    bigint references ships (id) on delete cascade
);
alter table staff
    owner to "user";