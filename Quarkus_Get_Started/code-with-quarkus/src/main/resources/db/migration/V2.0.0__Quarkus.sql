-- Creating users
insert into user_roles (role)
values ('student');
insert into user_roles (role)
values ('professor');

insert into users (firstname, lastname, email, password, role_id)
values ('Grygor', 'Smyshko', 'Grygor@gmail.com', 'password', 1);
insert into users (firstname, lastname, email, password, role_id)
values ('Alex', 'Sybri', 'AlSyb@gmail.com', 'password1', 1);
insert into users (firstname, lastname, email, password, role_id)
values ('Antony', 'Ulin', 'AnUl@gmail.com', 'password2', 1);
insert into users (firstname, lastname, email, password, role_id)
values ('Petr', 'Petrenko', 'petr@gmail.com', 'password3', 2);
insert into users (firstname, lastname, email, password, role_id)
values ('Sydor', 'Sydorenko', 'sydor@gmail.com', 'password4', 2);

-- Creating courses
insert into course_level (level)
values ('zero');
insert into course_level (level)
values ('first');
insert into course_level (level)
values ('second');
insert into course_level (level)
values ('third');

insert into course_status(status)
values ('pending');
insert into course_status(status)
values ('completed');

insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Math', 'Introductory course in mathematics',2,4,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Trigonometry', 'Introductory course in Trigonometry',2,4,2);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Geometry', 'Introductory course in Geometry',1,4,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Algebra', 'Introductory course in Algebra',2,4,4);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Plain Forms', 'Introductory course in Plain Forms',1,4,1);

insert into courses (name, annotation, course_status, professor_id, course_level)
values ('English', 'Introductory course in English discipline',1,5,3);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Spanish', 'Introductory course in Spanish discipline',2,5,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('German', 'Introductory course in German discipline',2,5,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Ukrainian', 'Introductory course in Ukrainian discipline',2,5,1);

insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Science', 'Introductory course in Science discipline',2,5,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Chemistry', 'Introductory course in Chemistry discipline',2,5,1);
insert into courses (name, annotation, course_status, professor_id, course_level)
values ('Economics', 'Introductory course in Economics discipline',2,5,1);