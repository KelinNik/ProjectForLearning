--liquibase formatted sql
--changeset Nick:1 splitStatements:true endDelimiter:;
create table if not exists accountModel
(
    account_id  varchar(128) not null,
    first_name  varchar(128) not null,
    second_name varchar(128),

    constraint acc_id unique (account_id)
);

create index if not exists account_index on accountModel(account_id);