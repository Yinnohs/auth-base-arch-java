CREATE TABLE IF NOT EXISTS users(
    id bigint not null primary key,
    first_name  varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) unique not null,
    created_at timestamp,
    last_update timestamp
);

create sequence if not exists users_seq increment by 2;