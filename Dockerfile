FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar arquivos do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Baixar dependências
RUN ./mvnw dependency:go-offline -B

# Copiar código fonte
COPY src src

# Compilar o projeto e criar o JAR
RUN ./mvnw clean package -DskipTests

# Expor porta da aplicação
EXPOSE 8080

# Executar o JAR gerado (CORREÇÃO PRINCIPAL)
ENTRYPOINT ["java", "-jar", "target/movieflix-0.0.1-SNAPSHOT.jar"]