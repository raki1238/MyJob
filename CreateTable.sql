CREATE TABLE `gcd`.`gcd_numbers` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `num1` int(6) NOT NULL,
  `num2` int(6) NOT NULL,
  `gcd_val` int(6) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COMMENT='Stores the gcd numbers';
