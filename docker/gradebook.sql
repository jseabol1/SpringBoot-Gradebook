CREATE DATABASE Gradebook
GO

USE Gradebook;
--DROP AND RECREATE TABLES



DROP TABLE IF EXISTS Submission;
DROP TABLE IF EXISTS Assignment;
DROP TABLE IF EXISTS AssignmentType;
DROP TABLE IF EXISTS Registration;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Teacher;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Address;



CREATE TABLE Address (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	StreetLine1 VARCHAR(30) NOT NULL,
	StreetLine2 VARCHAR(30),
	City VARCHAR(30) NOT NULL,
	State VARCHAR(2) NOT NULL,
	PostalCode INT NOT NULL
);

CREATE TABLE Person (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	FirstName VARCHAR(30) NOT NULL,
	LastName VARCHAR(30) NOT NULL,
	AddressID INT FOREIGN KEY REFERENCES Address(ID) NOT NULL
);

CREATE TABLE Student (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	PersonID INT FOREIGN KEY (ID) REFERENCES Person(ID) NOT NULL,
	Major VARCHAR(16) NOT NULL
);


CREATE TABLE Teacher (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	PersonID INT FOREIGN KEY REFERENCES Person(ID) NOT NULL,
	Rank VARCHAR(3) NOT NULL, --This would really be another table
	Department VARCHAR(3) NOT NULL  --This would really be another table
);

CREATE TABLE Course (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TeacherID INT FOREIGN KEY REFERENCES Teacher(ID) NOT NULL,
	Name VARCHAR(32) NOT NULL,
	DateStart DATE NOT NULL,
	DateEnd DATE NOT NULL
);

CREATE TABLE Registration (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CourseID INT FOREIGN KEY REFERENCES Course(ID) NOT NULL,
	StudentID INT FOREIGN KEY REFERENCES Student(ID) NOT NULL,
	CONSTRAINT UniqueRegistration UNIQUE(CourseID,StudentID)
);

CREATE TABLE AssignmentType (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	Name VARCHAR(16) NOT NULL,
	Weight NUMERIC(3,0) NOT NULL
);

CREATE TABLE Assignment (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CourseID INT FOREIGN KEY REFERENCES Course(ID) NOT NULL,
	AssignmentTypeID INT FOREIGN KEY REFERENCES AssignmentType(ID) NOT NULL,
	Name VARCHAR(32) NOT NULL,
	PointsAvailable INT DEFAULT(100) NOT NULL,
	DateAssigned DATE NOT NULL,
	DateDue DATE NOT NULL,
	IsRepeatable BIT DEFAULT(0) NOT NULL,
	HasBonusPoints BIT DEFAULT(0) NOT NULL,
	TotalAverage INT,
	HighScoreAverage INT
);

CREATE TABLE Submission (
	ID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	StudentID INT FOREIGN KEY REFERENCES Student(ID) NOT NULL,
	AssignmentID INT FOREIGN KEY REFERENCES Assignment(ID) NOT NULL,
	PointsAwarded INT, --Null Allowed because submitted != Graded 
	DateSubmitted DATE NOT NULL,
	Comment VARCHAR(120),
	CONSTRAINT pointsAwardedNotNegative CHECK (PointsAwarded >= 0)
	);

	GO

--SEED DATA

--Guide
SET IDENTITY_INSERT dbo.Address ON;
INSERT INTO Address (ID,StreetLine1,StreetLine2,City,State,PostalCode)
	VALUES 
		(1,'123 MadeUp St', 'Apt B2', 'Beverly Hills','CA', 90210),
		(2,'456 NotReal Ave', NULL ,'Barboursville','WV',25504),
		(3,'1200 Ocean Blvd',NULL,'Ocean City','OC',01010),
		(4,'867 53rd St',NULL, 'Pinch','WV',75309);
SET IDENTITY_INSERT dbo.Address OFF;

--Person
SET IDENTITY_INSERT dbo.Person ON;
INSERT INTO Person(ID,FirstName,LastName,AddressID)
	VALUES
		(1,'Clint','Eastwood', 1),
		(2, 'Griffin', 'McElroy', 3),
		(3, 'Robert', 'Martin', 2),
		(4,'Bob', 'Dole', 4);
SET IDENTITY_INSERT dbo.Person OFF;

--Teacher
SET IDENTITY_INSERT dbo.Teacher ON;
INSERT INTO Teacher(ID,PersonID,Rank,Department)
	VALUES
		(1,3, 'SR', 'CS');
SET IDENTITY_INSERT dbo.Teacher OFF;

--Student
SET IDENTITY_INSERT dbo.Student ON;
INSERT INTO Student(ID,PersonID,Major)
	VALUES
		(1, 2,'CS'),
		(2, 1,'CS'),
		(3, 4,'CIT');
SET IDENTITY_INSERT dbo.Student OFF;

--Course
SET IDENTITY_INSERT dbo.Course ON;
INSERT INTO Course(ID,TeacherID,Name,DateStart,DateEnd)
	VALUES 
		(1, 1,'Agile Programming', '09-08-2020', '03-19-2021'),
		(2, 1,'App Design', '01-01-2021', '03-19-2021');
SET IDENTITY_INSERT dbo.Course OFF;

--Registration
SET IDENTITY_INSERT dbo.Registration ON;
INSERT INTO Registration(ID,CourseID,StudentID)
	VALUES
		( 1, 1,	1 ),
		( 2, 1, 2 ),
		( 3, 1, 3 ),
		( 4, 2, 1 ),
		( 5, 2, 2 );
SET IDENTITY_INSERT dbo.Registration OFF;

--AssignmentType
SET IDENTITY_INSERT dbo.AssignmentType ON;
INSERT INTO AssignmentType(ID, Name, Weight)
	VALUES
		(1, 'Test',20),
		(2, 'Journal',15),
		(3, 'Lab',15),
		(4, 'Project',20),
		(5, 'Final',30);
SET IDENTITY_INSERT dbo.AssignmentType OFF;


--Assignment
SET IDENTITY_INSERT dbo.Assignment ON;
INSERT INTO Assignment ( ID, CourseID, AssignmentTypeID, Name, DateAssigned,DateDue)
	VALUES
		( 1, 2, 1, 'Chapter 4 Test',	'03-05-2021', '03-12-2021'),
		( 2, 2, 2, 'Chapter 4 Journal',	'03-05-2021', '03-12-2021'),
		( 3, 2, 3, 'Chapter 4 Lab','03-12-2021', '03-19-2021'),
		( 4, 1, 5, 'Final',  '03-12-2021', '03-19-2021' ),
		( 5, 2, 3, 'Chapter 7 Lab', '03-12-2021', '03-19-2021'),
		( 6, 1, 4, 'Chapter 5 Project', '03-12-2021', '03-19-2021');
SET IDENTITY_INSERT dbo.Assignment OFF;	

--Submission
INSERT INTO Submission ( StudentID, AssignmentID, PointsAwarded, DateSubmitted, Comment)
	VALUES
		( 1, 1, 86, '03-11-2021', 'Nice'),
		( 2, 1, 100, '03-11-2021', 'Cool Beans'),
		( 3, 1, 5,'03-11-2021', 'Do better'),
		( 3, 1, 99, '03-12-2021', 'This is better'),
		( 1, 2, 66, '03-12-2021', 'Hmm, no?'),
		( 2, 2, 100, '03-12-2021', 'Awesome' ),
		( 3, 2, 8, '03-12-2021', 'Bob, this is a prob'),
		( 1, 3, 55, '03-19-2021', 'Keep it up'),
		( 2, 3, 88, '03-19-2021', 'Try again'),
		( 2, 3, 100, '03-19-2021', 'Excellent'),
		( 3, 3, 2, '03-19-2021', 'Really?'),
		( 1, 4, 99, '03-19-2021', 'Keep it up'),
		( 2, 4, 0, '03-19-2021', 'You have to do the work, you know that, right?'),
		( 1, 4, 100, '03-19-2021', 'Excellent'),
		( 3, 4, 2, '03-19-2021', 'I expected nothing more'),
		( 1, 5, 99, '03-19-2021', 'Keep it up'),
		( 2, 5, 100, '03-19-2021', 'GREAT!'),
		( 1, 5, 100, '03-19-2021', 'Excellent'),
		( 3, 5, 2, '03-19-2021', 'ok'),
		( 1, 6, 99, '03-19-2021', 'Keep it up'),
		( 2, 6, 75, '03-19-2021', 'Very okay'),
		( 1, 6, 100, '03-19-2021', 'Excellent'),
		( 3, 6, 2, '03-19-2021', null);

GO

--VIEWS

CREATE OR ALTER VIEW BestGrades AS
SELECT StudentID, AssignmentID, Max(PointsAwarded) as BestSubmission
	FROM Submission
		GROUP BY StudentID, AssignmentID

GO

CREATE OR ALTER VIEW AverageGrades AS
SELECT StudentID, AssignmentID, AVG(PointsAwarded) as AverageSubmissionGrade
	FROM Submission
		GROUP BY StudentID, AssignmentID

GO


--STORED PROCEDURES

CREATE OR ALTER PROCEDURE usp_UnenrollStudent
@studentID int,
@courseID int
AS
	DELETE FROM Submission
		WHERE @studentID = StudentID;

	DELETE FROM Registration
		WHERE StudentID = @studentID
		AND CourseID = @courseID;

GO

CREATE OR ALTER PROCEDURE usp_UpdateAverages
AS
UPDATE Assignment
SET TotalAverage = (SELECT AVG(AverageSubmissionGrade) FROM dbo.AverageGrades WHERE Assignment.ID = AverageGrades.AssignmentID);

UPDATE Assignment
SET HighScoreAverage = (SELECT AVG(BestSubmission) FROM dbo.BestGrades WHERE Assignment.ID = BestGrades.AssignmentID);

GO


--TRIGGERS

CREATE OR ALTER TRIGGER	trigger_SubmissionInserted
ON Submission
AFTER INSERT
AS
EXEC dbo.usp_UpdateAverages

GO

CREATE OR ALTER TRIGGER	trigger_SubmissionUpdated
ON Submission
AFTER UPDATE
AS
EXEC dbo.usp_UpdateAverages

GO

CREATE OR ALTER TRIGGER	trigger_SubmissionDeleted
ON Submission
AFTER DELETE
AS
EXEC dbo.usp_UpdateAverages

GO


