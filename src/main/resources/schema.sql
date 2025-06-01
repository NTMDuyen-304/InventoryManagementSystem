CREATE TABLE Product (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    category VARCHAR2(255),
    quantity NUMBER
);

CREATE TABLE InventoryLog (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id NUMBER NOT NULL,
    action VARCHAR2(10) CHECK (action IN ('IMPORT', 'EXPORT')),
    amount NUMBER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES Product(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    display_name VARCHAR(50),
    title VARCHAR(50)
);