alter table keys_pix add constraint account_fk foreign key (account_id) references accounts (id);