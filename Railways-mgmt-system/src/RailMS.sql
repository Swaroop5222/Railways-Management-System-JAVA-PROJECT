CREATE DATABASE IF NOT EXISTS RailwaySystem;

USE RailwaySystem;

CREATE TABLE Login(
User_Id Varchar(20),
Password_Id Varchar(20)
);

CREATE TABLE Trains(
Train_no int(5),
Train_name varchar(50),
Src varchar(50),
Destination varchar(50),
Arrival_time varchar(10),
Final_time varchar(10),
Arrival_date varchar(20),
Train_type varchar(100),
Seats int(5) default 30
);

CREATE TABLE Employees(
Employee_Id int(10),
Employee_name varchar(30),
Profession varchar(30),
Division varchar(30),
City varchar(30),
Salary varchar(20),
Qualification varchar(20),
Dob varchar(20),
Joining_year varchar(20)
);

CREATE TABLE Passengers(
Name varchar(255),
Contact_no varchar(10),
Age varchar(10),
Sex varchar(10),
Train_no varchar(5),
Pnr_no varchar(10),
Src varchar(255),
Destination varchar(255),
Travel_date varchar(20),
Ticket_status varchar(50)
);

INSERT INTO Login VALUES('12345','5222');
