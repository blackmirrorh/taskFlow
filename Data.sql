DROP DATABASE IF EXISTS taskflow;

CREATE DATABASE IF NOT EXISTS taskFlow;

USE taskFlow;

DROP TABLE IF EXISTS App_User;
CREATE TABLE IF NOT EXISTS App_User(
	username VARCHAR(15) NOT NULL,
    name VARCHAR (50),
    password VARCHAR(15) NOT NULL,
    team VARCHAR(15) NOT NULL,
    role VARCHAR(15) NOT NULL,
    PRIMARY KEY (username)
);

DROP TABLE IF EXISTS Task;
CREATE TABLE IF NOT EXISTS Task(
	id_task bigint NOT NULL auto_increment,
	username VARCHAR(20) NOT NULL,
    description VARCHAR (50),
    start_date date,
    end_date date,
    elapsed_time int default 0,
    assigned_by VARCHAR(15) NOT NULL,
    priority varchar(10) NOT NULL,
    task_status VARCHAR(15) NOT NULL,
    PRIMARY KEY (id_task)
);
/*
DROP TABLE Team;
CREATE TABLE IF NOT EXISTS Team(
	team_name VARCHAR(15) NOT NULL,
    username VARCHAR(15) NOT NULL,
    PRIMARY KEY (team_name)
);
*/

INSERT INTO App_User (username, name, password, team, role) VALUES ("aladmin123", "Luis Gerardo Ramirez Cabrera", "qwerty", "CobraKai", "Manager");
INSERT INTO App_User (username, name, password, team, role) VALUES ("elLider99", "Juanito Perez", "abc123", "CobraKai", "Lead");
INSERT INTO App_User (username, name, password, team, role) VALUES ("elDeveloper007", "Panchito Lopez", "esteesmip4ss", "CobraKai", "Developer");

INSERT INTO Task (username, description, assigned_by, priority, task_status) VALUES ("elDeveloper007", "Agregar boton para enviar formulario", "elLider99", "Low", "Started");
INSERT INTO Task (username, description, assigned_by, priority, task_status) VALUES ("elDeveloper007", "Agregar boton para borrar formulario", "elLider99", "Medium", "Completed");
INSERT INTO Task (username, description, assigned_by, priority, task_status) VALUES ("elDeveloper007", "Agregar boton para actualizar formulario", "elLider99", "High", "To_do");
/*
INSERT INTO Team (team_name, username) VALUES ("CobraKai", "aladmin123");
INSERT INTO Team (team_name, username) VALUES ("CobraKai", "elDeveloper007");
INSERT INTO Team (team_name, username) VALUES ("CobraKai", "elLider99");
*/
SELECT * FROM Task