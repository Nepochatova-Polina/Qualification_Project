create table if not exists ships
(
    id              serial primary key,
    route_id           bigint not null references route(id) on delete cascade,
    number_of_ports integer,
    passenger_capacity integer,
    start_Date      date,
    end_Date        date
);
alter table ships
    owner to "user";
