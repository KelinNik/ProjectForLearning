create table if not exists account
(
    account_id  serial primary key,
    first_name  varchar(128) not null,
    second_name varchar(128),
    is_active   bool,

    constraint acc_id unique (account_id)
);

create index if not exists account_index on account(account_id)