From ubuntu:22.04

# sets the working directory
WORKDIR /app

# update apt
RUN apt update && apt-get upgrade

# install jdk-19
RUN apt install -y openjdk-19-jdk

# install mongodb
RUN apt-get install gnupg curl && \
    curl -fsSL https://pgp.mongodb.com/server-6.0.asc | sudo gpg -o /usr/share/keyrings/mongodb-server-6.0.gpg --dearmor && \
    echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-6.0.gpg ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/6.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-6.0.list && \
    apt-get install -y mongodb-org

# copy the .jar package
COPY build/libs/demo-0.0.1-SNAPSHOT.jar .

# Start the MongoDB service
CMD ["service", "mongodb", "start"]

# Start the Spring Boot application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

