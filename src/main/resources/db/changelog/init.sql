CREATE SCHEMA IF NOT EXISTS auth;
CREATE SCHEMA IF NOT EXISTS spr;
CREATE SCHEMA IF NOT EXISTS business;

create table if not exists auth.user_detail
(
    id        uuid primary key,
    username  varchar(50) not null,
    email     varchar(150),
    firstname varchar(150),
    lastname  varchar(150),
    temporal  bool        not null
);

create table spr.business_type
(
    id       uuid primary key,
    value_ru varchar(255),
    value_kz varchar(255),
    archived boolean
);
create table business.business
(
    id               uuid primary key,
    name             varchar(255),
    address          varchar(255),
    payment_types    text[],
    business_type_id uuid references spr.business_type,
    created_by_user  varchar(255),
    updated_by_user  varchar(255),
    created_at       timestamp,
    updated_at       timestamp,
    archived         boolean
);
create table business.user_business
(
    id          uuid primary key,
    user_roles  VARCHAR(255),
    user_id     uuid references auth.user_detail,
    business_id uuid references business.business
);

