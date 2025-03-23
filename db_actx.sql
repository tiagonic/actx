-- Tabela Empresa
CREATE TABLE Empresa (
    id_empresa SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    endereco VARCHAR(255),
    uf VARCHAR(2) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

-- Tabela Produto
CREATE TABLE Produto (
    id_produto SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    codigo_barras VARCHAR(13) UNIQUE NOT NULL,
    preco_unitario NUMERIC(10, 2) NOT NULL CHECK (preco_unitario >= 0),
    data_validade DATE NOT NULL CHECK (data_validade >= CURRENT_DATE),
    deleted BOOLEAN DEFAULT FALSE
);

-- Tabela Nota_Fiscal
CREATE TABLE Nota_Fiscal (
    id_nota_fiscal SERIAL PRIMARY KEY,
    numero_nota_fiscal VARCHAR(20) NOT NULL,
    data_emissao DATE NOT NULL,
    id_empresa INTEGER NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

-- Tabela Item_Nota_Fiscal
CREATE TABLE Item_Nota_Fiscal (
    id_item_nota_fiscal SERIAL PRIMARY KEY,
    id_nota_fiscal INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    preco_unitario NUMERIC(10, 2),
    FOREIGN KEY (id_nota_fiscal) REFERENCES Nota_Fiscal(id_nota_fiscal),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

-- Tabela Imposto
CREATE TABLE Imposto (
    id_imposto SERIAL PRIMARY KEY,
    tipo_imposto VARCHAR(3) CHECK(tipo_imposto IN ('IBS', 'CBS', 'IVA')),
    aliquota NUMERIC(5, 2) CHECK (aliquota >= 0)
);

-- Tabela Cobranca
CREATE TABLE Cobranca (
    id_cobranca SERIAL PRIMARY KEY,
    id_nota_fiscal INTEGER NOT NULL,
    id_imposto INTEGER NOT NULL,
    valor_imposto NUMERIC(10, 2) CHECK (valor_imposto >= 0),
    FOREIGN KEY (id_nota_fiscal) REFERENCES Nota_Fiscal(id_nota_fiscal),
    FOREIGN KEY (id_imposto) REFERENCES Imposto(id_imposto)
);

-- Tabela Credito_Tributario
CREATE TABLE Credito_Tributario (
    id_credito_tributario SERIAL PRIMARY KEY,
    id_empresa INTEGER NOT NULL,
    id_imposto INTEGER NOT NULL,
    valor_credito NUMERIC(10, 2) CHECK (valor_credito >= 0),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa),
    FOREIGN KEY (id_imposto) REFERENCES Imposto(id_imposto)
);

-- Tabela Consumidor
CREATE TABLE Consumidor (
    id_consumidor SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL
);

-- Tabela Cashback
CREATE TABLE Cashback (
    id_cashback SERIAL PRIMARY KEY,
    id_consumidor INTEGER NOT NULL,
    id_nota_fiscal INTEGER NOT NULL,
    valor_cashback NUMERIC(10, 2) CHECK (valor_cashback >= 0),
    FOREIGN KEY (id_consumidor) REFERENCES Consumidor(id_consumidor),
    FOREIGN KEY (id_nota_fiscal) REFERENCES Nota_Fiscal(id_nota_fiscal)
);

-- Tabela Estoque
CREATE TABLE Estoque (
    id_estoque SERIAL PRIMARY KEY,
    id_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 0 CHECK (quantidade >= 0),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

-- Tabela Historico_Estoque
CREATE TABLE Historico_Estoque (
    id_historico_estoque SERIAL PRIMARY KEY,
    id_estoque INTEGER NOT NULL,
    data_movimentacao DATE NOT NULL DEFAULT CURRENT_DATE,
    tipo_movimentacao VARCHAR(10) CHECK(tipo_movimentacao IN ('ENTRADA', 'SAIDA')),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    FOREIGN KEY (id_estoque) REFERENCES Estoque(id_estoque)
);

-- Tabela Fornecedor
CREATE TABLE Fornecedor (
   id_fornecedor SERIAL PRIMARY KEY,
   nome VARCHAR(255) NOT NULL,
   cnpj VARCHAR(14) UNIQUE NOT NULL,
   endereco VARCHAR(255),
   uf VARCHAR(2) NOT NULL,
   cidade VARCHAR(50) NOT NULL,
   telefone VARCHAR(20)
);

-- Tabela Fornecedor_Empresa
CREATE TABLE Fornecedor_Empresa (
   id_fornecedor INTEGER NOT NULL,
   id_empresa INTEGER NOT NULL,
   PRIMARY KEY (id_fornecedor, id_empresa),
   FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor),
   FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

-- Tabela Item_Nota_Fiscal_Fornecedor
CREATE TABLE Item_Nota_Fiscal_Fornecedor (
   id_item_nota_fiscal INTEGER NOT NULL REFERENCES Item_Nota_Fiscal(id_item_nota_fiscal),
   id_fornecedor INTEGER NOT NULL REFERENCES Fornecedor(id_fornecedor),
   valor_imposto NUMERIC(10, 2) NOT NULL CHECK (valor_imposto >= 0),
   PRIMARY KEY (id_item_nota_fiscal, id_fornecedor)
);

-- Índices para melhorar o desempenho das consultas
CREATE INDEX idx_empresa_cnpj ON Empresa(cnpj);
CREATE INDEX idx_produto_codigo_barras ON Produto(codigo_barras);
CREATE INDEX idx_nota_fiscal_id_empresa ON Nota_Fiscal(id_empresa);
CREATE INDEX idx_item_nota_fiscal_id_nota_fiscal ON Item_Nota_Fiscal(id_nota_fiscal);
CREATE INDEX idx_item_nota_fiscal_id_produto ON Item_Nota_Fiscal(id_produto);
CREATE INDEX idx_cobranca_id_nota_fiscal ON Cobranca(id_nota_fiscal);
CREATE INDEX idx_cobranca_id_imposto ON Cobranca(id_imposto);
CREATE INDEX idx_consumidor_cpf ON Consumidor(cpf);
CREATE INDEX idx_fornecedor_cnpj ON Fornecedor(cnpj);
CREATE INDEX idx_item_nota_fiscal_fornecedor_id_item_nota_fiscal ON Item_Nota_Fiscal_Fornecedor(id_item_nota_fiscal);
CREATE INDEX idx_item_nota_fiscal_fornecedor_id_fornecedor ON Item_Nota_Fiscal_Fornecedor(id_fornecedor);
CREATE INDEX idx_fornecedor_empresa_id_fornecedor ON Fornecedor_Empresa(id_fornecedor);
CREATE INDEX idx_fornecedor_empresa_id_empresa ON Fornecedor_Empresa(id_empresa);


CREATE TABLE Usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL, -- Armazena a senha criptografada
    email VARCHAR(255) UNIQUE NOT NULL,
    data_cadastro TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE Grupo (
    id_grupo SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);

CREATE TABLE Usuario_Grupo (
    id_usuario INTEGER NOT NULL,
    id_grupo INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_grupo),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_grupo) REFERENCES Grupo(id_grupo)
);

CREATE TABLE Permissao (
    id_permissao SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);

CREATE TABLE Grupo_Permissao (
    id_grupo INTEGER NOT NULL,
    id_permissao INTEGER NOT NULL,
    PRIMARY KEY (id_grupo, id_permissao),
    FOREIGN KEY (id_grupo) REFERENCES Grupo(id_grupo),
    FOREIGN KEY (id_permissao) REFERENCES Permissao(id_permissao)
);

CREATE TABLE Usuario_Permissao (
    id_usuario INTEGER NOT NULL,
    id_permissao INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_permissao),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_permissao) REFERENCES Permissao(id_permissao)
);

CREATE TABLE Menu (
    id_menu SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    id_menu_pai INTEGER,
    ordem INTEGER,
    FOREIGN KEY (id_menu_pai) REFERENCES Menu(id_menu)
);

CREATE TABLE Menu_Permissao (
    id_menu INTEGER NOT NULL,
    id_permissao INTEGER NOT NULL,
    PRIMARY KEY (id_menu, id_permissao),
    FOREIGN KEY (id_menu) REFERENCES Menu(id_menu),
    FOREIGN KEY (id_permissao) REFERENCES Permissao(id_permissao)
);

INSERT INTO Usuario (nome, login, senha, email, cpf, ativo) VALUES
('João Silva', 'joao', 'senha_criptografada', 'joao@exemplo.com', '11111111111', TRUE),
('Maria Souza', 'maria', 'senha_criptografada', 'maria@exemplo.com', '22222222222', TRUE),
('Pedro Oliveira', 'pedro', 'senha_criptografada', 'pedro@exemplo.com', '33333333333', TRUE),
('Ana Costa', 'ana', 'senha_criptografada', 'ana@exemplo.com', '44444444444', TRUE);

INSERT INTO Grupo (nome, descricao) VALUES
('Administradores', 'Acesso total ao sistema'),
('Gerentes', 'Acesso a relatórios, gerenciamento de estoque e clientes'),
('Vendedores', 'Acesso a vendas, produtos e clientes'),
('Fiscais', 'Acesso a relatórios de vendas e estoque'),
('Auditores', 'Acesso somente leitura a relatórios e dados');

INSERT INTO Permissao (nome, descricao) VALUES
('Criar', 'Inserir novos registros'),
('Ler', 'Consultar registros existentes'),
('Atualizar', 'Alterar registros existentes'),
('Deletar', 'Excluir registros existentes'),
('Gerenciar', 'Acesso a funcionalidades administrativas');


INSERT INTO Usuario_Grupo (id_usuario, id_grupo) VALUES
(1, 3), -- João Silva é Vendedor
(2, 2), -- Maria Souza é Gerente
(3, 5), -- Pedro Oliveira é Auditor
(4, 1); -- Ana Costa é Administradora


INSERT INTO Grupo_Permissao (id_grupo, id_permissao) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), -- Administradores têm todas as permissões
(2, 2), (2, 3), -- Gerentes têm permissão de leitura e atualização
(3, 1), (3, 2), (3, 3), -- Vendedores têm permissão de criação, leitura e atualização
(4, 2), -- Fiscais têm permissão de leitura
(5, 2); -- Auditores têm permissão de leitura


INSERT INTO Usuario_Permissao (id_usuario, id_permissao) VALUES
(2, 1), (2, 3); -- Maria Souza (Gerente) tem permissões adicionais de criação e atualização


INSERT INTO Menu (nome, url, id_menu_pai, ordem) VALUES
('Vendas', '/vendas', NULL, 1),
('Realizar Venda', '/vendas/realizar', 1, 1),
('Consultar Vendas', '/vendas/consultar', 1, 2),
('Cancelar Venda', '/vendas/cancelar', 1, 3),
('Produtos', '/produtos', NULL, 2),
('Cadastrar Produto', '/produtos/cadastrar', 5, 1),
('Consultar Produtos', '/produtos/consultar', 5, 2),
('Alterar Produto', '/produtos/alterar', 5, 3),
('Estoque', '/estoque', NULL, 3),
('Movimentar Estoque', '/estoque/movimentar', 9, 1),
('Consultar Estoque', '/estoque/consultar', 9, 2),
('Clientes', '/clientes', NULL, 4),
('Cadastrar Cliente', '/clientes/cadastrar', 12, 1),
('Consultar Clientes', '/clientes/consultar', 12, 2),
('Alterar Cliente', '/clientes/alterar', 12, 3),
('Relatórios', '/relatorios', NULL, 5),
('Vendas por Período', '/relatorios/vendas-periodo', 16, 1),
('Estoque por Produto', '/relatorios/estoque-produto', 16, 2),
('Clientes por Região', '/relatorios/clientes-regiao', 16, 3),
('Administração', '/administracao', NULL, 6),
('Gerenciar Usuários', '/administracao/usuarios', 20, 1),
('Gerenciar Grupos', '/administracao/grupos', 20, 2),
('Gerenciar Permissões', '/administracao/permissoes', 20, 3),
('Backup e Restore', '/administracao/backup-restore', 20, 4);


INSERT INTO Menu_Permissao (id_menu, id_permissao) VALUES
(1, 2), (1, 3), (1, 5), -- Vendas: Leitura, Atualização, Gerenciamento
(2, 1), (2, 5), -- Realizar Venda: Criação, Gerenciamento
(3, 2), (3, 5), -- Consultar Vendas: Leitura, Gerenciamento
(4, 4), (4, 5), -- Cancelar Venda: Deleção, Gerenciamento
(5, 2), (5, 3), (5, 5), -- Produtos: Leitura, Atualização, Gerenciamento
(6, 1), (6, 5), -- Cadastrar Produto: Criação, Gerenciamento
(7, 2), (7, 5), -- Consultar Produtos: Leitura, Gerenciamento
(8, 3), (8, 5), -- Alterar Produto: Atualização, Gerenciamento
(9, 2), (9, 3), (9, 5), -- Estoque: Leitura, Atualização, Gerenciamento
(10, 1), (10, 5), -- Movimentar Estoque: Criação, Gerenciamento
(11, 2), (11, 5), -- Consultar Estoque: Leitura, Gerenciamento
(12, 2), (12, 3), (12, 5), -- Clientes: Leitura, Atualização, Gerenciamento
(13, 1), (13, 5), -- Cadastrar Cliente: Criação, Gerenciamento
(14, 2), (14, 5), -- Consultar Clientes: Leitura, Gerenciamento
(15, 3), (15, 5), -- Alterar Cliente: Atualização, Gerenciamento
(16, 2), (16, 5), -- Relatórios: Leitura, Gerenciamento
(17, 2), (17, 5), -- Vendas por Período: Leitura, Gerenciamento
(18, 2), (18, 5), -- Estoque por Produto: Leitura, Gerenciamento
(19, 2), (19, 5), -- Clientes por Região: Leitura, Gerenciamento
(20, 5), -- Administração: Gerenciamento
(21, 5), -- Gerenciar Usuários: Gerenciamento
(22, 5), -- Gerenciar Grupos: Gerenciamento
(23, 5), -- Gerenciar Permissões: Gerenciamento
(24, 5); -- Backup e Restore: Gerenciamento
