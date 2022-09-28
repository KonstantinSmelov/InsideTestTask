create schema if not exists smelov;

create table smelov.users
(
    id       bigint auto_increment primary key,
    name     varchar,
    password varchar(100)
);

-- insert into smelov.users (name, password) values ('user123', '$2a$08$bF19RnoQLJJoSN7eWdi1MuqYgkd/wt/jLOeYwn6bgM5aa7JasefVW');
-- insert into smelov.users (name, password) values ('user123456', '$2a$08$enhQMtKC5gNjqaN8T7/9m.PJV1EfcLxbnHCKZAIEt1RRd8MsM4nni');