drop table if exists role;
drop table if exists user;
drop table if exists user_roles;
drop table if exists user_timezone;
drop table if exists timezone;

create table user (id bigint not null auto_increment, email varchar(255), password varchar(255),primary key (id)) engine=InnoDB;
create table role (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table user_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB;
create table timezone (id bigint not null auto_increment, name varchar(255), city varchar(255),hourdiff integer, mindiff integer,user_id bigint, primary key (id),FOREIGN KEY (user_id) REFERENCES user(id)) engine=InnoDB;
create table user_timezone (user_id bigint not null, timezone_id bigint not null, primary key (user_id, timezone_id)) engine=InnoDB;

INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');

INSERT INTO user (id, email, password) VALUES (500, 'admin@gmail.com', '$2a$10$JquPqGKGKSplSc/lvHpsJedbK/xqe2Vghqw.nE17tNdB.UWcIVQ7K');
INSERT INTO user_roles (user_id, role_id) VALUES (500, 4);
