drop table user_role;
create table user_role
(
    id   serial primary key,
    role varchar(255)
);
alter table user_role
    owner to "user";
insert into user_role(role)
values ('passenger');
insert into user_role(role)
values ('staff');

create table status
(
    id     serial primary key,
    status varchar(255)
);
alter table status
    owner to "user";
insert into status(status)
values ('pending');
insert into status(status)
values ('paid');

-- create table order_status(
--     order_id bigint,
--     status_id bigint,
--     constraint orders
--         foreign key (order_id)
--             REFERENCES orders (id),
--     constraint status
--         foreign key (status_id)
--             REFERENCES status (id)
-- );
-- alter table order_status
-- owner to "user";