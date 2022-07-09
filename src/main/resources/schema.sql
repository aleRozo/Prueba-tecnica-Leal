CREATE TABLE IF NOT EXISTS parametro (
    id INTEGER auto_increment,
    codigo VARCHAR(50) NOT NULL,
    valor VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS codigo_parametro_uk ON parametro(codigo);

CREATE TABLE IF NOT EXISTS tipoTransaccion (
    id INTEGER auto_increment,
    codigo VARCHAR(50) NOT NULL,
    nombre VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS  codigo_tipoTransaccion_uk ON tipoTransaccion(codigo);

CREATE TABLE IF NOT EXISTS usuario (
    id INTEGER auto_increment,
    identificacion VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    puntos INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS identificacion_usuario_uk ON usuario(identificacion);

CREATE TABLE IF NOT EXISTS establecimiento (
    id INTEGER auto_increment,
    nit VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    puntosDisponibles INTEGER NOT NULL DEFAULT 0,
    valorPunto INTEGER NOT NULL DEFAULT 1000,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS nit_establecimiento_uk ON establecimiento(nit);

CREATE TABLE IF NOT EXISTS transaccion (
    id INTEGER auto_increment,
    fecha TIMESTAMP NOT NULL,
    valor FLOAT,
    puntos INTEGER NOT NULL,
    estado VARCHAR(10) NOT NULL,
    idUsuario INTEGER,
    idEstablecimiento INTEGER,
    idTipoTransaccion INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(idUsuario) REFERENCES usuario(id),
    FOREIGN KEY(idEstablecimiento) REFERENCES establecimiento(id),
    FOREIGN KEY(idTipoTransaccion) REFERENCES tipoTransaccion(id)
);

