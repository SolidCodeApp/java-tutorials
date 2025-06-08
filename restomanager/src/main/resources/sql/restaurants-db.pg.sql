-- Création de la table restaurant
CREATE TABLE IF NOT EXISTS restaurant (
    id SERIAL PRIMARY KEY,
    owner VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,  
    address VARCHAR(255),  
    identifier CHAR(13) NOT NULL UNIQUE,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Création de la table restaurant_ticket
CREATE TABLE IF NOT EXISTS restaurant_ticket (
    id SERIAL PRIMARY KEY,
    hasOrdered BOOLEAN DEFAULT TRUE,
    priority VARCHAR(10) NOT NULL,
    identifier CHAR(13) NOT NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    restaurantId INT NOT NULL, 
    CONSTRAINT UK_ticket_restaurantId_identifier UNIQUE (restaurantId, identifier),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id) ON DELETE CASCADE
);

-- Création de la table restaurant_table
CREATE TABLE IF NOT EXISTS restaurant_table (
    id SERIAL PRIMARY KEY,
    nbPlaces SMALLINT CHECK (nbPlaces > 0),  -- Vérifier que le nombre de places est positif
    isAvailable BOOLEAN DEFAULT TRUE,
    identifier CHAR(13) NOT NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    restaurantId INT NOT NULL, 
    CONSTRAINT UK_table_restaurantId_identifier UNIQUE (restaurantId, identifier),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id) ON DELETE CASCADE
);

-- Création de la table restaurant_plate
CREATE TABLE IF NOT EXISTS restaurant_plate (
    id SERIAL PRIMARY KEY,
    size VARCHAR(6) DEFAULT 'MEDIUM',
    isAvailable BOOLEAN DEFAULT TRUE, 
    identifier CHAR(13) NOT NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    restaurantId INT NOT NULL, 
    CONSTRAINT UK_plate_restaurantId_identifier UNIQUE (restaurantId, identifier),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id) ON DELETE CASCADE
);

-- Création de la table restaurant_menu
CREATE TABLE IF NOT EXISTS restaurant_menu (
    id SERIAL PRIMARY KEY,
    identifier CHAR(13) NOT NULL,
    isspecial BOOLEAN DEFAULT TRUE,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    restaurantId INT NOT NULL, 
    CONSTRAINT UK_menu_restaurantId_identifier UNIQUE (restaurantId, identifier),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id) ON DELETE CASCADE
);

-- Création de la table restaurant_menu_item
CREATE TABLE IF NOT EXISTS restaurant_menu_item (
    id SERIAL PRIMARY KEY,
    price DECIMAL(10, 2), 
    name VARCHAR(100) NOT NULL,
    description TEXT,  
    identifier CHAR(13) NOT NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    menuId INT NOT NULL, 
    CONSTRAINT UK_menu_item_menuId_identifier UNIQUE (menuId, identifier),
    FOREIGN KEY (menuId) REFERENCES restaurant_menu(id) ON DELETE CASCADE
);

-- Création de la table restaurant_order
CREATE TABLE IF NOT EXISTS restaurant_order (
    id SERIAL PRIMARY KEY,
    nbPersons SMALLINT CHECK (nbPersons >= 0),  -- Vérifier que le nombre de personnes est positif
    identifier CHAR(13) NOT NULL,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(6) NOT NULL,
    priority VARCHAR(10) NOT NULL,
    bill DECIMAL(15, 2),
    ticketId INT DEFAULT NULL, 
    restaurantId INT NOT NULL, 
    CONSTRAINT UK_order_restaurantId_identifier UNIQUE (restaurantId, identifier),
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id) ON DELETE CASCADE,
    FOREIGN KEY (ticketId) REFERENCES restaurant_ticket(id) ON DELETE SET NULL
);

-- Création de la table restaurant_order_dish
CREATE TABLE IF NOT EXISTS restaurant_order_dish (
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantity SMALLINT CHECK (quantity >= 0),  -- Vérifier que la quantité est positive
    orderId INT NOT NULL, 
    menuItemId INT NOT NULL, 
    plateId INT NOT NULL,  
    PRIMARY KEY(orderId, menuItemId),
    FOREIGN KEY (menuItemId) REFERENCES restaurant_menu_item(id) ON DELETE CASCADE,
    FOREIGN KEY (orderId) REFERENCES restaurant_order(id) ON DELETE CASCADE,
    FOREIGN KEY (plateId) REFERENCES restaurant_plate(id) ON DELETE CASCADE
);

-- Création de la table restaurant_order_table
CREATE TABLE IF NOT EXISTS restaurant_order_table (
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    orderId INT NOT NULL, 
    tableId INT NOT NULL,  
    PRIMARY KEY(orderId, tableId),
    FOREIGN KEY (tableId) REFERENCES restaurant_table(id) ON DELETE CASCADE,
    FOREIGN KEY (orderId) REFERENCES restaurant_order(id) ON DELETE CASCADE
);
