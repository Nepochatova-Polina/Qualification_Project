create table if not exists users
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    login  varchar(255) unique,
    password   varchar(255),
    role_id    bigint references user_role(id) on delete cascade,
    passportImg   bytea
);
alter table users
    owner to "user";

