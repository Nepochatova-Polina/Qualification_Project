drop table users;
drop table ships;

create table ships
(
    id              serial primary key,
    route_id           bigint,
    constraint route
        foreign key (route_id)
            REFERENCES route (id),
    number_Of_Ports integer,
    start_Date      date,
    end_Date        date
);
alter table ships
    owner to "user";
