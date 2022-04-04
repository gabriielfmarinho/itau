create table keys_pix (
    id char(36) not null,
    date_create datetime(6),
    date_update datetime(6),
    date_inactive datetime(6),
    inactive bit not null,
    key_type varchar(255),
    key_value varchar(255) not null,
    account_id bigint,
    primary key (id)
);