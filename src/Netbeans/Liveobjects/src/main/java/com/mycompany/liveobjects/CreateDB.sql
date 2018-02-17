CREATE DATABASE `<DBNAME>`;
USE `<DBNAME>`;

CREATE TABLE `object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_referer` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_object_parent_referer_idx` (`parent_referer`),
  CONSTRAINT `fk_object_parent_referer` FOREIGN KEY (`parent_referer`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `slot` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  KEY `holder_idx` (`object_holder_id`),
  CONSTRAINT `holder` FOREIGN KEY (`object_holder_id`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_reference` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `object_reference_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  KEY `fk_slot_reference_object_idx` (`object_reference_id`),
  CONSTRAINT `fk_slot_reference_object` FOREIGN KEY (`object_reference_id`) REFERENCES `object` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_slot_reference_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `slot_blob` (
  `object_holder_id` int(11) NOT NULL,
  `symbol` varchar(128) CHARACTER SET utf8 NOT NULL,
  `value` blob NOT NULL,
  PRIMARY KEY (`object_holder_id`,`symbol`),
  CONSTRAINT `fk_slot_blob_slot` FOREIGN KEY (`object_holder_id`, `symbol`) REFERENCES `slot` (`object_holder_id`, `symbol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (1, NULL, 0);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (2, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (2, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (2, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (3, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (3, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (3, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (4, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (4, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (4, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (5, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (5, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (5, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (6, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (6, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (6, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (7, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (7, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (7, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (8, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (8, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (8, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (9, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (9, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (9, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (10, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (10, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (10, 'parent', 1, 1);
INSERT INTO `object` (`id`,`parent_referer`,`type`) VALUES (11, NULL, 0);
INSERT INTO slot (object_holder_id, symbol, type) VALUES (11, 'parent', 0);
INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (11, 'parent', 1, 1);