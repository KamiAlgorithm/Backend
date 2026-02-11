
CREATE DATABASE IF NOT EXISTS proyecto;
USE proyecto;


CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO usuario (nombre, email) VALUES 
('Aphelios', 'darktwin@mail.com'),
('Diana', 'darkgothic@mail.com');


SELECT * FROM usuario;