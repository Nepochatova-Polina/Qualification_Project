create table if not exists user_roles
(
    id   serial primary key,
    role varchar(150)
);

create table if not exists course_status
(
    id     serial primary key,
    status varchar(50) not null unique

);
create table if not exists lesson_status
(
    id     serial primary key,
    status varchar(50) not null unique
);
create table if not exists course_level
(
    id    serial primary key,
    level varchar(50) not null unique
);
create table if not exists users
(
    id                  serial primary key,
    firstname           varchar(45)  not null,
    lastname            varchar(50)  not null,
    email               varchar(200) not null,
    password            varchar(200) not null,
    role_id             integer      not null,
    foreign key (role_id) references user_roles (id),
    create_date         varchar(45),
    last_modify_date    varchar(45),
    create_user_id      integer,
    last_modify_user_id integer,
    deleted             bool default false
);
create table if not exists courses
(
    id            serial primary key,
    name          varchar(200)       not null,
    annotation    varchar(500),
    course_status integer            not null,
    foreign key (course_status) references course_status (id),
    professor_id  integer            not null,
    foreign key (professor_id) references users (id),
    course_level  integer            not null,
    foreign key (course_level) references course_level (id)
);
create table if not exists groups
(
    id         serial primary key,
    group_name varchar(50) not null,
    user_id    integer     not null,
    foreign key (user_id) references users (id),
    course_id  integer     not null,
    foreign key (course_id) references courses (id)
);
create table if not exists lessons
(
    id          serial primary key,
    course_id   integer      not null,
    foreign key (course_id) references courses (id),
    description varchar(500) not null

);
create table if not exists materials
(
    id        serial primary key,
    lesson_id integer not null,
    foreign key (lesson_id) references lessons (id),
    material  bytea
);
create table if not exists tests
(
    id        serial primary key,
    test_name      varchar(500) not null,
    course_id integer      not null,
    foreign key (course_id) references courses (id)
);
create table if not exists questions
(
    id       serial primary key,
    question varchar(500) not null,
    test_id  integer      not null,
    foreign key (test_id) references tests (id)
);
create table if not exists answers
(
    id          serial primary key,
    answer      varchar(500) not null,
    correct     bool default false,
    question_id integer      not null,
    foreign key (question_id) references questions (id)
);
create table if not exists user_grades
(
    id      serial primary key,
    user_id integer not null,
    foreign key (user_id) references users (id),
    test_id integer not null,
    foreign key (test_id) references tests (id),
    grade   integer not null
);
create table if not exists user_lesson
(
    user_id       integer not null,
    foreign key (user_id) references users (id),
    lesson_id     integer not null,
    foreign key (lesson_id) references lessons (id),
    lesson_status integer not null,
    foreign key (lesson_status) references lesson_status (id)
);
create table if not exists user_course
(
    user_id   integer not null,
    foreign key (user_id) references users (id),
    course_id integer not null,
    foreign key (course_id) references courses (id)
);
