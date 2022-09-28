create schema if not exists smelov;

create table smelov.messages
(
    id      bigint auto_increment primary key,
    user_id bigint,
    message varchar(100),
    foreign key (user_id) references smelov.users (id)
);