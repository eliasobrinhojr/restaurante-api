CREATE TABLE IF NOT EXISTS produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    preco DECIMAL(10,2),
    categoria VARCHAR(50)
);

INSERT INTO Produto (nome, preco, categoria) VALUES ('Coca Cola', 5.00, 'BEBIDA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Corona', 8.50, 'BEBIDA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Agua Mineral', 3.00, 'BEBIDA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Batata Frita', 10.00, 'ENTRADA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Salada Caesar', 12.00, 'ENTRADA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Hamburguer Gourmet', 22.00, 'PRATO_PRINCIPAL');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Pizza Margherita', 25.00, 'PRATO_PRINCIPAL');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Sorvete de Chocolate', 8.00, 'SOBREMESA');
INSERT INTO Produto (nome, preco, categoria) VALUES ('Cheesecake', 11.00, 'SOBREMESA');