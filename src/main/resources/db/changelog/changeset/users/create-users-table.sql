create schema if not exists smelov;

create table smelov.users
(
    id   bigserial,
    name  varchar not null,
    password  varchar(100) not null,
    primary key (id)
);