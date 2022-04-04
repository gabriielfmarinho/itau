create table accounts (
    id bigint not null auto_increment,
    date_create datetime(6),
    date_update datetime(6),
    account_number integer not null,
    account_type varchar(255),
    agency_number integer not null,
    client_id bigint,
    primary key (id)
);