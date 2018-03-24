# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-alpine

WORKDIR /app

# Copy the current directory contents into the container at /app
ADD ./target/undertow-1.0-SNAPSHOT-jar-with-dependencies.jar ./app.jar
 
EXPOSE 8888

CMD ["java", "-jar", "app.jar"]