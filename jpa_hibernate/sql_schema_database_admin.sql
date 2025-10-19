-- Schema da tabela produto
CREATE TABLE produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    nome VARCHAR(100) NOT NULL, 
    quantidade INTEGER, 
    preco DOUBLE PRECISION, 
    status BOOLEAN
);

-- Schema da tabela pessoa  
CREATE TABLE pessoa (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    nome VARCHAR(100) NOT NULL, 
    email VARCHAR(150), 
    idade INTEGER, 
    cpf VARCHAR(14), 
    data_nascimento DATE
);