create table if not exists account
(
    account_id  int generated always as identity,
    first_name  varchar(128) not null,
    second_name varchar(128),
    is_active   bool,

    constraint acc_id unique (account_id)
);

insert into account(first_name, is_active)
values ('John', true)