create schema if not exists smelov;

create table smelov.messages
(
    id   bigserial,
    name  varchar not null,
    message  varchar(100) not null,
    primary key (id)
);