CREATE TABLE `appusers` (
	`id` INT,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255),
	`last_name` VARCHAR(255),
	`email` VARCHAR(255),
	`role` INT NOT NULL,
	`port` INT,
	`imgurl` VARCHAR(2083),
	`enabled` INT NOT NULL,
	PRIMARY KEY (`id`)
);
