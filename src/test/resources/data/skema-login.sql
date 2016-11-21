
drop table t_user;
drop table t_role;
drop table t_user_role;

create table t_user
(
    id varchar(36)PRIMARY KEY,
    username VARCHAR(100)NOT NULL,
    password VARCHAR(150)NOT NULL,
    active BOOLEAN
);

insert into t_user(id,username,password,active)
values('u001','dede','123',true);
insert into t_user(id,username,password,active)
values('u002','ica','123',true);

create table t_role
(
    id VARCHAR(36)PRIMARY KEY,
    rolename VARCHAR(100)NOT NULL
);

insert into t_role(id,rolename)
values('adm','ROLE_ADMIN');
insert into t_role(id,rolename)
values('stf','ROLE_STAFF');

create table t_user_role
(
    id_user VARCHAR(36) NOT NULL,
    id_role VARCHAR(36) NOT NULL
);

insert into t_user_role(id_user,id_role)
values('u001','adm');
insert into t_user_role(id_user,id_role)
values('u001','stf');
insert into t_user_role(id_user,id_role)
values('u002','stf');



select username,password,active as enabled from t_user where username='dede';

select r.rolename as authority
from t_user u
join t_user_role ur on ur.id_user = u.id
join t_role r on ur.id_role = r.id
where u.username='dede';