# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the project jar file to the container
COPY target/my-project.jar app.jar

# Create a new user to run the application
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose port 8080 for the application
EXPOSE 8080

# Set JVM options
ENV JAVA_OPTS=""

# Add build caching
VOLUME /tmp
ARG BUILD_DATE
RUN echo $BUILD_DATE > build-date.txt
COPY target/my-project.jar app.jar
RUN java -Xmx256m -Xss256k -jar /app/app.jar --thin.archive.root=/app --thin.dryrun=true && rm -rf /app/*

# Run the application
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar
