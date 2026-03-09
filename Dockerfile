FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar
COPY build/libs/connect-four.jar app.jar

# Run the game interactively
ENTRYPOINT ["java", "-jar", "app.jar"]

