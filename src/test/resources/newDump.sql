
DROP TABLE IF EXISTS `todo`;
DROP TABLE IF EXISTS `note`;
DROP TABLE IF EXISTS `bookmark`;
DROP TABLE IF EXISTS `date`;
DROP TABLE IF EXISTS `user`;

create table user
(
    user_id        int auto_increment
        primary key,
    username       varchar(50) not null,
    first_name     varchar(50) not null,
    last_name      varchar(50) not null,
    email          varchar(50) not null
);

LOCK TABLES user WRITE;

UNLOCK TABLES;

create table todo
(
    id        int auto_increment
        primary key,
    title     varchar(20)          not null,
    content   varchar (500)        not null,
    created   datetime             not null,
    completed tinyint(1) default 0 not null,
    due_date  datetime             not null,
    user_id   int                  not null,
    constraint todo_user_id_fk
        foreign key (user_id) references user (user_id)
);

LOCK TABLES `todo` WRITE;
UNLOCK TABLES;

CREATE TABLE `note`
(
    id        int auto_increment
        primary key,
    title     varchar(20)          not null,
    content   text                 not null,
    created   datetime             not null,
    updated   datetime,
    user_id   int                  not null,
    constraint note_user_id_fk
        foreign key (user_id) references user (user_id)
);

LOCK TABLES note WRITE;
UNLOCK TABLES;
