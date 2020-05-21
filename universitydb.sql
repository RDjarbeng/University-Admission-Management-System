-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2020 at 04:05 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `universitydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Admin`
--

CREATE TABLE `Admin` (
  `AdminNo` int(11) NOT NULL,
  `UserName` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `AdmittedStudents`
--

CREATE TABLE `AdmittedStudents` (
  `StudentID` varchar(8) NOT NULL,
  `ApplicationID` int(11) NOT NULL,
  `ReceiptID` varchar(25) NOT NULL,
  `CourseOfStudy` varchar(50) NOT NULL,
  `Hall of Residence` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Application`
--

CREATE TABLE `Application` (
  `ApplicationID` int(11) NOT NULL,
  `ReceiptID` varchar(25) NOT NULL,
  `FirstChoiceOfStudy` varchar(50) NOT NULL,
  `SecondChoiceOfStudy` varchar(50) DEFAULT NULL,
  `ThirdChoiceOfStudy` varchar(50) DEFAULT NULL,
  `FirstHallOfResidence` varchar(25) DEFAULT NULL,
  `SecondHallOfResidence` varchar(25) DEFAULT NULL,
  `ThirdHallOfResidence` varchar(25) DEFAULT NULL,
  `WASSCEResults` varchar(50) NOT NULL,
  `ApplicationSubmissionTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Results` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ApplicationStatus`
--

CREATE TABLE `ApplicationStatus` (
  `ApplicationNumber` int(11) NOT NULL,
  `ApplicationID` int(11) NOT NULL,
  `AdmissionStatus` varchar(25) NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ReceiptID` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `MiddleName` varchar(25) DEFAULT NULL,
  `DOB` varchar(25) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Nationality` varchar(25) NOT NULL,
  `PhoneNumber` varchar(25) NOT NULL,
  `ResidentialAddress` text,
  `PostalAddress` text,
  `Email` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ReceiptID`, `LastName`, `FirstName`, `MiddleName`, `DOB`, `Gender`, `Nationality`, `PhoneNumber`, `ResidentialAddress`, `PostalAddress`, `Email`) VALUES
('kofi', 'Asante', 'Kofi', 'Babone', '2/4/1998', 'MALE', 'GHANAIAN', '+233451324567', 'Engineering School Roundabout, Java Projects Lane House NO 4', 'The Java Group 4\r\nP.O Box JV4\r\nLegon, Accra', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `Password`) VALUES
('John', '123'),
('kofi', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`AdminNo`),
  ADD UNIQUE KEY `Unique` (`UserName`);

--
-- Indexes for table `AdmittedStudents`
--
ALTER TABLE `AdmittedStudents`
  ADD KEY `ApplicationID` (`ApplicationID`);

--
-- Indexes for table `Application`
--
ALTER TABLE `Application`
  ADD PRIMARY KEY (`ApplicationID`),
  ADD KEY `ReceiptID` (`ReceiptID`);

--
-- Indexes for table `ApplicationStatus`
--
ALTER TABLE `ApplicationStatus`
  ADD PRIMARY KEY (`ApplicationNumber`),
  ADD KEY `ApplicationNumber` (`ApplicationNumber`),
  ADD KEY `ApplicationID` (`ApplicationID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ReceiptID`),
  ADD KEY `ReceiptID` (`ReceiptID`),
  ADD KEY `ReceiptID_2` (`ReceiptID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Username`),
  ADD KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Admin`
--
ALTER TABLE `Admin`
  MODIFY `AdminNo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Application`
--
ALTER TABLE `Application`
  MODIFY `ApplicationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ApplicationStatus`
--
ALTER TABLE `ApplicationStatus`
  MODIFY `ApplicationNumber` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AdmittedStudents`
--
ALTER TABLE `AdmittedStudents`
  ADD CONSTRAINT `admittedstudents_ibfk_1` FOREIGN KEY (`ApplicationID`) REFERENCES `Application` (`ApplicationID`);

--
-- Constraints for table `Application`
--
ALTER TABLE `Application`
  ADD CONSTRAINT `application_ibfk_1` FOREIGN KEY (`ReceiptID`) REFERENCES `student` (`ReceiptID`);

--
-- Constraints for table `ApplicationStatus`
--
ALTER TABLE `ApplicationStatus`
  ADD CONSTRAINT `applicationstatus_ibfk_1` FOREIGN KEY (`ApplicationID`) REFERENCES `Application` (`ApplicationID`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`ReceiptID`) REFERENCES `users` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
