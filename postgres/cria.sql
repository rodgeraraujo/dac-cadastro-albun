CREATE TABLE albun(
    id serial,
    estilo varchar (25), 
    banda varchar (100),
    anoDeLancamento DATE,
    PRIMARY KEY (id)
);

CREATE TABLE banda(
    id serial,
    nome varchar (25), 
    localDeOrigem DATE,
    integrantes VARCHAR(100),
    PRIMARY KEY (id)
);
