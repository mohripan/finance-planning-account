# Build stage
FROM maven:3.6.3-jdk-11 as build
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application
RUN mvn clean package

# Run stage
FROM tomcat:9-jdk11-openjdk
WORKDIR /usr/local/tomcat/webapps/

# Copy the built WAR file
COPY --from=build /app/target/*.war .

EXPOSE 8080
CMD ["catalina.sh", "run"]
