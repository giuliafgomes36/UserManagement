# ------------ Stage 1: Build --------------

# Defines the base image for the build: linux, java 17 and maven 3.9.6.
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Working dir for the instructions that follows.
WORKDIR /app

# Copy the pom.xml file from the machine to the context.
COPY pom.xml .

# Download the dependencies.
RUN mvn dependency:go-offline

# Copy of the src code to the context: this step is done after the download of the dependencies
# because docker has a cache, this way the dependencies are only downloaded again if there is any changes
# on them, not every time the source code is altered.
COPY src ./src

# Generates .jar: if the comand returns anything but 0, docker will interupt the build.
RUN mvn clean package -DskipTests

# ------------ Stage 2: Runtime --------------

# Defines the base image for runtime: alpine linux (light image), java 17,
# maven is not necessary on this stage anymore, just for the build stage.
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

#Copy the .jar created in the last stage.
COPY --from=build /app/target/*.jar app.jar

# Command executed when the container is inicialized
CMD ["java","-jar","app.jar"]