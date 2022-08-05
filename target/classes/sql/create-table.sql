DROP TABLE employees;
DROP TABLE department;

CREATE TABLE IF NOT EXISTS department
(
    id  BIGSERIAL   NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    salary INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS employees
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    FIO        VARCHAR(60) NOT NULL,
    age        INTEGER     NOT NULL,
    ip_address VARCHAR(16) NOT NULL,
    department_id BIGINT REFERENCES department (id)
);

insert into department (id, name, salary)
values (1, 'Front-end developer', 1200);
insert into department (id, name, salary)
values (2, 'Back-end Java developer', 1600);
insert into department (id, name, salary)
values (3, 'Back-end C# developer', 1600);

insert into employees (id, FIO, age,ip_address,department_id)
values (1, 'Shitov Sergey Vladimirovich', 20,'192.168.15.44',2);
insert into employees (id, FIO, age,ip_address,department_id)
values (2, 'Nagaev Vadim Olegovich', 21,'192.168.11.28',3);
insert into employees (id, FIO, age,ip_address,department_id)
values (3, 'Zapolskih Vladislav Konstantinovich', 20,'192.168.12.32',1);