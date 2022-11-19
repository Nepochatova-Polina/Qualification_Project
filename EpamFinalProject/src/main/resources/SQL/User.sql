-- drop table users;
create table users
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    login  varchar(255),
    password   varchar(255),
    role_id    bigint,
    constraint role
        foreign key (role_id)
            REFERENCES user_role (id)
);
alter table users
    owner to "user";