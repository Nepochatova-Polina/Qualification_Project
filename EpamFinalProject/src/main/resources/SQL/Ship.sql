-- drop table users cascade ;
drop table ships cascade ;

create table ships
(
    id              serial primary key,
    route_id           bigint not null,
    constraint route
        foreign key (route_id)
            REFERENCES route (id),
    number_of_ports integer,
    passenger_capacity integer,
    start_Date      date,
    end_Date        date
);
alter table ships
    owner to "user";
