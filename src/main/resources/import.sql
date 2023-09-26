-- Insertar datos en la tabla 'branchs'
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 1', 'Rua das Gaviotas', 'Eldorado', 'Estado 1', '1234567890');
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 2', 'Las Lilas 1850', 'Florianopolis', 'Estado 1', '1234567890');
INSERT INTO branchs (name, address, city, state, phone) VALUES('Sucursal 3', 'Calle Guarani', 'Canasvieras', 'Estado 1', '1234567890');


-- Insertar datos en la tabla 'employees'
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345671, 98765431, 'Empleado 1', 'Apellido Empleado 1', '1991-01-15', 'Dirección Empleado 1', 'Gerente', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345672, 98765432, 'Empleado 2', 'Apellido Empleado 2', '1992-01-15', 'Dirección Empleado 2', 'Jefe', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345673, 98765433, 'Empleado 3', 'Apellido Empleado 3', '1993-01-15', 'Dirección Empleado 3', 'Supervisor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345674, 98765434, 'Empleado 4', 'Apellido Empleado 4', '1994-01-15', 'Dirección Empleado 4', 'Cajero', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345675, 98765435, 'Empleado 5', 'Apellido Empleado 5', '1990-01-15', 'Dirección Empleado 5', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345676, 98765436, 'Empleado 6', 'Apellido Empleado 6', '1995-01-15', 'Dirección Empleado 6', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345677, 98765437, 'Empleado 7', 'Apellido Empleado 7', '1996-01-15', 'Dirección Empleado 7', 'Vendedor', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345678, 98765438, 'Empleado 8', 'Apellido Empleado 8', '1997-01-15', 'Dirección Empleado 8', 'Limpieza', 1);
INSERT INTO employees (dni, cuit, name, lastname, birth, address, job, branch_id) VALUES(12345679, 98765439, 'Empleado 9', 'Apellido Empleado 9', '1998-01-15', 'Dirección Empleado 9', 'Estockista', 1);

-- Insertar datos en la tabla 'providers'
INSERT INTO providers (name, dni, phone, payment, address, email, date_at, branch_id) VALUES('Proveedor 1', '123456789', '987654321', 'Método de Pago 1', 'Dirección Proveedor 1', 'proveedor1@example.com', '2023-09-25', 1);

-- Insertar datos en la tabla 'customers'
INSERT INTO customers (dni, name, lastname, phone, payment, address, email, alta, branch_id) VALUES(11223344, 'Cliente 1', 'Apellido Cliente 1', '987654321', 'Método de Pago Cliente 1', 'Dirección Cliente 1', 'cliente1@example.com', '2023-09-25', 1);

-- Insertar datos en la tabla 'products'
INSERT INTO products (code, name, brand, price_sale, stock, expiration, price_buy, branch_id) VALUES(1001, 'Producto 1', 'Marca 1', 10.99, 100, '2023-12-31', 8.99, 1);

-- Insertar datos en la tabla 'buys'
INSERT INTO buys (description, create_alta, employee_id, provider_id, branch_id) VALUES('Compra 1', '2023-09-25', 1, 1, 1);

-- Insertar datos en la tabla 'sale'
INSERT INTO sales (description, create_alta, employee_id, customer_id, branch_id) VALUES('Venta 1', '2023-09-25', 1, 1, 1);

-- Insertar datos en la tabla 'buy_items'
INSERT INTO buy_items (amount, product_id, buy_id) VALUES(5, 1, 1);

-- Insertar datos en la tabla 'sale_items'
INSERT INTO sale_items (amount, product_id, sale_id) VALUES(3, 1, 1);




 