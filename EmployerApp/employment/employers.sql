create table employers
(
    idemployers    int auto_increment
        primary key,
    first_name     varchar(30) not null,
    last_name      varchar(30) not null,
    patronimic     varchar(30) null,
    phone_number   varchar(18) null,
    adress_company varchar(40) null
);

