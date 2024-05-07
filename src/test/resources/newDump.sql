
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
    title     varchar(25)          not null,
    content   varchar (100)        not null,
    created   datetime             not null,
    completed tinyint(1) default 0 not null,
    due_date  date                 not null,
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
    title     varchar(25)          not null,
    content   text                 not null,
    created   datetime             not null,
    updated   datetime,
    user_id   int                  not null,
    constraint note_user_id_fk
        foreign key (user_id) references user (user_id)
);

LOCK TABLES note WRITE;
UNLOCK TABLES;

CREATE TABLE `date`
(
    id        int AUTO_INCREMENT
            PRIMARY KEY,
    title     varchar(120)      NOT NULL,
    `date`    DATE              NOT NULL,
    content   varchar(500),
    user_id   INT               NOT NULL,
    constraint date_user_id
        FOREIGN KEY (user_id) REFERENCES user (user_id)
);

LOCK TABLES `date` WRITE;
UNLOCK TABLES ;

CREATE TABLE `bookmark`
(
    id        int auto_increment
        primary key,
    title         varchar(30)          not null,
    description   varchar(200)         ,
    link          varchar(100)         not null,
    user_id       int                  not null,
    constraint bookmark_user_id_fk
        foreign key (user_id) references user (user_id)
);