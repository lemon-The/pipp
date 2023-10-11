create table battle (date date, id bigserial not null, name varchar(255), primary key (id));
create table battle_participants (battles_id bigint not null, participants_id bigint not null);
create table country (id bigserial not null, name varchar(255), side varchar(255), primary key (id));
create table warship (commission_date date, decommission_date date, country_id bigint, id bigserial not null, name varchar(255), ship_class varchar(255), primary key (id));

alter table if exists battle_participants add constraint bp_participants_fk foreign key (participants_id) references warship;
alter table if exists battle_participants add constraint bp_battles_fk foreign key (battles_id) references battle;
alter table if exists warship add constraint w_country_fk foreign key (country_id) references country;


insert into country values (1, 'USSR', 'ALLIES');
insert into country values (2, 'USA', 'ALLIES');
insert into country values (3, 'Britain', 'ALLIES');
