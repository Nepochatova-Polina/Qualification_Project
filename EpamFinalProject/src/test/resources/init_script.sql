create table ships
(
    id                 serial primary key,
    name               varchar(255) unique,
    passenger_capacity integer
);

create table routes
(
    id           serial primary key,
    departure    varchar(255) not null,
    destination  varchar(255) not null,
    transit_time integer
);

create table cruises
(
    id          serial primary key,
    cruise_name varchar(400),
    ship_id     bigint not null references ships (id) unique,
    route_id    bigint not null references routes (id) unique,
    price       integer,
    start_date  date,
    end_date    date,
    deleted     bool default false,
    confirmed   bool default false
);

create table staff
(
    id         serial primary key,
    first_name varchar(255),
    last_name  varchar(255),
    ship_id    bigint references ships (id) on delete cascade
);

create table users
(
    id           serial primary key,
    first_name   varchar(255),
    last_name    varchar(255),
    login        varchar(255) unique,
    password     varchar(255),
    role         varchar(255),
    passport_img bytea
);

create table orders
(
    id        serial primary key,
    cruise_id bigint not null references cruises (id) on delete cascade,
    user_id   bigint not null references users (id) on delete cascade,
    status    varchar(255)
);
