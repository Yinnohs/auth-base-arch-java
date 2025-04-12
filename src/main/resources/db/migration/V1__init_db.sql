CREATE TABLE IF NOT EXISTS users(
    id bigint not null primary key,
    first_name  varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) unique not null,
    created_at timestamp,
    last_update timestamp
);

CREATE TABLE IF NOT EXISTS accounts(
    id bigint not null primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    refresh_token varchar(1000),
    user_id bigint not null,
    role varchar(255) not null,
    created_at timestamp,
    last_update timestamp,
    constraint fk_user_account foreign key(user_id) references users(id)
);

create sequence if not exists users_seq increment by 50;
create sequence if not exists accounts_seq increment by 50;