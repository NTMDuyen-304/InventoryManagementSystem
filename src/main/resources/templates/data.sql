-- Dữ liệu cho bảng Product
INSERT INTO Product (name, category, quantity) VALUES ('Bút bi Thiên Long', 'Văn phòng phẩm', 150);
INSERT INTO Product (name, category, quantity) VALUES ('Vở Campus', 'Văn phòng phẩm', 300);
INSERT INTO Product (name, category, quantity) VALUES ('Giấy A4', 'Văn phòng phẩm', 500);
INSERT INTO Product (name, category, quantity) VALUES ('Chuột Logitech M185', 'Thiết bị điện tử', 50);
INSERT INTO Product (name, category, quantity) VALUES ('Bàn phím cơ Akko', 'Thiết bị điện tử', 30);
INSERT INTO Product (name, category, quantity) VALUES ('USB 32GB Kingston', 'Thiết bị điện tử', 100);
INSERT INTO Product (name, category, quantity) VALUES ('Ổ cứng SSD 500GB', 'Thiết bị điện tử', 40);
INSERT INTO Product (name, category, quantity) VALUES ('Tai nghe Sony WH-1000XM4', 'Âm thanh', 20);
INSERT INTO Product (name, category, quantity) VALUES ('Loa Bluetooth JBL', 'Âm thanh', 25);
INSERT INTO Product (name, category, quantity) VALUES ('Máy in HP LaserJet', 'Thiết bị điện tử', 15);
INSERT INTO Product (name, category, quantity) VALUES ('Băng keo trong', 'Văn phòng phẩm', 200);
INSERT INTO Product (name, category, quantity) VALUES ('Kẹp giấy lớn', 'Văn phòng phẩm', 120);
INSERT INTO Product (name, category, quantity) VALUES ('Bìa nhựa A4', 'Văn phòng phẩm', 250);
INSERT INTO Product (name, category, quantity) VALUES ('Máy tính Casio FX580VN', 'Thiết bị điện tử', 60);
INSERT INTO Product (name, category, quantity) VALUES ('Cáp sạc Lightning', 'Phụ kiện', 80);
INSERT INTO Product (name, category, quantity) VALUES ('Sạc dự phòng Anker', 'Phụ kiện', 35);
INSERT INTO Product (name, category, quantity) VALUES ('Túi đựng laptop', 'Phụ kiện', 45);
INSERT INTO Product (name, category, quantity) VALUES ('Giấy note vàng', 'Văn phòng phẩm', 300);
INSERT INTO Product (name, category, quantity) VALUES ('Bảng trắng mini', 'Văn phòng phẩm', 70);
INSERT INTO Product (name, category, quantity) VALUES ('Bút dạ quang', 'Văn phòng phẩm', 190);

-- Dữ liệu cho bảng InventoryLog
-- Giả sử id sản phẩm từ 1 đến 20 tương ứng như trên
-- IMPORT = nhập kho, EXPORT = xuất kho

-- IMPORT logs
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (1, 'IMPORT', 100, SYSTIMESTAMP - INTERVAL '20' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (2, 'IMPORT', 200, SYSTIMESTAMP - INTERVAL '18' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (3, 'IMPORT', 500, SYSTIMESTAMP - INTERVAL '17' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (4, 'IMPORT', 60, SYSTIMESTAMP - INTERVAL '15' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (5, 'IMPORT', 30, SYSTIMESTAMP - INTERVAL '14' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (6, 'IMPORT', 100, SYSTIMESTAMP - INTERVAL '13' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (7, 'IMPORT', 50, SYSTIMESTAMP - INTERVAL '12' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (8, 'IMPORT', 20, SYSTIMESTAMP - INTERVAL '11' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (9, 'IMPORT', 30, SYSTIMESTAMP - INTERVAL '10' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (10, 'IMPORT', 10, SYSTIMESTAMP - INTERVAL '9' DAY);

-- EXPORT logs
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (1, 'EXPORT', 50, SYSTIMESTAMP - INTERVAL '7' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (2, 'EXPORT', 60, SYSTIMESTAMP - INTERVAL '6' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (3, 'EXPORT', 100, SYSTIMESTAMP - INTERVAL '5' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (4, 'EXPORT', 10, SYSTIMESTAMP - INTERVAL '5' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (5, 'EXPORT', 5, SYSTIMESTAMP - INTERVAL '4' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (6, 'EXPORT', 25, SYSTIMESTAMP - INTERVAL '3' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (7, 'EXPORT', 15, SYSTIMESTAMP - INTERVAL '3' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (8, 'EXPORT', 5, SYSTIMESTAMP - INTERVAL '2' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (9, 'EXPORT', 10, SYSTIMESTAMP - INTERVAL '1' DAY);
INSERT INTO InventoryLog (product_id, action, amount, created_at) VALUES (10, 'EXPORT', 2, SYSTIMESTAMP);
