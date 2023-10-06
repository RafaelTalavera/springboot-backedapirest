-- Insertar datos en la tabla 'branchs'
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 1', 'Rua das Gaviotas', 'Eldorado', 'Estado 1', '1234567890');
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 2', 'Las Lilas 1850', 'Florianopolis', 'Estado 1', '1234567890');
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 3', 'Calle Guarani', 'Canasvieras', 'Estado 1', '1234567890');


-- Insertar datos en la tabla 'employees'
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345671, 98765431, 'Empleado 1', 'Apellido Empleado 1', '1991-01-15', '1991-01-15', 'Dirección Empleado 1', 'Gerente', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345672, 98765432, 'Empleado 2', 'Apellido Empleado 2', '1992-01-15', '1991-01-15', 'Dirección Empleado 2', 'Jefe', 2);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345673, 98765433, 'Empleado 3', 'Apellido Empleado 3', '1992-01-15', '1991-01-15', 'Dirección Empleado 3', 'Supervisor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345674, 98765434, 'Empleado 4', 'Apellido Empleado 4', '1992-01-15', '1991-01-15', 'Dirección Empleado 4', 'Cajero', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345675, 98765435, 'Empleado 5', 'Apellido Empleado 5', '1992-01-15', '1991-01-15', 'Dirección Empleado 5', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345676, 98765436, 'Empleado 6', 'Apellido Empleado 6', '1992-01-15', '1991-01-15', 'Dirección Empleado 6', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345677, 98765437, 'Empleado 7', 'Apellido Empleado 7', '1992-01-15', '1991-01-15', 'Dirección Empleado 7', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345678, 98765438, 'Empleado 8', 'Apellido Empleado 8', '1992-01-15', '1991-01-15', 'Dirección Empleado 8', 'Limpieza', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, registration, address, job, branch_id) VALUES(12345679, 98765439, 'Empleado 9', 'Apellido Empleado 9', '1992-01-15', '1991-01-15', 'Dirección Empleado 9', 'Estockista', 1);

-- Insertar datos en la tabla 'providers'
INSERT INTO providers (name, dni, phone, payment, address, email, date_at,registration, branch_id) VALUES('Proveedor 1', '123456789', '987654321', 'Método de Pago 1', 'Dirección Proveedor 1', 'proveedor1@example.com', '2023-09-25','2023-09-25',  1);

-- Insertar datos en la tabla 'customers'
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(111, 'Cliente 1', 'Apellido Cliente 1', '11-1111-11', 'Método de Pago Cliente 1', 'Dirección Cliente 1', 'cliente1@example.com', '2021-09-25', 1);
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(112, 'Cliente 2', 'Apellido Cliente 2', '22-2222-22', 'Método de Pago Cliente 2', 'Dirección Cliente 2', 'cliente2@example.com', '2022-09-25', 2);
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(113, 'Cliente 3', 'Apellido Cliente 3', '33-3333-33', 'Método de Pago Cliente 3', 'Dirección Cliente 3', 'cliente3@example.com', '2023-09-25', 1);
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(114, 'Cliente 4', 'Apellido Cliente 4', '44-4444-44', 'Método de Pago Cliente 4', 'Dirección Cliente 4', 'cliente4@example.com', '2019-09-25', 1);
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(115, 'Cliente 5', 'Apellido Cliente 5', '55-5555-55', 'Método de Pago Cliente 5', 'Dirección Cliente 5', 'cliente5@example.com', '2018-09-25', 1);
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, registration, branch_id) VALUES(116, 'Cliente 6', 'Apellido Cliente 6', '66-6666-66', 'Método de Pago Cliente 6', 'Dirección Cliente 6', 'cliente6@example.com', '2023-09-25', 1);

-- Insertar datos en la tabla 'products'
INSERT INTO products (code, name, model, description, category, brand, price_sale, stock, expiration, price_buy, branch_id) VALUES(1001, 'Producto 1', 'modelo 1', 'descripción 1', 'category 1', 'Marca 1', 10.99, 100, '2023-12-31', 8.99, 1);

-- Insertar datos en la tabla 'buys'
INSERT INTO buys (description, create_alta, employee_id, provider_id, branch_id) VALUES('Compra 1', '2023-09-25', 1, 1, 1);

-- Insertar datos en la tabla 'sale'
INSERT INTO sales (description, create_alta, employee_id, customer_id, branch_id) VALUES('Venta 1', '2023-09-25', 1, 1, 1);

-- Insertar datos en la tabla 'buy_items'
INSERT INTO buy_items (amount, product_id, buy_id) VALUES(5, 1, 1);

-- Insertar datos en la tabla 'sale_items'
INSERT INTO sale_items (amount, product_id, sale_id) VALUES(3, 1, 1);




 