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
    status_id     integer                     not null,
    date_birth    timestamp(6) with time zone not null,
    name          varchar(255)                not null,
    phone         varchar(255)                not null,
    telegram_nick varchar(255)                not null,
    primary key (id)
);

create table client_status
(
    id      integer        not null,
    percent numeric(38, 2) not null,
    name    varchar(255)   not null,
    primary key (id)
);

create table contact
(
    id              integer not null,
    insta_link      varchar(255),
    telegram_nick   varchar(255),
    viber_link      varchar(255),
    whats_app_link  varchar(255),
    work_insta_link varchar(255),
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

create table price_menu
(
    id         integer        not null,
    master_id  integer        not null,
    price      numeric(38, 2) not null,
    service_id integer        not null,
    primary key (id)
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
    group_id integer,
    id       integer not null,
    name     varchar(255),
    primary key (id)
);

create table service_group
(
    id   integer not null,
    name varchar(255),
    primary key (id)
);

create table time_slot
(
    id              integer                     not null,
    master_id       integer                     not null,
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

ALTER TABLE public.service_group
    ADD CONSTRAINT service_group_uniq UNIQUE ("name");

ALTER TABLE public.service
    ADD CONSTRAINT service_uniq UNIQUE (group_id, "name");

ALTER TABLE public.client_status
    ADD CONSTRAINT client_status_uniq UNIQUE ("name");

ALTER TABLE public.contact
    ADD CONSTRAINT contact_check CHECK ("insta_link" is not null
        or "work_insta_link" is not null
        or "telegram_nick" is not null
        or "viber_link" is not null
        or "whats_app_link" is not null);
