create table if not exists user_role
(
    id   serial primary key,
    role varchar(255)
);
alter table user_role
    owner to "user";
insert into user_role(role)
values ('passenger');
insert into user_role(role)
values ('administrator');

create table if not exists status
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

