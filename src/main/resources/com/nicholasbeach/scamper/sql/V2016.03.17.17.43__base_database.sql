CREATE TABLE `account` (
  `account_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `receipt` (
  `receipt_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mime_type` varchar(100) NOT NULL,
  `file` mediumblob NOT NULL,
  PRIMARY KEY (`receipt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tag` (
  `tag_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transaction` (
  `transaction_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(10) unsigned DEFAULT NULL,
  `receipt_id` int(10) unsigned DEFAULT NULL,
  `date` date NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `amount` decimal(12,2) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `uq-receipt_id` (`receipt_id`),
  KEY `fk_transaction_receipt-idx` (`receipt_id`),
  KEY `fk_transaction_account-idx` (`account_id`),
  KEY `transaction_date-idx` (`date`),
  CONSTRAINT `fk-transaction-account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk-transaction-receipt` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`receipt_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transaction_tag` (
  `transaction_id` int(10) unsigned NOT NULL,
  `tag_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`transaction_id`,`tag_id`),
  KEY `fk_transaction_tag_tag-idx` (`tag_id`),
  CONSTRAINT `fk-transaction_tag-tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk-transaction_tag-transaction` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
