CREATE DATABASE `<db_name>`;
USE `<db_name>`;
CREATE TABLE `message_journal` (
  `sequence_number` int(11) NOT NULL AUTO_INCREMENT,
  `serialized_message_send` blob,
  PRIMARY KEY (`sequence_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
