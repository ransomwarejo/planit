# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.0-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
# Construire avec le profil prod
RUN mvn clean package -Pprod -DskipTests

# Étape 2 : Exécuter l'application avec OpenJDK
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Variable d'environnement pour le mode prod
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
