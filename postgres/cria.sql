CREATE TABLE banda(
    id serial,
    nome varchar (25), 
    localDeOrigem VARCHAR(100),
    integrantes VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE album(
    id serial,
    nome VARCHAR(25),
    estilo varchar (25), 
    banda int,
    anoDeLancamento VARCHAR(25),
    PRIMARY KEY (id),
    FOREIGN KEY(banda) REFERENCES Banda(id)
);