# Projeto transactions-api

Projeto de exemplo de implementação Hexagonal para uma Arquitetura baseada em Eventos.
Tem como objetivo de demonstrar os padrões de nomenclatura, as tecnologias e técnicas para criar um serviço
Java.

## Requisitos

- Java 11
  - [múltiplas versões do JDK no mesmo computador](https://dzone.com/articles/how-to-install-multiple-versions-of-java-on-the-sa)
- docker-compose
- subir o projeto no profile de dev

## Como rodar

- fazer o build `./gradlew build`
- rodar o docker compose na pasta dev: `docker-compose up -d`

## Configurações

### Gradle

Usar o [gradle](https://gradle.org) como ferramenta de gestão de dependências e configuração do
projeto, seguindo as práticas:

* Usar a última versão quando possível, para melhorar o desempenho e correção de falhas, exemplo de
  adição de dependências.

```groovy
Exemplo:

repositories {
  mavenCentral()
}

dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-actuator'
  implementation 'org.springframework.kafka:spring-kafka'
  testImplementation('org.springframework.boot:spring-boot-starter-test') {
    exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
  }
  testImplementation 'org.springframework.kafka:spring-kafka-test'
}
```

* Usar o [wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) para evitar a
  necessidade de instalar o gradle local

* configurar no mínimo os plugins:

```groovy
plugins {
  id 'org.springframework.boot' version '2.4.4'
  id 'io.spring.dependency-management' version '1.0.11.RELEASE'
  id 'java'
}
```

* configurar o [spotbugs](https://spotbugs.github.io/) para quebrar o build quando alguma regra for
  violada, evitando falhas:

 ```groovy
spotbugsMain {
  reports {
    html {
      enabled = true
      destination = file("$buildDir/reports/spotbugs/main/spotbugs.html")
      stylesheet = 'fancy-hist.xsl'
    }
  }
}
```

* Recomendamos o uso do [SonarLint](https://www.sonarlint.org) na IDE para geração de recomendações
  de boas práticas na implementação e evitar as falhas e erros mais comuns.

### Docker

O [Docker](https://www.docker.com/) é o artefato padrão para fazer o deploy nos ambientes do
Kubernetes. Para configuração é preciso criar o `Dockerfile` na raiz do projeto, com o seguinte
conteúdo:

```Dockerfile
FROM azul/zulu-openjdk-alpine:11.0.7

COPY build/libs/*.jar app.jar

CMD ["java", "-XX:MaxRAMPercentage=80.0", "-Dfile.encoding=UTF8", "-Duser.country=BR", "-Duser.language=pt", "-Duser.timezone=America/Sao_Paulo", "-jar", "app.jar"]
```

No Java 11 é possível configurar a quantidade de memória da imagem que a JVM vai
usar `-XX:MaxRAMPercentage=80.0`.

Para gerar a imagem é preciso:

* gerar o build da aplicação `./gradlew build`

* criar a imagem `docker build -t nome-da-imagem:versao`

### docker-compose

No desenvolvimento local é possível usar o [docker-compose](https://docs.docker.com/compose/) para
rodar as dependências externas, como banco de dados e Kafka, [nesse exemplo](/docker-compose.yml)
tem um banco de dados (mongo) e o zookeeper e kafka.

### Documentação de API OpenAPI e Swagger

Usamos o [Springdoc](https://springdoc.org) para gerar a documentação das APIs REST do projeto no
formato [Swagger](https://swagger.io) que pode ser acessada em http://localhost:8080/swagger-ui.html
.

Para configurar é preciso:

* Adicionar a dependência

```groovy
dependencies {
  ...
  implementation 'org.springdoc:springdoc-openapi-ui:1.4.3'
  ...
}
```

* Adicionar as configs:

```yaml
springdoc:
  swagger-ui:
    path: /swagger-ui.html
```

* habilitar a autenticação nas APIs, descrever o título e detalhes da API

```java
    @Bean
    public OpenAPI customOpenAPI2() {
        Info infoProject = new Info()
                .title("API de clientes para exemplo")
                .description("Serviço de exemplo para gerenciar transacoes")
                .version("0.0.1");
        return new OpenAPI()
                .info(infoProject)
                .addTagsItem(new Tag().name("Documentacao Transacoes"));
    }
```

* nos controllers é preciso adicionar a `@Operation` com a descrição do endpoint. Nos casos onde o
  status code for diferente de 200 adicionar `@ResponseStatus` a anotação para mapear corretamente,
  ex.

```java

```

### Organização do projeto


### Editor config

O [editorconfig](https://editorconfig.org/) é usado para manter o padrão de formatação do código
independente da IDE. Para configurar é preciso adicionar o arquivo `.editorconfig` na raiz do
projeto com o conteúdo:

```editorconfig
root = true

[*]
indent_style = space
indent_size = 2
end_of_line = lf
charset = utf-8
trim_trailing_whitespace = true
insert_final_newline = true

[*.md]
trim_trailing_whitespace = false

[*.java]
indent_style = space
indent_size = 4
continuation_indent_size = 8
```

### .gitignore

Configurar o .gitignore para otimizar o repositório, podendo gerar
no [gitignore.io](https://www.gitignore.io/).

## TODO
