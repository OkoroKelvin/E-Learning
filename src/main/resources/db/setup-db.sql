create database ileiwedb;

create user 'ileiwe_user'@'localhost' identified by 'Ileiwe123';
grant all privileges on ileiwedb.* to 'ileiwe_user'@'localhost';
flush privileges