create table if not exists cruises
(
    id           serial primary key,
    ship_id bigint not null references ships (id) on delete cascade,
    route_id bigint not null references routes (id) on delete cascade,
    start_date      date,
    end_date        date
);
alter table cruises
    owner to "user";
