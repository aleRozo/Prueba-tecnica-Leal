INSERT INTO usuario(identificacion, nombre, apellido, puntos) VALUES ('1', 'Alejandra', 'Rozo', 10);

INSERT INTO establecimiento(nit, nombre, puntosDisponibles, valorPunto) VALUES ('1', 'Falabella', 1000, 3000);

INSERT INTO tipotransaccion(codigo, nombre) VALUES ('ACUMULACION', 'ACUMULACION');
INSERT INTO tipotransaccion(codigo, nombre) VALUES ('REDENCION', 'REDENCION');

INSERT INTO parametro(codigo, valor) VALUES ('PUNTOS_MINIMOS_PARA_REDIMIR', '10');