#!/bin/bash

# Definir variáveis de ambiente do DB2
/database/config/db2inst1/sqllib/db2profile

# Verificar se o gerenciador de banco de dados DB2 já está em execução
echo "Verificando se o gerenciador de banco de dados DB2 já está em execução..."
db2 "LIST ACTIVE DATABASES"

# Parar o gerenciador de banco de dados DB2 (forçar, se necessário)
echo "Parando o gerenciador de banco de dados DB2 (se necessário)..."
db2stop force

# Iniciar o gerenciador de banco de dados DB2
echo "Iniciando o gerenciador de banco de dados DB2..."
db2start

# Verificar se o gerenciador de banco de dados iniciou corretamente
db2 "LIST ACTIVE DATABASES"

# Se o gerenciador de banco de dados DB2 não puder ser iniciado, verificar logs
if [ $? -ne 0 ]; then
  echo "Erro ao iniciar o gerenciador de banco de dados DB2. Verificando logs..."
  cat /database/config/db2inst1/sqllib/db2dump/db2diag.log
  exit 1
fi

# Criar o banco de dados BLUDB se não existir
echo "Criando o banco de dados BLUDB se não existir..."
db2 "CREATE DATABASE BLUDB"

# Conectar ao banco de dados DB2
echo "Conectando ao banco de dados DB2..."
db2 "CONNECT TO BLUDB USER db2inst1 USING myPassword"

# Criar a tabela tb_usuario
echo "Criando a tabela tb_usuario..."
db2 "CREATE TABLE tb_usuario (
    id INTEGER NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
)"
db2 "COMMIT"

# Importar dados do arquivo tabulado (sem cabeçalho)
echo "Importando dados do arquivo tabulado (sem cabeçalho)..."
db2 "IMPORT FROM '/workdir/usuarios_tab.txt' OF DEL MODIFIED BY COLDEL0x09 INSERT INTO tb_usuario (id, login, senha, email)"
db2 "COMMIT"

# Importar dados do arquivo delimitado por ponto-e-vírgula (com cabeçalho)
echo "Importando dados do arquivo delimitado por ponto-e-vírgula (com cabeçalho)..."
db2 "IMPORT FROM '/workdir/usuarios_delimitado.txt' OF DEL MODIFIED BY COLDEL; METHOD P (1, 2, 3, 4) INSERT INTO tb_usuario (id, login, senha, email)"
db2 "COMMIT"

# Consultar dados na tabela
echo "Consultando dados na tabela tb_usuario..."
db2 "SELECT * FROM tb_usuario"

# Atualizar dados
echo "Atualizando dados na tabela tb_usuario..."
db2 "UPDATE tb_usuario SET email = 'novoemail@example.com' WHERE id = 1"
db2 "COMMIT"

# Deletar dados
echo "Deletando dados na tabela tb_usuario..."
db2 "DELETE FROM tb_usuario WHERE id = 2"
db2 "COMMIT"

# Contar registros
echo "Contando registros na tabela tb_usuario..."
db2 "SELECT COUNT(*) FROM tb_usuario"

# Adicionar coluna à tabela
echo "Adicionando coluna telefone à tabela tb_usuario..."
db2 "ALTER TABLE tb_usuario ADD COLUMN telefone VARCHAR(20)"
db2 "COMMIT"

echo "Testes concluídos."

# Desconectar do banco
db2 "CONNECT RESET"
db2 "TERMINATE"
