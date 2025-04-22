CREATE TABLE IF NOT EXISTS users(
    id bigint not null primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) unique not null,
    created_at timestamp,
    last_update timestamp
);

CREATE TABLE IF NOT EXISTS roles(
    id bigint not null primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    created_at timestamp,
    last_update timestamp
);

CREATE TABLE IF NOT EXISTS permissions(
    id bigint not null primary key,
    name varchar(255) not null,
    description varchar(500)
);

CREATE TABLE IF NOT EXISTS accounts(
    id bigint not null primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    refresh_token varchar(1000),
    user_id bigint not null,
    role_id bigint not null,
    created_at timestamp,
    last_update timestamp,
    constraint fk_user_account foreign key(user_id) references users(id),
    constraint fk_role_account foreign key(role_id) references roles(id)
);

CREATE TABLE IF NOT EXISTS roles_permissions(
    permission_id bigint not null,
    role_id bigint not null,
    primary key(permission_id, role_id),
    constraint fk_permission_id_rolesPermissions foreign key(permission_id) references permissions(id),
    constraint fk_role_rolesPermissions foreign key(role_id) references roles(id)
);

create sequence if not exists users_seq increment by 50;
create sequence if not exists roles_seq increment by 50;
create sequence if not exists permissions_seq increment by 50;
create sequence if not exists accounts_seq increment by 50;