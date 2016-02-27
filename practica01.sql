CREATE TABLE capturista(
id SERIAL PRIMARY KEY,
nombres TEXT NOT NULL,
apellido_p TEXT NOT NULL, 
apellido_m TEXT NOT NULL);

CREATE TABLE venta(
id SERIAL PRIMARY KEY,
total REAL NOT NULL,
neto REAL NOT NULL,
impuestos REAL NOT NULL
);
CREATE TABLE captura(
fecha date NOT NULL,
id_capturista INT NOT NULL, 
id_venta INT NOT NULL, 
FOREIGN KEY (id_capturista) REFERENCES capturista(id),
FOREIGN KEY (id_venta) REFERENCES venta(id)
);