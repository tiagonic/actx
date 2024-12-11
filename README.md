# ActX

ActX é um projeto desenvolvido para demonstrar habilidades técnicas em desenvolvimento de software, com foco em uma arquitetura monolítica com Web Service RESTFull para simular sistemas legados em evolução.

O sistema utiliza JSF, Angular, EJB, JAX-RS, Hibernate e PostgreSQL e fornece uma interface com o usuário tanto em JSF quanto em Angular, com uma API RESTful para comunicação.

## Visão Geral da Arquitetura

A arquitetura da aplicação proposta é monolítica e tem como objetivo fornecer uma interface com o usuário usando JSF (Jakarta Server Faces) e uma API RESTful para ser consumida por um front-end Angular v19. A camada de negócios é implementada com EJB (Enterprise JavaBeans) e os dados são persistidos usando Hibernate com JPA em um banco de dados PostgreSQL. O servidor de aplicação é o WildFly 34.0.1.Final. Os testes são realizados com JUnit e Mockito.

### Componentes Principais

1. **Front-end Angular**: Interface do usuário construída com Angular v19, que consome a API RESTful fornecida pela aplicação.
2. **JSF (Jakarta Server Faces)**: Interface do usuário baseada em componentes, usada para fornecer uma interface rica diretamente pelo back-end.
3. **API RESTful**: Recursos RESTful expostos para o front-end Angular consumir. Implementada usando JAX-RS e RESTEasy.
4. **EJB (Enterprise JavaBeans)**: Camada de serviços que contém a lógica de negócios da aplicação.
5. **DAO (Data Access Object)**: Camada de acesso a dados, responsável pela persistência e recuperação de dados usando Hibernate e JPA.
6. **PostgreSQL**: Banco de dados relacional onde os dados da aplicação são armazenados.
7. **WildFly**: Servidor de aplicação onde a aplicação é implantada e executada.
8. **Testes**: junit-jupiter e mockito-core.

### Fluxo de Dados

1. **Interface do Usuário**:
   - **Front-end Angular**: Realiza chamadas HTTP para os recursos RESTful expostos pela aplicação.
   - **JSF**: Interage diretamente com os beans gerenciados para renderizar a interface do usuário.

2. **API RESTful**:
   - Os recursos RESTful recebem as requisições HTTP do front-end Angular e as encaminham para os serviços EJB.

3. **EJB Services**:
   - Executam a lógica de negócios e fazem chamadas para a camada de DAO para acessar o banco de dados.

4. **DAO**:
   - Utiliza Hibernate e JPA para persistir e recuperar dados do PostgreSQL.

5. **PostgreSQL**:
   - Armazena os dados da aplicação e responde às consultas realizadas pela camada de DAO.

6. **Testes Unitários**:
   - Garantem a qualidade e funcionamento correto da aplicação.

### 2. Diagrama de Componentes

O Diagrama de Componentes ajuda a visualizar a estrutura do sistema, mostrando a interação entre os diferentes componentes da aplicação. Cada componente é representado como um bloco que se conecta com outros blocos, destacando as dependências entre eles.

#### Diagrama de Componentes:

```plaintext
+--------------------+    +---------------------+
|  Front-end Angular |    |  Front-end JSF      |
+--------------------+    +---------------------+
          |                         |
          v                         v
+--------------------+    +---------------------+
| RESTful Resources  |    |  JSF Backing Bean   |
+--------------------+    +---------------------+
          |                         |
          v                         v
+---------------------------------------------+
|               EJB Services                  |
+---------------------------------------------+
                      |
                      v
+---------------------------------------------+
|                    DAO                      |
+---------------------------------------------+
                      |
                      v
+---------------------------------------------+
|                PostgreSQL                   |
+---------------------------------------------+
```

#### Descrição dos Componentes:

1. **Front-end Angular**:
   - **Descrição**: Interface com o usuário construído com Angular 18, que realiza chamadas HTTP para a API RESTful fornecida pela aplicação.
   - **Dependências**: RESTful Resources (API REST).

2. **Front-end JSF**:
   - **Descrição**: Interface do usuário baseada em componentes JSF.
   - **Dependências**: JSF Backing Bean.

3. **RESTful Resources**:
   - **Descrição**: Implementa a API RESTful utilizando JAX-RS e RESTEasy.
   - **Dependências**: EJB Services.

4. **JSF Backing Bean**:
   - **Descrição**: Beans gerenciados pelo JSF, responsáveis por ligar os componentes da interface JSF à lógica de negócios.
   - **Dependências**: EJB Services.

5. **EJB Services**:
   - **Descrição**: Camada que contém a lógica de negócios da aplicação, implementada utilizando Enterprise JavaBeans.
   - **Dependências**: DAO.

6. **DAO (Data Access Object)**:
   - **Descrição**: Camada responsável pelo acesso aos dados, utilizando Hibernate com JPA para interagir com o banco de dados.
   - **Dependências**: PostgreSQL.

7. **PostgreSQL**:
   - **Descrição**: Banco de dados relacional onde os dados da aplicação são armazenados.
   - **Dependências**: Nenhuma (é a fonte de dados).

#### Interações:

- **Front-end Angular**: Faz chamadas HTTP para os **RESTful Resources**.
- **Front-end JSF**: Interage diretamente com os **JSF Backing Beans**.
- **RESTful Resources**: Faz chamadas aos **EJB Services** para a lógica de negócios.
- **JSF Backing Beans**: Interagem com os **EJB Services** para a lógica de negócios.
- **EJB Services**: Fazem chamadas ao **DAO** para persistência e recuperação de dados.
- **DAO**: Interage com o **PostgreSQL** para operações de banco de dados.

### 3. Diagrama de Sequência

O Diagrama de Sequência é essencial para mostrar a interação entre os diferentes componentes do sistema ao longo do tempo. Ele ilustra como os objetos operam entre si e em que ordem esses processos ocorrem, enfatizando a troca de mensagens entre eles.

#### Diagrama de Sequência:

```plaintext
Front-end Angular     REST Resource         EJB Service              DAO                 PostgreSQL
---------------------------------------------------------------------------------------------------
      |                      |                     |                    |                       |
      |--(API Call)--------->|                     |                    |                       |
      |                      |--(Call Service)---->|                    |                       |
      |                      |                     |--(Call DAO)------->|                       |
      |                      |                     |                    |--(Query DB)---------->|
      |                      |                     |                    |<-(Result)-------------|
      |                      |                     |<-(Result)----------|                       |
      |<-(Result)------------|                     |                    |                       |
      |                      |                     |                    |                       |
```

#### Descrição dos Passos:

1. **Front-end Angular**:
   - **Descrição**: O usuário interage com a aplicação através do front-end Angular, que faz uma chamada de API para o REST Resource.
   - **Ação**: Realiza uma chamada HTTP (API Call) para o RESTful Resource.

2. **REST Resource**:
   - **Descrição**: Recebe a chamada de API do front-end Angular e processa a requisição.
   - **Ação**: Encaminha a requisição para o EJB Service correspondente através de um método de serviço.

3. **EJB Service**:
   - **Descrição**: Contém a lógica de negócios da aplicação. Recebe a chamada do REST Resource e processa os dados.
   - **Ação**: Chama o método correspondente no DAO para acessar ou persistir dados.

4. **DAO (Data Access Object)**:
   - **Descrição**: Camada de acesso a dados que interage com o banco de dados PostgreSQL.
   - **Ação**: Executa uma consulta (Query DB) no banco de dados PostgreSQL.

5. **PostgreSQL**:
   - **Descrição**: Banco de dados relacional onde os dados da aplicação são armazenados.
   - **Ação**: Retorna os resultados da consulta (Result) para o DAO.

6. **Retorno de Resultados**:
   - **DAO**: Recebe os resultados da consulta e os retorna para o EJB Service.
   - **EJB Service**: Recebe os resultados do DAO e os retorna para o REST Resource.
   - **REST Resource**: Recebe os resultados do EJB Service e os retorna para o Front-end Angular.

Este diagrama e os passos descritos exemplificam o fluxo de dados da aplicação, destacando como cada componente interage ao longo do tempo para atender uma solicitação do usuário.

### Especificação dos Módulos e Suas Dependências

A seguir está a especificação dos módulos e suas dependências para o arquivo `pom.xml` fornecido:

#### Dependências Principais

1. **Jakarta EE API**
   - **Descrição**: Provedor padrão das APIs Jakarta EE.
   - **Grupo**: `jakarta.platform`
   - **Artefato**: `jakarta.jakartaee-api`
   - **Versão**: `${jakartaee-api.version}`
   - **Escopo**: `provided`

2. **Jakarta JAX-RS**
   - **Descrição**: API para desenvolvimento de serviços web RESTful.
   - **Grupo**: `jakarta.ws.rs`
   - **Artefato**: `jakarta.ws.rs-api`
   - **Versão**: `${jaxrs.version}`

3. **Jakarta Server Faces (JSF)**
   - **Descrição**: API para construção de interfaces web baseadas em componentes.
   - **Grupo**: `jakarta.faces`
   - **Artefato**: `jakarta.faces-api`
   - **Versão**: `${jsf.version}`

4. **PrimeFaces**
   - **Descrição**: Biblioteca de componentes UI para JSF.
   - **Grupo**: `org.primefaces`
   - **Artefato**: `primefaces`
   - **Versão**: `14.0.8`
   - **Classifier**: `jakarta`

5. **RichFaces**
   - **Descrição**: Framework para componentes visuais JSF.
   - **Grupo**: `org.richfaces.ui`
   - **Artefato**: `richfaces-components-ui`
   - **Grupo**: `org.richfaces.core`
   - **Artefato**: `richfaces-core-impl`

6. **Jakarta EJB**
   - **Descrição**: API para Enterprise JavaBeans.
   - **Grupo**: `jakarta.ejb`
   - **Artefato**: `jakarta.ejb-api`
   - **Versão**: `${ejb.version}`

7. **Hibernate ORM**
   - **Descrição**: Framework de mapeamento objeto-relacional.
   - **Grupo**: `org.hibernate.orm`
   - **Artefato**: `hibernate-core`
   - **Versão**: `${hibernate.version}`

8. **PostgreSQL JDBC Driver**
   - **Descrição**: Driver JDBC para PostgreSQL.
   - **Grupo**: `org.postgresql`
   - **Artefato**: `postgresql`
   - **Versão**: `${postgresql.version}`

#### Dependências de Teste

1. **JUnit**
   - **Descrição**: Framework para testes unitários em Java.
   - **Grupo**: `org.junit.jupiter`
   - **Artefato**: `junit-jupiter`
   - **Versão**: `5.10.5`
   - **Escopo**: `test`

2. **Mockito**
   - **Descrição**: Framework para criação de mocks em testes unitários.
   - **Grupo**: `org.mockito`
   - **Artefato**: `mockito-core`
   - **Versão**: `4.11.0`
   - **Escopo**: `test`

#### Plugins

1. **Maven Compiler Plugin**
   - **Descrição**: Plugin para compilar o código fonte Java.
   - **Grupo**: `org.apache.maven.plugins`
   - **Artefato**: `maven-compiler-plugin`
   - **Versão**: `${compiler-plugin.version}`
   - **Configuração**: 
     ```xml
     <configuration>
         <release>${maven.compiler.release}</release>
     </configuration>
     ```

2. **Maven WAR Plugin**
   - **Descrição**: Plugin para empacotar a aplicação como um arquivo WAR.
   - **Grupo**: `org.apache.maven.plugins`
   - **Artefato**: `maven-war-plugin`
   - **Versão**: `${war-plugin.version}`
   - **Configuração**: 
     ```xml
     <configuration>
         <failOnMissingWebXml>false</failOnMissingWebXml>
     </configuration>
     ```

3. **WildFly Maven Plugin**
   - **Descrição**: Plugin para gerenciar o ciclo de vida da aplicação no servidor WildFly.
   - **Grupo**: `org.wildfly.plugins`
   - **Artefato**: `wildfly-maven-plugin`
   - **Versão**: `${wildfly-plugin.version}`
   - **Configuração**: 
     ```xml
     <configuration>
         <version>${wildfly.version}</version>
         <server-config>standalone-full.xml</server-config>
     </configuration>
     ```

#### Dependency Management

1. **RichFaces BOM**
   - **Descrição**: Bill of Materials para gerenciar as versões das dependências do RichFaces.
   - **Grupo**: `org.richfaces`
   - **Artefato**: `richfaces-bom`
   - **Versão**: `${org.richfaces.bom.version}`
   - **Escopo**: `import`
   - **Tipo**: `pom`


## Configuração do Ambiente de Desenvolvimento

### Backend

1. Clone o repositório:
    ```bash
    git clone https://github.com/tiagonic/actx.git
    cd actx
    ```

2. Compile e empacote a aplicação com Maven e inicie o servidor WildFly com a aplicação:
    ```bash
    mvn clean package wildfly:dev
    ```

### Front-end em Angular

O front-end foi criado com Angular v19 e possui as seguintes funcionalidades de CRUD para usuários:

- `header`: Componente de cabeçalho.
- `footer`: Componente de rodapé.
- `homepage`: Componente da página inicial.
- `cadastro/usuario/usuario-editar`: Componente para editar usuários.
- `cadastro/usuario/usuario-pesquisar`: Componente para pesquisar usuários.
- `cadastro/usuario/usuario-listar`: Componente para listar usuários.
- `cadastro/usuario/usuario-excluir`: Componente para excluir usuários.
- `cadastro/usuario/usuario-inserir`: Componente para inserir novos usuários.
- `cadastro/usuario/usuario-service`: Serviço para comunicação com a API RESTful.

#### Frontend

1. **Instalar Angular CLI**
    - Instale o Angular CLI globalmente se ainda não tiver instalado.
    ```bash
    npm install -g @angular/cli
    ```

2. **Configuração do Projeto Angular**
    - Navegue até o diretório do frontend dentro do projeto e instale as dependências necessárias.
    ```bash
    cd frontend
    npm install
    ```

3. **Configurar Servidor de Desenvolvimento Angular**
    - Inicie o servidor de desenvolvimento Angular para testar e desenvolver o front-end.
    ```bash
    ng serve
    ```

4. **Configuração do Ambiente de Produção**
    - Para preparar o projeto Angular para produção, execute:
    ```bash
    ng build --prod
    ```

5. **Deploy do Frontend**
    - Copie os arquivos gerados na pasta `dist/` para o servidor web desejado. Por exemplo, se estiver usando o Apache:
    ```bash
    sudo cp -r dist/ /var/www/html/actx
    ```

### Testes Unitários

Os testes unitários são realizados com JUnit e Mockito para garantir a qualidade e o funcionamento correto da aplicação.

## Contribuição

Se desejar contribuir com este projeto, por favor, abra uma issue ou envie um pull request.

## Autor

**Tiago Barreto dos Santos**
- [tiagonic@gmail.com](mailto:tiagonic@gmail.com)

## Licença
Este projeto é licenciado sob os termos da licença MIT.
