Visão Geral

Este repositório documenta a evolução de uma aplicação JavaFX desenvolvida para integrar novos colaboradores na equipe de software. A aplicação foi dividida em módulos progressivos, com o objetivo de introduzir conceitos importantes de desenvolvimento, boas práticas e tecnologias utilizadas no dia a dia da equipe.

Estrutura do Projeto

A evolução do projeto é organizada em cinco fases principais:

Projeto 01: Configuração Inicial e Primeira Aplicação JavaFX

Objetivos:

Configurar o ambiente de desenvolvimento Java.

Introduzir conceitos básicos de JavaFX e Git Flow.

Entregas:

Ambiente Java configurado.

Repositório Git com tags organizadas.

Arquivo .jar gerado da aplicação inicial.

Detalhes:
A aplicação inclui uma interface básica desenvolvida com JavaFX, utilizando FXML para organização da interface.

Projeto 02: Separando Frontend e Backend

Objetivos:

Reestruturar o projeto para separar o frontend e o backend.

Adotar uma arquitetura mais modular.

Entregas:

Dois repositórios (frontend e backend) com tags de versão.

Ajustes no frontend para consumir os dados disponibilizados pelo backend.

Detalhes:
O backend passa a atuar como uma biblioteca que gerencia os dados da aplicação, enquanto o frontend consome essas informações via métodos compartilhados.

Projeto 03: Conexão com Banco de Dados e Geração de Instalador

Objetivos:

Integrar o backend a um banco de dados PostgreSQL utilizando Hibernate.

Gerar instaladores para distribuição da aplicação.

Entregas:

Repositórios atualizados com suporte a banco de dados.

Backup do banco de dados.

Instalador funcional da aplicação.

Detalhes:
Esta fase introduz conceitos de persistência de dados e empacotamento de aplicações para distribuição.

Projeto 04: API REST e Integração Contínua

Objetivos:

Implementar uma API REST no backend utilizando Spring Boot.

Ajustar o frontend para consumir a API.

Configurar integração contínua com TeamCity.

Entregas:

API REST documentada com Swagger.

Builds configuradas no TeamCity para testes, geração de instaladores e deploy.

Instalador gerado através da pipeline de integração contínua.

Detalhes:
Essa fase reforça a modularização e introduz práticas modernas de desenvolvimento e entrega de software.

Projeto 05: Testes Unitários

Objetivos:

Implementar testes unitários para o frontend e backend.

Utilizar frameworks como JUnit e Mockito.

Entregas:

Testes unitários cobrindo os principais fluxos da aplicação.

Relatórios de cobertura de código.

Detalhes:
A fase finaliza o ciclo de desenvolvimento com foco em qualidade e manutenção do código.

Tecnologias Utilizadas

Backend:

Java, Hibernate, Spring Boot

Frontend:

JavaFX, FXML

Banco de Dados:

PostgreSQL

Outras Ferramentas:

Git, Git Flow, TeamCity, Swagger, JUnit, Mockito

Instruções de Uso

Clone os repositórios de frontend e backend.

Configure o banco de dados conforme especificado na documentação do Projeto 03.

Execute os testes e builds utilizando os scripts fornecidos.

Gere o instalador através do TeamCity ou manualmente.

Para mais informações sobre cada fase, consulte os respectivos diretórios no repositório.
