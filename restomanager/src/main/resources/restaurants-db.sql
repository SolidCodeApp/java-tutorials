CREATE DATABASE IF NOT EXISTS `restaurants_db`;

USE `restaurants_db`;

CREATE TABLE IF NOT EXISTS `restaurant` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `owner` VARCHAR(50) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `description` TEXT default null,
    `address` VARCHAR(255) default null,
    `identifier` char(6) NOT NULL UNIQUE,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `restaurant_ticket` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `hasOrdered` BOOLEAN DEFAULT TRUE,
    `priority` VARCHAR(10) NOT NULL,
    `identifier` CHAR(6) NOT NULL,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `restaurantId`INT(11) UNSIGNED NOT NULL, 
    UNIQUE(`restaurantId`, `identifier`),
    FOREIGN KEY (`restaurantId`) REFERENCES `restaurant`(`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `restaurant_table` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `nbPlaces` TINYINT DEFAULT 4,
    `isAvailable` BOOLEAN DEFAULT TRUE,
    `identifier` CHAR(6) NOT NULL,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `restaurantId`INT(11) UNSIGNED NOT NULL, 
    UNIQUE(`restaurantId`, `identifier`),
    FOREIGN KEY (`restaurantId`) REFERENCES `restaurant`(`id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `restaurant_plate` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `size` VARCHAR(6) DEFAULT 'MEDIUM',
    `isAvailable` BOOLEAN DEFAULT TRUE,
    `identifier` CHAR(6) NOT NULL,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `restaurantId`INT(11) UNSIGNED NOT NULL, 
    UNIQUE(`restaurantId`, `identifier`),
    FOREIGN KEY (`restaurantId`) REFERENCES `restaurant`(`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `restaurant_menu` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `identifier` CHAR(6) NOT NULL,
    `isspecial` BOOLEAN DEFAULT TRUE,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `restaurantId`INT(11) UNSIGNED NOT NULL, 
    UNIQUE(`restaurantId`, `identifier`),
    FOREIGN KEY (`restaurantId`) REFERENCES `restaurant`(`id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `restaurant_menu_item` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `price`DECIMAL (10, 2), 
    `name` VARCHAR(100) NOT NULL,
    `description` TEXT default null,
    `identifier` char(6) NOT NULL UNIQUE,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `menuId` INT(11) UNSIGNED NOT NULL, 
    FOREIGN KEY (`menuId`) REFERENCES `restaurant_menu`(`id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `restaurant_order` (
    `id` INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `nbPersons` SMALLINT UNSIGNED NOT NULL, 
    `identifier` CHAR(6) NOT NULL,
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `status` CHAR(6) NOT NULL,
   `priority` VARCHAR(10) NOT NULL,
   `bill` DECIMAL (15, 2),
    `ticketId` INT(11) UNSIGNED DEFAULT NULL, 
    `restaurantId` INT(11) UNSIGNED NOT NULL, 
    UNIQUE(`restaurantId`, `identifier`),
    FOREIGN KEY (`restaurantId`) REFERENCES `restaurant`(`id`),
    FOREIGN KEY (`ticketId`) REFERENCES `restaurant_ticket`(`id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `restaurant_order_dish` (
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `quantity`SMALLINT UNSIGNED NOT NULL, 

    `orderId` INT(11) UNSIGNED NOT NULL, 
    `menuItemId` INT(11) UNSIGNED NOT NULL, 
    `plateId` INT(11) UNSIGNED NOT NULL,  

    PRIMARY KEY(`orderId`, `menuItemId`),

    FOREIGN KEY (`menuItemId`) REFERENCES `restaurant_menu_item`(`id`),
    FOREIGN KEY (`orderId`) REFERENCES `restaurant_order`(`id`),
    
    FOREIGN KEY (`plateId`) REFERENCES `restaurant_plate`(`id`)

) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `restaurant_order_table` (
    `creationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `orderId` INT(11) UNSIGNED NOT NULL, 
    `tableId` INT(11) UNSIGNED NOT NULL,  

    PRIMARY KEY(`orderId`, `tableId`),

    FOREIGN KEY (`tableId`) REFERENCES `restaurant_table`(`id`),
    FOREIGN KEY (`orderId`) REFERENCES `restaurant_order`(`id`)
) ENGINE = InnoDB;