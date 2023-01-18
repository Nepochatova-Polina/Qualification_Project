create table if not exists cruises
(
    id          serial primary key,
    cruise_name varchar(400),
    ship_id     bigint not null references ships (id) unique,
    route_id    bigint not null references routes (id) unique,
    price       integer,
    start_date  date,
    end_date    date,
    deleted     bool default false
);
alter table cruises
    owner to "user";


