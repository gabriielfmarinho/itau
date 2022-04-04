create table clients (
    id bigint not null auto_increment,
    date_create datetime(6),
    date_update datetime(6),
    first_name varchar(255) not null,
    last_name varchar(255),
    type_person varchar(255),
    primary key (id)
);