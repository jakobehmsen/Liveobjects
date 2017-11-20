CREATE DATABASE `<DBNAME>`;
USE `<DBNAME>`;

CREATE TABLE `object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_referer` int(11) DEFAULT NULL,
  `usage` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_object_parent_referer_idx` (`parent_referer`),
  CONSTRAINT `fk_object_parent_referer` FOREIGN KEY (`parent_referer`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `slot` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  KEY `holder_idx` (`object_holder_id`),
  CONSTRAINT `holder` FOREIGN KEY (`object_holder_id`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_block` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `value` blob NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  CONSTRAINT `fk_slot_block_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_integer` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  CONSTRAINT `fk_slot_integer_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_reference` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `object_reference_id` int(11) NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  CONSTRAINT `fk_slot_reference_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_string` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `value` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  CONSTRAINT `fk_slot_string_255_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (1, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (2, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (3, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (4, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (5, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (6, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (7, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (8, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (9, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`usage`) VALUES (10, NULL, 0);
