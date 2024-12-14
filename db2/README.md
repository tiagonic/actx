### Resumo dos Comandos e Estrutura de Diretórios no DB2 Container

#### 1. Comandos Utilizados
Durante nossas tentativas, usamos uma série de comandos para diagnosticar e tentar configurar o DB2 dentro do container. Aqui está um resumo:

##### Comandos Básicos do Docker
- **Iniciar o Container**:
  ```sh
  docker run --rm -d --name db2 -e DB2INST1_PASSWORD=myPassword -e LICENSE=accept -p 50000:50000 -v .:/workdir ibmcom/db2
  ```
- **Verificar Containers Ativos**:
  ```sh
  docker ps
  ```
- **Acessar o Container**:
  ```sh
  docker exec -it db2-container bash
  ```

##### Comandos do DB2
- **Conectar ao DB2**:
  ```sh
  su - db2inst1
  db2
  ```
- **Verificar Instâncias do DB2**:
  ```sh
  db2ilist
  ```
- **Verificar Configuração do DB2**:
  ```sh
  db2 get dbm cfg
  ```
- **Parar a Instância do DB2**:
  ```sh
  db2stop force
  ```
- **Iniciar a Instância do DB2**:
  ```sh
  db2start
  ```
- **Criar Banco de Dados**:
  ```sh
  db2 "CREATE DATABASE BLUDB"
  ```
- **Conectar ao Banco de Dados**:
  ```sh
  db2 "CONNECT TO BLUDB USER db2inst1 USING myPassword"
  ```
- **Verificar Bancos de Dados Ativos**:
  ```sh
  db2 "LIST ACTIVE DATABASES"
  ```

#### 2. Necessidade de Usar "su - db2inst1"
O comando `su - db2inst1` é necessário para mudar o usuário para `db2inst1`, que é o usuário padrão configurado para a instância do DB2. Esse usuário possui as permissões e o ambiente configurado para administrar e operar o DB2.

#### 3. Estrutura de Diretórios
Aqui está uma visão geral dos diretórios dentro do container DB2, para que servem e o que têm dentro:

##### Diretório Root (`/`)
- **Diretórios Comuns**: Contém diretórios padrão do sistema, como `bin`, `dev`, `etc`, `home`, `lib`, `opt`, `root`, `sbin`, `usr`, e `var`.

##### Diretório `/database`
- **config/**: Contém a configuração da instância do DB2.
- **data/**: Diretório destinado a armazenar dados do banco de dados.

##### Diretório `/database/config`
- **db2fenc1/** e **db2inst1/**: Diretórios das instâncias do DB2.
- **instance.cfg**: Arquivo de configuração das instâncias.

##### Diretório `/database/config/db2inst1`
- **sqllib/**: Contém a biblioteca de software do DB2 para a instância.

##### Diretório `/database/config/db2inst1/sqllib`
- **bin/**: Executáveis do DB2.
- **cfg/**: Arquivos de configuração.
- **db2dump/**: Diretório de despejo de logs.
- **db2nodes.cfg**: Arquivo de configuração de nós DB2.
- **profile.env**: Arquivo de configuração de perfil.
- **ctrl/**: Arquivos de controle do DB2.

Este diretório também possui subdiretórios para diversos componentes e utilitários do DB2:
- **bnd/**: Diretório contendo arquivos de vinculação.
- **include/**: Cabeçalhos de arquivos de inclusão.
- **lib/**: Bibliotecas usadas pelo DB2.
- **tmp/**: Arquivos temporários.
