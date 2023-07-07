create table appointment
(
    client_id  integer not null,
    id         integer not null,
    master_id  integer not null,
    service_id integer not null,
    window_id  integer not null,
    created    timestamp(6) with time zone,
    status     varchar(255) check (status in ('RESERVED', 'DECLINED', 'FINISHED')),
    primary key (id)
);

create table client
(
    id            integer                     not null,
    date_birth    timestamp(6) with time zone not null,
    name          varchar(255)                not null,
    phone         varchar(255)                not null,
    status        varchar(255) check (status in ('NEW', 'OLD')),
    telegram_nick varchar(255)                not null,
    primary key (id)
);

create table contact
(
    id              integer      not null,
    insta_link      varchar(255) not null,
    telegram_nick   varchar(255) not null,
    viber_link      varchar(255) not null,
    watsupp_link    varchar(255) not null,
    work_insta_link varchar(255) not null,
    primary key (id)
);

create table master
(
    contact_id integer      not null,
    id         integer      not null,
    name       varchar(255) not null,
    primary key (id)
);

create table master_specialities
(
    master_id    integer not null,
    specialities varchar(255)
);

create table schedule
(
    id           integer                     not null,
    master_id    integer                     not null,
    step_in_hour integer                     not null,
    work_from    timestamp(6) with time zone not null,
    work_to      timestamp(6) with time zone not null,
    primary key (id)
);

create table schedule_booked_slots
(
    booked_slots integer,
    schedule_id  integer not null
);

create table service
(
    id           integer        not null,
    master_id    integer        not null,
    price        numeric(38, 2) not null,
    service_name varchar(255) check (service_name in ('MANICURE', 'PEDICURE', 'NAILS_CURE')),
    primary key (id)
);

create table time_slot
(
    id              integer                     not null,
    work_from       timestamp(6) with time zone not null,
    work_to         timestamp(6) with time zone not null,
    time_slot_state varchar(255) check (time_slot_state in ('BUSY', 'FREE')),
    primary key (id)
);

alter table master_specialities
    add constraint fk_master_specialities_master_id
        foreign key (master_id)
            references master;

alter table schedule_booked_slots
    add constraint fk_schedule_booked_slots_schedule_id
        foreign key (schedule_id)
            references schedule;
