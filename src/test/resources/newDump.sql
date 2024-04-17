
DROP TABLE IF EXISTS `todo`;
DROP TABLE IF EXISTS `user`;

create table user
(
    user_id       int auto_increment
        primary key,
    password varchar(50) not null,
    name     varchar(50) not null
);

LOCK TABLES user WRITE;

UNLOCK TABLES;

create table todo
(
    id        int auto_increment
        primary key,
    title     varchar(20)          not null,
    content   text                 not null,
    created   varchar(10)          not null,
    completed tinyint(1) default 0 not null,
    due_date  datetime             not null,
    user_id   int                  not null,
    constraint todo_user_id_fk
        foreign key (user_id) references user (user_id)
);

LOCK TABLES `todo`WRITE;
UNLOCK TABLES;
