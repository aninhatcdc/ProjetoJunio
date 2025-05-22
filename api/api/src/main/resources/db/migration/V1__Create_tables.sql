CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE lancamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    data DATE NOT NULL,
    ativo BOOLEAN NOT NULL,
    pessoa_id INTEGER NOT NULL,
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);
