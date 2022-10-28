create table workers
(
    idworkers   int auto_increment
        primary key,
    first_name  varchar(30) not null,
    last_name   varchar(30) not null,
    patronimic  varchar(30) null,
    salary      double      not null,
    total_earn  double      null,
    id_employer int         not null,
    constraint id_employer
        foreign key (id_employer) references employers (idemployers)
            on update cascade on delete cascade
);

create index idEmployer_idx
    on workers (id_employer);

