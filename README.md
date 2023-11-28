# Plataforma de vagas 

O projeto se trata de uma api de uma plataforma de vagas com autenticação de usuário e persistência de dados em um banco de dados MySql.

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos em Java, ministrada pelo professor Henrique Mota.

### Tecnologias utilizadas.
- Java
- Spring Framework
- Spring Boot
- MySql
- Docker
- JWT

### Como executar o projeto localmente

O arquivo compilado já está no diretório /target. Mas caso queira compilar localmente execute seguinte comando do maven (O projeto usa Java 21).

`mvn package -DskipTests`

O argumento 'skipTests' é necessário pois o perfil ativo da aplicação vai tentar se conectar ao container MySql que ainda não está funcionando. 

Compile a imagem presente no Dockerfile

``docker-compose build``

Com a imagem compilada basta executar: 

`docker-compose up`

Com isso o docker vai fazer o download da imagem necessária do MySql e executar a aplicação.

A aplicação pode reiniciar algumas vezes esperando o container do MySql ficar pronto.
